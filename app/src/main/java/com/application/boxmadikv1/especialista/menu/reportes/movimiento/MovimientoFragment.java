package com.application.boxmadikv1.especialista.menu.reportes.movimiento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;

public class MovimientoFragment extends BaseFragment<MovimientoView, MovimientoPresenter> implements MovimientoView {

    public static final String TAG = MovimientoFragment.class.getSimpleName();

    public static MovimientoFragment newInstance() {
        Bundle bundle = new Bundle();
        MovimientoFragment fragment = new MovimientoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected MovimientoPresenter getPresenter() {
        return new MovimientoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected MovimientoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.movimiento_espec_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
