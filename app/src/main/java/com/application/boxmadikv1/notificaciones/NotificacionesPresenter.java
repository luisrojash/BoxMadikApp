package com.application.boxmadikv1.notificaciones;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;

public interface NotificacionesPresenter extends BaseActivityPresenter<NotificacionesView> {
    void onScrollStateChanged(RecyclerView recyclerView, int newState);

    void onScrolled(LinearLayoutManager linearLayout, int dx, int dy);

    void onClickNoti(NotificacionesUi notificacionesUi);

    void onClickOptionsLeido(NotificacionesUi notificacionesUi);
}
