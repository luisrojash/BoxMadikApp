package com.application.boxmadikv1.especialista.menu.abstracto.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.local.EspecAbstractLocal;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.remote.EspecAbstractRemote;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosDataSource;

public class EspecAbstractRepository implements EspecAbstractDataSource {

    private EspecAbstractLocal especAbstractLocal;
    private EspecAbstractRemote especAbstractRemote;
    private static EspecAbstractRepository mInstance = null;

    public static final EspecAbstractRepository getmInstance(EspecAbstractLocal especAbstractLocal, EspecAbstractRemote especAbstractRemote) {
        if (mInstance == null) {
            mInstance = new EspecAbstractRepository(especAbstractLocal, especAbstractRemote);
        }
        return mInstance;
    }

    public EspecAbstractRepository(EspecAbstractLocal especAbstractLocal, EspecAbstractRemote especAbstractRemote) {
        this.especAbstractLocal = especAbstractLocal;
        this.especAbstractRemote = especAbstractRemote;
    }

    @Override
    public void mostrarListaClienteEnviados(String usuarioCodigo, String paisCodigo, String priEstado,final CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado) {
        especAbstractRemote.mostrarListaClienteEnviados(usuarioCodigo, paisCodigo, priEstado, new CallBackResultado<ListaCotizacionesResponse>() {
            @Override
            public void onCallBackResultado(ListaCotizacionesResponse resultado) {
                especAbstractLocal.llenarDataFaltante(resultado, new EspecialistaEnviadosDataSource.CallBackResultado<ListaCotizacionesResponse>() {
                    @Override
                    public void onCallBackResultado(ListaCotizacionesResponse resultado) {
                        listaCotizacionesResponseCallBackResultado.onCallBackResultado(resultado);
                    }
                });
            }
        });
    }

    @Override
    public void eliminarItem(EspecialistaEstadosUi especialistaEstadosUi, String keyUser, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        especAbstractRemote.eliminarItem(especialistaEstadosUi, keyUser, defaultResponseCallBackResultado);
    }
}
