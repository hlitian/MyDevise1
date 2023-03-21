package com.lxd.mydevise.data;

import com.lxd.mydevise.data.local.DiariesLocalDataSource;
import com.lxd.mydevise.model.Diary;
import com.lxd.mydevise.source.DataCallback;
import com.lxd.mydevise.source.DataSource;
import com.lxd.mydevise.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lixd
 */
public class DiariesRepository implements DataSource<Diary> {

    private static volatile DiariesRepository mInstance;
    private final DataSource<Diary> mLocalDataSource;
    private Map<String,Diary> mMemoryCache;

    public DiariesRepository() {
        mMemoryCache = new LinkedHashMap<>();
        mLocalDataSource = DiariesLocalDataSource.get();

    }

    public static DiariesRepository getInstance(){
        if(mInstance == null){
            synchronized (DiariesRepository.class){
                if(mInstance == null){
                    mInstance = new DiariesRepository();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getAll(DataCallback<List<Diary>> callback) {
        if(!CollectionUtils.isEmpty(mMemoryCache)){
            callback.onSuccess(new ArrayList<>(mMemoryCache.values()));
            return;
        }
        mLocalDataSource.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> data) {
                updateMemoryCache(data);
                callback.onSuccess(new ArrayList<>(mMemoryCache.values()));
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });

    }

    @Override
    public void get(String id, DataCallback<Diary> callback) {

        Diary cachedDiary = getDiaryByIdFromMemory(id);
        if(cachedDiary != null){
            callback.onSuccess(cachedDiary);
            return;
        }
        mLocalDataSource.get(id, new DataCallback<Diary>() {
            @Override
            public void onSuccess(Diary data) {
                mMemoryCache.put(data.getId(), data);
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void update(Diary diary) {
        mLocalDataSource.update(diary);
        mMemoryCache.put(diary.getId(),diary);
    }

    @Override
    public void clear() {

    }

    @Override
    public void delete(String id) {

    }

    private Diary getDiaryByIdFromMemory(String id){
        if(CollectionUtils.isEmpty(mMemoryCache)){
            return null;
        }else{
            return mMemoryCache.get(id);
        }
    }

    private void updateMemoryCache(List<Diary> diaryList){
        mMemoryCache.clear();
        for(Diary diary :diaryList){
            mMemoryCache.put(diary.getId(),diary);
        }
    }
}
