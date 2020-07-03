package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource.local.EspecialidadLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;

import java.util.List;

public class EspecialidadRepository implements EspecialidadDataSource {

    private static EspecialidadRepository mInstance = null;
    private EspecialidadLocal especialidadLocal;

    public static final EspecialidadRepository getmInstance(EspecialidadLocal especialidadLocal) {
        if (mInstance == null) {
            mInstance = new EspecialidadRepository(especialidadLocal);
        }
        return mInstance;
    }

    public EspecialidadRepository(EspecialidadLocal especialidadLocal) {
        this.especialidadLocal = especialidadLocal;
    }

    @Override
    public void onListarAutoComplete(int idRubro, int idOficio,String codigoPais, CallbackResultado<List<EspecialidadUi>> listCallbackResultado) {
        especialidadLocal.onListarAutoComplete(idRubro, idOficio,codigoPais, listCallbackResultado);
    }
}
