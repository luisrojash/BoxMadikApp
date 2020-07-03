package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase;

import com.application.boxmadikv1.api.response.especialista.DatosCotizacionResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionRepository;

public class ObtenerValidacionCotizacion extends UseCase<ObtenerValidacionCotizacion.RequestValues, ObtenerValidacionCotizacion.ResponseValue> {

    private CotizacionRepository cotizacionRepository;

    public ObtenerValidacionCotizacion(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        cotizacionRepository.onObtenerValidacionCotizacion(requestValues.getUsuarioCodigo(),
                requestValues.getPropuestaCodigo(),
                requestValues.getEstadoCotizacion(),
                requestValues.getPaisCodigo(), new CotizacionDataSource.CallBackResultado<DatosCotizacionResponse>() {
                    @Override
                    public void onCallBackResultado(DatosCotizacionResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        String usuarioCodigo;
        String propuestaCodigo;
        String estadoCotizacion;
        String paisCodigo;

        public RequestValues(String usuarioCodigo, String propuestaCodigo, String estadoCotizacion, String paisCodigo) {
            this.usuarioCodigo = usuarioCodigo;
            this.propuestaCodigo = propuestaCodigo;
            this.estadoCotizacion = estadoCotizacion;
            this.paisCodigo = paisCodigo;
        }

        public String getUsuarioCodigo() {
            return usuarioCodigo;
        }

        public String getPropuestaCodigo() {
            return propuestaCodigo;
        }

        public String getEstadoCotizacion() {
            return estadoCotizacion;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private DatosCotizacionResponse datosCotizacionEspecialistaResponse;

        public ResponseValue(DatosCotizacionResponse datosCotizacionEspecialistaResponse) {
            this.datosCotizacionEspecialistaResponse = datosCotizacionEspecialistaResponse;
        }

        public DatosCotizacionResponse getDatosCotizacionEspecialistaResponse() {
            return datosCotizacionEspecialistaResponse;
        }
    }
}
