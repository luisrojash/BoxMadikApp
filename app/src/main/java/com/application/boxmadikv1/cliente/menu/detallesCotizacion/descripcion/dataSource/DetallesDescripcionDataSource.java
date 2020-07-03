package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource;


import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;

public interface DetallesDescripcionDataSource {


    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void mostrarImagenPropuesta(String codigoPropuesta, CallBackResultado<MostrarImagenResponse> mostrarImagenResponseCallBackResultado);
}
