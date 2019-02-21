package com.practice.olegtojgildin.domain.model;

import java.util.ArrayList;

public class WeatherDay {

    public WeatherDay(long dt, float temp, float temp_max, float temp_min, float humidity, float pressure) {
        this.dt = dt;
        Main main = new Main();
        main.temp = temp;
        main.temp_max = temp_max;
        main.temp_min = temp_min;
        main.humidity = humidity;
        main.pressure = pressure;
        this.main = main;
    }

    private Main main;

    public Main getMain() {
        return main;
    }

    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    private long dt;

    public long getDt() {
        return dt;
    }



    public static class Main  {
        private float temp;
        public float temp_min;
        public float temp_max;
        private float humidity;
        private float pressure;


        public Main() {

        }




        public Float getTemp() {
            return temp;
        }

        public Float getTempMax() {
            return temp_max;
        }

        public Float getTempMin() {
            return temp_min;
        }

        public Float getHumidity() {
            return humidity;
        }

        public Float getPressure() {
            return pressure;
        }

    }

    class Sys {
        private long sunrise;
        private long sunset;

        public long getSunrise() {
            return sunrise;
        }

        public long getSunset() {
            return sunset;
        }
    }


    class Wind {
        private float speed;

        public Float getSpeed() {
            return speed;
        }

    }

    public class Weather {
        private int id;
        public String main;
        private String description;

        public String getDescription() {
            return description;
        }
    }

    private ArrayList<Weather> weather = new ArrayList();

    public ArrayList<Weather> getWeather() {
        return weather;
    }

}