package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.useCase;

import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.PerfilClienteDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.PerfilClienteRepository;

public class ObtenerPerfil extends UseCase<ObtenerPerfil.RequestValues, ObtenerPerfil.ResponseValue> {

    private PerfilClienteRepository perfilClienteRepository;

    public ObtenerPerfil(PerfilClienteRepository perfilClienteRepository) {
        this.perfilClienteRepository = perfilClienteRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        perfilClienteRepository.onObtenerPerfil(requestValues.getUsuario_codigo_propuesta(), requestValues.getEstado_propuesta_proceso(), requestValues.getEstado_propuesta_finalizada(), new PerfilClienteDataSource.CallBackResultado<DatosPerfilResponse>() {
            @Override
            public void onCallBackResultado(DatosPerfilResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String usuario_codigo_propuesta;
        private String estado_propuesta_proceso;
        private String estado_propuesta_finalizada;

        public RequestValues(String usuario_codigo_propuesta, String estado_propuesta_proceso, String estado_propuesta_finalizada) {
            this.usuario_codigo_propuesta = usuario_codigo_propuesta;
            this.estado_propuesta_proceso = estado_propuesta_proceso;
            this.estado_propuesta_finalizada = estado_propuesta_finalizada;
        }

        public String getUsuario_codigo_propuesta() {
            return usuario_codigo_propuesta;
        }

        public String getEstado_propuesta_proceso() {
            return estado_propuesta_proceso;
        }

        public String getEstado_propuesta_finalizada() {
            return estado_propuesta_finalizada;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private DatosPerfilResponse datosPerfilResponse;

        public ResponseValue(DatosPerfilResponse datosPerfilResponse) {
            this.datosPerfilResponse = datosPerfilResponse;
        }

        public DatosPerfilResponse getDatosPerfilResponse() {
            return datosPerfilResponse;
        }
    }
}
