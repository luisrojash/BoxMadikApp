package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.useCase;

import com.application.boxmadikv1.api.response.especialista.ComentarioResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource.DatosPerfilEspecialistaDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource.DatosPerfilEspecialistaRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;

import java.util.List;

public class ListaComentarios extends UseCase<ListaComentarios.RequestValues, ListaComentarios.ResponseValue> {

    private DatosPerfilEspecialistaRepository datosPerfilRepository;

    public ListaComentarios(DatosPerfilEspecialistaRepository datosPerfilRepository) {
        this.datosPerfilRepository = datosPerfilRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        datosPerfilRepository.onMostrarListaComentarios(requestValues.getUserKey(), requestValues.getPaisCodigo(),
                new DatosPerfilEspecialistaDataSource.CallBackResultado<List<DatosPropuestaUi>>() {
                    @Override
                    public void onCallBackResultado(List<DatosPropuestaUi> resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String userKey;
        private String paisCodigo;

        public RequestValues(String userKey, String paisCodigo) {
            this.userKey = userKey;
            this.paisCodigo = paisCodigo;
        }

        public String getUserKey() {
            return userKey;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<DatosPropuestaUi> resultado;

        public ResponseValue(List<DatosPropuestaUi> resultado) {
            this.resultado = resultado;
        }

        public List<DatosPropuestaUi> getResultado() {
            return resultado;
        }

        public void setResultado(List<DatosPropuestaUi> resultado) {
            this.resultado = resultado;
        }
    }
}
