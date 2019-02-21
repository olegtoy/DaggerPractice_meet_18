package com.practice.olegtojgildin.cleanpractice_meet_16;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practice.olegtojgildin.domain.model.WeatherDay;

import java.util.ArrayList;
import java.util.List;


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolderWeather> {
    private List<WeatherDay> mWeatherForecast;
    private OnWeatherListener mOnWeatherListener;

    public WeatherAdapter( OnWeatherListener onWeatherListener) {
        this.mOnWeatherListener = onWeatherListener;
        mWeatherForecast=new ArrayList<WeatherDay>();
        mWeatherForecast.add(new WeatherDay(1,2,3,4,5,6));
    }
    public void setForecastList(final List<WeatherDay> forecasts) {
        this.mWeatherForecast = forecasts;
        Log.d("size",Integer.toString(mWeatherForecast.size()));
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderWeather onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ViewHolderWeather(view, mOnWeatherListener);
    }

    @Override
    public void onBindViewHolder(ViewHolderWeather holder, int position) {
        holder.temp.setText(Float.toString(mWeatherForecast.get(position).getMain().getTemp())+"°C");
        if (mWeatherForecast.get(position).getWeather().size() != 0)
            holder.main.setText(mWeatherForecast.get(position).getWeather().get(0).getDescription());
        else
            holder.main.setText("Описание");

        holder.date.setText(DateUtils.covertDate(mWeatherForecast.get(position).getDt() ));

    }

    @Override
    public int getItemCount() {
        return mWeatherForecast.size();
    }

    public static class ViewHolderWeather extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView temp;
        public TextView main;
        public TextView date;

        OnWeatherListener onWeatherListener;

        public ViewHolderWeather(View v, OnWeatherListener onWeatherListener) {
            super(v);
            temp = v.findViewById(R.id.temp);
            main = v.findViewById(R.id.main);
            date = v.findViewById(R.id.date);
            v.setOnClickListener(this);
            this.onWeatherListener = onWeatherListener;
        }

        @Override
        public void onClick(View view) {
            onWeatherListener.onWeatherClick(getAdapterPosition());
        }
    }

    public interface OnWeatherListener {
        void onWeatherClick(int position);
    }
}
