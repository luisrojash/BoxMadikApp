package com.application.boxmadikv1.bandeja;

import com.application.boxmadikv1.bandeja.entidad.BandejaUi;
import com.application.boxmadikv1.base.activity.BaseActivityView;

import java.util.List;

public interface BandejaView extends BaseActivityView<BandejaPresenter> {
    void mostrarListaGrupo(List<BandejaUi> bandejaUiList);

    void initStartActivityBandejaCliente(BandejaUi bandejaUi);

    void initStartActivityBandejaEspecialista(BandejaUi bandejaUi);

    void actualizarVistasBandeja(BandejaUi bandejaUi);

    void mostrarMensaje(String mensaje);

    void mostrarTextViewEmpty();

    void ocultartTextViewEmpty();
}
