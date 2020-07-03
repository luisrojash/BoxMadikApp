package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionRepository;

public class ObtenerComision extends UseCase<ObtenerComision.RequestValues, ObtenerComision.ResponseValue> {

    private CotizacionRepository cotizacionRepository;

    public ObtenerComision(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        cotizacionRepository.onObtenerComisionPorcentaje(new CotizacionDataSource.CallBackResultado<String>() {
            @Override
            public void onCallBackResultado(String resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        String obtenerComisionPorcentaje;

        public ResponseValue(String obtenerComisionPorcentaje) {
            this.obtenerComisionPorcentaje = obtenerComisionPorcentaje;
        }

        public String getObtenerComisionPorcentaje() {
            return obtenerComisionPorcentaje;
        }
    }
}
