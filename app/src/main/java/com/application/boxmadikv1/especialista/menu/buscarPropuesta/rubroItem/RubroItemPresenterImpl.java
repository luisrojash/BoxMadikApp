package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem;

import android.content.res.Resources;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.useCase.MostrarListaRubroItem;

import java.util.List;

public class RubroItemPresenterImpl extends BaseActivityPresenterImpl<RubroItemView> implements RubroItemPresenter {

    public static final String TAG = RubroItemPresenterImpl.class.getSimpleName();

    public RubroItemPresenterImpl(UseCaseHandler handler, Resources res, MostrarListaRubroItem mostrarListaRubroItem) {
        super(handler, res);
        this.mostrarListaRubroItem = mostrarListaRubroItem;
    }

    private MostrarListaRubroItem mostrarListaRubroItem;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onStart() {
        super.onStart();
        initUsecaseListRubroItem();
    }

    List<RubroItemUi> rubroItemUiList;

    private void initUsecaseListRubroItem() {
        handler.execute(mostrarListaRubroItem, new MostrarListaRubroItem.RequestValues(),
                new UseCase.UseCaseCallback<MostrarListaRubroItem.Responsevalue>() {
                    @Override
                    public void onSuccess(MostrarListaRubroItem.Responsevalue response) {
                        if (response.getRubroItemList() == null) return;
                        rubroItemUiList = response.getRubroItemList();
                        if (view != null) view.mostrarListaRubrosItem(response.getRubroItemList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    boolean validarEstado = true;

    @Override
    public void onItemClickRubro(RubroItemUi rubroItemUi1) {

        String id = rubroItemUi1.getRubroItemId();
        String imageRubro = rubroItemUi1.getImagenRubro();
        String descripcion = rubroItemUi1.getDescripcion();

        if (view != null) view.startBuscarPropuesta(id, imageRubro, descripcion);
        //rubroItemUi1.setEstadoRubro(false);

        pintandoFilas(rubroItemUi1);

        /*if(rubroItemUi1.isEstadoRubro()){

        }*/

    }

    private void pintandoFilas(RubroItemUi rubroItemUi1) {
        for (int i = 0; i < rubroItemUiList.size(); i++) {
            RubroItemUi rubroItemUi = rubroItemUiList.get(i);
            if (!rubroItemUi.isEstadoRubro()) {
                Log.d(TAG, "remplazarItem");
                remplazarItem(rubroItemUi, rubroItemUi1);
                return;
            }
        }
        rubroItemUi1.setEstadoRubro(false);
    }

    private void remplazarItem(RubroItemUi rubroItemUi1Anterior, RubroItemUi rubroItemUi11Nueva) {
        rubroItemUi1Anterior.setEstadoRubro(true);
        rubroItemUi11Nueva.setEstadoRubro(false);
        if (view != null) view.mostrarListaRubrosItem(rubroItemUiList);
    }
}
