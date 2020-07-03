package com.application.boxmadikv1.cliente.menu.clientePerfil;

import android.content.res.Resources;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.ObtenerDatosPerfilContador;
import com.application.boxmadikv1.api.response.cliente.ComentarioClienteResponse;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePerfil.entidad.ComentariosUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientePerfilPresenterImpl extends BaseActivityPresenterImpl<ClientePerfilView> implements ClientePerfilPresenter {


    public static final String TAG = ClientePerfilPresenterImpl.class.getSimpleName();
    String paisCodigo = "51";

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "usuCodigo : " + usuCodigo +
                "paisCodigo : " + paisCodigo);
        initCaseListRetrofitComentarios(usuCodigo, paisCodigo);
    }


    public ClientePerfilPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void OnClickEditarPerfil() {
        if (view != null) view.starActivityEditarPerfil();
    }

    String usuCodigo, usuNombre, usuApellido, usuFoto;

    @Override
    public void onDataUser(String usuCodigo, String usuNombre, String usuApellido, String usuFoto) {
        this.usuCodigo = usuCodigo;
        this.usuNombre = usuNombre;
        this.usuApellido = usuApellido;
        this.usuFoto = usuFoto;

        initCaseRetrofit(usuCodigo, paisCodigo);
        Log.d(TAG, "usuCodigo : " + usuCodigo +
                "usuApellido : " + usuApellido
        );
        if (usuApellido == null) {
            usuApellido = "";
        }
        String usuario = validarTipoUsuario(usuNombre, usuApellido);
        if (view != null) view.mostrarDataInicial(usuCodigo, usuario, "", usuFoto);
    }


    private String validarTipoUsuario(String usuarioValidate, String usuarioApellido) {
        String nombreUsuario = usuarioValidate;
        if (usuarioApellido != null) {
            nombreUsuario = usuarioValidate + " " + usuarioApellido;
        }
        return nombreUsuario;
    }

    private void initCaseRetrofit(String usuCodigo, String paisCodigo) {

        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        service.obtenerDataPerfilContador(usuCodigo, paisCodigo, "1").enqueue(new Callback<ObtenerDatosPerfilContador>() {
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
                    if (editarPerfilResponse.getObtenerDataResponse().getPropuestaFinalizada() == null) {

                        view.mostrarDatosPropuesta("0", "0", "0", 0);
                        return;
                    }
                    String propuestaFinalizada = "";
                    String propuestaPendiente = editarPerfilResponse.getObtenerDataResponse().getPropuestaPendiente();
                    String propuestaProceso = editarPerfilResponse.getObtenerDataResponse().getPropuestaProceso();
                    float porcentaje = validarFloatNull(editarPerfilResponse);
                   /*if (editarPerfilResponse.getObtenerDataResponse().getPropuestaFinalizada().equals("0")) {
                        propuestaFinalizada = editarPerfilResponse.getObtenerDataResponse().getPropuestaPagados();
                    } else {
                        propuestaFinalizada = editarPerfilResponse.getObtenerDataResponse().getPropuestaFinalizada();
                    }*/
                    int countarPagados, countarFinalizados;
                    if (editarPerfilResponse.getObtenerDataResponse().getPropuestaPagados().equals("0")) {
                        countarPagados = 0;
                    } else {
                        countarPagados = Integer.parseInt(editarPerfilResponse.getObtenerDataResponse().getPropuestaPagados());
                    }
                    if (editarPerfilResponse.getObtenerDataResponse().getPropuestaFinalizada().equals("0")) {
                        countarFinalizados = 0;
                    } else {
                        countarFinalizados = Integer.parseInt(editarPerfilResponse.getObtenerDataResponse().getPropuestaFinalizada());
                    }

                    int resultadoOperacion = countarPagados + countarFinalizados;
                    if (view != null) {
                        view.mostrarDatosPropuesta(String.valueOf(resultadoOperacion), propuestaPendiente, propuestaProceso, porcentaje);
                        Log.d(TAG, "Aca Guarda Correctamente.getError()");
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<ObtenerDatosPerfilContador> call, Throwable t) {
                Log.d(TAG, "ObtenerDatosPerfilContador == onFailure");
                return;
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


    private void initCaseListRetrofitComentarios(String usuario, String paisCodigo) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ComentarioClienteResponse> call = apiService.obtenerListaComentarioCliente(usuario,
                paisCodigo);
        call.enqueue(new Callback<ComentarioClienteResponse>() {
            @Override
            public void onResponse(Call<ComentarioClienteResponse> call, Response<ComentarioClienteResponse> response) {
                ComentarioClienteResponse cambioResponse = response.body();
                if (cambioResponse.getComentariosClientResponses() == null) {
                    String mensaje = "Contactese con el soporte Aplicativo";
                    //   if (view != null) view.mostrarMensaje(mensaje);
                    return;
                } else {
                    // ComentarioClienteResponse comentarioClienteResponse = cambioResponse.getComentarioClienteResponse();
                    if (cambioResponse.getError()) {
                        if (view != null)
                            if (view != null) view.mostrarTextViewEmpty();
                        //     view.mostrarMensaje(comentarioClienteResponse.getMensaje());
                        return;
                    } else {
                        List<ComentarioClienteResponse.ComentariosClientResponse> clienteList =
                                cambioResponse.getComentariosClientResponses();
                        if (clienteList == null) {
                            if (view != null) view.mostrarTextViewEmpty();
                            Log.d(TAG, "Datos Nulos ");
                        } else {
                            List<ComentariosUi> datosClienteList = new ArrayList<>();
                            for (ComentarioClienteResponse.ComentariosClientResponse comentariosClientResponse :
                                    clienteList) {
                                Log.d(TAG, "omentariosClientResponse" + comentariosClientResponse.getComentarioCliente());
                                ComentariosUi datosCliente = new ComentariosUi();
                                datosCliente.setEstrellas(comentariosClientResponse.getEstrellasCliente());
                                datosCliente.setTextoComentario(comentariosClientResponse.getComentarioCliente());
                                datosCliente.setNombrePropuesta(comentariosClientResponse.getPropuestaTitulo());
                                datosCliente.setFechaComentario(comentariosClientResponse.getFechaRegistroComentario());
                                datosCliente.setImagenRubro(comentariosClientResponse.getRubroImagen());
                                datosClienteList.add(datosCliente);
                            }
                            if (datosClienteList.size() > 1) {
                                if (view != null) view.mostrarBottones();
                            }
                            if (view != null) view.mostrarListaComentarios(datosClienteList);
                            Log.d(TAG, "Datos LLENOS :" + datosClienteList.size());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ComentarioClienteResponse> call, Throwable t) {
                ///listCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailurecambioResponse : ");
            }
        });
    }
}
