package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles;

import android.net.Uri;

import com.application.boxmadikv1.base.activity.BaseActivityView;

public interface DetallesView extends BaseActivityView<DetallesPresenter> {

    void mostrarCabecera(String imageRubro, String nombreOficio);

    void mostrarImagenSubidaPrimera(Uri uriComprimida);

    void mostrarImagenSubidaSegunda(Uri uriComprimida);

    void mostrarMensajeErrorTitulo(String mensaje);

    void mostrarMensajeErrorDetalles(String mensaje);

    void mostrarMensaje(String mensaje);

    void mostrarDialogProgress(String mensaje);

    void ocultarDialogProgress();

    void startMenuClienteActivity(String estado);

    void limpiarSubidaImagenUno();

    void limpiarSubidaImagenSegundo();

    void limpiarCajasTexto();

    void startActivityBackAtencion(int posicionTipoPrecio, int posicionTipoTurno, int posicionTipoDias);

    void mostrarTextViewUbicacion(String nombreDepartamento, String nombreProvincia, String nombreDistrito);

    void initDialogUbicacion(String codigoPais);

    void habilitarButtonPublicar();

    void deshabilitarButtonPublicar();
}
