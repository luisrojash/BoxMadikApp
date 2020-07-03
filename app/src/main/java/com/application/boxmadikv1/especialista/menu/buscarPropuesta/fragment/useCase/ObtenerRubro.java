package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemRepository;

public class ObtenerRubro extends UseCase<ObtenerRubro.RequestValues, ObtenerRubro.ResponseValue> {

    private ItemRepository itemRepository;

    public ObtenerRubro(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        itemRepository.onObtenerRubro(requestValues.getCodigoRubro(), new ItemDataSource.TwoCallbackResultado<String,String>() {
            @Override
            public void onCallBackResultado(String resultadoA,String resultadoB) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultadoA,resultadoB));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigoRubro;

        public RequestValues(String codigoRubro) {
            this.codigoRubro = codigoRubro;
        }

        public String getCodigoRubro() {
            return codigoRubro;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String imageRubro;
        private String nombreRubro;

        public ResponseValue(String imageRubro, String nombreRubro) {
            this.imageRubro = imageRubro;
            this.nombreRubro = nombreRubro;
        }

        public String getImageRubro() {
            return imageRubro;
        }

        public String getNombreRubro() {
            return nombreRubro;
        }
    }
}
