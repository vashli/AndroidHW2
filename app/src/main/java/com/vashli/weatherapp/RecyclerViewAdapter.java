package com.vashli.weatherapp;

import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vashli.weatherapp.Model.Country;
import com.vashli.weatherapp.Model.Data;
import com.vashli.weatherapp.Model.Forecast;
import com.vashli.weatherapp.Model.Forecastday;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RecyclerViewAdapter extends RecyclerView.Adapter<WeatherViewHolder>  {
    private Forecast forecast = new Forecast();
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_main_cell, viewGroup, false);
        getData();
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
        Log.d("mari", "in recyclerView setData");
        this.forecast = forecast;
        notifyDataSetChanged();
    }

    public void getData(){
//        Api api = RetrofitProvider.getRetrofitInstance().create(Api.class);
//        api.getWeather(countryName).enqueue(new retrofit2.Callback<Data> () {
//            @Override
//            public void onResponse(Call<Data > call, Response<Data> response) {
//                setData(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Data> call, Throwable t) {
//                Log.e("response_failure", t.getMessage() );
//            }
//        });
    }
}
