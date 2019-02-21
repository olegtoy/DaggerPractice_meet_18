package com.practice.olegtojgildin.cleanpractice_meet_16.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.practice.olegtojgildin.cleanpractice_meet_16.DateUtils;
import com.practice.olegtojgildin.cleanpractice_meet_16.R;
import com.practice.olegtojgildin.data.entity.WeatherDayModel;


/**
 * Created by olegtojgildin on 01/02/2019.
 */

public class DetailsWeatherActivity extends AppCompatActivity {

    private WeatherDayModel weatherDay;
    private TextView date;
    private TextView tempMax;
    private TextView tempMin;
    private TextView humidity;
    private TextView pressure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_weather_activity);
        initView();
        initDay();
    }
    public void initView(){
        date=findViewById(R.id.dt_details);
        tempMax=findViewById(R.id.temp_max_details);
        tempMin=findViewById(R.id.temp_min_details);
        humidity=findViewById(R.id.humidity_detalis);
        pressure=findViewById(R.id.pressure_detalis);
    }
    public void initDay(){
        weatherDay=getIntent().getParcelableExtra(WeatherDayModel.class.getCanonicalName());
        date.setText(DateUtils.covertDate(weatherDay.getDt()));
        tempMax.setText(Float.toString(weatherDay.getMain().getTempMax())+"°C");
        tempMin.setText(Float.toString(weatherDay.getMain().getTempMin())+"°C");
        humidity.setText(Float.toString(weatherDay.getMain().getHumidity())+"%");
        pressure.setText(Float.toString(weatherDay.getMain().getPressure())+"hPa");
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, DetailsWeatherActivity.class);
        return intent;
    }

}

