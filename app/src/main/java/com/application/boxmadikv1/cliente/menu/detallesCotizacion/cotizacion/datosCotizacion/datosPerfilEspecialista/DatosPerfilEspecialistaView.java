package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;


import java.util.List;

public interface DatosPerfilEspecialistaView extends BaseActivityView<DatosPerfilEspecialistaPresenter>{
    void mostrarMensaje(String mensaje);

    void mostrarListaComentarios(List<DatosPropuestaUi> resultado);

    void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarDialogCursos(String idUsuarioCotizacion);

    void mostrarButtonCalificar();

    void ocultarButtonCalificar();

    void initStartActivityCalificacion(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarBotonDerecho();

    void ocultarBotonDerecho();

    void mostrarBotonIzquierdo();

    void ocultarBotonIzquierdo();

    void mostrarListaCursosDialog(List<CursosUi> cursosUiList);
}
