package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.useCase;

import com.application.boxmadikv1.api.response.cliente.ComentarioClienteResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.PerfilClienteDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.PerfilClienteRepository;

public class ObtenerListaComentarios extends UseCase<ObtenerListaComentarios.RequestValues, ObtenerListaComentarios.ResponseValue> {

    private PerfilClienteRepository perfilClienteRepository;

    public ObtenerListaComentarios(PerfilClienteRepository perfilClienteRepository) {
        this.perfilClienteRepository = perfilClienteRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        perfilClienteRepository.onObtenerListaComentarios(requestValues.getUsuCodigoPropuesta(), requestValues.getCodigoPais(), new PerfilClienteDataSource.CallBackResultado<ComentarioClienteResponse>() {
            @Override
            public void onCallBackResultado(ComentarioClienteResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private ComentarioClienteResponse comentarioClienteResponse;

        public ResponseValue(ComentarioClienteResponse comentarioClienteResponse) {
            this.comentarioClienteResponse = comentarioClienteResponse;
        }

        public ComentarioClienteResponse getComentarioClienteResponse() {
            return comentarioClienteResponse;
        }

        public void setComentarioClienteResponse(ComentarioClienteResponse comentarioClienteResponse) {
            this.comentarioClienteResponse = comentarioClienteResponse;
        }


    }

    public static final class RequestValues implements UseCase.RequestValues {

        private String usuCodigoPropuesta;
        private String codigoPais;

        public RequestValues(String usuCodigoPropuesta, String codigoPais) {
            this.usuCodigoPropuesta = usuCodigoPropuesta;
            this.codigoPais = codigoPais;
        }

        public String getUsuCodigoPropuesta() {
            return usuCodigoPropuesta;
        }

        public void setUsuCodigoPropuesta(String usuCodigoPropuesta) {
            this.usuCodigoPropuesta = usuCodigoPropuesta;
        }

        public String getCodigoPais() {
            return codigoPais;
        }

        public void setCodigoPais(String codigoPais) {
            this.codigoPais = codigoPais;
        }
    }
}
