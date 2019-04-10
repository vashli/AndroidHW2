package com.vashli.weatherapp;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.vashli.weatherapp.Model.Data;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    private Retrofit retrofit;
    private Api api;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private TextView location;
    private TextView localTime;
    private TextView temp;
    private TextView tempPerc;
    private TextView perciperation;
    private TextView humidity;
    private TextView wind;
    private TextView dayAndNight;
    private String countryName;
    private View viewMain;
    private ImageView iconMain;
    private ImageView iconDrop;
    private ImageView iconHumidity;
    private ImageView iconFlag;
    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViewComponents(view);
        countryName = this.getArguments().getString("name", "noName");
        getData();
        return view;
    }

    private void initViewComponents(View view){
        recyclerView = view.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        viewMain = view.findViewById(R.id.view);
        iconMain = view.findViewById(R.id.activity_main_icon_sun);
        iconDrop = view.findViewById(R.id.activity_main_precipitation_icon);
        iconHumidity = view.findViewById(R.id.activity_main_humidity_icon);
        iconFlag = view.findViewById(R.id.activity_main_wind_icon);

        location = view.findViewById(R.id.fragment_main_app_city_name);
        localTime = view.findViewById(R.id.fragment_main_app_date);
        temp = view.findViewById(R.id.activity_main_temperature);
        tempPerc = view.findViewById(R.id.fragment_main_pereived_value);
        perciperation = view.findViewById(R.id.activity_main_precipitation_value);
        humidity = view.findViewById(R.id.activity_main_humidity_value);
        wind = view.findViewById(R.id.activity_main_wind_value);
        dayAndNight = view.findViewById(R.id.activity_main_day_value);
        setDayMode();
    }

    private void setValues(Data data) {
        location.setText(data.getLocation().getName());
        localTime.setText(data.getLocation().getLocaltime());
        Double tmp = data.getCurrent().getTempC();
        temp.setText(String.format("%.0f", tmp ));

        Double feels =  data.getCurrent().getFeelslikeC();
        if (feels != null) tempPerc.setText(String.format("%.0f", feels) + "°C");
        else tempPerc.setText(String.format("%.0f ", tmp ) + "°C");

        perciperation.setText(String.format("%.0f", data.getCurrent().getPrecipMm()) + "%");
        humidity.setText(String.format("%d", data.getCurrent().getHumidity()) + "%");
        wind.setText(String.format("%.0f", data.getCurrent().getWindKph()) + "kmh");
        dayAndNight.setText(String.format("%s %s ", data.getForecast().getForecastday().get(0).getAstro().getSunrise(),
                data.getForecast().getForecastday().get(0).getAstro().getSunset() ));

        adapter.setData(data.getForecast());

    }

    public void getData(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        api.getWeather(countryName).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful()){
                    setValues(response.body());
                }else{
                    Log.d("mari","error occurred");
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("mari","onFailure");
            }
        });

    }


    private void setDayMode(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(hour >= 18 || hour < 6) {
            viewMain.setBackgroundResource(R.drawable.dark_background);
            iconMain.setImageResource(R.drawable.ic_moon);
            iconDrop.setImageResource(R.drawable.ic_drop_night);
            iconHumidity.setImageResource(R.drawable.ic_humidity_night);
            iconFlag.setImageResource(R.drawable.ic_flag_night);
        }else{
            viewMain.setBackgroundResource(R.drawable.light_background);
            iconMain.setImageResource(R.drawable.ic_sun);
            iconDrop.setImageResource(R.drawable.ic_drop);
            iconHumidity.setImageResource(R.drawable.ic_humidity);
            iconFlag.setImageResource(R.drawable.ic_flag);
        }
    }
}
