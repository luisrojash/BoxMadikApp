package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.useCase.MostrarImagenPropuesta;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescripcionPresenterImpl extends BaseFragmentPresenterImpl<DescripcionView> implements DescripcionPresenter {


    public static final String TAG = DescripcionPresenterImpl.class.getSimpleName();

    private MostrarImagenPropuesta mostrarImagenPropuesta;

    public DescripcionPresenterImpl(UseCaseHandler handler, Resources res, MostrarImagenPropuesta mostrarImagenPropuesta) {
        super(handler, res);
        this.mostrarImagenPropuesta = mostrarImagenPropuesta;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    ItemUi itemUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.itemUi = extras.getParcelable("itemUi");
        Log.d(TAG, "itemUi : " + itemUi.getNombrePropuesta() + "");
        initVistasInicial(itemUi);
        validarListaEspecialidades(itemUi);
        validarTipoEstadoInicial(itemUi);
    }

    private void initVistasInicial(ItemUi itemUi) {
        if (view != null) {
            view.mostrarDataInicial(itemUi);
            view.ocultarTeclado();
        }
        initCaseMostrarImagenPropuesta(itemUi.getCodigoPropuesta());
    }

    private void validarListaEspecialidades(ItemUi itemUi) {
        Log.d(TAG, "itemUi.getEspecialidadesUiList()" + itemUi.getEspecialidadesUiList());
        if (itemUi.getEspecialidadesUiList() == null || itemUi.getEspecialidadesUiList().isEmpty()) {
            if (view != null) view.mostrarTextoListaVacia("--- No Hay Especialides ---");
            return;
        }
        if (itemUi.getEspecialidadesUiList().size() > 0) {
            Log.d(TAG, "else" + itemUi.getEspecialidadesUiList());
            if (view != null) view.mostrarListaEspecialidades(itemUi.getEspecialidadesUiList());
            return;
        }
    }

    String primeraImagen = null;
    String segundaImagen = null;

    private void initCaseMostrarImagenPropuesta(String codigoPropuesta) {
        handler.execute(mostrarImagenPropuesta, new MostrarImagenPropuesta.RequestValues(codigoPropuesta),
                new UseCase.UseCaseCallback<MostrarImagenPropuesta.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarImagenPropuesta.ResponseValue response) {
                        if (response == null) {
                            Log.d(TAG, "ResponseNull");
                            if (view != null) view.mostrarTextoImagenVacio("No hay Imagenes");
                            return;
                        }
                        MostrarImagenResponse mostrarImagenPropuesta = response.getMostrarImagenResponse();
                        if (mostrarImagenPropuesta == null) return;
                        MostrarImagenResponse.ImagenesResponse imagenesResponse = mostrarImagenPropuesta.getImagenesResponse();
                        if (imagenesResponse == null) return;
                        if (imagenesResponse.getPri_foto1() == null &&
                                imagenesResponse.getPri_foto2() == null) {
                            if (view != null) view.mostrarTextoImagenVacio("No hay Imagenes");
                            Log.d(TAG, "2 fotos nulas");
                            return;

                        } else if (imagenesResponse.getPri_foto1() == null) {
                            Log.d(TAG, "getPri_foto1() == null");
                            String segundaImageUri = imagenesResponse.getPri_foto2();
                            segundaImagen = segundaImageUri;
                            if (view != null) view.mostrarImagenSegunda(segundaImageUri);
                            return;
                        } else if (imagenesResponse.getPri_foto2() == null) {
                            Log.d(TAG, "getPri_foto2()== null");
                            String primeraImageUri = imagenesResponse.getPri_foto1();
                            primeraImagen = primeraImageUri;
                            if (view != null) view.mostrarImagenPrimera(primeraImageUri);
                            return;
                        } else {
                            String segundaImageUri = imagenesResponse.getPri_foto2();
                            String primeraImageUri = imagenesResponse.getPri_foto1();
                            primeraImagen = primeraImageUri;
                            segundaImagen = segundaImageUri;
                            if (view != null)
                                view.mostrarTodasImagenes(primeraImageUri, segundaImageUri);
                            Log.d(TAG, "2 fotos Llenas");
                            return;
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (view != null) view.ocultarTeclado();
    }

    @Override
    public void onClickImagePrimero() {
        if (primeraImagen == null) return;
        if (primeraImagen.length() > 0) {
            Log.d(TAG, "onClickImagePrimero");
            if (view != null) view.mostrarImagenDetallePrimero(primeraImagen);
        }
    }

    @Override
    public void onClickImageSegundo() {
        if (segundaImagen == null) return;
        if (segundaImagen.length() > 0) {
            Log.d(TAG, "onClickImageSegundo");
            if (view != null) view.mostrarImagenDetalleSegundo(segundaImagen);
        }

    }

    @Override
    public void onClickRevocacion() {
        // validarTipoEstadoRevocar(itemUi);
        if(view!=null){
            view.deshabilitarButtonRevocacion();
            view.initStartActivityRespuestaRevocacion(itemUi);
        }

    }

    @Override
    public void onClickButtonVerRevocacion() {
        if(view!=null){
            view.deshabilitarButtonVerRevocacion();
            view.startActivityRevocacion(itemUi);
        }
    }


    private void validarTipoEstadoInicial(ItemUi itemUi) {

        switch (itemUi.getPropuestaEstado()) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
                if (view != null) {
                    view.mostrarButtonRevocacion();
                    view.mostrarButtonVerRevocacion();
                   // view.mostrarButtonRevocacionTextoEnProceso("Revocación en Proceso");
                    //view.deshabilitarButtonRevocacion();
                }
                initValidarRespuestaRevocacion();
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:
                validarCotizacionUsu(itemUi);
                break;
            default:
                if (view != null) view.ocultarButtonRevocacion();
                break;
        }
    }

    private void initValidarRespuestaRevocacion() {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);


        Call<DefaultResponse> call = apiService.validaRespuestaCotizacion(itemUi.getCodigoPropuesta(),
                itemUi.getCodigoUsuarioPropuesta(),
                itemUi.getIdUsuarioCotizacion());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse == null) return;
                if (cambioResponse.getError()) {


                    if (view != null) {
                        view.deshabilitarButtonRevocacion();
                        view.mostrarButtonRevocacion();
                    }
                    return;
                } else {
                    if (view != null) {
                        view.habilitarButtonRevocacion();
                        view.mostrarButtonRevocacion();
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
             //   defaultResponseCallBackResultado.onResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });


    }

    private void validarCotizacionUsu(ItemUi itemUi) {
        if (itemUi.getCotiEstado() == null) {
            if (view != null) view.ocultarButtonRevocacion();
            return;
        }
        switch (itemUi.getCotiEstado()) {
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                if (view != null) {
                   // view.mostrarButtonRevocacion();
                    view.ocultarButtonRevocacion();
                    view.mostrarButtonRevocacionTextoEnProceso("Revocación ");
                    view.habilitarButtonRevocacion();
                }
                break;
            default:
                if (view != null) view.ocultarButtonRevocacion();
                break;
        }
    }
}
