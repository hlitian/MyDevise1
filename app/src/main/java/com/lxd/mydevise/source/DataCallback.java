package com.lxd.mydevise.source;

/**
 * @Author lixd
 */
public interface DataCallback<T> {

    void onSuccess(T data);
    void onError();
}
