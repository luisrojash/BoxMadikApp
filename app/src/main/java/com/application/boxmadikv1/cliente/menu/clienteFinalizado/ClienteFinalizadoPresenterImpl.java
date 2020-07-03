package com.application.boxmadikv1.cliente.menu.clienteFinalizado;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePendiente.ClientePendientePresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePendiente.useCase.ListarClientePendiente;

public class ClienteFinalizadoPresenterImpl extends BaseFragmentPresenterImpl<ClienteFinalizadoView> implements ClienteFinalizadoPresenter {

    public static final String TAG = ClienteFinalizadoPresenterImpl.class.getSimpleName();

    public ClienteFinalizadoPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onActivityCreated() {

    }
}

/*public class ClienteFinalizadoPresenterImpl extends ClientePendientePresenterImpl {


    public ClienteFinalizadoPresenterImpl(UseCaseHandler handler, Resources res, ListarClientePendiente listarClientePendiente) {
        super(handler, res, listarClientePendiente);
    }
}*/
