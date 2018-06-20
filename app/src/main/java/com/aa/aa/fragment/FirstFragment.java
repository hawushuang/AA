package com.aa.aa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aa.aa.R;
import com.aa.aa.activity.NewActivity;
import com.aa.aa.base.BaseFragment;
import com.aa.aa.bean.Book;
import com.aa.aa.network.RetrofitHelper;
import com.aa.aa.network.RetrofitService;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/30.
 */

public class FirstFragment extends BaseFragment {
    private TextView tv1;

    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        RetrofitHelper.getInstance().getServer().getSearchBook("英雄志", null, 0, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Book>() {
                    @Override
                    public void accept(Book book) throws Exception {
                        tv1.setText(book.getBooks().get(0).getAuthor_intro());
                    }
                });
        return view;
    }

    private void initView(View view) {
        tv1 = view.findViewById(R.id.tv1);
        tv1.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(_mActivity, NewActivity.class));
            }
        });
    }
}
