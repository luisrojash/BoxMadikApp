package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;

import java.util.List;

public interface EspecialistaPerfilDistritoDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onListarDepartamento(String codigoPais, EspecialistaPerfilDistritoDataSource.CallBackResultado<List<TipoDepartamentoUi>> listCallBackResultado);

    void onListarProvincia(String paisCodigo, String departamentoCodigo,
                           EspecialistaPerfilDistritoDataSource.CallBackResultado<List<TipoProvinciaUi>> listCallBackResultado);

    void onListarDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo
            , EspecialistaPerfilDistritoDataSource.CallBackResultado<List<TipoDistritoUi>> listCallBackResultado);


}
