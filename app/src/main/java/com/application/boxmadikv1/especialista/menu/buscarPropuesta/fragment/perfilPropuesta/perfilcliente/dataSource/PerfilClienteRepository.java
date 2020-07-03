package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource;


import com.application.boxmadikv1.api.response.cliente.ComentarioClienteResponse;
import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.remote.PerfilClienteRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad.DatosCliente;

import java.util.List;

public class PerfilClienteRepository implements PerfilClienteDataSource {

    private static PerfilClienteRepository mInstance = null;

    public static final PerfilClienteRepository getmInstance(PerfilClienteRemote perfilClienteRemote) {
        if (mInstance == null) {
            mInstance = new PerfilClienteRepository(perfilClienteRemote);
        }
        return mInstance;
    }

    private PerfilClienteRemote perfilClienteRemote;

    public PerfilClienteRepository(PerfilClienteRemote perfilClienteRemote) {
        this.perfilClienteRemote = perfilClienteRemote;
    }

    @Override
    public void onObtenerPerfil(String codigoUsuarioPropuesta, String estado_propuesta_proceso, String estado_propuesta_finalizada, CallBackResultado<DatosPerfilResponse> datosPerfilResponseCallBackResultado) {
        perfilClienteRemote.onObtenerPerfil(codigoUsuarioPropuesta, estado_propuesta_proceso, estado_propuesta_finalizada, datosPerfilResponseCallBackResultado);
    }

    @Override
    public void onObtenerListaComentarios(String usuCodigoPropuesta, String codigoPais, CallBackResultado<ComentarioClienteResponse> listCallBackResultado) {
        perfilClienteRemote.onObtenerListaComentarios(usuCodigoPropuesta, codigoPais, listCallBackResultado);
    }
}
