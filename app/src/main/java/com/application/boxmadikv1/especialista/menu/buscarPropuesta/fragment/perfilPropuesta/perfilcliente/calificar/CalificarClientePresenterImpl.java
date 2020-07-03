package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.calificar;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalificarClientePresenterImpl extends BaseActivityPresenterImpl<CalificarClienteView> implements CalificarClientePresenter {

    public static final String TAG = CalificarClientePresenterImpl.class.getSimpleName();


    public CalificarClientePresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    ItemUi itemUi;
    String nombreCliente, paisCliente, imagenCliente;


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.itemUi = extras.getParcelable("itemUi");
        this.nombreCliente = extras.getString("nombreCliente");
        this.paisCliente = extras.getString("paisCliente");
        this.imagenCliente = extras.getString("imagenCliente");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mostrarDataInicial(itemUi);
    }

    private void mostrarDataInicial(ItemUi itemUi) {
        if (view != null)
            view.mostrarDataInicial(itemUi, nombreCliente, paisCliente, imagenCliente);
    }

    @Override
    public void onClickCalificar(float ratingValue, String editTextComentario) {
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

        //initUseCaseGuardarUsuarioCalificacion(ratingValue, Constantes.isResultadoEspecial(isSegundoresultadoCharacter));
        initUseCaseGuardarUsuarioCalificacion(ratingValue, isSegundoresultadoCharacter);
        Log.d(TAG, "onClickFinalizarDesemBolso : " + ratingValue +
                "editTextComentario : " + editTextComentario);
    }

    private void initUseCaseGuardarUsuarioCalificacion(float ratingValue, String editTextComentario) {
        if(view!=null)view.mostrarProgressBarDialog();
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = apiService.calificarCliente(itemUi.getPaisCodigo(),
                itemUi.getCodigoPropuesta(),
                itemUi.getKeyUser(),
                itemUi.getCodigoUsuarioPropuesta(),
                String.valueOf(ratingValue),
                editTextComentario,
                "cliente",
                "",
                "",
                "");
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse.getError()) {
                    if (view != null) {
                        view.mostrarMensaje(cambioResponse.getMessage());
                        view.ocultarProgressBarDialog();
                    }
                } else {
                    String mensaje = cambioResponse.getMessage();
                    initRetrofitActualizarEstadoCotiza(mensaje);
                   /* if (view != null) {
                        view.ocultarProgressBarDialog();
                        view.finishActivity(mensaje);
                    }*/
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d(TAG, "cambioResponse : " + t.getMessage());
                if (view != null) {
                    view.mostrarMensaje("Errores de Conexion");
                    view.ocultarProgressBarDialog();
                }
            }
        });
    }

    private void initRetrofitActualizarEstadoCotiza(final String mensaje) {

        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String pais_codigo = itemUi.getPaisCodigo();

        Call<DefaultResponse> call = loginService.aceptarCotizacionClienteDeEspecialista(pais_codigo,
                itemUi.getCodigoPropuesta(),
                itemUi.getIdCotizacion(),
                itemUi.getCodigoUsuarioPropuesta(),
                itemUi.getIdUsuarioCotizacion(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO,
                Constantes.ESTADO_ESPECIALISTA_FINALIZADO,
                "2");
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse defaultResponse = response.body();
                        // defaultResponseCallBackResultado.onCallBackResultado(message);

                        if (defaultResponse.getError()) {
                            if (view != null) view.mostrarMensaje(defaultResponse.getMessage());
                            return;
                        } else {
                            if (view != null) {
                                view.ocultarProgressBarDialog();
                                view.finishActivity(mensaje);
                            }
                            return;

                        }
                    } else {
                        // defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
