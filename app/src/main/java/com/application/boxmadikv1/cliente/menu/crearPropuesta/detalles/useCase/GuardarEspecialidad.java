package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase;

import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesRepository;

import java.util.ArrayList;

public class GuardarEspecialidad extends UseCase<GuardarEspecialidad.RequestValues, GuardarEspecialidad.ResponseValue> {

    private DetallesRepository detallesRepository;

    public GuardarEspecialidad(DetallesRepository detallesRepository) {
        this.detallesRepository = detallesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        detallesRepository.onRegistrarEspecialidad(requestValues.getUserLastId(),
                requestValues.getRubroId(),
                requestValues.getOficioId(),
                requestValues.getCodigoPais(),
                requestValues.getListaIdEspecialistas(),
                new DetallesDataSource.CallbackResultado<DefaultResponseRegistro>() {
                    @Override
                    public void onCallbackResultado(DefaultResponseRegistro resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String userLastId;
        private int rubroId;
        private int oficioId;
        private String codigoPais;
        private ArrayList<String> listaIdEspecialistas;

        public RequestValues(String userLastId, int rubroId, int oficioId, String codigoPais, ArrayList<String> listaIdEspecialistas) {
            this.userLastId = userLastId;
            this.rubroId = rubroId;
            this.oficioId = oficioId;
            this.codigoPais = codigoPais;
            this.listaIdEspecialistas = listaIdEspecialistas;
        }

        public String getUserLastId() {
            return userLastId;
        }

        public int getRubroId() {
            return rubroId;
        }

        public int getOficioId() {
            return oficioId;
        }

        public String getCodigoPais() {
            return codigoPais;
        }

        public ArrayList<String> getListaIdEspecialistas() {
            return listaIdEspecialistas;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private DefaultResponseRegistro defaultResponseRegistro;

        public ResponseValue(DefaultResponseRegistro defaultResponseRegistro) {
            this.defaultResponseRegistro = defaultResponseRegistro;
        }

        public DefaultResponseRegistro getDefaultResponseRegistro() {
            return defaultResponseRegistro;
        }
    }
}
