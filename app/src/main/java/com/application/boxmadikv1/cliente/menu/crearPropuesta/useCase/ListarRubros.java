package com.application.boxmadikv1.cliente.menu.crearPropuesta.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource.RubrosDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource.RubrosRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;

import java.util.List;

public class ListarRubros extends UseCase<ListarRubros.RequestValues, ListarRubros.ResponseValue> {

    RubrosRepository rubrosRepository;

    public ListarRubros(RubrosRepository rubrosRepository) {
        this.rubrosRepository = rubrosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        rubrosRepository.onListarRubros(requestValues.getCodigoPais(),new RubrosDataSource.CallBackResultado<List<RubrosUi>>() {
            @Override
            public void onCalbackResultado(List<RubrosUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigoPais;

        public RequestValues(String codigoPais) {
            this.codigoPais = codigoPais;
        }

        public String getCodigoPais() {
            return codigoPais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<RubrosUi> rubrosUiList;

        public ResponseValue(List<RubrosUi> rubrosUiList) {
            this.rubrosUiList = rubrosUiList;
        }

        public List<RubrosUi> getRubrosUiList() {
            return rubrosUiList;
        }
    }
}
