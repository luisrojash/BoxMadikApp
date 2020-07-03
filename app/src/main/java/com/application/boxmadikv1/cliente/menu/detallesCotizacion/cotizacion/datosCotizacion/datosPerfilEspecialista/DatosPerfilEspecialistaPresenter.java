package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista;

import com.application.boxmadikv1.base.fragment.BaseFragmentPresenter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;

public interface DatosPerfilEspecialistaPresenter extends BaseFragmentPresenter<DatosPerfilEspecialistaView> {
    void onClickCursos();

    void onDatosCursos(DatosPropuestaUi datosPropuestaUi);

    void onClickCalificar();

    void onMostrarListaCursos(String idUsuarioCotizacion);
}
