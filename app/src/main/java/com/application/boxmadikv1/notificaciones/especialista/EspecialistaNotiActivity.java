package com.application.boxmadikv1.notificaciones.especialista;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.notificaciones.NotificacionesActivity;
import com.application.boxmadikv1.notificaciones.dataSource.NotificacionesRepository;
import com.application.boxmadikv1.notificaciones.dataSource.remote.NotificacionesRemote;
import com.application.boxmadikv1.notificaciones.especialista.useCase.ListaNotiEspe;

public class EspecialistaNotiActivity extends NotificacionesActivity<EspecialistaNotiActivity, EspecialistaNotiPresenterImpl> {

    @Override
    protected EspecialistaNotiPresenterImpl obtenerPresenter() {
        NotificacionesRepository notificacionesRepository = NotificacionesRepository.getmInstance(new NotificacionesRemote());
        return new EspecialistaNotiPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new ListaNotiEspe(notificacionesRepository));
    }
}
