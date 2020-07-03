package com.application.boxmadikv1.especialista.menu.edicionDatos.dataSource.local;

import com.application.boxmadikv1.dao.departamento.DepartamentoDao;
import com.application.boxmadikv1.dao.distrito.DistritoDao;
import com.application.boxmadikv1.dao.provincia.ProvinciaDao;
import com.application.boxmadikv1.especialista.menu.edicionDatos.dataSource.DatosEspecDataSource;
import com.application.boxmadikv1.modelo.Departamento;
import com.application.boxmadikv1.modelo.Distrito;
import com.application.boxmadikv1.modelo.Provincia;

public class DatosEspecLocal implements DatosEspecDataSource {

    private DepartamentoDao departamentoDao;
    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;

    public DatosEspecLocal(DepartamentoDao departamentoDao, ProvinciaDao provinciaDao, DistritoDao distritoDao) {
        this.departamentoDao = departamentoDao;
        this.provinciaDao = provinciaDao;
        this.distritoDao = distritoDao;
    }

    @Override
    public void onObtenerDireccion(String codigoPais, String codigoDepartamento, String codigoProvincia, String codigoDistrito, CallbackResultadoDireccion callbackResultadoDireccion) {
        Departamento departamento = departamentoDao.obtenerMiIdQuerySimple(Integer.valueOf(codigoDepartamento), Integer.parseInt(codigoPais));

        Provincia provincia = provinciaDao.obtenerMiIdQuerySimple(Integer.valueOf(codigoProvincia), Integer.valueOf(codigoPais), departamento.getDepa_Cod());

        Distrito distrito = distritoDao.obtenerMiIdQuerySimple(Integer.valueOf(codigoDistrito),
                provincia.getProv_cod(),
                departamento.getDepa_Cod(),
                Integer.valueOf(codigoPais));

        String codeDepart = String.valueOf(departamento.getDepa_Cod());
        String codeProv = String.valueOf(provincia.getProv_cod());
        String codeDist = String.valueOf(distrito.getDist_cod());

        callbackResultadoDireccion.onCallbackDireccion(codeDepart, codeProv, codeDist,
                departamento.getDepa_Desc(),
                provincia.getProv_Desc(),
                distrito.getDist_Desc());
    }
}
