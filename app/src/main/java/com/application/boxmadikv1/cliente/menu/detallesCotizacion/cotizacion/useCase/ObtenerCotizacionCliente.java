package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.useCase;

import com.application.boxmadikv1.api.response.cliente.ListaCotizaResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource.DetallesCotizaDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource.DetallesCotizaRepository;

public class ObtenerCotizacionCliente extends UseCase<ObtenerCotizacionCliente.RequestValues, ObtenerCotizacionCliente.ResponseValue> {

    private DetallesCotizaRepository detallesCotizaRepository;

    public ObtenerCotizacionCliente(DetallesCotizaRepository detallesCotizaRepository) {
        this.detallesCotizaRepository = detallesCotizaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        detallesCotizaRepository.onListarCotizaciones(requestValues.getUsu_codigo(),
                requestValues.getPais_codigo(),
                requestValues.getPropuesta_inicial_codigo(),
                requestValues.getEstadoCotiza(),
                new DetallesCotizaDataSource.CallBackResultado<ListaCotizaResponse>() {
                    @Override
                    public void onCallBackResultado(ListaCotizaResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String usu_codigo;
        private String pais_codigo;
        private String propuesta_inicial_codigo;
        private String estadoCotiza;

        public RequestValues(String usu_codigo, String pais_codigo, String propuesta_inicial_codigo, String estadoCotiza) {
            this.usu_codigo = usu_codigo;
            this.pais_codigo = pais_codigo;
            this.propuesta_inicial_codigo = propuesta_inicial_codigo;
            this.estadoCotiza = estadoCotiza;
        }

        public String getUsu_codigo() {
            return usu_codigo;
        }

        public String getPais_codigo() {
            return pais_codigo;
        }

        public String getPropuesta_inicial_codigo() {
            return propuesta_inicial_codigo;
        }

        public String getEstadoCotiza() {
            return estadoCotiza;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private ListaCotizaResponse listaCotizacionResponse;

        public ResponseValue(ListaCotizaResponse listaCotizacionResponse) {
            this.listaCotizacionResponse = listaCotizacionResponse;
        }

        public ListaCotizaResponse getListaCotizacionResponse() {
            return listaCotizacionResponse;
        }

        public void setListaCotizacionResponse(ListaCotizaResponse listaCotizacionResponse) {
            this.listaCotizacionResponse = listaCotizacionResponse;
        }
    }
}
