package com.application.boxmadikv1.especialista.menu.buscarPropuesta;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.sesion.TinyDB;

public interface BuscarPropuestaPresenter extends BaseActivityPresenter<BuscarPropuestaView> {
    void onClickFiltro();

    void onClickEspecialidades();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void setTinyDB(TinyDB tinyDB);

    void onClickMisRubros(MisRubrosUi misRubrosUi);

    void onNewIntent(Intent intent);

    void onKeyUser(String userKey,String departamentoId,String codigPais);

    void onScrollStateChanged(RecyclerView recyclerView, int newState);

    void onScrolled(LinearLayoutManager linearLayoutManager, int dx, int dy);

    void onClickPropuestaListener(ItemUi itemUi);

    void onClickEstadoInternet(Boolean internet,Object objeto);
}
