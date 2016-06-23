package com.example.huo.myappgankio.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.android.volley.toolbox.ImageRequest;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.base.BaseActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class SisterActivity extends BaseActivity {
    private static final String TAG = "SisterActivity";
    Uri mUri;
    @BindView(R.id.iv_sister)
    ImageView mIvSister;

    @Override
    public void initView() {
        setContentView(R.layout.activity_sister);
        ButterKnife.bind(this);
        mUri = getIntent().getData();
        super.initView();
        initDataRetrofit(mUri);
    }

//    @Override
//    public void initData() {
//        ImageRequest imageRequest = new ImageRequest(mUri.toString(), new Response
//                .Listener<Bitmap>() {
//
//
//            @Override
//            public void onResponse(Bitmap response) {
//                mIvSister.setImageBitmap(response);
//            }
//        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mIvSister.setImageResource(R.mipmap.sorry);
//            }
//        });
//        mQueue.add(imageRequest);
//        super.initData();
//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        Log.d(TAG, "initData: " + metrics.heightPixels + ">>>>" + metrics.widthPixels);
//    }

    public void initDataRetrofit() {

        Observable.just(mUri).map(new Func1<Uri, Bitmap>() {
            @Override
            public Bitmap call(Uri uri) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(uri.toString()).build();

                byte[] bytes = new byte[0];
                try {
                    bytes = client.newCall(request).execute().body().bytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mIvSister.setImageBitmap(bitmap);
                    }
                });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        MovieService movieService = retrofit.create(MovieService.class);
//        movieService.getSister()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//
//                    }
//                });
    }

    public void initDataRetrofit(Uri uri) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io/api/")
                //这个参数只是因为方法需要一个参数而已 没有实际作用
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofit.create(MovieService.class)
                .getSister(uri.toString())
                .map(new Func1<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap call(ResponseBody responseBody) {
                        return BitmapFactory.decodeStream(responseBody.byteStream());
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mIvSister.setImageBitmap(bitmap);
                    }
                });
    }

    interface MovieService {
        @GET
        Observable<ResponseBody> getSister(@Url String url);
    }

    @Override
    public void initEvent() {
        mIvSister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                        .cancel_action);
                Snackbar.make(coordinatorLayout, "你点？ 你再点？", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
