package com.application.boxmadikv1.especialista.menu.abstracto;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.boxmadikv1.base.fragment.BaseFragmentPresenter;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;

public interface EspecAbstractPresenter extends BaseFragmentPresenter<EspecAbstractView> {
    void onClickEliminarItem(EspecialistaEstadosUi especialistaEstadosUi);

    void onClickItem(EspecialistaEstadosUi especialistaEstadosUi);

    void onAceptarDeleteItem(EspecialistaEstadosUi especialistaEstadosUi);

    void onValidateInternet(Boolean internet, EspecialistaEstadosUi especialistaEstadosUi);

    void onScrollStateChanged(RecyclerView recyclerView, int newState);

    void onScrolled(LinearLayoutManager linearLayout, int dx, int dy);

    void onRealTipoEstado(String usuarioCodigo, String codigoPais, String tipoEstado);
}
