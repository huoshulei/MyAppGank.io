package com.example.huo.myappgankio.adapter;

import android.content.Context;
import android.widget.ImageView.ScaleType;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.bean.SisterBean;

import java.util.Random;

/**
 * Created by huo on 19/06/16.
 */

public class SisterAdapter extends BaseQuickAdapter<SisterBean.ResultsBean> {
    ImageLoader mLoader;

    public SisterAdapter(Context context, int layoutResId, ImageLoader loader) {
        super(context, layoutResId, null);
        mLoader = loader;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SisterBean.ResultsBean sisterBean) {
        baseViewHolder.setText(R.id.tv_sister, "提供者：" + sisterBean.getWho());
        NetworkImageView imageView = baseViewHolder.getView(R.id.niv_sister);
        imageView.setDefaultImageResId(R.mipmap.ic_launcher);
        imageView.setErrorImageResId(R.mipmap.sorry);
        imageView.setImageUrl(sisterBean.getUrl(), mLoader, new NetworkImageView.ImageURLBuilder() {
            @Override
            public String buildUrl(String url, int width, int height, ScaleType scaleType,
                                   Context context) {
                StringBuilder key = new StringBuilder();
                key.append(url)
                        .append("#W").append(width)
                        .append("#H").append(height)
                        .append("#S").append(scaleType.toString());
                return key.toString();
            }
        });
    }

    @Override
    public void openLoadAnimation() {
        openLoadAnimation(new Random().nextInt(5) + 1);
    }
}
