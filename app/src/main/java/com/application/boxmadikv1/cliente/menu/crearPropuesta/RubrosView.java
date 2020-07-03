package com.application.boxmadikv1.cliente.menu.crearPropuesta;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.OficiosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;

import java.util.List;

public interface RubrosView extends BaseActivityView<RubrosPresenter> {

    void mostrarListaRubros(List<RubrosUi> rubrosUiList);

    void mostrarListaOficiosTrue(RubrosUi rubrosUi);

    void mostrarListaOficiosFalse(RubrosUi rubrosUi);

    void startActivityEspecialidadesNecesarias(int rubroId, int oficioId,String imagenRubro,String nombreOficio,String codigoPais);
}
