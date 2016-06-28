package com.example.huo.myappgankio.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.bean.ResultBean;
/**
 * Created by huo on 28/06/16.
 */

public class VideoAdapter extends BaseQuickAdapter<ResultBean> {
    public VideoAdapter(Context context, int layoutResId) {
        super(context, layoutResId, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ResultBean resultBean) {
        VideoView view = baseViewHolder.getView(R.id.vv_show);
        view.setVideoURI(Uri.parse(resultBean.getUrl()));
        view.setMediaController(new MediaController(mContext));
    }
}
