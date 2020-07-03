package com.application.boxmadikv1.notificaciones.listener;

import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;

public interface NotificacionListener {

    void onClickNoti(NotificacionesUi notificacionesUi);

    void onClickOptions(NotificacionesUi notificacionesUi);
}
