package com.application.boxmadikv1.recuperarClave;

import android.content.res.Resources;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.recuperarClave.useCase.RecuperarClave;

public class RecuperarClavePresenterImpl extends BaseActivityPresenterImpl<RecuperarClaveView> implements RecuperarClavePresenter {

    public static final String TAG = RecuperarClavePresenterImpl.class.getSimpleName();
    private RecuperarClave recuperarClave;

    public RecuperarClavePresenterImpl(UseCaseHandler handler, Resources res, RecuperarClave recuperarClave) {
        super(handler, res);
        this.recuperarClave = recuperarClave;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void onEnviarCorreo(String correo) {
        //correo.trim().toString().matches("")
        if (correo.trim().toString().matches("")) {
            if (view != null) view.mostrarErrorCorreoCampoVacio("Ingresar Correo Electronico");
        } else {
            initUseCaseRecuperarClave(correo);
        }
    }


    private void initUseCaseRecuperarClave(String correo) {
        if (view != null) view.mostrarProgressDialog();
        handler.execute(recuperarClave, new RecuperarClave.RequestValues(correo),
                new UseCase.UseCaseCallback<RecuperarClave.ResponseValue>() {
                    @Override
                    public void onSuccess(RecuperarClave.ResponseValue response) {
                        Log.d(TAG, "onSuccess : " + response.isEstado());
                        if (response.isEstado()) {
                            String estado = "true";
                            if (view != null) {
                                view.mostrarMensaje(response.getMensaje(), estado);
                                view.ocultarProgressDialog();
                            }
                        } else {
                            String estado = "false";
                            if (view != null) {
                                view.mostrarMensaje(response.getMensaje(), estado);
                                view.ocultarProgressDialog();
                                view.finishActivity();
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }
}
