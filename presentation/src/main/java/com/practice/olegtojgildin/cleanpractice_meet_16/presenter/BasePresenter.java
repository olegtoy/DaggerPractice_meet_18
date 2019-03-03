package com.practice.olegtojgildin.cleanpractice_meet_16.presenter;

abstract class BasePresenter<T> {

    protected T mView;

    public void attachView(T view) {
        this.mView = view;
    }

    public void detachView() {
        this.mView = null;
    }
}
