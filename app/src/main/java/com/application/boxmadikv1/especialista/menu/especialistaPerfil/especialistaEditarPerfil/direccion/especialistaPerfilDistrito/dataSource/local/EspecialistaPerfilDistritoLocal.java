package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.dao.departamento.DepartamentoDao;
import com.application.boxmadikv1.dao.distrito.DistritoDao;
import com.application.boxmadikv1.dao.provincia.ProvinciaDao;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.EspecialistaPerfilDistritoDataSource;
import com.application.boxmadikv1.modelo.Departamento;
import com.application.boxmadikv1.modelo.Distrito;
import com.application.boxmadikv1.modelo.Provincia;

import java.util.ArrayList;
import java.util.List;

public class EspecialistaPerfilDistritoLocal implements EspecialistaPerfilDistritoDataSource {

    private DepartamentoDao departamentoDao;
    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;

    public EspecialistaPerfilDistritoLocal(DepartamentoDao departamentoDao, ProvinciaDao provinciaDao, DistritoDao distritoDao) {
        this.departamentoDao = departamentoDao;
        this.provinciaDao = provinciaDao;
        this.distritoDao = distritoDao;
    }


    @Override
    public void onListarDepartamento(String codigoPais, CallBackResultado<List<TipoDepartamentoUi>> listCallBackResultado) {
        int paisCodigo = 51;
        List<Departamento> departamento = departamentoDao.departamentoPorPais(paisCodigo);

        if (departamento == null) return;
        List<TipoDepartamentoUi> tipoDepartamentoUis = new ArrayList<>();
        for (Departamento departamento1 : departamento) {
            TipoDepartamentoUi tipoDepartamentoUi1 = new TipoDepartamentoUi();
            tipoDepartamentoUi1.setId(departamento1.getDepa_Cod() + "");
            tipoDepartamentoUi1.setDescripcion(departamento1.getDepa_Desc());
            tipoDepartamentoUis.add(tipoDepartamentoUi1);
        }
        listCallBackResultado.onCallBackResultado(tipoDepartamentoUis);
    }

    @Override
    public void onListarProvincia(String paisCodigo, String departamentoCodigo, CallBackResultado<List<TipoProvinciaUi>> listCallBackResultado) {
        int paisCod = Integer.parseInt(paisCodigo);
        int departmentCod = Integer.parseInt(departamentoCodigo);
        List<TipoProvinciaUi> tipoProvinciaUis = new ArrayList<>();
        List<Provincia> provinciaList = provinciaDao.obtenerProvinciaListaFiltroPaisYdepart(paisCod, departmentCod);
        if (provinciaList == null) return;
        for (Provincia provincia1 : provinciaList) {
            TipoProvinciaUi tipoProvinciaUi1 = new TipoProvinciaUi();
            tipoProvinciaUi1.setId(provincia1.getProv_cod() + "");
            tipoProvinciaUi1.setDescripcion(provincia1.getProv_Desc());
            tipoProvinciaUis.add(tipoProvinciaUi1);
        }
        listCallBackResultado.onCallBackResultado(tipoProvinciaUis);
    }

    @Override
    public void onListarDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo, CallBackResultado<List<TipoDistritoUi>> listCallBackResultado) {

        int paisCod = Integer.parseInt(paisCodigo);
        int departmentCod = Integer.parseInt(departamentoCodigo);
        int provinCod = Integer.parseInt(provinciaCodigo);
        List<Distrito> distritoList = distritoDao.obtenerDistritoListaFiltroPaisYDepartUProvi(paisCod,departmentCod,provinCod);
        List<TipoDistritoUi> tipoDistritoUis = new ArrayList<>();
        for (Distrito distrito1 : distritoList) {
            TipoDistritoUi tipoDistritoUi1 = new TipoDistritoUi();
            tipoDistritoUi1.setId(distrito1.getDist_cod() + "");
            tipoDistritoUi1.setDescripcion(distrito1.getDist_Desc());
            tipoDistritoUis.add(tipoDistritoUi1);
        }
        listCallBackResultado.onCallBackResultado(tipoDistritoUis);

    }
}
