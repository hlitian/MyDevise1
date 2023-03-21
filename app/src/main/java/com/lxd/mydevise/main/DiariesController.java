package com.lxd.mydevise.main;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxd.mydevise.EnApplication;
import com.lxd.mydevise.R;
import com.lxd.mydevise.data.DiariesRepository;
import com.lxd.mydevise.model.Diary;
import com.lxd.mydevise.source.DataCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lixd
 */
public class DiariesController {

    private final DiariesRepository mDiariesRepository;
    private final DiariesFragment mView;
    private DiariesAdapter mListAdapter;

    public DiariesController(DiariesFragment diariesFragment) {
        mDiariesRepository = DiariesRepository.getInstance();
        mView = diariesFragment;
        mView.setHasOptionsMenu(true);
        initAdapter();
    }

    private void initAdapter() {
        mListAdapter = new DiariesAdapter(new ArrayList<>());
        mListAdapter.setOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
                showInputDialog(data);
                return false;
            }
        });
    }
    
    public void loadDiaries(){
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> data) {
                processDiaries(data);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void showError() {
        showMessage(mView.getString(R.string.error));
    }

    private void processDiaries(List<Diary> data) {
        mListAdapter.update(data);
    }

    public void setDiariesList(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        recyclerView.setAdapter(mListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(mView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void showInputDialog(Diary data) {
        final EditText editText = new EditText(mView.getContext()); // 创建输入框
        editText.setText(data.getDescription()); // 设置输入框默认文字为日志详情信息
        new AlertDialog.Builder(mView.getContext()).setTitle(data.getTitle())
                .setView(editText)
                .setPositiveButton(EnApplication.get().getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { // 确认按钮点击监听
                                data.setDescription(editText.getText().toString()); // 修改日记信息为输入框信息
                                mDiariesRepository.update(data); // 更新日记数据
                                loadDiaries(); // 重新加载列表
                            }
                        })
                .setNegativeButton(EnApplication.get().getString(R.string.cancel), null).show(); // 弹出对话框
    }

    public void gotoWriteDiary(){
        showMessage(mView.getString(R.string.error));
    }

    private void showMessage(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show(); // 弹出文字提示信息
    }
    
}
