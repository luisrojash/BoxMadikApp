package com.application.boxmadikv1.login;

import android.accounts.Account;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.login.useCase.GuardarUsuario;
import com.application.boxmadikv1.login.useCase.GuardarUsuariosOnline;
import com.application.boxmadikv1.login.useCase.Login;
import com.application.boxmadikv1.login.useCase.UsuariosOnline;
import com.application.boxmadikv1.modelo.Usuario;
import com.application.boxmadikv1.registraUser.RegistrarUsuarioPresenterImpl;
import com.application.boxmadikv1.utils.Constantes;

public class LoginPresenterImpl extends BaseActivityPresenterImpl<LoginView> implements LoginPresenter {

    public static final String TAG = LoginPresenterImpl.class.getSimpleName();

    private Login login;
    private GuardarUsuario guardarUsuario;
    //private GuardarUsuariosOnline guardarUsuariosOnline;
    private UsuariosOnline guardarUsuariosOnline;

    public LoginPresenterImpl(UseCaseHandler handler, Resources res, Login login, GuardarUsuario guardarUsuario, UsuariosOnline guardarUsuariosOnline) {
        super(handler, res);
        this.login = login;
        this.guardarUsuario = guardarUsuario;
        this.guardarUsuariosOnline = guardarUsuariosOnline;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClickLogin(String usuario, String clave) {
        Constantes constantes = new Constantes();
        if (usuario.isEmpty() && clave.isEmpty()) {
            if (view != null) view.mostrarMensaje("Complete los Datos");
        } else if (usuario.isEmpty()) {
            if (view != null) view.mostrarErrorUsuarioCampoVacio("Ingrese Correo *");
        } else if (!constantes.validarEmail(usuario)) {
            if (view != null) view.mostrarErrorUsuarioCampoVacio("Ingrese un Correo Correcto *");
        } else if (clave.isEmpty()) {
            if (view != null) view.mostrarErrorClaveCampoVacio("Ingrese Clave *");
        } else {
            initUseCaseLogin(usuario, clave);
        }
    }

    @Override
    public void validarConexion(Boolean estado) {
        if (estado) {
            if (view != null) view.isConectadoInternet();
            Log.d(TAG, "CONECTADO A INTERNET");
        } else {
            if (view != null) view.isDesconectadoInternet();
            Log.d(TAG, "DESCONECTADO A INTERNET");
        }
    }

    private void initUseCaseLogin(String usuario, String clave) {
        if (view != null) view.mostrarDialog();
        handler.execute(login, new Login.RequestValues(usuario, clave),
                new UseCase.UseCaseCallback<Login.ResponseValue>() {
                    @Override
                    public void onSuccess(Login.ResponseValue response) {
                        LoginResponse loginResponse = response.getResultado();
                        if (loginResponse == null) {
                            String mensaje = "Problemas con la conexion , Intentelo mas Tarde";
                            view.mostrarMensaje(mensaje);
                            view.ocultarDialog();
                        } else {
                            if (!loginResponse.getError()) {
                                Log.d(TAG, "onSuccess : " + loginResponse.getMensaje() +
                                        " / onSuccess : " + loginResponse.getUsuario().getUsu_Nom1() +
                                        "/ codeUsuario " + loginResponse.getUsuario().getUsu_Codigo());

                                //initUseCaseGuardarUsuario(loginResponse.getUsuario());

                                String usuarioCodigo = String.valueOf(loginResponse.getUsuario().getUsu_Codigo());
                                String usuarioDni = loginResponse.getUsuario().getUsu_DNI();
                               // String usuNombre = loginResponse.getUsuario().getUsu_Nom1();  obtenerNombre
                                String usuNombre = obtenerNombre(loginResponse);
                                String usuaApellidos = loginResponse.getUsuario().getUsu_Ape_Pat_Mate();
                                String usuCelular = loginResponse.getUsuario().getUsu_Celular();
                                String usuEmail = loginResponse.getUsuario().getUsu_Email();
                                String usuFoto = loginResponse.getUsuario().getUsu_foto();
                                String codigoPais = loginResponse.getUsuario().getPais_Pais_Codigo();
                                String tipoDocumento = loginResponse.getUsuario().getTDoc_Codigo();
                                Log.d(TAG, "onSuccess" + codigoPais);
                                initUseCaseUsuaConectados(usuarioCodigo, usuarioDni, usuNombre,
                                        usuaApellidos, usuCelular, usuEmail, usuFoto, codigoPais, tipoDocumento);
                                /*if (view != null) {
                                    view.ocultarDialog();
                                    view.startActivitySeleccionUser(usuarioCodigo, usuarioDni, usuNombre,
                                            usuaApellidos,usuCelular,usuEmail,usuFoto,codigoPais,tipoDocumento );
                                }*/

                            } else {
                                if (view != null) {
                                    view.mostrarMensaje(loginResponse.getMensaje());
                                    view.ocultarDialog();
                                }
                            }
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private String obtenerNombre(LoginResponse loginResponse) {
        String nombreUsuario = "";
        if (loginResponse.getUsuario().getUsu_Nom1() == null) {
            nombreUsuario = loginResponse.getUsuario().getUsu_razon_social();
        } else {
            nombreUsuario = loginResponse.getUsuario().getUsu_Nom1();
        }
        return nombreUsuario;
    }

    private void initUseCaseUsuaConectados(final String usuarioCodigo, final String usuarioDni, final String usuNombre, final String usuaApellidos, final String usuCelular, final String usuEmail, final String usuFoto, final String codigoPais, final String tipoDocumento) {
        Log.d(TAG, "paisCodigo : " + codigoPais);
        handler.execute(guardarUsuariosOnline, new GuardarUsuariosOnline.RequestValues(usuarioCodigo, "1", codigoPais),
                new UseCase.UseCaseCallback<GuardarUsuariosOnline.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarUsuariosOnline.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();
                        if (defaultResponse == null) {
                            String mensaje = "Ocurrio Algun Error";
                            if (view != null) {
                                view.ocultarDialog();
                                view.mostrarMensaje(mensaje);
                            }
                            return;
                        }
                        if (defaultResponse != null) {
                            if (defaultResponse.getError()) {
                                String mensaje = "Ocurrio Algun Error";
                                if (view != null) {
                                    view.ocultarDialog();
                                    view.mostrarMensaje(mensaje);
                                }
                                return;
                            } else {
                                if (view != null) {
                                    view.ocultarDialog();
                                    view.startActivitySeleccionUser(usuarioCodigo, usuarioDni, usuNombre,
                                            usuaApellidos, usuCelular, usuEmail, usuFoto, codigoPais, tipoDocumento);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }


    private void initUseCaseGuardarUsuario(LoginResponse.UsuarioResponse usuario) {
        handler.execute(guardarUsuario, new GuardarUsuario.RequestValues(usuario),
                new UseCase.UseCaseCallback<GuardarUsuario.ResponsValue>() {
                    @Override
                    public void onSuccess(GuardarUsuario.ResponsValue response) {
                        if (response.isaBoolean()) {
                            if (view != null) {
                                view.ocultarDialog();
                                // view.startActivitySeleccionUser(codeUsuario, nombre, email);
                            }
                        } else {
                            Log.d(TAG, "Ocurrio Algun Problema con nuestros servidores");
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        validarEstadoActivities(extras);
    }

    private void validarEstadoActivities(Bundle extras) {
        if (extras == null) return;
        String tipoEstado = extras.getString("estado");
        if (tipoEstado == null) return;
        switch (tipoEstado) {
            case RegistrarUsuarioPresenterImpl.REGISTRO_USUARIO_CORRECTAMENTE:
                Log.d(TAG, "CREADO_CORRECTAMENTE_PROPUESTA");
                if (view != null) view.mostrarMensajeToast("Usuario Creado Correctamente");
                break;
        }
    }
}
