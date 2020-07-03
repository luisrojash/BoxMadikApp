package com.application.boxmadikv1.cliente.menu.clientePerfil;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.clientePerfil.entidad.ComentariosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.PerfilClientePresenter;

import java.util.List;

public interface ClientePerfilView  extends BaseActivityView<ClientePerfilPresenter> {
    void starActivityEditarPerfil();

    void mostrarDataInicial(String usuCodigo, String usuNombre, String usuApellido, String usuFoto);

    void mostrarDatosPropuesta(String propuestaFinalizada, String propuestaPendiente, String propuestaProceso,float estrellasUsu);

    void mostrarListaComentarios(List<ComentariosUi> datosClienteList);

    void mostrarTextViewEmpty();

    void mostrarBottones();
}
