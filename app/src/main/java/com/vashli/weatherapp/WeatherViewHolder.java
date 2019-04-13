package com.vashli.weatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class WeatherViewHolder extends RecyclerView.ViewHolder{
    private TextView date;
    private TextView temperature;
    private ImageView icon;

    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.cell_date);
        temperature = itemView.findViewById(R.id.cell_temperature);
        icon = itemView.findViewById(R.id.cell_icon);
    }

    public void setOneDayData(String dateStr, Double temp, String iconUrl){
        date.setText(dateStr);
        temperature.setText(String.format("%.0f ", temp ) + "°C");
        Picasso.get().load("https:" + iconUrl).into(icon);
    }
}
