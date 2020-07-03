package com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource;

import com.application.boxmadikv1.api.response.especialista.ListaPropuestaTotalResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import java.util.ArrayList;
import java.util.List;

public interface BuscarPropuestaDataSource {

    interface CallbackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onMostrarListaRubros(ArrayList<String> stringListIdRubros,String userKey, CallbackResultado<List<MisRubrosUi>> listCallbackResultado);

    void onMostrarListaTotalRubros(int primerRubroId, int segundoRubroId,
                                   int tercerRubrodId, int estado,String userKey,
                                   String depaCodigo,int pageCount,String codigoPais,
                                   CallbackResultado<ListaPropuestaTotalResponse> listaPropuestaTotalResponseCallbackResultado);

}
