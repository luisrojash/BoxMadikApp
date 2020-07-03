package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase.EnvioNotificacionRevocacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase.ObtenerListaMotivoRevocacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase.ObtenerListaSolucionRevocacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase.RegistrarRevocacionEspecialista;
import com.application.boxmadikv1.utils.Constantes;

public class RevocacionPresenterImpl extends BaseActivityPresenterImpl<RevocacionView> implements RevocacionPresenter {

    public static final String TAG = RevocacionPresenterImpl.class.getSimpleName();

    private ObtenerListaMotivoRevocacion obtenerListaMotivoRevocacion;
    private ObtenerListaSolucionRevocacion obtenerListaSolucionRevocacion;
    private RegistrarRevocacionEspecialista registrarRevocacionEspecialista;
    private EnvioNotificacionRevocacion envioNotificacionRevocacion;

    public RevocacionPresenterImpl(UseCaseHandler handler, Resources res, ObtenerListaMotivoRevocacion obtenerListaMotivoRevocacion, ObtenerListaSolucionRevocacion obtenerListaSolucionRevocacion, RegistrarRevocacionEspecialista registrarRevocacionEspecialista) {
        super(handler, res);
        this.obtenerListaMotivoRevocacion = obtenerListaMotivoRevocacion;
        this.obtenerListaSolucionRevocacion = obtenerListaSolucionRevocacion;
        this.registrarRevocacionEspecialista = registrarRevocacionEspecialista;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //if (view != null)view.ocultarTeclado();
    }


    int valorPorcentajeTrabajo;

    @Override
    public void onProgressChanged(int value) {
        this.valorPorcentajeTrabajo = value;
        if (view != null) view.mostrarProcentajeTrabajo(value);
    }

    String idTipSolucionRevocacion;

    @Override
    public void onSpinnerTipoSolucionRevocacion(String idTipSolucionRevocacion) {
        Log.d(TAG, "onSpinnerTipoSolucionRevocacion : " + idTipSolucionRevocacion);
        this.idTipSolucionRevocacion = idTipSolucionRevocacion;
    }

    String idTipoMotivoRevocacion;

    @Override
    public void onSpinnerTipoMotivoRevocacion(String idTipoMotivoRevocacion) {
        this.idTipoMotivoRevocacion = idTipoMotivoRevocacion;
    }

    @Override
    public void onClickEnviarRevocacion(String observacion) {

        Log.d(TAG, "valorPorcentajeTrabajo : " + valorPorcentajeTrabajo);
        if (idTipoMotivoRevocacion.equals("0")) {
            if (view != null) view.mostrarMensaje("Ingrese Motivo Revocacion");
            return;
        }
       /* else if (valorPorcentajeTrabajo == 0) {
            if (view != null) view.mostrarMensaje("Ingrese Valor mayor 0 %");
            return;
        } */
        else if (idTipSolucionRevocacion.equals("0") || idTipSolucionRevocacion == null) {
            if (view != null) view.mostrarMensaje("Ingrese Solución Revocacion");
            return;
        } else if (observacion.isEmpty()) {
            if (view != null) view.mostrarMensajeErrorEditTextObservacion("Ingrese Observación");
            return;
        } else {
            //String validaObservacion=Constantes.isResultadoEspecial(observacion);
            String removeAcentosCadena = Constantes.removeAcentos(observacion);
            String isPrimeroResultadoCharacter = Constantes.isPrimeroResultadoCharacter(removeAcentosCadena);
            String isSegundoresultadoCharacter = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacter);
            initUseCaseRegistrarRevocacion(itemUi, idTipSolucionRevocacion, idTipoMotivoRevocacion, valorPorcentajeTrabajo, isSegundoresultadoCharacter);
            Log.d(TAG, "Ya podemos registrar PAPu !!" + itemUi.getPaisCodigo());
        }
    }

    private void initUseCaseRegistrarRevocacion(final ItemUi itemUi, String idTipSolucionRevocacion, String idTipoMotivoRevocacion, int valorPorcentajeTrabajo, String observacion) {
        if (view != null) view.mostrarDialogProgressBar();
        handler.execute(registrarRevocacionEspecialista, new RegistrarRevocacionEspecialista.RequestValues(itemUi, idTipSolucionRevocacion, idTipoMotivoRevocacion, valorPorcentajeTrabajo, observacion),
                new UseCase.UseCaseCallback<RegistrarRevocacionEspecialista.ResponseValue>() {
                    @Override
                    public void onSuccess(RegistrarRevocacionEspecialista.ResponseValue response) {
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
                                    view.ocultarDialogProgressBar();

                                }
                                initEnvioNotificacionRevocados(itemUi);
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

    private void initEnvioNotificacionRevocados(ItemUi itemUi) {
       /* handler.execute(envioNotificacionRevocacion, new EnvioNotificacionRevocacion.RequestValues(Constantes.GRUPO_NOTIFICACION_CLIENTE,
                        Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION,
                        itemUi.getCodigoUsuarioPropuesta(),
                        itemUi.getCodigoPropuesta(),
                        itemUi.getPaisCodigo(),
                        cotizacionesUi.getIdCotizacion()),
                new UseCase.UseCaseCallback<EnvioNotificacionRevocacion.ResponseValue>() {
                    @Override
                    public void onSuccess(EnvioNotificacionRevocacion.ResponseValue response) {

                    }

                    @Override
                    public void onError() {

                    }
                });*/
    }

    @Override
    public void onStart() {
        super.onStart();
        initUseCaseMotivoRevocacion();
        initUseCaseSolucionRevocacion();
        initVistasInicial(itemUi);
    }

    private void initUseCaseSolucionRevocacion() {
        handler.execute(obtenerListaSolucionRevocacion, new ObtenerListaSolucionRevocacion.RequestValues(),
                new UseCase.UseCaseCallback<ObtenerListaSolucionRevocacion.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerListaSolucionRevocacion.ResponseValue response) {
                        if (response.getTipoSolucionRevocacionUis() == null) return;
                        if (view != null)
                            view.mostrarListaSolucionRevocacion(response.getTipoSolucionRevocacionUis());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseCaseMotivoRevocacion() {
        handler.execute(obtenerListaMotivoRevocacion, new ObtenerListaMotivoRevocacion.RequestValues(),
                new UseCase.UseCaseCallback<ObtenerListaMotivoRevocacion.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerListaMotivoRevocacion.ResponseValue response) {
                        if (response.getTipoMotivoRevocacionUiList() == null) return;
                        if (view != null)
                            view.mostrarListaMotivoRevocacion(response.getTipoMotivoRevocacionUiList());

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    ItemUi itemUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.itemUi = extras.getParcelable("itemUi");
        Log.d(TAG, "itemUi : " + itemUi.getNombrePropuesta() + "");
        initObtenerCotizacion(itemUi.getCodigoPropuesta(), itemUi.getCodigoUsuarioPropuesta());

    }

    private void initObtenerCotizacion(String codigoPropuesta, String codigoUsuarioPropuesta) {
    }

    private void initVistasInicial(ItemUi itemUi) {
        if (view != null) {
            view.mostrarDataInicial(itemUi);
        }
    }
}
