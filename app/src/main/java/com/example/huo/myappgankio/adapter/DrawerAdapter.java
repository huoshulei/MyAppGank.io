package com.example.huo.myappgankio.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.ui.menu.MenuIetm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huo on 28/06/16.
 */
public class DrawerAdapter extends BaseAdapter {
    List<MenuIetm> mIetms = new ArrayList<>();
    Context mContext;

    public DrawerAdapter(Context context) {
        mContext = context;
        mIetms.add(new MenuIetm("", R.mipmap.ic_launcher));
        mIetms.add(new MenuIetm("all", R.mipmap.all));
        mIetms.add(new MenuIetm("Android", R.mipmap.android));
        mIetms.add(new MenuIetm("iOS", R.mipmap.ios));
        mIetms.add(new MenuIetm("休息视频", R.mipmap.shipin));
        mIetms.add(new MenuIetm("福利", R.mipmap.fuli));
        mIetms.add(new MenuIetm("拓展资源", R.mipmap.tuozhan));
        mIetms.add(new MenuIetm("前端", R.mipmap.ui));
        mIetms.add(new MenuIetm("瞎推荐", R.mipmap.xiatuijian));
        mIetms.add(new MenuIetm("App", R.mipmap.app));
    }

    @Override
    public int getCount() {
        return mIetms.size();
    }

    @Override
    public MenuIetm getItem(int position) {
        return mIetms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_gank, parent,
                        false);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_gank);
                imageView.setImageResource((getItem(position).getMenuIcon()));
            }

        } else {

            if (convertView == null)
                convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_menu, parent,
                        false);
            ((TextView) convertView).setText(getItem(position).getMenuTitle());
            ((TextView) convertView).setCompoundDrawablesWithIntrinsicBounds(getItem(position)
                    .getMenuIcon(), 0, 0, 0);
        }
        return convertView;
    }
}
