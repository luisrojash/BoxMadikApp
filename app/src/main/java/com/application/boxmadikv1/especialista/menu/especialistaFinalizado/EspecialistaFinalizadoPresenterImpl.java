package com.application.boxmadikv1.especialista.menu.especialistaFinalizado;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;

public class EspecialistaFinalizadoPresenterImpl extends BaseFragmentPresenterImpl<EspecialistaFinalizadoView> implements EspecialistaFinalizadoPresenter {

    public static final String TAG = EspecialistaFinalizadoPresenterImpl.class.getSimpleName();

    public EspecialistaFinalizadoPresenterImpl(UseCaseHandler handler, Resources res) {
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
