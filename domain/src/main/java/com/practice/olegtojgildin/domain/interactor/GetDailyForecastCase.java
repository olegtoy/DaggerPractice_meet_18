package com.practice.olegtojgildin.domain.interactor;


import com.practice.olegtojgildin.domain.callback.OnDataReceivedListener;
import com.practice.olegtojgildin.domain.repository.ForecastRepository;

public final class GetDailyForecastCase {

    private final ForecastRepository mForecastRepository;

    public GetDailyForecastCase(final ForecastRepository repository) {
        this.mForecastRepository = repository;
    }

    public void execute(final int epochDate, final OnDataReceivedListener callback) {
        mForecastRepository.requestDailyForecast(epochDate, callback);
    }
}
