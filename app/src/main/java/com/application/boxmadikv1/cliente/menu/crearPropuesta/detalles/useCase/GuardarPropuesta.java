package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesRepository;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class GuardarPropuesta extends UseCase<GuardarPropuesta.RequestValues, GuardarPropuesta.ResponseValue> {

    private DetallesRepository detallesRepository;

    public GuardarPropuesta(DetallesRepository detallesRepository) {
        this.detallesRepository = detallesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        detallesRepository.onRegistrarPropuesta(requestValues.getBodyTitulo(),
                requestValues.getBodyDetalles(), requestValues.getBodyPaisCodigo(),
                requestValues.getBodyRangPrecioId(), requestValues.getBodyRangDiasId(),
                requestValues.getBodyRangTurnoId(), requestValues.getBodyRubroId(),
                requestValues.getBodyOficioId(), requestValues.getListaIdEspecialistas(),
                requestValues.getRequestFile1(), requestValues.getRequestFile2(),
                requestValues.getRequesKeyUser(),requestValues.getBodyCodigoDepartamento(),requestValues.getBodyCodigoProvincia(),
                requestValues.getBodyCodigoDistrito(),new DetallesDataSource.CallbackResultado<DefaultResponseRegistro>() {
                    @Override
                    public void onCallbackResultado(DefaultResponseRegistro resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        RequestBody bodyTitulo;
        RequestBody bodyDetalles;
        RequestBody bodyPaisCodigo;
        RequestBody bodyRangPrecioId;
        RequestBody bodyRangDiasId;
        RequestBody bodyRangTurnoId;
        RequestBody bodyRubroId;
        RequestBody bodyOficioId;
        ArrayList<String> listaIdEspecialistas;
        RequestBody requestFile1;
        RequestBody requestFile2;
        RequestBody requesKeyUser;
        RequestBody bodyCodigoDepartamento;
        RequestBody bodyCodigoProvincia;
        RequestBody bodyCodigoDistrito;

        public RequestValues(RequestBody bodyTitulo, RequestBody bodyDetalles,
                             RequestBody bodyPaisCodigo, RequestBody bodyRangPrecioId,
                             RequestBody bodyRangDiasId, RequestBody bodyRangTurnoId,
                             RequestBody bodyRubroId, RequestBody bodyOficioId,
                             ArrayList<String> listaIdEspecialistas, RequestBody requestFile1,
                             RequestBody requestFile2, RequestBody requesKeyUser,
                             RequestBody bodyCodigoDepartamento,
                             RequestBody bodyCodigoProvincia,
                             RequestBody bodyCodigoDistrito) {
            this.bodyTitulo = bodyTitulo;
            this.bodyDetalles = bodyDetalles;
            this.bodyPaisCodigo = bodyPaisCodigo;
            this.bodyRangPrecioId = bodyRangPrecioId;
            this.bodyRangDiasId = bodyRangDiasId;
            this.bodyRangTurnoId = bodyRangTurnoId;
            this.bodyRubroId = bodyRubroId;
            this.bodyOficioId = bodyOficioId;
            this.listaIdEspecialistas = listaIdEspecialistas;
            this.requestFile1 = requestFile1;
            this.requestFile2 = requestFile2;
            this.requesKeyUser = requesKeyUser;
            this.bodyCodigoDepartamento = bodyCodigoDepartamento;
            this.bodyCodigoProvincia = bodyCodigoProvincia;
            this.bodyCodigoDistrito = bodyCodigoDistrito;
        }

        public RequestBody getBodyTitulo() {
            return bodyTitulo;
        }

        public void setBodyTitulo(RequestBody bodyTitulo) {
            this.bodyTitulo = bodyTitulo;
        }

        public RequestBody getBodyDetalles() {
            return bodyDetalles;
        }

        public void setBodyDetalles(RequestBody bodyDetalles) {
            this.bodyDetalles = bodyDetalles;
        }

        public RequestBody getBodyPaisCodigo() {
            return bodyPaisCodigo;
        }

        public void setBodyPaisCodigo(RequestBody bodyPaisCodigo) {
            this.bodyPaisCodigo = bodyPaisCodigo;
        }

        public RequestBody getBodyRangPrecioId() {
            return bodyRangPrecioId;
        }

        public void setBodyRangPrecioId(RequestBody bodyRangPrecioId) {
            this.bodyRangPrecioId = bodyRangPrecioId;
        }

        public RequestBody getBodyRangDiasId() {
            return bodyRangDiasId;
        }

        public void setBodyRangDiasId(RequestBody bodyRangDiasId) {
            this.bodyRangDiasId = bodyRangDiasId;
        }

        public RequestBody getBodyRangTurnoId() {
            return bodyRangTurnoId;
        }

        public void setBodyRangTurnoId(RequestBody bodyRangTurnoId) {
            this.bodyRangTurnoId = bodyRangTurnoId;
        }

        public RequestBody getBodyRubroId() {
            return bodyRubroId;
        }

        public void setBodyRubroId(RequestBody bodyRubroId) {
            this.bodyRubroId = bodyRubroId;
        }

        public RequestBody getBodyOficioId() {
            return bodyOficioId;
        }

        public void setBodyOficioId(RequestBody bodyOficioId) {
            this.bodyOficioId = bodyOficioId;
        }

        public ArrayList<String> getListaIdEspecialistas() {
            return listaIdEspecialistas;
        }

        public void setListaIdEspecialistas(ArrayList<String> listaIdEspecialistas) {
            this.listaIdEspecialistas = listaIdEspecialistas;
        }

        public RequestBody getRequestFile1() {
            return requestFile1;
        }

        public void setRequestFile1(RequestBody requestFile1) {
            this.requestFile1 = requestFile1;
        }

        public RequestBody getRequestFile2() {
            return requestFile2;
        }

        public void setRequestFile2(RequestBody requestFile2) {
            this.requestFile2 = requestFile2;
        }

        public RequestBody getRequesKeyUser() {
            return requesKeyUser;
        }

        public void setRequesKeyUser(RequestBody requesKeyUser) {
            this.requesKeyUser = requesKeyUser;
        }

        public RequestBody getBodyCodigoDepartamento() {
            return bodyCodigoDepartamento;
        }

        public RequestBody getBodyCodigoProvincia() {
            return bodyCodigoProvincia;
        }

        public RequestBody getBodyCodigoDistrito() {
            return bodyCodigoDistrito;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        DefaultResponseRegistro defaultResponse;

        public ResponseValue(DefaultResponseRegistro defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public DefaultResponseRegistro getDefaultResponse() {
            return defaultResponse;
        }
    }
}
