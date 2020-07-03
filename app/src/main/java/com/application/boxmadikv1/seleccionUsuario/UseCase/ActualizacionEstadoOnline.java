package com.application.boxmadikv1.seleccionUsuario.UseCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.login.useCase.GuardarUsuariosOnline;
import com.application.boxmadikv1.seleccionUsuario.dataSource.SeleccionUserDataSource;
import com.application.boxmadikv1.seleccionUsuario.dataSource.SeleccionUserRepository;

public class ActualizacionEstadoOnline extends GuardarUsuariosOnline {

    private SeleccionUserRepository seleccionUserRepository;

    public ActualizacionEstadoOnline(SeleccionUserRepository seleccionUserRepository) {
        this.seleccionUserRepository = seleccionUserRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        seleccionUserRepository.onActualizarEstadoUsuarioDesconectado(requestValues.getUsuarioCodigo(), requestValues.getEstado(), new SeleccionUserDataSource.CallBackResultado<DefaultResponse>() {
            @Override
            public void onCallBackResultado(DefaultResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }
}
