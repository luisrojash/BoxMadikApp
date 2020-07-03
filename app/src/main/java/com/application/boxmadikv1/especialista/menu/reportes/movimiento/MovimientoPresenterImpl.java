package com.application.boxmadikv1.especialista.menu.reportes.movimiento;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;

public class MovimientoPresenterImpl extends BaseFragmentPresenterImpl<MovimientoView> implements MovimientoPresenter {

    public static final String TAG = MovimientoPresenterImpl.class.getSimpleName();

    public MovimientoPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }
}
