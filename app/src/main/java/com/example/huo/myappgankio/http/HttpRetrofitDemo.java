package com.example.huo.myappgankio.http;

import com.example.huo.myappgankio.rxjava.HttpRxJava;
import com.example.huo.myappgankio.bean.GankIoEntity;
import com.example.huo.myappgankio.bean.ResultBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by huo on 26/06/16.
 */

public class HttpRetrofitDemo extends HttpRxJava<ResultBean> {
    String data = "data";
    String type = "福利";
    String quantity = "20";
    String page = "1";

    public HttpRetrofitDemo() {
        super();
    }

    @Override
    protected Object getObject() {
        return null;
    }

    @Override
    protected Observable<GankIoEntity<List<ResultBean>>> getObsercable() {
        return mRetrofit.create(GetSisterResult.class)
                .getData(data, type, quantity, page);
    }

    public void getData(String data, String type, String quantity, String page) {
        this.data = data;
        this.type = type;
        this.quantity = quantity;
        this.page = page;
        super.getData();
    }

    /**
     * API接口 不能继承其他接口
     */
    interface GetSisterResult {
        /**
         * @param data     {data,day,random}
         * @param type     {Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App 可以选其一}
         * @param quantity {0<quantity<=50}
         * @param page     {页码>0}
         */
        @GET("api/{data}/{type}/{quantity}/{page}")
        Observable<GankIoEntity<List<ResultBean>>> getData(@Path("data") String data,
                                                           @Path("type") String type,
                                                           @Path("quantity") String quantity,
                                                           @Path("page") String page);
    }

}
