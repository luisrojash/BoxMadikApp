package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.local.EspecialistaPerfilDistritoLocal;
import java.util.List;

public class EspecialistaPerfilDistritoRepository implements EspecialistaPerfilDistritoDataSource {

    private EspecialistaPerfilDistritoLocal especialistaPerfilDistritoLocal;
    private static EspecialistaPerfilDistritoRepository mInstance = null;

    public static final EspecialistaPerfilDistritoRepository getmInstance(EspecialistaPerfilDistritoLocal especialistaPerfilDistritoLocal) {
        if (mInstance == null) {
            mInstance = new EspecialistaPerfilDistritoRepository(especialistaPerfilDistritoLocal);
        }
        return mInstance;
    }


    public EspecialistaPerfilDistritoRepository(EspecialistaPerfilDistritoLocal especialistaPerfilDistritoLocal) {
        this.especialistaPerfilDistritoLocal = especialistaPerfilDistritoLocal;
    }

    @Override
    public void onListarDepartamento(String codigoPais, CallBackResultado<List<TipoDepartamentoUi>> listCallBackResultado) {
        especialistaPerfilDistritoLocal.onListarDepartamento(codigoPais, listCallBackResultado);
    }

    @Override
    public void onListarProvincia(String paisCodigo, String departamentoCodigo, CallBackResultado<List<TipoProvinciaUi>> listCallBackResultado) {
        especialistaPerfilDistritoLocal.onListarProvincia(paisCodigo, departamentoCodigo, listCallBackResultado);
    }

    @Override
    public void onListarDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo, CallBackResultado<List<TipoDistritoUi>> listCallBackResultado) {
        especialistaPerfilDistritoLocal.onListarDistrito(paisCodigo, departamentoCodigo, provinciaCodigo, listCallBackResultado);
    }
}
