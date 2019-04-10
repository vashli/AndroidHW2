package com.vashli.weatherapp;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    private static Retrofit retrofit;
    private static  final String BASE_URL = "https://restcountries.eu";

    private static Retrofit retrofitForData;
    private static  final String BASE_URL_DATA = "http://api.apixu.com";

    public static Retrofit getRetrofitInstanceForCountries(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url();
                request.newBuilder().addHeader("Content-Type", "application/json");
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }



    public static Retrofit getRetrofitInstanceForData(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url();
                request.newBuilder().addHeader("Content-Type", "application/json");
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        if(retrofitForData == null) {
            retrofitForData = new Retrofit.Builder()
                    .baseUrl(BASE_URL_DATA)
                    .baseUrl(BASE_URL_DATA)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofitForData;
    }
}
