package com.application.boxmadikv1.seleccionUsuario;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.api.response.ListaRubrosEspecResponse;
import com.application.boxmadikv1.api.response.especialista.ListaZonaTrabajoResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;
import com.application.boxmadikv1.login.useCase.GuardarUsuariosOnline;
import com.application.boxmadikv1.seleccionUsuario.UseCase.ActualizacionEstadoOnline;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeleccionUserPresenterImpl extends BaseActivityPresenterImpl<SeleccionUserView> implements SeleccionUserPresenter {


    public static final String TAG = SeleccionUserPresenterImpl.class.getSimpleName();

    private ActualizacionEstadoOnline actualizacionEstadoOnline;

    public SeleccionUserPresenterImpl(UseCaseHandler handler, Resources res, ActualizacionEstadoOnline actualizacionEstadoOnline) {
        super(handler, res);
        tipoUsuario = TipoUsuario.TIPO_USUARIO_DEFAULT;
        this.actualizacionEstadoOnline = actualizacionEstadoOnline;
    }

    @Override
    protected String getTag() {
        return TAG;
    }


    @Override
    public void onBackPressed() {

    }

    private enum TipoUsuario {TIPO_USUARIO_CLIENTE, TIPO_USUARIO_ESPECIALISTA, TIPO_USUARIO_DEFAULT}

    TipoUsuario tipoUsuario;

    @Override
    public void onClickCliente(String tipoCliente) {
        String tipoClienteUsuario = "1";
        if (tipoCliente == null || tipoCliente.isEmpty()) {
            initRetrofitActualizarDataUsuarioCliente(codigoUsuario, paisCodigo, tipoClienteUsuario);
            actualizarEstadoOnline(codigoUsuario, "1", Constantes.PAIS_CODIGO_PERU);
            return;
        }
        if (tipoCliente.equals("0")) {
            Log.d(TAG, "Agregar Preferencias tipo = 1");
            initRetrofitActualizarDataUsuarioCliente(codigoUsuario, paisCodigo, tipoClienteUsuario);
            actualizarEstadoOnline(codigoUsuario, "1", Constantes.PAIS_CODIGO_PERU);
            return;
        }
        if (tipoCliente.equals("1"))/*Cliente*/ {
            if (view != null) {
                view.startActivityMenuCliente();
                actualizarEstadoOnline(codigoUsuario, "1", Constantes.PAIS_CODIGO_PERU);
            }
        } else if (tipoCliente.equals("2")) {
            if (view != null) {
                view.startActivityMenuCliente();
                view.mostrarCheckCliente();
                actualizarEstadoOnline(codigoUsuario, "1", Constantes.PAIS_CODIGO_PERU);
            }

        }
    }

    Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initRetrofitNotiCountEspecialista(String tipoNotiEspec) {
//        Log.d(TAG,"paisCodigo : " +paisCodigo+
//                "codigoUsuario : " +codigoUsuario+
//                "tipoNotiCliente : " +tipoNotiEspec+
//                "GRUPO_NOTIFICACION_CLIENTE : " +Constantes.GRUPO_NOTIFICACION_CLIENTE
//        );

        loginService.obtenerConteoNotificacion(paisCodigo,
                // Constantes.GRUPO_NOTIFICACION_ESPECIALISTA,
                Constantes.GRUPO_NOTIFICACION_CLIENTE,
                codigoUsuario, tipoNotiEspec).
                enqueue(new Callback<DefaultResponseEstados>() {
                    @Override
                    public void onResponse(Call<DefaultResponseEstados> call, Response<DefaultResponseEstados> response) {
                        DefaultResponseEstados defaultResponse = response.body();
                       /* if (defaultResponse == null) {
                            String mensaje = "Ocurrio Algun Error initRetrofitNotiCountEspecialista";
                            Log.d(TAG, "mensaje : " + mensaje);
                            return;
                        }*/
                        if (response.isSuccessful()) {
                            if (defaultResponse != null) {
                                if (defaultResponse.getError()) {
                                    String mensaje = "Ocurrio Algun Error initRetrofitNotiCountEspecialista";
                                    if (view != null) view.mostrarConteoEspecialista("0");
                                    //     Log.d(TAG, "mensaje : " + mensaje);
                                } else {
                                    //   Log.d(TAG, "SE REGISTRARON CORRECTAMENTE  initRetrofitNotiCountEspecialista: " + defaultResponse.getEstado());
                                    if (view != null)
                                        view.mostrarConteoEspecialista(defaultResponse.getEstado());
                                }
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<DefaultResponseEstados> call, Throwable t) {
                        String mensaje = "Ocurrio Algun Error";
                        // Log.d(TAG, "onFailure : " + t.getMessage().toString());
                    }
                });


    }

    private void initRetrofitNotiCountCliente(String tipoNotiCliente) {
        Log.d(TAG, "paisCodigo : " + paisCodigo +
                "codigoUsuario : " + codigoUsuario +
                "tipoNotiCliente : " + tipoNotiCliente +
                "GRUPO_NOTIFICACION_CLIENTE : " + Constantes.GRUPO_NOTIFICACION_ESPECIALISTA
        );
        loginService.obtenerConteoNotificacion(paisCodigo,
                Constantes.GRUPO_NOTIFICACION_ESPECIALISTA,
                codigoUsuario,
                tipoNotiCliente).
                enqueue(new Callback<DefaultResponseEstados>() {
                    @Override
                    public void onResponse(Call<DefaultResponseEstados> call, Response<DefaultResponseEstados> response) {
                        DefaultResponseEstados defaultResponse = response.body();
                        if (defaultResponse == null) {
                            String mensaje = "Ocurrio Algun Error initRetrofitNotiCountCliente";
                            // Log.d(TAG, "mensaje : " + mensaje);
                            return;
                        }
                        if (defaultResponse != null) {
                            if (defaultResponse.getError()) {
                                String mensaje = "Ocurrio Algun Error initRetrofitNotiCountCliente";
                                if (view != null) view.mostrarConteoCliente("0");
                                Log.d(TAG, "mensaje : " + mensaje);
                            } else {

                                if (view != null)
                                    view.mostrarConteoCliente(defaultResponse.getEstado());
                                //  Log.d(TAG, "SE REGISTRARON CORRECTAMENTE initRetrofitNotiCountCliente : " + defaultResponse.getEstado());

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponseEstados> call, Throwable t) {
                        // String mensaje = "Ocurrio Algun Error";
                        // Log.d(TAG, "onFailure : " + t.getMessage().toString());
                    }
                });
    }

    private void initRetrofitActualizarDataUsuarioCliente(String usuarioCodigo, String paisCodigo, final String tipoUsuarios) {
        Call<DefaultResponse> call = loginService.actualizarTipoUsuario(usuarioCodigo, paisCodigo, tipoUsuarios);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    if (view != null) {
                        String mensaje = "Error con nuestros servidores";
                        view.mostrarMensaje(mensaje);
                    }
                    return;
                } else {
                    if (defaultResponse.getError()) {
                        if (view != null) {
                            view.mostrarMensaje(defaultResponse.getMessage());
                        }
                        return;
                    } else {
                        if (view != null) view.startMenuCliente(tipoUsuarios);
                        // Log.d(TAG, "CUANDO ACTUALIZO CORRECTAMENTE");
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClickEspecialista() {
      /*  tipoUsuario = TipoUsuario.TIPO_USUARIO_ESPECIALISTA;
        if (view != null) view.mostrarCheckEspecialista();
        if (view != null) view.startMenuEspecialista(arrayList);*/
    }

    @Override
    public void onClickSiguiente() {
//        initRetrofitValidarUsuarioRubro(codigoUsuario);

        //Log.d(TAG, "onClickSiguiente");
        /*switch (tipoUsuario) {
            case TIPO_USUARIO_CLIENTE:
                Log.d(TAG, "TIPO_USUARIO_CLIENTE");
                if (view != null) view.startMenuCliente(codeUsuario);
                //if(view!=null)view.mostrarMensaje("TIPO_USUARIO_CLIENTE");
                break;
            case TIPO_USUARIO_ESPECIALISTA:
                Log.d(TAG, "TIPO_USUARIO_ESPECIALISTA");
                if (view != null) view.startMenuEspecialista(codeUsuario);
                //if(view!=null)view.mostrarMensaje("TIPO_USUARIO_ESPECIALISTA");
                break;
            default:
                if (view != null) view.mostrarMensaje("Selecciona un Tipo Usuario");
                Log.d(TAG, "default");
                break;
        }*/



    }

    public void habilitarButtonEspec() {
        if (view != null) {
            view.habilitarButtonEspec();
        }
    }

    private void initRetrofitValidarUsuarioRubro(final String codigoUsuario, final TinyDB tinyDB, final String departamentoId, final String tipoClienteEspec) {

        loginService.validarUsuRubroExiste(codigoUsuario).enqueue(new Callback<DefaultResponseEstados>() {
            @Override
            public void onResponse(Call<DefaultResponseEstados> call, Response<DefaultResponseEstados> response) {
                DefaultResponseEstados defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "initRetrofitValidarUsuarioRubro Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    habilitarButtonEspec();
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        String mensaje = "initRetrofitValidarUsuarioRubro defaultResponse != null error";
                        Log.d(TAG, "mensaje : " + mensaje);
                        habilitarButtonEspec();
                    } else {
                        Log.d(TAG, "SE REGISTRARON CORRECTAMENTE");
                        //  String tipoClienteEspec = "2";
                        switch (defaultResponse.getEstado()) {
                            case DefaultResponseEstados.RUBRO_USUARIO_EXISTE:
                                Log.d(TAG, "RUBRO_USUARIO_EXISTE : ");
                                if (view != null) {
                                    // view.startMenuEspecialista(null);
                                    view.mostrarCheckEspecialista();
                                }
                                initRetrofitUsuRubroPreferences(departamentoId, tinyDB);
                                break;
                            case DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE:
                                validarDatoNoExiste(codigoUsuario, tinyDB, departamentoId, tipoClienteEspec);
                                Log.d(TAG, "RUBRO_USUARIO_NO_EXISTE : ");
                                break;
                        }
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

    private void validarDatoNoExiste(String codigoUsuario, TinyDB tinydb, String departamentoId, String tipoClienteEspec) {
        ArrayList<String> arrayList = tinydb.getListString("mylist");
        if (arrayList == null || arrayList.isEmpty()) {

            if (view != null) {
                view.startSeleccionRubros(tipoClienteEspec);
                view.mostrarCheckEspecialista();
                habilitarButtonEspec();
            }

            Log.d(TAG, "arrayList == null || arrayList.isEmpty()");
            return;
        }
        if (departamentoId == null || departamentoId.isEmpty() || departamentoId.equals("null")) {
            String tipoInicial = "0";
            if (view != null) view.startActivityDistritoTrabajo(tipoInicial);
            Log.d(TAG, "departamentoId == null || departamentoId.isEmpty() || departamentoId.equals(\"null\")");
            habilitarButtonEspec();
            return;
        }
    }

    private void initRetrofitUsuRubroPreferences(final String departamentoId, final TinyDB tinydb) {

        loginService.mostrarListaRubrosEspec(codigoUsuario, paisCodigo).enqueue(new Callback<ListaRubrosEspecResponse>() {
            @Override
            public void onResponse(Call<ListaRubrosEspecResponse> call, Response<ListaRubrosEspecResponse> response) {
                ListaRubrosEspecResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "mostrarListaRubrosEspec Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    habilitarButtonEspec();
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        String mensaje = "mostrarListaRubrosEspec defaultResponse != null";
                        Log.d(TAG, "mensaje : " + mensaje);
                        habilitarButtonEspec();
                    } else {
                        Log.d(TAG, "SE TRAJERON LOS RUBROS CORRECTAMENTE");
                        List<ListaRubrosEspecResponse.ListaRubrosEspec> listaRubrosEspec = defaultResponse.getListaRubros();
                        ArrayList<String> stringListIdRubros = new ArrayList<>();
                        for (int i = 0; i < listaRubrosEspec.size(); i++) {
                            ListaRubrosEspecResponse.ListaRubrosEspec rubrosEspec = listaRubrosEspec.get(i);
                            stringListIdRubros.add(rubrosEspec.getRubro_Rub_Codigo());
                        }
                        if (view != null)
                            view.onGuardarDataEnPreferencesListaRubros(stringListIdRubros);
                        initRetrofitDepartamento(departamentoId);

                    }
                }
            }

            @Override
            public void onFailure(Call<ListaRubrosEspecResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                habilitarButtonEspec();
                Log.d(TAG, "onFailure mostrarListaRubrosEspec : " + t.getMessage().toString());
            }
        });
    }

    private void initRetrofitDepartamento(final String departamentoId) {

        Call<ListaZonaTrabajoResponse> call = loginService.mostrarListaZonaTrabajo(codigoUsuario, paisCodigo);
        call.enqueue(new Callback<ListaZonaTrabajoResponse>() {
            @Override
            public void onResponse(Call<ListaZonaTrabajoResponse> call, Response<ListaZonaTrabajoResponse> response) {
                if (response == null) {
                    String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                    //if (view != null) view.mostrarMensaje(mensajeError);
                    Log.d(TAG, "initRetrofitDepartamento == null : " + mensajeError);
                    habilitarButtonEspec();
                    return;
                }
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ListaZonaTrabajoResponse mostrarPerfilResponse = response.body();
                        if (mostrarPerfilResponse == null) {
                            String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                            Log.d(TAG, "mensajeError  initRetrofitDepartamento: " + mensajeError);
                            habilitarButtonEspec();
                            return;
                        } else {
                            if (mostrarPerfilResponse.getError()) {
                                if (departamentoId == null) {
                                    String tipoInicial = "0";
                                    if (view != null)
                                        view.startActivityDistritoTrabajo(tipoInicial);
                                    habilitarButtonEspec();
                                    return;
                                }
                                //Log.d(TAG, "mensajeError else: " + mostrarPerfilResponse.getMensaje());
                                //    return;
                            } else {
                                List<ListaZonaTrabajoResponse.ListZonaTrabajoRes> listZonaTrabajoRes = mostrarPerfilResponse.getListZonaTrabajoRes();
                                if (listZonaTrabajoRes == null) {
                                    String mensajeError = "Lista Vacia";
                                    Log.d(TAG, "mensajeError listZonaTrabajoRes == null: " + mostrarPerfilResponse.getMensaje());
                                    if (view != null) view.mostrarMensaje(mensajeError);
                                    habilitarButtonEspec();
                                    return;
                                } else {
                                    String codigoDepartamento = listZonaTrabajoRes.get(0).getCodigoDepartamento();
                                    String nombreDepartamento = listZonaTrabajoRes.get(0).getNombreDepartamento();
                                    String codigoProvincia = listZonaTrabajoRes.get(0).getCodigoProvincia();
                                    String nombreProvincia = listZonaTrabajoRes.get(0).getNombreProvincia();
                                    if (view != null) {
                                        view.onGuardarDataEnPreferencesDepartamentoId(codigoDepartamento);
                                        view.startMenuEspecialista(null);
                                        habilitarButtonEspec();
                                    }

                                }
                            }

                        }


                    }
                }

            }

            @Override
            public void onFailure(Call<ListaZonaTrabajoResponse> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage());
                habilitarButtonEspec();
            }
        });
    }


    @Override
    public void onValidateSeleccionRubros(TinyDB tinydb, String tipoCliente, String departamentoId) {
        Log.d(TAG, "tipoCliente : " + tipoCliente);
        String tipoClienteEspec = "2";
        if (tipoCliente.equals("1") || tipoCliente.equals("0")) {
            initRetrofitActualizarDataUsuarioEspec(codigoUsuario, paisCodigo, tipoClienteEspec);
        }
        if (tipoCliente == null || tipoCliente.isEmpty() || tipoCliente.equals("null") || tipoCliente.equals("1")) {
            initRetrofitActualizarDataUsuarioEspec(codigoUsuario, paisCodigo, tipoClienteEspec);
        }
       // iniciarThreadValidarUsuarioRubro(codigoUsuario, tinydb, departamentoId, tipoClienteEspec);
        initRetrofitValidarUsuarioRubro(codigoUsuario, tinydb, departamentoId, tipoClienteEspec);
    }

    private void iniciarThreadValidarUsuarioRubro(final String codigoUsuario, final TinyDB tinydb, final String departamentoId, final String tipoClienteEspec) {
        new Thread(new Runnable() {
            public void run() {
                initRetrofitValidarUsuarioRubro(codigoUsuario, tinydb, departamentoId, tipoClienteEspec);
            }
        }).start();
    }


    private void initRetrofitActualizarDataUsuarioEspec(String codeUsuario, String paisCodigoPeru, final String tipoUsuario) {
        Call<DefaultResponse> call = loginService.actualizarTipoUsuario(codeUsuario, paisCodigoPeru, tipoUsuario);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    Log.d(TAG, "Error con nuestros servidores");
                } else {
                    if (defaultResponse.getError()) {
                        Log.d(TAG, "Error: " + defaultResponse.getMessage());
                    } else {
                        Log.d(TAG, "CUANDO ACTUALIZO CORRECTAMENTE");
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d(TAG, "onFailureActualizarDatosUsuario:" + t.getLocalizedMessage());
            }
        });
    }

    String codigoUsuario, paisCodigo;

    @Override
    public void onDatosPreferencias(String codigoUsuario, String paisCodigo, String tokenUser) {
        this.codigoUsuario = codigoUsuario;
        this.paisCodigo = paisCodigo;
        validacionTokenPreferences(tokenUser);
        initRetrofitNotiCountCliente("notiCliente");
        initRetrofitNotiCountEspecialista("notiEspecialista");

    }

    private void validacionTokenPreferences(String tokenUser) {
        if (tokenUser == null) {
            initRetrofitActualizarDataToken(tokenUser);
        } else {
            Log.d(TAG, "no hacer  nada");
        }
    }


    @Override
    public void onLogoutUser() {
        //actualizarEstadoOnline(codigoUsuario, "0", Constantes.PAIS_CODIGO_PERU);
        handler.execute(actualizacionEstadoOnline, new GuardarUsuariosOnline.RequestValues(codigoUsuario, "0", Constantes.PAIS_CODIGO_PERU),
                new UseCase.UseCaseCallback<GuardarUsuariosOnline.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarUsuariosOnline.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();
                        if (defaultResponse == null) {
                            String mensaje = "Ocurrio Algun Error";
                            if (view != null) {
                                // view.ocultarDialog();
                                view.mostrarMensaje(mensaje);
                            }
                            return;
                        }
                        if (defaultResponse != null) {
                            if (defaultResponse.getError()) {
                                String mensaje = "Ocurrio Algun Error";
                                if (view != null) {
                                    // view.ocultarDialog();
                                    view.mostrarMensaje(mensaje);
                                }
                                return;
                            } else {
                                if (view != null) {
                                    ///  view.ocultarDialog();
                                    view.limpiarPreferencias();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void actualizarEstadoOnline(String codigoUsuario, String tipoEstado, String paisCodigo) {
        handler.execute(actualizacionEstadoOnline, new GuardarUsuariosOnline.RequestValues(codigoUsuario, tipoEstado, paisCodigo),
                new UseCase.UseCaseCallback<GuardarUsuariosOnline.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarUsuariosOnline.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();
                        if (defaultResponse == null) {
                            String mensaje = "Ocurrio Algun Error";
                            if (view != null) {
                                // view.ocultarDialog();
                                view.mostrarMensaje(mensaje);
                            }
                            return;
                        }
                        if (defaultResponse != null) {
                            if (defaultResponse.getError()) {
                                /*String mensaje = "Ocurrio Algun Error";
                                if (view != null) {
                                    // view.ocultarDialog();
                                    view.mostrarMensaje(mensaje);
                                }*/
                                return;
                            } else {
                                /*if (view != null) {
                                    ///  view.ocultarDialog();
                                    view.limpiarPreferencias();
                                }*/
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    String tokenUser;

    @Override
    public void onRegistroToken(String newToken) {
        this.tokenUser = newToken;
        initRetrofitActualizarDataToken(newToken);
    }

    @Override
    public void onClickNotificacionCliente() {
        String tipoNotificacion = "notiCliente";
        if (view != null)
            view.initStartActivityNotificacionCliente(codigoUsuario, paisCodigo, tipoNotificacion);
    }

    @Override
    public void onClickNotificacionEspecialista() {
        String tipoNotificacion = "notiEspec";
        if (view != null)
            view.initStartActivityNotificacionEspecialista(codigoUsuario, paisCodigo, tipoNotificacion);
    }

    private void initRetrofitActualizarDataToken(final String newToken) {

        Call<DefaultResponse> call = loginService.actualizarTokenUser(codigoUsuario, paisCodigo, newToken);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    if (view != null) {
                        String mensaje = "Error con nuestros servidores";
                        //view.mostrarMensaje(mensaje);
                    }
                    return;
                } else {
                    if (defaultResponse.getError()) {
                        if (view != null) {
                            /// view.mostrarMensaje(defaultResponse.getMessage());
                        }
                        return;
                    } else {
                        if (view != null) {
                            view.actualizarToken(newToken);
                        }
                        Log.d(TAG, "CUANDO ACTUALIZO CORRECTAMENTE");
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

   /* @Override
    public void onStartMenuClienteValidar(String tipoCliente) {
        if (tipoCliente == null) {
            Log.d(TAG, "Agregar Preferencias tipo = 1");
            return;

        }
        if (tipoCliente.equals("1")) {
            Log.d(TAG, "Inicia Normal ");
            return;
        } else if (tipoCliente.equals("2")) {
            Log.d(TAG, "Inicia Normal ");
            return;
        }

    }*/

  /*  @Override
    public void onStartMenuEspecialistaValidar(String tipoCliente) {
        if (tipoCliente == null) {
            Log.d(TAG, "Agregar Preferencias tipo = 2");
            return;

        }
        if (tipoCliente.equals("1")) {
            Log.d(TAG, "Agregar Preferencias tipo = 2 ");
            return;
        } else if (tipoCliente.equals("2")) {
            Log.d(TAG, "Inicia Normal ");
            return;
        }
    }*/


}
