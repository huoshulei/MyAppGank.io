package com.example.huo.myappgankio.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by huo on 19/06/16.
 */

public class BaseActivity extends AppCompatActivity {
    public RequestQueue mQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mQueue == null)
            mQueue = Volley.newRequestQueue(getApplicationContext());
        initView();
        initData();
        initEvent();
    }

    public RequestQueue getQueue() {
        if (mQueue == null)
            mQueue = Volley.newRequestQueue(getApplicationContext());
        return mQueue;
    }

    public void initView() {

    }

    public void initData() {

    }

    public void initEvent() {

    }
}
