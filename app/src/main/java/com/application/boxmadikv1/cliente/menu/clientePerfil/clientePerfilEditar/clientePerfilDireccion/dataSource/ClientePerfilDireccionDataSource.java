package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;

import java.util.List;

import okhttp3.RequestBody;

public interface ClientePerfilDireccionDataSource {

    /*respuesta*/
    interface CallBackResultado {
        void onResultado(List<TipoDepartamentoUi> tipoDepartamentoUis);

    }

    /*respuesta*/
    interface CallBackResultadoProvincia {
        void onResultado(List<TipoProvinciaUi> tipoProvinciaUis);
    }

    /*respuesta*/
    interface CallBackResultadoDistrito {
        void onResultado(List<TipoDistritoUi> tipoDistritoUis);
    }


    /*peticion*/
    void onListarDepartamento(ClientePerfilDireccionDataSource.CallBackResultado
                                      callBackResultado);


    /*peticion*/
    void onListarProvincia(String paisCodigo, String departamentoCodigo,
                           ClientePerfilDireccionDataSource.CallBackResultadoProvincia callBackResultadoProvincia);


    /*peticion*/
    void onListarDistrito(String paisCodigo, String departamentoCodigo,String provinciaCodigo,
            ClientePerfilDireccionDataSource.CallBackResultadoDistrito callBackResultadoDistrito);


    void onGuardarDatosEditados(RequestBody requestKeyUser, RequestBody requestBodyEstado, RequestBody requestFile, RequestBody requestBodyNombre,
                                RequestBody requestBodyApellidos, RequestBody requestBodyCelular, RequestBody requestBodyCodigoDepartamento,
                                RequestBody requestBodyCodigoProvincia, RequestBody requestBodyCodigoDistrito,
                                RequestBody requestBodyLatitud, RequestBody requestBodyLongitud,
                                RequestBody requestBodyDireccion, DatosPerfilDataSource.CallBackResultado<EditarPerfilResponse> defaultResponseCallBackResultado);



}
