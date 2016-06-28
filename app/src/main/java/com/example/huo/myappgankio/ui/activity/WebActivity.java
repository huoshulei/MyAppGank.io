package com.example.huo.myappgankio.ui.activity;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {
    private static final String TAG = "WebActivity";
    String url;
    @BindView(R.id.wv_show)
    WebView mWvShow;

    @Override
    public void initView() {
        url = getIntent().getData().toString();
        setContentView(R.layout.activity_web);
        Log.d(TAG, "initView: " + url);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        WebSettings settings = mWvShow.getSettings();
        if (url != null) mWvShow.loadUrl(url);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
    }

}
