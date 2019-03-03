package com.practice.olegtojgildin.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.practice.olegtojgildin.data.OnForecastsReceivedListener;
import com.practice.olegtojgildin.data.entity.WeatherDayModel;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by olegtojgildin on 01/02/2019.
 */

public class DBManager {
    private DbHelper dbHelper;

    @Inject
    public DBManager(Context context) {
        this.dbHelper = new DbHelper(context);
    }


    public void addWeather(WeatherDayModel weather) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            if (db.inTransaction())
                addWeatherInternal(db, getContentValues(weather));
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (db != null) {
                if (db.inTransaction())
                    db.endTransaction();
                db.close();
            }
        }
    }

    public void removeForecasts() {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            removeForecastsInternal(db);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
    }


    public void getAllWeatherDay(final OnForecastsReceivedListener callback) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();

            String selectQuery = "SELECT  * FROM " + DbHelper.FORECAST_TABLE;
            final List<WeatherDayModel> mWeatherList=cursorToList(db,selectQuery);

            db.setTransactionSuccessful();
            new Handler(Looper.getMainLooper()).post(() -> callback.onForecastsReceived(mWeatherList));

        } catch (SQLiteException e) {
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (db != null && db.inTransaction()) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public List<WeatherDayModel> cursorToList(SQLiteDatabase db, String selectQuery){
        List<WeatherDayModel> mWeatherList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst() && db.inTransaction()) {
            do {
                mWeatherList.add(converterCursor(cursor));
            } while (cursor.moveToNext());
        }
        return mWeatherList;
    }


    private ContentValues getContentValues(WeatherDayModel weatherDay) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.DATE, weatherDay.getDt());
        contentValues.put(DbHelper.TEMPER, weatherDay.getMain().getTemp());
        contentValues.put(DbHelper.TEMP_MIN, weatherDay.getMain().getTempMin());
        contentValues.put(DbHelper.TEMP_MAX, weatherDay.getMain().getTempMax());
        contentValues.put(DbHelper.HUMIDITY, weatherDay.getMain().getHumidity());
        contentValues.put(DbHelper.PRESSURE, weatherDay.getMain().getPressure());
        return contentValues;
    }

    public WeatherDayModel converterCursor(Cursor cursor) {
        return new WeatherDayModel(cursor.getLong(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getFloat(6));

    }

    private void addWeatherInternal(SQLiteDatabase db, ContentValues contentValues) {
        db.insertOrThrow(DbHelper.FORECAST_TABLE, null, contentValues);
    }

    private void removeForecastsInternal(SQLiteDatabase db) {
        if (db != null) {
            db.delete(DbHelper.FORECAST_TABLE, null, null);
        }
    }
}
