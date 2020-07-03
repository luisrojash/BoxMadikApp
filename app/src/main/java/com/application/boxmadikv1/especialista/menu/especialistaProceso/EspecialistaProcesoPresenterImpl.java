package com.application.boxmadikv1.especialista.menu.especialistaProceso;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;

public class EspecialistaProcesoPresenterImpl extends BaseFragmentPresenterImpl<EspecialistaProcesoView> implements EspecialistaProcesoPresenter {

    public static final String TAG = EspecialistaProcesoPresenterImpl.class.getSimpleName();


    public EspecialistaProcesoPresenterImpl(UseCaseHandler handler, Resources res) {
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
