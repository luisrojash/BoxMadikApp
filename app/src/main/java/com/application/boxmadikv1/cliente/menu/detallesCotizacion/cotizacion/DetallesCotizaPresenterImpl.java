package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.cliente.ListaCotizaResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.useCase.ObtenerCotizacionCliente;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class DetallesCotizaPresenterImpl extends BaseFragmentPresenterImpl<DetallesCotizaView> implements DetallesCotizaPresenter {

    public static final String TAG = DetallesCotizaPresenterImpl.class.getSimpleName();
    private ObtenerCotizacionCliente obtenerCotizacionCliente;

    public DetallesCotizaPresenterImpl(UseCaseHandler handler, Resources res, ObtenerCotizacionCliente obtenerCotizacionCliente) {
        super(handler, res);
        this.obtenerCotizacionCliente = obtenerCotizacionCliente;
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
        initUseCaseCotizaciones(detallesCotizacionUi);
    }

    private List<CotizacionesUi> cotizacionesUis;

    private void initUseCaseCotizaciones(DetallesCotizacionUi detallesCotizacionUi) {
        String usu_codigo = detallesCotizacionUi.getKeyUser();
        String pais_codigo = detallesCotizacionUi.getPaisCodigo();
        String propuesta_inicial_codigo = detallesCotizacionUi.getIdPropuesta();
        Log.d(TAG, "propuesta_inicial_codigo : " + propuesta_inicial_codigo);
        validarTipoEstado(detallesCotizacionUi);
        //String estado_coti= detallesCotizacionUi.getCotiEstado();
        if (view != null) view.mostrarProgressBar();
        handler.execute(obtenerCotizacionCliente, new ObtenerCotizacionCliente.RequestValues(usu_codigo,
                        pais_codigo,
                        propuesta_inicial_codigo,
                        tipoEstado),
                new UseCase.UseCaseCallback<ObtenerCotizacionCliente.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerCotizacionCliente.ResponseValue response) {
                        if (response == null) {
                            //view.mostrarMensaje("Errores con la Conexion");
                            return;
                        }

                        if (response != null) {
                            if (response.getListaCotizacionResponse() == null) {
                                if (view != null) {
                                    // view.mostrarMensaje(response.getListaCotizacionResponse().getMessage());
                                    view.ocultarProgressBar();
                                }
                                return;
                            }
                            if (response.getListaCotizacionResponse().getError()) {
                                Log.d(TAG, "response.getListaCotizacionResponse().getError()");

                                if (view != null) {
                                    //view.mostrarMensaje(response.getListaCotizacionResponse().getMessage());
                                    view.ocultarProgressBar();
                                }
                                return;
                            } else {
                                /*Aqui es cuando no hay ningun Error*/
                                if (response.getListaCotizacionResponse().getCotizacionesResponseList() != null) {
                                    Log.d(TAG, "response.getListaCotizacionResponse().getCotizacionesResponseList() != null");
                                    List<ListaCotizaResponse.CotizacionesResponse> cotizacionesResponses
                                            = response.getListaCotizacionResponse().getCotizacionesResponseList();
                                    cotizacionesUis = llenarCotizaciones(cotizacionesResponses);
                                    if (view != null) {
                                        // view.mostrarMensaje(response.getListaCotizacionResponse().getMessage());
                                        view.ocultarProgressBar();
                                        view.mostrarListaCotiza(cotizacionesUis);
                                        enviarListaActivity(cotizacionesUis);
                                    }
                                    return;
                                } else {
                                    Log.d(TAG, "response.getListaCotizacionResponse().getCotizacionesResponseList() != null else");
                                    if (view != null) {
                                        // view.mostrarMensaje("Lista Vacia");
                                        view.ocultarProgressBar();
                                    }
                                    return;
                                }

                            }
                        } else {
                            if (view != null) {
                                // view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
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

    String tipoEstado;

    private void validarTipoEstado(DetallesCotizacionUi detallesCotizacionUi) {
        switch (detallesCotizacionUi.getTipoEstado()) {
            case DetallesCotizacionUi.ESTADO_CANCELADO:
                /*Oculta Eliminar*/
                tipoEstado = "0";
                break;
            case DetallesCotizacionUi.ESTADO_PENDIENTE:
                tipoEstado = "1";
                /*Muestra Eliminar*/
                break;
        }
    }

    private void enviarListaActivity(List<CotizacionesUi> cotizacionesUis) {
        if (view != null) view.enviarListaActividadPrincipal(cotizacionesUis);
    }

    private List<CotizacionesUi> llenarCotizaciones(List<ListaCotizaResponse.CotizacionesResponse> cotizacionesResponses) {
        List<CotizacionesUi> cotizacionesUiList = new ArrayList<>();
        for (ListaCotizaResponse.CotizacionesResponse cotizacionesResponse : cotizacionesResponses) {

            //Log.d(TAG,"cotizacionesResponse.getEstrellasUsuario() "+cotizacionesResponse.getEstrellasUsuario());
            CotizacionesUi cotizacionesUi = new CotizacionesUi();
            cotizacionesUi.setIdPropuesta(cotizacionesResponse.getCodigoPropuesta());
            cotizacionesUi.setIdCotizacion(cotizacionesResponse.getCodigoCotizacion());
            cotizacionesUi.setEstadoCotizacion(cotizacionesResponse.getEstadoCotizacion());
            cotizacionesUi.setIdUsuarioCotizacion(cotizacionesResponse.getCodigoUsuarioCotizacion());
            cotizacionesUi.setEstadoPropuesta(cotizacionesResponse.getEstadoPropuesta());
            // cotizacionesUi.setId(detallesCotizacionUi.getIdPropuesta());

            cotizacionesUi.setNombreEspecialista(obtenerUsuario(cotizacionesResponse));
            cotizacionesUi.setFecha(Constantes.f_fecha_letras(cotizacionesResponse.getCoti_fecha()));
            cotizacionesUi.setImagen(cotizacionesResponse.getUsu_foto());
            cotizacionesUi.setMonto(cotizacionesResponse.getCoti_monto_inicial());
            cotizacionesUi.setPuntuacion(Constantes.validateUsuCalificacion(cotizacionesResponse.getEstrellasUsuario()));
            cotizacionesUi.setUsuRazonSocial(cotizacionesResponse.getUsuRazonSocial());
            //cotizacionesUi.setPuntuacion(usuPuntuacion(cotizacionesResponse.getEstrellasUsuario()));

            cotizacionesUi.setCotiDescripcion(cotizacionesResponse.getCotiDescripcion());
            cotizacionesUi.setCotiPendiente(cotizacionesResponse.getCotiPendiente());
            cotizacionesUi.setCotiFinalizado(cotizacionesResponse.getCotiFinalizado());
            cotizacionesUi.setCotiAceptado(cotizacionesResponse.getCotiAceptado());
            cotizacionesUi.setUsuDireccion(cotizacionesResponse.getUsuDepartamento()+"-"+ cotizacionesResponse.getUsuDistrito());
            cotizacionesUi.setPaisImagen(cotizacionesResponse.getPaisImagen());
            cotizacionesUi.setUsuCelular(cotizacionesResponse.getUsuCelular());
            cotizacionesUi.setUsuEmail(cotizacionesResponse.getUsuEmail());
            cotizacionesUiList.add(cotizacionesUi);
        }
        return cotizacionesUiList;
    }

    private String obtenerUsuario(ListaCotizaResponse.CotizacionesResponse cotizacionesResponse) {
        String nombreUsuario;
        if (cotizacionesResponse.getUsu_nombre() == null || cotizacionesResponse.getUsu_nombre().isEmpty()) {
            nombreUsuario = cotizacionesResponse.getUsuRazonSocial();
        } else {
            nombreUsuario = cotizacionesResponse.getUsu_nombre() + " " + cotizacionesResponse.getUsu_apellidos();
        }
        return nombreUsuario;
    }

    private String usuPuntuacion(String estrellas) {
        String tipoEstrellas = "";
        if (Constantes.validateUsuCalificacion(estrellas) == null) {
            tipoEstrellas = "0.0";
        } else {
            tipoEstrellas = Constantes.validateUsuCalificacion(estrellas);
        }
        return tipoEstrellas;
    }


    DetallesCotizacionUi detallesCotizacionUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        Log.d(TAG, "DetallesDescripcionPresenterImpl : " + detallesCotizacionUi.getNombreProyecto());
    }


    @Override
    public void onClickDetalleCotizaciones(CotizacionesUi cotizacionesUi) {
        /*switch (cotizacionesUi.getEstadoCotizacion()) {
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_ACEPTADO");
                if (view != null)
                    view.initStartActivityCalifica(detallesCotizacionUi, cotizacionesUi);
                break;
            default:
                Log.d(TAG, " default");
                if (view != null)
                    view.startDatosCotizacionActivity(cotizacionesUi, detallesCotizacionUi);
                break;
        }*/
        if (view != null) view.startDatosCotizacionActivity(cotizacionesUi, detallesCotizacionUi);
    }

    Boolean estadoInternet;

    @Override
    public void onValidateInternet(Boolean estadoInternet, CotizacionesUi cotizacionesUi) {
        if (estadoInternet) {
            this.estadoInternet = estadoInternet;
            Log.d(TAG, "CONECTADO A INTERNET");
            onClickDetalleCotizaciones(cotizacionesUi);
        } else {
            this.estadoInternet = estadoInternet;
            Log.d(TAG, "DESCONECTADO A INTERNET");
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Intentelo mas tarde");
        }
    }
}
