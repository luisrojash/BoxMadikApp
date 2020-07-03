package com.application.boxmadikv1.notificaciones.cliente.useCase;

import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.notificaciones.dataSource.NotificacionesDataSource;
import com.application.boxmadikv1.notificaciones.dataSource.NotificacionesRepository;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;

import java.util.List;

public class ListaNotiCliente extends UseCase<ListaNotiCliente.RequestValues, ListaNotiCliente.ResponseValue> {

    public static final String TAG = ListaNotiCliente.class.getSimpleName();

    private NotificacionesRepository notificacionesRepository;

    public ListaNotiCliente(NotificacionesRepository notificacionesRepository) {
        this.notificacionesRepository = notificacionesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "codigoUsuario : " + requestValues.getCodigoUsuario() + " / " +
                "paisCodigo : " +   requestValues.getCodigoPais() + " / " +
                "grupoNotificacionCodigo : " + requestValues.getCodigoGrupoNoti() + " / " +
                "countPAge : " + requestValues.getPageCount() + " / " +
                " requestValues.getTipoNotificacion() : " +  requestValues.getTipoNotificacion());
        notificacionesRepository.onMostrarListaNotificaciones(requestValues.getCodigoUsuario(),
                requestValues.getCodigoPais(),
                requestValues.getCodigoGrupoNoti(),
                requestValues.getPageCount(),
                requestValues.getTipoNotificacion(),
                new NotificacionesDataSource.CallBackResultado<List<NotificacionesUi>>() {
                    @Override
                    public void onCallBackResultado(List<NotificacionesUi> resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigoUsuario;
        private String codigoPais;
        private String codigoGrupoNoti;
        private int pageCount;
        private String tipoNotificacion;

        public RequestValues(String codigoUsuario, String codigoPais, String codigoGrupoNoti, int pageCount, String tipoNotificacion) {
            this.codigoUsuario = codigoUsuario;
            this.codigoPais = codigoPais;
            this.codigoGrupoNoti = codigoGrupoNoti;
            this.pageCount = pageCount;
            this.tipoNotificacion = tipoNotificacion;
        }

        public String getCodigoUsuario() {
            return codigoUsuario;
        }

        public String getCodigoPais() {
            return codigoPais;
        }

        public String getCodigoGrupoNoti() {
            return codigoGrupoNoti;
        }

        public int getPageCount() {
            return pageCount;
        }

        public String getTipoNotificacion() {
            return tipoNotificacion;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<NotificacionesUi> notificacionesUis;

        public ResponseValue(List<NotificacionesUi> notificacionesUis) {
            this.notificacionesUis = notificacionesUis;
        }

        public List<NotificacionesUi> getNotificacionesUis() {
            return notificacionesUis;
        }
    }
}
