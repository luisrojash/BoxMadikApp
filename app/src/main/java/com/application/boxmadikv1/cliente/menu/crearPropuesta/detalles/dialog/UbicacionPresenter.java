package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog;

import android.os.Bundle;

import com.application.boxmadikv1.base.BasePresenter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;

public interface UbicacionPresenter extends BasePresenter<UbicacionView> {
    void onExtras(Bundle bundle);

    void onActivityCreated(Bundle savedInstanceState);

    void onSpinnerTipoDepartamento(TipoDepartamentoUi tipoDepartamentoUi);

    void onSpinnerTipoProvincia(TipoProvinciaUi tipoProvinciaUi);

    void onSpinnerTipoDistrito(TipoDistritoUi tipoDistritoUi);

    void onClickCloseDepartamento();

    void onClickCloseProvincia();

    void onClickCloseDistrito();

    void onGuardarUbicacion();
}
