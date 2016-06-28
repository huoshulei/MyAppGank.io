package com.example.huo.myappgankio.rxjava;

import com.example.huo.myappgankio.bean.GankIoEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by huo on 26/06/16.
 */

public abstract class HttpRxJava<T> {
    private static final String BASE_URL = "http://gank.io/";
    public Retrofit mRetrofit;
    private RxjavaListener mRxjavaListener;
    public HttpRxJava() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(5, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    protected abstract Object getObject();

    protected abstract Observable<GankIoEntity<List<T>>> getObsercable();

    private void getData(Subscriber<List<T>> subscriber) {
        getObsercable()
                .map(new GankIoEntityFunc<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    protected void getData() {
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

        getData(subscriber);
    }

    public void setRxjavaListener(RxjavaListener rxjavaListener) {
        mRxjavaListener = rxjavaListener;
    }

    public interface RxjavaListener {
        void onNext(List t);

        void onError();
    }

    private class GankIoEntityFunc<T> implements Func1<GankIoEntity<List<T>>, List<T>> {

        @Override
        public List<T> call(GankIoEntity<List<T>> tHttpRetrofit) {
            return tHttpRetrofit.getResults();
        }
    }

}
