package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.useCase.ListaBanco;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.ListaDistrito;

public class ClientePerfilCuentaBancariaPresenterImpl extends BaseActivityPresenterImpl<ClientePerfilCuentaBancariaView> implements ClientePerfilCuentaBancariaPresenter {
    String   nombre = "", apellidos = "", celular = "",direccion="", tipodepartamento = "", tipoprovincia = "", tipodistrito = "",imagenperfil="";

    ListaBanco listaBanco;

    @Override
    public void onStart() {
            super.onStart();
            initUseCaseTipoBanco();
    }

    private void initUseCaseTipoBanco() {
        handler.execute(listaBanco, new ListaBanco.RequestValues(), new UseCase.UseCaseCallback<ListaBanco.ResponseValue>() {
            @Override
            public void onSuccess(ListaBanco.ResponseValue response) {
                Log.d(TAG,"execute"+response.getTipoBancoUis().size());

                if (response.getTipoBancoUis() == null) return;
                if (view != null)
                    view.mostrarListaTipoBanco(response.getTipoBancoUis());
            }

            @Override
            public void onError() {

            }
        });
    }

    public static final String TAG = ClientePerfilCuentaBancariaPresenterImpl.class.getSimpleName();

    @Override
    protected String getTag() {
        return null;
    }

    public ClientePerfilCuentaBancariaPresenterImpl(UseCaseHandler handler, Resources res,ListaBanco listaBanco) {
        super(handler, res);

        this.listaBanco = listaBanco;
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);

        super.setExtras(extras);
        if (extras == null) return;
        this.nombre = extras.getString("nombre");
        this.apellidos = extras.getString("apellidos");
        this.nombre = extras.getString("nombre");
        this.celular = extras.getString("celular");
        this.tipodepartamento = extras.getString("tipodepartamento");
        this.tipoprovincia = extras.getString("tipoprovincia");
        this.tipodistrito = extras.getString("tipodistrito");
        this.direccion = extras.getString("direccion");
        this.imagenperfil = extras.getString("imagenperfil");


        Log.d(TAG, "ClientePerfilCuentaBancariaPresenterImpl setExtras : " + nombre +
                apellidos + celular + tipodepartamento +
                tipoprovincia + tipodistrito +direccion+ imagenperfil);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void OnClickMenuCliente() {
        if(view!=null)view.starActivityMenuCliente();
    }
}
