package com.practice.olegtojgildin.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.practice.olegtojgildin.data.NetworkManager;
import com.practice.olegtojgildin.data.OnForecastsReceivedListener;
import com.practice.olegtojgildin.data.executor.DatabaseExecutor;
import com.practice.olegtojgildin.data.local.DBManager;
import com.practice.olegtojgildin.data.remote.ApiMapper;
import com.practice.olegtojgildin.data.remote.RetrofitHelper;


public final class ForecastsDataSourceFactory {
/*
    private final Context mContext;

    public ForecastsDataSourceFactory(@NonNull final Context context) {
        this.mContext = context;
    }

    public ForecastsDataSource create(OnForecastsReceivedListener callback) {
        ForecastsDataSource dataSource;
        Log.d("connnect", Boolean.toString(NetworkManager.isNetworkAvailable(mContext)));
        if (NetworkManager.isNetworkAvailable(mContext)) {
            dataSource = createRemoteDataSource(callback);
        } else {

            dataSource = createLocalDataSource(callback);
        }
        return dataSource;
    }

    public ForecastsDataSource createLocalDataSource(OnForecastsReceivedListener callback) {
        final DatabaseExecutor databaseExecutor = new DatabaseExecutor();
        DBManager dbManager= DBManager.getInstance(mContext,callback);

        return new ForecastsLocalDataSource(dbManager,databaseExecutor);
    }

    public ForecastsRemoteDataSource createRemoteDataSource(OnForecastsReceivedListener callback) {
        ApiMapper mApiMapper = new ApiMapper(new RetrofitHelper(), callback);
        return new ForecastsRemoteDataSource(mApiMapper,mContext);
    }*/
}
