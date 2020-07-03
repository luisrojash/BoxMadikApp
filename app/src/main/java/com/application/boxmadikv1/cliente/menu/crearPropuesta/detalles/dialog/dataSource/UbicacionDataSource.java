package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;

import java.util.List;

public interface UbicacionDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onListarDepartamento(String codigoPais, UbicacionDataSource.CallBackResultado<List<TipoDepartamentoUi>> listCallBackResultado);

    void onListarProvincia(String paisCodigo, String departamentoCodigo, UbicacionDataSource.CallBackResultado<List<TipoProvinciaUi>> listCallBackResultado);

    void onListarDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo, UbicacionDataSource.CallBackResultado<List<TipoDistritoUi>> listCallBackResultado);
}
