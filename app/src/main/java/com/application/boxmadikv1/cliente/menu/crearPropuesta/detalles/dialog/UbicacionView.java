package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog;

import com.application.boxmadikv1.base.BaseView;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;

import java.util.List;

public interface UbicacionView extends BaseView<UbicacionPresenter> {
    void mostrarListaTipoDepartamento(List<TipoDepartamentoUi> tipoDepartamentoUis);

    void mostrarTextViewDepartamento(String s);

    void mostrarListaTipoProvincia(List<TipoProvinciaUi> tipoProvinciaUis);

    void mostrarTextViewProvincia(String s);

    void mostrarListaTipoDistrito(List<TipoDistritoUi> tipoDistritoUis);

    void mostrarTextViewDistrito(String s);

    void borrarTextDepartamento();

    void borrarTextProvincia();

    void borrarTextDistrito();

    void mostrarMensajeErrorAutoCompleteDepartamentoError(String mensaje);

    void mostrarMensajeErrorAutoCompleteProvinciaError(String mensaje);

    void mostrarMensajeErrorAutoCompleteDistritoError(String mensaje);

    void deshabilitarAutoCompleteTextViewProvincia();

    void habilitarAutoCompleteTextViewProvincia();//

    void deshabilitarAutoCompleteTextViewDistrito();

    void habilitarAutoCompleteTextViewDistrito();

    void enviarDataPadreActivity(String codigoDepartamento, String nombreDepartamento, String codigoProvincia, String nombreProvincia, String codigoDistrito, String nombreDistrito);
}
