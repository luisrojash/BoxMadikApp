package com.application.boxmadikv1.especialista.menu.seleccionRubros.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource.SeleccionRubrosDataSource;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource.SeleccionRubrosRepository;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;

import java.util.List;

public class ListarSeleccionRubros extends UseCase<ListarSeleccionRubros.RequestValues, ListarSeleccionRubros.ResponseValue> {

    private SeleccionRubrosRepository seleccionRubrosRepository;

    public ListarSeleccionRubros(SeleccionRubrosRepository seleccionRubrosRepository) {
        this.seleccionRubrosRepository = seleccionRubrosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        seleccionRubrosRepository.onMostrarListaSeleccionRubros(requestValues.getCodigoPais(),new SeleccionRubrosDataSource.CallbackResultado<List<SeleccionRubrosUi>>() {
            @Override
            public void onCallBackResultado(List<SeleccionRubrosUi> resultado) {
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
        List<SeleccionRubrosUi> seleccionRubrosUiList;

        public ResponseValue(List<SeleccionRubrosUi> seleccionRubrosUiList) {
            this.seleccionRubrosUiList = seleccionRubrosUiList;
        }

        public List<SeleccionRubrosUi> getSeleccionRubrosUiList() {
            return seleccionRubrosUiList;
        }
    }
}
