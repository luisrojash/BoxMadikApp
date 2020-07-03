package com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.local.ClientePendienteLocal;
import com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.remote.ClientePendienteRemote;


public class ClientePendienteRepository implements ClientePendienteDataSource {

    private static ClientePendienteRepository mInstance = null;

    public static final ClientePendienteRepository getmInstance(ClientePendienteLocal clienteTodosLocal, ClientePendienteRemote clientePendienteRemote) {
        if (mInstance == null) {
            mInstance = new ClientePendienteRepository(clienteTodosLocal, clientePendienteRemote);
        }
        return mInstance;
    }

    private ClientePendienteLocal clienteTodosLocal;
    private ClientePendienteRemote clientePendienteRemote;

    public ClientePendienteRepository(ClientePendienteLocal clienteTodosLocal, ClientePendienteRemote clientePendienteRemote) {
        this.clienteTodosLocal = clienteTodosLocal;
        this.clientePendienteRemote = clientePendienteRemote;
    }

    @Override
    public void mostrarListaClienteTodos(final String keyUser, final CallBackResultado<ListaPropuestaPendienteResponse> listCallBackResultado) {
        // clienteTodosLocal.mostrarListaClienteTodos(keyUser, listCallBackResultado);
        clientePendienteRemote.mostrarListaClienteTodos(keyUser, new CallBackResultado<ListaPropuestaPendienteResponse>() {
            @Override
            public void onCallBackResultado(ListaPropuestaPendienteResponse resultado) {
                if (resultado != null) {

                    //List<PropuestaInicial> propuestaInicialList = resultado.getPropuestasClientePendientes();
                    clienteTodosLocal.mostrarListaFiltradaLocal(resultado, new CallBackResultado<ListaPropuestaPendienteResponse>() {
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
