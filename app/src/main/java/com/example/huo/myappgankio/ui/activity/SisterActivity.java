package com.example.huo.myappgankio.ui.activity;

import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SisterActivity extends BaseActivity {
    private static final String TAG = "SisterActivity";
    Uri mUri;
    @BindView(R.id.iv_sister)
    ImageView mIvSister;

    @Override
    public void initView() {
        setContentView(R.layout.activity_sister);
        ButterKnife.bind(this);
        mUri = getIntent().getData();
        super.initView();
    }

    @Override
    public void initData() {
        ImageRequest imageRequest = new ImageRequest(mUri.toString(), new Response
                .Listener<Bitmap>() {


            @Override
            public void onResponse(Bitmap response) {
                mIvSister.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mIvSister.setImageResource(R.mipmap.sorry);
            }
        });
        mQueue.add(imageRequest);
        super.initData();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Log.d(TAG, "initData: "+metrics.heightPixels+">>>>"+metrics.widthPixels);
    }

}
