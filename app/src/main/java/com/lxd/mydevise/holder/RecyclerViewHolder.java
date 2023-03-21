package com.lxd.mydevise.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author lixd
 */
public class RecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    private T mDatas;

    public RecyclerViewHolder(@NonNull ViewGroup itemView,int resource) {
        super(LayoutInflater.from(itemView.getContext()).inflate(resource,itemView,false));
    }


    public void onBindView(T data){
        mDatas = data;
    }

    public T getData(){
        return mDatas;
    }
}
