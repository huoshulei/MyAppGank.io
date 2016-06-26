package com.example.huo.myappgankio.http;

import java.util.List;

/**
 * Created by huo on 26/06/16.
 */
public interface RxjavaListener<T> {
    void onNext(List<T> t);

    void onError();
}
