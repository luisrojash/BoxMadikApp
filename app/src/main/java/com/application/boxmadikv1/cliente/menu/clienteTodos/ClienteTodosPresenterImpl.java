package com.application.boxmadikv1.cliente.menu.clienteTodos;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;

public class ClienteTodosPresenterImpl extends BaseFragmentPresenterImpl<ClienteTodosView> implements ClienteTodosPresenter {

    public static final String TAG = ClienteTodosPresenterImpl.class.getSimpleName();


    public ClienteTodosPresenterImpl(UseCaseHandler handler, Resources res) {
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
