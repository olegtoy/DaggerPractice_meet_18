package com.practice.olegtojgildin.domain.interactor;

import com.practice.olegtojgildin.domain.callback.OnDataListReceivedListener;
import com.practice.olegtojgildin.domain.repository.ForecastRepository;

import javax.inject.Inject;

public final class GetForecastsCase {

    private final ForecastRepository mForecastRepository;

    @Inject
    public GetForecastsCase(final ForecastRepository repository) {
        this.mForecastRepository = repository;
    }

    public void execute(final String city,  OnDataListReceivedListener callback) {
        mForecastRepository.getForecast(city, callback);
    }
}
