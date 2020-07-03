package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource;

import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource.remote.DescripcionRemote;

public class DescripcionRepository implements DescripcionDataSource {

    private DescripcionRemote descripcionRemote;

    private static DescripcionRepository mInstance = null;

    public static final DescripcionRepository getmInstance(DescripcionRemote descripcionRemote) {
        if (mInstance == null) {
            mInstance = new DescripcionRepository(descripcionRemote);
        }
        return mInstance;
    }


    public DescripcionRepository(DescripcionRemote descripcionRemote) {
        this.descripcionRemote = descripcionRemote;
    }

    @Override
    public void onMostrarImagenPropuesta(String codigo_propuesta_inicial, CallbackResultado<MostrarImagenResponse> mostrarImagenResponseCallbackResultado) {
        descripcionRemote.onMostrarImagenPropuesta(codigo_propuesta_inicial, mostrarImagenResponseCallbackResultado);
    }
}
