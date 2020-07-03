package com.application.boxmadikv1.splash;

import android.content.res.Resources;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.splash.useCase.ImportarDatosIniciales;

public class SplashPresenterImpl extends BaseActivityPresenterImpl<SplashView> implements SplashPresenter {

    public static final String TAG = SplashPresenterImpl.class.getSimpleName();
    ImportarDatosIniciales importarDatosIniciales;

    public SplashPresenterImpl(UseCaseHandler handler, Resources res, ImportarDatosIniciales importarDatosIniciales) {
        super(handler, res);
        this.importarDatosIniciales = importarDatosIniciales;
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

    private void validarDatosIniciales() {
        if (estadoInternet) {
        handler.execute(importarDatosIniciales, new ImportarDatosIniciales.RequestValues(),
                new UseCase.UseCaseCallback<ImportarDatosIniciales.ResponseValue>() {
                    @Override
                    public void onSuccess(ImportarDatosIniciales.ResponseValue response) {
                        if (response.getResultadoString().equals("IMPORT_DATA_CORRECTO")) {
                            if (view != null) view.startSeleccionarUserActivity();
                            return;
                        } else if (response.getResultadoString().equals("IMPORT_DATA_CORRECTO")) {
                            if (view != null)
                                view.mostrarDialogMensaje("No se pudo conectar a Internet. Desea Intentar denuevo?");
                            return;
                        } else {
                            if (view != null) view.mostrarProgressBar();
                            return;
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
        } else {
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Desea Intentar denuevo?");
        }

    }

    Boolean estadoInternet;

    @Override
    public void onCheckConexion(Boolean estadoInternet) {
        if (estadoInternet) {
            this.estadoInternet = estadoInternet;
            Log.d(TAG, "CONECTADO A INTERNET");
            validarDatosIniciales();
        } else {
            this.estadoInternet = estadoInternet;
            Log.d(TAG, "DESCONECTADO A INTERNET");
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Desea Intentar denuevo?");
        }
        // validarDatosIniciales();
    }

    @Override
    public void onClickRenuevaDatosGenerales(Boolean estadoInternet) {
        this.estadoInternet = estadoInternet;
        Log.d(TAG, "onClickRenuevaDatosGenerales : " + estadoInternet);
        validarDatosIniciales();
    }

}
