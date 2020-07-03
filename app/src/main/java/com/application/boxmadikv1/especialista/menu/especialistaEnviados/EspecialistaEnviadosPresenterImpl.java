package com.application.boxmadikv1.especialista.menu.especialistaEnviados;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.useCase.EliminarCotizacion;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.useCase.ListaCotizaciones;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.useCase.ListarEspecialistaEnviados;

import java.util.ArrayList;
import java.util.List;

public class EspecialistaEnviadosPresenterImpl extends BaseFragmentPresenterImpl<EspecialistaEnviadosView> implements EspecialistaEnviadosPresenter {

    public static final String TAG = EspecialistaEnviadosPresenterImpl.class.getSimpleName();

    private ListarEspecialistaEnviados listarClienteTodos;
    private ListaCotizaciones listaCotizaciones;
    private EliminarCotizacion eliminarCotizacion;

    public EspecialistaEnviadosPresenterImpl(UseCaseHandler handler, Resources res, ListarEspecialistaEnviados listarClienteTodos, ListaCotizaciones listaCotizaciones, EliminarCotizacion eliminarCotizacion) {
        super(handler, res);
        this.listarClienteTodos = listarClienteTodos;
        this.listaCotizaciones = listaCotizaciones;
        this.eliminarCotizacion = eliminarCotizacion;
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
        initUseCaseMostrarListaCotizaciones();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //initiUseCaseMostrasListaEnviadosEspecialista();

    }

    private void initUseCaseMostrarListaCotizaciones() {
        if (view != null) view.mostrarProgressBar();
        String estadoPendiente = "1";
        String codigoPais = "51";
        handler.execute(listaCotizaciones, new ListaCotizaciones.RequestValues(keyUser, codigoPais, estadoPendiente),
                new UseCase.UseCaseCallback<ListaCotizaciones.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaCotizaciones.ResponseValue response) {
                        if (response == null) {
                            if (view != null) {
                                view.mostrarMensaje("Error al Cargar los Datos, Intentelo más Tarde");
                                view.ocultarProgressBar();
                            }
                            return;
                        } else {
                            ListaCotizacionesResponse body = response.getListaCotizacionesResponses();

                            if (body == null) return;
                            /*True porque hay error*/
                            if (body.getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(body.getMensaje());
                                    view.ocultarProgressBar();
                                }
                                return;
                            }

                            List<ListaCotizacionesResponse.CotizacionesResponse> listaResponse = body.getCotizacionesResponseList();
                            if (listaResponse.isEmpty() || listaResponse == null) {
                                if (view != null) {
                                    view.mostrarMensaje("Lista Vacia ");
                                    view.ocultarProgressBar();
                                }
                                return;
                            } else {
                                List<EspecialistaEnviadosUi> enviadosUiList = response.getListaCotizacionesResponses().getEspecialistaEnviadosUis();
                                if (enviadosUiList == null) {
                                    if (view != null) {
                                        view.mostrarMensaje("Error al Cargar los Datos, Intentelo más Tarde");
                                        view.ocultarProgressBar();
                                    }
                                    return;
                                } else {
                                    if (view != null) {
                                        view.mostraListaEspecialistaEnviados(response.getListaCotizacionesResponses().getEspecialistaEnviadosUis());
                                        view.ocultarProgressBar();
                                    }
                                    return;
                                }
                            }
                        }
                    }


                    @Override
                    public void onError() {

                    }
                });
    }

    /*private List<EspecialistaEnviadosUi> llenarDataEspecialista(List<ListaCotizacionesResponse.CotizacionesResponse> listaResponse) {
        List<EspecialistaEnviadosUi> especialistaEnviadosUis = new ArrayList<>();

    }*/

    private void initiUseCaseMostrasListaEnviadosEspecialista() {
        if (view != null) view.mostrarProgressBar();
        handler.execute(listarClienteTodos, new ListarEspecialistaEnviados.RequestValues(),
                new UseCase.UseCaseCallback<ListarEspecialistaEnviados.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarEspecialistaEnviados.ResponseValue response) {
                        if (view != null) {
                            view.mostraListaEspecialistaEnviados(response.getClienteTodosUiList());
                            view.ocultarProgressBar();
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
        super.setExtras(extras);
        if (extras != null) {
            keyUser = extras.getString("keyUser");
            Log.d(TAG, "Extras : " + keyUser);
        }
    }

    @Override
    public void onClickEliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi) {
        String mensaje = "¿Esta seguro que desea eliminar ?";
        if (view != null) view.mostrarDialogConfirmacionDelete(especialistaEnviadosUi, mensaje);
        Log.d(TAG, "getIdCodigoCotizacion : " + especialistaEnviadosUi.getIdCodigoCotizacion()
                + "getIdCodigoPropuesta" + especialistaEnviadosUi.getIdCodigoPropuesta());
    }

    @Override
    public void onAceptarDeleteItem(final EspecialistaEnviadosUi especialistaEnviadosUi) {
        handler.execute(eliminarCotizacion, new EliminarCotizacion.RequestValues(especialistaEnviadosUi, keyUser), new UseCase.UseCaseCallback<EliminarCotizacion.ResponseValue>() {
                    @Override
                    public void onSuccess(EliminarCotizacion.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();
                        if (defaultResponse != null) {
                            if (defaultResponse.getMessage() == null) {
                                if (view != null) {
                                     view.mostrarMensaje(response.getDefaultResponse().getMessage());

                                }
                                return;
                            }
                            if (response.getDefaultResponse().getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    //view.ocultarDialogProgress();
                                }
                                return;
                            } else {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    view.eliminarItem(especialistaEnviadosUi);
                                }
                                return;
                            }
                        } else {
                            if (view != null) {
                                view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                            }
                            return;
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    @Override
    public void onClickItem(EspecialistaEnviadosUi especialistaEnviadosUi) {
        ItemUi itemUi = new ItemUi();
        itemUi.setCodigoPropuesta(especialistaEnviadosUi.getIdCodigoPropuesta());
        itemUi.setNombrePropuesta(especialistaEnviadosUi.getNombreProyecto());
        itemUi.setImagePropuesta(especialistaEnviadosUi.getImagenRubro());
        itemUi.setFechaPropuesta(especialistaEnviadosUi.getFechaRealizo());
        itemUi.setDetallesPropuesta(especialistaEnviadosUi.getDetallePropuesta());
        itemUi.setCodigoRubro(especialistaEnviadosUi.getCodigoRubro());
        itemUi.setDescripcionRubro(especialistaEnviadosUi.getNombreRubro());
        itemUi.setCodigoOficio(especialistaEnviadosUi.getCodigoOficio());
        itemUi.setDescripcionOficio(especialistaEnviadosUi.getNombreOficio());
        itemUi.setCodigoRangoDias(especialistaEnviadosUi.getCodigoRangoDias());
        itemUi.setDescripcionRangoDias(especialistaEnviadosUi.getNombreRangoDias());
        itemUi.setCodigoRangoTurno(especialistaEnviadosUi.getCodigoRangoTurno());
        itemUi.setDescripcionRangoTurno(especialistaEnviadosUi.getNombreRangoTurno());
        itemUi.setCodigoRangoPrecio(especialistaEnviadosUi.getCodigoRangoPrecio());
        itemUi.setCodigoUsuarioPropuesta(keyUser);
        //itemUi.setEspecialidadesUiList(llenarEspecialidad(especialidadList));

        itemUi.setPromedioCotizacion(especialistaEnviadosUi.getCostoPromedio());
        itemUi.setNumeroCotizacion(especialistaEnviadosUi.getNumeroCotizacion());
        if (view != null)
            view.startActivityPerfilPropuesta(itemUi);
    }
}
