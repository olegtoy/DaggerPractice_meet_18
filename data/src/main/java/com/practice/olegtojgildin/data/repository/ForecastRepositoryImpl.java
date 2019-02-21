package com.practice.olegtojgildin.data.repository;


import com.practice.olegtojgildin.data.OnForecastsReceivedListener;
import com.practice.olegtojgildin.data.entity.WeatherDayMapper;
import com.practice.olegtojgildin.data.entity.WeatherDayModel;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsDataSourceFactory;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsLocalDataSource;
import com.practice.olegtojgildin.domain.callback.OnDataListReceivedListener;
import com.practice.olegtojgildin.domain.callback.OnDataReceivedListener;
import com.practice.olegtojgildin.domain.repository.ForecastRepository;

import java.util.List;


public class ForecastRepositoryImpl implements ForecastRepository,OnForecastsReceivedListener {
    private final ForecastsDataSourceFactory mDataSourceFactory;
    private static volatile ForecastRepositoryImpl INSTANCE;
    private OnDataListReceivedListener mDataListCallback;

    @Override
    public void getForecast(String city, OnDataListReceivedListener dataListReceivedListener) {
        this.mDataListCallback = dataListReceivedListener;
        mDataSourceFactory.create(this).getForecast(city);
    }

    @Override
    public void requestDailyForecast(int epochDate, OnDataReceivedListener dataCallback) {

    }


    public static ForecastRepositoryImpl getInstance(final ForecastsDataSourceFactory factory) {
        ForecastRepositoryImpl instance = INSTANCE;
        if (instance == null) {
            synchronized (ForecastRepositoryImpl.class) {
                instance = INSTANCE;
                if (instance == null) {
                    instance = INSTANCE = new ForecastRepositoryImpl(factory);
                }
            }
        }
        return instance;
    }

    private ForecastRepositoryImpl(final ForecastsDataSourceFactory factory) {
        this.mDataSourceFactory = factory;
    }

    @Override
    public void onForecastsReceived(List<WeatherDayModel> entities) {
        if (entities != null) {
            ((ForecastsLocalDataSource) mDataSourceFactory.createLocalDataSource(this)).saveAllForecasts(entities);
            mDataListCallback.onDataListReceived(WeatherDayMapper.transform(entities));
        }
    }
}
