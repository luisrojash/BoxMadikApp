package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.presentacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad.DireccionUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity.*;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.presentacion.PresentacionPerfilActivity.EXTRA_PRESENTACION_PERFIL_PRESENTACION;

public class PresentacionPerfilPresenterImpl extends BaseActivityPresenterImpl<PresentacionPerfilView> implements PresentacionPerfilPresenter {

    public static final String TAG = PresentacionPerfilPresenterImpl.class.getSimpleName();
    public static final String EDIT_PERFIL_CORRECTAMENTE_PRESENTACION = "PresentacionPerfilPresenterImpl";


    public PresentacionPerfilPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    DireccionUi direccionUi;

    @Override
    public void onCreate() {
        super.onCreate();
        if (view != null) {
            view.obtenerKeyUser();
        }
        mostrarDataLlenaInicial();
    }

    private void mostrarDataLlenaInicial() {
        if (usuPresentacion != null) {
            view.mostrarPresentacion(usuPresentacion);
            view.habilitaTextPresentacion();
        } else {
            view.habilitaTextPresentacion();
        }
    }

    String usuPresentacion;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        /*this.direccionUi = extras.getParcelable(EspecialistaPerfilDistritoActivity.EXTRA_PERFIL_ACTIVITY_PERFIL_DIRECCION);
        Log.d(TAG, "PresentacionPerfilPresenterImpl : " + direccionUi.getUsuarioFoto()
                + "    lista    " + direccionUi.getDistritoUi().getCodigoDistritoList().size());*/
        this.usuPresentacion = extras.getString(EXTRA_PRESENTACION_PERFIL_PRESENTACION);
    }

    @Override
    public void onClickSiguiente(String presentacion) {
        if(view!=null)view.deshabilitarButtonGuardar();
        if (presentacion.isEmpty() || presentacion.equals("") || presentacion.trim().isEmpty() || presentacion == null) {
            String mensaje = "Llene su presentacion";
            if (view != null) {
                view.mostrarMensajeErrorTextPresentacion(mensaje);
                view.habilitarButtonGuardar();
            }
            return;
        }
        Log.d(TAG, "AQUI GUARDA");
        if (view != null) {
            view.mostrarDialogProgressBar();
            view.habilitarButtonGuardar();
        }

        String removeAcentosCadena = Constantes.removeAcentos(presentacion);
        String isPrimeroResultadoCharacter = Constantes.isPrimeroResultadoCharacter(removeAcentosCadena);
        String isSegundoresultadoCharacter = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacter);
        initUseCaseGuardar(isSegundoresultadoCharacter);
        //initStartActivityCursosActivity(direccionUi, presentacion);
    }

    private void initUseCaseGuardar(String presentacion) {
        String otros = "otros";
        String presentacionSpecial = Constantes.isResultadoEspecial(presentacion).trim();
        if (usuPresentacion != null) {

            String estadoActualizar = "0";
            initUseCaseGuardarPresentacion(keyUser, estadoActualizar, codigoPais, presentacionSpecial);
        } else {
            String estadoInsertar = "1";
            initUseCaseGuardarPresentacion(keyUser, estadoInsertar, codigoPais, presentacionSpecial);
        }
    }

    private void initUseCaseGuardarPresentacion(String keyUser, String estadoActualizar, String paisCodigoPeru, String presentacion) {

        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        service.guardarUsuarioPresentacion(keyUser, paisCodigoPeru, estadoActualizar, presentacion).enqueue(
                new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        DefaultResponse body = response.body();
                        if (body == null) {
                            String mensaje = "Ocurrio algun Problema de conexión";
                            if (view != null) {
                                view.ocultarDialogProgressBar();
                                view.mostrarMensaje(mensaje);
                                view.habilitarButtonGuardar();
                            }
                        } else {
                            if (body.getError()) {
                                if (view != null) {
                                    view.ocultarDialogProgressBar();
                                    view.mostrarMensaje(body.getMessage());
                                    view.habilitarButtonGuardar();
                                }
                            } else {

                                if (view != null) {
                                    view.ocultarDialogProgressBar();
                                    view.initStartActivityEspecEditPerfil(EDIT_PERFIL_CORRECTAMENTE_PRESENTACION);
                                    view.habilitarButtonGuardar();

                                }

                            }
                        }
                        //  Log.d(TAG, "body : " + body.getMessage());

                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        String mensaje = "Ocurrio algun Problema de conexión";
                        if (view != null) {
                            view.mostrarMensaje(mensaje);
                            view.habilitarButtonGuardar();
                        }
                        Log.d(TAG, "onFailure : " + t.getMessage().toString());
                    }
                });
    }

    String keyUser, codigoPais;

    @Override
    public void onKeyUser(String keyUser, String codigoPais) {
        this.keyUser = keyUser;
        this.codigoPais = codigoPais;
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        //    String paisCodigo = "51";
        Call<DefaultResponse> call = loginService.validacionAntecedentes(keyUser);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response == null) {
                    String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                    if (view != null) view.mostrarMensaje(mensajeError);
                    return;
                }
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        if (message.getError()) {

                            if (view != null) {
                                view.mostrarMensaje(message.getMessage());
                                view.mostrarImagenTextSinValidar();
                            }
                            return;
                        }
                        if (!message.getError()) {
                            if (view != null) {
                                view.mostrarMensaje(message.getMessage());
                                view.mostrarImagenTextValidado();
                            }
                            return;
                        }

                    } else {
                        String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                        if (view != null) view.mostrarMensaje(mensajeError);
                        return;
                    }
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //callBackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });


    }

    private void initStartActivityCursosActivity(DireccionUi direccionUi, String presentacion) {
        if (view != null) {
            view.initStartActivityCursos(direccionUi, presentacion);
        }
    }
}
