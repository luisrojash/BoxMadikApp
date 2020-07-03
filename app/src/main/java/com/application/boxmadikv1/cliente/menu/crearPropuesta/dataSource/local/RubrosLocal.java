package com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource.local;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource.RubrosDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.OficiosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;
import com.application.boxmadikv1.dao.oficio.OficioDao;
import com.application.boxmadikv1.dao.rubro.RubroDao;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.utils.Constantes;


import java.util.ArrayList;
import java.util.List;

public class RubrosLocal implements RubrosDataSource {

    private RubroDao rubroDao;
    private OficioDao oficioDao;


    public RubrosLocal(RubroDao rubroDao, OficioDao oficioDao) {
        this.rubroDao = rubroDao;
        this.oficioDao = oficioDao;
    }


    @Override
    public void onListarRubros(String codigoPais,CallBackResultado<List<RubrosUi>> listCallBackResultado) {
       // List<Rubro> rubroList = rubroDao.getAll();

        List<Rubro> rubroList = rubroDao.obtenerListaRubroPorPais(codigoPais, Constantes.ESTADO_ACTIVO);/*List<Rubro> rubroList = SQLite.select().
                from(Rubro.class)
                .queryList();*/
        List<RubrosUi> rubrosUiList = new ArrayList<>();
        for (Rubro rubro : rubroList) {
            RubrosUi rubrosUi = new RubrosUi();
            rubrosUi.setIdRub(rubro.getRub_Codigo() + "");
            rubrosUi.setDescripcionRub(rubro.getRub_Desc());
            rubrosUi.setEstadoRub(rubro.getRub_Estado());
            rubrosUi.setImagenRub(rubro.getRub_Imagen());
            rubrosUi.setPresencialRub(rubro.getRub_presencial());
            rubrosUi.setEstadoRotar(true);
            rubrosUi.setOficiosUiList(listarOficiosRubro(rubro, rubrosUi,codigoPais));
            rubrosUiList.add(rubrosUi);
        }
        listCallBackResultado.onCalbackResultado(rubrosUiList);
    }

    private List<OficiosUi> listarOficiosRubro(Rubro rubros, RubrosUi rubrosUi,String codigoPais) {
        /*List<Oficio> oficioList = SQLite.select().
                from(Oficio.class)
                .where(Oficio_Table.rubro_Rub_Codigo.withTable().is(rubros.getRub_Codigo()))
                .queryList();*/
        List<Oficio> oficioList = oficioDao.obtenerListaOficioPorRubroCodigo(String.valueOf(rubros.getRub_Codigo()),codigoPais,Constantes.ESTADO_ACTIVO);
        List<OficiosUi> oficiosUiList = new ArrayList<>();
        for (Oficio oficio : oficioList) {
            OficiosUi oficiosUi = new OficiosUi();
            oficiosUi.setIdOficio(oficio.getOfi_codigo() + "");
            oficiosUi.setDescripcion(oficio.getOfi_desc());
            oficiosUi.setEstado(oficio.getOfi_Estado());
            oficiosUi.setRubrosUi(rubrosUi);
            oficiosUiList.add(oficiosUi);
        }
        return oficiosUiList;
    }
}
