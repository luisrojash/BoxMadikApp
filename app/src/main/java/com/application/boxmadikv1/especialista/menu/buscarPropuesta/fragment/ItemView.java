package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment;


import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;

import java.util.List;

public interface ItemView extends BaseActivityView<ItemPresenter> {

    void mostrarListaPropuestaEspecialidad(List<PropuestaEspecialidad> especialidadList);

    void mostrarDataBasica(String titulo, String fecha,String nombreDepartamento,String nombreDistrito);

    void mostrarRangoDiasTexto(String nombreRangoDias);

    void mostrarRangoPrecioTexto(String nombreRangoPrecio);

    void mostrarRangoturnoTexto(String nombreRangoTurno);

    void mostrarImagenRubro(String descripcion);

    void mostrarTextoVacio(String no_tiene_especialidades);

    // void startActivityPerfilPropuesta(String imagenRubro,String titulo, String fecha, int codigoPropuesta, String codigoRubro, String codigoOficio, String codigoRangoDiasId, String codigoRangoTurnoId, String codigoRangoPrecioId);

    void startActivityPerfilPropuesta(ItemUi itemUi);
}
