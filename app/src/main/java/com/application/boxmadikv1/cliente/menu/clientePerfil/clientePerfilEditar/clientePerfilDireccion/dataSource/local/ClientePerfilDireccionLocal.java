package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;
import com.application.boxmadikv1.dao.departamento.DepartamentoDao;
import com.application.boxmadikv1.dao.distrito.DistritoDao;
import com.application.boxmadikv1.dao.provincia.ProvinciaDao;
import com.application.boxmadikv1.modelo.Departamento;
import com.application.boxmadikv1.modelo.Distrito;
import com.application.boxmadikv1.modelo.Provincia;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

public class ClientePerfilDireccionLocal implements ClientePerfilDireccionDataSource {


    public static final String TAG = ClientePerfilDireccionLocal.class.getSimpleName();
    private DepartamentoDao departamentoDao;
    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;

    public ClientePerfilDireccionLocal(DepartamentoDao departamentoDao, ProvinciaDao provinciaDao, DistritoDao distritoDao) {
        this.departamentoDao = departamentoDao;
        this.provinciaDao = provinciaDao;
        this.distritoDao = distritoDao;
    }


    @Override
    public void onListarDepartamento(CallBackResultado callBackResultado) {
       /* List<Departamento> departamento = departamentoDao.getAll();
        List<TipoDepartamentoUi> tipoDepartamentoUis = new ArrayList<>();
        TipoDepartamentoUi tipoDepartamentoUi=new TipoDepartamentoUi();
        tipoDepartamentoUi.setId("0");
        tipoDepartamentoUi.setDescripcion("SELECCIONE DEPARTAMENTO");
        tipoDepartamentoUis.add(tipoDepartamentoUi);
        for (Departamento departamento1 : departamento) {
            TipoDepartamentoUi tipoDepartamentoUi1=new TipoDepartamentoUi();
            tipoDepartamentoUi1.setId(departamento1.getDepa_Cod()+"");
            tipoDepartamentoUi1.setDescripcion(departamento1.getDepa_Desc());
            tipoDepartamentoUis.add(tipoDepartamentoUi1);
        }
        callBackResultado.onResultado(tipoDepartamentoUis);*/

        int paisCodigo = 51;
        List<Departamento> departamento = departamentoDao.departamentoPorPais(paisCodigo);
        Log.d(TAG, "LocalDeparmentos : " + departamento.size());
        if (departamento == null) return;
        List<TipoDepartamentoUi> tipoDepartamentoUis = new ArrayList<>();
        for (Departamento departamento1 : departamento) {
            TipoDepartamentoUi tipoDepartamentoUi1 = new TipoDepartamentoUi();
            tipoDepartamentoUi1.setId(departamento1.getDepa_Cod() + "");
            tipoDepartamentoUi1.setDescripcion(departamento1.getDepa_Desc());
            tipoDepartamentoUis.add(tipoDepartamentoUi1);
        }
        callBackResultado.onResultado(tipoDepartamentoUis);


    }

    @Override
    public void onListarProvincia(String paisCodigo, String departamentoCodigo, CallBackResultadoProvincia callBackResultadoProvincia) {

       /* List<Provincia> provincias = provinciaDao.getAll();
        List<TipoProvinciaUi> tipoProvinciaUis = new ArrayList<>();
        TipoProvinciaUi tipoProvinciaUi = new TipoProvinciaUi();
        tipoProvinciaUi.setId("0");
        tipoProvinciaUi.setDescripcion("SELECCIONE PROVINCIA");
        tipoProvinciaUis.add(tipoProvinciaUi);
        for (Provincia provincia1 : provincias) {
            TipoProvinciaUi tipoProvinciaUi1 = new TipoProvinciaUi();
            tipoProvinciaUi1.setId(provincia1.getProv_cod() + "");
            tipoProvinciaUi1.setDescripcion(provincia1.getProv_Desc());
            tipoProvinciaUis.add(tipoProvinciaUi1);
        }
        callBackResultadoProvincia.onResultado(tipoProvinciaUis);*/
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
        callBackResultadoProvincia.onResultado(tipoProvinciaUis);

    }

    @Override
    public void onListarDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo, CallBackResultadoDistrito callBackResultadoDistrito) {

        /*List<Distrito> distritos = distritoDao.getAll();
        List<TipoDistritoUi> tipoDistritoUis = new ArrayList<>();
        TipoDistritoUi tipoDistritoUi = new TipoDistritoUi();
        tipoDistritoUi.setId("0");
        tipoDistritoUi.setDescripcion("SELECCIONE DISTRITO");
        tipoDistritoUis.add(tipoDistritoUi);
        for (Distrito distrito1 : distritos) {
            TipoDistritoUi tipoDistritoUi1 = new TipoDistritoUi();
            tipoDistritoUi1.setId(distrito1.getDist_cod() + "");
            tipoDistritoUi1.setDescripcion(distrito1.getDist_Desc());
            tipoDistritoUis.add(tipoDistritoUi1);
        }
        callBackResultadoDistrito.onResultado(tipoDistritoUis);*/

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
        callBackResultadoDistrito.onResultado(tipoDistritoUis);

    }

    @Override
    public void onGuardarDatosEditados(RequestBody requestKeyUser, RequestBody requestBodyEstado, RequestBody requestFile, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyCelular, RequestBody requestBodyCodigoDepartamento, RequestBody requestBodyCodigoProvincia, RequestBody requestBodyCodigoDistrito, RequestBody requestBodyLatitud, RequestBody requestBodyLongitud, RequestBody requestBodyDireccion, DatosPerfilDataSource.CallBackResultado<EditarPerfilResponse> defaultResponseCallBackResultado) {

    }


}
