package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource.EspecialidadDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;
import com.application.boxmadikv1.dao.especialidades.EspecialidadesDao;
import com.application.boxmadikv1.modelo.Especialidades;

import com.application.boxmadikv1.modelo.Oficio;

import com.application.boxmadikv1.modelo.Rubro;

import com.application.boxmadikv1.utils.Constantes;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadLocal implements EspecialidadDataSource {

    public static final String TAG = EspecialidadLocal.class.getSimpleName();

    private EspecialidadesDao especialidadesDao;

    public EspecialidadLocal(EspecialidadesDao especialidadesDao) {
        this.especialidadesDao = especialidadesDao;
    }

    @Override
    public void onListarAutoComplete(int idRubro, int idOficio,String codigoPais, CallbackResultado<List<EspecialidadUi>> listCallbackResultado) {

        /*List<Especialidades> especialidadesList = SQLite.select()
                .from(Especialidades.class)
                .innerJoin(Oficio.class)
                .on(Especialidades_Table.oficio_Ofi_codigo.withTable().eq(Oficio_Table.Ofi_codigo.withTable()))
                .innerJoin(Rubro.class)
                .on(Especialidades_Table.oficio_rubro_Rub_Codigo.withTable().eq(Rubro_Table.Rub_Codigo.withTable()))
                .where(Especialidades_Table.oficio_rubro_Rub_Codigo.withTable().is(idRubro))
                .and(Especialidades_Table.oficio_Ofi_codigo.withTable().is(idOficio))
                .queryList();*/
        String rubroId= String.valueOf(idRubro);
        String oficioId = String.valueOf(idOficio);
        List<Especialidades> especialidadesList = especialidadesDao.obtenerListaPorRubroIdAndOficioId(rubroId,oficioId,codigoPais, Constantes.ESTADO_ACTIVO);

        Log.d(TAG, "onListarAutoComplete : " + idRubro + " / " + idOficio);
        Log.d(TAG, "onListarAutoCompletea : " + especialidadesList.size());
        List<EspecialidadUi> especialidadUiListItem = new ArrayList<>();
        for (Especialidades especialidades : especialidadesList) {
            EspecialidadUi especialidadUi = new EspecialidadUi();
            especialidadUi.setIdEspecialidad(especialidades.getEspe_codigo() + "");
            especialidadUi.setDescripcion(especialidades.getEspe_desc());
            especialidadUiListItem.add(especialidadUi);
        }
        listCallbackResultado.onCallbackResultado(especialidadUiListItem);
    }
}
