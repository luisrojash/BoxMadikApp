package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase;

import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemRepository;

public class MostrarListaPropuestaEspecialidad extends UseCase<MostrarListaPropuestaEspecialidad.RequestValues,MostrarListaPropuestaEspecialidad.ResponseValue> {

    private ItemRepository itemRepository;

    public MostrarListaPropuestaEspecialidad(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        itemRepository.onMostrarListaPropuestaEspecialidad(requestValues.getPropuestaCodigo(), requestValues.getRubroCodigo(), requestValues.getOficioCodigo(), new ItemDataSource.CallbackResultado<ListaPropuestaEspecialidadResponse>() {
            @Override
            public void onCallBackResultado(ListaPropuestaEspecialidadResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private int propuestaCodigo;
        private int rubroCodigo;
        private int oficioCodigo;

        public RequestValues(int propuestaCodigo, int rubroCodigo, int oficioCodigo) {
            this.propuestaCodigo = propuestaCodigo;
            this.rubroCodigo = rubroCodigo;
            this.oficioCodigo = oficioCodigo;
        }

        public int getPropuestaCodigo() {
            return propuestaCodigo;
        }

        public int getRubroCodigo() {
            return rubroCodigo;
        }

        public int getOficioCodigo() {
            return oficioCodigo;
        }


    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private ListaPropuestaEspecialidadResponse listaPropuestaEspecialidadResponse;

        public ResponseValue(ListaPropuestaEspecialidadResponse listaPropuestaEspecialidadResponse) {
            this.listaPropuestaEspecialidadResponse = listaPropuestaEspecialidadResponse;
        }

        public ListaPropuestaEspecialidadResponse getListaPropuestaEspecialidadResponse() {
            return listaPropuestaEspecialidadResponse;
        }
    }
}
