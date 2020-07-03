package com.application.boxmadikv1.bandeja.useCase;

import com.application.boxmadikv1.bandeja.dataSource.BandejaDataSource;
import com.application.boxmadikv1.bandeja.dataSource.BandejaRepository;
import com.application.boxmadikv1.bandeja.entidad.BandejaUi;
import com.application.boxmadikv1.base.UseCase;

import java.util.List;

public class MostrarListaGrupo extends UseCase<MostrarListaGrupo.RequestValues, MostrarListaGrupo.ResponseValue> {

    private BandejaRepository repository;

    public MostrarListaGrupo(BandejaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onMostrarListaGrupoChat(requestValues.getUsuarioCodigo(), requestValues.getPaisCodigo(), requestValues.getTipoUsuario(), new BandejaDataSource.CallBackResultado<List<BandejaUi>>() {
            @Override
            public void onCallBackResultado(List<BandejaUi> resultado) {
                if (resultado != null) {
                    getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                } else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String usuarioCodigo;
        private String paisCodigo;
        private String tipoUsuario;

        public RequestValues(String usuarioCodigo, String paisCodigo, String tipoUsuario) {
            this.usuarioCodigo = usuarioCodigo;
            this.paisCodigo = paisCodigo;
            this.tipoUsuario = tipoUsuario;
        }

        public String getUsuarioCodigo() {
            return usuarioCodigo;
        }

        public void setUsuarioCodigo(String usuarioCodigo) {
            this.usuarioCodigo = usuarioCodigo;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }

        public void setPaisCodigo(String paisCodigo) {
            this.paisCodigo = paisCodigo;
        }

        public String getTipoUsuario() {
            return tipoUsuario;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<BandejaUi> bandejaUiList;

        public ResponseValue(List<BandejaUi> bandejaUiList) {
            this.bandejaUiList = bandejaUiList;
        }

        public List<BandejaUi> getBandejaUiList() {
            return bandejaUiList;
        }

        public void setBandejaUiList(List<BandejaUi> bandejaUiList) {
            this.bandejaUiList = bandejaUiList;
        }
    }
}
