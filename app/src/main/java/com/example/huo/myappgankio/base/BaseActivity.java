package com.example.huo.myappgankio.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    public void startActivity(Class<?> activity) {

        startActivity(activity, null, null);
    }

    public void startActivity(Class<?> activity, Bundle bundle) {
        startActivity(activity, bundle, null);
    }

    public void startActivity(Class<?> activity, Uri uri) {
        startActivity(activity, null, uri);
    }

    public void startActivity(Class<?> activity, Bundle bundle, Uri uri) {
        Intent intent = new Intent(this, activity);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
    }
    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
        super.startActivityFromFragment(fragment, intent, requestCode);
    }
}
