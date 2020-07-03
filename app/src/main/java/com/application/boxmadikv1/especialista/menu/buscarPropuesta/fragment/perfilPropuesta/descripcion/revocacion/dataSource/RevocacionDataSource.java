package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoSolucionRevocacionUi;

import java.util.List;

public interface RevocacionDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onListarTipoMotivoRevocacion(CallBackResultado<List<TipoMotivoRevocacionUi>> tipoMotivoRevocacionUiCallBackResultado);

    void onListarTipoSolucionRevocacion(CallBackResultado<List<TipoSolucionRevocacionUi>> listCallBackResultado);

    void onRegistrarRevocacion(ItemUi itemUi, String idTipSolucionRevocacion, String idTipoMotivoRevocacion, int valorPorcentajeTrabajo, String observacion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);

    void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo, String codigoCotizacion,
                             RevocacionDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);

}
