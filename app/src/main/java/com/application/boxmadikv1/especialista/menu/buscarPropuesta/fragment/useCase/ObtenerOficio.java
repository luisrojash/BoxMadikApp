package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemRepository;

public class ObtenerOficio extends UseCase<ObtenerOficio.RequestValues, ObtenerOficio.ResponseValue> {

    private ItemRepository itemRepository;

    public ObtenerOficio(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        itemRepository.onObtenerOficio(requestValues.getCodigoOficio(), new ItemDataSource.CallbackResultado<String>() {
            @Override
            public void onCallBackResultado(String resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigoOficio;

        public RequestValues(String codigoOficio) {
            this.codigoOficio = codigoOficio;
        }

        public String getCodigoOficio() {
            return codigoOficio;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String nombreOficio;

        public ResponseValue(String nombreOficio) {
            this.nombreOficio = nombreOficio;
        }

        public String getNombreOficio() {
            return nombreOficio;
        }
    }
}
