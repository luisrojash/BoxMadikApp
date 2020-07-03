package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.UbicacionDataSource;
import com.application.boxmadikv1.dao.departamento.DepartamentoDao;
import com.application.boxmadikv1.dao.distrito.DistritoDao;
import com.application.boxmadikv1.dao.provincia.ProvinciaDao;
import com.application.boxmadikv1.modelo.Departamento;
import com.application.boxmadikv1.modelo.Distrito;
import com.application.boxmadikv1.modelo.Provincia;

import java.util.ArrayList;
import java.util.List;

public class UbicacionLocal implements UbicacionDataSource {
    private DepartamentoDao departamentoDao;
    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;

    public UbicacionLocal(DepartamentoDao departamentoDao, ProvinciaDao provinciaDao, DistritoDao distritoDao) {
        this.departamentoDao = departamentoDao;
        this.provinciaDao = provinciaDao;
        this.distritoDao = distritoDao;
    }

    @Override
    public void onListarDepartamento(String codigoPais, CallBackResultado<List<TipoDepartamentoUi>> listCallBackResultado) {
        List<Departamento> departamentoList = departamentoDao.departamentoPorPais(Integer.parseInt(codigoPais));

        if (departamentoList == null) return;
        List<TipoDepartamentoUi> tipoDepartamentoUis = new ArrayList<>();
        for (Departamento departamento1 : departamentoList) {
            TipoDepartamentoUi tipoDepartamentoUi1 = new TipoDepartamentoUi();
            tipoDepartamentoUi1.setId(departamento1.getDepa_Cod() + "");
            tipoDepartamentoUi1.setDescripcion(departamento1.getDepa_Desc());
            tipoDepartamentoUis.add(tipoDepartamentoUi1);
        }
        listCallBackResultado.onCallBackResultado(tipoDepartamentoUis);
    }

    @Override
    public void onListarProvincia(String paisCodigo, String departamentoCodigo, CallBackResultado<List<TipoProvinciaUi>> listCallBackResultado) {
        int paisSt= Integer.parseInt(paisCodigo);
        int depat= Integer.parseInt(departamentoCodigo);
        List<Provincia> provinciaList = provinciaDao.obtenerProvinciaListaFiltroPaisYdepart(paisSt,depat);
        List<TipoProvinciaUi> tipoProvinciaUis = new ArrayList<>();
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
        int paisSt= Integer.parseInt(paisCodigo);
        int depat= Integer.parseInt(departamentoCodigo);
        int proviT = Integer.parseInt(provinciaCodigo);
        List<Distrito> distritoList = distritoDao.obtenerDistritoListaFiltroPaisYDepartUProvi(paisSt,depat,proviT);
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
