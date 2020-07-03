package com.application.boxmadikv1.notificaciones.dataSource;

import com.application.boxmadikv1.notificaciones.dataSource.remote.NotificacionesRemote;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;

import java.util.List;

public class NotificacionesRepository implements NotificacionesDataSource {

    private NotificacionesRemote notificacionesRemote;
    private static NotificacionesRepository mInstance = null;

    public static final NotificacionesRepository getmInstance(NotificacionesRemote notificacionesRemote) {
        if (mInstance == null) {
            mInstance = new NotificacionesRepository(notificacionesRemote);
        }
        return mInstance;
    }

    public NotificacionesRepository(NotificacionesRemote notificacionesRemote) {
        this.notificacionesRemote = notificacionesRemote;
    }

    @Override
    public void onMostrarListaNotificaciones(String codigoUsuario, String codigoPais, String codigoGrupoNoti,int pageCount,String tipoNotificacion, CallBackResultado<List<NotificacionesUi>> listCallBackResultado) {
        notificacionesRemote.onMostrarListaNotificaciones(codigoUsuario, codigoPais, codigoGrupoNoti,pageCount, tipoNotificacion,listCallBackResultado);
    }
}
