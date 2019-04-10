package com.vashli.weatherapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import com.vashli.weatherapp.Model.Country;

import java.util.ArrayList;
import java.util.List;


public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Country> countries = new ArrayList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        MainFragment fragment =  MainFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("name", countries.get(i).getName());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return  countries.size();

    }

    public void setData(List<Country> countries){
        this.countries.clear();
        this.countries.addAll(countries);
        notifyDataSetChanged();
    }


}
