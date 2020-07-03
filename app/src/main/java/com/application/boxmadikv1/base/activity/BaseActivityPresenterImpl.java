package com.application.boxmadikv1.base.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.base.UseCaseHandler;


public abstract class BaseActivityPresenterImpl<V extends BaseActivityView> implements BaseActivityPresenter<V> {

    protected abstract String getTag();

    protected V view;
    protected UseCaseHandler handler;
    protected Resources res;

    public BaseActivityPresenterImpl(UseCaseHandler handler, Resources res) {
        this.handler = handler;
        this.res = res;
    }

    public Bundle getExtras() {
        return extras;
    }

    @Override
    public void attachView(V view) {
        Log.d(getTag(), "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(getTag(), "onCreate");
    }

    @Override
    public void onStart() {
        Log.d(getTag(), "onStart");
    }

    @Override
    public void onResume() {
        Log.d(getTag(), "onResume");
    }

    @Override
    public void onPause() {
        Log.d(getTag(), "onPause");
    }

    @Override
    public void onStop() {
        Log.d(getTag(), "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(getTag(), "onDestroy");
        this.view = null;
    }

    protected Bundle extras;

    @Override
    public void setExtras(Bundle extras) {
        Log.d(getTag(), "setExtras");
        this.extras = extras;
    }

    protected void showProgress() {
        if (view != null) view.mostrarProgressBar();
    }

    protected void hideProgress() {
        if (view != null) view.ocultarProgressBar();
    }


}