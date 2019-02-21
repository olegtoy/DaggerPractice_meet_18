package com.practice.olegtojgildin.cleanpractice_meet_16;

import com.practice.olegtojgildin.domain.model.WeatherDay;

import java.util.List;

public interface MainView {

    void setForecasts(final List<WeatherDay> forecasts);

    void showProgressBar();

    void hideProgressBar();

    void hideRefresher();

    void showError();


}
