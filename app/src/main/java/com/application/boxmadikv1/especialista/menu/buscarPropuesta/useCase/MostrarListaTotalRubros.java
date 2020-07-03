package com.application.boxmadikv1.especialista.menu.buscarPropuesta.useCase;

import com.application.boxmadikv1.api.response.especialista.ListaPropuestaTotalResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.BuscarPropuestaDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.BuscarPropuestaRepository;

public class MostrarListaTotalRubros extends UseCase<MostrarListaTotalRubros.RequestValues, MostrarListaTotalRubros.ResponseValue> {

    private BuscarPropuestaRepository buscarPropuestaRepository;

    public MostrarListaTotalRubros(BuscarPropuestaRepository buscarPropuestaRepository) {
        this.buscarPropuestaRepository = buscarPropuestaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        buscarPropuestaRepository.onMostrarListaTotalRubros(requestValues.getPrimerRubroId(),
                requestValues.getSegundoRubroId(),
                requestValues.getTercerRubroId(),
                requestValues.getEstado(),
                requestValues.getUserKey(),
                requestValues.getDepaCodigo(), requestValues.getPageCount(), requestValues.getCodigoPais(),new BuscarPropuestaDataSource.CallbackResultado<ListaPropuestaTotalResponse>() {
                    @Override
                    public void onCallBackResultado(ListaPropuestaTotalResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int primerRubroId;
        private int segundoRubroId;
        private int tercerRubroId;
        private int estado;
        private String userKey;
        private String depaCodigo;
        private int pageCount;
        private String codigoPais;

        public RequestValues(int primerRubroId, int segundoRubroId,
                             int tercerRubroId, int estado,
                             String userKey, String depaCodigo,
                             int pageCount, String codigoPais) {
            this.primerRubroId = primerRubroId;
            this.segundoRubroId = segundoRubroId;
            this.tercerRubroId = tercerRubroId;
            this.estado = estado;
            this.userKey = userKey;
            this.depaCodigo = depaCodigo;
            this.pageCount = pageCount;
            this.codigoPais = codigoPais;
        }

        public int getPrimerRubroId() {
            return primerRubroId;
        }

        public int getSegundoRubroId() {
            return segundoRubroId;
        }

        public int getTercerRubroId() {
            return tercerRubroId;
        }

        public int getEstado() {
            return estado;
        }

        public String getUserKey() {
            return userKey;
        }

        public String getDepaCodigo() {
            return depaCodigo;
        }

        public int getPageCount() {
            return pageCount;
        }

        public String getCodigoPais() {
            return codigoPais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private ListaPropuestaTotalResponse listaPropuestaTotalResponse;

        public ResponseValue(ListaPropuestaTotalResponse listaPropuestaTotalResponse) {
            this.listaPropuestaTotalResponse = listaPropuestaTotalResponse;
        }

        public ListaPropuestaTotalResponse getListaPropuestaTotalResponse() {
            return listaPropuestaTotalResponse;
        }
    }

}
