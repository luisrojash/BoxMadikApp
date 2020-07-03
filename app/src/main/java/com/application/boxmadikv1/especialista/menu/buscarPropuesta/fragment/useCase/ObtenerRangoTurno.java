package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemRepository;

public class ObtenerRangoTurno extends UseCase<ObtenerRangoTurno.RequestValues,ObtenerRangoTurno.ResponseValue> {

    private ItemRepository itemRepository;

    public ObtenerRangoTurno(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        itemRepository.onObtenerStringRangoTurno(requestValues.getRangoTurnoId(), new ItemDataSource.CallbackResultado<String>() {
            @Override
            public void onCallBackResultado(String resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String rangoTurnoId;

        public RequestValues(String rangoTurnoId) {
            this.rangoTurnoId = rangoTurnoId;
        }

        public String getRangoTurnoId() {
            return rangoTurnoId;
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
