package com.practice.olegtojgildin.domain.repository;

import com.practice.olegtojgildin.domain.callback.OnDataListReceivedListener;
import com.practice.olegtojgildin.domain.callback.OnDataReceivedListener;
import com.practice.olegtojgildin.domain.model.WeatherDay;
import java.util.List;

public interface ForecastRepository {
     void getForecast(String city,OnDataListReceivedListener dataListCallback);
     void requestDailyForecast(final int epochDate, final OnDataReceivedListener dataCallback);

}