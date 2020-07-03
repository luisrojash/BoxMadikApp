package com.application.boxmadikv1.especialista.menu.seleccionRubros;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;

import java.util.ArrayList;
import java.util.List;

public interface SeleccionRubrosView extends BaseActivityView<SeleccionRubrosPresenter> {

    void mostrarListaRubrosSel(List<SeleccionRubrosUi> seleccionRubrosUiList);

    void mostrarMensaje(String mensaje);

    void mostrarCheckTrue(SeleccionRubrosUi seleccionRubrosUi);

    void mostrarOcultarFalse(SeleccionRubrosUi seleccionRubrosUi);

    void startMenuEspecialista(ArrayList<String> stringList);

    void startMenuEspecialistaLlena(ArrayList<String> arrayList);

    void finishActivityRubros(ArrayList<String> arrayList);

    void habilitarButtonSiguiente();

    void deshabilitarButtonSiguiente();
}
