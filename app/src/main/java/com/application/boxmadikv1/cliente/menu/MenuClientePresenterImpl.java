package com.application.boxmadikv1.cliente.menu;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clienteCancelado.ClienteCanceladoFragment;
import com.application.boxmadikv1.cliente.menu.clienteFinalizado.ClienteFinalizadoFragment;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.ClientePerfilDireccionPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clienteProceso.ClienteProcesoFragment;
import com.application.boxmadikv1.cliente.menu.clientePendiente.ClientePendienteFragment;
import com.application.boxmadikv1.cliente.menu.clienteTodos.ClienteTodosFragment;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.DetallesPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.DetallesCotizacionPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.DesembolsarPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.ReportePagoPresenterImpl;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuClientePresenterImpl extends BaseActivityPresenterImpl<MenuClienteView> implements MenuClientePresenter {


    public static final String TAG = MenuClientePresenterImpl.class.getSimpleName();

    public MenuClientePresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (view != null) view.obteniendoKeyUser();
        //  iniciarFragmentos();
    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }


    private Class<? extends Fragment> fragmentClassVisible;

    @Override
    public void onPageChanged(Class<? extends Fragment> fragmentClass) {
        if (fragmentClass == null) return;
        fragmentClassVisible = fragmentClass;
        if (fragmentClass.equals(ClientePendienteFragment.class)) {
            mostrarFab();
            ocultarBuscador();
            return;
        }

        if (fragmentClass.equals(ClienteProcesoFragment.class)) {
            mostrarFab();
            ocultarBuscador();
            return;
        }
        if (fragmentClass.equals(ClienteFinalizadoFragment.class)) {
            mostrarFab();
            ocultarBuscador();
            return;
        }
        if (fragmentClass.equals(ClienteCanceladoFragment.class)) {
            mostrarFab();
            ocultarBuscador();
            return;
        }
        if (fragmentClass.equals(ClienteTodosFragment.class)) {
            mostrarFab();
            mostrarBuscador();
            return;
        }


    }

    private void mostrarBuscador() {
        if (view != null) view.mostrarBuscador();
    }

    private void ocultarBuscador() {
        if (view != null) view.ocultarBuscador();
    }

    private void ocultarFab() {
        if (view != null) view.ocultarFab();
    }

    private void mostrarFab() {
        if (view != null) view.mostrarFab();
    }

    private boolean aBooleanClickBotonMenu = true;

    @Override
    public void onFabClicked(int pagePosition) {
        if (aBooleanClickBotonMenu) {
            aBooleanClickBotonMenu = false;
            if (view != null) view.mostrarContenidoPagerTransparente();
        } else {
            if (view != null) view.ocultarContenidoPagerTransparente();
        }
    }

    String keyUser;
    String paisCodigo;

    @Override
    public void onInitKeyUser(String keyUser, String paisCodigo) {
        this.keyUser = keyUser;
        this.paisCodigo = paisCodigo;
        Log.d(TAG, "onInitKeyUser : " + keyUser);
        if (view != null) view.mostrarFragmentos(keyUser, paisCodigo);
    }

    @Override
    public void OnClickCrearPropusta() {
        if (view != null) view.mostrarProgressBar();
        initValidateDatosUsuarioDireccion(keyUser, paisCodigo);
        //  if (view != null) view.starActivityRubros();
    }

    private void initValidateDatosUsuarioDireccion(String keyUser, String paisCodigo) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        loginService.validarUsuDireccion(keyUser, paisCodigo).enqueue(new Callback<DefaultResponseEstados>() {

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
                            if (view != null) view.starActivityRubros();
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
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    @Override
    public void OnClickVerPerfil() {
        if (view != null) view.starActivityPerfil();
    }

    @Override
    public void initStartActivitySeleccionUser() {
        if (view != null) view.initStartActivitySeleccionUser();
    }

    @Override
    public void onRealTipoEstado(Bundle extras) {
        String tipoNotificacion = extras.getString("tipoNotificacion");
        switch (tipoNotificacion) {
            case Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK:
                Log.d(TAG, "Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA:
                Log.d(TAG, "Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION:
                Log.d(TAG, "Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION");
                break;
            case Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION:
                Log.d(TAG, "Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION:
                if(view!=null)view.actualizarFragmentoCotizacionPendiente();
                Log.d(TAG, "Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION");
                break;
            case Constantes.TIPO_NOTIFICACION_CHAT_MENSAJE:
                String tituloChat = extras.getString("titulo");
                String mensajeChat = extras.getString("mensaje");
                Log.d(TAG, "tituloChat : " + tituloChat +
                        "mensajeChat : " + mensajeChat);
                if (view != null) view.mostrarNotificacion(tituloChat, mensajeChat);
                Log.d(TAG, " Constantes.TIPO_NOTIFICACION_CHAT_MENSAJE");
                break;
            default:
                break;
        }
    }

    //String codeUsuario;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);

    }

    @Override
    public void onStart() {
        super.onStart();
        validarEstadoActivities(extras);
    }

    @Override
    public void onStop() {
        super.onStop();
        extras = null;
    }

    private void validarEstadoActivities(Bundle extras) {
        if (extras == null) return;
        String tipoEstado = extras.getString("estado");
        if (tipoEstado == null) return;
        // codeUsuario = extras.getString("codeUsuario");

        switch (tipoEstado) {
            case DetallesPresenterImpl.CREADO_CORRECTAMENTE_PROPUESTA:
                Log.d(TAG, "CREADO_CORRECTAMENTE_PROPUESTA");
                if (view != null) view.mostrarMensaje("Propuesta Creada Correctamente");
                break;
            case ReportePagoPresenterImpl.CREADO_CORRECTAMENTE_REPORTE_PAGO:
                Log.d(TAG, "CREADO_CORRECTAMENTE_REPORTE_PAGO");
                if (view != null)
                    view.mostrarMensaje("Cotizacion Aceptada, Su propuesta Cambio de estado");
                break;
            case DesembolsarPresenterImpl.CREADO_CORRECTAMENTE_DEMSEMBOLSO_FINALIZADO:
                Log.d(TAG, "CREADO_CORRECTAMENTE_REPORTE_PAGO");
                if (view != null)
                    view.mostrarMensaje("Cotizacion Aceptada, Su propuesta Cambio de estado");
                break;
            case DetallesCotizacionPresenterImpl.ELIMINADO_CORRECTAMENTE_PROPUESTA:
                if (view != null) view.mostrarMensaje("Propuesta eliminada Correctamente");
                break;
            case ClientePerfilDireccionPresenterImpl.EDIT_PERFIL_CORRECTAMENTE_USUARIO:
                if (view != null) view.mostrarMensaje("Perfil Actualizado");
                break;
        }

    }
}
