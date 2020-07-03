package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource.RubroItemDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource.RubroItemRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;

import java.util.List;

public class MostrarListaRubroItem extends UseCase<MostrarListaRubroItem.RequestValues, MostrarListaRubroItem.Responsevalue> {

    private RubroItemRepository rubroItemRepository;

    public MostrarListaRubroItem(RubroItemRepository rubroItemRepository) {
        this.rubroItemRepository = rubroItemRepository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        rubroItemRepository.onMostrarListaRubroItem(new RubroItemDataSource.CallBackResultado<List<RubroItemUi>>() {
            @Override
            public void onCallBackResultado(List<RubroItemUi> resultado) {
                getUseCaseCallback().onSuccess(new Responsevalue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class Responsevalue implements UseCase.ResponseValue {
        private List<RubroItemUi> rubroItemList;

        public Responsevalue(List<RubroItemUi> rubroItemList) {
            this.rubroItemList = rubroItemList;
        }

        public List<RubroItemUi> getRubroItemList() {
            return rubroItemList;
        }
    }
}
