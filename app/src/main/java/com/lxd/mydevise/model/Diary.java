package com.lxd.mydevise.model;

import java.util.UUID;

/**
 * @Author lixd
 * 日记model
 */
public class Diary {

    private String id;//日记唯一标识
    private String title;// 日记标题
    private String description;// 日记内容

    public Diary(String title,String description){
        this(title,description, UUID.randomUUID().toString());
    }

    public Diary(String title,String description,String id){
        this.id=  id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
