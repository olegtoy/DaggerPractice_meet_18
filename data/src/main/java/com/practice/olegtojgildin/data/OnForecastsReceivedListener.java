package com.practice.olegtojgildin.data;


import com.practice.olegtojgildin.data.entity.WeatherDayModel;

import java.util.List;

public interface OnForecastsReceivedListener {

    void onForecastsReceived(final List<WeatherDayModel> entities);

}
