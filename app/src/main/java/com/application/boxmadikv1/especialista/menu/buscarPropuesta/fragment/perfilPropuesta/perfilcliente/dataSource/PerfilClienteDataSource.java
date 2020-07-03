package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource;

import com.application.boxmadikv1.api.response.cliente.ComentarioClienteResponse;
import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad.DatosCliente;

import java.util.List;

public interface PerfilClienteDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onObtenerPerfil(String codigoUsuarioPropuesta, String estado_propuesta_proceso, String estado_propuesta_finalizada, CallBackResultado<DatosPerfilResponse> datosPerfilResponseCallBackResultado);

    void onObtenerListaComentarios(String usuCodigoPropuesta, String codigoPais, PerfilClienteDataSource.CallBackResultado<ComentarioClienteResponse> callBackResultado);
}
