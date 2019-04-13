package com.vashli.weatherapp;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vashli.weatherapp.Model.Forecast;
import com.vashli.weatherapp.Model.Forecastday;

public class RecyclerViewAdapter extends RecyclerView.Adapter<WeatherViewHolder>  {
    private Forecast forecast = new Forecast();
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_main_cell, viewGroup, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        Forecastday day = forecast.getForecastday().get(i);
        weatherViewHolder.setOneDayData(day.getDate(), day.getDay().getAvgtempC(), day.getDay().getCondition().getIcon());
    }

    @Override
    public int getItemCount() {
        if(forecast.getForecastday() == null) return  0;
        return forecast.getForecastday().size();
    }

    public void setData(Forecast forecast){
        this.forecast = forecast;
        notifyDataSetChanged();
    }

}
