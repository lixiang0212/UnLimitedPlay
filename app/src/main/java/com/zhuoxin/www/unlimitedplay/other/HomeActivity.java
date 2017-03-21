package com.zhuoxin.www.unlimitedplay.other;

import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoxin.www.unlimitedplay.R;

public class HomeActivity extends AppCompatActivity {
    private MyViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (MyViewPager) findViewById(R.id.my_viewPager);
        PagerAdapter adapter =new SimpleAdapter(viewPager, new int[]{R.layout.pager1,
                R.layout.pager2, R.layout.pager3,R.layout.pager4});
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setmLifeCycle(viewPager.RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.setmLifeCycle(viewPager.PAUSE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.setmLifeCycle(viewPager.DESTROY);
    }

    private static class SimpleAdapter extends MyViewPagerAdapter<MyViewPager>{
        private int[] viewResIds;

        public SimpleAdapter(MyViewPager mViewPager, int[] viewResIds) {
            super(mViewPager);
            this.viewResIds = viewResIds;
        }

        @Override
        public int getRealCount() {
            return viewResIds!=null?viewResIds.length:0;
        }

        @Override
        public Object instantiateRealItem(ViewGroup container, int position) {
            int resId = viewResIds[position];
            View bannerView = LayoutInflater.from(container.getContext()).inflate(resId, null);
            container.addView(bannerView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            return bannerView;
        }
    }

}
