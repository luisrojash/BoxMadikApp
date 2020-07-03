package com.application.boxmadikv1.cliente.menu.abstracto;

import com.application.boxmadikv1.base.fragment.BaseFragmentPresenter;
import com.application.boxmadikv1.cliente.menu.abstracto.entidad.ClienteEstadosUi;

public interface ClientAbstractPresenter extends BaseFragmentPresenter<ClientAbstractView>{
    void onClickEstado(ClienteEstadosUi clienteEstadosUi);
}
