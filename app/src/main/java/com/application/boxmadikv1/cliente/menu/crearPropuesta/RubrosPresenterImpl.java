package com.application.boxmadikv1.cliente.menu.crearPropuesta;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.OficiosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.useCase.ListarRubros;

public class RubrosPresenterImpl extends BaseActivityPresenterImpl<RubrosView> implements RubrosPresenter {

    public static final String TAG = RubrosPresenterImpl.class.getSimpleName();

    ListarRubros listarRubros;

    public RubrosPresenterImpl(UseCaseHandler handler, Resources res, ListarRubros listarRubros) {
        super(handler, res);
        this.listarRubros = listarRubros;
    }

    @Override
    public void onStart() {
        super.onStart();
        initUseCaseListarRubro();
    }

    private void initUseCaseListarRubro() {
        handler.execute(listarRubros, new ListarRubros.RequestValues(codigoPais),
                new UseCase.UseCaseCallback<ListarRubros.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarRubros.ResponseValue response) {
                        if (view != null) view.mostrarListaRubros(response.getRubrosUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    // boolean rotateImage = true;

    @Override
    public void onClickItemRubro(RubrosUi rubrosUi) {
        if (rubrosUi.isEstadoRotar()) {
            rubrosUi.setEstadoRotar(false);
            if (view != null) view.mostrarListaOficiosTrue(rubrosUi);
        } else {
            rubrosUi.setEstadoRotar(true);
            if (view != null) view.mostrarListaOficiosFalse(rubrosUi);
        }
    }

    @Override
    public void onClickItemOficio(OficiosUi oficiosUi) {
        int rubroId = Integer.parseInt(oficiosUi.getRubrosUi().getIdRub());
        int oficioId = Integer.parseInt(oficiosUi.getIdOficio());
        String imageRubro = oficiosUi.getRubrosUi().getImagenRub();
        String nombreOficio = oficiosUi.getDescripcion();
        Log.d(TAG, "rubroId : " + rubroId + " /* " + oficioId);
        if (view != null)
            view.startActivityEspecialidadesNecesarias(rubroId, oficioId, imageRubro, nombreOficio,codigoPais);
    }

    String codigoPais;

    @Override
    public void onKeyPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    String keyUser;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        keyUser = extras.getString("keyUser");
        Log.d(TAG, "keyUser : " + keyUser);
    }

}
