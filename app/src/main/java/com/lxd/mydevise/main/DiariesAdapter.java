package com.lxd.mydevise.main;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lxd.mydevise.holder.DiaryHolder;
import com.lxd.mydevise.model.Diary;

import java.util.List;

/**
 * @Author lixd
 */
public class DiariesAdapter extends RecyclerView.Adapter<DiaryHolder> {

    private List<Diary> mDiaries;//日记数据
    private OnLongClickListener<Diary> mOnLongClickListner;

    public DiariesAdapter(List<Diary> diaries){
        mDiaries = diaries;
    }

    public void update(List<Diary> diaries){
        mDiaries = diaries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiaryHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {

        final Diary diary = mDiaries.get(position);
        holder.onBindView(diary);
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnLongClickListner != null && mOnLongClickListner.onLongClick(v,diary);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDiaries.size();
    }

    public void setOnLongClickListener(OnLongClickListener<Diary> onLongClickListener){
        this.mOnLongClickListner = onLongClickListener;
    }

    public interface OnLongClickListener<T>{
        boolean onLongClick(View v,T data);
    }
}
