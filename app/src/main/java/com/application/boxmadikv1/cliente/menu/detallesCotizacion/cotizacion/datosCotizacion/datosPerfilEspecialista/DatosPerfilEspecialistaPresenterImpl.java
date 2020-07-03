package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.useCase.ListaComentarios;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.useCase.ListaCursos;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class DatosPerfilEspecialistaPresenterImpl extends BaseFragmentPresenterImpl<DatosPerfilEspecialistaView> implements DatosPerfilEspecialistaPresenter {

    public static final String TAG = DatosPerfilEspecialistaPresenterImpl.class.getSimpleName();
    private ListaComentarios listaComentarios;
    private ListaCursos listaCursos;

    public DatosPerfilEspecialistaPresenterImpl(UseCaseHandler handler, Resources res, ListaComentarios listaComentarios, ListaCursos listaCursos) {
        super(handler, res);
        this.listaComentarios = listaComentarios;
        this.listaCursos = listaCursos;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;
    String tipoLlegadaExtra;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        this.cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
        this.tipoLlegadaExtra = extras.getString("notificacion");
        Log.d(TAG, "ASDADADAD : " + detallesCotizacionUi.getNombreProyecto() +
                "ASDADADASDAD : " + cotizacionesUi.getNombreEspecialista());

        mostrarDataInicial(detallesCotizacionUi, cotizacionesUi);
        initUseCaseMostrarListaComentarios();
    }


    private void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        if (view != null) view.mostrarDataInicial(detallesCotizacionUi, cotizacionesUi);
    }

    @Override
    public void onStart() {
        super.onStart();
        //validateEstadoPropuestaCoti(cotizacionesUi);
    }

    private void validateEstadoPropuestaCoti(CotizacionesUi cotizacionesUi) {
        if (cotizacionesUi.getEstadoPropuesta().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO) &&
                cotizacionesUi.getEstadoCotizacion().equals(Constantes.ESTADO_ESPECIALISTA_PAGADO)) {
            if (view != null) view.mostrarButtonCalificar();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initUseCaseMostrarListaComentarios() {
        Log.d(TAG, "initUseCaseMostrarListaComentarios : " + cotizacionesUi.getIdUsuarioCotizacion());
        handler.execute(listaComentarios, new ListaComentarios.RequestValues(cotizacionesUi.getIdUsuarioCotizacion(), Constantes.PAIS_CODIGO_PERU),
                new UseCase.UseCaseCallback<ListaComentarios.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaComentarios.ResponseValue response) {
                        String mensaje = "Error con nuestros servidores..";

                        if (response.getResultado() == null) {
                            Log.d(TAG, "mensaje IF ");
                            //  if (view != null) view.mostrarMensaje(mensaje);
                            // return;
                        } else {
                            Log.d(TAG, "mensaje NO ");
                            if (response.getResultado().size() > 1) {
                                if (view != null) {
                                    view.mostrarBotonDerecho();
                                    view.mostrarBotonIzquierdo();
                                }
                            }
                            if (view != null) view.mostrarListaComentarios(response.getResultado());
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onClickCursos() {
        if (view != null) view.mostrarDialogCursos(cotizacionesUi.getIdUsuarioCotizacion());
    }

    // DatosPropuestaUi datosPropuestaUi;

    @Override
    public void onDatosCursos(DatosPropuestaUi datosPropuestaUi) {
    }

    @Override
    public void onClickCalificar() {
        if (view != null) view.initStartActivityCalificacion(detallesCotizacionUi, cotizacionesUi);
    }

    @Override
    public void onMostrarListaCursos(String idUsuarioCotizacion) {
        initUseCaseListaCursos(idUsuarioCotizacion);
    }

    private void initUseCaseListaCursos(String keyUser) {
        handler.execute(listaCursos, new ListaCursos.RequestValues(keyUser),
                new UseCase.UseCaseCallback<ListaCursos.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaCursos.ResponseValue response) {
                        if (response == null) return;
                        if (response.getListaCursosResponse().getError()) {
                            if (view != null) {
                                String mensaje = "No cuenta con Cursos Registrados";
                                view.mostrarMensaje(mensaje);
                               // view.ocultarDialog();
                            }

                        } else {
                            getCursosUiList(response.getListaCursosResponse().getListaCurResponses());
                            if (view != null) {
                               view.mostrarListaCursosDialog(getCursosUiList(response.getListaCursosResponse().getListaCurResponses()));
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

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
}
