package com.example.huo.myappgankio.bean;

import java.util.List;

/**
 * Created by huo on 26/06/16.
 */

public class GankIoEntity<T> {
    private boolean error;

    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
