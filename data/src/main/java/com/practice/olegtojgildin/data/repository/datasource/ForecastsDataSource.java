package com.practice.olegtojgildin.data.repository.datasource;

import com.practice.olegtojgildin.domain.model.WeatherDay;

import java.util.List;

public interface ForecastsDataSource {

    void getForecast(final String city);
}
