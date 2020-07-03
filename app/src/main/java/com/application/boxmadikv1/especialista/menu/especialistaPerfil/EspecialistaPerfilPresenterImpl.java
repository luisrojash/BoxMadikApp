package com.application.boxmadikv1.especialista.menu.especialistaPerfil;

import android.content.res.Resources;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.ObtenerDatosPerfilContador;
import com.application.boxmadikv1.api.response.especialista.ComentarioResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.entidad.TrabajosRealizadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.ComentariosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.useCase.MostrarComentarios;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspecialistaPerfilPresenterImpl extends BaseActivityPresenterImpl<EspecialistaPerfilView> implements EspecialistaPerfilPresenter {

    public static final String TAG = EspecialistaPerfilPresenterImpl.class.getSimpleName();
    private MostrarComentarios mostrarComentarios;

    public EspecialistaPerfilPresenterImpl(UseCaseHandler handler, Resources res, MostrarComentarios mostrarComentarios) {
        super(handler, res);
        this.mostrarComentarios = mostrarComentarios;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    String keyUser;

    @Override
    public void onObtenerDataPref(String keyUser, String nombreUsu, String apellidoUsu, String fotoUsu) {
        this.keyUser = keyUser;
        initUseCase();
        if (view != null) {
            if (apellidoUsu == null) {
                view.mostrarDataInicial(nombreUsu.toUpperCase(), "", fotoUsu);
            } else {
                view.mostrarDataInicial(nombreUsu.toUpperCase(), apellidoUsu.toUpperCase(), fotoUsu);
            }

        }
        String paisCodigo = "51";
        initRetrofit(keyUser, paisCodigo);
        initAdapterFragment();
        initUseCaseListaComentarios(keyUser);
    }

    private void initRetrofit(String keyUser, String paisCodigo) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        service.obtenerDataPerfilContador(keyUser, paisCodigo, "2").enqueue(new Callback<ObtenerDatosPerfilContador>() {
            @Override
            public void onResponse(Call<ObtenerDatosPerfilContador> call, Response<ObtenerDatosPerfilContador> response) {
                String guardarPerfil = "Guardando Perfil";

                if (response == null) {
                    Log.d(TAG, "response == null");
                    return;
                }
                ObtenerDatosPerfilContador editarPerfilResponse = response.body();
                if (editarPerfilResponse.getError()) {

                    Log.d(TAG, "editarPerfilResponse.getError()" + editarPerfilResponse.getMensaje());

                    return;
                } else {
                    /*Aca Guarda Correctamente*/

                    String cotiFinalizada = editarPerfilResponse.getObtenerDataResponse().getCotiFinalizada();
                    String cotiPendiente = editarPerfilResponse.getObtenerDataResponse().getCotiPendiente();
                    String cotiAceptado = editarPerfilResponse.getObtenerDataResponse().getCotiAceptado();
                    String usuPresentacion = editarPerfilResponse.getObtenerDataResponse().getUsuPresentacion();

                    String datosResenia;
                    if (usuPresentacion == null) {
                        datosResenia = "Falta Completar sus Datos";
                        if (view != null) view.mostrarMensajeResenia(datosResenia);
                        Log.d(TAG, "usuPresentacion == nul " + usuPresentacion);
                    } else {
                        datosResenia = usuPresentacion;
                    }
                    float porcentaje = validarFloatNull(editarPerfilResponse);
                    if (view != null) {
                        view.mostrarDatosPropuesta(cotiFinalizada, cotiPendiente, cotiAceptado, porcentaje,datosResenia);
                        Log.d(TAG, "Aca Guarda Correctamente.getError()");
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<ObtenerDatosPerfilContador> call, Throwable t) {
                Log.d(TAG, "ObtenerDatosPerfilContador == onFailure");
                //  return;
            }
        });
    }

    private float validarFloatNull(ObtenerDatosPerfilContador editarPerfilResponse) {
        float usuarioEstrellas = 0;
        if (editarPerfilResponse.getObtenerDataResponse().getEstrellasUsuario() == null) {
            usuarioEstrellas = 0;
        } else {
            float porcentaje = Float.parseFloat(editarPerfilResponse.getObtenerDataResponse().getEstrellasUsuario());
            usuarioEstrellas = porcentaje;
        }
        return usuarioEstrellas;
    }

    private void initUseCase() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // initUseCaseListaComentarios(keyUser);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initAdapterFragment() {
        if (view != null) {
            view.initClearFragments();
            view.initViewPagerAdapter();
        }
    }

    private void initUseCaseListaComentarios(String keyUser) {
        handler.execute(mostrarComentarios, new MostrarComentarios.RequestValues(keyUser,
                        "51"),
                new UseCase.UseCaseCallback<MostrarComentarios.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarComentarios.ResponseValue response) {
                        ComentarioResponse resultado = response.getComentarioResponse();

                        if (resultado == null) {
                            if (view != null) {
                                view.ocultarProgressBar();
                                view.mostrarMensajeToast("Contactese con el soporte del aplicativo");
                                view.mostrarTextoEmpty("--No cuenta trabajos Realizados--");
                            }
                            return;
                        } else {
                            /*True- Porque hay Error*/
                            if (resultado.getError()) {
                                if (view != null) {
                                    view.ocultarProgressBar();
                                    Log.d(TAG, "resultado.getMensaje() : " + resultado.getMensaje());
                                    // view.mostrarMensajeSnackBar(resultado.getMensaje());
                                    // view.mostrarMensajeToast("No hay propuestas en este Rubro");
                                    view.mostrarTextoEmpty("--No cuenta trabajos Realizados--");
                                    // view.mostrarMensajeEmptyTexto("No hay propuestas en este Rubro");
                                }
                                return;
                            } else {
                                if (view != null) {
                                    view.mostrarMensajeToast(resultado.getMensaje());
                                }
                                initListaFragment(response.getComentarioResponse());
                                return;
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initListaFragment(ComentarioResponse comentarioResponse) {
        int countLista = comentarioResponse.getComentarioEspecResponseList().size();
        if (countLista > 0) {
            if (view != null) view.ocultarTextoEmpty();
        }
        if (countLista > 1) {
            if (view != null) view.mostrarBotones();
        }
        Log.d(TAG, "countLista :  " + countLista);
        List<TrabajosRealizadosUi> trabajosRealizadosUis = new ArrayList<>();
        for (int i = 0; i < countLista; i++) {
            ComentarioResponse.ComentarioEspecResponse propuestaInicial = comentarioResponse.getComentarioEspecResponseList().get(i);
            String nombrePropuesta = propuestaInicial.getNombrePropuesta();
            String fechaPropuesta = Constantes.f_fecha_letras(propuestaInicial.getFechaPropuesta());
            String detallePropuesta = propuestaInicial.getDetallePropuesta();
            String fotoCliente = propuestaInicial.getFotoCliente();
            String nombreCliente = propuestaInicial.getNombreCliente();
            String apellidoCliente = propuestaInicial.getApellidoCliente();
            String estrellasEspec = propuestaInicial.getEstrellasEspec();
            String comentarioEspec = propuestaInicial.getComentarioEspec();
            String imagenRubro = propuestaInicial.getRubroImagen();
            String usuRazonSocial = propuestaInicial.getUsuRazonSocial();
            TrabajosRealizadosUi comentariosUi = new TrabajosRealizadosUi(
                    nombrePropuesta, fechaPropuesta, detallePropuesta, fotoCliente,
                    nombreCliente, apellidoCliente, estrellasEspec, comentarioEspec,
                    imagenRubro, keyUser, usuRazonSocial);
            trabajosRealizadosUis.add(comentariosUi);
            // initUseCaseRangoDias(titulo, fecha, codigoRubro, codigoOficio, codigoRangoDiasId, codigoRangoTurnoId, codigoRangoPrecioId);
            Log.d(TAG, "nombrePropuesta : " + nombrePropuesta
                    + " /fechaPropuesta " + fechaPropuesta
                    + " / detallePropuesta" + detallePropuesta
                    + " /fotoCliente " + fotoCliente
                    + " / nombreCliente" + nombreCliente
                    + " / apellidoCliente" + apellidoCliente
                    + " / estrellasEspec" + estrellasEspec
                    + " / comentarioEspec" + comentarioEspec);

        }
        if (view != null) {
            view.mostrarListaComentarios(trabajosRealizadosUis);
        }
        // if (view != null) view.actualizarAdapterFragmentos();
    }
}
