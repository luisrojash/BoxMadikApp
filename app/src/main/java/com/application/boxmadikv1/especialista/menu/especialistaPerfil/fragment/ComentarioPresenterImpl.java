package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.CeldasCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.ColumnaCabeceraCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.FilaCabeceraCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.CeldasResutadoUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.ComentariosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoCabeceraCursosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoFilaValidacionCursosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.useCase.ListaCursos;

import java.util.ArrayList;
import java.util.List;

import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoCabeceraCursosUi.EnumTipoCabeceraCursoColumna.TIPO_CABECERA_CURSOS_CENTRO_ESTUDIOS;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoCabeceraCursosUi.EnumTipoCabeceraCursoColumna.TIPO_CABECERA_CURSOS_FECHA_FIN;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoCabeceraCursosUi.EnumTipoCabeceraCursoColumna.TIPO_CABECERA_CURSOS_FECHA_INICIO;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoCabeceraCursosUi.EnumTipoCabeceraCursoColumna.TIPO_CABECERA_CURSOS_NOMBRE;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoCabeceraCursosUi.EnumTipoCabeceraCursoColumna.TIPO_CABECERA_CURSOS_TIPO_ESTUDIOS;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoCabeceraCursosUi.EnumTipoCabeceraCursoColumna.TIPO_CABECERA_CURSOS_VALIDACION;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoFilaValidacionCursosUi.EnumTipoValidacionCursoFila.FILAS_CURSOS_SIN_VALIDAR;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.TipoFilaValidacionCursosUi.EnumTipoValidacionCursoFila.FILAS_CURSOS_VALIDADO;

public class ComentarioPresenterImpl extends BaseFragmentPresenterImpl<ComentarioView> implements ComentarioPresenter {

    public static final String TAG = ComentarioPresenterImpl.class.getSimpleName();
    private ListaCursos listaCursos;
    List<ColumnaCabeceraCursos> columnHeaderList;
    List<List<CeldasCursos>> cellsList;
    List<List<CeldasCursos>> cellsList2;
    List<FilaCabeceraCursos> rowHeaderList;

    public ComentarioPresenterImpl(UseCaseHandler handler, Resources res, ListaCursos listaCursos) {
        super(handler, res);
        this.listaCursos = listaCursos;
        columnHeaderList = new ArrayList<>();
        cellsList = new ArrayList<>();
        rowHeaderList = new ArrayList<>();

        celdasResutadoUiList = new ArrayList<>();
        tipoCabeceraCursosUiList = new ArrayList<>();
        tipoFilaValidacionCursosUiList = new ArrayList<>();

    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initTablaInstancia();
    }

    private void initTablaInstancia() {

        /*alumnosList = new ArrayList<>();
        celdasAsistenciasColumnaPresentes = new ArrayList<>();
        celdasAsistencias = new ArrayList<>();*/
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    List<CeldasResutadoUi> celdasResutadoUiList;
    List<TipoCabeceraCursosUi> tipoCabeceraCursosUiList;
    List<TipoFilaValidacionCursosUi> tipoFilaValidacionCursosUiList;


    List<ListaCursosResponse.ListaCurResponse> listaCurResponseList;
    private void initUseCaseListaCursos(String keyUser) {
        handler.execute(listaCursos, new ListaCursos.RequestValues(keyUser),
                new UseCase.UseCaseCallback<ListaCursos.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaCursos.ResponseValue response) {
                        if (response == null) return;
                        if (response.getListaCursosResponse().getError()) {
                            if (view != null)
                                view.mostrarMensaje(response.getListaCursosResponse().getMensaje());
                        } else {
                            //getTipoFilaValidacionCursosUiList(response.getListaCursosResponse().getListaCurResponses());
                            // tipoCabeceraCursosUiList.addAll(getTipoCabeceraCursosUiList());
                            // tipoFilaValidacionCursosUiList.addAll(getTipoFilaValidacionCursosUiList(response.getListaCursosResponse().getListaCurResponses()));
                            tipoCabeceraCursosUiList = getTipoCabeceraCursosUiList();
                            tipoFilaValidacionCursosUiList = getTipoFilaValidacionCursosUiList(response.getListaCursosResponse().getListaCurResponses());
                            cellsList2 = getCellsList(response.getListaCursosResponse().getListaCurResponses());

                           // celdasResutadoUiList = getCellsList(response.getListaCursosResponse().getListaCurResponses())
                            listaCurResponseList = response.getListaCursosResponse().getListaCurResponses();
                            columnHeaderList.addAll(tipoCabeceraCursosUiList);
                            rowHeaderList.addAll(tipoFilaValidacionCursosUiList);
                           // cellsList.addAll(getCellsList(response.getListaCursosResponse().getListaCurResponses()));
                            cellsList.addAll(cellsList2);

                            if(view!=null){
                                view.mostrarListaTablas(columnHeaderList,rowHeaderList,cellsList);
                            }

                            //celdasResutadoUiList.addAll(getCellsList(response.getListaCursosResponse().getListaCurResponses()));
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private List<TipoFilaValidacionCursosUi> getTipoFilaValidacionCursosUiList(List<ListaCursosResponse.ListaCurResponse> listaCurResponses) {
        List<TipoFilaValidacionCursosUi> tipoFilaValidacionCursosUiList = new ArrayList<>();
        for (ListaCursosResponse.ListaCurResponse listaCurResponse : listaCurResponses) {
            if (listaCurResponse.getEstadoValidacion().equals("0")) {
                TipoFilaValidacionCursosUi filaValidacionCursosUi = new TipoFilaValidacionCursosUi();
                filaValidacionCursosUi.setEnumTipoValidacionCursoFila(FILAS_CURSOS_SIN_VALIDAR);
                filaValidacionCursosUi.setDescripcion("0");
                tipoFilaValidacionCursosUiList.add(filaValidacionCursosUi);
            } else if (listaCurResponse.getEstadoValidacion().equals("1")) {
                TipoFilaValidacionCursosUi filaValidacionCursosUi = new TipoFilaValidacionCursosUi();
                filaValidacionCursosUi.setEnumTipoValidacionCursoFila(FILAS_CURSOS_VALIDADO);
                filaValidacionCursosUi.setDescripcion("1");
                tipoFilaValidacionCursosUiList.add(filaValidacionCursosUi);
            }
        }
        return tipoFilaValidacionCursosUiList;

    }

    private List<TipoCabeceraCursosUi> getTipoCabeceraCursosUiList() {
        List<TipoCabeceraCursosUi> tipoCabeceraCursosUiList = new ArrayList<>();

        /*TipoCabeceraCursosUi tipoCursoValidacion = new TipoCabeceraCursosUi();
        tipoCursoValidacion.setEnumTipoCabecera(TIPO_CABECERA_CURSOS_VALIDACION);
        tipoCursoValidacion.setDescripcion("Validado");
        tipoCabeceraCursosUiList.add(tipoCursoValidacion);*/

        TipoCabeceraCursosUi tipoEstudio = new TipoCabeceraCursosUi();
        tipoEstudio.setEnumTipoCabecera(TIPO_CABECERA_CURSOS_TIPO_ESTUDIOS);
        tipoEstudio.setDescripcion("Tipo Estudio");
        tipoCabeceraCursosUiList.add(tipoEstudio);

        TipoCabeceraCursosUi cursoNombre = new TipoCabeceraCursosUi();
        cursoNombre.setEnumTipoCabecera(TIPO_CABECERA_CURSOS_NOMBRE);
        cursoNombre.setDescripcion("Nombre");
        tipoCabeceraCursosUiList.add(cursoNombre);

        TipoCabeceraCursosUi tipoCentroEstudios = new TipoCabeceraCursosUi();
        tipoCentroEstudios.setEnumTipoCabecera(TIPO_CABECERA_CURSOS_CENTRO_ESTUDIOS);
        tipoCentroEstudios.setDescripcion("Centro de Estudios");
        tipoCabeceraCursosUiList.add(tipoCentroEstudios);

        TipoCabeceraCursosUi fechaInicio = new TipoCabeceraCursosUi();
        fechaInicio.setEnumTipoCabecera(TIPO_CABECERA_CURSOS_FECHA_INICIO);
        fechaInicio.setDescripcion("Fecha Inicio");
        tipoCabeceraCursosUiList.add(fechaInicio);

        TipoCabeceraCursosUi fechaFin = new TipoCabeceraCursosUi();
        fechaFin.setEnumTipoCabecera(TIPO_CABECERA_CURSOS_FECHA_FIN);
        fechaFin.setDescripcion("Fecha Fin");
        tipoCabeceraCursosUiList.add(fechaFin);
        /*for (ListaCursosResponse.ListaCurResponse listaCurResponse : listaCurResponses) {

        }*/
        return tipoCabeceraCursosUiList;
    }


    private List<List<CeldasCursos>> getCellsList(List<ListaCursosResponse.ListaCurResponse> listaCurResponses) {
        List<List<CeldasCursos>> list = new ArrayList<>();
        //  for (TipoFilaValidacionCursosUi filaValidacionCursosUi : tipoFilaValidacionCursosUiList) {
        for (ListaCursosResponse.ListaCurResponse listaCurResponse : listaCurResponses) {
            Log.d(TAG, "filaValidacionCursosUi : " + listaCurResponse.getNombreEspeEstudios());
            List<CeldasCursos> cellList = new ArrayList<>();

            for (int j = 0; j < tipoCabeceraCursosUiList.size(); j++) {
                TipoCabeceraCursosUi tipoCabeceraCursosUi = tipoCabeceraCursosUiList.get(j);
                if (tipoCabeceraCursosUi.getEnumTipoCabecera() == TIPO_CABECERA_CURSOS_VALIDACION) {
                    CeldasResutadoUi celdasResutadoUi = new CeldasResutadoUi(listaCurResponse.getEstadoValidacion());
                    cellList.add(celdasResutadoUi);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 1: " + tipoCabeceraCursosUi.getEnumTipoCabecera().name().toString());
                } else if (tipoCabeceraCursosUi.getEnumTipoCabecera() == TIPO_CABECERA_CURSOS_TIPO_ESTUDIOS) {
                    CeldasResutadoUi celdasResutadoUi = new CeldasResutadoUi(listaCurResponse.getTipoEstudioNombre());
                    cellList.add(celdasResutadoUi);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 2: " + tipoCabeceraCursosUi.getEnumTipoCabecera().name().toString());
                } else if (tipoCabeceraCursosUi.getEnumTipoCabecera() == TIPO_CABECERA_CURSOS_NOMBRE) {
                    CeldasResutadoUi celdasResutadoUi = new CeldasResutadoUi(listaCurResponse.getNombreEspeEstudios());
                    cellList.add(celdasResutadoUi);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 3: " + tipoCabeceraCursosUi.getEnumTipoCabecera().name().toString());
                } else if (tipoCabeceraCursosUi.getEnumTipoCabecera() == TIPO_CABECERA_CURSOS_CENTRO_ESTUDIOS) {
                    CeldasResutadoUi celdasResutadoUi = new CeldasResutadoUi(listaCurResponse.getNombreCentroEstu());
                    cellList.add(celdasResutadoUi);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 4: " + tipoCabeceraCursosUi.getEnumTipoCabecera().name().toString());
                } else if (tipoCabeceraCursosUi.getEnumTipoCabecera() == TIPO_CABECERA_CURSOS_FECHA_INICIO) {
                    CeldasResutadoUi celdasResutadoUi = new CeldasResutadoUi(listaCurResponse.getAnoInicioEspeEstudios());
                    cellList.add(celdasResutadoUi);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 5: " + tipoCabeceraCursosUi.getEnumTipoCabecera().name().toString());
                } else if (tipoCabeceraCursosUi.getEnumTipoCabecera() == TIPO_CABECERA_CURSOS_FECHA_FIN) {
                    CeldasResutadoUi celdasResutadoUi = new CeldasResutadoUi(listaCurResponse.getAnoFinEspeEstudios());
                    cellList.add(celdasResutadoUi);
                    Log.d(TAG, "alumnosUi.getTipoAsistencia() 6: " + tipoCabeceraCursosUi.getEnumTipoCabecera().name().toString());
                }
            }
            list.add(cellList);
        }
        return list;
    }


    String keyUser;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        /*this.nombrePropuesta = extras.getString(EspecialistaPerfilActivity.EXTRA_EDITAR_PERFIL_NOMBRE_PROPUESTA);
        this.fechaPropuesta = extras.getString(EspecialistaPerfilActivity.EXTRA_EDITAR_PERFIL_FECHA_PROPUESTA);
        this.fotoCliente = extras.getString(EspecialistaPerfilActivity.EXTRA_EDITAR_PERFIL_FOTO_CLIENTE);
        this.nombreCliente = extras.getString(EspecialistaPerfilActivity.EXTRA_EDITAR_PERFIL_NOMBRE_CLIENTE);
        this.apellidoCliente = extras.getString(EspecialistaPerfilActivity.EXTRA_EDITAR_PERFIL_APELLIDO_CLIENTE);
        this.estrellasCliente = extras.getString(EspecialistaPerfilActivity.EXTRA_EDITAR_PERFIL_ESTRELLAS_CLIENTE);
        this.comentarioCliente = extras.getString(EspecialistaPerfilActivity.EXTRA_EDITAR_PERFIL_COMENTARIO_CLIENTE);*/
        ComentariosUi comentariosUi = extras.getParcelable("coment");
        this.keyUser = comentariosUi.getKeyUser();
        if (view != null) view.mostrarDataInicial(comentariosUi);
        initUseCaseListaCursos(keyUser);
        Log.d(TAG, "setExtras " + comentariosUi.getNombreCliente());

    }


    @Override
    public void onClickCursos() {

        if(view!=null)view.initListenerCursos(keyUser);
    }
}
