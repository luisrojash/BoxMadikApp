package com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.api.response.especialista.ListaPropuestaTotalResponse;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDao;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDao;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDao;
import com.application.boxmadikv1.dao.rubro.RubroDao;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.BuscarPropuestaDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.modelo.Rubro_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class BuscarPropuestaLocal implements BuscarPropuestaDataSource {


    public static final String TAG = BuscarPropuestaLocal.class.getSimpleName();
    private RubroDao rubroDao;

    public BuscarPropuestaLocal(RubroDao rubroDao) {
        this.rubroDao = rubroDao;
    }


    @Override
    public void onMostrarListaRubros(ArrayList<String> stringListIdRubros,String userKey, CallbackResultado<List<MisRubrosUi>> listCallbackResultado) {
        List<MisRubrosUi> misRubrosUis = new ArrayList<>();
        if (stringListIdRubros == null) return;
        if (stringListIdRubros.size() == 1) {
            Log.d(TAG, "BuscarPropuestaLocal " + 1 + "");
            agregarItemsFaltantes(stringListIdRubros, listCallbackResultado, misRubrosUis);
            MisRubrosUi misRubrosUiNum1 = new MisRubrosUi();
            misRubrosUiNum1.setTipoRubro(MisRubrosUi.DEFAULT_MIS_RUBROS);
            misRubrosUiNum1.setDescripcion("-");
            misRubrosUiNum1.setImagenRubros("");
            misRubrosUis.add(misRubrosUiNum1);
            MisRubrosUi misRubrosUiNum2 = new MisRubrosUi();
            misRubrosUiNum2.setTipoRubro(MisRubrosUi.DEFAULT_MIS_RUBROS);
            misRubrosUiNum2.setDescripcion("-");
            misRubrosUiNum2.setImagenRubros("");
            misRubrosUis.add(misRubrosUiNum2);
        } else if (stringListIdRubros.size() == 2) {
            Log.d(TAG, "BuscarPropuestaLocal " + 2 + "");
            agregarItemsFaltantes(stringListIdRubros, listCallbackResultado, misRubrosUis);
            MisRubrosUi misRubrosUiNum1 = new MisRubrosUi();
            misRubrosUiNum1.setTipoRubro(MisRubrosUi.DEFAULT_MIS_RUBROS);
            misRubrosUiNum1.setDescripcion("-");
            misRubrosUiNum1.setImagenRubros("");
            misRubrosUis.add(misRubrosUiNum1);
        } else if (stringListIdRubros.size() == 3) {
            Log.d(TAG, "BuscarPropuestaLocal " + 3 + "");
            agregarItemsFaltantes(stringListIdRubros, listCallbackResultado, misRubrosUis);
        }

    }

    @Override
    public void onMostrarListaTotalRubros(int primerRubroId, int segundoRubroId,
                                          int tercerRubrodId, int estado,String userKey,String depaCodigo,
                                          int pageCount,String codigoPais,CallbackResultado<ListaPropuestaTotalResponse> listaPropuestaTotalResponseCallbackResultado) {

    }


    private void agregarItemsFaltantes(ArrayList<String> stringListIdRubros, CallbackResultado<List<MisRubrosUi>> listCallbackResultado, List<MisRubrosUi> misRubrosUis) {
        for (String string : stringListIdRubros) {
            // Log.d(TAG, "String lis for : " + string);
            int rubroid = Integer.parseInt(string);
            MisRubrosUi misRubrosUi = new MisRubrosUi();
            Rubro rubro = rubroDao.obtenerMiIdQuerySimple(rubroid);
            misRubrosUi.setTipoRubro(MisRubrosUi.MIS_RUBROS_NORMAL);
            misRubrosUi.setIdMisRubros(rubro.getRub_Codigo() + "");
            misRubrosUi.setDescripcion(rubro.getRub_Abre());
            misRubrosUi.setImagenRubros(rubro.getRub_Imagen());
            misRubrosUi.setEstadoCheck(true);
            misRubrosUis.add(misRubrosUi);
        }
        listCallbackResultado.onCallBackResultado(misRubrosUis);
    }
}


