package com.application.boxmadikv1.terminosCondiciones.dataSource;

import com.application.boxmadikv1.terminosCondiciones.dataSource.local.TerminosCondicionesLocal;

public class TerminosCondicionesRepository implements TerminosCondicionesDataSource {

    private TerminosCondicionesLocal terminosCondicionesLocal;
    private static TerminosCondicionesRepository mInstance = null;

    public static TerminosCondicionesRepository getmInstance(TerminosCondicionesLocal terminosCondicionesLocal) {
        if (mInstance == null) {
            mInstance = new TerminosCondicionesRepository(terminosCondicionesLocal);
        }
        return mInstance;
    }

    public TerminosCondicionesRepository(TerminosCondicionesLocal terminosCondicionesLocal) {
        this.terminosCondicionesLocal = terminosCondicionesLocal;
    }

    @Override
    public void onObtenerTerminosCondiciones(int tipoEstadoTerminos, CallBackResultado<String, String> stringStringCallBackResultado) {
        terminosCondicionesLocal.onObtenerTerminosCondiciones(tipoEstadoTerminos, stringStringCallBackResultado);
    }
}
