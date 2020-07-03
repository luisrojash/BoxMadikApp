package com.application.boxmadikv1.cliente.menu.clientePendiente;

import android.os.Bundle;
import com.application.boxmadikv1.cliente.menu.abstracto.ClientAbstractFragment;


public class ClientePendienteFragment extends ClientAbstractFragment{

    public static ClientePendienteFragment newInstance(String keyUser,String codigoPais,String tipoEstado) {
        Bundle args = new Bundle();
        ClientePendienteFragment fragment = new ClientePendienteFragment();
        args.putString("keyUser", keyUser);
        args.putString("codigoPais",codigoPais);
        args.putString("tiposEstadoCliente",tipoEstado);
        fragment.setArguments(args);
        return fragment;
    }

    public void ocultarTextEmpty(){
        ocultarEmpty();
    }


}
