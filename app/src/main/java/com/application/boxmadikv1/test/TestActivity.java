package com.application.boxmadikv1.test;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;

import butterknife.ButterKnife;

public class TestActivity extends BaseActivity<TestView, TestPresenter> implements TestView {

    public static final String TAG = TestActivity.class.getSimpleName();

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected TestPresenter getPresenter() {
        return new TestPresenterImple(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected TestView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }


    @Override
    protected void setContentView() {
        setContentView(R.layout.test_activity);
        ButterKnife.bind(this);


    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }



    @Override

    public void cambiartexto(String texto) {


    }



}
