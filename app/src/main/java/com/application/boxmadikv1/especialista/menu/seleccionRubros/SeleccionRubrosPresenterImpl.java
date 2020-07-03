package com.application.boxmadikv1.especialista.menu.seleccionRubros;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.api.response.ListaRubrosEspecResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.useCase.ListarSeleccionRubros;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeleccionRubrosPresenterImpl extends BaseActivityPresenterImpl<SeleccionRubrosView> implements SeleccionRubrosPresenter {


    public static final String TAG = SeleccionRubrosPresenterImpl.class.getSimpleName();

    ListarSeleccionRubros listarSeleccionRubros;
    Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

    public SeleccionRubrosPresenterImpl(UseCaseHandler handler, Resources res, ListarSeleccionRubros listarSeleccionRubros) {
        super(handler, res);
        this.listarSeleccionRubros = listarSeleccionRubros;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    String tipoInicial;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.tipoInicial = extras.getString("tipoInicial");
        if (tipoInicial == null) return;
    }

    @Override
    public void onStart() {
        super.onStart();
        initUseCaseListaRubrosSel();
    }

    List<SeleccionRubrosUi> seleccionRubrosUiList;

    private void initUseCaseListaRubrosSel() {
        if (view != null) view.mostrarProgressBar();
        handler.execute(listarSeleccionRubros, new ListarSeleccionRubros.RequestValues(codigoPais),
                new UseCase.UseCaseCallback<ListarSeleccionRubros.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarSeleccionRubros.ResponseValue response) {
                        if (response.getSeleccionRubrosUiList() == null) return;
                        seleccionRubrosUiList = response.getSeleccionRubrosUiList();
//                        if (view != null) {
//                            view.ocultarProgressBar();
//                            view.mostrarListaRubrosSel(response.getSeleccionRubrosUiList());
//                        }
                        if (tipoInicial != null) {
                            initUseRetrofitCheckRubros();
                            return;
                        }

                        if (view != null) {
                            view.ocultarProgressBar();
                            view.mostrarListaRubrosSel(seleccionRubrosUiList);
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseRetrofitCheckRubros() {
        loginService.mostrarListaRubrosEspec(keyUser, codigoPais).enqueue(new Callback<ListaRubrosEspecResponse>() {
            @Override
            public void onResponse(Call<ListaRubrosEspecResponse> call, Response<ListaRubrosEspecResponse> response) {
                ListaRubrosEspecResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        String mensaje = "Ocurrio Algun Error";
                        Log.d(TAG, "mensaje : " + mensaje);
                    } else {
                        Log.d(TAG, "SE TRAJERON LOS RUBROS CORRECTAMENTE");
                        List<ListaRubrosEspecResponse.ListaRubrosEspec> listaRubrosEspec = defaultResponse.getListaRubros();
                        if (view != null) view.ocultarProgressBar();
                        for (int i = 0; i < listaRubrosEspec.size(); i++) {
                            ListaRubrosEspecResponse.ListaRubrosEspec rubrosEspec = listaRubrosEspec.get(i);
                            for (SeleccionRubrosUi seleccionRubrosUi : seleccionRubrosUiList) {
                                if (seleccionRubrosUi.getIdSeleccionRubroId().equals(rubrosEspec.getRubro_Rub_Codigo())) {
                                    seleccionRubrosUi.setEstadoRubro(false);
                                    Log.d(TAG, "seleccionRubrosUi : " + seleccionRubrosUi.getIdSeleccionRubroId());
                                }

                            }
                        }
                        if (view != null) view.mostrarListaRubrosSel(seleccionRubrosUiList);
                        // if(view!=null)view.actualizarLista();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListaRubrosEspecResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    @Override
    public void onClickItemRubroSeleccion(SeleccionRubrosUi seleccionRubrosUi) {
        int conteoClickRubro = conteoRubros();
        validarConteo(conteoClickRubro, seleccionRubrosUi);
        Log.d(TAG, "onClickItemRubroSeleccionPResenter : ");

    }

    private void validarConteo(int conteoClickRubro, SeleccionRubrosUi seleccionRubrosUi) {
        if (conteoClickRubro < 3) {
            Log.d(TAG, "if : " + conteoClickRubro);
            if (seleccionRubrosUi.isEstadoRubro()) {
                seleccionRubrosUi.setEstadoRubro(false);
                if (view != null) view.mostrarCheckTrue(seleccionRubrosUi);
            } else {
                seleccionRubrosUi.setEstadoRubro(true);
                if (view != null) view.mostrarOcultarFalse(seleccionRubrosUi);
            }
        } else {
            seleccionRubrosUi.setEstadoRubro(true);
            if (view != null) view.mostrarOcultarFalse(seleccionRubrosUi);
            if (view != null) view.mostrarMensaje("Solo se puede elegir como Maximo 3");
            Log.d(TAG, "else : " + conteoClickRubro);
        }
    }


    private int conteoRubros() {
        int conteo = 0;
        for (SeleccionRubrosUi seleccionRubrosUi : seleccionRubrosUiList) {
            if (!seleccionRubrosUi.isEstadoRubro()) {
                conteo++;
                // stringList.add(seleccionRubrosUi.getIdSeleccionRubroId());
            }
            continue;
        }
        return conteo;
    }

    String zonaTrabajodepartamentoId;

    @Override
    public void onClickSiguiente(String departamentoId) {
        if(view!=null)view.deshabilitarButtonSiguiente();
        this.zonaTrabajodepartamentoId = departamentoId;
        int conteo = 0;
        if (seleccionRubrosUiList == null) {
            if (view != null){
                view.mostrarMensaje("Seleccione al menos 1 Item");
                view.habilitarButtonSiguiente();
            }
        }
        int conteoRubros = conteoRubros();
       /* for (SeleccionRubrosUi seleccionRubrosUi : seleccionRubrosUiList) {
            if (!seleccionRubrosUi.isEstadoRubro()) {
                conteo++;
                // stringList.add(seleccionRubrosUi.getIdSeleccionRubroId());
            }
            continue;
        }*/
        if (conteoRubros == 0) {
            if (view != null) {
                view.mostrarMensaje("Seleccione al menos 1 Item");
                view.habilitarButtonSiguiente();
            }
        } else {
            if (conteoRubros < 4) {
                Log.d(TAG, "if : " + conteoRubros);
                ArrayList<String> stringList = new ArrayList<>();
                for (SeleccionRubrosUi seleccionRubrosUi : seleccionRubrosUiList) {
                    if (!seleccionRubrosUi.isEstadoRubro()) {
                        stringList.add(seleccionRubrosUi.getIdSeleccionRubroId());
                    }
                    continue;
                }

                initRetrofitUsuRubroValidar(stringList, keyUser);
            } else {
                if (view != null){
                    view.mostrarMensaje("Solo se puede elegir como Maximo 3");
                    view.habilitarButtonSiguiente();
                }
                Log.d(TAG, "else : " + conteoRubros);
            }
        }
    }

    private void initRetrofitUsuRubroValidar(final ArrayList<String> stringList, final String keyUser) {

        //System.out.println();
        Log.d(TAG, "stringList: " + new Gson().toJson(stringList));
        Gson gson = new Gson();
        final String jsonList = gson.toJson(stringList);

        loginService.validarUsuRubroExiste(keyUser).enqueue(new Callback<DefaultResponseEstados>() {
            @Override
            public void onResponse(Call<DefaultResponseEstados> call, Response<DefaultResponseEstados> response) {
                DefaultResponseEstados defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        String mensaje = "Ocurrio Algun Error";
                        Log.d(TAG, "mensaje : " + mensaje);
                    } else {
                        Log.d(TAG, "SE REGISTRARON CORRECTAMENTE");
                        initRetrofitUsuRubro(defaultResponse.getEstado(),
                                stringList, keyUser, jsonList);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponseEstados> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }


    private void initRetrofitUsuRubro(String defaultResponseEstado, ArrayList<String> stringListIdRubros, String keyUser, String jsonList) {
        switch (defaultResponseEstado) {
            case DefaultResponseEstados.RUBRO_USUARIO_EXISTE:
                /*Debemos Actualizar la anterior data a inactiva = 0*/
                //actualizarDataInactivaUsuRubro(defaultResponseEstado, stringListIdRubros, keyUser,jsonList);
                actualizarDataInactivaUsuRubroGson(defaultResponseEstado, stringListIdRubros, keyUser, jsonList);
                Log.d(TAG, "ACTUALIZARRUBRODATAINACTIVA : ");
                break;
            case DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE:
                actualizarDataInactivaUsuRubroGson(defaultResponseEstado, stringListIdRubros, keyUser, jsonList);
                Log.d(TAG, "INSERTAR : ");
                break;
        }

    }

    private void actualizarDataInactivaUsuRubroGson(final String defaultResponseEstado, final ArrayList<String> stringListIdRubros, final String keyUser, final String jsonList) {

        loginService.guardarUsuRubroGson(keyUser, jsonList, defaultResponseEstado, codigoPais).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                // DefaultResponse loginResponse = response.body();
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    if(view!=null)view.habilitarButtonSiguiente();
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        String mensaje = "Ocurrio Algun Error";
                        Log.d(TAG, "mensaje : " + mensaje);
                        if(view!=null)view.habilitarButtonSiguiente();
                    } else {
                            /*conteoActualizacion++;
                            contarTipoEstados(defaultResponse,)
                            Log.d(TAG, "SE REGISTRARON CORRECTAMENTE +" + conteoActualizacion);*/
                        // if (view != null) view.startMenuEspecialista(stringListIdRubros);
                       /* if (view != null) {
                            view.ocultarDialogProgress();
                            view.limpiarCajasTexto();
                            view.startMenuClienteActivity(CREADO_CORRECTAMENTE_PROPUESTA);
                        }*/
                        //Log.d(TAG, "estadoestado : " + defaultResponseEstado);


                        if (defaultResponseEstado.equals(DefaultResponseEstados.RUBRO_USUARIO_EXISTE)) {
                            Log.d(TAG, "RUBRO_USUARIO_EXISTEdefaultResponseEstado : " + defaultResponseEstado);
                            initRetrofitUsuRubro(DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE, stringListIdRubros, keyUser, jsonList);
                            return;
                        } else if (defaultResponseEstado.equals(DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE)) {
                            if (tipoInicial != null) {
                                if (view != null){
                                    view.finishActivityRubros(stringListIdRubros);
                                    view.habilitarButtonSiguiente();
                                }
                            } else {
                                Log.d(TAG, "RUBRO_USUARIO_NO_EXISTEdefaultResponseEstado : " + defaultResponseEstado);
                                if (view != null) {
                                    if (view != null){
                                        view.startMenuEspecialista(stringListIdRubros);
                                        view.habilitarButtonSiguiente();
                                    }

                                }
                                return;
                            }

                        }
                        //initRetrofitUsuRubroValidar(stringListIdRubros, keyUser);
                        // return;
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

    }


    @Override
    public void onValidateSeleccionRubros(TinyDB tinydb) {

    }

    String keyUser, codigoPais;

    @Override
    public void onKeyUser(String keyUser, String codigoPais) {
        this.keyUser = keyUser;
        Log.d(TAG, "onKeyUser : " + keyUser);
        this.codigoPais = codigoPais;
    }


}
