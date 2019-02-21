package com.practice.olegtojgildin.data.repository.datasource;


import com.practice.olegtojgildin.data.entity.WeatherDayModel;
import com.practice.olegtojgildin.data.executor.DatabaseExecutor;
import com.practice.olegtojgildin.data.local.DBManager;

import java.util.List;

/**
 * Created by olegtojgildin on 17/02/2019.
 */

public final class ForecastsLocalDataSource implements ForecastsDataSource {

    private final DatabaseExecutor mExecutor;
    DBManager dbManager ;

    public ForecastsLocalDataSource(DBManager dbManager, final DatabaseExecutor executor) {
        this.mExecutor = executor;
        this.dbManager=dbManager;
    }


    @Override
    public void getForecast(String city) {
        mExecutor.execute(() -> dbManager.getAllWeatherDay());

    }

    public void saveAllForecasts(final List<WeatherDayModel> entities) {
       // mExecutor.execute(() -> dbManager.(entities));
    }


}