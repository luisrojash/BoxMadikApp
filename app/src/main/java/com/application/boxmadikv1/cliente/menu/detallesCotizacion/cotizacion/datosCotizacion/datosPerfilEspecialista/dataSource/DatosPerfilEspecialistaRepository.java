package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource;

import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource.remote.DatosPerfilEspecialistaRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;

import java.util.List;

public class DatosPerfilEspecialistaRepository implements DatosPerfilEspecialistaDataSource {

    private static DatosPerfilEspecialistaRepository mInstance = null;
    private DatosPerfilEspecialistaRemote datosPerfilEspecialistaRemote;

    public static final DatosPerfilEspecialistaRepository getmInstance(DatosPerfilEspecialistaRemote datosPerfilEspecialistaRemote) {
        if (mInstance == null) {
            mInstance = new DatosPerfilEspecialistaRepository(datosPerfilEspecialistaRemote);
        }
        return mInstance;
    }

    public DatosPerfilEspecialistaRepository(DatosPerfilEspecialistaRemote datosPerfilEspecialistaRemote) {
        this.datosPerfilEspecialistaRemote = datosPerfilEspecialistaRemote;
    }

    @Override
    public void onMostrarListaComentarios(String keyUser, String codigoPais, CallBackResultado<List<DatosPropuestaUi>> listCallBackResultado) {
        datosPerfilEspecialistaRemote.onMostrarListaComentarios(keyUser, codigoPais, listCallBackResultado);
    }
}
