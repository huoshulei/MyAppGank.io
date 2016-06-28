package com.example.huo.myappgankio.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by huo on 19/06/16.
 */

public class BaseActivity extends AppCompatActivity {
    public RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    public void showToast(String data) {
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }

    public float getWidth() {
        DisplayMetrics metrics = getDisplayMetrics();
        return metrics.widthPixels;
    }

    public float getHeight() {
        DisplayMetrics metrics = getDisplayMetrics();
        return metrics.heightPixels;
    }

    @NonNull
    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
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
