package com.aa.aa.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aa.aa.R;
import com.aa.aa.base.BaseFragment;
import com.aa.aa.base.observer.BaseObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/30.
 */

public class SecondFragment extends BaseFragment {
    private TextView tv2;
    private Button btn2;

    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        initView(view);
        initOperation();
        return view;
    }

    private void initView(View view) {
        tv2 = view.findViewById(R.id.tv2);
        btn2 = view.findViewById(R.id.btn2);
    }

    private void initOperation() {
        final List<String> list = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            list.add(String.valueOf(i));
        }
        tv2.setText("数量：" + list.size());
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable.intervalRange(1, 20, 1, 1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<Long>() {
                            @Override
                            public void onSuccess(Long aLong) {
                                list.remove(0);
                                tv2.setText("数量：" + list.size());
                            }
                        });
            }
        });
    }
}
