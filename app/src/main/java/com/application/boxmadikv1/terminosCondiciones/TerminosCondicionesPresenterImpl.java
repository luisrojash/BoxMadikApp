package com.application.boxmadikv1.terminosCondiciones;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.terminosCondiciones.useCase.ObtenerTerminos;
import com.application.boxmadikv1.utils.Constantes;

public class TerminosCondicionesPresenterImpl extends BaseActivityPresenterImpl<TerminosCondicionesView> implements TerminosCondicionesPresenter {

    public static final String TAG = TerminosCondicionesPresenterImpl.class.getSimpleName();
    private ObtenerTerminos obtenerTerminos;

    public TerminosCondicionesPresenterImpl(UseCaseHandler handler, Resources res, ObtenerTerminos obtenerTerminos) {
        super(handler, res);
        this.obtenerTerminos = obtenerTerminos;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClickSiguiente() {
       /* if (aceptarTermino == true) {
            if (view != null) view.mostrarMensaje("Acepte los Terminos y Condiciones!");
        } else {*/
            switch (tipoEstados) {
                case Constantes.TERMINOS_CONDICIONES_REGISTRAR_USER:
                    if (view != null) view.initStartActivityRegistroUser(aceptarTermino);
                    Log.d(TAG, "TERMINOS_CONDICIONES_REGISTRAR_USER");
                    break;
                case Constantes.TERMINOS_CONDICIONES_PAGAR:
                    if (view != null) view.initStartActivityReportePago(detallesCotizacionUi,cotizacionesUi);
                    Log.d(TAG, "TERMINOS_CONDICIONES_PAGAR");
                    break;
                case Constantes.TERMINOS_CONDICIONES_REVOCACION:
                    if (view != null) view.initStartActivityReportePago(detallesCotizacionUi,cotizacionesUi);
                    Log.d(TAG, "TERMINOS_CONDICIONES_REVOCACION");
                    break;
                default:
                    break;
            }

       // }
    }

    boolean aceptarTermino = true;

    @Override
    public void onAceptarTerminosCondiciones() {
        if (aceptarTermino) {
            aceptarTermino = false;
            Log.d(TAG, "onAceptarTerminosCondiciones " + aceptarTermino + "");
            if (view != null) view.mostrarCheckTrue();
        } else {
            aceptarTermino = true;
            Log.d(TAG, "onAceptarTerminosCondiciones " + aceptarTermino + "");
            if (view != null) view.mostrarCheckFalse();
        }
    }

    int tipoEstados;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        tipoEstados = extras.getInt(Constantes.EXTRAS_TERMINOS_CONDICIONES);

    }

    @Override
    public void onStart() {
        super.onStart();
        validarTipoEstados(extras,tipoEstados);

    }
    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;
    private void validarTipoEstados(Bundle bundle,int tipoEstados) {
        switch (tipoEstados) {
            case Constantes.TERMINOS_CONDICIONES_REGISTRAR_USER:
                String terminosRegistro="Registro Usuarios";
                if(view!=null)view.mostrarTituloTerminos(terminosRegistro);
                initUseCaseTerminosCondiciones(tipoEstados);
                Log.d(TAG, "TERMINOS_CONDICIONES_REGISTRAR_USER");
                break;
            case Constantes.TERMINOS_CONDICIONES_PAGAR:
                this.detallesCotizacionUi = bundle.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
                this.cotizacionesUi = bundle.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
                String terminosRegistroPagos="Registro Pagos";
                if(view!=null)view.mostrarTituloTerminos(terminosRegistroPagos);
                Log.d(TAG, "TERMINOS_CONDICIONES_PAGAR");
                break;
            case Constantes.TERMINOS_CONDICIONES_REVOCACION:
                this.detallesCotizacionUi = bundle.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
                this.cotizacionesUi = bundle.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
                Log.d(TAG, "TERMINOS_CONDICIONES_REVOCACION");
                String terminosRevocacion="Registro Revocacion";
                if(view!=null)view.mostrarTituloTerminos(terminosRevocacion);
                break;
            default:
                break;
        }
    }


    private void initUseCaseTerminosCondiciones(final int tipoEstados) {
        handler.execute(obtenerTerminos, new ObtenerTerminos.RequestValues(tipoEstados),
                new UseCase.UseCaseCallback<ObtenerTerminos.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerTerminos.ResponseValue response) {
                        if(response==null)return;
                        if(view!=null)view.mostrarDatosTerminos("","");
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
