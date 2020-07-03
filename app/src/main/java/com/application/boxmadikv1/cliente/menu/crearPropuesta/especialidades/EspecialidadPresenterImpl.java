package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.useCase.ListarAutoComplete;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.EspecialidadActivity.SECOND_ACTIVITY_REQUEST_CODE_ESPECIALIDAD;
import static com.application.boxmadikv1.registraUser.RegistrarUserActivity.SECOND_ACTIVITY_REQUEST_CODE;

public class EspecialidadPresenterImpl extends BaseActivityPresenterImpl<EspecialidadView> implements EspecialidadPresenter {

    public static final String TAG = EspecialidadPresenterImpl.class.getSimpleName();

    ListarAutoComplete listarAutoComplete;
    List<EspecialidadUi> listaEspecialidad = new ArrayList<>();

    public EspecialidadPresenterImpl(UseCaseHandler handler, Resources res, ListarAutoComplete listarAutoComplete) {
        super(handler, res);
        this.listarAutoComplete = listarAutoComplete;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    String imagenRubro = "";
    String nombreRubro = "";
    String codigoPais = "";
    int rubroId;
    int oficioId;

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.rubroId = extras.getInt("rubroId", 0);
        this.oficioId = extras.getInt("oficioId", 0);
        this.imagenRubro = extras.getString("imagenRubro");
        this.nombreRubro = extras.getString("nombreOficio");
        this.codigoPais = extras.getString("codigoPais");
        Log.d(TAG, "imagenRubro : " + imagenRubro + " / nombreRubro : " + nombreRubro);
    }

    @Override
    public void onStart() {
        super.onStart();
        mostrarVistaCabecera(imagenRubro, nombreRubro);
        initUseCaseListarAutoComplete(rubroId, oficioId,codigoPais);
    }


    List<EspecialidadUi> listaEspecialidadCompleta;

    private void initUseCaseListarAutoComplete(int rubroId, int oficioId,String codigoPais) {
        handler.execute(listarAutoComplete, new ListarAutoComplete.RequestValues(rubroId, oficioId,codigoPais),
                new UseCase.UseCaseCallback<ListarAutoComplete.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarAutoComplete.ResponseValue response) {
                        Log.d(TAG, "onSuccess : " + response.getEspecialidadUiList().size());
                        if (response.getEspecialidadUiList() == null) return;
                        listaEspecialidadCompleta = response.getEspecialidadUiList();
                        if (view != null){
                            view.mostrarListaAutoComplete(response.getEspecialidadUiList());
                            view.onOcultarTeclado();
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    private void mostrarVistaCabecera(String imagenRubro, String nombreOficio) {
        if (view != null) view.mostrarVistaCabecera(imagenRubro, nombreOficio);
    }

    EspecialidadUi especialidadUi;


    @Override
    public void onClickAutoComplete(EspecialidadUi especialidadUi) {
        this.especialidadUi = especialidadUi;
    }

    String tipoEspecialidad;

    @Override
    public void onClickAgregarItem(String tipoEspecialidad) {

        if (tipoEspecialidad.isEmpty()) {

            view.mostrarMensaje("Escriba o Seleccione una Especialidad");
            view.ClearEditText();
            return;
        }
        if (especialidadUi == null) {

            if (view != null) {
                view.mostrarMensaje("Especialidad no Encontrada");
                view.ClearEditText();
            }

            return;
        }
        if (especialidadUi.getDescripcion().isEmpty() || especialidadUi.getDescripcion() == null) {
            if (view != null) view.mostrarMensaje("Especialidad no Encontrada");
            return;
        }


        this.tipoEspecialidad = tipoEspecialidad;


        if (view != null) view.validarItemsAgregar(especialidadUi);
        //if (view != null) view.agregarItemEspecialidad(especialidadUi);
    }

    @Override
    public void onValidarItemsAgregar(int totalItem, EspecialidadUi especialidadUi) {
        if (totalItem > 4) {
            if (view != null) view.mostrarMensaje("Llego asu limite");
            return;
        } else {


            if (listaEspecialidad != null) {
                for (int i = 0; i < listaEspecialidad.size(); i++) {
                    EspecialidadUi especialidadUi2 = listaEspecialidad.get(i);

                    if (!especialidadUi.getDescripcion().equals(tipoEspecialidad)) {
                        view.mostrarMensaje("Especialidad no encontrada");
                        view.ClearEditText();
                        return;
                    }

                    if (especialidadUi.getDescripcion().equals(especialidadUi2.getDescripcion())) {
                        view.mostrarMensaje("Ya se agrego esta especialidad");
                        view.ClearEditText();
                        return;
                    }
                }
            }

            listaEspecialidad.add(especialidadUi);

            if (view != null) view.agregarItemEspecialidad(especialidadUi);

            return;
        }
    }

    @Override
    public void onClickSiguiente() {
        ArrayList<String> listaIdEspecialidades = new ArrayList<>();
        if (listaEspecialidad == null || listaEspecialidad.isEmpty()) {
            if (view != null)
                view.startActivityHorarioAtencion(listaIdEspecialidades, rubroId, oficioId, imagenRubro, nombreRubro,
                        posicionTipoPrecio, posicionTipoTurno, posicionTipoDias,codigoPais);
            return;
        } else {
            for (EspecialidadUi especialidadUi : listaEspecialidad) {
                listaIdEspecialidades.add(especialidadUi.getIdEspecialidad());
            }
            if (view != null)
                view.startActivityHorarioAtencion(listaIdEspecialidades, rubroId, oficioId, imagenRubro, nombreRubro,
                        posicionTipoPrecio, posicionTipoTurno, posicionTipoDias,codigoPais);
        }
    }

    @Override
    public void onClickEspecialidad() {

        if (view != null) view.mostrarOnclickLista(listaEspecialidadCompleta);
    }

    @Override
    public void onItemClickEspecialidadDelete(EspecialidadUi especialidadUi) {
        listaEspecialidad.remove(especialidadUi);
        /*for (EspecialidadUi especialidadUi1:  listaEspecialidad){
            if (especialidadUi1.getDescripcion().equals(especialidadUi.getDescripcion())) {
                listaEspecialidad.remove(especialidadUi);
                return;
            }

        }*/
        if (view != null) {
            view.eliminarEspecialidad(especialidadUi);
        }
    }

    int posicionTipoPrecio, posicionTipoTurno, posicionTipoDias = 0;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE_ESPECIALIDAD) {  // Check that it is the SecondActivity with an OK result
            Log.d(TAG, "SECOND_ACTIVITY_REQUEST_CODE");
            if (resultCode == RESULT_OK) {
                this.posicionTipoPrecio = data.getIntExtra("posicionTipoPrecio", 0);
                this.posicionTipoTurno = data.getIntExtra("posicionTipoTurno", 0);
                this.posicionTipoDias = data.getIntExtra("posicionTipoDias", 0);
                Log.d(TAG, "posicionTipoPrecio " + posicionTipoPrecio + "" +
                        "posicionTipoTurno " + posicionTipoTurno + "" +
                        "posicionTipoDias " + posicionTipoDias + "");
            }

        } else {
            Log.d(TAG, "elseSECOND_ACTIVITY_REQUEST_CODE");
        }
    }
}
