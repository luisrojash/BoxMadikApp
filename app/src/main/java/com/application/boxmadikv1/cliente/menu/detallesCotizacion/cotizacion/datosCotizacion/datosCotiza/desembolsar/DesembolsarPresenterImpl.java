package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.useCase.CambiarEstadoFinalizado;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.useCase.RegistrarDesembolso;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesembolsarPresenterImpl extends BaseActivityPresenterImpl<DesembolsarView> implements DesembolsarPresenter {

    public static final String CREADO_CORRECTAMENTE_DEMSEMBOLSO_FINALIZADO= "DesembolsarPresenterImpl";

    public static final String TAG = DesembolsarPresenterImpl.class.getSimpleName();
    private RegistrarDesembolso registrarDesembolso;
    private CambiarEstadoFinalizado cambiarEstadoFinalizado;

    public DesembolsarPresenterImpl(UseCaseHandler handler, Resources res, RegistrarDesembolso registrarDesembolso, CambiarEstadoFinalizado cambiarEstadoFinalizado) {
        super(handler, res);
        this.registrarDesembolso = registrarDesembolso;
        this.cambiarEstadoFinalizado = cambiarEstadoFinalizado;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        this.cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
        Log.d(TAG, "DatosPerfilEspecialistaPresenterImpldetallesCotizacionUi : " + detallesCotizacionUi.getNombreProyecto() +
                "DatosPerfilEspecialistaPresenterImpldetallesCotizacionUi : " + cotizacionesUi.getIdUsuarioCotizacion());
    }

    @Override
    public void onStart() {
        super.onStart();
        initDataInicial();
    }

    private void initDataInicial() {
        if (view != null) view.mostrarDatainicial(detallesCotizacionUi, cotizacionesUi);
    }

    @Override
    public void onClickFinalizarDesemBolso(float ratingValue, String editTextComentario) {
        float valorEmpty = (float) 0.0;
        if (ratingValue == valorEmpty) {
            Log.d(TAG, "Necesita Evaluar al Especialista");
            String mensaje = "Necesita Evaluar al Especialista";
            if (view != null) view.mostrarMensaje(mensaje);
            return;
        }
        if (editTextComentario.isEmpty() || editTextComentario == null || editTextComentario.equals("")) {
            Log.d(TAG, "Necesita colocar un Comentario ");
            String mensaje = "Necesita colocar un Comentario";
            if (view != null) view.mostrarMensaje(mensaje);
            return;
        }

        String removeAcentosCadena = Constantes.removeAcentos(editTextComentario);
        String isPrimeroResultadoCharacter = Constantes.isPrimeroResultadoCharacter(removeAcentosCadena);
        String isSegundoresultadoCharacter = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacter);

        initUseCaseGuardarUsuarioCalificacion(ratingValue, isSegundoresultadoCharacter);
        Log.d(TAG, "onClickFinalizarDesemBolso : " + ratingValue +
                "editTextComentario : " + editTextComentario);
    }

    private void initUseCaseGuardarUsuarioCalificacion(float ratingValue, String editTextComentario) {
        String ratinValue = String.valueOf(ratingValue);
        if (view != null) view.mostrarDialogProgressBar();
        handler.execute(registrarDesembolso, new RegistrarDesembolso.RequesValues(ratinValue,
                        editTextComentario,
                        detallesCotizacionUi,
                        cotizacionesUi),
                new UseCase.UseCaseCallback<RegistrarDesembolso.ResponseValue>() {
                    @Override
                    public void onSuccess(RegistrarDesembolso.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();
                        if (response != null) {
                            if (defaultResponse.getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(defaultResponse.getMessage());
                                    view.ocultarDialogProgressBar();
                                }

                                return;
                            } else {
                               //if(view!=null) view.ocultarDialogProgressBar();
                                Log.d(TAG, "RegistrarCorrectamente Calificaciones");
                                initUseCaseCambiarEstadoPropuesta(detallesCotizacionUi, cotizacionesUi);
                                return;
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseCaseCambiarEstadoPropuesta(final DetallesCotizacionUi detallesCotizacionUi, final CotizacionesUi cotizacionesUi) {

        handler.execute(cambiarEstadoFinalizado, new CambiarEstadoFinalizado.RequesValues(detallesCotizacionUi, cotizacionesUi),
                new UseCase.UseCaseCallback<CambiarEstadoFinalizado.ResponseValue>() {
                    @Override
                    public void onSuccess(CambiarEstadoFinalizado.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();
                        if (response != null) {
                            if (defaultResponse.getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(defaultResponse.getMessage());
                                    view.ocultarDialogProgressBar();
                                }

                                return;
                            } else {
                                Log.d(TAG, "RegistrarCorrectamente initUseCaseCambiarEstadoPropuesta");
                                if (view != null) {
                                   // view.mostrarMensaje(defaultResponse.getMessage());
                                    view.ocultarDialogProgressBar();
                                    view.initStartActivityMenu(CREADO_CORRECTAMENTE_DEMSEMBOLSO_FINALIZADO);
                                  // enviarNotificacionDesembolso(detallesCotizacionUi,cotizacionesUi);
                                }
                                return;
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    private void enviarNotificacionDesembolso(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        Call<DefaultResponse> call = loginService.envioNotificacionCotizacionAceptada(Constantes.NOTIFICACION_NOT_ESTADO_PENDIENTE,
                Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION,
                Constantes.GRUPO_NOTIFICACION_CLIENTE,
                detallesCotizacionUi.getIdPropuesta(),
                cotizacionesUi.getIdCotizacion(),
                detallesCotizacionUi.getPaisCodigo());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

    }
}
