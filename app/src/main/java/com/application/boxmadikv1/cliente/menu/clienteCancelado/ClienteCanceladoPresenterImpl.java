package com.application.boxmadikv1.cliente.menu.clienteCancelado;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;

public class ClienteCanceladoPresenterImpl extends BaseFragmentPresenterImpl<ClienteCanceladoView> implements ClienteCanceladoPresenter {

    public static final String TAG = ClienteCanceladoPresenterImpl.class.getSimpleName();


    public ClienteCanceladoPresenterImpl(UseCaseHandler handler, Resources res) {
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
