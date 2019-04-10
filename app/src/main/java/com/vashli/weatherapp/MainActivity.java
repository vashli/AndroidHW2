package com.vashli.weatherapp;

import android.net.ConnectivityManager;
import android.os.Debug;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewParent;
import android.widget.Toast;

import com.vashli.weatherapp.Model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private Retrofit retrofit;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.activity_main_view_pager);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);
        if(isNetworkConnected()){
            getCountries();
        }else{
            Log.d("mari", "no internet");
            Toast.makeText(this, "no internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void getCountries(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);

        api.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()){
                    mainViewPagerAdapter.setData(response.body());
                }else{
                    Log.e("mari", "error occurred" );
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("mari", t.getMessage() );
            }
        });

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null  && cm.getActiveNetworkInfo().isConnected();
    }

}
