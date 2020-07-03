package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.ClientePerfilEditarActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDireccionUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.GuardarDatosEditados;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.ListaDepartamento;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.ListaDistrito;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.ListaProvincia;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecEditDireccion;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.EspecPerfilDireccionActivity;
import com.application.boxmadikv1.utils.Constantes;
import com.google.android.gms.maps.GoogleMap;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.ClientePerfilEditActivity.EXTRA_TIPO_ESTADO_PERFIL;

public class ClientePerfilDireccionPresenterImpl extends BaseActivityPresenterImpl<ClientePerfilDireccionView> implements ClientePerfilDireccionPresenter {

    public static final String EDIT_PERFIL_CORRECTAMENTE_USUARIO = "ClientePerfilDireccionPresenterImpl";

    private ListaDepartamento listaDepartamento;
    private ListaProvincia listaProvincia;
    private ListaDistrito listaDistrito;
    private GuardarDatosEditados guardarDatosEditados;


    public static final String TAG = ClientePerfilDireccionPresenterImpl.class.getSimpleName();

    public ClientePerfilDireccionPresenterImpl(UseCaseHandler handler, Resources res, ListaDepartamento listaDepartamento, ListaProvincia listaProvincia,
                                               ListaDistrito listaDistrito, GuardarDatosEditados guardarDatosEditados) {
        super(handler, res);
        this.listaDepartamento = listaDepartamento;
        this.listaProvincia = listaProvincia;
        this.listaDistrito = listaDistrito;
        this.guardarDatosEditados = guardarDatosEditados;
        //  tipoDireccionUi = new TipoDireccionUi();
    }


    @Override
    public void onStart() {
        super.onStart();
        initDataEditEspec();
        initUseCaseTipoDepartamento();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initDataEditEspec() {
        if (datosEspecEditDireccion != null) {
            Log.d(TAG, "datosEspecEditDireccion : " + datosEspecEditDireccion.getNombreDepartamento());
            initLlegarDataEspecDireccion(datosEspecEditDireccion);

        }

    }

    private void validartInicioBotton() {
        switch (tipEstadoLLegada) {
            case "estadoCliente":
                Log.d(TAG, "estadoCliente");
                if (view != null) view.mostrarButtonTextGuardar();
                break;
            case "estadoEspecialista":
                Log.d(TAG, "estadoEspecialista");
                if (view != null) view.mostrarButtonTextSiguiente();
                break;
        }
    }

    String tipEstadoLLegada;
    DatosEspecEditDireccion datosEspecEditDireccion;
    String validateRubro;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;

        this.datosEspecEditDireccion = extras.getParcelable(EspecPerfilDireccionActivity.EXTRA_DIRECCION_EDIT_ESPEC);
        this.validateRubro = extras.getString("validateRubro");

    }

    private void initLlegarDataEspecDireccion(DatosEspecEditDireccion datosEspecEditDireccion) {
        if (datosEspecEditDireccion != null) {
            if (view != null) {
                view.mostrarTextViewDepartamento("Dpto : " + datosEspecEditDireccion.getNombreDepartamento());
                view.mostrarTextViewProvincia("Prov : " + datosEspecEditDireccion.getNombreProvincia());
                view.mostrarTextViewDistrito("Dtto : " + datosEspecEditDireccion.getNombreDistrito());
                if (datosEspecEditDireccion.getNombreDireccion() != null) {
                    view.mostrarEditextDireccion(datosEspecEditDireccion.getNombreDireccion());
                    view.deshabilitarText();
                    view.filtrarAdapterGoogle(datosEspecEditDireccion.getNombreDistrito());
                    Log.d(TAG, "dbLatitud : " + datosEspecEditDireccion.getUsuLatitud() +
                            "dbLongitud : " + datosEspecEditDireccion.getUsuLongitud());
                } else {

                }

            }
            return;
        }

        if (view != null) view.habilitartText();
    }

    private void initUseCaseTipoDepartamento() {
        handler.execute(listaDepartamento, new ListaDepartamento.RequestValues(),
                new UseCase.UseCaseCallback<ListaDepartamento.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaDepartamento.ResponseValue response) {

                        Log.d(TAG, "Lista Departamentos : " + response.getTipoDepartamentoUis().size());
                        if (response.getTipoDepartamentoUis() == null) return;

                        if (view != null) {
                            view.mostrarListaTipoDepartamento(response.getTipoDepartamentoUis());
                            view.deshabilitarProvDist();
                        }
                        initTipoDepartmentoLleno(datosEspecEditDireccion);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    private void initTipoDepartmentoLleno(DatosEspecEditDireccion datosEspecEditDireccion) {
        if (datosEspecEditDireccion != null) {
            tipoDepartamentoUi = new TipoDepartamentoUi();
            tipoDepartamentoUi.setId(datosEspecEditDireccion.getCodigoDepartamento());
            tipoDepartamentoUi.setDescripcion(datosEspecEditDireccion.getNombreDepartamento());
            initUseCaseTipoProvincia(paisCodigo, datosEspecEditDireccion.getCodigoDepartamento());
        }
    }


    private void initUseCaseTipoProvincia(String paisCodigo, String departamentoCodigo) {
        handler.execute(listaProvincia, new ListaProvincia.RequestValues(paisCodigo, departamentoCodigo),
                new UseCase.UseCaseCallback<ListaProvincia.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaProvincia.ResponseValue response) {
                        if (response.getTipoProvinciaUis() == null) return;
                        if (view != null)
                            view.mostrarListaTipoProvincia(response.getTipoProvinciaUis());
                        initTipoProvinciaLleno(datosEspecEditDireccion);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    private void initTipoProvinciaLleno(DatosEspecEditDireccion datosEspecEditDireccion) {
        if (datosEspecEditDireccion != null) {
            tipoProvinciaUi = new TipoProvinciaUi();
            tipoProvinciaUi.setId(datosEspecEditDireccion.getCodigoProvincia());
            tipoProvinciaUi.setDescripcion(datosEspecEditDireccion.getNombreProvincia());
            initUseCaseTipoDistrito(paisCodigo, datosEspecEditDireccion.getCodigoDepartamento(), datosEspecEditDireccion.getCodigoProvincia());
        }
    }


    private void initUseCaseTipoDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo) {
        handler.execute(listaDistrito, new ListaDistrito.RequestValues(paisCodigo, departamentoCodigo, provinciaCodigo),
                new UseCase.UseCaseCallback<ListaDistrito.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaDistrito.ResponseValue response) {

                        if (response.getTipoDistritoUis() == null) return;
                        if (view != null)
                            view.mostrarListaTipoDistrito(response.getTipoDistritoUis());
                        initTipoDistritoLleno(datosEspecEditDireccion);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    private void initTipoDistritoLleno(DatosEspecEditDireccion datosEspecEditDireccion) {
        if (datosEspecEditDireccion != null) {
            tipoDistritoUi = new TipoDistritoUi();
            tipoDistritoUi.setId(datosEspecEditDireccion.getCodigoDistrito());
            tipoDistritoUi.setDescripcion(datosEspecEditDireccion.getNombreDistrito());
        }
    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    TipoDepartamentoUi tipoDepartamentoUi;

    @Override
    public void onSpinnerTipoDepartamento(TipoDepartamentoUi tipoDepartamentoUi) {

        this.tipoDepartamentoUi = tipoDepartamentoUi;
        initUseCaseTipoProvincia(paisCodigo, tipoDepartamentoUi.getId());
        if (view != null) {
            view.mostrarTextViewDepartamento("Dpto: " + tipoDepartamentoUi.getDescripcion());
            //view.mostrarEditextDireccion(tipoDepartamentoUi.getDescripcion());
        }
    }


    TipoProvinciaUi tipoProvinciaUi;

    @Override
    public void onSpinnerTipoProvincia(TipoProvinciaUi tipoProvinciaUi) {
        this.tipoProvinciaUi = tipoProvinciaUi;
        initUseCaseTipoDistrito(paisCodigo, tipoDepartamentoUi.getId(), tipoProvinciaUi.getId());

        if (view != null) {
            view.mostrarTextViewProvincia("Prov: " + tipoProvinciaUi.getDescripcion());
            //view.mostrarEditextDireccion(tipoProvinciaUi.getDescripcion() + " " + tipoDepartamentoUi.getDescripcion());
        }

    }

    TipoDistritoUi tipoDistritoUi;

    @Override
    public void onSpinnerTipoDistrito(TipoDistritoUi tipoDistritoUi) {
        this.tipoDistritoUi = tipoDistritoUi;
        if (view != null) {
            view.mostrarTextViewDistrito("Dtto : " + tipoDistritoUi.getDescripcion());
            //view.mostrarEditextDireccion(tipoDistritoUi.getDescripcion() + " " + tipoProvinciaUi.getDescripcion() + " " + tipoDepartamentoUi.getDescripcion());
            view.filtrarAdapterGoogle(tipoDistritoUi.getDescripcion());
        }

    }

    @Override
    public void onAutCompleteTipoDireccion(TipoDireccionUi tipoDireccionUi) {
        this.tipoDireccionUi = tipoDireccionUi;
        if (tipoDireccionUi.getId().length() > 0) {
            Log.d(TAG, "ififififif");
            if (view != null) view.desactivarAutoCompleDireccion(tipoDireccionUi);
        } else {
            if (view != null) view.activarAutoCompleDireccion();
            Log.d(TAG, "elseelseelse");
        }
    }

    TipoDireccionUi tipoDireccionUi;

    @Override
    public void onObtenerCoordenadasDireccion(TipoDireccionUi tipoDireccionUi2) {
        tipoDireccionUi.setLatitud(tipoDireccionUi2.getLatitud());
        tipoDireccionUi.setLongitud(tipoDireccionUi2.getLongitud());
        if (view != null) view.buscarMapaDireccion(tipoDireccionUi, googleMap);
        Log.d(TAG, "onObtenerCoordenadasDireccion : " + tipoDireccionUi.getLatitud());
    }


    @Override
    public void onClickBuscarMapaDireccion(String direccion) {
        if (tipoDireccionUi == null) {
            if (view != null) view.mostrarMensaje("Seleccione una Direccion Correcta");
            return;
        }
        if (direccion.isEmpty() || direccion.equals("") || direccion.trim().isEmpty() || direccion == null) {
            if (view != null) view.mostrarMensaje("Seleccione una Direccion Correcta");
            return;
        } else {
            if (view != null) view.buscarMapaDireccion(tipoDireccionUi, googleMap);
        }
    }

    @Override
    public void onClickSiguiente(String mensajeBox) {
        if (view != null) view.deshabilitarButtonGuardar();
       /* if(tipoDireccionUi== null){
            if (view != null) {
                view.mostrarMensaje("Seleccione una Direccion Correcta");
                view.habilidarButtonGuardar();
            }
            return;
        }
        if (mensajeBox.equals("BoxMadick") || tipoDireccionUi.getDescripcion().equals("BoxMadick")) {
            String mensaje = "No se permiten campos vacios";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilidarButtonGuardar();
            }
            return;
        }*/
        if (tipoDepartamentoUi == null) {
            Log.d(TAG, "Necesitas Esribir un tipo DireccionUi");
            String mensaje = "Ingrese Departamento";
            if (view != null) {
                view.mostrarMensajeErrorAutoCompleteDepartamentoError(mensaje);
                view.habilidarButtonGuardar();
            }
            return;
        }
        if (tipoProvinciaUi == null) {
            Log.d(TAG, "Necesitas Esribir un tipo Provincia");
            String mensaje = "Ingrese Provincia";
            if (view != null) {
                view.mostrarMensajeErrorAutoCompleteProvinciaError(mensaje);
                view.habilidarButtonGuardar();
            }
            return;
        }
        if (tipoDistritoUi == null) {
            String mensaje = "Ingrese Distrito";
            if (view != null) {
                view.mostrarMensajeErrorAutoCompleteDistritoError(mensaje);
                view.habilidarButtonGuardar();
            }
            return;
        }
       /* if (tipoDireccionUi == null) {
            if (view != null) {
                view.mostrarMensaje("Seleccione una Direccion Correcta");
                view.habilidarButtonGuardar();
            }
            return;
        }
        if (tipoDireccionUi.getDescripcion().isEmpty() || tipoDireccionUi.getDescripcion().equals(" ")) {
            String mensaje = "No se permiten campos vacios";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilidarButtonGuardar();
            }
        }
        Double comproboacionDatosLtng = new Double(tipoDireccionUi.getLatitud());
        if (comproboacionDatosLtng == null) {
            String mensaje = "Busque su Dirección - Boton Buscar";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilidarButtonGuardar();
            }
            return;
        }*/
        if (view != null) view.mostrarProgressBarDialog();
        String codigoDepartamento = tipoDepartamentoUi.getId();
        String codigoProvincia = tipoProvinciaUi.getId();
        String codigoDistrito = tipoDistritoUi.getId();
        String direccion = null, latitud = null, longitud = null;
       /*String latitudNull = String.valueOf(tipoDireccionUi.getLatitud());
        String longitudNull = String.valueOf(tipoDireccionUi.getLongitud());*/

       /* Double latitudNull = new Double(tipoDireccionUi.getLatitud());
        Double longitudNull = new Double(tipoDireccionUi.getLongitud());*/
        /*String direccion = tipoDireccionUi.getDescripcion();
        String latitud = String.valueOf(tipoDireccionUi.getLatitud());
        String longitud = String.valueOf(tipoDireccionUi.getLongitud());*/
        if (tipoDireccionUi.getDescripcion() != null) {
            direccion = tipoDireccionUi.getDescripcion();
        } else {
            //direccion = null;

        }
        if (tipoDireccionUi.getLatitud() != null) {
            latitud = String.valueOf(tipoDireccionUi.getLatitud());

        } else {
            //latitud = null;
        }
        if (tipoDireccionUi.getLongitud() != null) {
            longitud = String.valueOf(tipoDireccionUi.getLongitud());
        } else {
            // longitud = null;

        }


        // Log.d(TAG, "direccion : " + direccion);
        initUseCaseGuardarDireccionRetrofit(codigoDepartamento, codigoProvincia, codigoDistrito, direccion, latitud, longitud);


    }

    private void initUseCaseGuardarDireccionRetrofit(final String codigoDepartamento, final String codigoProvincia,
                                                     final String codigoDistrito, String direccion,
                                                     String latitud, String longitud) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        Log.d(TAG, "codigoDepartamento : " + codigoDepartamento +
                "codigoProvincia : " + codigoProvincia +
                "codigoDistrito : " + codigoDistrito);
        service.guardarDireccionPerfil(codigoDepartamento, codigoProvincia, codigoDistrito, direccion, keyUser,
                latitud, longitud).enqueue(
                new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        DefaultResponse body = response.body();
                        if (body == null) {
                            String mensaje = "Ocurrio algun Problema de conexión";
                            if (view != null) {
                                view.ocultarProgressBarDialog();
                                view.mostrarMensaje(mensaje);
                                view.habilidarButtonGuardar();
                            }
                        } else {
                            if (body.getError()) {
                                if (view != null) {
                                    view.ocultarProgressBarDialog();
                                    view.mostrarMensaje(body.getMessage());
                                    view.habilidarButtonGuardar();
                                }
                            } else {
                                if (validateRubro != null) {
                                    switch (validateRubro) {
                                        case "validateRubroCliente":
                                            initValidateCliente();

                                            break;
                                        case "validateRubroEspec":
                                            initValidateEspec();
                                            break;
                                        default:
                                            break;
                                    }
                                } else {
                                    Log.d(TAG, "nuloo");
                                    if (view != null) {
                                        view.ocultarProgressBarDialog();
                                        view.initStartActivityEspecEditPerfil();

                                    }
                                }

                                /*if (validateRubro != null) {
                                    if(view!=null){
                                        view.ocultarProgressBarDialog();
                                        view.initStartActivityRubros();
                                    }
                                } else {
                                    if (view != null) {
                                        view.ocultarProgressBarDialog();
                                        view.initStartActivityEspecEditPerfil(codigoDepartamento, codigoProvincia, codigoDistrito);

                                    }
                                }*/

                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        String mensaje = "Ocurrio algun Problema de conexión";
                        if (view != null) {
                            view.ocultarProgressBarDialog();
                            view.mostrarMensaje(mensaje);
                        }
                        Log.d(TAG, "onFailure : " + t.getMessage().toString());
                    }
                });


    }

    private void initValidateEspec() {
        if (validateRubro != null) {
            if (view != null) {
                view.ocultarProgressBarDialog();
                view.initStartMenuEspecialista();
                view.habilidarButtonGuardar();
            }
        } else {
            if (view != null) {
                view.ocultarProgressBarDialog();
                view.initStartActivityEspecEditPerfil();
                view.habilidarButtonGuardar();

            }
        }
    }

    private void initValidateCliente() {
        if (validateRubro != null) {
            if (view != null) {
                view.ocultarProgressBarDialog();
                view.initStartMenuCliente();
                view.habilidarButtonGuardar();
            }
        } else {
            if (view != null) {
                view.ocultarProgressBarDialog();
                view.initStartActivityEspecEditPerfil();
                view.habilidarButtonGuardar();

            }
        }
    }


    String keyUser, userFoto, paisCodigo;

    @Override
    public void onKeyUser(String keyUser, String userFoto, String paisCodigo) {
        this.keyUser = keyUser;
        this.userFoto = userFoto;
        this.paisCodigo = paisCodigo;
        Log.d(TAG, "onKeyUser");
    }

    GoogleMap googleMap;

    @Override
    public void onGooglenMap(GoogleMap googleMap) {
        if (googleMap != null) {
            this.googleMap = googleMap;
            this.tipoDireccionUi = new TipoDireccionUi();
            if (datosEspecEditDireccion != null) {
                if (datosEspecEditDireccion.getUsuLatitud() == null) {
                    TipoDireccionUi tipoDireccionUi2 = new TipoDireccionUi();
                    //tipoDireccionUi2.setDescripcion("");
                    tipoDireccionUi2.setLatitud(String.valueOf(-12.1015323));
                    tipoDireccionUi2.setLongitud(String.valueOf(-76.9414436));
                    if (view != null) view.buscarMapaDireccion(tipoDireccionUi2, googleMap);
                } else {
                    double dbLatitud = Double.parseDouble(datosEspecEditDireccion.getUsuLatitud());
                    double dbLongitud = Double.parseDouble(datosEspecEditDireccion.getUsuLongitud());
                    tipoDireccionUi.setDescripcion(datosEspecEditDireccion.getNombreDireccion());
                    tipoDireccionUi.setLatitud(String.valueOf(dbLatitud));
                    tipoDireccionUi.setLongitud(String.valueOf(dbLongitud));
                    Log.d(TAG, "dbLatitud : " + dbLatitud +
                            "dbLongitud : " + dbLongitud);
                    if (view != null) view.buscarMapaDireccion(tipoDireccionUi, googleMap);
                }


            } else {
                TipoDireccionUi tipoDireccionUi2 = new TipoDireccionUi();
                //tipoDireccionUi2.setDescripcion("");
                tipoDireccionUi2.setLatitud(String.valueOf(-12.1015323));
                tipoDireccionUi2.setLongitud(String.valueOf(-76.9414436));
                if (view != null) view.buscarMapaDireccion(tipoDireccionUi2, googleMap);
                /*tipoDireccionUi.setDescripcion("");
                tipoDireccionUi.setLatitud(String.valueOf(-12.1015323));
                tipoDireccionUi.setLongitud(String.valueOf(-76.9414436));
                if (view != null) view.buscarMapaDireccion(tipoDireccionUi, googleMap);*/
            }

            // Log.d(TAG, "LLENO");
        } else {
            Log.d(TAG, "LLENOVACIO");
        }
    }

    @Override
    public void onClickCloseDepartamento() {
        this.tipoDepartamentoUi = null;
        this.tipoProvinciaUi = null;
        this.tipoDistritoUi = null;
        this.tipoDireccionUi = null;
        if (view != null) view.borrarTextDepartamento();
    }

    @Override
    public void onClickCloseProvincia() {
        this.tipoProvinciaUi = null;
        this.tipoDistritoUi = null;
        this.tipoDireccionUi = null;
        if (view != null) view.borrarTextProvincia();
    }

    @Override
    public void onClickCloseDistrito() {
        this.tipoDistritoUi = null;
        this.tipoDireccionUi = null;
        if (view != null) view.borrarTextDistrito();
    }

    @Override
    public void onClickCloseDireccion() {
        //this.tipoDireccionUi = null;
    }


}
