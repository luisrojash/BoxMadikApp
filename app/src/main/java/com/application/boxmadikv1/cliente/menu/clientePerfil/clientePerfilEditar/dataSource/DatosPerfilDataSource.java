package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource;


import com.application.boxmadikv1.api.response.DefaultResponse;

import okhttp3.RequestBody;

public interface DatosPerfilDataSource {

    interface CallBackResultado<T> {
        void onResultado(T resultado);
    }

    void onObtenerNombrePais(String codigoPais, DatosPerfilDataSource.CallBackResultado<String> stringDatosPerfilDataSource);

    void onObtenerNombreTipoDoc(String codigotipoDoc, DatosPerfilDataSource.CallBackResultado<String> stringDatosPerfilDataSource);

  }
