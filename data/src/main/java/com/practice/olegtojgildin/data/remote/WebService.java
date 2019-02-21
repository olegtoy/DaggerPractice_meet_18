package com.practice.olegtojgildin.data.remote;


import com.practice.olegtojgildin.data.entity.WeatherDayModel;
import com.practice.olegtojgildin.data.entity.WeatherForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    @GET("weather?")
    Call<WeatherDayModel> getToday(@Query("q") String cityName,
                                   @Query("appid") String appid);

    @GET("forecast?")
    Call<WeatherForecast> getForecast(@Query("q") String cityName,
                                      @Query("mode") String mode,
                                      @Query("units") String units,
                                      @Query("lang") String lang,
                                      @Query("appid") String appid);
}
