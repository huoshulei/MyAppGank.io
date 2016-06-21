package com.example.huo.myappgankio.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

/**
 * Created by huo on 20/06/16.
 */

public class Utils {
    public static float getScreenHeight(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
//        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        return height;
    }
}
