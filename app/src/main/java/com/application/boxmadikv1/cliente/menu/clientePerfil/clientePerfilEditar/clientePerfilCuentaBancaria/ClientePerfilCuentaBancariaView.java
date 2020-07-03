package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.entidadUi.TipoBancoUi;

import java.util.List;

public interface ClientePerfilCuentaBancariaView extends BaseActivityView<ClientePerfilCuentaBancariaPresenter> {
    void starActivityMenuCliente();
    void mostrarListaTipoBanco(List<TipoBancoUi> tipoBancoUis);
}
