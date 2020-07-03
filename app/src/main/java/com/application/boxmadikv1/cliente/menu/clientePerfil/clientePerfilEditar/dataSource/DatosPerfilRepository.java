package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.local.ClienteDatosPerfilLocal;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.remote.ClienteDatosPerfilRemote;

import okhttp3.RequestBody;

public class DatosPerfilRepository implements DatosPerfilDataSource {


    private ClienteDatosPerfilLocal clienteDatosPerfilLocal;
    private ClienteDatosPerfilRemote clienteDatosPerfilRemote;

    public DatosPerfilRepository(ClienteDatosPerfilLocal clienteDatosPerfilLocal, ClienteDatosPerfilRemote clienteDatosPerfilRemote) {
        this.clienteDatosPerfilLocal = clienteDatosPerfilLocal;
        this.clienteDatosPerfilRemote = clienteDatosPerfilRemote;
    }

    private static DatosPerfilRepository mInstance = null;


    public static final DatosPerfilRepository getmInstance(ClienteDatosPerfilLocal clienteDatosPerfilLocal, ClienteDatosPerfilRemote clienteDatosPerfilRemote) {

        if (mInstance == null) {
            mInstance = new DatosPerfilRepository(clienteDatosPerfilLocal,clienteDatosPerfilRemote);
        }
        return mInstance;
    }

    @Override
    public void onObtenerNombrePais(String codigoPais, CallBackResultado<String> stringDatosPerfilDataSource) {
        clienteDatosPerfilLocal.onObtenerNombrePais(codigoPais, stringDatosPerfilDataSource);
    }

    @Override
    public void onObtenerNombreTipoDoc(String codigotipoDoc, CallBackResultado<String> stringDatosPerfilDataSource) {
        clienteDatosPerfilLocal.onObtenerNombreTipoDoc(codigotipoDoc, stringDatosPerfilDataSource);
    }

   /* @Override
    public void onGuardarDatosEditados(RequestBody requestKeyUser, RequestBody requestBodyEstado, RequestBody requestFile, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyCelular, RequestBody requestBodyCodigoDepartamento, RequestBody requestBodyCodigoProvincia, RequestBody requestBodyCodigoDistrito, RequestBody requestBodyLatitud, RequestBody requestBodyLongitud, RequestBody requestBodyDireccion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        clienteDatosPerfilRemote.onGuardarDatosEditados(requestKeyUser, requestBodyEstado, requestFile, requestBodyNombre, requestBodyApellidos, requestBodyCelular, requestBodyCodigoDepartamento, requestBodyCodigoProvincia, requestBodyCodigoDistrito, requestBodyLatitud, requestBodyLongitud, requestBodyDireccion, defaultResponseCallBackResultado);
    }*/

   /* @Override
    public void onMostrarDatosUsuario(String id_usuario, CallBackResultado callBackResultado) {
        clienteDatosPerfilRemote.onMostrarDatosUsuario(id_usuario,callBackResultado);
    }*/
}
