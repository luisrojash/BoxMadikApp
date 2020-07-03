package com.application.boxmadikv1.base.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityView;

public abstract class BaseFragmentPresenterImpl <V extends BaseActivityView> implements BaseFragmentPresenter<V> {

    protected abstract String getTag();

    protected V view;
    protected UseCaseHandler handler;
    protected Resources res;

    public BaseFragmentPresenterImpl(UseCaseHandler handler, Resources res) {
        this.handler = handler;
        this.res = res;
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
    public void onCreateView() {
        Log.d(getTag(), "onCreateView");
    }

    @Override
    public void onViewCreated() {
        Log.d(getTag(), "onViewCreated");
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
    public void onDestroyView() {
        Log.d(getTag(), "onDestroyView");
        this.view = null;
    }

    @Override
    public void onDestroy() {
        Log.d(getTag(), "onDestroy");
    }

    @Override
    public void onDetach() {
        Log.d(getTag(), "onDetach");
    }

    protected Bundle extras;

    @Override
    public void setExtras(Bundle extras) {
        Log.d(getTag(), "setExtras");
        this.extras = extras;
    }

    @Override
    public void onBackPressed() {
        Log.d(getTag(), "onBackPressed");
    }


    protected void mostrarProgressBar() {
        if (view != null) view.mostrarProgressBar();
    }

    protected void ocultarProgressBar() {
        if (view != null) view.ocultarProgressBar();
    }
}