package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.api.response.cliente.ComentarioClienteResponse;
import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad.DatosCliente;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.useCase.ObtenerListaComentarios;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.useCase.ObtenerPerfil;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilClientePresenterImpl extends BaseFragmentPresenterImpl<PerfilClienteView> implements PerfilClientePresenter {


    public static final String TAG = PerfilClientePresenterImpl.class.getSimpleName();

    private ObtenerPerfil obtenerPerfil;
    private ObtenerListaComentarios obtenerListaComentarios;

    public PerfilClientePresenterImpl(UseCaseHandler handler, Resources res, ObtenerPerfil obtenerPerfil, ObtenerListaComentarios obtenerListaComentarios) {
        super(handler, res);
        this.obtenerPerfil = obtenerPerfil;
        this.obtenerListaComentarios = obtenerListaComentarios;
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
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.itemUi = extras.getParcelable("itemUi");
        Log.d(TAG, "getCodigoPropuesta : " + itemUi.getCodigoUsuarioPropuesta()
                + "codigokeyUser: " + itemUi.getKeyUser());
        initUserProfile(itemUi.getCodigoUsuarioPropuesta());
        initListaComentarios(itemUi.getCodigoUsuarioPropuesta(), itemUi.getPaisCodigo());
        initValidateEstado(itemUi);
        //initValidateCotiEstad(ItemUi)
    }

    private void initValidateEstado(ItemUi itemUi) {
        if (itemUi.getCotiEstado() == null) {
            if (view != null) view.ocultarButtonCalificar();
            return;
        }
        switch (itemUi.getCotiEstado()) {
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                if (view != null) view.ocultarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_REVOCADOS");
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                if (view != null) view.ocultarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS");
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                if (view != null) view.ocultarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_ACEPTADO");
                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                if (view != null) view.ocultarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_NO_ACEPTADO");
                break;
            case Constantes.ESTADO_ESPECIALISTA_PAGADO:
                if (view != null) view.mostrarButtonCalificar();
                break;
            case Constantes.ESTADO_ESPECIALISTA_FINALIZADO:
                if (view != null) view.ocultarButtonCalificar();
                //if (view != null) view.mostrarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_NO_ACEPTADO");
                break;
            default:
                Log.d(TAG, "default");
                break;
        }
    }

    private void initListaComentarios(String codigoUsuarioPropuesta, String paisCodigoPeru) {
        handler.execute(obtenerListaComentarios, new ObtenerListaComentarios.RequestValues(codigoUsuarioPropuesta, paisCodigoPeru),
                new UseCase.UseCaseCallback<ObtenerListaComentarios.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerListaComentarios.ResponseValue response) {
                        if (response.getComentarioClienteResponse() == null) {
                            String mensaje = "Contactese con el soporte Aplicativo";
                            //   if (view != null) view.mostrarMensaje(mensaje);
                            return;
                        } else {
                            ComentarioClienteResponse comentarioClienteResponse = response.getComentarioClienteResponse();
                            if (comentarioClienteResponse.getError()) {
                                if (view != null)
                                    //     view.mostrarMensaje(comentarioClienteResponse.getMensaje());
                                    return;
                            } else {
                                List<DatosCliente> clienteList = obtenerListaComentarios(comentarioClienteResponse.getComentariosClientResponses());
                                if (clienteList == null) {
                                    Log.d(TAG, "Datos Nulos ");
                                } else {
                                    Log.d(TAG, "Datos LLENOS ");
                                }
                                int conteoItem = 0;
                                for (DatosCliente datosCliente : clienteList) {
                                    if (datosCliente.getComentario() != null) {
                                        conteoItem++;
                                    }
                                }
                                if (conteoItem > 1) {
                                    if (view != null) {
                                        view.mostrarBotonDerecho();
                                        view.mostrarBotonIzquierdo();

                                    }
                                }
                                if (view != null) {
                                    view.mostrarListaComentarios(clienteList);
                                }
                                // obtenerListaComentarios(comentarioClienteResponse.getComentariosClientResponses());
                            }
                        }


                    }

                    @Override
                    public void onError() {

                    }
                });

    }


    private List<DatosCliente> obtenerListaComentarios(List<ComentarioClienteResponse.ComentariosClientResponse> comentariosClientResponses) {
        List<DatosCliente> datosClienteList = new ArrayList<>();
        for (ComentarioClienteResponse.ComentariosClientResponse comentariosClientResponse : comentariosClientResponses) {
            DatosCliente datosCliente = new DatosCliente();
            datosCliente.setFoto(comentariosClientResponse.getUsuFoto());
            datosCliente.setNombre(comentariosClientResponse.getUsuNombre());
            datosCliente.setApellido(comentariosClientResponse.getUsuApellido());
            datosCliente.setEstrellas(comentariosClientResponse.getEstrellasCliente());
            datosCliente.setComentario(comentariosClientResponse.getComentarioCliente());
            datosCliente.setUsuRazonSocial(comentariosClientResponse.getUsuRazonSocial());
            datosCliente.setPropuestaTitulo(comentariosClientResponse.getPropuestaTitulo());
            datosCliente.setFechaRegistroComentario(comentariosClientResponse.getFechaRegistroComentario());
            datosCliente.setRubroImagen(comentariosClientResponse.getRubroImagen());
            datosClienteList.add(datosCliente);
        }
        return datosClienteList;
    }

    DatosPerfilResponse.DatosPerfilClienteResponse clienteResponse;

    private void initUserProfile(String codigoPropuesta) {
        handler.execute(obtenerPerfil, new ObtenerPerfil.RequestValues(codigoPropuesta,
                Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE,
                Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO), new UseCase.UseCaseCallback<ObtenerPerfil.ResponseValue>() {
            @Override
            public void onSuccess(ObtenerPerfil.ResponseValue response) {
                if (response.getDatosPerfilResponse() == null) return;
                if (response != null) {
                    if (response.getDatosPerfilResponse() == null) {
                        if (view != null) {
                            //  view.mostrarMensaje(response.getDatosPerfilResponse().getMensaje());
                            view.ocultarProgressBar();
                            //view.limpiarTexto();
                            Log.d(TAG, "response.getDefaultResponse() == null");
                            //   view.startMenuClienteActivity(CREADO_CORRECTAMENTE_PROPUESTA);
                        }
                        return;
                    }
                    if (response.getDatosPerfilResponse().getError()) {
                        if (view != null) {
                            // view.mostrarMensaje(response.getDatosPerfilResponse().getMensaje());
                            view.ocultarProgressBar();
                            Log.d(TAG, "response.getDefaultResponse().getError()");
                        }
                        return;
                    } else {
                        clienteResponse =
                                response.getDatosPerfilResponse().getDatosPerfilClienteResponse();
                        if (response.getDatosPerfilResponse().getDatosPerfilClienteResponse().getUsuRazonSocial() != null) {
                            Log.d(TAG, "usuRazonSocialif : " + response.getDatosPerfilResponse().getDatosPerfilClienteResponse().getUsuRazonSocial());
                            if (view != null) {
                                view.ocultarProgressBar();
                                view.mostrarDatosPerfilRazonSocial(response.getDatosPerfilResponse().getDatosPerfilClienteResponse());
                            }
                            return;
                        } else {
                            Log.d(TAG, "usuRazonSocialelse : " + response.getDatosPerfilResponse().getDatosPerfilClienteResponse().getUsuRazonSocial());
                            int finalizada, pagadas;
                            if (response.getDatosPerfilResponse().getDatosPerfilClienteResponse().getTotal_propuestas_finalizada().equals("0")) {
                                finalizada = 0;
                            } else {
                                finalizada = Integer.parseInt(response.getDatosPerfilResponse().getDatosPerfilClienteResponse().getTotal_propuestas_finalizada());
                            }
                            if (response.getDatosPerfilResponse().getDatosPerfilClienteResponse().getTotalPagados().equals("0")) {
                                pagadas = 0;
                            } else {
                                pagadas = Integer.parseInt(response.getDatosPerfilResponse().getDatosPerfilClienteResponse().getTotalPagados());
                            }

                            int resultado = finalizada + pagadas;
                            if (view != null) {
                                //view.mostrarMensaje(response.getDatosPerfilResponse().getMensaje());
                                view.ocultarProgressBar();
                                view.mostrarDatosPerfil(response.getDatosPerfilResponse().getDatosPerfilClienteResponse(),resultado);
                                Log.d(TAG, "else response.getDefaultResponse().getError()");
                                //view.startMenuClienteActivity(CREADO_CORRECTAMENTE_PROPUESTA);
                            }
                            return;
                        }

                    }
                } else {
                    if (view != null) {
                        //view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                        view.ocultarProgressBar();
                    }
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
        if (itemUi.getCotiEstado() == null) {
            if (view != null) view.ocultarButtonCalificar();
            return;
        }
        switch (itemUi.getCotiEstado()) {
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                if (view != null) view.ocultarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_REVOCADOS");
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                if (view != null) view.ocultarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS");
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                if (view != null) view.ocultarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_ACEPTADO");
                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                if (view != null) view.ocultarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_NO_ACEPTADO");
                break;
            case Constantes.ESTADO_ESPECIALISTA_FINALIZADO:
                validarCalificacion();
                //if (view != null) view.mostrarButtonCalificar();
                Log.d(TAG, "ESTADO_ESPECIALISTA_NO_ACEPTADO");
                break;
            default:
                Log.d(TAG, "default");
                break;
        }
       /* String paisCodigo = itemUi.getPaisCodigo();
        String propuestaCodigo = itemUi.getCodigoPropuesta();
        String keyUserCodigo = itemUi.getKeyUser();
        String codigoUsuarioPropuesta = itemUi.getCodigoUsuarioPropuesta();
        Log.d(TAG, "tipo estado : " + itemUi.getCotiEstado());
        if(itemUi.getPropuestaEstado()!=null){
            if (itemUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO) &&
                    itemUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_FINALIZADO)) {//ESTADO_ESPECIALISTA_PAGADO
                initiValidarCalificacion(paisCodigo, propuestaCodigo, keyUserCodigo, codigoUsuarioPropuesta);
            }
        }*/
    }

    private void validarCalificacion() {
        String paisCodigo = itemUi.getPaisCodigo();
        String propuestaCodigo = itemUi.getCodigoPropuesta();
        String keyUserCodigo = itemUi.getKeyUser();
        String codigoUsuarioPropuesta = itemUi.getCodigoUsuarioPropuesta();
        Log.d(TAG, "tipo estado : " + itemUi.getCotiEstado());
        if (itemUi.getPropuestaEstado() != null) {
            if (itemUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO) &&
                    itemUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_FINALIZADO)) {//ESTADO_ESPECIALISTA_PAGADO
                initiValidarCalificacion(paisCodigo, propuestaCodigo, keyUserCodigo, codigoUsuarioPropuesta);
            }
        }
    }


    @Override
    public void onClickCalificar() {
//        String paisCodigo = itemUi.getPaisCodigo();
//        String propuestaCodigo = itemUi.getCodigoPropuesta();
//        String keyUserCodigo = itemUi.getKeyUser();
//        String codigoUsuarioPropuesta = itemUi.getCodigoUsuarioPropuesta();
//        initiValidarCalificacion(paisCodigo, propuestaCodigo, keyUserCodigo, codigoUsuarioPropuesta);
        String nombreCliente;

        String paisCliente = clienteResponse.getPaisImagen();
        String imagenCliente = clienteResponse.getFoto();
        if (clienteResponse.getUsuRazonSocial() != null) {
            nombreCliente = clienteResponse.getUsuRazonSocial();
            if (view != null)
                view.initStartCalificarClienteActivity(itemUi, nombreCliente, paisCliente, imagenCliente);
            return;
        }
        nombreCliente = clienteResponse.getNombre();
        if (view != null)
            view.initStartCalificarClienteActivity(itemUi, nombreCliente, paisCliente, imagenCliente);


    }

    private void initiValidarCalificacion(String paisCodigo, String propuestaCodigo, String keyUserCodigo, String codigoUsuarioPropuesta) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        apiService.validarCliente(paisCodigo,
                propuestaCodigo,
                keyUserCodigo,
                codigoUsuarioPropuesta).enqueue(new Callback<DefaultResponseEstados>() {

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
                            String mensaje = defaultResponse.getMessage();
                            Log.d(TAG, "RUBRO_USUARIO_EXISTE ");
                            //if (view != null) view.mostrarMensaje(mensaje);
                            if (view != null) view.ocultarButtonCalificar();
                            break;
                        case DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE:
                            if (view != null) view.mostrarButtonCalificar();
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
}
