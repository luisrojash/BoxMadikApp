package com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource.local.RubrosLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource.remote.RubrosRemote;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;

import java.util.List;

public class RubrosRepository implements RubrosDataSource {

    private static RubrosRepository mInstance = null;
    RubrosLocal rubrosLocal;

    public static final RubrosRepository getmInstance(RubrosLocal rubrosLocal) {
        if (mInstance == null) {
            mInstance = new RubrosRepository(rubrosLocal);
        }
        return mInstance;
    }

    public RubrosRepository(RubrosLocal rubrosLocal) {
        this.rubrosLocal = rubrosLocal;
    }

    @Override
    public void onListarRubros(String codigoPais,CallBackResultado<List<RubrosUi>> listCallBackResultado) {
        rubrosLocal.onListarRubros(codigoPais,listCallBackResultado);
    }
}
