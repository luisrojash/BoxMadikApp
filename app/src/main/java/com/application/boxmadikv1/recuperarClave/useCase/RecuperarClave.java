package com.application.boxmadikv1.recuperarClave.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.recuperarClave.dataSource.RecuperarClaveDataSource;
import com.application.boxmadikv1.recuperarClave.dataSource.RecuperarClaveRepository;

public class RecuperarClave extends UseCase<RecuperarClave.RequestValues,RecuperarClave.ResponseValue>{

    private RecuperarClaveRepository recuperarClaveRepository;

    public RecuperarClave(RecuperarClaveRepository recuperarClaveRepository) {
        this.recuperarClaveRepository = recuperarClaveRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        recuperarClaveRepository.onRecuperarClave(requestValues.getEmail(), new RecuperarClaveDataSource.CallbackResultado<Boolean, String>() {
            @Override
            public void onResultado(Boolean resultado, String resultado2) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado,resultado2));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String email;

        public RequestValues(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private boolean estado;
        private String mensaje;

        public ResponseValue(boolean estado, String mensaje) {
            this.estado = estado;
            this.mensaje = mensaje;
        }

        public boolean isEstado() {
            return estado;
        }

        public String getMensaje() {
            return mensaje;
        }
    }


}
