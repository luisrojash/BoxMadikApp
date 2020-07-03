package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente;

import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad.DatosCliente;

import java.util.List;

public interface PerfilClienteView  extends BaseActivityView<PerfilClientePresenter>{
    void mostrarMensaje(String mensaje);

    void mostrarDatosPerfil(DatosPerfilResponse.DatosPerfilClienteResponse datosPerfilClienteResponse, int resultado);

    void mostrarListaComentarios(List<DatosCliente> clienteList);

    void ocultarButtonCalificar();

    void mostrarButtonCalificar();

    void initStartCalificarClienteActivity(ItemUi itemUi, String nombreCliente, String paisCliente, String imagenCliente);

    void mostrarBotonDerecho();

    void ocultarBotonDerecho();

    void mostrarBotonIzquierdo();

    void ocultarBotonIzquierdo();

    void mostrarDatosPerfilRazonSocial(DatosPerfilResponse.DatosPerfilClienteResponse datosPerfilClienteResponse);
}
