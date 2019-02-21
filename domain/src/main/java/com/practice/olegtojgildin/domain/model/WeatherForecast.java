package com.practice.olegtojgildin.domain.model;


import java.util.List;

/**
 * Created by olegtojgildin on 31/01/2019.
 */

public class WeatherForecast {
    private List<WeatherDay> items;

    public WeatherForecast(List<WeatherDay> items) {
        this.items = items;
    }

    public List<WeatherDay> getItems() {
        return items;
    }
}