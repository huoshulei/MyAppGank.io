package com.example.huo.myappgankio.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView.ScaleType;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.bean.SisterBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

/**
 * Created by huo on 19/06/16.
 */

public class SisterAdapter extends BaseQuickAdapter<SisterBean.ResultsBean> {
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";
    ImageLoader mLoader;
    int lastAnimatedPosition = 0;


    public SisterAdapter(Context context, int layoutResId, ImageLoader loader) {
        super(context, layoutResId, null);
        mLoader = loader;
    }
//    private void runEnterAnimation(View view, int position) {
////        if (!animateItems || position >= ANIMATED_ITEMS_COUNT - 1) {
////            return;
////        }
//
//        if (position > lastAnimatedPosition) {
//            lastAnimatedPosition = position;
//            view.setTranslationY(Utils.getScreenHeight(mContext));
//            view.animate()
//                    .translationY(0)
//                    .setInterpolator(new DecelerateInterpolator(3.f))
//                    .setDuration(700)
//                    .start();
//        }
//    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int positions) {
//        runEnterAnimation(holder.itemView,positions);
//        super.onBindViewHolder(holder, positions);
//    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, SisterBean.ResultsBean sisterBean) {
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
        baseViewHolder.setOnClickListener(R.id.niv_sister, new OnItemChildClickListener());
//        View view=baseViewHolder.itemView;
//        setUpClickableViews(view,baseViewHolder);
    }

//    private void setUpClickableViews(View view, final BaseViewHolder baseViewHolder) {
//        baseViewHolder.getView(R.id.niv_sister).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int adapterPosition = baseViewHolder.getAdapterPosition();
////                feedItems.get(adapterPosition).likesCount++;
//                notifyItemChanged(adapterPosition, ACTION_LIKE_IMAGE_CLICKED);
////                if (mContext instanceof MainActivity) {
////                    ((MainActivity) mContext).showLikedSnackbar();
////                }
//            }
//        });
//    }



    @Override
    public void openLoadAnimation() {
        openLoadAnimation(new Random().nextInt(5) + 1);
    }
}
