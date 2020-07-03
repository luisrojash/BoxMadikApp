package com.application.boxmadikv1.especialista.menu.especialistaEnviados;

import com.application.boxmadikv1.base.fragment.BaseFragmentPresenter;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;

public interface EspecialistaEnviadosPresenter extends BaseFragmentPresenter<EspecialistaEnviadosView>{
    void onClickEliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi);

    void onAceptarDeleteItem(EspecialistaEnviadosUi especialistaEnviadosUi);

    void onClickItem(EspecialistaEnviadosUi especialistaEnviadosUi);
}
