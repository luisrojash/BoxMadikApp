package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource.local;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource.ClientePerfilCuentaBancariaDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.entidadUi.TipoBancoUi;
import com.application.boxmadikv1.dao.banco.BancoDao;
import com.application.boxmadikv1.modelo.Banco;

import java.util.ArrayList;
import java.util.List;

public class ClientePerfilCuentaBancariaLocal implements ClientePerfilCuentaBancariaDataSource{


    private BancoDao bancoDao;

    public ClientePerfilCuentaBancariaLocal(BancoDao bancoDao) {
        this.bancoDao = bancoDao;

    }
    @Override
    public void onListarBanco(CallBackResultado callBackResultado) {

        List<Banco> bancos = bancoDao.getAll();
        List<TipoBancoUi> tipoBancoUis = new ArrayList<>();
        TipoBancoUi tipoBancoUi=new TipoBancoUi();
        tipoBancoUi.setId("0");
        tipoBancoUi.setDescripcion("SELECCIONE BANCO");
        tipoBancoUis.add(tipoBancoUi);
        for (Banco banco1 : bancos) {
            TipoBancoUi tipoBancoUi1=new TipoBancoUi();
            tipoBancoUi1.setId(banco1.getBan_Codigo()+"");
            tipoBancoUi1.setDescripcion(banco1.getBan_Desc());
            tipoBancoUis.add(tipoBancoUi1);
        }
        callBackResultado.onResultado(tipoBancoUis);
    }
}
