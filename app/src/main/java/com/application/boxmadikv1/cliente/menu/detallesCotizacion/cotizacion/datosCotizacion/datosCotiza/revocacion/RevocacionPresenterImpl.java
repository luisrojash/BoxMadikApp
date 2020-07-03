package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.EnvioNotificacionRevocacion;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.MostrarListaTipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.MostrarListaTipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.MostrarListaTipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.RegistrarRevocacion;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.useCase.CambiarEstadoRevocados;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

public class RevocacionPresenterImpl extends BaseActivityPresenterImpl<RevocacionView> implements RevocacionPresenter {

    public static final String CREADO_CORRECTAMENTE_REVOCACION_CLIENTE = "RevocacionPresenterImplcliente";
    public static final String TAG = RevocacionPresenterImpl.class.getSimpleName();

    private MostrarListaTipoCalidadTrabajoUi mostrarListaTipoCalidadTrabajoUi;
    private MostrarListaTipoMotivoRevocacionUi mostrarListaTipoMotivoRevocacionUi;
    private MostrarListaTipoSolicitaRevocanteUi mostrarListaTipoSolicitaRevocanteUi;
    private RegistrarRevocacion registrarRevocacion;
    private CambiarEstadoRevocados cambiarEstadoRevocados;
    private EnvioNotificacionRevocacion envioNotificacionRevocacion;

    public RevocacionPresenterImpl(UseCaseHandler handler, Resources res, MostrarListaTipoCalidadTrabajoUi mostrarListaTipoCalidadTrabajoUi,
                                   MostrarListaTipoMotivoRevocacionUi mostrarListaTipoMotivoRevocacionUi,
                                   MostrarListaTipoSolicitaRevocanteUi mostrarListaTipoSolicitaRevocanteUi,
                                   RegistrarRevocacion registrarRevocacion,
                                   CambiarEstadoRevocados cambiarEstadoRevocados,
                                   EnvioNotificacionRevocacion envioNotificacionRevocacion) {
        super(handler, res);
        this.mostrarListaTipoCalidadTrabajoUi = mostrarListaTipoCalidadTrabajoUi;
        this.mostrarListaTipoMotivoRevocacionUi = mostrarListaTipoMotivoRevocacionUi;
        this.mostrarListaTipoSolicitaRevocanteUi = mostrarListaTipoSolicitaRevocanteUi;
        this.registrarRevocacion = registrarRevocacion;
        this.cambiarEstadoRevocados = cambiarEstadoRevocados;
        this.envioNotificacionRevocacion = envioNotificacionRevocacion;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if(view!=null)view.mostrarData(detallesCotizacionUi,cotizacionesUi);
        initUseCaseTipoCalidadTrabajo();
        initUseCaseTipoMotivoRevocacion();
        initUseCaseTipoSolicitaRevocante();
    }

    private void initUseCaseTipoSolicitaRevocante() {
        handler.execute(mostrarListaTipoSolicitaRevocanteUi, new MostrarListaTipoSolicitaRevocanteUi.RequestValues(),
                new UseCase.UseCaseCallback<MostrarListaTipoSolicitaRevocanteUi.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarListaTipoSolicitaRevocanteUi.ResponseValue response) {
                        if (response.getSolicitaRevocanteUiList() == null) return;
                        if (view != null)
                            view.mostrarListaTipoSolicitaRevocante(response.getSolicitaRevocanteUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseCaseTipoMotivoRevocacion() {
        handler.execute(mostrarListaTipoMotivoRevocacionUi, new MostrarListaTipoMotivoRevocacionUi.RequestValues(),
                new UseCase.UseCaseCallback<MostrarListaTipoMotivoRevocacionUi.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarListaTipoMotivoRevocacionUi.ResponseValue response) {
                        if (response.getTipoMotivoRevocacionUiList() == null) return;
                        if (view != null)
                            view.mostrarListaTipoMotivoRevocacion(response.getTipoMotivoRevocacionUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseCaseTipoCalidadTrabajo() {
        handler.execute(mostrarListaTipoCalidadTrabajoUi, new MostrarListaTipoCalidadTrabajoUi.RequestValues(),
                new UseCase.UseCaseCallback<MostrarListaTipoCalidadTrabajoUi.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarListaTipoCalidadTrabajoUi.ResponseValue response) {
                        if (response.getTipoCalidadTrabajoUiList() == null) return;
                        if (view != null)
                            view.mostrarListaTipoCalidadTrabajo(response.getTipoCalidadTrabajoUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        this.cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);

    }

    String idTipoMotivoRevocacion;

    @Override
    public void onSpinnerTipoMotivoRevocacion(TipoMotivoRevocacionUi tipoMotivoRevocacionUi) {
        if (tipoMotivoRevocacionUi == null) {
            Log.d(TAG, "Error: spinnerMotivoRevocacion");
            return;
        }
        this.idTipoMotivoRevocacion = tipoMotivoRevocacionUi.getId();
    }

    String idTipoCalidadTrabajo;

    @Override
    public void onSpinnerTipoCalidadTrabajo(TipoCalidadTrabajoUi tipoCalidadTrabajoUi) {
        if (tipoCalidadTrabajoUi == null) {
            Log.d(TAG, "Error: spinnerCalidadtrabajo");
            return;
        }
        this.idTipoCalidadTrabajo = tipoCalidadTrabajoUi.getId();
    }

    String idSolicitaRevocante;

    @Override
    public void onSpinnerSolucitaRevocante(TipoSolicitaRevocanteUi tipoSolucionRevocacionUi) {
        if (tipoSolucionRevocacionUi == null) {
            Log.d(TAG, "Error: spinnerSolucionRevocante");
            return;
        }
        this.idSolicitaRevocante = tipoSolucionRevocacionUi.getId();
    }


    int valorPorcentajeTrabajo;

    @Override
    public void onProgressChanged(int value) {
        this.valorPorcentajeTrabajo = value;
        if (view != null) view.mostrarProcentajeTrabajo(value);
    }

    @Override
    public void onClickEnviar(String observacion) {
        if(view!=null)view.deshhabilitarButtonRevocacion();
        if (idTipoMotivoRevocacion.equals("0") || idTipoMotivoRevocacion.isEmpty() || idTipoMotivoRevocacion == null) {
            String mensaje = "Seleccione Motivo Revocación";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilitarButtonRevocacion();
            }
            return;
        }
        if (idTipoCalidadTrabajo.equals("0") || idTipoCalidadTrabajo.isEmpty() || idTipoCalidadTrabajo == null) {
            String mensaje = "Seleccione Calidad Trabajo";
            if (view != null){
                view.mostrarMensaje(mensaje);
                view.habilitarButtonRevocacion();
            }
            return;
        }
        /*if (valorPorcentajeTrabajo == 0) {
            String mensaje = "Evalue el porcentaje de trabajo";
            if (view != null){
                view.mostrarMensaje(mensaje);
                view.habilitarButtonRevocacion();
            }
            return;
        }
        if (idSolicitaRevocante.equals("0") || idSolicitaRevocante.isEmpty() || idSolicitaRevocante == null) {
            String mensaje = "Seleccione Solicitud Revocante";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilitarButtonRevocacion();
            }
            return;
        }
        */
        if (observacion.isEmpty() || observacion.equals(" ") || observacion == null) {
            String mensaje = "Escriba la observación";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilitarButtonRevocacion();
            }
            return;
        }

        String removeAcentosCadena = Constantes.removeAcentos(observacion);
        String isPrimeroResultadoCharacter = Constantes.isPrimeroResultadoCharacter(removeAcentosCadena);
        String isSegundoresultadoCharacter = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacter);

        initUsecaseRegistrarRevocacion(isSegundoresultadoCharacter);

    }

    String paisCodigo;

    @Override
    public void onKeyPais(String paisCodigo) {
        this.paisCodigo = paisCodigo;
    }

    private void initUsecaseRegistrarRevocacion(String observacion) {
        String valorPorcentaje = String.valueOf(valorPorcentajeTrabajo);
        if (view != null) view.mostrarDialogProgressBar();
        detallesCotizacionUi.setPaisCodigo(paisCodigo);


        handler.execute(registrarRevocacion, new RegistrarRevocacion.RequestValues(
                        idTipoMotivoRevocacion,
                        idTipoCalidadTrabajo,
                        valorPorcentaje,
                        idSolicitaRevocante,
                        observacion,
                        detallesCotizacionUi,
                        cotizacionesUi),
                new UseCase.UseCaseCallback<RegistrarRevocacion.ResponseValue>() {
                    @Override
                    public void onSuccess(RegistrarRevocacion.ResponseValue response) {
                        if (response != null) {
                            if (response.getDefaultResponse() == null) {
                                if (view != null) {
                                    try {
                                        view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    }catch (Exception  e){
                                        view.mostrarMensaje("Ocurrio algun error intentelo mas tarde");
                                    }

                                    view.ocultarDialogProgressBar();
                                    view.habilitarButtonRevocacion();
                                }
                                return;
                            }
                            if (response.getDefaultResponse().getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    view.ocultarDialogProgressBar();
                                    view.habilitarButtonRevocacion();
                                }
                                return;
                            } else {
                                if (view != null) {
                                    // view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    initUseCaseCambiarEstado();
                                    initEnvioNotificacionRevocados(cotizacionesUi, detallesCotizacionUi);
                                    /*view.ocultarDialogProgressBar();
                                    view.initStarActivityDatosCotiza(CREADO_CORRECTAMENTE_REVOCACION_CLIENTE,
                                            detallesCotizacionUi,cotizacionesUi);*/
                                }
                                return;
                            }
                        } else {
                            if (view != null) {
                                view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                                view.ocultarDialogProgressBar();
                                view.habilitarButtonRevocacion();
                            }
                            return;
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initEnvioNotificacionRevocados(CotizacionesUi cotizacionesUi, DetallesCotizacionUi detallesCotizacionUi) {
        handler.execute(envioNotificacionRevocacion, new EnvioNotificacionRevocacion.RequestValues(Constantes.GRUPO_NOTIFICACION_CLIENTE,
                        Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION,
                        detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                        detallesCotizacionUi.getIdPropuesta(),
                        paisCodigo,
                        cotizacionesUi.getIdCotizacion()),
                new UseCase.UseCaseCallback<EnvioNotificacionRevocacion.ResponseValue>() {
                    @Override
                    public void onSuccess(EnvioNotificacionRevocacion.ResponseValue response) {

                    }

                    @Override
                    public void onError() {

                    }
                });


    }

    private void initUseCaseCambiarEstado() {
        handler.execute(cambiarEstadoRevocados, new CambiarEstadoRevocados.RequestValues(detallesCotizacionUi, cotizacionesUi),
                new UseCase.UseCaseCallback<CambiarEstadoRevocados.ResponseValue>() {
                    @Override
                    public void onSuccess(CambiarEstadoRevocados.ResponseValue response) {
                        if (response != null) {
                            if (response.getDefaultResponse() == null) {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    view.ocultarDialogProgressBar();
                                }
                                return;
                            }
                            if (response.getDefaultResponse().getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    view.ocultarDialogProgressBar();
                                }
                                return;
                            } else {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    //initUseCaseCambiarEstado();
                                    view.ocultarDialogProgressBar();
                                    /*view.initStarActivityDatosCotiza(CREADO_CORRECTAMENTE_REVOCACION_CLIENTE,
                                            detallesCotizacionUi, cotizacionesUi);*/
                                    view.initStartActivityMainPrincipal();
                                }
                                return;
                            }
                        } else {
                            if (view != null) {
                                view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                                view.ocultarDialogProgressBar();
                            }
                            return;
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
