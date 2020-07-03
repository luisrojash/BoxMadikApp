package com.application.boxmadikv1.especialista.menu.especialistaEnviados.useCase;

import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosRepository;

import java.util.List;

public class ListaCotizaciones extends UseCase<ListaCotizaciones.RequestValues, ListaCotizaciones.ResponseValue> {

    private EspecialistaEnviadosRepository especialistaEnviadosRepository;

    public ListaCotizaciones(EspecialistaEnviadosRepository especialistaEnviadosRepository) {
        this.especialistaEnviadosRepository = especialistaEnviadosRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        especialistaEnviadosRepository.mostrarListaClienteEnviados(requestValues.getCodigo_usuario(),
                requestValues.getCodigo_pais(), requestValues.getPri_Estado(), new EspecialistaEnviadosDataSource.CallBackResultado<ListaCotizacionesResponse>() {
                    @Override
                    public void onCallBackResultado(ListaCotizacionesResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigo_usuario;
        private String codigo_pais;
        private String pri_Estado;

        public RequestValues(String codigo_usuario, String codigo_pais, String pri_Estado) {
            this.codigo_usuario = codigo_usuario;
            this.codigo_pais = codigo_pais;
            this.pri_Estado = pri_Estado;
        }

        public String getCodigo_usuario() {
            return codigo_usuario;
        }

        public String getCodigo_pais() {
            return codigo_pais;
        }

        public String getPri_Estado() {
            return pri_Estado;
        }


    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private ListaCotizacionesResponse listaCotizacionesResponses;

        public ResponseValue(ListaCotizacionesResponse listaCotizacionesResponses) {
            this.listaCotizacionesResponses = listaCotizacionesResponses;
        }

        public ListaCotizacionesResponse getListaCotizacionesResponses() {
            return listaCotizacionesResponses;
        }
    }
}
