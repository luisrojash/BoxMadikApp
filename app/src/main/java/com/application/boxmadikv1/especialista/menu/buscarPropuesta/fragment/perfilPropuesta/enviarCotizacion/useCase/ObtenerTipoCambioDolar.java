package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionRepository;

public class ObtenerTipoCambioDolar extends UseCase<ObtenerTipoCambioDolar.RequestValues,ObtenerTipoCambioDolar.ResponseValue>{

    private CotizacionRepository cotizacionRepository;

    public ObtenerTipoCambioDolar(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        cotizacionRepository.onObtenerTipoCambioDolar(new CotizacionDataSource.CallBackResultado<String>() {
            @Override
            public void onCallBackResultado(String resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        },requestValues.getCodigoPais());
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String codigoPais;

        public RequestValues(String codigoPais) {
            this.codigoPais = codigoPais;
        }

        public String getCodigoPais() {
            return codigoPais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        String tipoCambio;

        public ResponseValue(String tipoCambio) {
            this.tipoCambio = tipoCambio;
        }

        public String getTipoCambio() {
            return tipoCambio;
        }
    }
}
