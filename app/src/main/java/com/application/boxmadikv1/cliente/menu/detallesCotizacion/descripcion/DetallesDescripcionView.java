package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.entidad.EspecialidadUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface DetallesDescripcionView extends BaseActivityView<DetallesDescripcionPresenter> {
    void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi);

    void ocultarTeclado();

    void mostrarTextoImagenVacio(String s);

    void mostrarImagenSegunda(String segundaImageUri);

    void mostrarImagenPrimera(String primeraImageUri);

    void mostrarTodasImagenes(String primeraImageUri, String segundaImageUri);

    void mostrarTextoVacio(String no_tiene_especialidades);

    void mostrarListaEspec(List<EspecialidadUi> especialidadUis);

    void ocultarTexto();
}
