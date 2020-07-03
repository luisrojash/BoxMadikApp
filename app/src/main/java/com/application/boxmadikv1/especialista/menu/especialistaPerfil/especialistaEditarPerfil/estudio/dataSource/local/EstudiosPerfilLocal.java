package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.local;

import com.application.boxmadikv1.dao.tipoCentroEstudios.TipoCentroEstudiosDao;
import com.application.boxmadikv1.dao.tipoEstudio.TipoEstudiosDao;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.EstudioPerfilDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;
import com.application.boxmadikv1.modelo.CentroEstudios;
import com.application.boxmadikv1.modelo.TipoEstudios;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class EstudiosPerfilLocal implements EstudioPerfilDataSource {

    private TipoEstudiosDao tipoEstudiosDao;
    private TipoCentroEstudiosDao tipoCentroEstudiosDao;

    public EstudiosPerfilLocal(TipoEstudiosDao tipoEstudiosDao, TipoCentroEstudiosDao tipoCentroEstudiosDao) {
        this.tipoEstudiosDao = tipoEstudiosDao;
        this.tipoCentroEstudiosDao = tipoCentroEstudiosDao;
    }

    @Override
    public void onListarTipoEstudios(CallBackResultado<List<TipoEstudiosUi>> listCallBackResultado) {
        List<TipoEstudios> tipoEstudiosList = tipoEstudiosDao.obtenerTipoEstudiosActivo(Constantes.ESTADO_ACTIVO);
        List<TipoEstudiosUi> tipoEstudiosUis = new ArrayList<>();
        for (TipoEstudios tipoEstudios : tipoEstudiosList) {
            TipoEstudiosUi tipoEstudiosUi = new TipoEstudiosUi();
            tipoEstudiosUi.setId(String.valueOf(tipoEstudios.getTest_Codigo()));
            tipoEstudiosUi.setDescripcion(tipoEstudios.getTest_descripcion());
            tipoEstudiosUis.add(tipoEstudiosUi);
        }
        listCallBackResultado.onCallBackResultado(tipoEstudiosUis);
    }

    @Override
    public void onListarTipoCentroEstudios(String paisCodigo,CallBackResultado<List<TipoCentroEstudiosUi>> listCallBackResultado) {
        List<CentroEstudios> centroEstudiosList = tipoCentroEstudiosDao.obtenerCentroEstudiosActivo(paisCodigo,Constantes.ESTADO_ACTIVO);
        List<TipoCentroEstudiosUi> tipoCentroEstudiosUis = new ArrayList<>();
        for (CentroEstudios centroEstudios : centroEstudiosList) {
            TipoCentroEstudiosUi tipoCentroEstudiosUi = new TipoCentroEstudiosUi();
            tipoCentroEstudiosUi.setId(String.valueOf(centroEstudios.getCest_Codigo()));
            tipoCentroEstudiosUi.setDescripcion(centroEstudios.getCest_descripcion());
            tipoCentroEstudiosUis.add(tipoCentroEstudiosUi);
        }
        listCallBackResultado.onCallBackResultado(tipoCentroEstudiosUis);
    }
}
