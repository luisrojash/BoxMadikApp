package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosBancaria;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.entidad.TipoBancoUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.useCase.MostrarListaBanco;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.EspecBancoActivity.EXTRA_DATA_BANCARAIA_ACTIVITY;

public class EspecBancoPresenterImpl extends BaseActivityPresenterImpl<EspecBancoView> implements EspecBancoPresenter {

    public static final String TAG = EspecBancoPresenterImpl.class.getSimpleName();

    public static final String EXTRA_CORRECTAMENTE_ESPEC_BANCO = EspecBancoPresenterImpl.class.getCanonicalName();

    private MostrarListaBanco mostrarListaBanco;

    DatosBancaria datosBancaria;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.datosBancaria = extras.getParcelable(EXTRA_DATA_BANCARAIA_ACTIVITY);


    }

    private void mostrarDataLlena() {
        if (datosBancaria != null) {
            this.tipoBancoUi = new TipoBancoUi();
            this.tipoEstadStringTipoCuenta = datosBancaria.getTipoCuentaCodigo();
            tipoBancoUi.setId(datosBancaria.getBancoCodigo());
            tipoBancoUi.setDescripcion(datosBancaria.getBancoNombre());
            if (view != null) {
                view.mostrarDataInicial(datosBancaria);
                //view.deshabilitarTextBank();
            }

        }

    }


    private void validarTipoBancoLleno() {
        for(TipoBancoUi tipoBancoUi : tipoBancoUiList){
            if(tipoBancoUi.getId().equals(datosBancaria.getBancoCodigo())){
                int posicion = tipoBancoUiList.indexOf(tipoBancoUi);
                if(view!=null)view.mostrarSpinnerLleno(posicion);
                return;
            }
        }
    }

    public EspecBancoPresenterImpl(UseCaseHandler handler, Resources res, MostrarListaBanco mostrarListaBanco) {
        super(handler, res);
        this.mostrarListaBanco = mostrarListaBanco;
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

    }

    String keyUser, codigoPais;

    @Override
    public void onKeyUser(String keyUser, String codigoPais) {
        this.keyUser = keyUser;
        this.codigoPais = codigoPais;
        Log.d(TAG, "onKeyUser : " + keyUser);
        stringListTipoCuenta = new ArrayList<>();
        stringListTipoCuenta.add("TIPO CUENTA");
        stringListTipoCuenta.add("AHORRO");
        stringListTipoCuenta.add("CUENTA CORRIENTE");

        initUseCaseTipoBancoLista(codigoPais);

    }

    TipoBancoUi tipoBancoUi;

    @Override
    public void onClickTipoBanco(TipoBancoUi tipoBancoUi) {
        this.tipoBancoUi = tipoBancoUi;
        if (view != null) view.mostrarTextViewBanco(tipoBancoUi.getDescripcion());
    }

    String tipoEstadStringTipoCuenta;

    @Override
    public void onSpinnerTipoCuenta(String tipoRangoPrecioUi) {
        switch (tipoRangoPrecioUi) {
            case "TIPO CUENTA":
                this.tipoEstadStringTipoCuenta = "0";
                break;
            case "AHORRO":
                this.tipoEstadStringTipoCuenta = "1";
                break;
            case "CUENTA CORRIENTE":
                this.tipoEstadStringTipoCuenta = "2";
                break;
        }

    }

    @Override
    public void onClickGuardar(String numeroCuenta, String numeroCuentainte) {
        if (view != null) {
            view.deshabilitarButtonGuardar();
            view.mostrarDialogProgress();
        }
        if (tipoBancoUi == null) {
            Log.d(TAG, "");
            String mensaje = "Necesitas Banco";
            if (view != null) {
                view.mostrarMensajeErrorBancoText(mensaje);
                view.habilitarButtonGuardar();
                view.ocultarDialogProgress();
            }
            return;
        }
        if(tipoBancoUi.getId().equals("0")){
            if (view != null) {
               // view.mostrarMensajeErrorBancoText("");
                view.mostrarMensaje("Elija el Tipo Banco Correcto");
                view.habilitarButtonGuardar();
                view.ocultarDialogProgress();
            }
            return;
        }
        if (tipoEstadStringTipoCuenta.equals("0")) {
            Log.d(TAG, "");
            String mensaje = "Elije Tipo Cuenta";
            if (view != null) {
                view.mostrarMensaje(mensaje);
                view.habilitarButtonGuardar();
                view.ocultarDialogProgress();
            }
            return;
        }
        if (numeroCuenta.isEmpty() || numeroCuenta.equals(" ") || numeroCuenta == null) {
            String mensaje = "Escriba Correctamente ";
            if (view != null) {
                view.mostrarMensajeErrorNumeroText(mensaje);
                view.habilitarButtonGuardar();
                view.ocultarDialogProgress();
            }
            return;
        }
       /* if (numeroCuentainte.isEmpty() || numeroCuentainte.equals(" ") || numeroCuentainte == null) {
            String mensaje = "Escriba Correctamente ";
            if (view != null) {
                view.mostrarMensajeErrorNumeroInterbakText(mensaje);
                view.habilitarButtonGuardar();
                view.ocultarDialogProgress();
            }
            return;
        }*/
        if (datosBancaria != null) {
            initUseCaseGuardarDataRetrofit(tipoBancoUi.getId(), numeroCuenta, tipoEstadStringTipoCuenta, numeroCuentainte,"actualiza");
        }else{
            initUseCaseGuardarDataRetrofit(tipoBancoUi.getId(), numeroCuenta, tipoEstadStringTipoCuenta, numeroCuentainte,"inserta");
        }

    }

    @Override
    public void onCloseBank() {
        this.tipoBancoUi = null;
        if (view != null) view.limpiarTextBank();
    }

    private void initUseCaseGuardarDataRetrofit(String idBanco, String numeroCuenta,
                                                String tipoEstadStringTipoCuenta, String numeroCuentainte,String tipoInsertacion) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = loginService.guardarBancoEspec(keyUser, codigoPais,
                numeroCuenta, tipoEstadStringTipoCuenta, numeroCuentainte, idBanco);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    if (view != null) {
                        String mensaje = "Error con nuestros servidores";
                        view.mostrarMensaje(mensaje);
                        view.habilitarButtonGuardar();
                        view.ocultarDialogProgress();
                    }
                    return;
                } else {
                    if (defaultResponse.getError()) {
                        if (view != null) {
                            view.mostrarMensaje(defaultResponse.getMessage());
                            view.habilitarButtonGuardar();
                            view.ocultarDialogProgress();
                        }
                        return;
                    } else {
                        if (view != null) {
                            view.initStartEditActivityPerfil(EXTRA_CORRECTAMENTE_ESPEC_BANCO);
                            view.habilitarButtonGuardar();
                            view.ocultarDialogProgress();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });


    }

    List<String> stringListTipoCuenta;

    @Override
    public void onStart() {
        super.onStart();
        initSpinnerAdapterTipoCuenta(stringListTipoCuenta);
        mostrarDataLlena();

    }

    private void initSpinnerAdapterTipoCuenta(List<String> stringListTipoCuenta) {
        if (view != null) view.mostrarListaTipoCuenta(stringListTipoCuenta);
    }

    List<TipoBancoUi> tipoBancoUiList;

    private void initUseCaseTipoBancoLista(String paisCodigoPeru) {
        Log.d(TAG,"initUseCaseTipoBancoLista");
        handler.execute(mostrarListaBanco, new MostrarListaBanco.RequestValues(paisCodigoPeru),
                new UseCase.UseCaseCallback<MostrarListaBanco.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarListaBanco.ResponseValue response) {
                        // List<TipoBancoUi> tipoBancoUiList = response.getTipoBancoUiList();
                        if (response.getTipoBancoUiList() == null) return;
                        tipoBancoUiList = response.getTipoBancoUiList();
                        if (view != null) view.mostrarListaTipoBancio(tipoBancoUiList);
                        if (datosBancaria != null) {
                            validarTipoBancoLleno();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });


    }


}
