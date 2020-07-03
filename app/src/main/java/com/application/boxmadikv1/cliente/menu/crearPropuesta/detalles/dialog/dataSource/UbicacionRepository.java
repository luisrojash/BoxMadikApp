package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.local.UbicacionLocal;

import java.util.List;

public class UbicacionRepository implements UbicacionDataSource {

    private static UbicacionRepository mIntance = null;
    private UbicacionLocal ubicacionLocal;

    public UbicacionRepository(UbicacionLocal ubicacionLocal) {
        this.ubicacionLocal = ubicacionLocal;
    }

    public static final UbicacionRepository getInstance(UbicacionLocal ubicacionLocal) {
        if (mIntance == null) {
            mIntance = new UbicacionRepository(ubicacionLocal);
        }
        return mIntance;
    }

    @Override
    public void onListarDepartamento(String codigoPais, CallBackResultado<List<TipoDepartamentoUi>> listCallBackResultado) {
        ubicacionLocal.onListarDepartamento(codigoPais, listCallBackResultado);
    }

    @Override
    public void onListarProvincia(String paisCodigo, String departamentoCodigo, CallBackResultado<List<TipoProvinciaUi>> listCallBackResultado) {
        ubicacionLocal.onListarProvincia(paisCodigo, departamentoCodigo, listCallBackResultado);
    }

    @Override
    public void onListarDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo, CallBackResultado<List<TipoDistritoUi>> listCallBackResultado) {
        ubicacionLocal.onListarDistrito(paisCodigo, departamentoCodigo, provinciaCodigo, listCallBackResultado);
    }
}
