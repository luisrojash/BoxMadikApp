package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;

public interface EspecialistaPerfilDistritoPresenter extends BaseActivityPresenter<EspecialistaPerfilDistritoView> {
    void onSpinnerTipoDepartamento(TipoDepartamentoUi tipoDepartamentoUi);

    void onSpinnerTipoProvincia(TipoProvinciaUi tipoProvinciaUi);

    void onSpinnerTipoDistrito(TipoDistritoUi tipoDistritoUi);

    void onClickAgregarDistritos(String nombreDistrito);

    void onValidarItemsAgregar(int contarItem, TipoDistritoUi tipoDistritoUi);

    void onClickEliminar(TipoDistritoUi tipoDistritoUi);

    void onClickSiguiente();

    void onKeyUser(String keyUser,String codigoPais);

    void onClickCloseDepart();

    void onClickCloseProv();

    void onClickCloseDistrit();
}
