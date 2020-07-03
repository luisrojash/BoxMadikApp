package com.application.boxmadikv1.especialista.menu.reportes.deposito;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;

public class DepositoFragment extends BaseFragment<DepositoView, DepositoPresenter> implements DepositoView {

    public static final String TAG = DepositoFragment.class.getSimpleName();

    public static DepositoFragment newInstance(){
        Bundle bundle = new Bundle();
        DepositoFragment fragment = new DepositoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected DepositoPresenter getPresenter() {
        return new DepositoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources());
    }

    @Override
    protected DepositoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.deposito_espec_fragment,container,false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
