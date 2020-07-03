package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;

import java.util.List;

public interface DescripcionView extends BaseActivityView<DescripcionPresenter> {

    void mostrarDataInicial(ItemUi itemUi);

    void ocultarTeclado();

    void mostrarTeclado();

    void mostrarTextoImagenVacio(String no_hay_imagenes);

    void mostrarImagenSegunda(String segundaImageUri);

    void mostrarImagenPrimera(String primeraImageUri);

    void mostrarTodasImagenes(String primeraImageUri, String segundaImageUri);

    void mostrarImagenDetallePrimero(String uriImagenPrimero);

    void mostrarImagenDetalleSegundo(String uriImagenSegundo);

    void mostrarTextoListaVacia(String s);

    void mostrarListaEspecialidades(List<EspecialidadesUi> especialidadesUiList);

    void startActivityRevocacion(ItemUi itemUi);

    void mostrarMensaje(String mensajePendiente);

    void mostrarButtonRevocacion();

    void ocultarButtonRevocacion();

    void mostrarButtonRevocacionTextoEnProceso(String texto);

    void habilitarButtonRevocacion();

    void deshabilitarButtonRevocacion();


    void mostrarButtonVerRevocacion();

    void ocultarButtonVerRevocacion();

    void habilitarButtonVerRevocacion();

    void deshabilitarButtonVerRevocacion();

    void initStartActivityRespuestaRevocacion(ItemUi itemUi);
}
