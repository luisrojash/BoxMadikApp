package com.application.boxmadikv1.notificaciones.dataSource;

import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;

import java.util.List;

public interface NotificacionesDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onMostrarListaNotificaciones(String codigoUsuario, String codigoPais,
                                      String codigoGrupoNoti,int pageCount,
                                      String tipoNotificacion,NotificacionesDataSource.CallBackResultado<List<NotificacionesUi>> listCallBackResultado);

}
