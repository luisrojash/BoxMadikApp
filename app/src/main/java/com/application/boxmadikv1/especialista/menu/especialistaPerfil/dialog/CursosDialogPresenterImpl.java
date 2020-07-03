package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog;

import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.base.BasePresenter;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.useCase.ListaCursos;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class CursosDialogPresenterImpl implements CursosDialogPresenter {

    public static final String TAG = CursosDialogPresenterImpl.class.getSimpleName();

    CursosDialogView view;
    // private ListaCursos listaCursos;
    private UseCaseHandler handler;

    public CursosDialogPresenterImpl(UseCaseHandler handler) {
        // this.listaCursos = listaCursos;
        this.handler = handler;
    }

    @Override
    public void attachView(CursosDialogView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        if (view != null) view.mostrarListaCursos(cursosUiList);

    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {

    }

    List<CursosUi> cursosUiList;

    @Override
    public void onExtras(Bundle bundle) {
        if (bundle == null) return;
        // String keyUser = bundle.getString("cursosUiList");
        this.cursosUiList = Parcels.unwrap(bundle.getParcelable("cursosUiList"));
        if (cursosUiList == null) return;
        //initUseCaseListaCursos(keyUser);
        Log.d(TAG, "cursosUiList" + cursosUiList.size());

    }


    /*private void initUseCaseListaCursos(String keyUser) {
        handler.execute(listaCursos, new ListaCursos.RequestValues(keyUser),
                new UseCase.UseCaseCallback<ListaCursos.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaCursos.ResponseValue response) {
                        if (response == null) return;
                        if (response.getListaCursosResponse().getError()) {
                            if (view != null) {
                                String mensaje = "No cuenta con Cursos Registrados";
                                view.mostrarMensaje(mensaje);
                                view.ocultarDialog();
                            }

                        } else {
                            getCursosUiList(response.getListaCursosResponse().getListaCurResponses());
                            if (view != null) {
                                view.mostrarListaCursos(getCursosUiList(response.getListaCursosResponse().getListaCurResponses()));
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
*/
    private List<CursosUi> getCursosUiList(List<ListaCursosResponse.ListaCurResponse> listaCursosResponse) {
        List<CursosUi> cursosUiList = new ArrayList<>();
        for (ListaCursosResponse.ListaCurResponse listaCurResponse : listaCursosResponse) {
            CursosUi cursosUi = new CursosUi();
            cursosUi.setCodigoEspeEstudios(listaCurResponse.getCodigoEspeEstudios());
            cursosUi.setEstadoValidacion(listaCurResponse.getEstadoValidacion());
            cursosUi.setTipoEstudioNombre(listaCurResponse.getTipoEstudioNombre());
            cursosUi.setNombreEspeEstudios(listaCurResponse.getNombreEspeEstudios());
            cursosUi.setNombreCentroEstu(listaCurResponse.getNombreCentroEstu());
            cursosUi.setAnoInicioEspeEstudios(listaCurResponse.getAnoInicioEspeEstudios());
            cursosUi.setAnoFinEspeEstudios(listaCurResponse.getAnoFinEspeEstudios());
            cursosUi.setMesInicioEspeEstudios(listaCurResponse.getMesInicioEspeEstudios());
            cursosUi.setMesFinEspeEstudios(listaCurResponse.getMesFinEspeEstudios());
            cursosUiList.add(cursosUi);
        }
        return cursosUiList;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
    }
}
