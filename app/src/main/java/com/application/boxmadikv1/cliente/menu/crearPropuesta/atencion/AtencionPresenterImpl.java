package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase.ListarTipoDias;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase.ListarTipoPrecio;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase.ListarTipoTurno;

import java.util.ArrayList;

public class AtencionPresenterImpl extends BaseActivityPresenterImpl<AtencionView> implements AtencionPresenter {


    public static final String TAG = AtencionPresenterImpl.class.getSimpleName();

    ListarTipoDias listarTipoDias;
    ListarTipoPrecio listarTipoPrecio;
    ListarTipoTurno listarTipoTurno;


    public AtencionPresenterImpl(UseCaseHandler handler, Resources res, ListarTipoDias listarTipoDias, ListarTipoPrecio listarTipoPrecio, ListarTipoTurno listarTipoTurno) {
        super(handler, res);
        this.listarTipoDias = listarTipoDias;
        this.listarTipoPrecio = listarTipoPrecio;
        this.listarTipoTurno = listarTipoTurno;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    int rubroId;
    int oficioId;
    String imageRubro = "";
    String nombreOficio = "";
    String codigoPais = "";
    ArrayList<String> listaIdEspecialistas;
    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.rubroId = extras.getInt("rubroId", 0);
        this.oficioId = extras.getInt("oficioId", 0);
        this.imageRubro = extras.getString("imageRubro");
        this.nombreOficio = extras.getString("nombreOficio");
        this.listaIdEspecialistas = extras.getStringArrayList("mylist");
        this.posicionTipoPrecio = extras.getInt("posicionTipoPrecio");
        this.posicionTipoTurno = extras.getInt("posicionTipoTurno");
        this.posicionTipoDias = extras.getInt("posicionTipoDias");
        this.codigoPais =extras.getString("codigoPais");
        Log.d(TAG, "posicionTipoPrecio " + posicionTipoPrecio + "" +
                "posicionTipoTurno " + posicionTipoTurno + "" +
                "posicionTipoDias " + posicionTipoDias + "");
        if (listaIdEspecialistas == null) {
            return;
        } else {
            for (String mylista : listaIdEspecialistas) {
                Log.d(TAG, "  / mylista :" + mylista);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mostrarCabecera(imageRubro, nombreOficio);
        initUseCaseListaPrecio(codigoPais);
        initUseCaseListaDias();
        initUseCaseListaTurno();
    }

    private void mostrarCabecera(String imageRubro, String nombreOficio) {
        if (view != null) view.mostrarCabecera(imageRubro, nombreOficio);
    }

    private void initUseCaseListaPrecio(String codigoPais) {
        handler.execute(listarTipoPrecio, new ListarTipoPrecio.RequestValues(codigoPais),
                new UseCase.UseCaseCallback<ListarTipoPrecio.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarTipoPrecio.ResponseValue response) {
                        if (response.getTipoRangoPrecioUiList() == null) return;
                        if (view != null)
                            view.mostrarListaTipoPrecio(response.getTipoRangoPrecioUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
        Log.d(TAG, "onBackposicionTipoPrecio : " + onBackposicionTipoPrecio);
        //if (view != null) view.mostrarSpinnerSeleccionTipoPrecio(onBackposicionTipoPrecio);
    }

    private void initUseCaseListaDias() {
        handler.execute(listarTipoDias, new ListarTipoDias.RequestValues(),
                new UseCase.UseCaseCallback<ListarTipoDias.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarTipoDias.ResponseValue response) {
                        if (response.getTipoRangoDiasUis() == null) return;
                        if (view != null)
                            view.mostrarListaTipoDias(response.getTipoRangoDiasUis());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseCaseListaTurno() {
        handler.execute(listarTipoTurno, new ListarTipoTurno.RequestValues(),
                new UseCase.UseCaseCallback<ListarTipoTurno.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarTipoTurno.ResponseValue response) {
                        if (response.getTipoRangoTurnoUiList() == null) return;
                        if (view != null)
                            view.mostrarListaTipoTurno(response.getTipoRangoTurnoUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    String tipoPrecio = "";
    String tipoTurno = "";
    String tipoDias = "";
    int posicionTipoPrecio, posicionTipoTurno, posicionTipoDias;

    @Override
    public void onSpinnerTipoPrecio(String idPrecio, int posicionTipoPrecio) {
        this.tipoPrecio = idPrecio;
        this.posicionTipoPrecio = posicionTipoPrecio;
    }

    @Override
    public void onSpinnerTipoTurno(String idTurno, int posicionTipoTurno) {
        this.tipoTurno = idTurno;
        this.posicionTipoTurno = posicionTipoTurno;
    }

    @Override
    public void onSpinnerTipoDias(String idDias, int posicionTipoDias) {
        this.tipoDias = idDias;
        this.posicionTipoDias = posicionTipoDias;
    }

    @Override
    public void onClickSiguiente() {
        if (tipoTurno.equals("0") && tipoPrecio.equals("0") && tipoDias.equals("0")) {
            if (view != null) view.mostrarMensaje("Ingrese Presupuesto");
            return;
        } else if (tipoPrecio.equals("0")) {
            if (view != null) view.mostrarMensaje("Seleccionar Precio");
            return;
        } else if (tipoDias.equals("0")) {
            if (view != null) view.mostrarMensaje("Indique cuando atendemos su servicio");
            return;
        } else if (tipoTurno.equals("0")) {
            if (view != null) view.mostrarMensaje("Indique el turno de su preferencia");
            return;
        } else {
            if (view != null)
                view.startActivityDetalles(rubroId, oficioId, tipoTurno, tipoPrecio, tipoDias, imageRubro, nombreOficio,
                        posicionTipoPrecio,
                        posicionTipoTurno,
                        posicionTipoDias,
                        listaIdEspecialistas);
        }
    }

    @Override
    public void validarTipoPrecio() {
        Log.d(TAG, "validarTipoPrecio" + onBackposicionTipoPrecio);

        if (view != null) view.mostrarSpinnerSeleccionTipoPrecio(onBackposicionTipoPrecio);
    }

    @Override
    public void validarTipoDias() {
        Log.d(TAG, "validarTipoDias");
        if (view != null) view.mostrarSpinnerSeleccionTipoDias(onBackposicionTipoDias);
    }

    @Override
    public void validarTipoTurno() {
        Log.d(TAG, "validarTipoTurno");
        if (view != null) view.mostrarSpinnerSeleccionTipoturno(onBackposicionTipoTurno);
    }

    int onBackposicionTipoPrecio, onBackposicionTipoTurno, onBackposicionTipoDias;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "requestCode " + requestCode +
                " / resultCode " + resultCode);
        if (requestCode == AtencionActivity.RESULTADO_ACTIVIDAD_ACTIVIDAD_ATENCION) {
            if (resultCode == Activity.RESULT_OK) {
                this.onBackposicionTipoPrecio = data.getIntExtra("posicionTipoPrecio", 0);
                this.onBackposicionTipoTurno = data.getIntExtra("posicionTipoTurno", 0);
                this.onBackposicionTipoDias = data.getIntExtra("posicionTipoDias", 0);
                //if (view != null) view.mostrarContenidoFiltro(descripcion, imageRubro);
                /*validarTipoPrecio(onBackposicionTipoPrecio);
                validarTipoDias(onBackposicionTipoTurno);
                validarTipoTurno(onBackposicionTipoDias);*/
                Log.d(TAG, "onActivityResult : " + posicionTipoPrecio +
                        " / " + posicionTipoTurno +
                        "/ " + posicionTipoDias);


            } else {
                this.onBackposicionTipoPrecio = posicionTipoPrecio;
                this.onBackposicionTipoTurno = posicionTipoTurno;
                this.onBackposicionTipoDias = posicionTipoDias;
                Log.d(TAG, "No Hubo Items elejidos");
            }
        } else {
            Log.d(TAG, "RESULTADO_ACTIVIDAD_ACTIVIDAD_PROPUESTA NO HUBO");
        }
    }


    @Override
    public void onNewIntent(Intent intent) {
        this.onBackposicionTipoPrecio = posicionTipoPrecio;
        this.onBackposicionTipoTurno = posicionTipoTurno;
        this.onBackposicionTipoDias = posicionTipoDias;

        Log.d(TAG, "onNewIntent : " + posicionTipoPrecio +
                " / " + posicionTipoTurno +
                "/ " + posicionTipoDias);

    }

    @Override
    public void onFinishActivity() {
        Log.d(TAG, "onFinishActivity : "   );
        if (view != null)
            view.onBackActivityEspec(posicionTipoPrecio, posicionTipoTurno, posicionTipoDias);
    }
}
