package com.zhuoxin.www.unlimitedplay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by lixiang on 2016/10/17.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> views;

    public MyAdapter(FragmentManager fm,List<Fragment> views) {
        super(fm);
        this.views=views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position%views.size());
    }

    @Override
    public int getCount() {
        return 10000;
    }
}
