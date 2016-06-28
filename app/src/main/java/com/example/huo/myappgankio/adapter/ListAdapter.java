package com.example.huo.myappgankio.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.bean.ResultBean;

import java.util.Random;

/**
 * Created by huo on 28/06/16.
 */

public class ListAdapter extends BaseQuickAdapter<ResultBean> {
    public ListAdapter(Context context, int layoutResId) {
        super(context, layoutResId, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ResultBean resultBean) {
        baseViewHolder.setText(R.id.tv_text, resultBean.getDesc());
    }

    public void openLoadAnimation() {
        openLoadAnimation(new Random().nextInt(5) + 1);
    }

}
