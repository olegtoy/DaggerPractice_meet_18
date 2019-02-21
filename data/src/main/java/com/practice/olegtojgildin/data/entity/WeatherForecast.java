package com.practice.olegtojgildin.data.entity;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WeatherForecast {
    @SerializedName("list")
    private List<WeatherDayModel> items;

    public WeatherForecast(List<WeatherDayModel> items) {
        this.items = items;
    }

    public List<WeatherDayModel> getItems() {
        return items;
    }
}