package com.application.boxmadikv1.especialista.menu.abstracto.useCase;

import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.EspecAbstractDataSource;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.EspecAbstractRepository;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosRepository;

public class ListaCotizaciones extends UseCase<ListaCotizaciones.RequestValues, ListaCotizaciones.ResponseValue> {

    private EspecAbstractRepository especAbstractRepository;

    public ListaCotizaciones(EspecAbstractRepository especAbstractRepository) {
        this.especAbstractRepository = especAbstractRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        especAbstractRepository.mostrarListaClienteEnviados(requestValues.getCodigo_usuario(),
                requestValues.getCodigo_pais(),
                requestValues.getCoti_estado(),
                new EspecAbstractDataSource.CallBackResultado<ListaCotizacionesResponse>() {
                    @Override
                    public void onCallBackResultado(ListaCotizacionesResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigo_usuario;
        private String codigo_pais;
        private String coti_estado;

        public RequestValues(String codigo_usuario, String codigo_pais, String coti_estado) {
            this.codigo_usuario = codigo_usuario;
            this.codigo_pais = codigo_pais;

            this.coti_estado = coti_estado;
        }

        public String getCodigo_usuario() {
            return codigo_usuario;
        }

        public String getCodigo_pais() {
            return codigo_pais;
        }

        public String getCoti_estado() {
            return coti_estado;
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
