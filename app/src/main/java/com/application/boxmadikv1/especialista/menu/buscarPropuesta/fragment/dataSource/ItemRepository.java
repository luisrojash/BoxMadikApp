package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource;

import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.local.ItemLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.remote.ItemRemote;

public class ItemRepository implements ItemDataSource {

    private static ItemRepository mInstance = null;
    private ItemLocal itemLocal;
    private ItemRemote itemRemote;

    public static final ItemRepository getmInstance(ItemLocal itemLocal, ItemRemote itemRemote) {
        if (mInstance == null) {
            mInstance = new ItemRepository(itemLocal, itemRemote);
        }
        return mInstance;
    }

    public ItemRepository(ItemLocal itemLocal, ItemRemote itemRemote) {
        this.itemLocal = itemLocal;
        this.itemRemote = itemRemote;
    }

    @Override
    public void onObtenerStringRangoPrecio(String rangoPrecioId, CallbackResultado<String> callbackResultado) {
        itemLocal.onObtenerStringRangoPrecio(rangoPrecioId, callbackResultado);
        itemRemote.onObtenerStringRangoPrecio(rangoPrecioId, callbackResultado);
    }

    @Override
    public void onObtenerStringRangoTurno(String rangoTurnoId, CallbackResultado<String> callbackResultado) {
        itemLocal.onObtenerStringRangoTurno(rangoTurnoId, callbackResultado);
        itemRemote.onObtenerStringRangoTurno(rangoTurnoId, callbackResultado);
    }

    @Override
    public void onObtenerStringRangoDias(String rangoDiasId, CallbackResultado<String> callbackResultado) {
        itemLocal.onObtenerStringRangoDias(rangoDiasId, callbackResultado);
        itemRemote.onObtenerStringRangoDias(rangoDiasId, callbackResultado);
    }

    @Override
    public void onObtenerRubro(String codigoRubro, TwoCallbackResultado<String,String> callbackResultado) {
        itemLocal.onObtenerRubro(codigoRubro, callbackResultado);
    }

    @Override
    public void onObtenerOficio(String codigoOficio, CallbackResultado<String> callbackResultado) {
        itemLocal.onObtenerOficio(codigoOficio, callbackResultado);
    }

    @Override
    public void onMostrarListaPropuestaEspecialidad(int propuestaCodigo, int rubroCodigo, int oficioCodigo, CallbackResultado<ListaPropuestaEspecialidadResponse> listaPropuestaEspecialidadResponseCallbackResultado) {
        itemRemote.onMostrarListaPropuestaEspecialidad(propuestaCodigo, rubroCodigo, oficioCodigo, listaPropuestaEspecialidadResponseCallbackResultado);
    }
}