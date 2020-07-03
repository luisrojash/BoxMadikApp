package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta;

import android.content.res.Resources;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.api.response.DefaultResponseEstadosLastId;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilPropuestaPresenterImpl extends BaseActivityPresenterImpl<PerfilPropuestaView> implements PerfilPropuestaPresenter {

    public static final String TAG = PerfilPropuestaPresenterImpl.class.getSimpleName();
    Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

    public PerfilPropuestaPresenterImpl(UseCaseHandler handler, Resources res) {
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
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onStart() {
        super.onStart();

        iniciarFragmentos();
        validacionActualizarNotificacion(tipoLlegadaExtra);
        initValidarFabButton();
        initValidateDatosUsuarioDireccion(keyUser);

    }

    private void initValidateDatosUsuarioDireccion(String keyUser) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        loginService.validarUsuDireccion(keyUser, codigoPais).enqueue(new Callback<DefaultResponseEstados>() {
            @Override
            public void onResponse(Call<DefaultResponseEstados> call, Response<DefaultResponseEstados> response) {
                DefaultResponseEstados defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    return;
                }
                if (defaultResponse != null) {
                    Log.d(TAG, "SE REGISTRARON CORRECTAMENTE " + defaultResponse.getEstado());
                    switch (defaultResponse.getEstado()) {
                        case DefaultResponseEstados.RUBRO_USUARIO_EXISTE:
                            //if (view != null) view.startBuscarPropuestaActivity(listIdRubros);
                            break;
                        case DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE:
                            String mensaje = "Por favor ingresar datos de su perfil necesarios para la propuesta";
                            if (view != null) view.mostrarDialogDireccion(mensaje);
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<DefaultResponseEstados> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "onFailure : " + mensaje);
            }
        });
    }

    private void initValidarFabButton() {
        switch (itemUi.getPropuestaEstado()) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                habilitarBottonChat();
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
                // deshabilitarBottonChat();
                validaCotizacion("inicio");
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_REVOCADOS");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_REVOCADOS");
                validaCotizacion("inicio");
                break;
            default:
                deshabilitarBottonChat();
                break;
        }
    }


    private void iniciarFragmentos() {
        if (view != null) {
            itemUi.setKeyUser(keyUser);
            Log.d(TAG, "keyUser ; " + keyUser);
            view.mostrarFragmentos(itemUi);
            view.mostrarDataInicial(itemUi);
        }
    }

    ItemUi itemUi;
    String tipoLlegadaExtra;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.itemUi = extras.getParcelable("itemUi");
        Log.d(TAG, "itemUi : " + itemUi.getMontoOfico());
        this.tipoLlegadaExtra = extras.getString("notificacion");
    }

    private void validacionActualizarNotificacion(String tipoLlegadaExtra) {
        Log.d(TAG, "validacionActualizarNotificacion : " + codigoPais);
        /*Actualizamos el estado notificacion a Leido*/
        switch (tipoLlegadaExtra) {
            case "notificacionClicked": /*Aqui Cuando llega de notificaciones de Segundo Plano*/
                initRetrofitActualizarEstado(codigoPais, Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA,
                        Constantes.GRUPO_NOTIFICACION_CLIENTE, itemUi.getCodigoUsuarioPropuesta(), itemUi.getCodigoPropuesta());
                Log.d(TAG, "notificacionClicked : ");
                break;
            case "notificacionAceptoCotizacionEspec":
                /*initRetrofitActualizarEstado(codigoPais, Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION,
                        Constantes.GRUPO_NOTIFICACION_ESPECIALISTA, itemUi.getCodigoUsuarioPropuesta(), itemUi.getCodigoPropuesta());*/

                int tipoDataEnviar = extras.getInt("tipoDataEnviar");
                validarTipoEnviosDataActualizar(tipoDataEnviar);
                break;
            case "actividadNotiNoti":/*Aqui cuando llega de Seleccionar Usuario*/
                initRetrofitActualizarEstado(codigoPais, Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA,
                        Constantes.GRUPO_NOTIFICACION_CLIENTE, itemUi.getCodigoUsuarioPropuesta(), itemUi.getCodigoPropuesta());
                Log.d(TAG, "actividadNotiNoti : ");
                break;
            default:
                Log.d(TAG, "default : ");
                //  if (view != null) view.finishActivity();
                break;
        }
    }

    private void validarTipoEnviosDataActualizar(int tipoDataEnviar) {
        switch (tipoDataEnviar) {
            case 10://  int tipoCreadaCotiza = 10;
                initRetrofitActualizarEstado(codigoPais, Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION,
                        Constantes.GRUPO_NOTIFICACION_CLIENTE, itemUi.getCodigoUsuarioPropuesta(), itemUi.getCodigoPropuesta());
                Log.d(TAG, "VISTO ACEPTO COTIZACION : ");
                break;
            case 20://  int tipoCreadaRevocada = 20;
                initRetrofitActualizarEstado(codigoPais, Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION,
                        Constantes.GRUPO_NOTIFICACION_CLIENTE, itemUi.getCodigoUsuarioPropuesta(), itemUi.getCodigoPropuesta());
                Log.d(TAG, "VISTO REVOCACION : ");
                break;
        }
    }

    private void initRetrofitActualizarEstado(String paisCodigoPeru, String tipoNotificacionCreacionPropuesta, String grupoNotificacionCliente, String codigoUsuarioPropuesta, String codigoPropuesta) {
        Log.d(TAG, "initRetrofitActualizarEstado :"
                + "paisCodigoPeru :" + paisCodigoPeru
                + "tipoNotificacionCreacionPropuesta :" + tipoNotificacionCreacionPropuesta
                + "grupoNotificacionCliente :" + grupoNotificacionCliente
                + "codigoUsuarioPropuesta :" + codigoUsuarioPropuesta
                + "codigoPropuesta :" + codigoPropuesta);//codigoPropuesta

        loginService.actualizarNotificacionesLeidas(paisCodigoPeru,
                tipoNotificacionCreacionPropuesta,
                grupoNotificacionCliente,
                codigoUsuarioPropuesta,
                codigoPropuesta
        ).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                // DefaultResponse loginResponse = response.body();
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, mensaje);
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        Log.d(TAG, "ALGO PASU PPAU");
                    } else {
                        Log.d(TAG, "ACTUALIZO CORRECTAMENTE");
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "mensaje : " + mensaje);
                return;
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    String keyUser, codigoPais;

    @Override
    public void onSessionManager(String keyUser, String codigoPais) {
        this.keyUser = keyUser;
        this.codigoPais = codigoPais;
    }

    @Override
    public void onClickClose() {
        switch (tipoLlegadaExtra) {
            case "notificacionClicked": // Cuando llega del Servicio NOtificacion
                if (view != null) view.iniStartActivityMenuEspecialista();
                break;
            case "actividadClicked":
                if (view != null) view.finishActivity();
                break;
            case "actividadNotiNoti":
                if (view != null) view.finishActivity();
                break;
            default:
                if (view != null) view.finishActivity();
                break;
        }
    }

    @Override
    public void onStartChat() {
        if (view != null) view.mostrarProgressBar();
        if (itemUi.getPropuestaEstado() == null) {
            deshabilitarBottonChat();
            Log.d(TAG, "itemUi.getPropuestaEstado() == null");
            return;
        }
        switch (itemUi.getPropuestaEstado()) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_CANCELADOS:
                deshabilitarBottonChat();
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_CANCELADOS");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                //deshabilitarBottonChat();
                habilitarBottonChat();
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PENDIENTE");
                if (view != null) {
                    view.deshabilitarButtonChat();
                    // view.habilitarButtonChat();
                    view.mostrarFabButtonChat();
                }
                initValidarChatExiste(itemUi.getCodigoPropuesta(), codigoPais, keyUser, itemUi.getCodigoUsuarioPropuesta());
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
                // deshabilitarBottonChat();
                validaCotizacion("button");
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_REVOCADOS");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:
                validaCotizacion("button");
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PROCESO");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO:
                deshabilitarBottonChat();
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_FINALIZADO");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO:
                deshabilitarBottonChat();
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PAGADO");
                break;

        }

        //initValidarChatExiste(itemUi.getCodigoPropuesta(), codigoPais, keyUser, itemUi.getCodigoUsuarioPropuesta());
    }


    @Override
    public void onCheckConexion(boolean isConnected, NetworkInfo activeNetwork) {
        Log.d(TAG, "Wifi  : " + isConnected);
        if (activeNetwork != null) {
            // connected to the internet
            if (isConnected) {
                //Esta Conectado a Intenet
                if (view != null) view.ocultarDialogInternet();
                Log.d(TAG, "Conexion Internet Exitosa");
            } else {
                if (view != null) view.mostrarDialogInternet();
            }
            //Log.d(TAG, "Wifi  : " + isConnected);

        } else {
            try {
                if (view != null) view.mostrarDialogInternet();
                Log.d(TAG, "else  : " + isConnected);
                // not connected to the internet
            } catch (Exception e) {
                Log.d(TAG, "ALGO PASO AL CREAR  : ");
            }

        }

    }

    private void validaCotizacion(String tipoEstado) {
        if (itemUi.getCotiEstado() == null) {
            deshabilitarBottonChat();
        }

        switch (itemUi.getCotiEstado()) {
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                //puede Conversar
                if (tipoEstado.equals("inicio")) {

                } else {
                    if (view != null) {
                        view.deshabilitarButtonChat();
                        // view.habilitarButtonChat();
                        view.mostrarFabButtonChat();
                    }
                    initValidarChatExiste(itemUi.getCodigoPropuesta(), codigoPais, keyUser, itemUi.getCodigoUsuarioPropuesta());
                }
                break;
            default:
                deshabilitarBottonChat();
                break;
        }
    }

    private void deshabilitarBottonChat() {
        String mensajeButton = "Boton Deshabilitado";
        if (view != null) {
            view.ocultarFabButtonChat();
            view.deshabilitarButtonChat();
            //  view.mostrarMensaje(mensajeButton);
        }
        return;
    }

    private void habilitarBottonChat() {
        if (view != null) {
            view.mostrarFabButtonChat();
            view.habilitarButtonChat();
        }
    }


    private void initValidarChatExiste(String codigoPropuesta, final String codigoPais, final String keyUser, String codigoUsuarioPropuesta) {
      /*  loginService.validarChatGrupoExiste(codigoPropuesta,
                codigoPais,
                keyUser,
                codigoUsuarioPropuesta*/
        loginService.validarChatGrupoExiste(codigoPropuesta,
                codigoPais,
                codigoUsuarioPropuesta,
                keyUser
        ).enqueue(new Callback<DefaultResponseEstadosLastId>() {
            @Override
            public void onResponse(Call<DefaultResponseEstadosLastId> call, Response<DefaultResponseEstadosLastId> response) {
                DefaultResponseEstadosLastId defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    return;
                }
                if (defaultResponse != null) {


                    String tipoEstadoGrupo = defaultResponse.getEstado();
                    //String tipoUsuario = "especialista";
                    Log.d(TAG, "tipoEstadoGrupo : " + tipoEstadoGrupo);
                    String tipoUsuario = "especialista";
                    String idUsuarioPropuesta = itemUi.getCodigoUsuarioPropuesta();
                    String idPropuesta = itemUi.getCodigoPropuesta();
                    String idGrupoChat = defaultResponse.getLastid();
                    String imagenRubro = itemUi.getImagePropuesta();
                    String nombrePropuesta = itemUi.getNombrePropuesta();
                    switch (defaultResponse.getEstado()) {
                        case DefaultResponseEstados.RUBRO_USUARIO_EXISTE:
                            Log.d(TAG, "SE REGISTRARON CORRECTAMENTE " + defaultResponse.getLastid());
                            if (view != null) {
                                view.starActivityChat(tipoEstadoGrupo,
                                        idUsuarioPropuesta, keyUser, idPropuesta, idGrupoChat, tipoUsuario, imagenRubro, nombrePropuesta);
                                //view.habilitarButtonChat();
                            }
                            break;
                        case DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE:
                            if (view != null) view.starActivityChat(tipoEstadoGrupo,
                                    idUsuarioPropuesta, keyUser, idPropuesta, idGrupoChat, tipoUsuario, imagenRubro, nombrePropuesta);
                            break;
                        case DefaultResponseEstados.CREACION_CORRECTA:
                            /*Crea*/
                            Log.d(TAG, "SE REGISTRARON CORRECTAMENTE " + defaultResponse.getLastid());
                            if (view != null) view.starActivityChat(tipoEstadoGrupo,
                                    idUsuarioPropuesta, keyUser, idPropuesta, idGrupoChat, tipoUsuario, imagenRubro, nombrePropuesta);
                            break;
                        case DefaultResponseEstados.CREACION_ERROR:
                            /*Crea*/
                            Log.d(TAG, "CREACION_ERROR");
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<DefaultResponseEstadosLastId> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "mensaje : " + mensaje);
                return;
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });


    }



      /*if(view!=null)  view.starActivityChat(tipoEstadoGrupo,
                idUsuarioPropuesta, tipoUsuario, idUsuarioEmisor,idPropuesta);*/

}
