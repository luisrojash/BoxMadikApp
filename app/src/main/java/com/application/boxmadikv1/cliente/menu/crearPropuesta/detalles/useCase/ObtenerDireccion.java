package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesRepository;

public class ObtenerDireccion extends UseCase<ObtenerDireccion.RequestValues, ObtenerDireccion.ResponseValue> {

    private DetallesRepository repository;

    public ObtenerDireccion(DetallesRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onObtenerDireccion(requestValues.getCodigoPais(), requestValues.getCodigoDepartamento(), requestValues.getCodigoProvincia(), requestValues.getCodigoDistrito(),
                new DetallesDataSource.CallbackResultadoDireccion() {
                    @Override
                    public void onCallbackDireccion(String codigoDepartamento, String codigoProvincia, String codigoDistrito, String nombreDepartamento, String nombreProvincia, String nombreDistrito) {
                        if (codigoDepartamento != null) {
                            getUseCaseCallback().onSuccess(new ResponseValue(codigoDepartamento, codigoProvincia, codigoDistrito, nombreDepartamento, nombreProvincia, nombreDistrito));

                        } else {
                            getUseCaseCallback().onError();
                        }
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigoPais;
        private String codigoDepartamento;
        private String codigoProvincia;
        private String codigoDistrito;

        public RequestValues(String codigoPais, String codigoDepartamento, String codigoProvincia, String codigoDistrito) {
            this.codigoPais = codigoPais;
            this.codigoDepartamento = codigoDepartamento;
            this.codigoProvincia = codigoProvincia;
            this.codigoDistrito = codigoDistrito;
        }

        public String getCodigoPais() {
            return codigoPais;
        }

        public void setCodigoPais(String codigoPais) {
            this.codigoPais = codigoPais;
        }

        public String getCodigoDepartamento() {
            return codigoDepartamento;
        }

        public void setCodigoDepartamento(String codigoDepartamento) {
            this.codigoDepartamento = codigoDepartamento;
        }

        public String getCodigoProvincia() {
            return codigoProvincia;
        }

        public void setCodigoProvincia(String codigoProvincia) {
            this.codigoProvincia = codigoProvincia;
        }

        public String getCodigoDistrito() {
            return codigoDistrito;
        }

        public void setCodigoDistrito(String codigoDistrito) {
            this.codigoDistrito = codigoDistrito;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String codigoDepartamento;
        private String codigoProvincia;
        private String codigoDistrito;
        private String nombreDepartamento;
        private String nombreProvincia;
        private String nombreDistrito;

        public ResponseValue(String codigoDepartamento, String codigoProvincia, String codigoDistrito, String nombreDepartamento, String nombreProvincia, String nombreDistrito) {
            this.codigoDepartamento = codigoDepartamento;
            this.codigoProvincia = codigoProvincia;
            this.codigoDistrito = codigoDistrito;
            this.nombreDepartamento = nombreDepartamento;
            this.nombreProvincia = nombreProvincia;
            this.nombreDistrito = nombreDistrito;
        }

        public String getCodigoDepartamento() {
            return codigoDepartamento;
        }

        public void setCodigoDepartamento(String codigoDepartamento) {
            this.codigoDepartamento = codigoDepartamento;
        }

        public String getCodigoProvincia() {
            return codigoProvincia;
        }

        public void setCodigoProvincia(String codigoProvincia) {
            this.codigoProvincia = codigoProvincia;
        }

        public String getCodigoDistrito() {
            return codigoDistrito;
        }

        public void setCodigoDistrito(String codigoDistrito) {
            this.codigoDistrito = codigoDistrito;
        }

        public String getNombreDepartamento() {
            return nombreDepartamento;
        }

        public void setNombreDepartamento(String nombreDepartamento) {
            this.nombreDepartamento = nombreDepartamento;
        }

        public String getNombreProvincia() {
            return nombreProvincia;
        }

        public void setNombreProvincia(String nombreProvincia) {
            this.nombreProvincia = nombreProvincia;
        }

        public String getNombreDistrito() {
            return nombreDistrito;
        }

        public void setNombreDistrito(String nombreDistrito) {
            this.nombreDistrito = nombreDistrito;
        }
    }

}

