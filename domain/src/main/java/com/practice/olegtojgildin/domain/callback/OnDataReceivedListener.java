package com.practice.olegtojgildin.domain.callback;


import com.practice.olegtojgildin.domain.model.WeatherDay;

public interface OnDataReceivedListener {

    void onDataReceived(final WeatherDay data);

}
