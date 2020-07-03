package com.application.boxmadikv1.cliente.menu.clientePendiente;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.cliente.menu.clientePendiente.useCase.ListarClientePendiente;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import java.util.List;

public class ClientePendientePresenterImpl extends BaseFragmentPresenterImpl<ClientePendienteView> implements ClientePendientePresenter {

    public static final String TAG = ClientePendientePresenterImpl.class.getSimpleName();

    private ListarClientePendiente listarClientePendiente;

    public ClientePendientePresenterImpl(UseCaseHandler handler, Resources res, ListarClientePendiente listarClientePendiente) {
        super(handler, res);
        this.listarClientePendiente = listarClientePendiente;
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
        if (view != null) view.mostrarProgressBar();
        initiUseCaseMostrasListaClientes();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //  initiUseCaseMostrasListaClientes();
    }

    private void initiUseCaseMostrasListaClientes() {

        //if (view != null) view.mostrarProgressBar();
        handler.execute(listarClientePendiente, new ListarClientePendiente.RequestValues(keyUser),
                new UseCase.UseCaseCallback<ListarClientePendiente.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarClientePendiente.ResponseValue response) {
                        if (response == null) {
                            if (view != null) {
                                view.mostrarMensaje("Error al Cargar los Datos, Intentelo más Tarde");
                                view.ocultarProgressBar();
                            }
                            return;
                        } else {
                            ListaPropuestaPendienteResponse body = response.getPendienteResponse();

                            if (body == null) return;
                            /*True porque hay error*/
                            if (body.getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(body.getMessage());
                                    view.ocultarProgressBar();
                                }
                                return;
                            }

                            /*List<ClientePendienteUi> pendienteUiList = body.getClientePendienteUiList();
                            if (pendienteUiList.isEmpty() || pendienteUiList == null) {
                                if (view != null) {
                                    view.mostrarMensaje("Lista Vacia ");
                                    view.ocultarProgressBar();
                                }
                                return;
                            } else {
                                if (view != null) {
                                    view.mostraListaClienteTodos(pendienteUiList);
                                    view.ocultarProgressBar();
                                }
                                return;
                            }*/
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    String keyUser;

    @Override
    public void setExtras(Bundle extras) {
        if (extras != null) {
            keyUser = extras.getString("keyUser");
            Log.d(TAG, "Extras : " + keyUser);
        }
    }

    @Override
    public void onClickPendiente(ClientePendienteUi clientePendienteUi) {

        if (view != null) view.onStartActivityDetalles(detallesCotizacionUi(clientePendienteUi));
    }

    private DetallesCotizacionUi detallesCotizacionUi(ClientePendienteUi clientePendienteUi) {
        DetallesCotizacionUi detallesCotizacionUi = new DetallesCotizacionUi();
        detallesCotizacionUi.setIdPropuesta(clientePendienteUi.getId());
        detallesCotizacionUi.setNombreProyecto(clientePendienteUi.getNombreProyecto());
        detallesCotizacionUi.setDetallesPropuesta(clientePendienteUi.getDetallesProyecto());
        detallesCotizacionUi.setImageRubro(clientePendienteUi.getImage());
        detallesCotizacionUi.setFechaPropuesta(clientePendienteUi.getFechaRealizo());
        detallesCotizacionUi.setCodigoRangoDias(clientePendienteUi.getCodigoRangoDias());
        detallesCotizacionUi.setDescripcionRangoDias(clientePendienteUi.getDescripcionRangoDias());
        detallesCotizacionUi.setCodigoRangoTurno(clientePendienteUi.getCodigoRangoTurno());
        detallesCotizacionUi.setDescripcionRangoTurno(clientePendienteUi.getDescripcionRangoTurno());
        detallesCotizacionUi.setCodigoRangoPrecio(clientePendienteUi.getCodigoRangoPresupuesto());
        detallesCotizacionUi.setDescripcionRangoPrecio(clientePendienteUi.getDescripcionRangoPresupuesto());
        detallesCotizacionUi.setNombreRubro(clientePendienteUi.getNombreRubro());
        detallesCotizacionUi.setNombreOficio(clientePendienteUi.getNombreOficio());
        detallesCotizacionUi.setNumeroCotizacion(clientePendienteUi.getNumeroCotizacion());
        detallesCotizacionUi.setCostoPromedio(clientePendienteUi.getPromedioCotizacion());
        detallesCotizacionUi.setKeyUser(keyUser);
        detallesCotizacionUi.setUsuarioCodigoPropuesta(clientePendienteUi.getUsuarioCodigoPropuestaInicial());
        detallesCotizacionUi.setTipoEstado(DetallesCotizacionUi.ESTADO_PENDIENTE);
        return detallesCotizacionUi;
    }
}
