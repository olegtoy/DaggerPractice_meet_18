package com.practice.olegtojgildin.domain.callback;

import com.practice.olegtojgildin.domain.model.WeatherDay;
import java.util.List;

public interface OnDataListReceivedListener {
    void onDataListReceived( List<WeatherDay> dataList);
}
