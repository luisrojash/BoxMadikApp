package com.application.boxmadikv1.dao.distrito;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Distrito;
import com.application.boxmadikv1.modelo.Distrito_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class DistritoDaoImpl extends BaseDaoImpl<Distrito, Distrito_Table> implements DistritoDao {


    private static DistritoDaoImpl mInstance = null;

    public static final DistritoDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new DistritoDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<Distrito> getEntityClass() {
        return Distrito.class;
    }

    @Override
    protected Class<Distrito_Table> getTableclass() {
        return Distrito_Table.class;
    }

    @Override
    public Distrito obtenerMiIdQuerySimple(int distritoId,int provinciaId,int departamentoId,int paisId) {
        return SQLite.select()
                .from(Distrito.class)
                .where(Distrito_Table.provincia_departamento_pais_Pais_Codigo.is(paisId))
                .and(Distrito_Table.provincia_departamento_Depa_Cod.is(departamentoId))
                .and(Distrito_Table.provincia_Prov_cod.is(provinciaId))
                .and(Distrito_Table.Dist_cod.is(distritoId))
                .querySingle();
    }

    @Override
    public Distrito obtenerMiIdQuerySimplePorDepartamento(int departamentoId, int paisId) {
        return SQLite.select()
                .from(Distrito.class)
                .where(Distrito_Table.provincia_departamento_pais_Pais_Codigo.is(paisId))
                .and(Distrito_Table.provincia_departamento_Depa_Cod.is(departamentoId))
                .querySingle();
    }

    @Override
    public List<Distrito> obtenerDistritoListaFiltroPaisYDepartUProvi(int paisCodigo, int departamentoCodigo, int provinciaCodigo) {
        List<Distrito> distritoList = SQLite.select()
                .from(Distrito.class)
                .where(Distrito_Table.provincia_departamento_pais_Pais_Codigo.is(paisCodigo))
                .and(Distrito_Table.provincia_departamento_Depa_Cod.is(departamentoCodigo))
                .and(Distrito_Table.provincia_Prov_cod.is(provinciaCodigo))
                .queryList();
        return distritoList;
    }
}
