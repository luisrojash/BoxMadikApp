package com.application.boxmadikv1.especialista.menu;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.especialistaCancelado.EspecialistaCanceladoFragment;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.EspecialistaEnviadosFragment;
import com.application.boxmadikv1.especialista.menu.especialistaFinalizado.EspecialistaFinalizadoFragment;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.EstudioPerfilPresenterImpl;
import com.application.boxmadikv1.especialista.menu.especialistaProceso.EspecialistaProcesoFragment;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuEspecialistaPresenterImpl extends BaseActivityPresenterImpl<MenuEspecialistaView> implements MenuEspecialistaPresenter {

    public static final String TAG = MenuEspecialistaPresenterImpl.class.getSimpleName();


    public MenuEspecialistaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //iniciarFragmentos();
    }

    @Override
    public void onBackPressed() {

    }

    private Class<? extends Fragment> fragmentClassVisible;

    @Override
    public void onPageChanged(Class<? extends Fragment> fragmentClass) {
        if (fragmentClass == null) return;
        fragmentClassVisible = fragmentClass;
        if (fragmentClass.equals(EspecialistaEnviadosFragment.class)) {
            ocultarBuscador();
           /* mostrarFab();
            ocultarBuscador();*/
            return;
        }
        if (fragmentClass.equals(EspecialistaProcesoFragment.class)) {
           /* mostrarFab();
            ocultarBuscador();*/
            return;
        }
        if (fragmentClass.equals(EspecialistaFinalizadoFragment.class)) {
            /*mostrarFab();
            ocultarBuscador();*/
            return;
        }
        if (fragmentClass.equals(EspecialistaCanceladoFragment.class)) {
           /* mostrarFab();
            ocultarBuscador();*/
            return;
        }


    }

    private void ocultarBuscador() {
        if (view != null) view.ocultarBuscador();
    }

    @Override
    public void onFabClicked(int pagePosition) {

    }

    @Override
    public void onClickBuscarPropuesta() {
        if (view != null) view.mostrarProgressBar();
        initValidateDatosUsuarioDireccion(keyUser);
        // if (view != null) view.startBuscarPropuestaActivity(listIdRubros);
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
                            if (view != null) view.startBuscarPropuestaActivity(listIdRubros);
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
                Log.d(TAG, "onFailure : " );
            }
        });
    }

    String keyUser, codigoPais;

    @Override
    public void onInitKeyUser(String keyUser, String codigoPais) {
        this.keyUser = keyUser;
        this.codigoPais = codigoPais;
        Log.d(TAG, "onInitKeyUser : " + keyUser);
        if (view != null) view.mostrarFragmentos(keyUser, codigoPais);
    }

    @Override
    public void initStartActivitySeleccionUser() {
        if (view != null) view.initStartActivitySeleccionUser();
    }

    @Override
    public void onRealTipoEstado(Bundle extras) {
        String tipoNotificacion = extras.getString("tipoNotificacion");
        switch (tipoNotificacion){
            case Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK:
                Log.d(TAG, " Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA:
                Log.d(TAG, " Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION:
                Log.d(TAG, " Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION");
                break;
            case Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION:
                if(view!=null)view.actualizarListasAceptoCotiza();
                Log.d(TAG, " Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION:
                Log.d(TAG, " Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION");
                break;
            case Constantes.TIPO_NOTIFICACION_CHAT_MENSAJE:
                String tituloChat= extras.getString("titulo");
                String mensajeChat= extras.getString("mensaje");
                Log.d(TAG, "tituloChat : " + tituloChat +
                        "mensajeChat : " + mensajeChat
                );
                if(view!=null)view.mostrarNotificacion(tituloChat,mensajeChat);
                Log.d(TAG, " Constantes.TIPO_NOTIFICACION_CHAT_MENSAJE");
                break;
            default:
                break;
        }

        Log.d(TAG, "tipoNotificacion : " + tipoNotificacion);
    }



    ArrayList<String> listIdRubros;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);


    }



    @Override
    public void onStart() {
        super.onStart();

        validarEstadoActivities(extras);
    }

    private void validarEstadoActivities(Bundle extras) {
        if (extras == null) return;
        String tipoEstado = extras.getString("estado");
        if (tipoEstado == null) return;
        switch (tipoEstado) {
            case EstudioPerfilPresenterImpl.ACTUALIZACION_CORRECTAMENTE_PERFIL_ESPECIALISTA:
                Log.d(TAG, "CREADO_CORRECTAMENTE_PROPUESTA");
                if (view != null) view.mostrarMensaje("Datos Sincronizados Correctamente");
                break;

        }
    }
}
