package com.practice.olegtojgildin.cleanpractice_meet_16.presenter;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;


import com.practice.olegtojgildin.cleanpractice_meet_16.MainView;
import com.practice.olegtojgildin.domain.interactor.GetForecastsCase;
import com.practice.olegtojgildin.domain.model.WeatherDay;

import java.util.ArrayList;
import java.util.List;

public final class MainPresenter<T extends MainView> {



    private T mView;
    private final GetForecastsCase mGetForecastsUseCase;


    public MainPresenter(final GetForecastsCase forecastsUseCase) {
        this.mGetForecastsUseCase = forecastsUseCase;
    }

    public void attachView(T view) {
        this.mView = view;
    }

    public void getForecasts(final String city) {
        mView.showProgressBar();
        mGetForecastsUseCase.execute(city, this::onForecastsReceived);
    }


    private void onForecastsReceived(final List<WeatherDay> forecasts) {
        if (!forecasts.isEmpty()) {
            mView.setForecasts(forecasts);
        } else {
            mView.showError();
        }
        mView.hideProgressBar();
        mView.hideRefresher();
    }


    public void detachView() {
        mView = null;
    }
}
