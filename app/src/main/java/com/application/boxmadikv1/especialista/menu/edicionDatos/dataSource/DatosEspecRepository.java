package com.application.boxmadikv1.especialista.menu.edicionDatos.dataSource;

import com.application.boxmadikv1.especialista.menu.edicionDatos.dataSource.local.DatosEspecLocal;

public class DatosEspecRepository implements DatosEspecDataSource {

    private DatosEspecLocal datosEspecLocal;
    private static DatosEspecRepository mInstance = null;

    public static final DatosEspecRepository getInstance(DatosEspecLocal datosEspecLocal) {
        if (mInstance == null) {
            mInstance = new DatosEspecRepository(datosEspecLocal);
        }
        return mInstance;
    }

    public DatosEspecRepository(DatosEspecLocal datosEspecLocal) {
        this.datosEspecLocal = datosEspecLocal;
    }

    @Override
    public void onObtenerDireccion(String codigoPais, String codigoDepartamento, String codigoProvincia, String codigoDistrito, CallbackResultadoDireccion callbackResultadoDireccion) {
        datosEspecLocal.onObtenerDireccion(codigoPais, codigoDepartamento, codigoProvincia, codigoDistrito, callbackResultadoDireccion);
    }
}
