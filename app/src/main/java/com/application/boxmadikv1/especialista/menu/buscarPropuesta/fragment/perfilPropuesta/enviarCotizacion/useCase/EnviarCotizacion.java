package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase;


import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionRepository;

public class EnviarCotizacion extends UseCase<EnviarCotizacion.RequestValues, EnviarCotizacion.ResponseValue> {

    private CotizacionRepository cotizacionRepository;

    public EnviarCotizacion(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        cotizacionRepository.onEnviarCotizacion(requestValues.getDescripcion(),
                requestValues.getCoti_montoNeto(), requestValues.getCoti_comisionTotal(), requestValues.getCoti_sumaTotalSoles(),
                requestValues.getCoti_sumaTotalDolares(), requestValues.getCoti_monedaCambio(), requestValues.getCodigo_usuario(), requestValues.getCodigo_propuesta_inicial(),
                requestValues.getCoti_fecha_inicio(), requestValues.getCoti_fecha_fin(), requestValues.getBoxmadik_comision(),
                new CotizacionDataSource.CallBackResultado<DefaultResponseRegistro>() {
                    @Override
                    public void onCallBackResultado(DefaultResponseRegistro resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        String descripcion;
        double coti_montoNeto;
        double coti_comisionTotal;
        double coti_sumaTotalSoles;
        double coti_sumaTotalDolares;
        String codigo_usuario;
        double coti_monedaCambio;
        String codigo_propuesta_inicial;
        String coti_fecha_inicio;
        String coti_fecha_fin;
        String boxmadik_comision;

        public RequestValues(String descripcion, double coti_montoNeto, double coti_comisionTotal, double coti_sumaTotalSoles, double coti_sumaTotalDolares, String codigo_usuario, double coti_monedaCambio, String codigo_propuesta_inicial, String coti_fecha_inicio, String coti_fecha_fin, String boxmadik_comision) {
            this.descripcion = descripcion;
            this.coti_montoNeto = coti_montoNeto;
            this.coti_comisionTotal = coti_comisionTotal;
            this.coti_sumaTotalSoles = coti_sumaTotalSoles;
            this.coti_sumaTotalDolares = coti_sumaTotalDolares;
            this.codigo_usuario = codigo_usuario;
            this.coti_monedaCambio = coti_monedaCambio;
            this.codigo_propuesta_inicial = codigo_propuesta_inicial;
            this.coti_fecha_inicio = coti_fecha_inicio;
            this.coti_fecha_fin = coti_fecha_fin;
            this.boxmadik_comision = boxmadik_comision;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public double getCoti_montoNeto() {
            return coti_montoNeto;
        }

        public double getCoti_comisionTotal() {
            return coti_comisionTotal;
        }

        public double getCoti_sumaTotalSoles() {
            return coti_sumaTotalSoles;
        }

        public double getCoti_sumaTotalDolares() {
            return coti_sumaTotalDolares;
        }

        public String getCodigo_usuario() {
            return codigo_usuario;
        }

        public double getCoti_monedaCambio() {
            return coti_monedaCambio;
        }

        public String getCodigo_propuesta_inicial() {
            return codigo_propuesta_inicial;
        }

        public String getCoti_fecha_inicio() {
            return coti_fecha_inicio;
        }

        public String getCoti_fecha_fin() {
            return coti_fecha_fin;
        }

        public String getBoxmadik_comision() {
            return boxmadik_comision;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        DefaultResponseRegistro defaultResponse;

        public ResponseValue(DefaultResponseRegistro defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public DefaultResponseRegistro getDefaultResponse() {
            return defaultResponse;
        }
    }
}
