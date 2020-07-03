package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades;

import android.content.Intent;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;

public interface EspecialidadPresenter extends BaseActivityPresenter<EspecialidadView> {

    void onClickAutoComplete(EspecialidadUi especialidadUi);

    void onClickAgregarItem(String tipoEspecialidad);

    void onValidarItemsAgregar(int totalItem, EspecialidadUi especialidadUi);

    void onClickSiguiente();

    void onClickEspecialidad();

    void onItemClickEspecialidadDelete(EspecialidadUi especialidadUi);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
