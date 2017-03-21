package com.zhuoxin.www.unlimitedplay.other;

import android.support.annotation.IntRange;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lixiang on 2016/10/18.
 */

public abstract class MyViewPagerAdapter<V extends MyViewPager> extends PagerAdapter {

    private static final int COEFFICIENT = 10;
    private V mViewPager;

    public MyViewPagerAdapter(V mViewPager) {
        this.mViewPager = mViewPager;
    }
    @IntRange(from = 0)
    public abstract int getRealCount();

    @Override
    public final int getCount() {
        long realCount = getRealCount();
        if(realCount>1){
            realCount = getRealCount()*COEFFICIENT;
            realCount = realCount>Integer.MAX_VALUE? Integer.MAX_VALUE:realCount;
        }
        return (int)realCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public final void finishUpdate(ViewGroup container) {
        if(getCount()<=1){
            return;
        }
        int position = mViewPager.getCurrentItem();
        if(position ==0){
            position = getRealCount();
            mViewPager.setCurrentItem(position, false);
        }else if(position ==getCount()-1){
            position =getRealCount()-1;
            mViewPager.setCurrentItem(position, false);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position%getRealCount();
        return this.instantiateRealItem(container,position);
    }

    public abstract Object instantiateRealItem(ViewGroup container, int position);


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
