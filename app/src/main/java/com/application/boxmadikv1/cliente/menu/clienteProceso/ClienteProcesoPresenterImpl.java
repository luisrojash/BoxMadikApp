package com.application.boxmadikv1.cliente.menu.clienteProceso;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;

public class ClienteProcesoPresenterImpl extends BaseFragmentPresenterImpl<ClienteProcesoView> implements ClienteProcesoPresenter {

    public static final String TAG = ClienteProcesoPresenterImpl.class.getSimpleName();


    public ClienteProcesoPresenterImpl(UseCaseHandler handler, Resources res) {
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
