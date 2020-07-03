package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;


public interface RevocacionPresenter extends BaseActivityPresenter<RevocacionView> {


    void onSpinnerTipoMotivoRevocacion(TipoMotivoRevocacionUi tipoMotivoRevocacionUi);

    void onSpinnerTipoCalidadTrabajo(TipoCalidadTrabajoUi tipoCalidadTrabajoUi);

    void onSpinnerSolucitaRevocante(TipoSolicitaRevocanteUi tipoSolucionRevocacionUi);

    void onProgressChanged(int value);

    void onClickEnviar(String observacion);

    void onKeyPais(String paisCodigo);
}
