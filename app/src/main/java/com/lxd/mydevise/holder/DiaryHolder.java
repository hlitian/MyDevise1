package com.lxd.mydevise.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lxd.mydevise.R;
import com.lxd.mydevise.model.Diary;

/**
 * @Author lixd
 */
public class DiaryHolder extends RecyclerViewHolder<Diary> {

    private View.OnLongClickListener mOnLongClickListener;

    public DiaryHolder(@NonNull ViewGroup itemView) {
        super(itemView, R.layout.list_diary_item);
    }

    public void setOnLongClickListener(View.OnLongClickListener mOnLongClickListener){
        this.mOnLongClickListener = mOnLongClickListener;
    }

    @Override
    public void onBindView(Diary diary) {
        super.onBindView(diary);
        TextView title = itemView.findViewById(R.id.title);
        title.setText(diary.getTitle());

        TextView desc  = itemView.findViewById(R.id.desc);
        desc.setText(diary.getDescription());

        itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v);
            }
        });
    }
}
