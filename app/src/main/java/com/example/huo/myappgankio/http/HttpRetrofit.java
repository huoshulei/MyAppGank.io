package com.example.huo.myappgankio.http;

import com.example.huo.myappgankio.bean.DayBean;
import com.example.huo.myappgankio.bean.GankIoEntity;
import com.example.huo.myappgankio.bean.RandomBean;
import com.example.huo.myappgankio.bean.ResultBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by huo on 26/06/16.
 */

public class HttpRetrofit<T> {
    public static final String BASE_URL = "http://gank.io/";
    public Retrofit mRetrofit;
    private GetSisterResult mGetResult;
//    Mode mode = Mode.DATA;
    RxjavaListener mRxjavaListener;

    public HttpRetrofit(Mode type) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(5, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mGetResult = mRetrofit.create(GetSisterResult.class);
//        this.mode = type;
    }

    private Object getObject(Mode type) {
        if (type == Mode.DATA) return mRetrofit.create(GetSisterResult.class);
        if (type == Mode.DAY) return mRetrofit.create(GetDayResult.class);
        if (type == Mode.RANDOM) return mRetrofit.create(GetRandomResult.class);
        return null;
    }


    private void getData(Subscriber<List<T>> subscriber, String type, String quantity, String
            page) {
        mGetResult.getData(type, quantity, page)
                .map(new GankIoEntityFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    public void getData(String type, String quantity, String page) {
        Subscriber<List<T>> subscriber = new Subscriber<List<T>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRxjavaListener.onError();
            }

            @Override
            public void onNext(List<T> ts) {
                mRxjavaListener.onNext(ts);
            }


        };

        getData(subscriber, type, quantity, page);
    }

    public void setRxjavaListener(RxjavaListener rxjavaListener) {
        mRxjavaListener = rxjavaListener;
    }

    private GetSisterResult getGetResult(Mode type) {
        if (type == Mode.DATA) return mRetrofit.create(GetSisterResult.class);
//        if (type == Mode.DAY) return mRetrofit.create(GetDayResult.class);
//        if (type == Mode.RANDOM) return mRetrofit.create(GetRandomResult.class);
        return null;
    }

    public enum Mode {
        DATA,
        DAY,
        RANDOM,
    }

    /**
     * API接口 不能继承其他接口
     */
    interface GetSisterResult {
        /**
         * @param type     {Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App 可以选其一}
         * @param quantity {0<quantity<=50}
         * @param page     {页码>0}
         */
        @GET("api/data/{type}/{quantity}/{page}")
        Observable<GankIoEntity<List<ResultBean>>> getData(
                @Path("type") String type,
                @Path("quantity") String quantity,
                @Path("page") String page);
    }

    interface GetDayResult extends GetResult<DayBean> {
        /**
         * @param data {day}
         * @param type     {Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App 可以选其一}
         * @param quantity {0<quantity<=50}
         * @param page     {页码>0}
         */
    }

    interface GetRandomResult extends GetResult<RandomBean> {
        /**
         * @param data {random}
         * @param type     {Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App 可以选其一}
         * @param quantity {0<quantity<=50}
         * @param page     {页码>0}
         */
    }

    interface GetResult<T> {
        /**
         * @param type     {Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App 可以选其一}
         * @param quantity {0<quantity<=50}
         * @param page     {页码>0}
         */
        @GET("api/{data}/{type}/{quantity}/{page}")
        Observable<GankIoEntity<List<T>>> getData(
                @Path("data") String data,
                @Path("type") String type,
                @Path("quantity") String quantity,
                @Path("page") String page);
    }

    public class GankIoEntityFunc<T> implements Func1<GankIoEntity<List<T>>, List<T>> {

        @Override
        public List<T> call(GankIoEntity<List<T>> tHttpRetrofit) {
//            if (tHttpRetrofit.getResults() == null && tHttpRetrofit.isError())
//                try {
//                    throw new Exception("网络异常");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            return tHttpRetrofit.getResults();

        }
    }

}
