package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDireccionUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.google.android.gms.maps.GoogleMap;

public interface ClientePerfilDireccionPresenter extends BaseActivityPresenter<ClientePerfilDireccionView> {

    void onSpinnerTipoDepartamento(TipoDepartamentoUi tipoDepartamentoUi);

    void onSpinnerTipoProvincia(TipoProvinciaUi tipoProvinciaUi);

    void onSpinnerTipoDistrito(TipoDistritoUi tipoDistritoUi);

    void onAutCompleteTipoDireccion(TipoDireccionUi tipoDireccionUi);

    void onObtenerCoordenadasDireccion(TipoDireccionUi tipoDireccionUi);

    void onClickBuscarMapaDireccion(String direccion);

    void onClickSiguiente(String mensaje);

    void onKeyUser(String keyUser,String userFoto,String codigoPais);


    void onGooglenMap(GoogleMap googleMap);

    void onClickCloseDepartamento();

    void onClickCloseProvincia();

    void onClickCloseDistrito();

    void onClickCloseDireccion();
}
