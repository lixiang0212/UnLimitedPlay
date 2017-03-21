package com.zhuoxin.www.unlimitedplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lixiang on 2016/10/17.
 */

public class DemoFragment extends Fragment{
    private TextView tv_title;
    private String title;
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title","null");
        return inflater.inflate(R.layout.demo_fragment,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(title);
    }
}
