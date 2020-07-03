package com.application.boxmadikv1.especialista.menu.buscarPropuesta;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.api.response.especialista.ListaPropuestaTotalResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.useCase.MostrarListaRubros;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.useCase.MostrarListaTotalRubros;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;
import com.application.boxmadikv1.modelo.PropuestaInicial;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarPropuestaPresenterImpl extends BaseActivityPresenterImpl<BuscarPropuestaView> implements BuscarPropuestaPresenter {

    public static final String TAG = BuscarPropuestaPresenterImpl.class.getSimpleName();
    private MostrarListaRubros mostrarListaRubros;
    private MostrarListaTotalRubros mostrarListaTotalRubros;
    int pageCount = 2;


    public BuscarPropuestaPresenterImpl(UseCaseHandler handler, Resources res, MostrarListaRubros mostrarListaRubros, MostrarListaTotalRubros mostrarListaTotalRubros) {
        super(handler, res);
        this.mostrarListaRubros = mostrarListaRubros;
        this.mostrarListaTotalRubros = mostrarListaTotalRubros;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {


    }

    ArrayList<String> listIdRubros;

    Bundle bundle;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.bundle = extras;
    }

    List<MisRubrosUi> misRubrosUiList;

    private void initUseCaseListaRubros(final ArrayList<String> listIdRubros, String userKey) {
        //if (view != null) view.mostrarProgressBar();
        handler.execute(mostrarListaRubros, new MostrarListaRubros.RequestValues(listIdRubros, userKey),
                new UseCase.UseCaseCallback<MostrarListaRubros.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarListaRubros.ResponseValue response) {
                        if (response.getMisRubrosUiList() == null) return;
                        //initUseCaseListaPropuestaRubros(listIdRubros);
                        misRubrosUiList = response.getMisRubrosUiList();
                        if (view != null) {
                            view.ocultarProgressBar();
                            view.mostrarListaMisRubros(response.getMisRubrosUiList());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }


    @Override
    public void onClickFiltro() {
        if (view != null) view.startSeleccionRubroItem();
    }

    boolean rotation = true;

    @Override
    public void onClickEspecialidades() {
        if (view != null) view.mostrarContenidoEspecialidades();
        if (rotation) {
            rotation = false;
            if (view != null) view.mostrarOnclickEspecialidadRotationTrue();
        } else {
            rotation = true;
            if (view != null) view.mostrarOnclickEspecialidadRotationFalse();
        }
        pintarMisEspecialidades(); /*Solamente Checks - Cuando Cliquea*/
        initAdapterFragment();
        this.pageCount = 2;
        this.isScrolling = true;
        initUseCaseListaMisRubrosTotal(listIdRubros, userKey);
    }

    private void initAdapterFragment() {
        if (view != null) {
            view.initClearFragments();
            view.initViewPagerAdapter();
        }
    }

    private void pintarMisEspecialidades() {
        if (misRubrosUiList == null) return;
        for (int i = 0; i < misRubrosUiList.size(); i++) {
            MisRubrosUi misRubrosUi = misRubrosUiList.get(i);
            if (misRubrosUi.getTipoRubro() == MisRubrosUi.MIS_RUBROS_NORMAL) {
                misRubrosUi.setEstadoCheck(true);
            }
            continue;

        }
        if (view != null) view.actualizarMisRubrosAdapter();
    }

    int rubroIdPrimero, rubroIdSegundo, rubroIdTercero, estado;

    private void initUseCaseListaMisRubrosTotal(ArrayList<String> listIdRubros, String userKey) {

        int countLista = listIdRubros.size();
        int idPrimero = 0, idSegundo = 0, idTercero = 0;
        int[] entityId = {idPrimero, idSegundo, idTercero};
        for (int i = 0; i < countLista; i++) {
            String entidad = listIdRubros.get(i);
            entityId[i] = Integer.parseInt(entidad);
        }
        rubroIdPrimero = entityId[0];
        rubroIdSegundo = entityId[1];
        rubroIdTercero = entityId[2];

        int pageCount = 1;
        if (rubroIdPrimero > 0 && rubroIdSegundo > 0 && rubroIdTercero > 0) {
            Log.d(TAG, "LOS 3 RUBROS");
            estado = 3;
            initUseCaseTotalRubros(rubroIdPrimero, rubroIdSegundo, rubroIdTercero, estado, pageCount);
            return;
        } else if (rubroIdPrimero > 0 && rubroIdSegundo > 0) {
            Log.d(TAG, "LOS 2 RUBROS");
            estado = 2;
            initUseCaseTotalRubros(rubroIdPrimero, rubroIdSegundo, rubroIdTercero, estado, pageCount);
            return;
        } else if (rubroIdPrimero > 0) {
            estado = 1;
            initUseCaseTotalRubros(rubroIdPrimero, rubroIdSegundo, rubroIdTercero, estado, pageCount);
            Log.d(TAG, "LOS 1 RUBROS");
            return;
        }
    }

    // List<ItemUi> itemUiList;

    private void initUseCaseTotalRubros(int rubroIdPrimero, int rubroIdSegundo, int rubroIdTercero, int estado, final int pageContador) {
        if (view != null) view.mostrarProgressBar();
        Log.d(TAG, "departamentoId : " + departamentoId);
        handler.execute(mostrarListaTotalRubros, new MostrarListaTotalRubros.RequestValues(rubroIdPrimero,
                        rubroIdSegundo, rubroIdTercero, estado, userKey, departamentoId, pageContador, codigPais),
                new UseCase.UseCaseCallback<MostrarListaTotalRubros.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarListaTotalRubros.ResponseValue response) {
                        ListaPropuestaTotalResponse resultado = response.getListaPropuestaTotalResponse();

                        if (resultado == null) {
                            if (view != null) {
                                view.ocultarProgressBar();
                                view.ocultarBotones();
                                //view.mostrarMensajeSnackBar("Contactese con el soporte del aplicativo");
                            }
                            return;
                        } else {
                            /*True- Porque hay Error*/
                            if (resultado.getError()) {
                                if (view != null) {
                                    view.ocultarProgressBar();
                                    Log.d(TAG, "resultado.getMensaje() : " + resultado.getMensaje());
                                    // view.mostrarMensajeSnackBar(resultado.getMensaje());
                                    // view.mostrarMensajeToast("No hay propuestas en este Rubro");
                                    view.mostrarMensajeEmptyTexto("No hay propuestas en este Rubro");
                                    view.ocultarBotones();
                                }
                                return;
                            } else {


                                List<ItemUi> itemUiList = new ArrayList<>();
                                //pageCount = pageContador + 1;

                                for (PropuestaInicial propuestaInicial :
                                        response.getListaPropuestaTotalResponse().getPropuestaTotal()) {
                                    final String codigoPropuesta = String.valueOf(propuestaInicial.getPri_Codigo());
                                    final String codigoRubro = propuestaInicial.getRubro_Rub_Codigo();
                                    final String codigoOficio = propuestaInicial.getOficio_Ofi_codigo();
                                    final ItemUi itemUi = new ItemUi();
                                    itemUi.setCodigoPropuesta(String.valueOf(propuestaInicial.getPri_Codigo()));
                                    itemUi.setNombrePropuesta(propuestaInicial.getPri_Titulo());
                                    itemUi.setImagePropuesta(propuestaInicial.getRubroImagen());
                                    itemUi.setFechaPropuesta(propuestaInicial.getPri_Fecha());
                                    itemUi.setDetallesPropuesta(propuestaInicial.getPri_Detalle());
                                    itemUi.setCodigoRubro(propuestaInicial.getRubro_Rub_Codigo());
                                    itemUi.setDescripcionRubro(propuestaInicial.getRubroDescripcion());
                                    itemUi.setCodigoOficio(propuestaInicial.getOficio_Ofi_codigo());
                                    itemUi.setDescripcionOficio(propuestaInicial.getOficioDescripcion());
                                    itemUi.setCodigoRangoDias(propuestaInicial.getRango_dias_Rad_Item());
                                    itemUi.setDescripcionRangoDias(propuestaInicial.getRangoDiasDescripcion());
                                    itemUi.setCodigoRangoTurno(propuestaInicial.getRango_turno_Rat_Item());
                                    itemUi.setDescripcionRangoTurno(propuestaInicial.getRangoTurnoDescripcion());
                                    itemUi.setCodigoRangoPrecio(propuestaInicial.getRango_precio_Ran_Item());
                                    itemUi.setCodigoUsuarioPropuesta(propuestaInicial.getUsuario_Usu_Codigo());
                                    itemUi.setCotiEstado(propuestaInicial.getCotiEstado());
                                    itemUi.setPropuestaEstado(propuestaInicial.getPri_Estado());
                                    itemUi.setPromedioCotizacion(propuestaInicial.getPromedio_coti());
                                    itemUi.setNumeroCotizacion(propuestaInicial.getNum_cotizacion());
                                    itemUi.setNombreDepartamento(propuestaInicial.getDepa_Desc());
                                    itemUi.setNombreDistrito(propuestaInicial.getDist_Desc());
                                    itemUi.setDescripcionRangoPrecio(propuestaInicial.getRangoPrecionDescripcion());
                                    Log.d(TAG, "propuestaInicial.getMontoOficio() " + propuestaInicial.getMontoOficio());
                                    itemUi.setMontoOfico(propuestaInicial.getMontoOficio());
                                    Log.d(TAG, "itemUi : " + itemUi.getMontoOfico());
                                    /*Desactivado Especilidad 19-02-2019*/
                                    /*itemUi.setEspecialidadesUiList(llenarEspecialidad(codigoPropuesta, codigoRubro,
                                            codigoOficio, itemUi, codigPais));*/
                                    itemUi.setPaisCodigo(codigPais);
                                    itemUiList.add(itemUi);

                                }
                                if (view != null) {
                                    view.ocultarProgressBar();
                                    view.mostrarListaPropuesta(itemUiList);
                                    view.mostrarBotones();
                                }
                                return;
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BuscarPropuestaActivity.RESULTADO_ACTIVIDAD_ACTIVIDAD_PROPUESTA) {

            if (resultCode == Activity.RESULT_OK) {
                String id = data.getStringExtra("id");
                String imageRubro = data.getStringExtra("imageRubro");
                String descripcion = data.getStringExtra("descripcion");
                if (view != null) view.mostrarContenidoFiltro(descripcion, imageRubro);
                this.pageCount = 2;
                this.isScrolling = true;
                initUseCaseListaPropuestaFiltro(id);
            } else {


                Log.d(TAG, "No Hubo Items elejidos : ");
            }
        } else {
            Log.d(TAG, "RESULTADO_ACTIVIDAD_ACTIVIDAD_PROPUESTA NO HUBO");
        }
    }

    @Override
    public void setTinyDB(TinyDB tinyDB) {
        this.listIdRubros = tinyDB.getListString("mylist");
        initAdapterFragment();
        initUseCaseListaRubros(listIdRubros, userKey);
        initUseCaseListaMisRubrosTotal(listIdRubros, userKey);
    }

    @Override
    public void onClickMisRubros(MisRubrosUi misRubrosUi) {
        Log.d(TAG, "onClickMisRubros : " + misRubrosUi.getDescripcion());
        this.pageCount = 2;
        this.isScrolling = true;
        pintandoFilas(misRubrosUi);
        initAdapterFragment();
        ArrayList<String> listIdRubros = new ArrayList<>();
        listIdRubros.add(misRubrosUi.getIdMisRubros());
        initUseCaseListaMisRubrosTotal(listIdRubros, userKey);
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent : ");
       /* String id = intent.getStringExtra("id");
        String imageRubro = intent.getStringExtra("imageRubro");
        String descripcion = intent.getStringExtra("descripcion");
        if (view != null) view.mostrarContenidoFiltro(descripcion, imageRubro);
        initUseCaseListaPropuestaFiltro(id);*/
    }

    String userKey, departamentoId, codigPais;

    @Override
    public void onKeyUser(String userKey, String departamentoId, String codigPais) {
        this.userKey = userKey;
        this.departamentoId = departamentoId;
        this.codigPais = codigPais;
    }

    //boolean isScrolling = false;
    boolean isScrolling = true;
    //int pageCount = 1;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        /*if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true;
            Log.d(TAG, "Aqui no Cargar::isScrolling" + isScrolling);
        }*/
    }

    int currentItems, totalItems, scrollOutItems;

    @Override
    public void onScrolled(LinearLayoutManager linearLayoutManager, int dx, int dy) {
        currentItems = linearLayoutManager.getChildCount();
        totalItems = linearLayoutManager.getItemCount();
        scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();
        Log.d(TAG, "totalItems : " + totalItems +
                "currentItems : " + totalItems +
                "scrollOutItems : " + totalItems);
        if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
            isScrolling = false;
            if (view != null) view.mostrarProgressBar();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    // initLoadMorepropuesta(pageCount);
                    Log.d(TAG, "isScrolling" + isScrolling);
                    initLoadMorepropuesta(rubroIdPrimero,
                            rubroIdSegundo,
                            rubroIdTercero,
                            estado,
                            pageCount);
                    Log.d(TAG, "CargarData : " + pageCount);

                    return;
                }
            }, 2000);
        }


    }

    @Override
    public void onClickPropuestaListener(ItemUi itemUi) {
        /*Click Propuesta Detalles*/

        if (view != null) view.initStartActivityDetallesPropuesta(itemUi);
    }

    @Override
    public void onClickEstadoInternet(Boolean internet, Object objeto) {
        if (internet) {
            ItemUi itemUi = (ItemUi) objeto;
            Log.d(TAG, "itemUi : " + itemUi.getMontoOfico());
            if (itemUi != null) if (view != null) view.initStartActivityDetallesPropuesta(itemUi);
        } else {
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Verifique su conexión");
        }
    }


    private void initLoadMorepropuesta(int rubroIdPrimero, int rubroIdSegundo, int rubroIdTercero, int estado, final int countPAge) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        service.loadMorePropuesta(rubroIdPrimero,
                rubroIdSegundo,
                rubroIdTercero,
                estado,
                userKey,
                departamentoId,
                countPAge).enqueue(new Callback<ListaPropuestaTotalResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaTotalResponse> call, Response<ListaPropuestaTotalResponse> response) {
                ListaPropuestaTotalResponse resultado = response.body();

                if (resultado == null) {
                    Log.d(TAG, "resultado == null : ");
                    if (view != null) view.ocultarProgressBar();
                    return;
                } else {
                    /*True- Porque hay Error*/
                    if (resultado.getError()) {
                        Log.d(TAG, "resultado.getMensaje() : " + resultado.getMensaje());
                        if (view != null) view.ocultarProgressBar();
                        return;
                    } else {


                        List<ItemUi> itemUiList = new ArrayList<>();
                        //countTotalLoad = pageCount + 1;
                        Log.d(TAG, "countPAge :" + countPAge);
                        pageCount = countPAge + 1;
                        isScrolling = true;
                        Log.d(TAG, "pageCount :" + pageCount);
                        for (PropuestaInicial propuestaInicial :
                                resultado.getPropuestaTotal()) {
                            final String codigoPropuesta = String.valueOf(propuestaInicial.getPri_Codigo());
                            final String codigoRubro = propuestaInicial.getRubro_Rub_Codigo();
                            final String codigoOficio = propuestaInicial.getOficio_Ofi_codigo();
                            //initUseCaseListaOficio(codigoPropuesta, codigoRubro, codigoOficio);
                            final ItemUi itemUi = new ItemUi();
                           /* itemUi.setCodigoPropuesta(String.valueOf(propuestaInicial.getPri_Codigo()));
                            itemUi.setNombrePropuesta(propuestaInicial.getPri_Titulo());
                            itemUi.setImagePropuesta(propuestaInicial.getRubroImagen());
                            itemUi.setFechaPropuesta(propuestaInicial.getPri_Fecha());
                            itemUi.setDetallesPropuesta(propuestaInicial.getPri_Detalle());
                            itemUi.setCodigoRubro(propuestaInicial.getRubro_Rub_Codigo());
                            itemUi.setDescripcionRubro(propuestaInicial.getRubroDescripcion());
                            itemUi.setCodigoOficio(propuestaInicial.getOficio_Ofi_codigo());
                            itemUi.setDescripcionOficio(propuestaInicial.getOficioDescripcion());
                            itemUi.setCodigoRangoDias(propuestaInicial.getRango_dias_Rad_Item());
                            itemUi.setDescripcionRangoDias(propuestaInicial.getRangoDiasDescripcion());
                            itemUi.setCodigoRangoTurno(propuestaInicial.getRango_turno_Rat_Item());
                            itemUi.setDescripcionRangoTurno(propuestaInicial.getRangoTurnoDescripcion());
                            itemUi.setCodigoRangoPrecio(propuestaInicial.getRango_precio_Ran_Item());
                            itemUi.setCodigoUsuarioPropuesta(propuestaInicial.getUsuario_Usu_Codigo());
                            //itemUi.setCotiEstado(propuestaInicial.getPri_Estado());
                            itemUi.setCotiEstado(propuestaInicial.getCotiEstado());
                            itemUi.setPropuestaEstado(propuestaInicial.getPri_Estado());
                            itemUi.setPromedioCotizacion(propuestaInicial.getPromedio_coti());
                            itemUi.setNumeroCotizacion(propuestaInicial.getNum_cotizacion());
                            itemUi.setNombreDepartamento(propuestaInicial.getDepa_Desc());
                            itemUi.setNombreDistrito(propuestaInicial.getDist_Desc());
                            itemUi.setDescripcionRangoPrecio(propuestaInicial.getRangoPrecionDescripcion());*/

                            itemUi.setCodigoPropuesta(String.valueOf(propuestaInicial.getPri_Codigo()));
                            itemUi.setNombrePropuesta(propuestaInicial.getPri_Titulo());
                            itemUi.setImagePropuesta(propuestaInicial.getRubroImagen());
                            itemUi.setFechaPropuesta(propuestaInicial.getPri_Fecha());
                            itemUi.setDetallesPropuesta(propuestaInicial.getPri_Detalle());
                            itemUi.setCodigoRubro(propuestaInicial.getRubro_Rub_Codigo());
                            itemUi.setDescripcionRubro(propuestaInicial.getRubroDescripcion());
                            itemUi.setCodigoOficio(propuestaInicial.getOficio_Ofi_codigo());
                            itemUi.setDescripcionOficio(propuestaInicial.getOficioDescripcion());
                            itemUi.setCodigoRangoDias(propuestaInicial.getRango_dias_Rad_Item());
                            itemUi.setDescripcionRangoDias(propuestaInicial.getRangoDiasDescripcion());
                            itemUi.setCodigoRangoTurno(propuestaInicial.getRango_turno_Rat_Item());
                            itemUi.setDescripcionRangoTurno(propuestaInicial.getRangoTurnoDescripcion());
                            itemUi.setCodigoRangoPrecio(propuestaInicial.getRango_precio_Ran_Item());
                            itemUi.setCodigoUsuarioPropuesta(propuestaInicial.getUsuario_Usu_Codigo());
                            itemUi.setCotiEstado(propuestaInicial.getCotiEstado());
                            itemUi.setPropuestaEstado(propuestaInicial.getPri_Estado());
                            itemUi.setPromedioCotizacion(propuestaInicial.getPromedio_coti());
                            itemUi.setNumeroCotizacion(propuestaInicial.getNum_cotizacion());
                            itemUi.setNombreDepartamento(propuestaInicial.getDepa_Desc());
                            itemUi.setNombreDistrito(propuestaInicial.getDist_Desc());
                            itemUi.setDescripcionRangoPrecio(propuestaInicial.getRangoPrecionDescripcion());
                            Log.d(TAG, "propuestaInicial.getMontoOficio() " + propuestaInicial.getMontoOficio());
                            itemUi.setMontoOfico(propuestaInicial.getMontoOficio());

                            itemUiList.add(itemUi);

                        }
                        if (view != null) {
                            view.agregarLoadMoreLista(itemUiList);
                            view.ocultarProgressBar();
                        }
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<ListaPropuestaTotalResponse> call, Throwable t) {
                //listCallBackResultado.onCallBackResultado(null);
                isScrolling = true;
                if (view != null) view.ocultarProgressBar();
                Log.d(TAG, "onFailure : ");
                //listaPropuestaTotalResponseCallbackResultado.onCallBackResultado(null);
            }
        });

    }

    private List<EspecialidadesUi> llenarEspecialidad(String codigoPropuesta, String codigoRubro,
                                                      String codigoOficio, final ItemUi itemUi, String codigPais) {
        final List<EspecialidadesUi> especialidadesUiList = new ArrayList<>();
        synchronized (this) {
            int propuestaCodigo = Integer.parseInt(codigoPropuesta);
            int rubroCodigo = Integer.parseInt(codigoRubro);
            int oficioCodigo = Integer.parseInt(codigoOficio);

            Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
            Call<ListaPropuestaEspecialidadResponse> call = loginService.obtenerListaPropuestaEspecialidad(propuestaCodigo,
                    rubroCodigo,
                    oficioCodigo,
                    Integer.parseInt(codigPais));
            call.enqueue(new Callback<ListaPropuestaEspecialidadResponse>() {
                @Override
                public void onResponse(Call<ListaPropuestaEspecialidadResponse> call, Response<ListaPropuestaEspecialidadResponse> response) {
                    ListaPropuestaEspecialidadResponse body = response.body();

                    if (body == null) return;
                    Log.d(TAG, "resultado == NULL");
                    List<PropuestaEspecialidad> especialidadList = body.getPropuestaEspecialidad();
                    if (especialidadList == null) {
                        if (view != null) view.actualizarEspecialistaAdapter(itemUi);
                        Log.d(TAG, "WSP if No tiene Especialidades");
                        // if (view != null) view.mostrarTextoVacio("No tiene Especialidades");
                    } else {
                        Log.d(TAG, "WSP ELSE");

                        for (PropuestaEspecialidad propuestaEspecialidad
                                : especialidadList) {
                            EspecialidadesUi especialidadesUi = new EspecialidadesUi();
                            especialidadesUi.setCodigoEspecialidad(String.valueOf(propuestaEspecialidad.getPE_Codigo()));
                            especialidadesUi.setDescripcionEspecialidad(propuestaEspecialidad.getPE_descripcion());
                            especialidadesUiList.add(especialidadesUi);
                        }
                        itemUi.setEspecialidadesUiList(especialidadesUiList);
                        if (view != null) view.actualizarEspecialistaAdapter(itemUi);
                    }
                }

                @Override
                public void onFailure(Call<ListaPropuestaEspecialidadResponse> call, Throwable t) {
                    if (view != null) view.actualizarEspecialistaAdapter(itemUi);
                }
            });

        }
        return especialidadesUiList;
    }

    private void pintandoFilas(MisRubrosUi rubroItemUi1) {
        int itemsLista = misRubrosUiList.size();
        int itemTrue = 0;
        for (int i = 0; i < itemsLista; i++) {
            MisRubrosUi rubroItemUi = misRubrosUiList.get(i);
            if (rubroItemUi.isEstadoCheck()) {
                itemTrue++;
            }
            continue;
        }
        if (itemsLista == itemTrue) {
            for (int i = 0; i < itemsLista; i++) {
                MisRubrosUi rubroItemUi = misRubrosUiList.get(i);
                rubroItemUi.setEstadoCheck(false);
            }
            rubroItemUi1.setEstadoCheck(true);
            if (view != null) view.actualizarMisRubrosAdapter();
        } else {
            for (int i = 0; i < itemsLista; i++) {
                MisRubrosUi rubroItemUi = misRubrosUiList.get(i);
                if (rubroItemUi.isEstadoCheck()) {
                    Log.d(TAG, "remplazarItem");
                    remplazarItem(rubroItemUi, rubroItemUi1);
                    return;
                }
            }
        }
    }

    private void remplazarItem(MisRubrosUi rubroItemUi1Anterior, MisRubrosUi rubroItemUi11Nueva) {
        rubroItemUi1Anterior.setEstadoCheck(false);
        rubroItemUi11Nueva.setEstadoCheck(true);
        if (view != null) view.actualizarMisRubrosAdapter();
    }

    private void initUseCaseListaPropuestaFiltro(String id) {
        initAdapterFragment();
        ArrayList<String> listIdRubros = new ArrayList<>();
        listIdRubros.add(id);
        initUseCaseListaMisRubrosTotal(listIdRubros, userKey);
    }

    private void initListaFragment(ListaPropuestaTotalResponse resultado) {
        int countLista = resultado.getPropuestaTotal().size();
        Log.d(TAG, "countLista : " + countLista);
        if (countLista == 0) {
            if (view != null) view.ocultarBotones();
        } else {
            if (view != null) view.mostrarBotones();
        }
        for (int i = 0; i < countLista; i++) {
            PropuestaInicial propuestaInicial = resultado.getPropuestaTotal().get(i);
            int codigoPropuesta = propuestaInicial.getPri_Codigo();
            String titulo = propuestaInicial.getPri_Titulo();
            String detallesPropuesta = propuestaInicial.getPri_Detalle();
            Log.d(TAG, "Fecha : " + propuestaInicial.getFecha() +
                    "propuestaInicial.getPri_Fecha() " + propuestaInicial.getPri_Fecha());
            // String fecha = Constantes.f_fecha_letras(propuestaInicial.getPri_Fecha());
            String fecha = propuestaInicial.getPri_Fecha();
            Log.d(TAG, "date : " + fecha);
            String codigoRubro = propuestaInicial.getRubro_Rub_Codigo();
            String codigoOficio = propuestaInicial.getOficio_Ofi_codigo();
            String codigoRangoDiasId = propuestaInicial.getRango_dias_Rad_Item();
            String codigoRangoTurnoId = propuestaInicial.getRango_turno_Rat_Item();
            String codigoRangoPrecioId = propuestaInicial.getRango_precio_Ran_Item();
            String codigoUsuarioPropuesta = propuestaInicial.getUsuario_Usu_Codigo();
            String tipoEstadoPropuesta = propuestaInicial.getPri_Estado();
            String numeroCotizacion = validacionNumeroCotizacion(propuestaInicial.getNum_cotizacion());
            String promedioCotizacion = validacionPromedioCotizacion(propuestaInicial.getPromedio_coti());
            String nombreDepartamento = propuestaInicial.getDepa_Desc();
            String nombreDistrito = propuestaInicial.getDist_Desc();
            String nombreProvincia = propuestaInicial.getProv_Desc();
            Log.d(TAG, "nombreDistrito : " + nombreDistrito);

            // initUseCaseRangoDias(titulo, fecha, codigoRubro, codigoOficio, codigoRangoDiasId, codigoRangoTurnoId, codigoRangoPrecioId);
            Log.d(TAG, "codigoPropuesta : " + codigoPropuesta
                    + " /titulo " + titulo
                    + " / fecha" + fecha
                    + " /codigoRubro " + codigoRubro
                    + " / codigoOficio" + codigoOficio
                    + " / codigoRangoDiasId" + codigoRangoDiasId
                    + " / codigoRangoTurnoId" + codigoRangoTurnoId
                    + " / codigoRangoPrecioId" + codigoRangoPrecioId
                    + " / codigoUsuarioPropuesta" + codigoUsuarioPropuesta
                    + " / numeroCotizacion " + numeroCotizacion);
            if (view != null) {
                view.mostrarListaBuscadorPropuestaFragment(codigoPropuesta, titulo, detallesPropuesta, fecha, codigoRubro
                        , codigoOficio, codigoRangoDiasId, codigoRangoTurnoId, codigoRangoPrecioId, codigoUsuarioPropuesta,
                        numeroCotizacion, promedioCotizacion, tipoEstadoPropuesta, nombreDepartamento,
                        nombreProvincia,
                        nombreDistrito

                );
            }
        }
        if (view != null) view.actualizarAdapterFragmentos();
    }

    private String validacionPromedioCotizacion(String promedio_coti) {
        String stringResponse = "";
        if (promedio_coti == null) {
            stringResponse = "0";
        } else {
            stringResponse = promedio_coti;
        }
        return stringResponse;
    }

    private String validacionNumeroCotizacion(String num_cotizacion) {
        String stringResponse = "";
        if (num_cotizacion == null) {
            stringResponse = "0";
        } else {
            stringResponse = num_cotizacion;
        }
        return stringResponse;
    }


}
