package com.application.boxmadikv1.chat;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.api.response.ListaChatResponse;
import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.chat.entidad.MensajesUi;
import com.application.boxmadikv1.utils.Constantes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatPresenterImpl extends BaseActivityPresenterImpl<ChatView> implements ChatPresenter {

    public static final String TAG = ChatPresenterImpl.class.getSimpleName();
    Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

    public ChatPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
        mensajesUiList = new ArrayList<>();
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    String tipoEstadoGrupo, idUsuarioPropuesta, keyUser, idPropuesta, idGrupoChat, tipoUsuario, imagenRubro, nombrePropuesta;


    @Override
    public void onStart() {
        super.onStart();
        initValidarTipoUsuarioChatLeidos(tipoUsuario);
        int pageCount = 1;
        String tipoCarga = "SinLoadMore";
        initObtenerListaChat(idGrupoChat, pageCount, tipoCarga);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        //  this.tipoEstadoGrupo = extras.getString("tipoEstadoGrupo");//---
        this.idUsuarioPropuesta = extras.getString("idUsuarioPropuesta");
        this.keyUser = extras.getString("keyUser");
        //this.idPropuesta = extras.getString("idPropuesta");//----
        this.idGrupoChat = extras.getString("idGrupoChat");
        this.tipoUsuario = extras.getString("tipoUsuario");
        this.imagenRubro = extras.getString("imagenRubro");
        this.nombrePropuesta = extras.getString("nombrePropuesta");
        initValidateTipoUsuario(tipoUsuario);


        Log.d(TAG, "tipoEstadoGrupo :: " + tipoEstadoGrupo +
                " /  idUsuarioPropuesta :: " + idUsuarioPropuesta +
                " /  keyUser :: " + keyUser +
                "/  idPropuesta :: " + idPropuesta +
                "/  idGrupoChat :: " + idGrupoChat +
                "/  tipoUsuario :: " + tipoUsuario +
                "/  imagenRubro :: " + imagenRubro +
                "/  nombrePropuesta :: " + nombrePropuesta

        );

    }

    private void initValidarTipoUsuarioChatLeidos(String tipoUsuario) {
        /*Si entro como cliente , al especialista sus mensajes le pongo leido*/
        switch (tipoUsuario) {
            case "especialista": /**/

                Log.d(TAG, "especialista : "
                        + "keyUser : " + keyUser
                        + "Propuesta ; " + idUsuarioPropuesta);
                // initLeerMensajesTipoUsuario(idUsuarioPropuesta, idGrupoChat, paisCodigo);
                initLeerMensajesTipoUsuario(idUsuarioPropuesta, idGrupoChat, paisCodigo);
                break;
            case "cliente":
                Log.d(TAG, "cliente : "
                        + "keyUser : " + keyUser
                        + "Propuesta ; " + idUsuarioPropuesta);

                //initLeerMensajesTipoUsuario(keyUser, idGrupoChat, paisCodigo);
                initLeerMensajesTipoUsuario(idUsuarioPropuesta, idGrupoChat, paisCodigo);
                //initLeerMensajesTipoUsuario(idUsuarioPropuesta, idGrupoChat, paisCodigo);
                break;
        }
    }


    private void initLeerMensajesTipoUsuario(String codigoUsuario, String idGrupoChat, String paisCodigo) {
        Log.d(TAG, "initLeerMensajesTipoUsuario : " + codigoUsuario);
        Call<DefaultResponse> call = apiService.actualizarChatMensajeLeidos(codigoUsuario,
                paisCodigo,
                idGrupoChat);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse != null) {
                    if (cambioResponse.getError()) {
                        Log.d(TAG, "cambioResponse.getError() : ");
                    } else {


                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //datosPerfilResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });
    }

    private List<MensajesUi> mensajesUiList;

    int pageCount = 1;

    private void initObtenerListaChat(String idGrupoChat, final int countPAge, final String tipoCarga) {
        //final int countPAge = 1;
        if (mensajesUiList != null) mensajesUiList.clear();
        Log.d(TAG, "initObtenerListaChat : " + mensajesUiList.size());
        if (view != null) view.mostrarProgressBar();
        //obtenerListaChat
        Call<ListaChatResponse> call = apiService.obtenerListaChat(Constantes.ESTADO_ACTIVO,
                idGrupoChat,
                String.valueOf(countPAge));
        call.enqueue(new Callback<ListaChatResponse>() {
            @Override
            public void onResponse(Call<ListaChatResponse> call, Response<ListaChatResponse> response) {
                ListaChatResponse cambioResponse = response.body();

                if (cambioResponse != null) {
                    if (cambioResponse.getError()) {
                        if (view != null) view.ocultarProgressBar();
                        Log.d(TAG, "cambioResponse.getError() : " + cambioResponse.getMessage());
                    } else {

                        List<ListaChatResponse.ChatResponseList> responseLists = cambioResponse.getChatResponseListList();
                        if (cambioResponse.getChatResponseListList() == null) return;

                        isScrolling = false;

                        switch (tipoCarga) {
                            case "SinLoadMore":
                                isScrolling = true;
                                List<MensajesUi> mensajesUis = new ArrayList<>();
                                for (ListaChatResponse.ChatResponseList list
                                        : responseLists) {
                                    MensajesUi mensajesUi = new MensajesUi();
                                    mensajesUi.setCodigoUsuario(list.getUsu_Codigo());
                                    mensajesUi.setMensaje(list.getChat_mensaje());
                                    mensajesUi.setFecha(list.getChat_Fecha());
                                    mensajesUis.add(mensajesUi);
                                    // mensajesUiList.add(mensajesUi);
                                    if (view != null) view.ocultarProgressBar();
                                }
                                if (view != null) {
                                    view.mostrarLista(mensajesUis);
                                    view.ocultarProgressBar();
                                }
                                pageCount = countPAge + 1;
                                Log.d(TAG, "SinLoadMore : ");
                                break;
                            case "ConLoadMore":
                                isScrolling = true;
                                for (ListaChatResponse.ChatResponseList list
                                        : responseLists) {
                                    MensajesUi mensajesUi = new MensajesUi();
                                    mensajesUi.setCodigoUsuario(list.getUsu_Codigo());
                                    mensajesUi.setMensaje(list.getChat_mensaje());
                                    mensajesUi.setFecha(list.getChat_Fecha());
                                    mensajesUiList.add(mensajesUi);
                                    if (view != null) view.ocultarProgressBar();
                                }
                                if (view != null) {
                                    view.mostrarListaAdd(mensajesUiList);
                                    view.ocultarProgressBar();
                                }
                                pageCount = countPAge + 1;
                                Log.d(TAG, "ConLoadMore : " + mensajesUiList.size());
                                break;
                        }

                        /*for (ListaChatResponse.ChatResponseList list
                                : responseLists) {
                            MensajesUi mensajesUi = new MensajesUi();
                            mensajesUi.setCodigoUsuario(list.getUsu_Codigo());
                            mensajesUi.setMensaje(list.getChat_mensaje());
                            mensajesUi.setFecha(list.getChat_Fecha());
                            mensajesUiList.add(mensajesUi);
                            if(view!=null)view.ocultarProgressBar();
                        }
                        if (view != null) view.mostrarLista(mensajesUiList);*/
                    }
                } else {
                    if (view != null) view.ocultarProgressBar();
                    Log.d(TAG, "cambioResponse == null : ");
                }
            }

            @Override
            public void onFailure(Call<ListaChatResponse> call, Throwable t) {
                //datosPerfilResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
                if (view != null) view.ocultarProgressBar();
            }
        });

    }

    private void initValidateTipoUsuario(String tipoUsuario) {
        switch (tipoUsuario) {
            case "especialista":
                initMostrardatosInicialRetrofit(idUsuarioPropuesta, tipoUsuario);
                break;
            case "cliente":
                initMostrardatosInicialRetrofit(idUsuarioPropuesta, tipoUsuario);
                break;
        }
    }

    private void initMostrardatosInicialRetrofit(String userKeyReceptor, final String tipoUsuario) {
        Call<DatosPerfilResponse> call = apiService.obtenerDatosPerfilCliente(userKeyReceptor,
                Constantes.PAIS_CODIGO_PERU);
        call.enqueue(new Callback<DatosPerfilResponse>() {
            @Override
            public void onResponse(Call<DatosPerfilResponse> call, Response<DatosPerfilResponse> response) {
                DatosPerfilResponse cambioResponse = response.body();
                if (cambioResponse.getDatosPerfilClienteResponse() == null) return;
                if (cambioResponse != null) {
                    if (cambioResponse.getError()) {
                        Log.d(TAG, "cambioResponse.getError() : " + cambioResponse.getMensaje());
                    } else {
                        //  String nombre = cambioResponse.getDatosPerfilClienteResponse().getNombre() + " " + cambioResponse.getDatosPerfilClienteResponse().getApellidos();
                        String nombre = validateNombreNull(cambioResponse);
                        String foto = cambioResponse.getDatosPerfilClienteResponse().getFoto();
                        switch (tipoUsuario) {
                            case "especialista":
                                initMostrardatosInicial(nombre.toUpperCase(), foto, "Cliente", imagenRubro, nombrePropuesta.toUpperCase());
                                break;
                            case "cliente":
                                initMostrardatosInicial(nombre.toUpperCase(), foto, "Especialista", imagenRubro, nombrePropuesta.toUpperCase());
                                break;
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<DatosPerfilResponse> call, Throwable t) {
                //datosPerfilResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });

    }

    private String validateNombreNull(DatosPerfilResponse cambioResponse) {
        String nombre = "";
        if (cambioResponse.getDatosPerfilClienteResponse().getNombre() == null) {
            nombre = cambioResponse.getDatosPerfilClienteResponse().getUsuRazonSocial();
        } else {
            nombre = cambioResponse.getDatosPerfilClienteResponse().getNombre() + " " + cambioResponse.getDatosPerfilClienteResponse().getApellidos();
        }
        return nombre;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    private void initMostrardatosInicial(String nombre, String foto, String tipoUsuario, String imagenRubro, String nombrePropuesta) {
        if (view != null)
            view.mostrarDatos(nombre, foto, tipoUsuario, imagenRubro, nombrePropuesta);
    }

    @Override
    public void onClickBackPressed() {
        if (view != null) view.finishActivity();
    }

    String keyUserActual, paisCodigo;

    @Override
    public void onObtenerDatoPreferencia(String usuarioCodigo, String paisCodigo) {
        this.keyUserActual = usuarioCodigo;
        this.paisCodigo = paisCodigo;
    }

    @Override
    public void onEnviarMensaje(String mensaje) {
        Log.d(TAG, "mensaje : " + mensaje);
        //if (view != null) view.deshabilitarButtonEnviar();
        if (mensaje.trim().toString().matches("")) {
            //if (view != null) view.mostrarErrorCorreoCampoVacio("Ingresar Correo Electronico");
        } else {

            String chat = "chat";
            initRegistroMensaje(Constantes.isResultadoCharacterChat(mensaje));
        }
        // initRegistroMensaje(Constantes.isResultadoCharacterChat(mensaje));
    }

    @Override
    public void onDataRealTime(String mensaje, String timeStamp, String codigoUsuario, String codigoMensaje) {
        MensajesUi mensajesUi = new MensajesUi();
        mensajesUi.setCodigoUsuario(codigoUsuario);
        mensajesUi.setCodigoMensaje(codigoMensaje);
        mensajesUi.setMensaje(mensaje);
        mensajesUi.setFecha(timeStamp + "");
        if (view != null) view.agregarMensaje(mensajesUi);
        mensajesUiList.add(mensajesUi);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

    }

    //int currentItems, totalItems, scrollOutItems;
    boolean isScrolling = false;

    @Override
    public void onScrolled(LinearLayoutManager layoutManager, int dx, int dy) {

// To check if at the top of recycler view firstCompletelyVisibleItemPosition
        if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
            Log.d(TAG, "onNotBottom");
            // Its at top
            Log.d(TAG, "top");
            if (isScrolling) {
                isScrolling = false;
                // if (view != null) view.mostrarProgressBar();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String tipoCargaConLoadMore = "ConLoadMore";
                        initObtenerListaChat(idGrupoChat, pageCount, tipoCargaConLoadMore);
                        Log.d(TAG, "isScrolling" + pageCount);

                    }
                }, 2000);
                Log.d(TAG, "onNotBottom");
            }
        }



       /* int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
        if (pastVisibleItems + visibleItemCount >= totalItemCount) {
            //End of list
            Log.d(TAG, "onBottomReachedListener");
        } else {

            if (isScrolling) {
                isScrolling = false;
               // if (view != null) view.mostrarProgressBar();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String tipoCargaConLoadMore = "ConLoadMore";
                        initObtenerListaChat(idGrupoChat, pageCount, tipoCargaConLoadMore);
                        Log.d(TAG, "isScrolling" + pageCount);

                    }
                }, 2000);
                Log.d(TAG, "onNotBottom");
            }


        }*/
    }

    private void initRegistroMensaje(final String mensaje) {
        if (view != null) view.deshabilitarButtonEnviar();
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final String timeStamp = formatter.format(today);
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        Log.d(TAG, "initRegistroMensaje : " + tipoUsuario +
                " / keyUser : " + keyUser +
                " / idGrupoChat:" + idGrupoChat +
                " / paisCodigo:" + paisCodigo +
                " / timeStamp:" + timeStamp );
        Call<DefaultResponseRegistro> call = loginService.enviarMensajeChat(mensaje,
                Constantes.ESTADO_ACTIVO,
                keyUser,
                idGrupoChat,
                paisCodigo,
                timeStamp,
                tipoUsuario);
        call.enqueue(new Callback<DefaultResponseRegistro>() {
            @Override
            public void onResponse(Call<DefaultResponseRegistro> call, Response<DefaultResponseRegistro> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponseRegistro defaultResponse = response.body();

                        if (defaultResponse.getError()) {
                            if (view != null) view.habilitarButtonEnviar();
                            Log.d(TAG, "initRegistroMensaje :if  Existe Error" + defaultResponse.getMessage());
                        } else {
                            String idMensajeChat = defaultResponse.getLastid();
                            MensajesUi mensajesUi = new MensajesUi();
                            mensajesUi.setCodigoUsuario(keyUser);
                            mensajesUi.setCodigoMensaje(idMensajeChat);
                            mensajesUi.setMensaje(mensaje);
                            mensajesUi.setFecha(timeStamp);
                            mensajesUi.setTipoEstadoMensaje(Constantes.ESTADO_ACTIVO);
                            mensajesUiList.add(mensajesUi);
                            if (view != null) {
                                view.agregarMensaje(mensajesUi);
                                view.habilitarButtonEnviar();
                            }
                        }
                    } else {
                        if (view != null) view.habilitarButtonEnviar();
                        Log.d(TAG, "else : Nulo response ");
                        // listaCotizacionesResponseCallBackResultado.onCallBackResultado(null);
                    }
                }

            }

            @Override
            public void onFailure(Call<DefaultResponseRegistro> call, Throwable t) {
                if (view != null) view.habilitarButtonEnviar();
                Log.d(TAG, "onFailure : ");
            }
        });
    }
}
