package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource;

import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;

import java.util.List;

public interface DatosPerfilEspecialistaDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }


    void onMostrarListaComentarios(String keyUser, String codigoPais, DatosPerfilEspecialistaDataSource.CallBackResultado<List<DatosPropuestaUi>> listCallBackResultado);
}
