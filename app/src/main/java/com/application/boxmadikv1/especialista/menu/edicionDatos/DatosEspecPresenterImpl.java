package com.application.boxmadikv1.especialista.menu.edicionDatos;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.MostrarPerfilResponse;
import com.application.boxmadikv1.api.response.UsuPresentacionResponse;
import com.application.boxmadikv1.api.response.especialista.ListaZonaTrabajoResponse;
import com.application.boxmadikv1.api.response.especialista.MostrarCentroEstudiosResponse;
import com.application.boxmadikv1.api.response.especialista.MostrarEspeBancoResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosBancaria;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosCentroEstudioList;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecEditDireccion;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecUi;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecZonaTrabList;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecZonaTrabajo;
import com.application.boxmadikv1.especialista.menu.edicionDatos.useCase.ObtenerDireccion;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosEspecPresenterImpl extends BaseActivityPresenterImpl<DatosEspecView> implements DatosEspecPresenter {

    public static final String TAG = DatosEspecPresenterImpl.class.getSimpleName();
    private ObtenerDireccion obtenerDireccion;
    Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

    public DatosEspecPresenterImpl(UseCaseHandler handler, Resources res, ObtenerDireccion obtenerDireccion) {
        super(handler, res);
        this.obtenerDireccion = obtenerDireccion;

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
        Log.d(TAG, "onCreate");
        mostrarTitleToolbar();
    }

    private void mostrarTitleToolbar() {
        String titulo = "Edición Datos";
        switch (tipoEstado) {
            case "cliente":
                if (view != null) view.mostrarTitleToolbar(titulo);
                break;
            case "especialista":
                if (view != null) view.mostrarTitleToolbar(titulo);
                break;
            default:
                if (view != null) view.mostrarTitleToolbar(titulo);
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        // datosEspecNew();
        // if (view != null) view.mostrarLista(datosEspecUiList);
    }

    String keyUser, paisCodigo;
    TinyDB tinyDB;
    List<DatosEspecUi> datosEspecUiList;

    @Override
    public void onKeyUser(String keyUser, String paisCodigo, TinyDB tinyDB) {
        this.keyUser = keyUser;
        this.paisCodigo = paisCodigo;
        this.tinyDB = tinyDB;
        Log.d(TAG, "onKeyUser: " + paisCodigo);
        //datosEspecNew();
    }

    String tipoEstado;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.tipoEstado = extras.getString("tipoEstado");
    }

    @Override
    public void onClickFinish() {
        switch (tipoEstado) {
            case "cliente":
                if (view != null) view.onFinishCliente();
                break;
            case "especialista":
                if (view != null) view.onFinishEspecialista();
                break;
            default:
                break;
        }
    }

    Boolean estadoInternet;

    @Override
    public void onStatusConexion(Boolean estadoInternet, Object objeto) {
        if (estadoInternet) {
            this.estadoInternet = estadoInternet;
            Log.d(TAG, "CONECTADO A INTERNET");
            datosEspecNew();
            if (view != null) view.mostrarLista(datosEspecUiList);
        } else {
            this.estadoInternet = estadoInternet;
            Log.d(TAG, "DESCONECTADO A INTERNET");
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Desea Intentar denuevo?");
        }
    }

    private void datosEspecNew() {
        if (estadoInternet) {
            switch (tipoEstado) {
                case "cliente":
                    //  if (view != null) view.mostrarTextoToolbar("Edicio");
                    initDataClient(tipoEstado);
                    break;
                case "especialista":
                    // if (view != null) view.mostrarTextoToolbar("Cliente");
                    initDataEspec(tipoEstado);
                    break;
                default:
                    break;
            }
        } else {
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Desea Intentar denuevo?");
        }


    }

    private void initDataClient(String tipoEstado) {
        datosEspecUiList = new ArrayList<>();
        DatosEspecUi datosEspecUiDatos = new DatosEspecUi();
        datosEspecUiDatos.setId("1");
        datosEspecUiDatos.setEstado(true);
        datosEspecUiDatos.setDescripcion("Perfil Datos");
        datosEspecUiDatos.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiDatos);

        DatosEspecUi datosEspecUiDireccion = new DatosEspecUi();
        datosEspecUiDireccion.setId("2");
        datosEspecUiDireccion.setDescripcion("Dirección");
        datosEspecUiDireccion.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiDireccion);
        initUseCaseEditarDireccionRetrofit(keyUser);

        DatosEspecUi datosEspecUiClave = new DatosEspecUi();
        datosEspecUiClave.setId("3");
        datosEspecUiClave.setDescripcion("Cambiar Clave");
        datosEspecUiClave.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiClave);
    }

    private void initDataEspec(String tipoEstado) {
        datosEspecUiList = new ArrayList<>();
        DatosEspecUi datosEspecUiDatos = new DatosEspecUi();
        datosEspecUiDatos.setId("1");
        datosEspecUiDatos.setEstado(true);
        datosEspecUiDatos.setDescripcion("Perfil Datos");
        datosEspecUiDatos.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiDatos);

        DatosEspecUi datosEspecUiDireccion = new DatosEspecUi();
        datosEspecUiDireccion.setId("2");
        datosEspecUiDireccion.setDescripcion("Dirección");
        datosEspecUiDireccion.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiDireccion);
        initUseCaseEditarDireccionRetrofit(keyUser);

        DatosEspecUi datosEspecUiZonaTrabajo = new DatosEspecUi();
        datosEspecUiZonaTrabajo.setId("3");
        datosEspecUiZonaTrabajo.setDescripcion("Zona Trabajo");
        datosEspecUiZonaTrabajo.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiZonaTrabajo);
        initUseCaseEditarZonaTrabajo(keyUser);


        DatosEspecUi datosEspecUiRubrosTrabajo = new DatosEspecUi();
        datosEspecUiRubrosTrabajo.setId("4");
        datosEspecUiRubrosTrabajo.setDescripcion("Rubros Trabajo");
        datosEspecUiRubrosTrabajo.setEstado(true);
        datosEspecUiRubrosTrabajo.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiRubrosTrabajo);
        // initValidateRubrotrabajo(tinyDB);


        DatosEspecUi datosEspecUiPresentacion = new DatosEspecUi();
        datosEspecUiPresentacion.setId("5");
        datosEspecUiPresentacion.setDescripcion("Presentación");
        datosEspecUiPresentacion.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiPresentacion);
        initUseCaseEditarPresentacion(keyUser);


        DatosEspecUi datosEspecUiCursos = new DatosEspecUi();
        datosEspecUiCursos.setId("6");
        datosEspecUiCursos.setDescripcion("Cursos Estudios");
        datosEspecUiCursos.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiCursos);
        initUseCaseEditarCentroEstudios(keyUser);

        DatosEspecUi datosEspecUiCuentaBancaria = new DatosEspecUi();
        datosEspecUiCuentaBancaria.setId("7");
        datosEspecUiCuentaBancaria.setDescripcion("Cuenta Bancaria");
        datosEspecUiCuentaBancaria.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiCuentaBancaria);
        initUseCaseEditarBancaria(keyUser);


        DatosEspecUi datosEspecUiClave = new DatosEspecUi();
        datosEspecUiClave.setId("8");
        datosEspecUiClave.setDescripcion("Cambiar Clave");
        datosEspecUiClave.setTipoEstado(tipoEstado);
        datosEspecUiList.add(datosEspecUiClave);
    }

    private void initValidateRubrotrabajo(TinyDB tinydb) {

        ArrayList<String> arrayList = tinydb.getListString("mylist");
        String tipEstado = "4";
        if (arrayList == null || arrayList.isEmpty()) {

            Log.d(TAG, "onValidateSeleccionRubros SELECCIONARITEMS");
            if (view != null) view.estadoDireccion(onFalseData(), tipEstado);
        } else {
            Log.d(TAG, "onValidateSeleccionRubros LLENO" + onTrueData() + tipEstado);
            if (view != null) view.estadoDireccion(onTrueData(), tipEstado);
        }
    }


    private void initUseCaseEditarCentroEstudios(String keyUser) {
        Call<MostrarCentroEstudiosResponse> call = loginService.mostrarListaCentroEstudiosEspe(keyUser);
        call.enqueue(new Callback<MostrarCentroEstudiosResponse>() {
            @Override
            public void onResponse(Call<MostrarCentroEstudiosResponse> call, Response<MostrarCentroEstudiosResponse> response) {
                MostrarCentroEstudiosResponse mostrarCentroEstudiosResponse = response.body();
                String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                if (mostrarCentroEstudiosResponse == null) {
                    //if (view != null) view.mostrarMensaje(mensajeError);
                    Log.d(TAG, mensajeError);
                    return;
                } else {
                    if (mostrarCentroEstudiosResponse.getError()) {
                        //if (view != null)
                        //   view.mostrarMensaje(mostrarCentroEstudiosResponse.getMensaje());

                        Log.d(TAG, "Data Vacio: " + mostrarCentroEstudiosResponse.getMensaje());
                        return;
                    } else {
                        List<MostrarCentroEstudiosResponse.MostrarCentroEstudiosResp> estudiosRespsList = mostrarCentroEstudiosResponse.getEstudiosRespList();
                        if (estudiosRespsList != null) {
                            if (estudiosRespsList.size() > 0) {
                                List<DatosCentroEstudioList> centroEstudioLists = new ArrayList<>();

                                for (MostrarCentroEstudiosResponse.MostrarCentroEstudiosResp estudiosResp :
                                        estudiosRespsList) {
                                    Log.d(TAG, "anioInicio : " + estudiosResp.getAnioInicio());

                                    DatosCentroEstudioList datosCentroEstudio = new DatosCentroEstudioList();
                                    datosCentroEstudio.setCodigoEspeEstudios(estudiosResp.getCodigoEspeEstudios());
                                    datosCentroEstudio.setNombreCurso(estudiosResp.getNombreCurso());
                                    datosCentroEstudio.setCodigoTipoEstudios(estudiosResp.getCodigoTipoEstudios());
                                    datosCentroEstudio.setNombreTipoEstudiosM(estudiosResp.getNombreTipoEstudios());
                                    datosCentroEstudio.setCodigoCentroEstudios(estudiosResp.getCodigoCentroEstudios());
                                    datosCentroEstudio.setNombreCentroEstudios(estudiosResp.getNombreCentroEstudios());
                                    datosCentroEstudio.setMesInicio(estudiosResp.getMesInicio());
                                    datosCentroEstudio.setAnioInicio(estudiosResp.getAnioInicio());
                                    datosCentroEstudio.setMesFin(estudiosResp.getMesFin());
                                    datosCentroEstudio.setAnioFin(estudiosResp.getAnioFin());
                                    datosCentroEstudio.setFechaInicio(Constantes.recortarFechaRealizo(estudiosResp.getFechaInicio()));
                                    datosCentroEstudio.setFechaFin(Constantes.recortarFechaRealizo(estudiosResp.getFechaFin()));
                                    centroEstudioLists.add(datosCentroEstudio);
                                }
                                validaPorMostrarDataCentroEstudios(centroEstudioLists);

                            } else {
                                Log.d(TAG, "Lista Vacia");
                                String mensaje = "Lista sin Campos";
                                if (view != null)
                                    // view.mostrarMensaje(mensaje);
                                    return;
                            }

                        } else {
                            if (view != null)
                                // view.mostrarMensaje(mostrarCentroEstudiosResponse.getMensaje());
                                return;
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<MostrarCentroEstudiosResponse> call, Throwable t) {
                String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                //  if (view != null) view.mostrarMensaje(mensajeError);
                Log.d(TAG, "onFailure : " );
            }
        });

    }

    List<DatosCentroEstudioList> centroEstudioLists;

    private void validaPorMostrarDataCentroEstudios(List<DatosCentroEstudioList> centroEstudioLists) {
        String tipEstado = "6";
        if (centroEstudioLists.size() > 0) {
            this.centroEstudioLists = centroEstudioLists;
            if (view != null) view.estadoDireccion(onTrueData(), tipEstado);
        } else {
            if (view != null) view.estadoDireccion(onFalseData(), tipEstado);
        }
    }

    private void initUseCaseEditarBancaria(String keyUser) {

        Call<MostrarEspeBancoResponse> call = loginService.mostrarDataBancaria(keyUser, paisCodigo);
        call.enqueue(new Callback<MostrarEspeBancoResponse>() {
            @Override
            public void onResponse(Call<MostrarEspeBancoResponse> call, Response<MostrarEspeBancoResponse> response) {
                MostrarEspeBancoResponse mostrarEspeBancoResponse = response.body();
                String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                if (mostrarEspeBancoResponse == null) {
                    //if (view != null) view.mostrarMensaje(mensajeError);
                    return;
                } else {
                    if (mostrarEspeBancoResponse.getError()) {
                        if (view != null)
                            // view.mostrarMensaje(mostrarEspeBancoResponse.getMensaje());
                            return;
                    } else {
                        MostrarEspeBancoResponse.MostrarEspeBancoResp bancoResp = mostrarEspeBancoResponse.getMostrarEspeBancoResp();
                        if (bancoResp != null) {
                            String bancoCodigo = bancoResp.getBancoCodigo();
                            String bancoNombre = bancoResp.getBancoNombre();
                            String tipoCuentaCodigo = bancoResp.getTipoCuentaCodigo();
                            String numerCuenta = bancoResp.getNumerCuenta();
                            String numerCuentaInterbank = bancoResp.getNumerCuentaInterbank();
                            validaPorMostrarDataBancaria(bancoCodigo, bancoNombre, tipoCuentaCodigo,
                                    numerCuenta, numerCuentaInterbank);
                        } else {
                            if (view != null)
                                //  view.mostrarMensaje(mostrarEspeBancoResponse.getMensaje());
                                return;
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<MostrarEspeBancoResponse> call, Throwable t) {
                String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                if (view != null) view.mostrarMensaje(mensajeError);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

    }

    DatosBancaria datosBancaria;

    private void validaPorMostrarDataBancaria(String bancoCodigo, String bancoNombre, String tipoCuentaCodigo, String numerCuenta, String numerCuentaInterbank) {
        String tipoDireccion = "7";
        if (bancoCodigo == null) {
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        if (tipoCuentaCodigo == null) {
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        if (numerCuenta == null) {
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        if (numerCuentaInterbank == null) {
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        datosBancaria = new DatosBancaria();
        datosBancaria.setBancoCodigo(bancoCodigo);
        datosBancaria.setBancoNombre(bancoNombre);
        datosBancaria.setTipoCuentaCodigo(tipoCuentaCodigo);
        datosBancaria.setNumerCuenta(numerCuenta);
        datosBancaria.setNumerCuentaInterbank(numerCuentaInterbank);
        if (view != null) view.estadoDireccion(onTrueData(), tipoDireccion);
    }

    String usuarioPresentacion;

    private void initUseCaseEditarPresentacion(String keyUser) {
        // String tipoPresentacion = "4";
        Call<UsuPresentacionResponse> call = loginService.mostrarUsuarioPresentacion(keyUser, paisCodigo);
        call.enqueue(new Callback<UsuPresentacionResponse>() {
            @Override
            public void onResponse(Call<UsuPresentacionResponse> call, Response<UsuPresentacionResponse> response) {
                if (response == null) {
                    String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                    //  if (view != null) view.mostrarMensaje(mensajeError);
                    return;
                } else {
                    UsuPresentacionResponse presentacionResponse = response.body();
                    if (presentacionResponse == null) {
                        String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                        // if (view != null) view.mostrarMensaje(mensajeError);
                        return;
                    } else {
                        if (presentacionResponse.getError()) {
                            if (view != null)
                                //      view.mostrarMensaje(presentacionResponse.getMessage());
                                return;
                        } else {
                            usuarioPresentacion = presentacionResponse.getPresentacion();
                            validarPorMostrarPresentacion(usuarioPresentacion);
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<UsuPresentacionResponse> call, Throwable t) {
                String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                //  if (view != null) view.mostrarMensaje(mensajeError);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
                return;

            }
        });
    }

    private void validarPorMostrarPresentacion(String usuarioPresentacion2) {
        String tipoDireccion = "5";
        if (usuarioPresentacion2 == null) {
            Log.d(TAG, "IF::NULOnombreDepartamento");
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        // this.usuarioPresentacion = usuarioPresentacion2;
        if (view != null) view.estadoDireccion(onTrueData(), tipoDireccion);
    }

    private void initUseCaseEditarZonaTrabajo(String keyUser) {
        Call<ListaZonaTrabajoResponse> call = loginService.mostrarListaZonaTrabajo(keyUser, paisCodigo);
        call.enqueue(new Callback<ListaZonaTrabajoResponse>() {
            @Override
            public void onResponse(Call<ListaZonaTrabajoResponse> call, Response<ListaZonaTrabajoResponse> response) {
                if (response == null) {
                    String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                    //if (view != null) view.mostrarMensaje(mensajeError);
                    // Log.d(TAG, "onFailure : " + mensajeError);
                    return;
                }
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ListaZonaTrabajoResponse mostrarPerfilResponse = response.body();
                        if (mostrarPerfilResponse == null) {
                            String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                            if (view != null) view.mostrarMensaje(mensajeError);
                            return;
                        } else {
                            if (mostrarPerfilResponse.getError()) {
                                if (view != null)
                                    view.mostrarMensaje(mostrarPerfilResponse.getMensaje());
                                return;
                            } else {
                                List<ListaZonaTrabajoResponse.ListZonaTrabajoRes> listZonaTrabajoRes = mostrarPerfilResponse.getListZonaTrabajoRes();
                                if (listZonaTrabajoRes == null) {
                                    String mensajeError = "Lista Vacia";
                                    if (view != null) view.mostrarMensaje(mensajeError);
                                    return;
                                } else {
                                    String codigoDepartamento = listZonaTrabajoRes.get(0).getCodigoDepartamento();
                                    String nombreDepartamento = listZonaTrabajoRes.get(0).getNombreDepartamento();
                                    String codigoProvincia = listZonaTrabajoRes.get(0).getCodigoProvincia();
                                    String nombreProvincia = listZonaTrabajoRes.get(0).getNombreProvincia();
                                    validarPorMostrarZonaTrabajo(nombreDepartamento, nombreProvincia,
                                            codigoDepartamento, codigoProvincia, listZonaTrabajoRes);
                                    //mostrarDatosZonaTrabajos(listZonaTrabajoRes);
                                }
                            }

                        }


                    }
                }

            }

            @Override
            public void onFailure(Call<ListaZonaTrabajoResponse> call, Throwable t) {
                //callBackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

    }

    private void validarPorMostrarZonaTrabajo(String nombreDepartamento, String nombreProvincia,
                                              String codigoDepartamento, String codigoProvincia,
                                              List<ListaZonaTrabajoResponse.ListZonaTrabajoRes> listZonaTrabajoRes) {

        String tipoDireccion = "3";
        if (nombreDepartamento == null) {
            Log.d(TAG, "IF::NULOnombreDepartamento");
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        if (nombreProvincia == null) {
            Log.d(TAG, "IF::NULOnombreProvincia");
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }

        datosEspecZonaTrabajo = new DatosEspecZonaTrabajo();
        datosEspecZonaTrabajo.setCodigoDepartamento(codigoDepartamento);
        datosEspecZonaTrabajo.setNombreDepartamento(nombreDepartamento);
        datosEspecZonaTrabajo.setCodigoProvincia(codigoProvincia);
        datosEspecZonaTrabajo.setNombreProvincia(nombreProvincia);
        List<DatosEspecZonaTrabList> datosEspecZonaTrabLists = new ArrayList<>();
        for (ListaZonaTrabajoResponse.ListZonaTrabajoRes listZonaTrabajoRes1 : listZonaTrabajoRes) {
            DatosEspecZonaTrabList zonaTrabList = new DatosEspecZonaTrabList();
            zonaTrabList.setCodigoDistrito(listZonaTrabajoRes1.getCodigoDistrito());
            zonaTrabList.setNombreDistrito(listZonaTrabajoRes1.getNombreDistrito());
            datosEspecZonaTrabLists.add(zonaTrabList);
        }
        datosEspecZonaTrabajo.setZonaTrabLists(datosEspecZonaTrabLists);
        if (view != null) view.estadoDireccion(onTrueData(), tipoDireccion);
    }


    DatosEspecZonaTrabajo datosEspecZonaTrabajo;


    @Override
    public void onClickItem(DatosEspecUi datosEspecUi) {
        switch (tipoEstado) {
            case "cliente":
                initStartActivityCliente(datosEspecUi);
                break;
            case "especialista":
                initStartActivityEspecialista(datosEspecUi);
                break;
            default:
                break;
        }




        /*switch (datosEspecUi.getId()) {
            case "1":
                if (view != null) view.initStartActivityEditarPerfil();
                break;
            case "2":
                initStartActivityDireccion(datosEspecEditDireccion);
                break;
            case "3":
                initStartActivityZonaTrabjo(datosEspecZonaTrabajo);
                break;
            case "4":
                if (view != null) view.initStartActivityEditarRubroTrabajo();
                break;
            case "5":
                initStartActivityPresentacion(usuarioPresentacion);
                break;
            case "6":
                initStartActivityCentroEstudios(centroEstudioLists);
                break;
            case "7":
                initStartActivityDatabancaria(datosBancaria);
                break;
            default:
                Log.d(TAG, "onClickItem : ");
                break;
        }*/
    }

    private void initStartActivityEspecialista(DatosEspecUi datosEspecUi) {
        switch (datosEspecUi.getId()) {
            case "1":
                if (view != null) view.initStartActivityEditarPerfil();
                break;
            case "2":
                initStartActivityDireccion(datosEspecEditDireccion);
                break;
            case "3":
                initStartActivityZonaTrabjo(datosEspecZonaTrabajo);
                break;
            case "4":
                if (view != null) view.initStartActivityEditarRubroTrabajo();
                break;
            case "5":
                initStartActivityPresentacion(usuarioPresentacion);
                break;
            case "6":
                initStartActivityCentroEstudios(centroEstudioLists);
                break;
            case "7":
                initStartActivityDatabancaria(datosBancaria);
                break;
            case "8":
                initStartActivityCambiarClave();
                break;
            default:
                Log.d(TAG, "onClickItem : ");
                break;
        }
    }

    private void initStartActivityCambiarClave() {
        if (view != null) view.initStartActivityCambiarClave();
    }

    private void initStartActivityCliente(DatosEspecUi datosEspecUi) {
        switch (datosEspecUi.getId()) {
            case "1":
                if (view != null) view.initStartActivityEditarPerfil();
                break;
            case "2":
                initStartActivityDireccion(datosEspecEditDireccion);
                break;
            case "3":
                initStartActivityCambiarClave();
                break;
            default:
                Log.d(TAG, "onClickItem : ");
                break;
        }
    }

    private void initStartActivityCentroEstudios(List<DatosCentroEstudioList> centroEstudioLists) {
        if (view != null) view.initStartActivityEditarCursos(centroEstudioLists);
    }

    private void initStartActivityDatabancaria(DatosBancaria datosBancaria) {
        if (view != null) view.initStartActivityEditarCuentaBancaria(datosBancaria);
    }

    private void initStartActivityPresentacion(String usuarioPresentacion) {
        if (usuarioPresentacion != null) {
            Log.d(TAG, "initStartActivityPresentacion" + datosEspecZonaTrabajo.getNombreDepartamento());
            if (view != null) view.initStartActivityEditarPresentacion(usuarioPresentacion);
        } else {
            Log.d(TAG, "initStartActivityDireccion : Datos Vacios ");
            if (view != null) view.initStartActivityEditarPresentacion(usuarioPresentacion);
        }
    }

    private void initStartActivityZonaTrabjo(DatosEspecZonaTrabajo datosEspecZonaTrabajo) {
        if (datosEspecZonaTrabajo != null) {
            Log.d(TAG, "initStartActivityDireccion : Datos Llenos" + datosEspecZonaTrabajo.getNombreDepartamento()
                    + "count Lista : " + datosEspecZonaTrabajo.getZonaTrabLists().size());
            if (view != null) view.initStartActivityEditarZonaTrabajo(datosEspecZonaTrabajo);
        } else {

            if (view != null) view.initStartActivityEditarZonaTrabajo(datosEspecZonaTrabajo);
        }
    }


    private void initUseCaseEditarDireccionRetrofit(String keyUser) {
        Call<MostrarPerfilResponse> call = loginService.mostrarPerfilDireccionUsuario(keyUser);
        call.enqueue(new Callback<MostrarPerfilResponse>() {
            @Override
            public void onResponse(Call<MostrarPerfilResponse> call, Response<MostrarPerfilResponse> response) {
                if (response == null) {
                    String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                    if (view != null) view.mostrarMensaje(mensajeError);
                    return;
                }
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        MostrarPerfilResponse mostrarPerfilResponse = response.body();
                        if (mostrarPerfilResponse == null) {
                            String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                            if (view != null) view.mostrarMensaje(mensajeError);
                            return;
                        }
                        MostrarPerfilResponse.MostrarUsuResponse mostrarUsuResponse = mostrarPerfilResponse.getMostrarUsuResponse();
                        if (mostrarUsuResponse == null) {
                            String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                            if (view != null) view.mostrarMensaje(mensajeError);
                            return;
                        }

                        String codigoDepartamento = mostrarUsuResponse.getCodigoDepartamento();
                        String nombreDepartamento = mostrarUsuResponse.getNombreDepartamento();
                        String codigoProvincia = mostrarUsuResponse.getCodigoProvincia();
                        String nombreProvincia = mostrarUsuResponse.getNombreProvincia();
                        String codigoDistrito = mostrarUsuResponse.getCodigoDistrito();
                        String nombreDistrito = mostrarUsuResponse.getNombreDistrito();
                        String nombreDireccion = mostrarUsuResponse.getDescripcionDireccion();
                        String latitud = mostrarUsuResponse.getUsuLatitud();
                        String longitud = mostrarUsuResponse.getUsuLongitud();

                        initUseCaseEditarDireccionLocal(codigoDepartamento, codigoProvincia,
                                codigoDistrito, latitud, longitud, nombreDireccion);


                        //Log.d(TAG, "mostrarUsuResponse==null : " + nombreDepartamento.trim());

                      /*  validarPorMostrarDireccion(nombreDepartamento, nombreProvincia, nombreDistrito, nombreDireccion,
                                codigoDepartamento, codigoProvincia, codigoDistrito, latitud, longitud);*/
                    }
                }

            }

            @Override
            public void onFailure(Call<MostrarPerfilResponse> call, Throwable t) {
                String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
                if (view != null) view.mostrarMensaje(mensajeError);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

    }

    private void initUseCaseEditarDireccionLocal(String codigoDepartamento, String codigoProvincia, String codigoDistrito, final String latitud, final String longitud, final String nombreDireccion) {
        if (codigoDepartamento == null) {
            Log.d(TAG, "No codigoDepartamento");
            String tipoDireccion = "2";
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        handler.execute(obtenerDireccion, new ObtenerDireccion.RequestValues(paisCodigo, codigoDepartamento, codigoProvincia, codigoDistrito),
                new UseCase.UseCaseCallback<ObtenerDireccion.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerDireccion.ResponseValue response) {

                        validarPorMostrarDireccion(response.getNombreDepartamento(), response.getNombreProvincia(),
                                response.getNombreDistrito(), nombreDireccion,
                                response.getCodigoDepartamento(), response.getCodigoProvincia(),
                                response.getCodigoDistrito(), latitud, longitud);
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "Error al traer la data");
                    }
                });
    }

    boolean dataFaltante = true;
    DatosEspecEditDireccion datosEspecEditDireccion;

    @Override
    public void onResume() {
        super.onResume();
    }

    private void validarPorMostrarDireccion(String nombreDepartamento, String nombreProvincia, String nombreDistrito, String nombreDireccion,
                                            String codigoDepartamento, String codigoProvincia, String codigoDistrito, String latitud, String longitud) {
        String tipoDireccion = "2";
        if (nombreDepartamento == null) {
            Log.d(TAG, "IF::NULOnombreDepartamento");
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        if (nombreProvincia == null) {
            Log.d(TAG, "IF::NULOnombreProvincia");
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }
        if (nombreDistrito == null) {
            Log.d(TAG, "IF::NULOnombreDistrito");
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }

        /*if (nombreDireccion == null) {
            Log.d(TAG, "IF::NULOnombreDireccion");
            if (view != null) view.estadoDireccion(onFalseData(), tipoDireccion);
            return;
        }*/
        datosEspecEditDireccion = new DatosEspecEditDireccion();
        datosEspecEditDireccion.setCodigoDepartamento(codigoDepartamento);
        datosEspecEditDireccion.setNombreDepartamento(nombreDepartamento);
        datosEspecEditDireccion.setCodigoProvincia(codigoProvincia);
        datosEspecEditDireccion.setNombreProvincia(nombreProvincia);
        datosEspecEditDireccion.setCodigoDistrito(codigoDistrito);
        datosEspecEditDireccion.setNombreDistrito(nombreDistrito);
        datosEspecEditDireccion.setNombreDireccion(nombreDireccion);
        datosEspecEditDireccion.setUsuLatitud(latitud);
        datosEspecEditDireccion.setUsuLongitud(longitud);
        if (view != null) view.estadoDireccion(onTrueData(), tipoDireccion);

    }

    private boolean onFalseData() {
        return dataFaltante = false;
    }

    private boolean onTrueData() {
        return dataFaltante = true;
    }


    private void initStartActivityDireccion(DatosEspecEditDireccion datosEspecEditDireccion) {
        if (datosEspecEditDireccion != null) {
            Log.d(TAG, "initStartActivityDireccion : Datos Llenos");
            if (view != null) view.initStartActivityEditarDireccion(datosEspecEditDireccion);
        } else {
            Log.d(TAG, "initStartActivityDireccion : Datos Vacios ");
            if (view != null) view.initStartActivityEditarDireccion(datosEspecEditDireccion);
        }
    }


}
