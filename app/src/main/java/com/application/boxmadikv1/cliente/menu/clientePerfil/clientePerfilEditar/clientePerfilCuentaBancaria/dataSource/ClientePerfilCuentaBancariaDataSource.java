package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.entidadUi.TipoBancoUi;

import java.util.List;

public interface ClientePerfilCuentaBancariaDataSource {

    /*respuesta*/
    interface CallBackResultado{
        void onResultado(List<TipoBancoUi> tipoBancoUis);

    }

    /*peticion*/
    void onListarBanco(ClientePerfilCuentaBancariaDataSource.CallBackResultado callBackResultado);


}
