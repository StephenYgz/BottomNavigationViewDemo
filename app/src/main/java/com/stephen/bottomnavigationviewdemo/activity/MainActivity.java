package com.stephen.bottomnavigationviewdemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.stephen.bottomnavigationviewdemo.R;
import com.stephen.bottomnavigationviewdemo.adapter.MainPagerAdapter;
import com.stephen.bottomnavigationviewdemo.fragment.BaseFragment;
import com.stephen.bottomnavigationviewdemo.widget.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephen on 2017/7/18.
 */

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MenuItem mMenuItem;
    private BottomNavigationView mBottomNavigationView;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);

        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mViewPager.setOffscreenPageLimit(3);

        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_news:
                                mViewPager.setCurrentItem(0);
                                break;
                            case R.id.item_lib:
                                mViewPager.setCurrentItem(1);
                                break;
                            case R.id.item_discovery:
                                mViewPager.setCurrentItem(2);
                                break;
                            case R.id.item_more:
                                mViewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                } else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                mMenuItem = mBottomNavigationView.getMenu().getItem(position);
                mMenuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        mViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        mFragments.add(BaseFragment.newInstance("新闻"));
        mFragments.add(BaseFragment.newInstance("图书"));
        mFragments.add(BaseFragment.newInstance("发现"));
        mFragments.add(BaseFragment.newInstance("更多"));
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);
    }
}