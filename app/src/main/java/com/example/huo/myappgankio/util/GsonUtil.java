package com.example.huo.myappgankio.util;

import com.example.huo.myappgankio.bean.SisterBean;
import com.google.gson.Gson;

/**
 * Created by huo on 19/06/16.
 */

public class GsonUtil {
    private static Gson mGson;

    public static Gson getGson() {
        if (mGson == null)
            mGson = new Gson();
        return mGson;
    }

    public static SisterBean getSisterBean(String json) {
        SisterBean sisterBean = getGson().fromJson(json, SisterBean.class);
        return sisterBean;
    }
}
