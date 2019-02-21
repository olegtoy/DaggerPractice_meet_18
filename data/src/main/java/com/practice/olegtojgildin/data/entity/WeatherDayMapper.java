package com.practice.olegtojgildin.data.entity;

import com.practice.olegtojgildin.domain.model.WeatherDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olegtojgildin on 21/02/2019.
 */

public final class WeatherDayMapper {

    public static WeatherDay transform(final WeatherDayModel entity) {
        return new WeatherDay(
             entity.getDt(),
                entity.getMain().getTemp(),
                entity.getMain().getTempMax(),
                entity.getMain().getTempMin(),
                entity.getMain().getHumidity(),
                entity.getMain().getPressure());
    }
    public static WeatherDayModel transform(final WeatherDay entity) {
        return new WeatherDayModel(
                entity.getDt(),
                entity.getMain().getTemp(),
                entity.getMain().getTempMax(),
                entity.getMain().getTempMin(),
                entity.getMain().getHumidity(),
                entity.getMain().getPressure());
    }
    public static List<WeatherDay> transform(final List<WeatherDayModel> entities) {
        final List<WeatherDay> data = new ArrayList<>();
        for (final WeatherDayModel entity : entities) {
            data.add(transform(entity));
        }
        return data;
    }

    private WeatherDayMapper() {
    }

}