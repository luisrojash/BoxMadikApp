package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosCentroEstudioList;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.DatosCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.useCase.ListarTipoCentroEstudios;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.useCase.ListarTipoEstudios;
import com.application.boxmadikv1.utils.Constantes;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.EstudioPerfilActivity.EXTRA_ESPE_CENTRO_ESTUDIOS;

public class EstudioPerfilPresenterImpl extends BaseActivityPresenterImpl<EstudioPerfilView> implements EstudioPerfilPresenter {

    public static final String TAG = EstudioPerfilPresenterImpl.class.getSimpleName();
    public static final String ACTUALIZACION_CORRECTAMENTE_PERFIL_ESPECIALISTA = "EstudioPerfilPresenterImpl";

    private ListarTipoCentroEstudios listarTipoCentroEstudios;
    private ListarTipoEstudios listarTipoEstudios;
    private Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

    public EstudioPerfilPresenterImpl(UseCaseHandler handler, Resources res,
                                      ListarTipoCentroEstudios listarTipoCentroEstudios,
                                      ListarTipoEstudios listarTipoEstudios) {
        super(handler, res);
        this.listarTipoCentroEstudios = listarTipoCentroEstudios;
        this.listarTipoEstudios = listarTipoEstudios;
    }

    @Override
    public void onStart() {
        super.onStart();
        initUseCaseTipoEstudio();
        initUseCaseTipoCentroEstudios();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        obtenerKeyUser();
        initListaLlena();
    }

    private List<String> stringListCodigoEspe;

    private void initListaLlena() {
        if (centroEstudioLists != null) {
            stringListCodigoEspe = new ArrayList<>();
            for (DatosCentroEstudioList datosCentroEstudioList : centroEstudioLists) {
                DatosCursos datosCursos = new DatosCursos();
                datosCursos.setId(datosCentroEstudioList.getCodigoEspeEstudios());
                datosCursos.setValidacion("0");
                datosCursos.setTipoEstudioCodigo(datosCentroEstudioList.getCodigoTipoEstudios());
                datosCursos.setTipoEstudio(datosCentroEstudioList.getNombreTipoEstudiosM());
                datosCursos.setNombreCurso(datosCentroEstudioList.getNombreCurso());
                datosCursos.setCentroEstudioCodigo(datosCentroEstudioList.getCodigoCentroEstudios());
                datosCursos.setNombreCrentroEstudio(datosCentroEstudioList.getNombreCentroEstudios());
                datosCursos.setFechaInicioEstudio(datosCentroEstudioList.getFechaInicio());
                datosCursos.setFechanFinEstudio(datosCentroEstudioList.getFechaFin());
                datosCursos.setAnioInicio(datosCentroEstudioList.getAnioInicio());
                datosCursos.setAnioFin(datosCentroEstudioList.getAnioFin());
                datosCursos.setMesFin(datosCentroEstudioList.getMesFin());
                datosCursos.setMesInicio(datosCentroEstudioList.getMesInicio());
                datosCursosList.add(datosCursos);
                Log.d(TAG, "datosCentroEstudioListgetAnioInicio:" + datosCentroEstudioList.getAnioInicio());

                stringListCodigoEspe.add(datosCentroEstudioList.getCodigoEspeEstudios());
            }


            if (view != null) {
                view.mostrarLista(datosCursosList);
            }
        }

    }


    private void obtenerKeyUser() {
        if (view != null) view.obtenerKeyUser();
    }

    private void initUseCaseTipoCentroEstudios() {
        handler.execute(listarTipoCentroEstudios, new ListarTipoCentroEstudios.RequestValues(paisCodigo),
                new UseCase.UseCaseCallback<ListarTipoCentroEstudios.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarTipoCentroEstudios.ResponseValue response) {
                        if (response.getTipoCentroEstudiosUiList() == null) return;
                        if (view != null)
                            view.mostrarListaAutoCompleteTipoCentroEstudios(response.getTipoCentroEstudiosUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initUseCaseTipoEstudio() {
        handler.execute(listarTipoEstudios, new ListarTipoEstudios.RequestValues(),
                new UseCase.UseCaseCallback<ListarTipoEstudios.ResponseValue>() {
                    @Override
                    public void onSuccess(ListarTipoEstudios.ResponseValue response) {
                        if (response.getTipoEstudiosUiList() == null) return;
                        if (view != null)
                            view.mostrarListaAutoCompleteTipoEstudios(response.getTipoEstudiosUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }


    private List<DatosCursos> datosCursosList = new ArrayList<>();
    private List<DatosCentroEstudioList> centroEstudioLists;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        centroEstudioLists = extras.getParcelableArrayList(EXTRA_ESPE_CENTRO_ESTUDIOS);
    }

    @Override
    public void onClickAgregarItem(String nombreCurso) {
        if (tipoEstudiosUi == null) {
            if (view != null) {
                String mensaje = "Estudio Vacio o No Encontrada";
                view.mostrarMensaje(mensaje);
                view.limpiarEditTextTipoEstudio();
            }
            return;
        }
        if (nombreCurso.isEmpty() || nombreCurso.equals(" ") || nombreCurso == null || nombreCurso.trim().isEmpty()) {
            if (view != null) view.mostrarMensaje("Nombre Curso Vacio");
            return;
        }
        if (tipoCentroEstudiosUi == null) {
            if (view != null) {
                String mensaje = "Centro Estudio Vacio o No Encontrada";
                view.mostrarMensaje(mensaje);
                view.limpiarEditTextTipoCentroEstudio();
            }
            return;
        }
        if (dateInicio == null || dateInicio.isEmpty() || dateInicio.equals("")||
                dateInicio.equals(" ") || dateInicio.equals("Fecha Inicio")) {
            if (view != null) view.mostrarMensaje("Elija la Fecha Inicio");
            Log.d(TAG, "dateInicio.isEmpty()");
            return;
        }
        if (dateFin == null || dateFin.isEmpty() || dateFin.equals("")||
                dateFin.equals(" ") || dateFin.equals("Fecha Fin")) {
            if (view != null) view.mostrarMensaje("Elija la Fecha Final");
            Log.d(TAG, "dateInicio.isEmpty()");
            return;
        }
        if (!Constantes.CheckDatesNow(dateInicio, dateFin)) {
            if (view != null)
                view.mostrarMensaje("La Fecha no tiene que ser mayor a la actual");
            return;
        }


        DatosCursos datosCursos = new DatosCursos();
        datosCursos.setId(tipoEstudiosUi.getId());
        datosCursos.setValidacion("0");
        datosCursos.setTipoEstudioCodigo(tipoCentroEstudiosUi.getId());
        datosCursos.setTipoEstudio(tipoEstudiosUi.getDescripcion());
        datosCursos.setNombreCurso(nombreCurso);
        datosCursos.setCentroEstudioCodigo(tipoCentroEstudiosUi.getId());
        datosCursos.setNombreCrentroEstudio(tipoCentroEstudiosUi.getDescripcion());
        datosCursos.setFechaInicioEstudio(dateInicio);
        datosCursos.setFechanFinEstudio(dateFin);
        datosCursos.setAnioInicio(anioInicio);
        datosCursos.setAnioFin(anioFin);
        validarItemRepetido(datosCursos);


    }


    private void validarItemRepetido(DatosCursos datosCursos2) {
        for (DatosCursos datosCursos : datosCursosList) {
            if (datosCursos2.getNombreCurso().equals(datosCursos.getNombreCurso())) {
                if (view != null) view.mostrarMensaje("No se permiten el mismo Curso");
                return;
            } else {
                continue;
            }
        }
        agregarCurso(datosCursos2);
    }

    private void agregarCurso(DatosCursos datosCursos) {
        if (view != null) {
            view.agregarItemDataCurso(datosCursos);
            view.limpiarTextPuesto();
            view.limpiartextNombreCurso();
            view.limpiarTextCentro();
            view.limpiarFechaFin();
            view.limpiarFechaInicio();
            initNullItem();
        }
        datosCursos.setMesInicio(nombreMesInicio);
        datosCursos.setMesFin(nombreMesFin);
        datosCursosList.add(datosCursos);
    }

    private void initNullItem() {
        tipoEstudiosUi = null;
        tipoCentroEstudiosUi = null;
        dateFin = "Fecha Fin";
        dateInicio = "Fecha Inicio";
    }

    private TipoEstudiosUi tipoEstudiosUi;

    @Override
    public void onClickAutoCompleteTipoEstudio(TipoEstudiosUi tipoEstudiosUi) {
        this.tipoEstudiosUi = tipoEstudiosUi;
        if (view != null)
            view.mostrarTextTipoEstudio("Tipo Estudios : " + tipoEstudiosUi.getDescripcion());

    }

    private TipoCentroEstudiosUi tipoCentroEstudiosUi;

    @Override
    public void onClickAutoCompleteTipoCentro(TipoCentroEstudiosUi tipoCentroEstudiosUi) {
        this.tipoCentroEstudiosUi = tipoCentroEstudiosUi;
        if (view != null)
            view.mostrarTextTipoCentro("Centro Estudios : " + tipoCentroEstudiosUi.getDescripcion());
    }

    @Override
    public void onClickFechaInicio() {
        if (view != null) view.mostrarDialogFechaInicio();
    }

    @Override
    public void onClickFechaFin() {
        if (view != null) view.mostrarDialogFechaFin();
    }

    private String dateInicio = " ";
    private String nombreMesInicio;
    private String anioInicio;

    @Override
    public void onAceptarDateInicio(String dateTime, String nombreMes, String anio) {
        this.dateInicio = dateTime;
        this.nombreMesInicio = nombreMes;
        this.anioInicio = anio;
        Log.d(TAG, "onAceptarDateInicio : " + dateTime);
    }

    private String dateFin = " ";
    private String nombreMesFin;
    private String anioFin;

    @Override
    public void onAceptarDateFin(String dateTime, String nombreMes, String anio) {
        this.dateFin = dateTime;
        this.nombreMesFin = nombreMes;
        this.anioFin = anio;
        Log.d(TAG, "onAceptarDateFin : " + dateTime);
    }

    @Override
    public void onLongClickCursoEliminar(DatosCursos datosCursos) {
        datosCursosList.remove(datosCursos);
        if (view != null) view.elimnarItemCurso(datosCursos);
    }

    private String keyUser, paisCodigo;

    @Override
    public void onKeyUser(String keyUser, String userFoto, String paisCodigo) {
        this.keyUser = keyUser;
        this.paisCodigo = paisCodigo;
        Log.d(TAG, "paisCodigo : " + paisCodigo);
    }

    @Override
    public void onClickGuardar() {
        if (view != null) view.deshabilitarButtonGuardar();
        if (datosCursosList == null || datosCursosList.isEmpty()) {
            if (view != null) {
                String mensaje = "No se permiten campos Vacios";
                view.mostrarMensaje(mensaje);
                view.habilitarButtonGuardar();
            }
            return;
        }
        initGuardarDataCentroEstudios();
    }

    @Override
    public void onClickDeletePuesto() {
        this.tipoEstudiosUi = null;
        if (view != null) view.limpiarTextPuesto();

    }

    @Override
    public void onClickDeleteCentro() {
        this.tipoCentroEstudiosUi = null;
        if (view != null) view.limpiarTextCentro();
    }

    private void initGuardarDataCentroEstudios() {
        String tipoEstadoValidadoCurso = "0";
        /*Utilizando un Servicio que Validara por estado
         *  2 = Actualiza
         *  3 = Inserta
         * */
        if (view != null) view.mostrarProgressBarDialog();
        Gson gson = new Gson();

        if (centroEstudioLists != null) {
            Log.d(TAG, "entroEstudioLists != null");
            String tipoEstadoActualizar = "2";
            String jsonList = gson.toJson(datosCursosList);
            initRetrofitGuardar(jsonList, tipoEstadoActualizar, tipoEstadoValidadoCurso);

        } else {
            Log.d(TAG, "entroEstudioLists != else");
            String tipoEstadoInsertar = "3";
            String jsonList = gson.toJson(datosCursosList);
            initRetrofitGuardar(jsonList, tipoEstadoInsertar, tipoEstadoValidadoCurso);
        }
    }

    private void initRetrofitGuardar(String jsonList, final String tipoEstadoInsertar, final String tipoEstadoValidadoCurso) {
        Log.d(TAG, "jsonList : " + jsonList);
        service.guardarCentroEspecCurEspecialista(keyUser, jsonList, tipoEstadoValidadoCurso, tipoEstadoInsertar,
                paisCodigo).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                String guardandoCursos = "Guardando Cursos Especialidades";
                if (defaultResponse == null) {
                    String mensaje = "Problemas con nuestros Servidores " + guardandoCursos;
                    if (view != null) {
                        view.mostrarMensaje(mensaje);
                        view.ocultarProgressBarDialog();
                        view.habilitarButtonGuardar();
                    }
                    return;
                } else {
                    if (defaultResponse.getError()) {
                        if (view != null) {
                            view.mostrarMensaje(defaultResponse.getMessage() + " " + guardandoCursos);
                            view.ocultarProgressBarDialog();
                            view.habilitarButtonGuardar();
                        }
                        return;

                    } else {
                        //Aqui es porque Registro Correctamente
                        switch (tipoEstadoInsertar) {
                            case "2"://Actualizar
                                String tipoEstadoInsertar = "3";
                                Gson gson = new Gson();
                                String jsonList = gson.toJson(datosCursosList);
                                //initRetrofitGuardar(datosCursosList, tipoEstadoInsertar, tipoEstadoValidadoCurso);jsonList
                                initRetrofitGuardar(jsonList, tipoEstadoInsertar, tipoEstadoValidadoCurso);
                                guardoCorrectamente();
                                break;
                            case "3"://Insertar
                                guardoCorrectamente();
                                break;
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                if (view != null) {
                    String mensaje = "Centro Estudios";
                    view.mostrarMensaje(t.getMessage().toString() + " " + mensaje);
                    view.ocultarProgressBarDialog();
                    view.habilitarButtonGuardar();
                }
                return;
            }
        });

    }

    private void guardoCorrectamente() {
        if (view != null) {
            view.ocultarProgressBarDialog();
            view.iniStartActivityMenuEspecialista(ACTUALIZACION_CORRECTAMENTE_PERFIL_ESPECIALISTA);
        }
        return;
    }

}
