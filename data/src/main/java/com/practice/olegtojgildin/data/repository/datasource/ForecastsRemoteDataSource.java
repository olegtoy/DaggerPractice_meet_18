package com.practice.olegtojgildin.data.repository.datasource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.practice.olegtojgildin.domain.model.WeatherDay;
import com.practice.olegtojgildin.data.local.DBManager;
import com.practice.olegtojgildin.data.remote.ApiMapper;
import com.practice.olegtojgildin.data.remote.RetrofitHelper;
import com.practice.olegtojgildin.domain.model.WeatherForecast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.practice.olegtojgildin.data.remote.ApiMapper.MODE;


public final class ForecastsRemoteDataSource implements ForecastsDataSource {

    private ApiMapper mapiMapper;
    private Context mContext;

    public ForecastsRemoteDataSource(ApiMapper apiMapper,Context context) {
        mapiMapper = apiMapper;
        mContext=context;
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void getForecast(String city) {
        List<WeatherDay> forecast;

        mapiMapper.getForecastWeatherSync("Moscow");
  /*
        DBManager dbManager = DBManager.getInstance(mContext);
        dbManager.removeForecasts();
        for (int i = 0; i < forecast.size(); i++)
            dbManager.addWeather(forecast.get(i));*/
    }
}