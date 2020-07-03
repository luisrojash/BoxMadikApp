package com.application.boxmadikv1.especialista.menu.abstracto;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.abstracto.useCase.EliminarCotizacion;
import com.application.boxmadikv1.especialista.menu.abstracto.useCase.ListaCotizaciones;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspecAbstractPresenterImpl extends BaseFragmentPresenterImpl<EspecAbstractView> implements EspecAbstractPresenter {

    public static final String TAG = EspecAbstractPresenterImpl.class.getSimpleName();
    private ListaCotizaciones listaCotizaciones;
    private EliminarCotizacion eliminarCotizacion;

    public EspecAbstractPresenterImpl(UseCaseHandler handler, Resources res, ListaCotizaciones listaCotizaciones, EliminarCotizacion eliminarCotizacion) {
        super(handler, res);
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
        validarTipoEstadosEspecialista();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*if(especialistaEstadosUiList.size() > 0){
            initUseCaseValidateBanco();
        }*/

    }

    private void validarTipoEstadosEspecialista() {
        if (view != null) view.mostrarProgressBar();

        // especialistaEstadosUiList.clear();
        //   String codigoPais = "51";
        Log.d(TAG, "tipoEstadoEspe : " + tipoEstadoEspe);
        switch (tipoEstadoEspe) {
            /*0= Cancelado; 1=Pendiente; 2= En Proceso;
                3= Finalizado; 4= Pagado; 5= Revocado)*/

            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                if (especialistaEstadosUiList != null) {
                    especialistaEstadosUiList.clear();
                    if (view != null) view.actualizarListas();
                }
                Log.d(TAG, "ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS");
                initUseCaseMostrarListaCotizaciones(keyUser, codigoPais, Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS);
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                if (especialistaEstadosUiList != null) {
                    especialistaEstadosUiList.clear();
                    if (view != null) view.actualizarListas();
                }
                Log.d(TAG, "ESTADO_ESPECIALISTA_ACEPTADO");
                initUseCaseMostrarListaCotizaciones(keyUser, codigoPais, Constantes.ESTADO_ESPECIALISTA_ACEPTADO);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO:
                if (especialistaEstadosUiList != null) {
                    especialistaEstadosUiList.clear();
                    if (view != null) view.actualizarListas();
                }
                Log.d(TAG, "ESTADO_ESPECIALISTA_NO_ACEPTADO");
                initUseCaseMostrarListaCotizaciones(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO:
                if (especialistaEstadosUiList != null) {
                    especialistaEstadosUiList.clear();
                    if (view != null) view.actualizarListas();
                }

                Log.d(TAG, "ESTADO_ESPECIALISTA_NO_ACEPTADO");
                initUseCaseMostrarListaCotizaciones(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
                if (especialistaEstadosUiList != null) {
                    especialistaEstadosUiList.clear();
                    if (view != null) view.actualizarListas();
                }
                Log.d(TAG, "ESTADO_ESPECIALISTA_NO_ACEPTADO");
                initUseCaseMostrarListaCotizaciones(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS);
                break;

        }
    }

    List<EspecialistaEstadosUi> especialistaEstadosUiList;

    private void initUseCaseMostrarListaCotizaciones(String keyUser, String codigoPais, final String tipoEstado) {

        handler.execute(listaCotizaciones, new ListaCotizaciones.RequestValues(keyUser, codigoPais, tipoEstado),
                new UseCase.UseCaseCallback<ListaCotizaciones.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaCotizaciones.ResponseValue response) {
                        if (response == null) {
                            if (view != null) {
                                //   view.mostrarMensaje("Error al Cargar los Datos, Intentelo más Tarde");
                                view.ocultarProgressBar();
                                view.actualizarListas();
                            }
                            Log.d(TAG, "response == null");
                            return;
                        } else {
                            ListaCotizacionesResponse body = response.getListaCotizacionesResponses();

                            if (body == null) {
                                if (view != null) view.actualizarListas();
                                return;
                            }
                            /*True porque hay error*/
                            if (body.getError()) {
                                if (view != null) {
                                    //     view.mostrarMensaje(body.getMensaje());
                                    view.ocultarProgressBar();
                                    view.mostraListaEspecialistaEstados(especialistaEstadosUiList);
                                    view.actualizarListas();
                                    view.mostrarTextViewEmpty();
                                }
                                Log.d(TAG, "body.getError()");
                                return;
                            }

                            List<ListaCotizacionesResponse.CotizacionesResponse> listaResponse = body.getCotizacionesResponseList();
                            if (listaResponse.isEmpty() || listaResponse == null) {
                                if (view != null) {
                                    /*Aqui cargamos un imagen lista Vacia*/
                                    //   view.mostrarMensaje("Lista Vacia ");
                                    view.ocultarProgressBar();
                                    view.actualizarListas();
                                    view.mostrarTextViewEmpty();
                                }
                                Log.d(TAG, "listaResponse.isEmpty() || listaResponse == null");
                                return;
                            } else {
                                especialistaEstadosUiList = response.getListaCotizacionesResponses().getEspecialistaEstadosUis();
                                if (especialistaEstadosUiList == null) {
                                    if (view != null) {
                                        //    view.mostrarMensaje("Error al Cargar los Datos, Intentelo más Tarde");
                                        view.ocultarProgressBar();
                                        view.actualizarListas();
                                    }
                                    Log.d(TAG, "enviadosUiList");
                                    return;
                                } else {
                                    if (tipoEstado.equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO)) {
                                        if (especialistaEstadosUiList.size() > 0) {
                                            if (view != null) {
                                                //view.mostraListaEspecialistaEstados(response.getListaCotizacionesResponses().getEspecialistaEstadosUis());
                                                view.actualizarListas();
                                                view.mostraListaEspecialistaEstados(especialistaEstadosUiList);
                                                view.ocultarProgressBar();
                                                view.ocultarTextViewEmpty();


                                            }
                                            initUseCaseValidateBanco();
                                        }


                                    } else {
                                        if (view != null) {
                                            //view.mostraListaEspecialistaEstados(response.getListaCotizacionesResponses().getEspecialistaEstadosUis());
                                            view.actualizarListas();
                                            view.mostraListaEspecialistaEstados(especialistaEstadosUiList);
                                            view.ocultarProgressBar();
                                            view.ocultarTextViewEmpty();

                                        }

                                        Log.d(TAG, "else");
                                        return;
                                    }

                                }
                            }
                        }
                    }

                    @Override
                    public void onError() {
                        if (view != null) view.actualizarListas();
                    }
                });
    }

    private void initUseCaseValidateBanco() {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = loginService.validarBanco(keyUser, codigoPais);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse mostrarPerfilResponse = response.body();
                        if (mostrarPerfilResponse == null) {
                            String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                            if (view != null) view.mostrarMensaje(mensajeError);
                            return;
                        } else {
                            if (mostrarPerfilResponse.getError()) {
                                if (view != null)
                                    view.mostrarDialogCentroBancario();
                                   // view.initStartActivityBancoDatos();
                                return;
                            } else {
                                Log.d(TAG, "YA EXISTE");
                            }

                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }


    String keyUser;
    String tipoEstadoEspe;
    String codigoPais;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras != null) {
            keyUser = extras.getString("keyUser");
            tipoEstadoEspe = extras.getString("tipoEstadoEspe");
            codigoPais = extras.getString("codigoPais");
            Log.d(TAG, "Extras : " + tipoEstadoEspe);
        }
    }

    @Override
    public void onClickEliminarItem(EspecialistaEstadosUi especialistaEstadosUi) {
        String mensaje = "¿Esta seguro que desea eliminar ?";

        especialistaEstadosUi.setPaisCodigo(codigoPais);
        if (view != null) view.mostrarDialogConfirmacionDelete(especialistaEstadosUi, mensaje);

    }

    @Override
    public void onClickItem(EspecialistaEstadosUi especialistaEstadosUi) {
        Log.d(TAG, "onClickItem:coti Estado : " + especialistaEstadosUi.getCotiEstado() +
                " onClickItem:propuesta Estado " + especialistaEstadosUi.getPropuestaEstado());

        ItemUi itemUi = new ItemUi();
        itemUi.setCodigoPropuesta(especialistaEstadosUi.getIdCodigoPropuesta());
        itemUi.setNombrePropuesta(especialistaEstadosUi.getNombreProyecto());
        itemUi.setImagePropuesta(especialistaEstadosUi.getImagenRubro());
        itemUi.setFechaPropuesta(especialistaEstadosUi.getFechaRealizo());
        itemUi.setDetallesPropuesta(especialistaEstadosUi.getDetallePropuesta());
        itemUi.setCodigoRubro(especialistaEstadosUi.getCodigoRubro());
        itemUi.setDescripcionRubro(especialistaEstadosUi.getNombreRubro());
        itemUi.setCodigoOficio(especialistaEstadosUi.getCodigoOficio());
        itemUi.setDescripcionOficio(especialistaEstadosUi.getNombreOficio());
        itemUi.setCodigoRangoDias(especialistaEstadosUi.getCodigoRangoDias());
        itemUi.setDescripcionRangoDias(especialistaEstadosUi.getNombreRangoDias());
        itemUi.setCodigoRangoTurno(especialistaEstadosUi.getCodigoRangoTurno());
        itemUi.setDescripcionRangoTurno(especialistaEstadosUi.getNombreRangoTurno());
        itemUi.setCodigoRangoPrecio(especialistaEstadosUi.getCodigoRangoPrecio());
        itemUi.setCodigoUsuarioPropuesta(especialistaEstadosUi.getCodigoUsuarioCreaPropuesta());
        itemUi.setCotiEstado(especialistaEstadosUi.getCotiEstado());
        itemUi.setPropuestaEstado(especialistaEstadosUi.getPropuestaEstado());
        itemUi.setNombreDepartamento(especialistaEstadosUi.getDepartamentoNombre());
        itemUi.setNombreDistrito(especialistaEstadosUi.getDistritoNombre());
        itemUi.setIdCotizacion(especialistaEstadosUi.getIdCodigoCotizacion());
        itemUi.setIdUsuarioCotizacion(especialistaEstadosUi.getCodigoCotiUsuario());
        itemUi.setKeyUser(keyUser);
        //itemUi.setEspecialidadesUiList(llenarEspecialidad(especialidadList));
        itemUi.setPaisCodigo(codigoPais);
        itemUi.setPromedioCotizacion(especialistaEstadosUi.getCostoPromedio());
        itemUi.setNumeroCotizacion(especialistaEstadosUi.getNumeroCotizacion());

        itemUi.setUsuNombreCliente(especialistaEstadosUi.getUsuNombreCliente());
        itemUi.setUsuAPellidosCliente(especialistaEstadosUi.getUsuAPellidosCliente());
        itemUi.setUsuRazonSocialCliente(especialistaEstadosUi.getUsuRazonSocialCliente());
        itemUi.setUsuRazonSocialCliente(especialistaEstadosUi.getUsuRazonSocialCliente());
        itemUi.setUsuFotoCliente(especialistaEstadosUi.getUsuFotoCliente());
        itemUi.setUsuPaisImagenCliente(especialistaEstadosUi.getPaisImagenCliente());
        itemUi.setMontoOfico(especialistaEstadosUi.getOfiMonto());
        String nombreUsu = validarNombreUsuarioNull(itemUi);
      /*  if (view != null)
            view.startActivityPerfilPropuesta(itemUi);*/
        // view.startActivityPerfilPropuesta(itemUi);
        if (especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_PAGADO) &&
                especialistaEstadosUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO)) {
            if (view != null)
                view.initStartActivityCalifica(itemUi, nombreUsu, itemUi.getUsuPaisImagenCliente(), itemUi.getUsuFotoCliente());
            return;
        } else {
            if (view != null)
                view.startActivityPerfilPropuesta(itemUi);
        }

    }


    private String validarNombreUsuarioNull(ItemUi itemUi) {
        String nombreUsuario = "";
        if (itemUi.getUsuNombreCliente() == null) {
            nombreUsuario = itemUi.getUsuRazonSocialCliente();
        } else {
            nombreUsuario = itemUi.getUsuNombreCliente() + " " + itemUi.getUsuAPellidosCliente();
        }
        return nombreUsuario;
    }

    @Override
    public void onAceptarDeleteItem(final EspecialistaEstadosUi especialistaEstadosUi) {
        handler.execute(eliminarCotizacion, new EliminarCotizacion.RequestValues(especialistaEstadosUi, keyUser), new UseCase.UseCaseCallback<EliminarCotizacion.ResponseValue>() {
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
                                    view.eliminarItem(especialistaEstadosUi);
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

    Boolean estadoInternet;

    @Override
    public void onValidateInternet(Boolean internet, EspecialistaEstadosUi especialistaEstadosUi) {
        if (internet) {
            this.estadoInternet = internet;
            Log.d(TAG, "CONECTADO A INTERNET");
            onClickItem(especialistaEstadosUi);
            // validarDatosIniciales();
        } else {
            this.estadoInternet = internet;
            Log.d(TAG, "DESCONECTADO A INTERNET");
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Intentelo mas tarde");
        }
    }


    RecyclerView recyclerView;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        this.recyclerView = recyclerView;
    }


    @Override
    public void onScrolled(LinearLayoutManager linearLayout, int dx, int dy) {
        int visibleItemCount = linearLayout.getChildCount();
        int totalItemCount = linearLayout.getItemCount();
        int pastVisibleItems = linearLayout.findFirstVisibleItemPosition();
        if (pastVisibleItems + visibleItemCount >= totalItemCount) {
            //End of list
            // onBottomReachedListener.onBottomReached();
            Log.d(TAG, "onBottomReached");
        } else {
            Log.d(TAG, "onNotBottom");
            //onBottomReachedListener.onNotBottom();
        }
    }


    @Override
    public void onRealTipoEstado(String usuarioCodigo, String codigoPais, String tipoEstado) {
        Log.d(TAG, "onRealTipoEstado : " + tipoEstado);
        validarTipoEstadosEspecialista();

    }


}
