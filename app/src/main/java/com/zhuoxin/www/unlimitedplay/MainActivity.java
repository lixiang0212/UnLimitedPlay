package com.zhuoxin.www.unlimitedplay;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment> views;
    private MyAdapter adapter;
    private int CurrentPage=5;
    private Button btn;
    private TextView tv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case  0x01:
                    viewPager.setCurrentItem(msg.arg1);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv_number);
        tv.setText(0+"");
        views = new ArrayList<>();
        for(int i=0;i<5;i++){
           DemoFragment fragment = new DemoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title","第"+i+"页");
            fragment.setArguments(bundle);
            views.add(fragment);
        }
        Log.i("AAA",views.size()+"");
        adapter = new MyAdapter(getSupportFragmentManager(),views);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(5,false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            try {
                                sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Message message = new Message();
                            message.what = 0x01;
                            message.arg1 = CurrentPage+1;
                            handler.sendMessage(message);
                        }
                    }
                }).start();
            }
        });
       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               tv.setText(position%views.size()+"");
               CurrentPage = position;
               Log.i("AAA",CurrentPage+" CurrentPage");
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

    }
}
