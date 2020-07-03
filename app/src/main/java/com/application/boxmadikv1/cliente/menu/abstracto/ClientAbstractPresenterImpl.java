package com.application.boxmadikv1.cliente.menu.abstracto;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.cliente.menu.abstracto.entidad.ClienteEstadosUi;
import com.application.boxmadikv1.cliente.menu.abstracto.useCase.ListarClienteEstados;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

public class ClientAbstractPresenterImpl extends BaseFragmentPresenterImpl<ClientAbstractView> implements ClientAbstractPresenter {

    public static final String TAG = ClientAbstractPresenterImpl.class.getSimpleName();
    private ListarClienteEstados listarClienteEstados;

    public ClientAbstractPresenterImpl(UseCaseHandler handler, Resources res, ListarClienteEstados listarClienteEstados) {
        super(handler, res);
        this.listarClienteEstados = listarClienteEstados;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    String keyUser;
    String tipoEstado;
    String codigoPais;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras != null) {
            keyUser = extras.getString("keyUser");
            tipoEstado = extras.getString("tiposEstadoCliente");
            codigoPais = extras.getString("codigoPais");
            Log.d(TAG, "Extras : " + keyUser +
                    "tipoEstado : " + tipoEstado +
                    "codigoPais : " + codigoPais);
        }
    }

    @Override
    public void onClickEstado(ClienteEstadosUi clienteEstadosUi) {

        if (view != null) view.onStartActivityDetalles(detallesCotizacionUi(clienteEstadosUi));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (view != null) view.mostrarProgressBar();
        initValidateTipoEstados(tipoEstado);

    }


    int estadosClienteGlobal;

    private void initValidateTipoEstados(String tipoEstado) {
        /*Guarda los siguientes estados:\\\\n
        (0= Cancelado; 1=Pendiente; 2= En Proceso; 3= Finalizado; 4= Pagado; 5= Revocado)*/
        //  if (clienteEstadosUiList == null)return; //clienteEstadosUiList.clear();
        switch (tipoEstado) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_CANCELADOS:
                estadosClienteGlobal = DetallesCotizacionUi.ESTADO_CANCELADO;
                estadosClienteGlobal = DetallesCotizacionUi.ESTADO_PROCESO;
                //initiUseCaseMostrasListaEstados(Constantes.PROPUESTA_ESTADO_CLIENTE_CANCELADOS);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                estadosClienteGlobal = DetallesCotizacionUi.ESTADO_PENDIENTE;
                initiUseCaseMostrasListaEstados(Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:
                estadosClienteGlobal = DetallesCotizacionUi.ESTADO_PROCESO;
                initiUseCaseMostrasListaEstados(Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO:
                estadosClienteGlobal = DetallesCotizacionUi.ESTADO_FINALIZADO;
                initiUseCaseMostrasListaEstados(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO:
                estadosClienteGlobal = DetallesCotizacionUi.ESTADO_PAGADOS;
                initiUseCaseMostrasListaEstados(Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
                estadosClienteGlobal = DetallesCotizacionUi.ESTADO_REVOCADOS;
                initiUseCaseMostrasListaEstados(Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS);
                validarPintarColorTabs();
                break;
            default:
                break;
        }
    }

    private void validarPintarColorTabs() {
        if(view!=null)view.initListener();
    }

    List<ClienteEstadosUi> clienteEstadosUiList;

    private void initiUseCaseMostrasListaEstados(String propuestaEstado) {
        handler.execute(listarClienteEstados, new ListarClienteEstados.RequestValues(keyUser, propuestaEstado, codigoPais),
                new UseCase.UseCaseCallback<ListarClienteEstados.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarClienteEstados.ResponseValue response) {
                        if (response == null) {
                            if (view != null) {
                                String mensaje = "Error al Cargar los Datos, Intentelo más Tarde";
                                view.mostrarMensaje("Error al Cargar los Datos, Intentelo más Tarde");
                                Log.d(TAG, mensaje);
                                view.ocultarProgressBar();
                            }
                            return;
                        } else {
                            ListaPropuestaPendienteResponse body = response.getPendienteResponse();

                            if (body == null) return;
                            /*True porque hay error*/
                            if (body.getError()) {
                                if (view != null) {
                                    // view.mostrarMensaje(body.getMessage());
                                    Log.d(TAG, "body.getMessage()" + body.getMessage());
                                    view.ocultarProgressBar();
                                }
                                return;
                            }

                            clienteEstadosUiList = body.getClienteEstadosUis();
                            if (clienteEstadosUiList.isEmpty() || clienteEstadosUiList == null) {
                                if (view != null) {
                                    view.mostrarEmpty();
                                    Log.d(TAG, "Lista Vacia");
                                    view.ocultarProgressBar();
                                }
                                return;
                            } else {
                                if (view != null) {
                                    view.mostraListaClienteEstados(clienteEstadosUiList);
                                    view.ocultarProgressBar();
                                    validarPintarColorTabs();
                                }
                                return;
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private DetallesCotizacionUi detallesCotizacionUi(ClienteEstadosUi clienteEstadosUi) {
        DetallesCotizacionUi detallesCotizacionUi = new DetallesCotizacionUi();
        detallesCotizacionUi.setIdPropuesta(clienteEstadosUi.getId());
        detallesCotizacionUi.setNombreProyecto(clienteEstadosUi.getNombreProyecto());
        detallesCotizacionUi.setDetallesPropuesta(clienteEstadosUi.getDetallesProyecto());
        detallesCotizacionUi.setImageRubro(clienteEstadosUi.getImage());
        detallesCotizacionUi.setFechaPropuesta(clienteEstadosUi.getFechaRealizo());
        detallesCotizacionUi.setCodigoRangoDias(clienteEstadosUi.getCodigoRangoDias());
        detallesCotizacionUi.setDescripcionRangoDias(clienteEstadosUi.getDescripcionRangoDias());
        detallesCotizacionUi.setCodigoRangoTurno(clienteEstadosUi.getCodigoRangoTurno());
        detallesCotizacionUi.setDescripcionRangoTurno(clienteEstadosUi.getDescripcionRangoTurno());
        detallesCotizacionUi.setCodigoRangoPrecio(clienteEstadosUi.getCodigoRangoPresupuesto());
        detallesCotizacionUi.setDescripcionRangoPrecio(clienteEstadosUi.getDescripcionRangoPresupuesto());
        detallesCotizacionUi.setNombreRubro(clienteEstadosUi.getNombreRubro());
        detallesCotizacionUi.setNombreOficio(clienteEstadosUi.getNombreOficio());
        detallesCotizacionUi.setNumeroCotizacion(clienteEstadosUi.getNumeroCotizacion());
        detallesCotizacionUi.setCostoPromedio(clienteEstadosUi.getPromedioCotizacion());
        detallesCotizacionUi.setKeyUser(keyUser);
        detallesCotizacionUi.setUsuarioCodigoPropuesta(clienteEstadosUi.getUsuarioCodigoPropuestaInicial());
        detallesCotizacionUi.setTipoEstado(estadosClienteGlobal);
        detallesCotizacionUi.setNombreDepartamento(clienteEstadosUi.getNombreDepartamento());
        detallesCotizacionUi.setNombreDistrito(clienteEstadosUi.getNombreDistrito());
        detallesCotizacionUi.setPaisCodigo(codigoPais);
        return detallesCotizacionUi;
    }


}
