package com.practice.olegtojgildin.data.repository;


import com.practice.olegtojgildin.data.NetworkManager;
import com.practice.olegtojgildin.data.OnForecastsReceivedListener;
import com.practice.olegtojgildin.data.entity.WeatherDayMapper;
import com.practice.olegtojgildin.data.entity.WeatherDayModel;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsDataSource;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsDataSourceFactory;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsLocalDataSource;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsRemoteDataSource;
import com.practice.olegtojgildin.domain.callback.OnDataListReceivedListener;
import com.practice.olegtojgildin.domain.callback.OnDataReceivedListener;
import com.practice.olegtojgildin.domain.model.WeatherDay;
import com.practice.olegtojgildin.domain.repository.ForecastRepository;

import java.util.List;

import javax.inject.Inject;


public class ForecastRepositoryImpl implements ForecastRepository {
    private final NetworkManager mNetworkManager;
    private final ForecastsLocalDataSource mLocalDataSource;
    private final ForecastsRemoteDataSource mRemoteDataSource;

    private OnDataReceivedListener mDataCallback;
    private OnDataListReceivedListener mDataListCallback;

    @Inject
    public ForecastRepositoryImpl(final NetworkManager networkManager,
                                  final ForecastsLocalDataSource localDataSource,
                                  final ForecastsRemoteDataSource remoteDataSource) {
        this.mNetworkManager = networkManager;
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getForecast(String city, OnDataListReceivedListener dataListReceivedListener) {
        this.mDataListCallback = dataListReceivedListener;
        ForecastsDataSource dataSource;
        if (mNetworkManager.isNetworkAvailable()) {
            dataSource = mRemoteDataSource;
        } else {
            dataSource = mLocalDataSource;
        }
        dataSource.getForecast(city, this::onForecastsReceived);
    }


    @Override
    public void requestDailyForecast(int epochDate, OnDataReceivedListener dataCallback) {

    }


    private void onForecastsReceived(final List<WeatherDayModel> entities) {
        if (entities != null) {
            mLocalDataSource.saveAllForecasts(entities);
            mDataListCallback.onDataListReceived(WeatherDayMapper.transform(entities));
        }
    }
}
