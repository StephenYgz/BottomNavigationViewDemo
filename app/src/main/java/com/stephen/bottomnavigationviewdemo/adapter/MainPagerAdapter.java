package com.stephen.bottomnavigationviewdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by stephen on 2017/7/18.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public MainPagerAdapter(FragmentManager manager, List<Fragment> fragments) {
        super(manager);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
