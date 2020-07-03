package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.useCase;

import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource.DetallesDescripcionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource.DetallesDescripcionRepository;

public class ObtenerImagen extends UseCase<ObtenerImagen.RequestValues,ObtenerImagen.ResponseValue>{

    private DetallesDescripcionRepository detallesDescripcionRepository;

    public ObtenerImagen(DetallesDescripcionRepository detallesDescripcionRepository) {
        this.detallesDescripcionRepository = detallesDescripcionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        detallesDescripcionRepository.mostrarImagenPropuesta(requestValues.getCodigoPropuesta(), new DetallesDescripcionDataSource.CallBackResultado<MostrarImagenResponse>() {
            @Override
            public void onCallBackResultado(MostrarImagenResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        String codigoPropuesta;

        public RequestValues(String codigoPropuesta) {
            this.codigoPropuesta = codigoPropuesta;
        }

        public String getCodigoPropuesta() {
            return codigoPropuesta;
        }

        public void setCodigoPropuesta(String codigoPropuesta) {
            this.codigoPropuesta = codigoPropuesta;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        MostrarImagenResponse mostrarImagenResponse;

        public ResponseValue(MostrarImagenResponse mostrarImagenResponse) {
            this.mostrarImagenResponse = mostrarImagenResponse;
        }

        public MostrarImagenResponse getMostrarImagenResponse() {
            return mostrarImagenResponse;
        }
    }
}
