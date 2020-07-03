package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.culqui.CargoResponse;
import com.application.boxmadikv1.api.culqui.IntegracionResponse;
import com.application.boxmadikv1.api.culqui.entidad.Objeto;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.useCase.CrearPago;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.useCase.RegistroToken;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.useCase.NotificacionCotiAceptada;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CulquiPresenterImpl extends BaseActivityPresenterImpl<CulquiView> implements CulquiPresenter {

    public static final String TAG = CulquiPresenterImpl.class.getSimpleName();

    private RegistroToken registroToken;
    private CrearPago crearPago;

    public CulquiPresenterImpl(UseCaseHandler handler, Resources res, RegistroToken registroToken, CrearPago crearPago) {
        super(handler, res);
        this.registroToken = registroToken;
        this.crearPago = crearPago;
    }


    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        this.cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (view != null) {
            view.mostrarDataInicial(detallesCotizacionUi, cotizacionesUi);
            view.habilitarCulquiPagoBasico();
        }
        Log.d(TAG, "motoPago " + cotizacionesUi.getMonto());

    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    String email;

    @Override
    public void onClickSiguiente(String numberCard, String numberCvv, String email, String anio, String mes) {
        if (view != null) view.deshabilitartBotonPago();
        if (numberCard.isEmpty() || numberCard == null) {
            String mensaje = "Ingrese N° Tarjeta";
            if (view != null) {
                view.mostraEditTextTarjetaError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }
        if (numberCard.contains(" ")) {
            String mensaje = "No se permiten espacios *";
            if (view != null) {
                view.mostraEditTextTarjetaError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }


        if (anio.isEmpty() || anio == null) {
            String mensaje = "Ingrese Año";
            if (view != null) {
                view.mostraEditTextAnioError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }
        if (anio.length() < 4) {
            String mensaje = "Coloque el Año Correcto *";
            if (view != null) {
                view.mostraEditTextAnioError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }
        if (anio.contains(" ")) {
            String mensaje = "No se permiten espacios *";
            if (view != null) {
                view.mostraEditTextAnioError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }

        if (mes.isEmpty() || mes == null) {
            String mensaje = "Ingrese Mes";
            if (view != null) {
                view.mostraEditTextMesError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }

        if (mes.contains(" ")) {
            String mensaje = "No se permiten espacios *";
            if (view != null) {
                view.mostraEditTextMesError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }
        if (numberCvv.isEmpty() || numberCvv == null) {
            String mensaje = "Ingrese CVV";
            if (view != null) {
                view.mostraEditTextCvvError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }
        if (numberCvv.contains(" ")) {
            String mensaje = "No se permiten espacios *";
            if (view != null) {
                view.mostraEditTextCvvError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }
        if (email.isEmpty() || email == null) {
            String mensaje = "Ingrese Correo";
            if (view != null) {
                view.mostraEditTextEmailError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }
        if (email.contains(" ")) {
            String mensaje = "No se permiten espacios *";
            if (view != null) {
                view.mostraEditTextEmailError(mensaje);
                view.habilitarBotonPago();
            }
            return;
        }
        this.email = email;
       initRetrofitActulizarPropuestaCoti();

        /*JSONObject jsonBody = new JSONObject();
        int anioInt = Integer.parseInt(anio);
        int mesInt = Integer.parseInt(mes);
        try {
            jsonBody.put("card_number", numberCard);
            jsonBody.put("cvv", numberCvv);
            jsonBody.put("expiration_month", mesInt);
            jsonBody.put("expiration_year", anioInt);
            jsonBody.put("email", email);
        } catch (Exception ex) {
            Log.v("", "ERROR: " + ex.getMessage());
        }
        initUseCaseRegistroToken(jsonBody);*/
    }

    private void initUseCaseRegistroToken(JSONObject jsonBody) {
        if (view != null) view.mostrarDialogProgress();
        handler.execute(registroToken, new RegistroToken.RequestValues(jsonBody),
                new UseCase.UseCaseCallback<RegistroToken.ResponseValue>() {
                    @Override
                    public void onSuccess(RegistroToken.ResponseValue response) {
                      /*  switch (response.getErroResponse()){
                            case 200
                        }*/
                        IntegracionResponse cargoResponseResponse = response.getServerResponse();

                        String object = response.getServerResponse().getmObject();
                        String idToken = response.getServerResponse().getmId();
                        Log.d(TAG, "object : " + response.getErroResponse());

                        String string = cotizacionesUi.getMonto();
                        String[] parts = string.split("\\.");
                        String part1 = parts[0];
                        String part2 = parts[1];
                        Log.d(TAG, "motoPago " + cotizacionesUi.getMonto() +
                                "part1 " + part1 +
                                "part2 " + part2
                        );
                        String tipoMoneda = "PEN";
                        String montoSinComa = part1 + part2;
                        initUseCaseRegistroPago(montoSinComa,
                                tipoMoneda,
                                email,
                                idToken);

                        /*initRetrofitCrearCargoPago(montoSinComa,
                                tipoMoneda,
                                email,
                                idToken);*/
                    }

                    @Override
                    public void onError() {
                        if (view != null) {
                            view.ocultarDialogProgress();
                            view.mostrarMensaje("Error con Servidores Culqui- Token");
                            view.habilitarBotonPago();
                            Log.d(TAG, "Ocurrio Algun Problema Servicio -Al crear el Token Culqui");
                        }
                    }
                });
    }

    private void initUseCaseRegistroPago(String montoSinComa, String tipoMoneda, String email, String idToken) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("amount", montoSinComa);
            jsonBody.put("currency_code", tipoMoneda);
            jsonBody.put("email", email);
            jsonBody.put("source_id", idToken);
        } catch (Exception ex) {
            if (view != null) {
                view.mostrarMensaje(ex.getMessage().toString());
                view.habilitarBotonPago();
            }
            Log.v("", "ERROR: " + ex.getMessage());
            return;
        }
        handler.execute(crearPago, new CrearPago.RequestValues(jsonBody),
                new UseCase.UseCaseCallback<CrearPago.ResponseValue>() {
                    @Override
                    public void onSuccess(CrearPago.ResponseValue response) {
                        Response<CargoResponse> cargoResponseResponse = response.getResponse();
                        if (cargoResponseResponse.isSuccessful()) {//Body
                            CargoResponse cargoResponse = cargoResponseResponse.body();
                            if (cargoResponse != null) {
                                String object = cargoResponse.getmObject();
                                //ActualizaLosEstados

                                Log.d(TAG, "object : " + object);
                                //envioAceptarNotificacionCliente(detallesCotizacionUi, cotizacionesUi);
                                initRetrofitActulizarPropuestaCoti();
                            }
                        } else {//ErrorBody
                            int dataErrorBody = cargoResponseResponse.code();
                            Log.d(TAG, "dataErrorBody" + dataErrorBody);
                            JSONObject jsonObject;
                            String object, user_message = "";
                            try {
                                jsonObject = new JSONObject(cargoResponseResponse.errorBody().string());
                                object = jsonObject.getString("object");
                                user_message = jsonObject.getString("user_message");
                                Log.d(TAG, "object" + object +
                                        "user_message" + user_message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (view != null) {
                                view.ocultarDialogProgress();
                                view.mostrarMensaje(user_message);
                                view.habilitarBotonPago();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onError() {
                        if (view != null) {
                            view.ocultarDialogProgress();
                            view.mostrarMensaje("Error Registro Pago");
                            view.habilitarBotonPago();
                            Log.d(TAG, "Ocurrio Algun Problema Servicio -Al crear el Pago Culqui");
                        }
                    }
                });
    }


    private void envioAceptarNotificacionCliente(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Log.d(TAG, "envioAceptarNotificacionCliente : " +
                "NOTIFICACION_NOT_ESTADO_PENDIENTE : " + Constantes.NOTIFICACION_NOT_ESTADO_PENDIENTE +
                "TIPO_NOTIFICACION_ACEPTO_COTIZACION : " + Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION +
                "GRUPO_NOTIFICACION_CLIENTE : " + Constantes.GRUPO_NOTIFICACION_CLIENTE +
                "getIdPropuesta : " + detallesCotizacionUi.getIdPropuesta() +
                "getIdCotizacion : " + cotizacionesUi.getIdCotizacion() +
                "getPaisCodigo : " + detallesCotizacionUi.getPaisCodigo());
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = loginService.envioNotificacionCotizacionAceptada(
                Constantes.NOTIFICACION_NOT_ESTADO_PENDIENTE,
                Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION,
                Constantes.GRUPO_NOTIFICACION_CLIENTE,
                detallesCotizacionUi.getIdPropuesta(),
                cotizacionesUi.getIdCotizacion(),
                detallesCotizacionUi.getPaisCodigo());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        // defaultResponseCallBackResultado.onCallBackResultado(message);
                    } else {
                        //defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailureEnvio Notificacion : " + t.getMessage().toString());
            }
        });


    }

    @Override
    public void onClickDepositar() {
        String motoPago = cotizacionesUi.getMonto();
        Double aDouble = Double.valueOf(cotizacionesUi.getMonto());
        Float aFloat = Float.valueOf(cotizacionesUi.getMonto());
        String tipoMoneda = "PEN";
        String correo = cotizacionesUi.getUsuEmail();

        String string = cotizacionesUi.getMonto();
        String[] parts = string.split("\\.");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556*/

        Log.d(TAG, "motoPago " + cotizacionesUi.getMonto() +
                "part1 " + part1 +
                "part2 " + part2
        );
        if (idToken != null) {
            //initRetrofitCrearCargoPago(motoPago, tipoMoneda, correo, idToken); initRetrofitActulizarPropuestaCoti();
        }

    }

    @Override
    public void onPreferencesToken(String idToke) {
        this.idToken = idToke;
        /*if (idToken == null) {
            view.habilitarCulquiPagoBasico();
            return;
        }
        view.habilitarCulquiPagoBasico();*/
        Log.d(TAG, "onPreferencesToken : " + idToke);
        // initRetrofitConsultarToken(idToken);
    }

    private void initRetrofitActulizarPropuestaCoti() {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String pais_codigo = detallesCotizacionUi.getPaisCodigo();
        Call<DefaultResponse> call = loginService.aceptarCotizacionClienteDeEspecialista(pais_codigo,
                cotizacionesUi.getIdPropuesta(),
                cotizacionesUi.getIdCotizacion(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO,
                Constantes.ESTADO_ESPECIALISTA_ACEPTADO,
                "0");
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse defaultResponse = response.body();
                        if (defaultResponse.getError()) {
                            if (view != null) {
                                view.mostrarMensaje(defaultResponse.getMessage());
                                view.ocultarDialogProgress();
                                view.habilitarBotonPago();
                            }
                            return;
                        } else {
                            if (view != null) {
                                view.mostrarMensaje(defaultResponse.getMessage());
                                view.initStartActivityReportePago(detallesCotizacionUi, cotizacionesUi);
                                view.ocultarDialogProgress();
                            }
                            envioAceptarNotificacionCliente(detallesCotizacionUi, cotizacionesUi);
                            initRetrofitActulizarCotizacionNoAceptado(detallesCotizacionUi.getIdPropuesta(),
                                    detallesCotizacionUi.getPaisCodigo(),
                                    Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO);

                        }
                    } else {
                        // defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //defaultResponseCallBackResultado.onCallBackResultado(null);
                if (view != null) {
                    view.ocultarDialogProgress();
                    view.habilitarBotonPago();
                }
                Log.d(TAG, "onFailureActulizarPropuestaCoti : " + t.getMessage().toString());
            }
        });
    }

    private void initRetrofitActulizarCotizacionNoAceptado(String idPropuesta, String paisCodigo, String propuestaEstadoClientePendiente) {
        Log.d(TAG, "propuestaEstadoClientePendiente");
        propuestaEstadoClientePendiente = "3" + propuestaEstadoClientePendiente;
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = loginService.actualizarCotizacion(idPropuesta,
                paisCodigo,
                propuestaEstadoClientePendiente);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse defaultResponse = response.body();
                        if (defaultResponse.getError()) {
                            Log.d(TAG, "defaultResponse.getError()");

                            return;
                        } else {
                            Log.d(TAG, "GUARDANDO COTIZACION NO ACEPTADO");

                        }
                    } else {
                        Log.d(TAG, "defaultResponse.NULO()");
                        // defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //defaultResponseCallBackResultado.onCallBackResultado(null);
                // Log.d(TAG, "onFailureonFailuredefaultResponse.NULO()");
                Log.d(TAG, "onFailure ActulizarCotizacionNoAceptado: ");
            }
        });

    }

    String idToken;


    long tiempoInicio;

    private void initRetrofitConsultarToken(final String idToken) {
        String urlCulqui = "https://api.culqi.com/v2/";
        Api loginService = RetrofitClient.createService(Api.class, urlCulqui);
        Call<IntegracionResponse> call = loginService.consultarToken(idToken);
        call.enqueue(new Callback<IntegracionResponse>() {
            @Override
            public void onResponse(Call<IntegracionResponse> call, Response<IntegracionResponse> response) {
                IntegracionResponse serverResponse = response.body();
                if (serverResponse != null) {
                    tiempoInicio = Long.parseLong(serverResponse.getmCreation_Date());
                    Calendar now = Calendar.getInstance();
                    long milliseconds2 = now.getTimeInMillis();
                    long diff = milliseconds2 - tiempoInicio;
                    long diffMinutes = diff / (60 * 1000);
                    Log.d(TAG, "diffMinutes : " + diffMinutes);
                    if (diffMinutes < 5) {
                        if (idToken != null) if (view != null) {
                            view.mostrarCardViewPagoAceptado();
                            view.deshabilitarCulquiPagoBasico();
                        }
                    } else {
                        if (idToken != null) if (view != null) {
                            view.ocultarCardViewPagoAceptado();
                            view.habilitarCulquiPagoBasico();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<IntegracionResponse> call, Throwable t) {
                //defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure ConsultarToken : ");
            }
        });
    }


}
