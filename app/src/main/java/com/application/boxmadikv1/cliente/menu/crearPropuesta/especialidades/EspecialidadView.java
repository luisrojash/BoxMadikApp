package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;

import java.util.ArrayList;
import java.util.List;

public interface EspecialidadView extends BaseActivityView<EspecialidadPresenter> {

    void mostrarVistaCabecera(String imagenRubro, String nombreOficio);

    void mostrarListaAutoComplete(List<EspecialidadUi> especialidadUiList);

    void mostrarMensaje(String mensaje);

    void agregarItemEspecialidad(EspecialidadUi especialidadUi);

    void validarItemsAgregar(EspecialidadUi especialidadUi);

    void startActivityHorarioAtencion(ArrayList<String> listaIdEspecialidades, int idRubro, int idOficio,String imagenRubro,String nombreOficio,
                                      int posicionTipoPrecio,int posicionTipoTurno,int posicionTipoDias,String codigoPais);

    void mostrarOnclickLista(List<EspecialidadUi> listaEspecialidadCompleta);

    void ClearEditText();


    void eliminarEspecialidad(EspecialidadUi especialidadUi);

    void onOcultarTeclado();
}
