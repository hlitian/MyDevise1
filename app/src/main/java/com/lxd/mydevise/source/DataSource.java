package com.lxd.mydevise.source;

import java.util.List;

/**
 * @Author lixd
 */
public interface DataSource<T> {

    void getAll(DataCallback<List<T>> callback);
    void get(String id,DataCallback<T> callback);
    void update(T diary);
    void clear();
    void delete(String id);
}
