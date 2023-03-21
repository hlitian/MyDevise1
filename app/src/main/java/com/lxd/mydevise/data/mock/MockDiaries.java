package com.lxd.mydevise.data.mock;

import com.lxd.mydevise.model.Diary;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author lixd
 */
public class MockDiaries {

    private static final String DESCRIPTION="今天，天气";

    public static Map<String, Diary> mock(){
        return mock(new LinkedHashMap<String,Diary>());
    }

    public static Map<String,Diary> mock(Map<String,Diary> data){
        Diary test1 = getDiary("11-2 节日");
        data.put(test1.getId(),test1);

        Diary test2 = getDiary("11-3 节日2");
        data.put(test2.getId(),test2);

        Diary test3 = getDiary("11-3 节日3");
        data.put(test3.getId(),test3);
        return data;
    }

    private static Diary getDiary(String title){
        return new Diary(title,DESCRIPTION);
    }
}
