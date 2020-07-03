package com.application.boxmadikv1.especialista.menu.edicionDatos;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosBancaria;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosCentroEstudioList;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecEditDireccion;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecUi;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecZonaTrabajo;

import java.util.List;

public interface DatosEspecView extends BaseActivityView<DatosEspecPresenter> {
    void mostrarLista(List<DatosEspecUi> datosEspecUiList);

    void initStartActivityEditarPerfil();

    void initStartActivityEditarDireccion(DatosEspecEditDireccion datosEspecEditDireccion);

    void initStartActivityEditarZonaTrabajo(DatosEspecZonaTrabajo datosEspecZonaTrabajo);

    void initStartActivityEditarPresentacion(String usuarioPresentacion);

    void initStartActivityEditarCursos(List<DatosCentroEstudioList> centroEstudioLists);

    void initStartActivityEditarCuentaBancaria(DatosBancaria datosBancaria);

    void estadoDireccion(boolean b, String tipoEstadoId);

    void mostrarMensaje(String mensajeError);

    void onFinishCliente();

    void onFinishEspecialista();

    void initStartActivityEditarRubroTrabajo();

    void initStartActivityCambiarClave();

    void mostrarDialogMensaje(String s);

    void mostrarTitleToolbar(String titulo);
}
