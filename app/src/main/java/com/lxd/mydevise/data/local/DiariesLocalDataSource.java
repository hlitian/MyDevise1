package com.lxd.mydevise.data.local;

import com.google.gson.reflect.TypeToken;
import com.lxd.mydevise.data.mock.MockDiaries;
import com.lxd.mydevise.model.Diary;
import com.lxd.mydevise.source.DataCallback;
import com.lxd.mydevise.source.DataSource;
import com.lxd.mydevise.util.CollectionUtils;
import com.lxd.mydevise.util.GsonUtils;
import com.lxd.mydevise.util.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lixd
 */
public class DiariesLocalDataSource implements DataSource<Diary> {

    private static final String DIARY_DATA = "diary_data";
    private static final String ALL_DIARY = "all_diary";

    private static SharedPreferenceUtils mSpUtils;
    private static Map<String,Diary> map = new LinkedHashMap<>();
    private static volatile DiariesLocalDataSource mInstance;

    private DiariesLocalDataSource(){
        mSpUtils = SharedPreferenceUtils.getInstance(DIARY_DATA);
        String diaryStr = mSpUtils.get(ALL_DIARY);
        LinkedHashMap<String,Diary> diaryLinkedHashMap = json2Obj(diaryStr);
        if(!CollectionUtils.isEmpty(diaryLinkedHashMap)){
            map = diaryLinkedHashMap;
        }else{
            map = MockDiaries.mock();
        }
    }

    public static DiariesLocalDataSource get(){
        if(mInstance == null){
            synchronized (DiariesLocalDataSource.class){
                if(mInstance == null){
                    mInstance = new DiariesLocalDataSource();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getAll(DataCallback<List<Diary>> callback) {

        if(map .isEmpty()){
            callback.onError();
        }else{
            callback.onSuccess(new ArrayList<>(map.values()));
        }

    }

    @Override
    public void get(String id, DataCallback<Diary> callback) {

        if(map.get(id) == null){
            callback.onError();
        }else{
            callback.onSuccess(map.get(id));
        }
    }

    @Override
    public void update(Diary diary) {

        map.put(diary.getId(),diary);
        mSpUtils.put(ALL_DIARY,obj2Json());
    }

    @Override
    public void clear() {

    }

    @Override
    public void delete(String id) {

    }

    private String obj2Json(){
        return GsonUtils.toJson(map);
    }
    private LinkedHashMap<String,Diary> json2Obj(String diaryStr){
        return GsonUtils.fromJson(diaryStr,new TypeToken<LinkedHashMap<String,Diary>>(){

        }.getType());
    }
}
