package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog;

import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.useCase.ListaDepartamento;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.useCase.ListaDistrito;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.useCase.ListaProvincia;

public class UbicacionPresenterImpl implements UbicacionPresenter {
    public static final String TAG = UbicacionPresenterImpl.class.getSimpleName();

    private UseCaseHandler handler;
    private UbicacionView view;
    private ListaDepartamento listaDepartamento;
    private ListaProvincia listaProvincia;
    private ListaDistrito listaDistrito;

    public UbicacionPresenterImpl(UseCaseHandler handler, ListaDepartamento listaDepartamento, ListaProvincia listaProvincia, ListaDistrito listaDistrito) {
        this.handler = handler;
        this.listaDepartamento = listaDepartamento;
        this.listaProvincia = listaProvincia;
        this.listaDistrito = listaDistrito;
    }

    @Override
    public void attachView(UbicacionView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {
        if (view != null) {
            view.deshabilitarAutoCompleteTextViewDistrito();
            view.deshabilitarAutoCompleteTextViewProvincia();
        }
        initUseCaseTipoDepartamento(codigoPais);

    }

    private void initUseCaseTipoDepartamento(String codigoPais) {
        handler.execute(listaDepartamento, new ListaDepartamento.RequestValues(codigoPais),
                new UseCase.UseCaseCallback<ListaDepartamento.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaDepartamento.ResponseValue response) {
                        Log.d(TAG, "Lista Departamentos : " + response.getTipoDepartamentoUis().size());
                        if (response.getTipoDepartamentoUis() == null) return;

                        if (view != null)
                            view.mostrarListaTipoDepartamento(response.getTipoDepartamentoUis());
                        //initTipoDepartmentoLleno(datosEspecEditDireccion);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    String codigoPais;

    @Override
    public void onExtras(Bundle bundle) {
        if (bundle == null) return;
        this.codigoPais = bundle.getString("codigoPais");
        Log.d(TAG, "onExtras" + codigoPais);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    TipoDepartamentoUi tipoDepartamentoUi;

    @Override
    public void onSpinnerTipoDepartamento(TipoDepartamentoUi tipoDepartamentoUi) {
        this.tipoDepartamentoUi = tipoDepartamentoUi;
        initUseCaseTipoProvincia(codigoPais, tipoDepartamentoUi.getId());
        if (view != null) {
            view.mostrarTextViewDepartamento("Dpto: " + tipoDepartamentoUi.getDescripcion());
            view.habilitarAutoCompleteTextViewProvincia();
        }
    }

    TipoProvinciaUi tipoProvinciaUi;

    @Override
    public void onSpinnerTipoProvincia(TipoProvinciaUi tipoProvinciaUi) {
        this.tipoProvinciaUi = tipoProvinciaUi;
        initUseCaseTipoDistrito(codigoPais, tipoDepartamentoUi.getId(), tipoProvinciaUi.getId());

        if (view != null) {
            view.mostrarTextViewProvincia("Prov: " + tipoProvinciaUi.getDescripcion());
            view.habilitarAutoCompleteTextViewDistrito();
            //view.mostrarEditextDireccion(tipoProvinciaUi.getDescripcion() + " " + tipoDepartamentoUi.getDescripcion());
        }
    }

    TipoDistritoUi tipoDistritoUi;

    @Override
    public void onSpinnerTipoDistrito(TipoDistritoUi tipoDistritoUi) {
        this.tipoDistritoUi = tipoDistritoUi;
        if (view != null) {
            view.mostrarTextViewDistrito("Dtto: " + tipoDistritoUi.getDescripcion());
        }
    }

    @Override
    public void onClickCloseDepartamento() {
        this.tipoDepartamentoUi = null;
        this.tipoProvinciaUi = null;
        this.tipoDistritoUi = null;
        if (view != null) {
            view.borrarTextDepartamento();
            view.borrarTextProvincia();
            view.borrarTextDistrito();
            view.deshabilitarAutoCompleteTextViewDistrito();
            view.deshabilitarAutoCompleteTextViewProvincia();
        }
    }

    @Override
    public void onClickCloseProvincia() {
        this.tipoProvinciaUi = null;
        this.tipoDistritoUi = null;
        if (view != null){
            view.borrarTextProvincia();
            view.borrarTextDistrito();
            view.deshabilitarAutoCompleteTextViewDistrito();
        }
    }

    @Override
    public void onClickCloseDistrito() {
        this.tipoDistritoUi = null;
        if (view != null) {
            view.borrarTextDistrito();

        }
    }

    @Override
    public void onGuardarUbicacion() {
        if (tipoDepartamentoUi == null) {
            Log.d(TAG, "Necesitas Esribir un tipo DireccionUi");
            String mensaje = "Ingrese Departamento";
            if (view != null) view.mostrarMensajeErrorAutoCompleteDepartamentoError(mensaje);
            return;
        }
        if (tipoProvinciaUi == null) {
            Log.d(TAG, "Necesitas Esribir un tipo Provincia");
            String mensaje = "Ingrese Provincia";
            if (view != null) view.mostrarMensajeErrorAutoCompleteProvinciaError(mensaje);
            return;
        }
        if (tipoDistritoUi == null) {
            String mensaje = "Ingrese Distrito";
            if (view != null) view.mostrarMensajeErrorAutoCompleteDistritoError(mensaje);
            return;
        }
        String codigoDepartamento = tipoDepartamentoUi.getId();
        String nombreDepartamento = tipoDepartamentoUi.getDescripcion();
        String codigoProvincia = tipoProvinciaUi.getId();
        String nombreProvincia = tipoProvinciaUi.getDescripcion();
        String codigoDistrito = tipoDistritoUi.getId();
        String nombreDistrito = tipoDistritoUi.getDescripcion();
        if (view != null)
            view.enviarDataPadreActivity(codigoDepartamento, nombreDepartamento, codigoProvincia, nombreProvincia, codigoDistrito, nombreDistrito);
    }

    private void initUseCaseTipoDistrito(String codigoPais, String departamentoCodigo, String provinciaCodigo) {

        handler.execute(listaDistrito, new ListaDistrito.RequestValues(codigoPais, departamentoCodigo, provinciaCodigo),
                new UseCase.UseCaseCallback<ListaDistrito.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaDistrito.ResponseValue response) {

                        if (response.getTipoDistritoUis() == null) return;
                        if (view != null)
                            view.mostrarListaTipoDistrito(response.getTipoDistritoUis());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    private void initUseCaseTipoProvincia(String codigoPais, String departamentoCodigo) {

        handler.execute(listaProvincia, new ListaProvincia.RequestValues(codigoPais, departamentoCodigo),
                new UseCase.UseCaseCallback<ListaProvincia.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaProvincia.ResponseValue response) {
                        if (response.getTipoProvinciaUis() == null) return;
                        if (view != null)
                            view.mostrarListaTipoProvincia(response.getTipoProvinciaUis());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
