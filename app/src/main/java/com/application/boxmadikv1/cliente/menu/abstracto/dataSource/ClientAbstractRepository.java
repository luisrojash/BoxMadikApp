package com.application.boxmadikv1.cliente.menu.abstracto.dataSource;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.local.ClientAbstractLocal;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.remote.ClientAbstractRemote;

public class ClientAbstractRepository implements ClientAbstractDataSource {

    private ClientAbstractRemote clientAbstractRemote;
    private ClientAbstractLocal clientAbstractLocal;
    private static ClientAbstractRepository mInstance = null;

    public ClientAbstractRepository(ClientAbstractLocal clientAbstractLocal, ClientAbstractRemote clientAbstractRemote) {
        this.clientAbstractRemote = clientAbstractRemote;
        this.clientAbstractLocal = clientAbstractLocal;
    }

    public static final ClientAbstractRepository getmInstance(ClientAbstractLocal clientAbstractLocal, ClientAbstractRemote clientAbstractRemote) {
        if (mInstance == null) {
            mInstance = new ClientAbstractRepository(clientAbstractLocal, clientAbstractRemote);
        }
        return mInstance;
    }


    @Override
    public void mostrarListaClienteEstados(String keyUser,String codigoPais, String tipoEstado, final CallBackResultado<ListaPropuestaPendienteResponse> listCallBackResultado) {
      //  clientAbstractLocal.mostrarListaClienteEstados(keyUser, tipoEstado, listCallBackResultado);
        clientAbstractRemote.mostrarListaClienteEstados(keyUser,codigoPais, tipoEstado, new CallBackResultado<ListaPropuestaPendienteResponse>() {
            @Override
            public void onCallBackResultado(ListaPropuestaPendienteResponse resultado) {
                if (resultado != null) {

                    //List<PropuestaInicial> propuestaInicialList = resultado.getPropuestasClientePendientes();
                    clientAbstractLocal.mostrarListaFiltradaLocal(resultado, new CallBackResultado<ListaPropuestaPendienteResponse>() {
                        @Override
                        public void onCallBackResultado(ListaPropuestaPendienteResponse resultado) {
                            listCallBackResultado.onCallBackResultado(resultado);
                        }
                    });
                } else {
                    listCallBackResultado.onCallBackResultado(null);
                }
            }
        });
    }
}
