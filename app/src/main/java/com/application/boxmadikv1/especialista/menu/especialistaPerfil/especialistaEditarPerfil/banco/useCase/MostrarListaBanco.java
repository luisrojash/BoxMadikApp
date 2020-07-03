package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.EspecBancoDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.EspecBancoRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.entidad.TipoBancoUi;

import java.util.List;

public class MostrarListaBanco extends UseCase<MostrarListaBanco.RequestValues, MostrarListaBanco.ResponseValue> {

    private EspecBancoRepository especBancoRepository;

    public MostrarListaBanco(EspecBancoRepository especBancoRepository) {
        this.especBancoRepository = especBancoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        especBancoRepository.onMostrarListaBanco(requestValues.getCodigoPais(), new EspecBancoDataSource.CallBackResultado<List<TipoBancoUi>>() {
            @Override
            public void onCallBackResultado(List<TipoBancoUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigoPais;

        public RequestValues(String codigoPais) {
            this.codigoPais = codigoPais;
        }

        public String getCodigoPais() {
            return codigoPais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoBancoUi> tipoBancoUiList;

        public ResponseValue(List<TipoBancoUi> tipoBancoUiList) {
            this.tipoBancoUiList = tipoBancoUiList;
        }

        public List<TipoBancoUi> getTipoBancoUiList() {
            return tipoBancoUiList;
        }

        public void setTipoBancoUiList(List<TipoBancoUi> tipoBancoUiList) {
            this.tipoBancoUiList = tipoBancoUiList;
        }
    }
}
