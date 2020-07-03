package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecZonaTrabList;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecZonaTrabajo;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.EspecPerfilDireccionActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad.DireccionUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad.DistritoUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.useCase.ListaDepartamento;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.useCase.ListaDistrito;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.useCase.ListaProvincia;
import com.application.boxmadikv1.utils.Constantes;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity.EXTRA_PERFIL_ZONA_TRABAJO;

public class EspecialistaPerfilDistritoPresenterImpl extends BaseActivityPresenterImpl<EspecialistaPerfilDistritoView> implements EspecialistaPerfilDistritoPresenter {

    public static final String TAG = EspecialistaPerfilDistritoPresenterImpl.class.getSimpleName();
    private ListaDepartamento listaDepartamento;
    private ListaProvincia listaProvincia;
    private ListaDistrito listaDistrito;
    Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);


    public EspecialistaPerfilDistritoPresenterImpl(UseCaseHandler handler, Resources res
            , ListaDepartamento listaDepartamento, ListaProvincia listaProvincia, ListaDistrito listaDistrito) {
        super(handler, res);
        this.listaDepartamento = listaDepartamento;
        this.listaProvincia = listaProvincia;
        this.listaDistrito = listaDistrito;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    /* String nombreEdit, apellidosEdit, celularEdit, usuarioFoto, codigoDepartamento, codigoProvincia, codigoDistrito, descripcionDireccion;
     double latitud, longitud;

     @Override
     public void setExtras(Bundle extras) {
         super.setExtras(extras);
         if (extras == null) return;
         this.nombreEdit = extras.getString(EspecPerfilDireccionActivity.EXTRA_DIRECCION_NOMBRE_EDIT);
         this.apellidosEdit = extras.getString(EspecPerfilDireccionActivity.EXTRA_DIRECCION_APELLIDOS_EDIT);
         this.celularEdit = extras.getString(EspecPerfilDireccionActivity.EXTRA_DIRECCION_CELULAR_EDIT);
         this.usuarioFoto = extras.getString(EspecPerfilDireccionActivity.EXTRA_DIRECCION_USUARIO_FOTO_EDIT);
         this.codigoDepartamento = extras.getString(EspecPerfilDireccionActivity.EXTRA_DIRECCION_CODIGO_DEPARTAMENTO_EDIT);
         this.codigoProvincia = extras.getString(EspecPerfilDireccionActivity.EXTRA_DIRECCION_CODIGO_PROVINCIA_EDIT);
         this.codigoDistrito = extras.getString(EspecPerfilDireccionActivity.EXTRA_DIRECCION_CODIGO_DISTRITO_EDIT);
         this.latitud = extras.getDouble(EspecPerfilDireccionActivity.EXTRA_DIRECCION_CODIGO_LATITUD_EDIT);
         this.longitud = extras.getDouble(EspecPerfilDireccionActivity.EXTRA_DIRECCION_CODIGO_lONGITUD_EDIT);
         this.descripcionDireccion = extras.getString(EspecPerfilDireccionActivity.EXTRA_DIRECCION_CODIGO_DESCRIPCION_DIRECCION);
         Log.d(TAG, "EspecialistaPerfilusuarioFoto : " + usuarioFoto);

         String paisCodigo = "51";
         initListaDepartamento(paisCodigo);
     }*/
    String tipoLlegadaActivity;
    DatosEspecZonaTrabajo datosEspecZonaTrabajo;


    @Override
    public void onStart() {
        super.onStart();
        mostrarDataInicial(datosEspecZonaTrabajo);
        initListaDepartamento(codigoPais);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        /*Hay 2 Tipos de Llegada - Al inicia y Otro al Editart*/
        this.tipoLlegadaActivity = extras.getString("tipoInicial");
        validacionExtrasLLegada(tipoLlegadaActivity, extras);

    }

    private void validacionExtrasLLegada(String tipoLlegadaActivity, Bundle extras) {
        switch (tipoLlegadaActivity) {
            case "0":
                Log.d(TAG, "No se a Guardado nada ");
                break;
            case "1":
                this.datosEspecZonaTrabajo = extras.getParcelable(EXTRA_PERFIL_ZONA_TRABAJO);
                Log.d(TAG, "1");
                break;
            default:
                Log.d(TAG, "default");
                break;
        }

    }

    private void mostrarDataInicial(DatosEspecZonaTrabajo datosEspecZonaTrabajo) {
        if (datosEspecZonaTrabajo != null) {
            String nombreDepartamento = datosEspecZonaTrabajo.getNombreDepartamento();
            String nombreProvincia = datosEspecZonaTrabajo.getNombreProvincia();
            if (view != null) {
                view.mostrarTextViewDepartamento("Dpto: " + nombreDepartamento);
                view.mostrarTextViewProvincia("Prov: " + nombreProvincia);
            }
        }

    }

    private void initListaDepartamento(String paisCodigo) {
        handler.execute(listaDepartamento, new ListaDepartamento.RequestValues(paisCodigo),
                new UseCase.UseCaseCallback<ListaDepartamento.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaDepartamento.ResponseValue response) {
                        if (response.getTipoDepartamentoUis() == null) return;
                        if (view != null) {
                            view.mostrarListaTipoDepartamento(response.getTipoDepartamentoUis());
                            view.deshabilitaProvDist();
                        }

                        initTipoDepartmentoLleno(datosEspecZonaTrabajo);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initTipoDepartmentoLleno(DatosEspecZonaTrabajo datosEspecZonaTrabajo) {
        if (datosEspecZonaTrabajo != null) {
            tipoDepartamentoUi = new TipoDepartamentoUi();
            tipoDepartamentoUi.setId(datosEspecZonaTrabajo.getCodigoDepartamento());
            tipoDepartamentoUi.setDescripcion(datosEspecZonaTrabajo.getNombreDepartamento());
            initUseCaseTipoProvincia(codigoPais, datosEspecZonaTrabajo.getCodigoDepartamento());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        validacionData();
    }

    private void validacionData() {
        if (datosEspecZonaTrabajo != null) {
            //if (view != null) view.deshabilitaFunciones();
            llenarListaDistritos(datosEspecZonaTrabajo);
        } else {
            if (view != null) view.habilitasFunciones();
        }
    }

    TipoDepartamentoUi tipoDepartamentoUi;

    @Override
    public void onSpinnerTipoDepartamento(TipoDepartamentoUi tipoDepartamentoUi) {
        this.tipoDepartamentoUi = tipoDepartamentoUi;
        if (view != null)
            view.mostrarTextViewDepartamento(tipoDepartamentoUi.getDescripcion());
        initUseCaseTipoProvincia(codigoPais, tipoDepartamentoUi.getId());
    }

    TipoProvinciaUi tipoProvinciaUi;

    @Override
    public void onSpinnerTipoProvincia(TipoProvinciaUi tipoProvinciaUi) {
        this.tipoProvinciaUi = tipoProvinciaUi;
        if (view != null)
            view.mostrarTextViewProvincia(tipoProvinciaUi.getDescripcion());
        initUseCaseTipoDistrito(codigoPais, tipoDepartamentoUi.getId(), tipoProvinciaUi.getId());
    }

    TipoDistritoUi tipoDistritoUi;

    @Override
    public void onSpinnerTipoDistrito(TipoDistritoUi tipoDistritoUi) {
        this.tipoDistritoUi = tipoDistritoUi;
        if (view != null)
            view.mostrarTextViewDistrito(tipoDistritoUi.getDescripcion());
    }

    String tipoDistritos;

    @Override
    public void onClickAgregarDistritos(String nombreDistrito) {

        if (nombreDistrito.isEmpty()) {
            view.mostrarMensaje("Escriba o Seleccione una Distrito");
            return;
        }
        if (tipoDistritoUi == null) {
            if (view != null) {
                view.mostrarMensaje("Distrito no Encontrada");
            }
            return;
        }
        if (tipoDistritoUi.getDescripcion().isEmpty() || tipoDistritoUi.getDescripcion() == null) {
            if (view != null) view.mostrarMensaje("Distrito no Encontrada");
            return;
        }
        String nombreDtto = "Dtto: " + tipoDistritoUi.getDescripcion();
        Log.d(TAG, "nombreDtto: " + nombreDtto);
        Log.d(TAG, "onClickAgregarDistritos : " + nombreDtto +
                " tipoDistritoUi.getDescripcion() : " + nombreDistrito);
        if (nombreDtto.equals(nombreDistrito)) {

            Log.d(TAG, "ifififififif : ");
        } else {
            if (view != null) {
                view.mostrarMensaje("Distrito no encontrada");
                Log.d(TAG, "elseelseelse : ");
                return;
            }
            Log.d(TAG, "elseelseelse : ");
            return;
        }
        this.tipoDistritos = nombreDistrito;

        //tipoDistritoUi.setDescripcion(nombreDistrito);
        Log.d(TAG, "onClickAgregarDistritos : " + nombreDistrito);
        if (view != null) view.validarItemsAgregar(tipoDistritoUi);
    }

    @Override
    public void onValidarItemsAgregar(int totalItem, TipoDistritoUi tipoDistritoUi) {

        Log.d(TAG, "tipoDistritoUitipoDistritoUi : " + tipoDistritoUi.getDescripcion() +
                "   tipoDistritos : " + tipoDistritos);
        //tipoDistritoUi.setDescripcion(tipoDistritos);
        if (totalItem > 4) {
            if (view != null) view.mostrarMensaje("Llego asu limite");
            return;
        } else {
            if (listaDistritos != null) {
                for (int i = 0; i < listaDistritos.size(); i++) {
                    TipoDistritoUi especialidadUi2 = listaDistritos.get(i);

                    Log.d(TAG, "onValidarItemsAgregar : " + especialidadUi2.getDescripcion());
                   /* if (!tipoDistritoUi.getDescripcion().equals(tipoDistritos)) {
                        view.mostrarMensaje("Distrito no encontrada3");
                        return;
                    }*/
                    // tipoDistritoUi.setDescripcion(tipoDistritos);
                    //   if (tipoDistritoUi.getDescripcion().equals(especialidadUi2.getDescripcion())) {

                    if (tipoDistritoUi.getDescripcion().equals(especialidadUi2.getDescripcion())) {
                        if (view != null) {
                            view.mostrarMensaje("Zona Trabajo ya ingresada");
                        }
                        return;
                    }

                }
            }
            listaDistritos.add(tipoDistritoUi);
            if (view != null) view.agregarItemDistrito(tipoDistritoUi);
            return;
        }
    }

    @Override
    public void onClickEliminar(TipoDistritoUi tipoDistritoUi) {
       /* if (datosEspecZonaTrabajo != null) {
            Log.d(TAG, "NO se puede eliminar");
        } else {
            listaDistritos.remove(tipoDistritoUi);
            if (view != null) {
                view.eliminarEspecialidad(tipoDistritoUi);
            }
        }*/
        listaDistritos.remove(tipoDistritoUi);
        if (view != null) {
            view.eliminarEspecialidad(tipoDistritoUi);
        }
    }

    @Override
    public void onClickSiguiente() {

        if (view != null) view.deshabilitarButtonGuardar();
        Log.d(TAG, "onClickSiguiente");
        if (tipoDepartamentoUi == null) {
            Log.d(TAG, "Necesitas Esribir un tipo DireccionUi");
            String mensaje = "Ingrese Departamento";
            if (view != null) {
                view.mostrarMensajeErrorAutoCompleteDepartamentoError(mensaje);
                view.habilitarButtonGuardar();
            }
            return;
        }
        if (tipoProvinciaUi == null) {
            Log.d(TAG, "Necesitas Esribir un tipo Provincia");
            String mensaje = "Ingrese Provincia";
            if (view != null) {
                view.mostrarMensajeErrorAutoCompleteProvinciaError(mensaje);
                view.habilitarButtonGuardar();
            }
            return;
        }
        int countListaDistritos = listaDistritos.size();
        if (countListaDistritos == 0) {
            String mensaje = "Tiene que eligiar por lo menos 1 Distrito";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilitarButtonGuardar();
            }
            return;
        }
        if (view != null) view.mostrarProgressBarDialog();
        //initGuardarDistritos(listaDistritos);
        validarDataExistente(listaDistritos);

    }

    private void validarDataExistente(final List<TipoDistritoUi> listaDistritosList) {

        Call call = loginService.validarZonaTrabajoExiste(keyUser, codigoPais);
        call.enqueue(
                new Callback<DefaultResponseEstados>() {
                    @Override
                    public void onResponse(Call<DefaultResponseEstados> call, Response<DefaultResponseEstados> response) {
                        DefaultResponseEstados defaultResponse = response.body();
                        if (defaultResponse == null) {
                            String mensaje = "Ocurrio Algun Error";
                            if (view != null) view.habilitarButtonGuardar();
                            Log.d(TAG, "mensaje : " + mensaje);
                            return;
                        }
                        if (defaultResponse != null) {
                            if (defaultResponse.getError()) {
                                String mensaje = "Ocurrio Algun Error";
                                if (view != null) view.habilitarButtonGuardar();
                                Log.d(TAG, "mensaje : " + mensaje);
                            } else {
                                Log.d(TAG, "SE REGISTRARON CORRECTAMENTE");
                                initRetrofitValidate(defaultResponse.getEstado(),
                                        keyUser, codigoPais, listaDistritosList);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<DefaultResponseEstados> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage().toString());
                    }
                }
        );
    }

    private void initRetrofitValidate(String estado, String keyUser, String paisCodigoPeru, List<TipoDistritoUi> listaDistritosList) {
        switch (estado) {
            case DefaultResponseEstados.RUBRO_USUARIO_EXISTE:
                /*Debemos Actualizar la anterior data a inactiva = 0*/
                actualizarDataGsonDistritos(estado, keyUser, paisCodigoPeru, listaDistritosList);
                Log.d(TAG, "ACTUALIZARRUBRODATAINACTIVA : ");
                break;
            case DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE:
                actualizarDataGsonDistritos(estado, keyUser, paisCodigoPeru, listaDistritosList);
                Log.d(TAG, "INSERTAR : ");
                break;
        }
    }

    private void actualizarDataGsonDistritos(final String estado, final String keyUser, final String paisCodigoPeru, final List<TipoDistritoUi> listaDistritosList) {
        Gson gson = new Gson();
        String jsonListDistritos = gson.toJson(listaDistritosList);
        Log.d(TAG, "jsonListDistritos: " + jsonListDistritos);
        loginService.guardarDistritoEspecialista(keyUser, paisCodigoPeru, tipoDepartamentoUi.getId(),
                tipoProvinciaUi.getId(), jsonListDistritos, estado).enqueue(
                new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if (estado.equals(DefaultResponseEstados.RUBRO_USUARIO_EXISTE)) {
                            Log.d(TAG, "RUBRO_USUARIO_EXISTEdefaultResponseEstado : " + estado);
                            initRetrofitValidate(DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE, keyUser, paisCodigoPeru, listaDistritosList);
                            return;
                        } else if (estado.equals(DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE)) {
                            Log.d(TAG, "RUBRO_USUARIO_NO_EXISTEdefaultResponseEstado : " + estado);
                            switch (tipoLlegadaActivity) {
                                case "0":
                                    String tipoUsuarioEspecialista = "2";
                                    if (view != null) {
                                        view.ocultarProgressBarDialog();
                                        view.habilitarButtonGuardar();
                                        view.initStartActivityMenuEspecialista(tipoDepartamentoUi.getId(), tipoUsuarioEspecialista);
                                    }
                                    break;
                                case "1":
                                    if (view != null) {
                                        view.ocultarProgressBarDialog();
                                        view.habilitarButtonGuardar();
                                        view.finishActivity(tipoDepartamentoUi.getId());
                                    }

                                    Log.d(TAG, "1");
                                    break;
                            }
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        String guardarDistritos = "Guardando Distritos";
                        String mensaje = "Problemas con nuestros Servidores " + guardarDistritos;
                        if (view != null) {
                            view.mostrarMensaje(mensaje);
                            view.ocultarProgressBarDialog();
                        }
                    }
                }
        );
    }

    String keyUser, codigoPais;

    @Override
    public void onKeyUser(String keyUser, String codigoPais) {
        Log.d(TAG, "onKeyUser : " + codigoPais);
        this.keyUser = keyUser;
        this.codigoPais = codigoPais;

    }

    @Override
    public void onClickCloseDepart() {
        this.tipoDepartamentoUi = null;
        this.tipoProvinciaUi = null;
        this.tipoDistritoUi = null;
        listaDistritos.clear();
        if (view != null) {
            view.limpiarDepartNull();
            view.limpiarListaDistritos(listaDistritos);
        }
    }

    @Override
    public void onClickCloseProv() {
        this.tipoProvinciaUi = null;
        this.tipoDistritoUi = null;
        listaDistritos.clear();
        if (view != null) {
            view.limpiarProvNull();
            view.limpiarListaDistritos(listaDistritos);
        }
    }

    @Override
    public void onClickCloseDistrit() {
        this.tipoDistritoUi = null;
        if (view != null) view.limpiarDistriNull();
    }


    private void initGuardarDistritos(final List<TipoDistritoUi> datosCursosList) {
        //Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Gson gson = new Gson();
        String jsonList = gson.toJson(datosCursosList);
        Log.d(TAG, "initGuardarDistritos : " + jsonList);
        for (TipoDistritoUi tipoDistritoUi : datosCursosList) {
            String estado = "1";
            loginService.guardarDistritoEspecialista(keyUser, codigoPais,
                    tipoDepartamentoUi.getId(), tipoProvinciaUi.getId(), tipoDistritoUi.getId(), estado).enqueue(
                    new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            DefaultResponse defaultResponse = response.body();
                            String guardarDistritos = "Guardando Distritos";
                            if (defaultResponse == null) {
                                String mensaje = "Problemas con nuestros Servidores " + guardarDistritos;
                                if (view != null) {
                                    view.ocultarProgressBarDialog();
                                    view.mostrarMensaje(mensaje);
                                }
                                return;
                            } else {
                                if (defaultResponse.getError()) {
                                    if (view != null) {
                                        view.mostrarMensaje(defaultResponse.getMessage() + " " + guardarDistritos);
                                        view.ocultarProgressBarDialog();
                                    }
                                    return;
                                } else {
                                    // view.mostrarMensaje(defaultResponse.getMessage());
                                    /*Aqui es porque Registro Correctamente*/
                                    validarTipoLLegada(tipoLlegadaActivity);
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {
                            String guardarDistritos = "Guardando Distritos";
                            String mensaje = "Problemas con nuestros Servidores " + guardarDistritos;
                            if (view != null) {
                                view.mostrarMensaje(mensaje);
                                view.ocultarProgressBarDialog();
                            }
                        }
                    }
            );
        }

    }

    private void validarTipoLLegada(String tipoLlegadaActivity) {
        switch (tipoLlegadaActivity) {
            case "0":
                String tipoUsuarioEspec = "2";
                /*if (view != null) {
                    view.ocultarProgressBarDialog();
                    view.initStartActivityMenuEspecialista(tipoDepartamentoUi.getId());
                }*/
                actualizarRetrofitUserEspec(keyUser, codigoPais, tipoUsuarioEspec);
                break;
            case "1":
                if (view != null) {
                    view.ocultarProgressBarDialog();
                    //view.finishActivity();
                }

                break;
        }
    }

    private void actualizarRetrofitUserEspec(String keyUser, String paisCodigoPeru, String tipoUsuarioEspec) {
        Call<DefaultResponse> call = loginService.actualizarTipoUsuario(keyUser, paisCodigoPeru, tipoUsuarioEspec);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    if (view != null) {
                        String mensaje = "Error con nuestros servidores";
                        view.mostrarMensaje(mensaje);
                        view.ocultarProgressBarDialog();
                    }
                    return;
                } else {
                    if (defaultResponse.getError()) {
                        if (view != null) {
                            view.mostrarMensaje(defaultResponse.getMessage());
                            view.ocultarProgressBarDialog();
                        }
                        return;
                    } else {
                        String tipoUsuarioEspec = "2";
                        if (view != null) {
                            view.ocultarProgressBarDialog();
                            view.initStartActivityMenuEspecialista(tipoDepartamentoUi.getId(), tipoUsuarioEspec);
                        }
                        Log.d(TAG, "CUANDO ACTUALIZO CORRECTAMENTE");
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }


    List<TipoDistritoUi> listaDistritos = new ArrayList<>();

    private void initUseCaseTipoDistrito(String paisCodigoPeru, String id, String id1) {
        handler.execute(listaDistrito, new ListaDistrito.RequestValues(paisCodigoPeru, id, id1),
                new UseCase.UseCaseCallback<ListaDistrito.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaDistrito.ResponseValue response) {

                        if (response.getTipoDistritoUis() == null) return;
                        if (view != null)
                            view.mostrarListaTipoDistrito(response.getTipoDistritoUis());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseCaseTipoProvincia(String paisCodigoPeru, String id) {
        handler.execute(listaProvincia, new ListaProvincia.RequestValues(paisCodigoPeru, id),
                new UseCase.UseCaseCallback<ListaProvincia.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaProvincia.ResponseValue response) {
                        if (response.getTipoProvinciaUis() == null) return;
                        if (view != null)
                            view.mostrarListaTipoProvincia(response.getTipoProvinciaUis());
                        initTipoProvinciaLleno(datosEspecZonaTrabajo);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initTipoProvinciaLleno(DatosEspecZonaTrabajo datosEspecZonaTrabajo) {
        if (datosEspecZonaTrabajo != null) {
            tipoProvinciaUi = new TipoProvinciaUi();
            tipoProvinciaUi.setId(datosEspecZonaTrabajo.getCodigoProvincia());
            tipoProvinciaUi.setDescripcion(datosEspecZonaTrabajo.getNombreProvincia());
            initUseCaseTipoDistrito(codigoPais, datosEspecZonaTrabajo.getCodigoDepartamento(), datosEspecZonaTrabajo.getCodigoProvincia());

        }

    }

    private void llenarListaDistritos(DatosEspecZonaTrabajo datosEspecZonaTrabajo) {
        if (datosEspecZonaTrabajo != null) {
            for (DatosEspecZonaTrabList zonaTrabList : datosEspecZonaTrabajo.getZonaTrabLists()) {
                TipoDistritoUi tipoDistritoUi = new TipoDistritoUi();
                tipoDistritoUi.setId(zonaTrabList.getCodigoDistrito());
                tipoDistritoUi.setDescripcion(zonaTrabList.getNombreDistrito());
                listaDistritos.add(tipoDistritoUi);
            }
            if (view != null) view.setMostrarLista(listaDistritos);
        }

    }
}
