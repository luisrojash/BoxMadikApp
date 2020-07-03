package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.entidad.EspecialidadUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.useCase.ObtenerImagen;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesDescripcionPresenterImpl extends BaseFragmentPresenterImpl<DetallesDescripcionView> implements DetallesDescripcionPresenter {

    public static final String TAG = DetallesDescripcionPresenterImpl.class.getSimpleName();

    private ObtenerImagen obtenerImagen;


    public DetallesDescripcionPresenterImpl(UseCaseHandler handler, Resources res, ObtenerImagen obtenerImagen) {
        super(handler, res);
        this.obtenerImagen = obtenerImagen;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    DetallesCotizacionUi detallesCotizacionUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        initVistasInicial(detallesCotizacionUi);
       // initListaEspec(detallesCotizacionUi.getIdPropuesta()); desactivador 19-02-2019
        Log.d(TAG, "DetallesDescripcionPresenterImpl : " + detallesCotizacionUi.getNombreProyecto());
    }

    List<EspecialidadUi> especialidadUis;

    private void initListaEspec(String idPropuesta) {

        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        loginService.obtenerListaEspecialidadCliente(Integer.parseInt(idPropuesta)).enqueue(new Callback<ListaPropuestaEspecialidadResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaEspecialidadResponse> call, Response<ListaPropuestaEspecialidadResponse> response) {
                // DefaultResponse loginResponse = response.body();
                ListaPropuestaEspecialidadResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    if (view != null) view.mostrarTextoVacio("No tiene Especialidades");
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        if (view != null) view.mostrarTextoVacio("No tiene Especialidades");
                    } else {
                        especialidadUis = new ArrayList<>();
                        for (PropuestaEspecialidad especialidad : defaultResponse.getPropuestaEspecialidad()) {
                            EspecialidadUi especialidadUi = new EspecialidadUi();
                            especialidadUi.setCodigo(especialidad.getEspecialidades_Espe_codigo());
                            especialidadUi.setDescripcion(especialidad.getPE_descripcion());
                            especialidadUi.setPE_estado(especialidad.getPE_estado());
                            especialidadUis.add(especialidadUi);
                        }
                        if (view != null) {
                            view.mostrarListaEspec(especialidadUis);
                            view.ocultarTexto();
                        }

                        Log.d(TAG, "else");
                    }
                }
            }

            @Override
            public void onFailure(Call<ListaPropuestaEspecialidadResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "mensaje : " + mensaje);
                if (view != null) view.mostrarTextoVacio("No tiene Especialidades");
                return;
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

         /*  ListaPropuestaEspecialidadResponse resultado = response.getListaPropuestaEspecialidadResponse();
                        Log.d(TAG, "resultado : " + resultado);
                        if (resultado == null) return;
                        Log.d(TAG, "resultado == NULL");
                        especialidadList = response.getListaPropuestaEspecialidadResponse().getPropuestaEspecialidad();
                        if (especialidadList == null) {
                            Log.d(TAG, "WSP if");
                            if (view != null) view.mostrarTextoVacio("No tiene Especialidades");
                        } else {
                            Log.d(TAG, "WSP ELSE");
                            if (view != null)
                                view.mostrarListaPropuestaEspecialidad(especialidadList);
                        }*/
    }

    private void initVistasInicial(DetallesCotizacionUi detallesCotizacionUi) {
        if (view != null) {
            view.mostrarDataInicial(detallesCotizacionUi);
            view.ocultarTeclado();
        }
        initCaseMostrarImagenPropuesta(detallesCotizacionUi.getIdPropuesta());
    }

    String primeraImagen = null;
    String segundaImagen = null;

    private void initCaseMostrarImagenPropuesta(String idPropuesta) {
        handler.execute(obtenerImagen, new ObtenerImagen.RequestValues(idPropuesta),
                new UseCase.UseCaseCallback<ObtenerImagen.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerImagen.ResponseValue response) {
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

}
