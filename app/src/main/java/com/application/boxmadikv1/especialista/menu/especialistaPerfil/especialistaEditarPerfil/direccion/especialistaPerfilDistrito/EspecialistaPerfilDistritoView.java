package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad.DireccionUi;

import java.util.List;

public interface EspecialistaPerfilDistritoView extends BaseActivityView<EspecialistaPerfilDistritoPresenter> {
    void mostrarListaTipoDepartamento(List<TipoDepartamentoUi> tipoDepartamentoUis);

    void mostrarListaTipoProvincia(List<TipoProvinciaUi> tipoProvinciaUis);

    void mostrarListaTipoDistrito(List<TipoDistritoUi> tipoDistritoUis);

    void mostrarTextViewProvincia(String s);

    void mostrarTextViewDepartamento(String s);

    void mostrarTextViewDistrito(String s);

    void mostrarMensaje(String escriba_o_seleccione_una_especialidad);

    void validarItemsAgregar(TipoDistritoUi tipoDistritoUi);

    void agregarItemDistrito(TipoDistritoUi tipoDistritoUi);

    void eliminarEspecialidad(TipoDistritoUi tipoDistritoUi);

    void mostrarMensajeErrorAutoCompleteProvinciaError(String mensaje);

    void mostrarMensajeErrorAutoCompleteDepartamentoError(String mensaje);


    void initStartActivityPresentacionActivity(DireccionUi direccionUi);

    void mostrarProgressBarDialog();

    void ocultarProgressBarDialog();

    void initStartActivityMenuEspecialista(String id,String tipoUsuarioEspec);

    void setMostrarLista(List<TipoDistritoUi> distritoUiList);

    void habilitasFunciones();

    void deshabilitaFunciones();

    void finishActivity(String codigoDepartamento);

    void limpiarDepartNull();

    void limpiarProvNull();

    void limpiarDistriNull();

    void deshabilitaProvDist();

    void limpiarListaDistritos( List<TipoDistritoUi> listaDistritos);

    void deshabilitarButtonGuardar();

    void habilitarButtonGuardar();
}
