package com.vashli.weatherapp;

import com.vashli.weatherapp.Model.Country;
import com.vashli.weatherapp.Model.Data;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/v1/forecast.json?key=5c48eb8669244b7b9ee63718191004&days=10")
    Call<Data> getWeather(@Query("q") String countryName);

    @GET("/rest/v2/all?fields=name")
    Call<List<Country>> getCountries();
}