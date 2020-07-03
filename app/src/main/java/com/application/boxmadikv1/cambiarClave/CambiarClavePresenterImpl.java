package com.application.boxmadikv1.cambiarClave;

import android.content.res.Resources;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.MostrarPerfilResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarClavePresenterImpl extends BaseActivityPresenterImpl<CambiarClaveView> implements CambiarClavePresenter {

    public static final String TAG = CambiarClavePresenterImpl.class.getSimpleName();

    public CambiarClavePresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void OnClickCambiarClave(String claveActual, String claveNueva, String claveRepetir) {
        if(view!=null)view.deshabilitarButtonGuardar();
        String passClaveActual = claveActual.replace(" ", "1");
        String passClaveNueva = claveNueva.replace(" ", "1");
        String passClaveRepetir = claveRepetir.replace(" ", "1");

        if (claveActual.isEmpty()) {
            if (view != null){
                view.mostrarMensaje("Ingrese una clave , No se permiten Campos vacio* claveActual");
                view.habilitarButtonGuardar();
            }
            return;
        }
        if (claveActual != passClaveActual) {
            if (view != null){
                view.mostrarMensaje("Ingrese una clave sin espacios en blanco* claveActual");
                view.habilitarButtonGuardar();
            }
            return;
        }
        if (claveNueva.isEmpty()) {
            if (view != null){
                view.mostrarMensaje("Ingrese una clave , No se permiten Campos vacio* claveNueva");
                view.habilitarButtonGuardar();
            }
            return;
        }
        if (claveNueva != passClaveNueva) {
            if (view != null){
                view.mostrarMensaje("Ingrese una clave sin espacios en blanco* claveNueva");
                view.habilitarButtonGuardar();
            }
            return;
        }
        if (claveNueva.length() <= 5) {
            if (view != null) {
                view.mostrarMensaje("Ingrese un numero de 6 digitos* claveNueva");
                view.habilitarButtonGuardar();
            }
            return;
        }

        if (claveRepetir.isEmpty()) {
            if (view != null){
                view.mostrarMensaje("Ingrese una clave , No se permiten Campos vacio* claveRepetir");
                view.habilitarButtonGuardar();
            }
            return;
        }

        if (claveRepetir != passClaveRepetir) {
            if (view != null){
                view.mostrarMensaje("Ingrese una clave sin espacios en blanco* claveRepetir");
                view.habilitarButtonGuardar();
            }
            return;
        }


        if (claveNueva.equals(claveRepetir)) {
            initCambioRegistro(claveNueva, claveActual);
        } else {
            if (view != null) {
                view.mostrarMensaje("Claves no coinciden*");
                view.habilitarButtonGuardar();
            }
            return;
        }

    }

    String keyUser, codigoPais;

    @Override
    public void onDataUser(String keyUser, String codigoPais) {
        this.keyUser = keyUser;
        this.codigoPais = codigoPais;
        Log.d(TAG, "onDataUser: " + codigoPais + "/keyUser" + keyUser);
    }

    private void initCambioRegistro(String claveNueva, String claveActual) {
        if (view != null) view.mostrarProgressBarDialog();
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = loginService.cambiarClave2(keyUser, codigoPais, claveActual, claveNueva);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response == null) {
                    String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                    if (view != null) {
                        view.mostrarMensaje(mensajeError);
                        view.ocultarProgressBarDialog();
                        view.habilitarButtonGuardar();
                    }
                    return;
                }
                if (response.isSuccessful()) {
                    DefaultResponse defaultResponse = response.body();
                    if (defaultResponse.getError()) {
                        if (view != null) {
                            view.mostrarMensaje(defaultResponse.getMessage());
                            view.ocultarProgressBarDialog();
                            view.habilitarButtonGuardar();
                        }
                    } else {
                        if (view != null) {
                            view.mostrarMensaje(defaultResponse.getMessage());
                            view.ocultarProgressBarDialog();
                            view.finishActivity();
                            view.habilitarButtonGuardar();

                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
