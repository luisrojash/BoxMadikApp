package com.application.boxmadikv1.base.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public abstract class BaseActivity<V extends BaseActivityView<P>, P extends BaseActivityPresenter<V>> extends AppCompatActivity implements BaseActivityView<P> {

    protected abstract String getTag();

    protected abstract AppCompatActivity getActivity();

    protected abstract P getPresenter();

    protected abstract V getBaseView();

    protected abstract Bundle getExtrasInf();

    protected P presenter;

    protected abstract void setContentView();

    protected abstract ProgressBar getProgressBar();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getTag(), "onCreate");
        setContentView();
        setupProgressBar();
        setupPresenter();
        if (presenter != null) presenter.onCreate();
    }
    private void setupProgressBar() {
        progressBar = getProgressBar();
    }


    private void setupPresenter() {
        presenter = (P) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = getPresenter();
            presenter.setExtras(getExtrasInf());
        }
        setPresenter(presenter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getTag(), "onStart");
        if (presenter != null) presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getTag(), "onResume");
        if (presenter != null) presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getTag(), "onPause");
        if (presenter != null) presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getTag(), "onStop");
        if (presenter != null) presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getTag(), "onDestroy");
        if (presenter != null) presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d(getTag(), "onBackPressed");
        if (presenter != null) presenter.onBackPressed();
        super.onBackPressed();
    }

    @Override
    public void setPresenter(P presenter) {
        if (presenter != null) {
            presenter.attachView(getBaseView());
        }
    }


    @Override
    public P onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    public void mostrarProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void ocultarProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }



}