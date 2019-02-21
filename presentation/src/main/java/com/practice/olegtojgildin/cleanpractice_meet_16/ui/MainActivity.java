package com.practice.olegtojgildin.cleanpractice_meet_16.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.practice.olegtojgildin.cleanpractice_meet_16.LinearSpacingItemDecoration;
import com.practice.olegtojgildin.cleanpractice_meet_16.MainView;
import com.practice.olegtojgildin.cleanpractice_meet_16.R;
import com.practice.olegtojgildin.cleanpractice_meet_16.WeatherAdapter;
import com.practice.olegtojgildin.cleanpractice_meet_16.presenter.MainPresenter;
import com.practice.olegtojgildin.data.entity.WeatherDayMapper;
import com.practice.olegtojgildin.data.entity.WeatherDayModel;
import com.practice.olegtojgildin.data.local.DBManager;
import com.practice.olegtojgildin.data.repository.ForecastRepositoryImpl;
import com.practice.olegtojgildin.data.repository.datasource.ForecastsDataSourceFactory;
import com.practice.olegtojgildin.domain.interactor.GetForecastsCase;
import com.practice.olegtojgildin.domain.model.WeatherDay;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainView, WeatherAdapter.OnWeatherListener {
    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<WeatherDay> mListForecast;
    private MainPresenter<MainActivity> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mAdapter = new WeatherAdapter( MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        final ForecastsDataSourceFactory factory = new ForecastsDataSourceFactory(this);
        final ForecastRepositoryImpl repository = ForecastRepositoryImpl.getInstance(factory);
        mPresenter = new MainPresenter<>(new GetForecastsCase(repository));
        mPresenter.attachView(this);
        mPresenter.getForecasts("Moscow");



    }

    public void initViews() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.addItemDecoration(LinearSpacingItemDecoration.newBuilder()
                .spacing(getResources().getDimensionPixelSize(R.dimen.margin_half))
                .orientation(LinearLayoutManager.VERTICAL)
                .includeEdge(true)
                .build());
    }



    @Override
    public void onWeatherClick(int position) {
        Intent intent = DetailsWeatherActivity.newIntent(this);
        intent.putExtra(WeatherDayModel.class.getCanonicalName(), WeatherDayMapper.transform(mListForecast.get(position)));
        startActivity(intent);
    }

    @Override
    public void setForecasts(List<WeatherDay> forecasts) {
        mAdapter.setForecastList(forecasts);
        Log.d("size",Integer.toString(forecasts.size()));
        mListForecast=forecasts;

        DBManager dbManager = new DBManager(MainActivity.this);
        dbManager.removeForecasts();
        for (int i = 0; i < forecasts.size(); i++)
            dbManager.addWeather(WeatherDayMapper.transform(forecasts.get(i)));

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void hideRefresher() {

    }

    @Override
    public void showError() {

    }
}