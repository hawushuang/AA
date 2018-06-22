package com.aa.aa.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aa.aa.R;
import com.aa.aa.adapter.MyRecyclerViewAdapter;
import com.aa.aa.base.BaseFragment;
import com.aa.aa.widget.dragselectrecyclerview.DragSelectTouchListener;
import com.aa.aa.widget.dragselectrecyclerview.DragSelectionProcessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ThirdFragment extends BaseFragment {
    private DragSelectionProcessor.Mode mMode = DragSelectionProcessor.Mode.Simple;

    private List<Integer> mDatas;

    private LinearLayout ll_edit;
    private TextView tv_selected;
    private Button btn_delete;
    private Button btn_selectall;
    private Button btn_unselectall;
    private Button btn_done;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter mAdapter;

    private DragSelectTouchListener mDragSelectTouchListener;
    private DragSelectionProcessor mDragSelectionProcessor;

    public static ThirdFragment newInstance() {
        Bundle args = new Bundle();
        ThirdFragment fragment = new ThirdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        mDatas = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mDatas.add(i);
        }
        initView(view);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(glm);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new MyRecyclerViewAdapter(getActivity(), mDatas);
        recyclerView.setAdapter(mAdapter);

        mDragSelectionProcessor = new DragSelectionProcessor(new DragSelectionProcessor.ISelectionHandler() {
            @Override
            public HashSet<Integer> getSelection() {
                return mAdapter.getSelection();
            }

            @Override
            public boolean isSelected(int index) {
                return mAdapter.getSelection().contains(index);
            }

            @Override
            public void updateSelection(int start, int end, boolean isSelected, boolean calledFromOnStart) {
                mAdapter.selectRange(start, end, isSelected);
                tv_selected.setText(mAdapter.getCountSelected() + "");
            }
        }).withMode(mMode);
        mDragSelectTouchListener = new DragSelectTouchListener()
                .withSelectListener(mDragSelectionProcessor);
        mDragSelectionProcessor.withMode(mMode);
        mAdapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mAdapter.toggleSelection(position);
                tv_selected.setText(mAdapter.getCountSelected() + "");
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                ll_edit.setVisibility(View.VISIBLE);
                mAdapter.setEdit(true);
                mDragSelectTouchListener.startDragSelection(position);
                return true;
            }
        });
        recyclerView.addOnItemTouchListener(mDragSelectTouchListener);

        btn_selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.selectAll();
                tv_selected.setText(mAdapter.getCountSelected() + "");
            }
        });
        btn_unselectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.deselectAll();
                tv_selected.setText(mAdapter.getCountSelected() + "");
            }
        });
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_edit.setVisibility(View.GONE);
                mAdapter.setEdit(false);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashSet<Integer> selection = mAdapter.getSelection();
                int index = 0;
                Iterator<Integer> it = mDatas.iterator();
                while (it.hasNext()) {
                    it.next();
                    if (selection.contains(index)) {
                        it.remove();
                    }
                    index++;
                }
                mAdapter.setmDatas(mDatas);
                mAdapter.deselectAll();
                tv_selected.setText(mAdapter.getCountSelected() + "");
            }
        });
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle);
        ll_edit = view.findViewById(R.id.ll_edit);
        tv_selected = view.findViewById(R.id.tv_selected);
        btn_delete = view.findViewById(R.id.btn_delete);
        btn_selectall = view.findViewById(R.id.btn_selectall);
        btn_unselectall = view.findViewById(R.id.btn_unselectall);
        btn_done = view.findViewById(R.id.btn_done);
    }
}
