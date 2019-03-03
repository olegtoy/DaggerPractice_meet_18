package com.practice.olegtojgildin.cleanpractice_meet_16.di.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.practice.olegtojgildin.cleanpractice_meet_16.presenter.MainPresenter;
import com.practice.olegtojgildin.data.NetworkManager;
import com.practice.olegtojgildin.data.executor.DatabaseExecutor;
import com.practice.olegtojgildin.data.local.DBManager;
import com.practice.olegtojgildin.data.local.DbHelper;
import com.practice.olegtojgildin.data.remote.ApiMapper;
import com.practice.olegtojgildin.data.remote.RetrofitHelper;
import com.practice.olegtojgildin.data.remote.WebService;
import com.practice.olegtojgildin.data.repository.ForecastRepositoryImpl;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsLocalDataSource;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsRemoteDataSource;
import com.practice.olegtojgildin.domain.interactor.GetForecastsCase;
import com.practice.olegtojgildin.domain.repository.ForecastRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by olegtojgildin on 03/03/2019.
 */

@Module
public final class AppModule {

    private final Application mApplication;

    public AppModule(final Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context providesAppContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources providesResources(final Context context) {
        return context.getResources();
    }


    @Provides
    @Singleton
    ConnectivityManager providesConnectivityManager(final Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }



    @Provides
    @Singleton
    ForecastsLocalDataSource providesLocalDataSource(DBManager dbManager, final DatabaseExecutor executor) {
        return new ForecastsLocalDataSource(dbManager, executor);
    }

    @Provides
    @Singleton
    DatabaseExecutor providesDatabaseExecutor() {
        return new DatabaseExecutor();
    }

    @Provides
    @Singleton
    DbHelper providesDbHelper(Context context) {
        return new DbHelper(context);
    }

    @Provides
    @Singleton
    DBManager providesDBManager(Context context) {
        return new DBManager(context);
    }

    @Provides
    @Singleton
    ApiMapper providesApiMapper(WebService webService) {
        return new ApiMapper(webService);
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(final Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(RetrofitHelper.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    WebService providesApiService(final Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }


    @Provides
    @Singleton
    ForecastsRemoteDataSource providesRemoteDataSource(final ApiMapper apiMapper) {
        return new ForecastsRemoteDataSource(apiMapper);
    }

    @Provides
    @Singleton
    ForecastRepository providesForecastRepository(final NetworkManager networkManager,
                                                  final ForecastsLocalDataSource localDataSource,
                                                  final ForecastsRemoteDataSource remoteDataSource) {
        return new ForecastRepositoryImpl(networkManager, localDataSource, remoteDataSource);
    }

    @Provides
    @Singleton
    NetworkManager providesNetworkManager(final ConnectivityManager connectivityManager) {
        return new NetworkManager(connectivityManager);
    }


    @Provides
    MainPresenter provideMainPresenter(final GetForecastsCase forecastsUseCase) {
        return new MainPresenter(forecastsUseCase);
    }

    @Provides
    GetForecastsCase providesForecastsCase(final ForecastRepository repository) {
        return new GetForecastsCase(repository);
    }




}
