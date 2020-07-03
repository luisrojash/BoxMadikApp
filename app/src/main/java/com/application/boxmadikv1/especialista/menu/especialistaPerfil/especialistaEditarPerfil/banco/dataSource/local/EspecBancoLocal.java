package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.local;

import com.application.boxmadikv1.dao.banco.BancoDao;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.EspecBancoDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.entidad.TipoBancoUi;
import com.application.boxmadikv1.modelo.Banco;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class EspecBancoLocal implements EspecBancoDataSource {

    private BancoDao bancoDao;

    public EspecBancoLocal(BancoDao bancoDao) {
        this.bancoDao = bancoDao;
    }

    @Override
    public void onMostrarListaBanco(String paisCodigo, CallBackResultado<List<TipoBancoUi>> listCallBackResultado) {
      //  List<Banco> bancos = bancoDao.getAll();
        List<Banco> bancos = bancoDao.obtenerBancoListaActivo(paisCodigo, Constantes.ESTADO_ACTIVO);
        List<TipoBancoUi> tipoBancoUis = new ArrayList<>();
        TipoBancoUi tipoBancoUiOp = new TipoBancoUi();
        tipoBancoUiOp.setId(String.valueOf(0));
        tipoBancoUiOp.setDescripcion("Seleccione Tipo Banco");
        tipoBancoUis.add(tipoBancoUiOp);
        for (Banco banco
                : bancos) {
            TipoBancoUi tipoBancoUi = new TipoBancoUi();
            tipoBancoUi.setId(String.valueOf(banco.getBan_Codigo()));
            tipoBancoUi.setDescripcion(banco.getBan_Desc());
            tipoBancoUis.add(tipoBancoUi);
        }
        listCallBackResultado.onCallBackResultado(tipoBancoUis);
    }
}
