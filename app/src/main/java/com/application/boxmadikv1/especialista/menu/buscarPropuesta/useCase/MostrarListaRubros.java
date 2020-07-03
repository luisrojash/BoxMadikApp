package com.application.boxmadikv1.especialista.menu.buscarPropuesta.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.BuscarPropuestaDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.BuscarPropuestaRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;

import java.util.ArrayList;
import java.util.List;

public class MostrarListaRubros extends UseCase<MostrarListaRubros.RequestValues, MostrarListaRubros.ResponseValue> {

    BuscarPropuestaRepository buscarPropuestaRepository;

    public MostrarListaRubros(BuscarPropuestaRepository buscarPropuestaRepository) {
        this.buscarPropuestaRepository = buscarPropuestaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        buscarPropuestaRepository.onMostrarListaRubros(requestValues.getListIdRubros(),requestValues.getUserKey(), new BuscarPropuestaDataSource.CallbackResultado<List<MisRubrosUi>>() {
            @Override
            public void onCallBackResultado(List<MisRubrosUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        ArrayList<String> listIdRubros;
        String userKey;

        public RequestValues(ArrayList<String> listIdRubros, String userKey) {
            this.listIdRubros = listIdRubros;
            this.userKey = userKey;
        }

        public ArrayList<String> getListIdRubros() {
            return listIdRubros;
        }

        public String getUserKey() {
            return userKey;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<MisRubrosUi> misRubrosUiList;

        public ResponseValue(List<MisRubrosUi> misRubrosUiList) {
            this.misRubrosUiList = misRubrosUiList;
        }

        public List<MisRubrosUi> getMisRubrosUiList() {
            return misRubrosUiList;
        }
    }
}
