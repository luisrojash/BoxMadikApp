package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.MostrarListaPropuestaEspecialidad;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerOficio;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerRangoDias;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerRangoPrecio;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerRangoTurno;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerRubro;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;

import java.util.ArrayList;
import java.util.List;

public class ItemPresenterImpl extends BaseFragmentPresenterImpl<ItemView> implements ItemPresenter {


    public static final String TAG = ItemPresenterImpl.class.getSimpleName();
    private ObtenerRubro obtenerRubro;
    private ObtenerOficio obtenerOficio;
    private ObtenerRangoDias obtenerRangoDias;
    private ObtenerRangoPrecio obtenerRangoPrecio;
    private ObtenerRangoTurno obtenerRangoTurno;
    private MostrarListaPropuestaEspecialidad mostrarListaPropuestaEspecialidad;


    public ItemPresenterImpl(UseCaseHandler handler, Resources res, ObtenerRubro obtenerRubro, ObtenerOficio obtenerOficio, ObtenerRangoDias obtenerRangoDias, ObtenerRangoPrecio obtenerRangoPrecio, ObtenerRangoTurno obtenerRangoTurno, MostrarListaPropuestaEspecialidad mostrarListaPropuestaEspecialidad) {
        super(handler, res);
        this.obtenerRubro = obtenerRubro;
        this.obtenerOficio = obtenerOficio;
        this.obtenerRangoDias = obtenerRangoDias;
        this.obtenerRangoPrecio = obtenerRangoPrecio;
        this.obtenerRangoTurno = obtenerRangoTurno;
        this.mostrarListaPropuestaEspecialidad = mostrarListaPropuestaEspecialidad;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    String titulo, fecha, detallePropuesta;
    int codigoPropuesta;
    String codigoRubro, codigoOficio, codigoRangoDiasId,
            codigoRangoTurnoId, codigoRangoPrecioId, codigoUsuarioPropuesta,
            numeroCotizacion, promedioCotizacion, tipoEstadoPropuesta, nombreDepartamento,
            nombreProvincia,nombreDistrito;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.codigoPropuesta = extras.getInt(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_PROPUESTA, 0);
        this.titulo = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_TITULO_PROPUESTA);
        this.detallePropuesta = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_DETALLE_PROPUESTA);
        this.fecha = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_FECHA_PROPUESTA);
        this.codigoRubro = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RUBRO);
        this.codigoOficio = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_OFICIO);
        this.codigoRangoDiasId = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_DIAS);
        this.codigoRangoTurnoId = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_TURNO);
        this.codigoRangoPrecioId = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_PRECIO);
        this.codigoUsuarioPropuesta = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_USUARIO_PROPUESTA);
        this.numeroCotizacion = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NUMERO_COTIZACION);
        this.promedioCotizacion = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_PROMEDIO_COTIZACION);
        this.tipoEstadoPropuesta = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_TIPO_COTIZACION);
        this.nombreDepartamento = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DEPARTAMENTO);
        this.nombreDepartamento = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DEPARTAMENTO);
        this.nombreProvincia = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_PROVINCIA);
        this.nombreDistrito = extras.getString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DISTRITO);

        Log.d(TAG, "codigoPropuesta : " + codigoPropuesta
//                + " /titulo " + titulo
//                + " / fecha" + fecha
//                + " /codigoRubro " + codigoRubro
//                + " / codigoOficio" + codigoOficio
//                + " / codigoRangoDiasId" + codigoRangoDiasId
//                + " / codigoRangoTurnoId" + codigoRangoTurnoId
//                + " / codigoRangoPrecioId" + codigoRangoPrecioId
//                + " / codigoUsuarioPropuesta" + codigoUsuarioPropuesta
                + "  / numeroCotizacion " + numeroCotizacion);
        initUseCaseRangoDias(codigoRangoDiasId);
        initUseCaseRangoTurno(codigoRangoTurnoId);
        initUseCaseRangoPrecio(codigoRangoPrecioId);
        initUseCaseRubro(codigoRubro);
        initUseCaseOficio(codigoOficio);
        initUseCaseListaOficio(codigoPropuesta, codigoRubro, codigoOficio);

        if (view != null) view.mostrarDataBasica(titulo, fecha,nombreDepartamento,nombreDistrito);
    }

    String nombreOficio;

    private void initUseCaseOficio(String codigoOficio) {
        handler.execute(obtenerOficio, new ObtenerOficio.RequestValues(codigoOficio),
                new UseCase.UseCaseCallback<ObtenerOficio.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerOficio.ResponseValue response) {
                        if (response == null) return;
                        nombreOficio = response.getNombreOficio();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    String imageRubro, nombreRubro;

    private void initUseCaseRubro(String codigoRubro) {
        handler.execute(obtenerRubro, new ObtenerRubro.RequestValues(codigoRubro),
                new UseCase.UseCaseCallback<ObtenerRubro.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerRubro.ResponseValue response) {
                        if (response == null) return;
                        imageRubro = response.getImageRubro();
                        nombreRubro = response.getNombreRubro();
                        if (view != null) view.mostrarImagenRubro(response.getImageRubro());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    List<PropuestaEspecialidad> especialidadList;

    private void initUseCaseListaOficio(int codigoPropuesta, String codigoRubro, String codigoOficio) {
        int codigoRub = Integer.parseInt(codigoRubro);
        int codigoOfi = Integer.parseInt(codigoOficio);
        handler.execute(mostrarListaPropuestaEspecialidad, new MostrarListaPropuestaEspecialidad.RequestValues(codigoPropuesta, codigoRub, codigoOfi),
                new UseCase.UseCaseCallback<MostrarListaPropuestaEspecialidad.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarListaPropuestaEspecialidad.ResponseValue response) {
                        ListaPropuestaEspecialidadResponse resultado = response.getListaPropuestaEspecialidadResponse();
                        Log.d(TAG, "resultado : " + resultado);
                        if (resultado == null) return;
                        Log.d(TAG, "resultado == NULL");
                        especialidadList = response.getListaPropuestaEspecialidadResponse().getPropuestaEspecialidad();
                        if (especialidadList == null) {
                            Log.d(TAG, "WSP if");
                            if (view != null) view.mostrarTextoVacio("No tiene Especialidades");
                        } else {
                            Log.d(TAG, "WSP ELSE");
                            if (view != null)
                                view.mostrarListaPropuestaEspecialidad(especialidadList);
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    String nombreRangoPrecio;

    private void initUseCaseRangoPrecio(String codigoRangoPrecioId) {
        handler.execute(obtenerRangoPrecio, new ObtenerRangoTurno.RequestValues(codigoRangoPrecioId),
                new UseCase.UseCaseCallback<ObtenerRangoTurno.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerRangoTurno.ResponseValue response) {
                        if (response == null) return;
                        nombreRangoPrecio = response.getDescripcion();
                        if (view != null) view.mostrarRangoPrecioTexto(nombreRangoPrecio);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    String nombreRangoTurno;

    private void initUseCaseRangoTurno(String codigoRangoTurnoId) {
        handler.execute(obtenerRangoTurno, new ObtenerRangoTurno.RequestValues(codigoRangoTurnoId),
                new UseCase.UseCaseCallback<ObtenerRangoTurno.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerRangoTurno.ResponseValue response) {
                        if (response == null) return;
                        nombreRangoTurno = response.getDescripcion();
                        if (view != null) view.mostrarRangoturnoTexto(nombreRangoTurno);
                        Log.d(TAG, "initUseCase : " + nombreRangoTurno);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    String nombreRangoDias;

    private void initUseCaseRangoDias(String codigoRangoDiasId) {
        handler.execute(obtenerRangoDias, new ObtenerRangoTurno.RequestValues(codigoRangoDiasId),
                new UseCase.UseCaseCallback<ObtenerRangoTurno.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerRangoTurno.ResponseValue response) {
                        if (response == null) return;
                        nombreRangoDias = response.getDescripcion();
                        if (view != null) view.mostrarRangoDiasTexto(nombreRangoDias);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }


    @Override
    public void onClickPropuesta() {
        Log.d(TAG, "setExtras : " + numeroCotizacion);
        ItemUi itemUi = new ItemUi();
        itemUi.setCodigoPropuesta(String.valueOf(codigoPropuesta));
        itemUi.setNombrePropuesta(titulo);
        itemUi.setImagePropuesta(imageRubro);
        itemUi.setFechaPropuesta(fecha);
        itemUi.setDetallesPropuesta(detallePropuesta);
        itemUi.setCodigoRubro(codigoRubro);
        itemUi.setDescripcionRubro(nombreRubro);
        itemUi.setCodigoOficio(codigoOficio);
        itemUi.setDescripcionOficio(nombreOficio);
        itemUi.setCodigoRangoDias(codigoRangoDiasId);
        itemUi.setDescripcionRangoDias(nombreRangoDias);
        itemUi.setCodigoRangoTurno(codigoRangoTurnoId);
        itemUi.setDescripcionRangoTurno(nombreRangoTurno);
        itemUi.setCodigoRangoPrecio(codigoRangoPrecioId);
        itemUi.setCodigoUsuarioPropuesta(codigoUsuarioPropuesta);
        itemUi.setCotiEstado(tipoEstadoPropuesta);
        itemUi.setEspecialidadesUiList(llenarEspecialidad(especialidadList));
        itemUi.setNombreDistrito(nombreDistrito);
        itemUi.setPromedioCotizacion(promedioCotizacion);
        itemUi.setNumeroCotizacion(numeroCotizacion);
        itemUi.setNombreDepartamento(nombreDepartamento);
        itemUi.setNombreDistrito(nombreDistrito);
        //itemUi.setPaisCodigo();


        if (view != null)
            view.startActivityPerfilPropuesta(validarEspecialidad(especialidadList, itemUi));
    }

    private String recortarNombreRangoPrecio(String nombreRangoPrecio) {
        String[] parts = nombreRangoPrecio.split("-");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556
        Log.d(TAG, "parte 1 : " + part1 + " / parte 2 " + part2);
        return part2;
    }

    private ItemUi validarEspecialidad(List<PropuestaEspecialidad> especialidadList, ItemUi itemUi) {
        if (especialidadList == null) {
            Log.d(TAG, "No agregar especialidad");
        } else {
            llenarEspecialidad(especialidadList);
        }
        return itemUi;
    }

    private List<EspecialidadesUi> llenarEspecialidad(List<PropuestaEspecialidad> especialidadList) {
        List<EspecialidadesUi> especialidadesUiList = new ArrayList<>();
        if (especialidadList == null) {
            Log.d(TAG, "No agregar especialidad");
            return null;
        }
        for (PropuestaEspecialidad propuestaEspecialidad : especialidadList) {
            EspecialidadesUi especialidadesUi = new EspecialidadesUi();
            especialidadesUi.setCodigoEspecialidad(propuestaEspecialidad.getEspecialidades_Espe_codigo());
            especialidadesUi.setDescripcionEspecialidad(propuestaEspecialidad.getPE_descripcion());
            especialidadesUiList.add(especialidadesUi);
        }
        return especialidadesUiList;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }
}
