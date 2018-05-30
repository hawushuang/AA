package com.aa.aa.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aa.aa.R;
import com.aa.aa.base.BaseFragment;

/**
 * Created by Administrator on 2018/5/30.
 */

public class FifthFragment extends BaseFragment {
    public static FifthFragment newInstance() {
        Bundle args = new Bundle();
        FifthFragment fragment = new FifthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fifth, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }
}
