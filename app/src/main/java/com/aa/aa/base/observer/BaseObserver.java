package com.aa.aa.base.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/6/7.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    private Disposable disposable;

    public abstract void onSuccess(T t);


    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        disposable.dispose();
    }

    @Override
    public void onComplete() {
        disposable.dispose();
    }
}
