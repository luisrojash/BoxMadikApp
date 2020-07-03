package com.application.boxmadikv1.splash.useCase;

import com.application.boxmadikv1.api.response.DatosInicialResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.splash.dataSource.SplashDataSource;
import com.application.boxmadikv1.splash.dataSource.SplashRepository;

public class ImportarDatosIniciales extends UseCase<ImportarDatosIniciales.RequestValues, ImportarDatosIniciales.ResponseValue> {

    SplashRepository splashRepository;

    public ImportarDatosIniciales(SplashRepository splashRepository) {
        this.splashRepository = splashRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        splashRepository.onImportartDatosIniciales(new SplashDataSource.CallBackResultado<DatosInicialResponse, String>() {
            @Override
            public void onCallBackResultado(DatosInicialResponse resultado, String resultadoString) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultadoString));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        String resultadoString;
        public ResponseValue(String resultadoString) {
            this.resultadoString = resultadoString;
        }
        public String getResultadoString() {
            return resultadoString;
        }
    }
}
