package com.application.boxmadikv1.especialista.menu.buscarPropuesta;

import android.os.Bundle;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;

import java.util.List;

public interface BuscarPropuestaView extends BaseActivityView<BuscarPropuestaPresenter> {

    void mostrarListaMisRubros(List<MisRubrosUi> misRubrosUiList);

    void mostrarContenidoFiltro(String descripcion, String image);

    void mostrarContenidoEspecialidades();

    void mostrarOnclickEspecialidadRotationTrue();

    void mostrarOnclickEspecialidadRotationFalse();

    void startSeleccionRubroItem();


    void initViewPagerAdapter();


    void initClearFragments();

    void mostrarMensajeSnackBar(String s);

    void mostrarMensajeToast(String mensaje);

    /*Agregando Lista Fragment(Detalles)*/
    void mostrarListaBuscadorPropuestaFragment(int codigoPropuesta, String titulo,
                                               String detallePropuesta, String fecha, String codigoRubro,
                                               String codigoOficio, String codigoRangoDiasId, String codigoRangoTurnoId,
                                               String codigoRangoPrecioId, String codigoUsuarioPropuesta,
                                               String numeroCotizacion, String promedioCotizacion,String tipoEstadoPropuesta,
                                               String nombreDepartamento,String nombreProvincia,String nombreDistrito);

    void actualizarMisRubrosAdapter();

    void actualizarAdapterFragmentos();

    void mostrarMensajeEmptyTexto(String s);

    void ocultarBotones();

    void mostrarBotones();

    void mostrarListaPropuesta(List<ItemUi> itemUiList);

    void agregarLoadMoreLista(List<ItemUi> itemUiList);

    void initStartActivityDetallesPropuesta(ItemUi itemUi);

    void actualizarEspecialistaAdapter(ItemUi itemUi);

    void mostrarDialogMensaje(String s);
}
