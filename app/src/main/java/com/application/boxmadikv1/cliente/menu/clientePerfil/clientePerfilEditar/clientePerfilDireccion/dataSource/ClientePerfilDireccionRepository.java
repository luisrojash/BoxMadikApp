package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.local.ClientePerfilDireccionLocal;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.remote.ClientePerfilDireccionRemote;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;

import okhttp3.RequestBody;

public class ClientePerfilDireccionRepository implements ClientePerfilDireccionDataSource {

    private ClientePerfilDireccionLocal clientePerfilDireccionLocal;
    private ClientePerfilDireccionRemote clientePerfilDireccionRemote;

    public ClientePerfilDireccionRepository(ClientePerfilDireccionLocal clientePerfilDireccionLocal, ClientePerfilDireccionRemote clientePerfilDireccionRemote) {
        this.clientePerfilDireccionLocal = clientePerfilDireccionLocal;
        this.clientePerfilDireccionRemote = clientePerfilDireccionRemote;
    }

    private static ClientePerfilDireccionRepository mInstance = null;


    public static final ClientePerfilDireccionRepository getmInstance(ClientePerfilDireccionLocal clientePerfilDireccionLocal,ClientePerfilDireccionRemote clientePerfilDireccionRemote) {

        if (mInstance == null) {
            mInstance = new ClientePerfilDireccionRepository(clientePerfilDireccionLocal,clientePerfilDireccionRemote);
        }
        return mInstance;
    }

    @Override
    public void onListarDepartamento(CallBackResultado callBackResultado) {
        clientePerfilDireccionLocal.onListarDepartamento(callBackResultado);
    }

    @Override
    public void onListarProvincia(String paisCodigo, String departamentoCodigo, CallBackResultadoProvincia callBackResultadoProvincia) {
        clientePerfilDireccionLocal.onListarProvincia(paisCodigo, departamentoCodigo, callBackResultadoProvincia);
    }

    @Override
    public void onListarDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo, CallBackResultadoDistrito callBackResultadoDistrito) {
        clientePerfilDireccionLocal.onListarDistrito(paisCodigo, departamentoCodigo, provinciaCodigo, callBackResultadoDistrito);
    }

    @Override
    public void onGuardarDatosEditados(RequestBody requestKeyUser, RequestBody requestBodyEstado, RequestBody requestFile, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyCelular, RequestBody requestBodyCodigoDepartamento, RequestBody requestBodyCodigoProvincia, RequestBody requestBodyCodigoDistrito, RequestBody requestBodyLatitud, RequestBody requestBodyLongitud, RequestBody requestBodyDireccion, DatosPerfilDataSource.CallBackResultado<EditarPerfilResponse> defaultResponseCallBackResultado) {
        clientePerfilDireccionRemote.onGuardarDatosEditados(requestKeyUser, requestBodyEstado, requestFile, requestBodyNombre, requestBodyApellidos, requestBodyCelular, requestBodyCodigoDepartamento, requestBodyCodigoProvincia, requestBodyCodigoDistrito, requestBodyLatitud, requestBodyLongitud, requestBodyDireccion, defaultResponseCallBackResultado);
    }
}
