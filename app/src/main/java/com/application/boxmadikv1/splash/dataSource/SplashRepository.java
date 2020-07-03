package com.application.boxmadikv1.splash.dataSource;

import android.util.Log;

import com.application.boxmadikv1.api.response.DatosInicialResponse;
import com.application.boxmadikv1.splash.asyntask.DatosInicialesAsyntask;
import com.application.boxmadikv1.splash.dataSource.local.SplashLocal;
import com.application.boxmadikv1.splash.dataSource.remote.SplashRemote;

public class SplashRepository implements SplashDataSource {

    public static final String TAG = SplashRepository.class.getSimpleName();

    private SplashRemote splashRemote;
    private SplashLocal splashLocal;

    private static SplashRepository mInstance = null;

    public static SplashRepository getmInstance(SplashRemote splashRemote, SplashLocal splashLocal) {
        if (mInstance == null) {
            mInstance = new SplashRepository(splashRemote, splashLocal);
        }
        return mInstance;
    }

    public SplashRepository(SplashRemote splashRemote, SplashLocal splashLocal) {
        this.splashRemote = splashRemote;
        this.splashLocal = splashLocal;
    }

    @Override
    public void onImportartDatosIniciales(final CallBackResultado<DatosInicialResponse, String> booleanCallBackResultado) {
        splashRemote.onImportartDatosIniciales(new CallBackResultado<DatosInicialResponse, String>() {
            @Override
            public void onCallBackResultado(final DatosInicialResponse resultado, final String resultadoString) {
                if (resultado == null) {
                    booleanCallBackResultado.onCallBackResultado(resultado, "IMPORT_DATA_ERROR_RED");
                    return;
                }
                if (!resultado.isError()) {
                    new DatosInicialesAsyntask(new DatosInicialesAsyntask.Callback<DatosInicialesAsyntask.DatosInicialesObject>() {
                        @Override
                        public void onFinish(DatosInicialesAsyntask.DatosInicialesObject object) {
                            switch (object.getState()) {
                                case DatosInicialesAsyntask.IMPORT_DATA_CORRECTO:
                                    booleanCallBackResultado.onCallBackResultado(resultado, "IMPORT_DATA_CORRECTO");
                                    Log.d(TAG, "IMPORT_DATA_CORRECTO");
                                    break;
                                case DatosInicialesAsyntask.IMPORT_DATA_ERROR_RED:
                                    Log.d(TAG, "IMPORT_DATA_ERROR_RED");
                                    booleanCallBackResultado.onCallBackResultado(resultado, "IMPORT_DATA_ERROR_RED");
                                    break;
                                default:
                                    booleanCallBackResultado.onCallBackResultado(resultado, "IMPORT_DATA_ERROR_RED");
                                    break;
                            }
                        }
                    }).execute(resultado);
                }
            }
        });
    }
}
