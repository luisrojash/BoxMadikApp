package com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource;

import com.application.boxmadikv1.api.response.especialista.ListaPropuestaTotalResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.local.BuscarPropuestaLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.remote.BuscarPropuestaRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;

import java.util.ArrayList;
import java.util.List;

public class BuscarPropuestaRepository implements BuscarPropuestaDataSource {

    private BuscarPropuestaLocal buscarPropuestaLocal;
    private BuscarPropuestaRemote buscarPropuestaRemote;
    private static BuscarPropuestaRepository mInstance = null;

    public static final BuscarPropuestaRepository getmInstance(BuscarPropuestaLocal buscarPropuestaLocal, BuscarPropuestaRemote buscarPropuestaRemote) {
        if (mInstance == null) {
            mInstance = new BuscarPropuestaRepository(buscarPropuestaLocal, buscarPropuestaRemote);
        }
        return mInstance;
    }

    public BuscarPropuestaRepository(BuscarPropuestaLocal buscarPropuestaLocal, BuscarPropuestaRemote buscarPropuestaRemote) {
        this.buscarPropuestaLocal = buscarPropuestaLocal;
        this.buscarPropuestaRemote = buscarPropuestaRemote;
    }

//    @Override
//    public void onMostrarListaRubros(ArrayList<String> stringListIdRubros, CallbackResultado<List<MisRubrosUi>> listCallbackResultado) {
//        buscarPropuestaLocal.onMostrarListaRubros(stringListIdRubros, listCallbackResultado);
//    }

//    @Override
//    public void onMostrarListaTotalRubros(int primerRubroId, int segundoRubroId, int tercerRubrodId, int estado, CallbackResultado<ListaPropuestaTotalResponse> listaPropuestaTotalResponseCallbackResultado) {
//        buscarPropuestaRemote.onMostrarListaTotalRubros(primerRubroId, segundoRubroId, tercerRubrodId, estado, listaPropuestaTotalResponseCallbackResultado);
//    }

    @Override
    public void onMostrarListaRubros(ArrayList<String> stringListIdRubros, String userKey, CallbackResultado<List<MisRubrosUi>> listCallbackResultado) {
        buscarPropuestaLocal.onMostrarListaRubros(stringListIdRubros, userKey,listCallbackResultado);

    }

    @Override
    public void onMostrarListaTotalRubros(int primerRubroId, int segundoRubroId, int tercerRubrodId,
                                          int estado, String userKey, String depaCodigo,
                                          int pageCount,String codigoPais,CallbackResultado<ListaPropuestaTotalResponse> listaPropuestaTotalResponseCallbackResultado) {
        buscarPropuestaRemote.onMostrarListaTotalRubros(primerRubroId, segundoRubroId, tercerRubrodId,
                estado,userKey,depaCodigo,pageCount,codigoPais, listaPropuestaTotalResponseCallbackResultado);

    }
}
