package com.application.boxmadikv1.cliente.menu.clientePerfil;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface ClientePerfilPresenter extends BaseActivityPresenter<ClientePerfilView> {
    void OnClickEditarPerfil();

    void onDataUser(String usuCodigo, String usuNombre, String usuApellido, String usuFoto);
}
