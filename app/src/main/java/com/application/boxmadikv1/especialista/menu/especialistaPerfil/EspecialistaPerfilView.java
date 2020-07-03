package com.application.boxmadikv1.especialista.menu.especialistaPerfil;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.entidad.TrabajosRealizadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.ComentariosUi;

import java.util.List;

public interface EspecialistaPerfilView extends BaseActivityView<EspecialistaPerfilPresenter> {

    void mostrarDataInicial(String nombreUsu, String apellidoUsu, String fotoUsu);

    void initViewPagerAdapter();

    void initClearFragments();

    void actualizarAdapterFragmentos();

    void mostrarMensajeToast(String s);

    void mostrarListaComentarios(List<TrabajosRealizadosUi> trabajosRealizadosUis);


    void mostrarDatosPropuesta(String cotiFinalizada, String cotiPendiente, String cotiAceptado,float estrellasUsu,String datosResenia);

    void mostrarTextoEmpty(String s);

    void ocultarTextoEmpty();

    void mostrarBotones();

    void mostrarMensajeResenia(String datosResenia);
}
