package com.application.boxmadikv1.bandeja;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.bandeja.entidad.BandejaUi;
import com.application.boxmadikv1.bandeja.useCase.MostrarListaGrupo;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class BandejaPresenterImpl extends BaseActivityPresenterImpl<BandejaView> implements BandejaPresenter {

    public static final String TAG = BandejaPresenterImpl.class.getSimpleName();
    private MostrarListaGrupo listaGrupo;
    private List<BandejaUi> bandejaUiList;

    public BandejaPresenterImpl(UseCaseHandler handler, Resources res, MostrarListaGrupo listaGrupo) {
        super(handler, res);
        this.listaGrupo = listaGrupo;
        bandejaUiList = new ArrayList<>();
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    String userKey, paisCodigo;

    @Override
    public void onPreferencesData(String userKey, String paisCodigo) {
        this.userKey = userKey;
        this.paisCodigo = paisCodigo;
    }

    @Override
    public void onClickBandeja(BandejaUi bandejaUi) {
        switch (tipoUsuario) {
            case "cliente":
                if (view != null) view.initStartActivityBandejaCliente(bandejaUi);
                break;
            case "especialista":
                validarTipoEstadoCotizacion(bandejaUi);
                // if (view != null) view.initStartActivityBandejaEspecialista(bandejaUi);
                break;
        }
    }

    private void validarTipoEstadoCotizacion(BandejaUi bandejaUi) {
        if (bandejaUi.getCotiEstado() == null) {
            if (view != null) view.initStartActivityBandejaEspecialista(bandejaUi);
            return;
        }
        switch (bandejaUi.getCotiEstado()) {
            case Constantes.ESTADO_ESPECIALISTA_CANCELADOS:
                if (view != null) view.mostrarMensaje("Usted aún no puede conversar");
                // if (view != null) view.initStartActivityBandejaEspecialista(bandejaUi);
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                if (view != null) view.mostrarMensaje("Usted aún no puede conversar");
                // if (view != null) view.initStartActivityBandejaEspecialista(bandejaUi);
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                if (view != null) view.initStartActivityBandejaEspecialista(bandejaUi);
                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                if (view != null) view.mostrarMensaje("Usted aún no puede conversar");
                break;
            case Constantes.ESTADO_ESPECIALISTA_PAGADO:
                if (view != null) view.initStartActivityBandejaEspecialista(bandejaUi);
                break;
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                if (view != null) view.initStartActivityBandejaEspecialista(bandejaUi);
                break;
            case Constantes.ESTADO_ESPECIALISTA_FINALIZADO:
                if (view != null) view.mostrarMensaje("Usted aún no puede conversar");
                break;
        }
    }


    @Override
    public void onDataRealTime(String mensaje, String codigoGrupoChat, String timeStamp) {
        int conteMensajeC;
        for (BandejaUi bandejaUi : bandejaUiList) {
            if (bandejaUi.getIdGrupoChat().equals(codigoGrupoChat)) {
                conteMensajeC = bandejaUi.getConteoMensaje() + 1;
                bandejaUi.setMensaje(mensaje);
                bandejaUi.setConteoMensaje(conteMensajeC);
                bandejaUi.setEstadoGrupoLeido("1");
                if (view != null) view.actualizarVistasBandeja(bandejaUi);
                break;
            }

        }

    }


    private void initUseCaseListaGrupo(String userKey, String paisCodigo, String tipoUsuario) {
        if (view != null) view.mostrarProgressBar();
        if (bandejaUiList != null) {
            bandejaUiList.clear();
        }
        handler.execute(listaGrupo, new MostrarListaGrupo.RequestValues(userKey, paisCodigo, tipoUsuario),
                new UseCase.UseCaseCallback<MostrarListaGrupo.ResponseValue>() {
                    @Override
                    public void onSuccess(MostrarListaGrupo.ResponseValue response) {
                        if (view != null) {
                            bandejaUiList = response.getBandejaUiList();
                            view.mostrarListaGrupo(response.getBandejaUiList());
                            view.ocultarProgressBar();
                            view.ocultartTextViewEmpty();
                        }
                    }

                    @Override
                    public void onError() {
                        if (view != null) {
                            view.mostrarTextViewEmpty();
                            view.ocultarProgressBar();
                        }
                        Log.d(TAG, "onError: Esta llegando Nulo la lista ");

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        initValidateTipoUsuario();
        //initUseCaseListaGrupo(userKey, paisCodigo);
    }

    private void initValidateTipoUsuario() {
        switch (tipoUsuario) {
            case "cliente":
                Log.d(TAG, "cliente" + tipoUsuario);
                initUseCaseListaGrupo(userKey, paisCodigo, tipoUsuario);
                break;
            case "especialista":
                Log.d(TAG, "cliente" + tipoUsuario);
                initUseCaseListaGrupo(userKey, paisCodigo, tipoUsuario);
                break;
        }
    }

    String tipoUsuario;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.tipoUsuario = extras.getString("tipoUsuario");
    }
}
