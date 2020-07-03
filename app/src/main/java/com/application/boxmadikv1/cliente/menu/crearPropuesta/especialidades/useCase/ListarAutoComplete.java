package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource.EspecialidadDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource.EspecialidadRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;

import java.util.List;

public class ListarAutoComplete extends UseCase<ListarAutoComplete.RequestValues, ListarAutoComplete.ResponseValue> {

    private EspecialidadRepository especialidadRepository;

    public ListarAutoComplete(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        especialidadRepository.onListarAutoComplete(requestValues.getIdRubro(), requestValues.getIdOficio(),requestValues.getCodigoPais(), new EspecialidadDataSource.CallbackResultado<List<EspecialidadUi>>() {
            @Override
            public void onCallbackResultado(List<EspecialidadUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        int idRubro;
        int idOficio;
        String codigoPais;

        public RequestValues(int idRubro, int idOficio, String codigoPais) {
            this.idRubro = idRubro;
            this.idOficio = idOficio;
            this.codigoPais = codigoPais;
        }

        public int getIdRubro() {
            return idRubro;
        }

        public int getIdOficio() {
            return idOficio;
        }

        public String getCodigoPais() {
            return codigoPais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<EspecialidadUi> especialidadUiList;

        public ResponseValue(List<EspecialidadUi> especialidadUiList) {
            this.especialidadUiList = especialidadUiList;
        }

        public List<EspecialidadUi> getEspecialidadUiList() {
            return especialidadUiList;
        }
    }
}
