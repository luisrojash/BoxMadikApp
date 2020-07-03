package com.application.boxmadikv1.notificaciones.cliente;

import android.os.Bundle;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.notificaciones.NotificacionesActivity;
import com.application.boxmadikv1.notificaciones.NotificacionesPresenter;
import com.application.boxmadikv1.notificaciones.cliente.useCase.ListaNotiCliente;
import com.application.boxmadikv1.notificaciones.dataSource.NotificacionesRepository;
import com.application.boxmadikv1.notificaciones.dataSource.remote.NotificacionesRemote;

public class ClienteNotiActivity extends NotificacionesActivity<ClienteNotiActivity, ClienteNotiPresenterImpl> {


    @Override
    protected ClienteNotiPresenterImpl obtenerPresenter() {
        NotificacionesRepository notificacionesRepository = NotificacionesRepository.getmInstance(new NotificacionesRemote());
        return new ClienteNotiPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new ListaNotiCliente(notificacionesRepository));
    }

   /* @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }*/


}
