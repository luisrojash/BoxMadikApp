package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemRepository;

public class ObtenerRangoPrecio extends UseCase<ObtenerRangoTurno.RequestValues,ObtenerRangoTurno.ResponseValue>{

    private ItemRepository itemRepository;

    public ObtenerRangoPrecio(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    protected void executeUseCase(ObtenerRangoTurno.RequestValues requestValues) {
        itemRepository.onObtenerStringRangoPrecio(requestValues.getRangoTurnoId(), new ItemDataSource.CallbackResultado<String>() {
            @Override
            public void onCallBackResultado(String resultado) {
                getUseCaseCallback().onSuccess(new ObtenerRangoTurno.ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String rangoPrecioId;

        public RequestValues(String rangoPrecioId) {
            this.rangoPrecioId = rangoPrecioId;
        }

        public String getRangoPrecioId() {
            return rangoPrecioId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private String descripcion;

        public ResponseValue(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
