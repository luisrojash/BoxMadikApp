package com.application.boxmadikv1.especialista.menu.reportes.deposito;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;

public class DepositoPresenterImpl extends BaseFragmentPresenterImpl<DepositoView> implements DepositoPresenter {

    public static final String TAG = DepositoPresenterImpl.class.getSimpleName();

    public DepositoPresenterImpl(UseCaseHandler handler, Resources res) {
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
