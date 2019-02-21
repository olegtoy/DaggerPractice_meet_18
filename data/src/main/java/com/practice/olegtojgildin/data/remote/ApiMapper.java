package com.practice.olegtojgildin.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.practice.olegtojgildin.data.OnForecastsReceivedListener;
import com.practice.olegtojgildin.data.entity.WeatherDayModel;
import com.practice.olegtojgildin.data.entity.WeatherForecast;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by olegtojgildin on 01/02/2019.
 */

public class ApiMapper {
    public static final String API_KEY="acf993bf91158e1b964db7d30554fc95";
    public static final String LANG="ru";
    public static final String UNITS="metric";
    public static final String MODE="json";

    private RetrofitHelper helper;
    private List<WeatherDayModel> mListWeather;
    private final OnForecastsReceivedListener mCallback;

    public ApiMapper(RetrofitHelper helper,OnForecastsReceivedListener callback) {
        this.helper = helper;
        mListWeather = new ArrayList<>();
        this.mCallback = callback;

    }

    private static volatile ApiMapper INSTANCE;

    public static ApiMapper getInstance(final RetrofitHelper helper, final OnForecastsReceivedListener callback) {
        ApiMapper instance = INSTANCE;
        if (instance == null) {
            synchronized (ApiMapper.class) {
                instance = INSTANCE;
                if (instance == null) {
                    instance = INSTANCE = new ApiMapper(helper,callback);
                }
            }
        }
        return instance;
    }


    public void getForecastWeatherSync(String city) {
        new RetrofitHelper().getService().getForecast("Moscow", MODE, ApiMapper.UNITS, ApiMapper.LANG, ApiMapper.API_KEY).enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(@NonNull Call<WeatherForecast> call, @NonNull Response<WeatherForecast> response) {
                if (response.isSuccessful()) {
                    final List<WeatherDayModel> models = Objects.requireNonNull(response.body()).getItems();
                    mCallback.onForecastsReceived(models);
                } else {
                    Log.e(TAG, response.code() + " " + response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<WeatherForecast> call, @NonNull Throwable t) {
                t.printStackTrace();
                Log.e(TAG, t.getLocalizedMessage());
            }
        });

    }


}
