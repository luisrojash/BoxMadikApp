package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDireccionUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

public interface ClientePerfilDireccionView extends BaseActivityView<ClientePerfilDireccionPresenter> {

    void mostrarListaTipoDepartamento(List<TipoDepartamentoUi> tipoDepartamentoUis);

    void mostrarListaTipoProvincia(List<TipoProvinciaUi> tipoProvinciaUis);

    void mostrarListaTipoDistrito(List<TipoDistritoUi> tipoDistritoUis);

    void mostrarMensaje(String mensaje);

    void desactivarAutoCompleDireccion(TipoDireccionUi tipoDireccionUi);

    void activarAutoCompleDireccion();

    void buscarMapaDireccion(TipoDireccionUi tipoDireccionUi, GoogleMap googleMap);

    void mostrarMensajeErrorAutoCompleteDepartamentoError(String mensaje);

    void mostrarTextViewDepartamento(String s);

    void mostrarMensajeErrorAutoCompleteProvinciaError(String mensaje);

    void mostrarTextViewProvincia(String s);

    void mostrarMensajeErrorAutoCompleteDistritoError(String mensaje);

    void mostrarTextViewDistrito(String s);

    void mostrarEditextDireccion(String direccion);

    void initStartActivityPresentacion(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto,
                                       String codigoDepartamento, String codigoProvincia, String codigoDistrito,
                                       double latitud, double longitud, String descripcionDireccion);

    void actualizarDataPreferencesSinFoto(String nombreEdit, String apellidosEdit, String celularEdit);

    void actualizarDataPreferencesConFoto(String nombreEdit, String apellidosEdit, String celularEdit, String usuFoto);

    void mostrarProgressBarDialog();

    void ocultarProgressBarDialog();

    void iniStartActivityMenuCliente(String ediPerfilCorrectamenteUsuario);

    void mostrarButtonTextGuardar();

    void mostrarButtonTextSiguiente();

    void initStartActivityDistritos(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto, String id, String id1, String id2, double latitud, double longitud, String descripcion);

    void initStartActivityEspecEditPerfil();

    void borrarTextDepartamento();

    void borrarTextProvincia();

    void borrarTextDistrito();

    void deshabilitarText();

    void habilitartText();

    void filtrarAdapterGoogle(String descripcion);

    void initStartActivityRubros();

    void initStartMenuCliente();

    void initStartMenuEspecialista();

    void deshabilitarProvDist();


    void deshabilitarButtonGuardar();

    void habilidarButtonGuardar();
}
