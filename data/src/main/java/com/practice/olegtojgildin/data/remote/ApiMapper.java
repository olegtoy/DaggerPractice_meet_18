package com.practice.olegtojgildin.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.practice.olegtojgildin.data.OnForecastsReceivedListener;
import com.practice.olegtojgildin.data.entity.WeatherDayModel;
import com.practice.olegtojgildin.data.entity.WeatherForecast;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

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
    private  OnForecastsReceivedListener mCallback;
    private WebService mWebService;

    @Inject
    public ApiMapper(WebService webService) {
       // this.helper = helper;
        mListWeather = new ArrayList<>();
        //this.mCallback = callback;
        mWebService=webService;

    }
    public void getForecastsRemote(@Nullable final String cityQuery, final OnForecastsReceivedListener callback) {
        this.mCallback = callback;
        this.getForecastWeatherSync(cityQuery);
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
