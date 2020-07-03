package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource.EspecialistaPerfilRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.EspecialistaPerfilDistritoDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.EspecialistaPerfilDistritoRepository;

import java.util.List;

public class ListaProvincia extends UseCase<ListaProvincia.RequestValues, ListaProvincia.ResponseValue> {

    private EspecialistaPerfilDistritoRepository especialistaPerfilRepository;

    public ListaProvincia(EspecialistaPerfilDistritoRepository especialistaPerfilRepository) {
        this.especialistaPerfilRepository = especialistaPerfilRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        especialistaPerfilRepository.onListarProvincia(requestValues.getPaisCodigo(), requestValues.getDepartamentoCodigo(), new EspecialistaPerfilDistritoDataSource.CallBackResultado<List<TipoProvinciaUi>>() {
            @Override
            public void onCallBackResultado(List<TipoProvinciaUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String paisCodigo;
        private String departamentoCodigo;

        public RequestValues(String paisCodigo, String departamentoCodigo) {
            this.paisCodigo = paisCodigo;
            this.departamentoCodigo = departamentoCodigo;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }

        public String getDepartamentoCodigo() {
            return departamentoCodigo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoProvinciaUi> tipoProvinciaUis;

        public ResponseValue(List<TipoProvinciaUi> tipoProvinciaUis) {
            this.tipoProvinciaUis = tipoProvinciaUis;
        }

        public List<TipoProvinciaUi> getTipoProvinciaUis() {
            return tipoProvinciaUis;
        }
    }
}
