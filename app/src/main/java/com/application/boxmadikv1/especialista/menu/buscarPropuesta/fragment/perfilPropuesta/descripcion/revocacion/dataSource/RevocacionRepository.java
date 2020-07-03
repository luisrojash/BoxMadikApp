package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.local.RevocacionLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.remote.RevocacionRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoSolucionRevocacionUi;

import java.util.List;

public class RevocacionRepository implements RevocacionDataSource {

    private static RevocacionRepository mInstance = null;
    private RevocacionLocal revocacionLocal;
    private RevocacionRemote revocacionRemote;

    public static RevocacionRepository getmInstance(RevocacionLocal revocacionLocal, RevocacionRemote revocacionRemote) {
        if (mInstance == null) {
            mInstance = new RevocacionRepository(revocacionLocal,revocacionRemote);
        }
        return mInstance;
    }

    public RevocacionRepository(RevocacionLocal revocacionLocal, RevocacionRemote revocacionRemote) {
        this.revocacionLocal = revocacionLocal;
        this.revocacionRemote = revocacionRemote;
    }

    @Override
    public void onListarTipoMotivoRevocacion(CallBackResultado<List<TipoMotivoRevocacionUi>> tipoMotivoRevocacionUiCallBackResultado) {
        revocacionLocal.onListarTipoMotivoRevocacion(tipoMotivoRevocacionUiCallBackResultado);
    }

    @Override
    public void onListarTipoSolucionRevocacion(CallBackResultado<List<TipoSolucionRevocacionUi>> listCallBackResultado) {
        revocacionLocal.onListarTipoSolucionRevocacion(listCallBackResultado);
    }

    @Override
    public void onRegistrarRevocacion(ItemUi itemUi, String idTipSolucionRevocacion, String idTipoMotivoRevocacion, int valorPorcentajeTrabajo, String observacion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        revocacionRemote.onRegistrarRevocacion(itemUi, idTipSolucionRevocacion, idTipoMotivoRevocacion, valorPorcentajeTrabajo, observacion,defaultResponseCallBackResultado);
    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo, String codigoCotizacion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        revocacionRemote.onEnvioNotificacion(grupoNotificacionCodigo, tipoNotificacionCodigo, usuarioCodigo, propuestaCodigo, paisCodigo, codigoCotizacion, defaultResponseCallBackResultado);
    }


}
