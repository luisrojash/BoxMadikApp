package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource.local.ClientePerfilCuentaBancariaLocal;

public class ClientePerfilCuentaBancariaRepository implements ClientePerfilCuentaBancariaDataSource{


    private ClientePerfilCuentaBancariaLocal clientePerfilCuentaBancariaLocal;

    public ClientePerfilCuentaBancariaRepository(ClientePerfilCuentaBancariaLocal clientePerfilCuentaBancariaLocal) {
        this.clientePerfilCuentaBancariaLocal = clientePerfilCuentaBancariaLocal;
    }

    private static ClientePerfilCuentaBancariaRepository mInstance = null;


    public static final ClientePerfilCuentaBancariaRepository getmInstance(ClientePerfilCuentaBancariaLocal clientePerfilCuentaBancariaLocal) {

        if (mInstance == null) {
            mInstance = new ClientePerfilCuentaBancariaRepository(clientePerfilCuentaBancariaLocal);
        }
        return mInstance;
    }


    @Override
    public void onListarBanco(CallBackResultado callBackResultado) {
        clientePerfilCuentaBancariaLocal.onListarBanco(callBackResultado);
    }
}
