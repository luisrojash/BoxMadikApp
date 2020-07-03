package com.application.boxmadikv1.dao.provincia;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Provincia;
import com.application.boxmadikv1.modelo.Provincia_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class ProvinciaDaoImpl extends BaseDaoImpl<Provincia, Provincia_Table> implements ProvinciaDao {

    private static ProvinciaDaoImpl mInstance = null;

    public static final ProvinciaDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new ProvinciaDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<Provincia> getEntityClass() {
        return Provincia.class;
    }

    @Override
    protected Class<Provincia_Table> getTableclass() {
        return Provincia_Table.class;
    }

    @Override
    public Provincia obtenerMiIdQuerySimple(int provinciaId,int paisId,int departamentoId) {
        return SQLite.select()
                .from(Provincia.class)
                .where(Provincia_Table.departamento_pais_Pais_Codigo.is(paisId))
                .and(Provincia_Table.departamento_Depa_Cod.is(departamentoId))
                .and(Provincia_Table.Prov_cod.is(provinciaId))
                .querySingle();
    }

    @Override
    public List<Provincia> obtenerProvinciaListaFiltroPaisYdepart(int paisCodigo, int departamentoCodigo) {
       /* List<Provincia> provinciaList = SQLite.select()
                .from(Provincia.class)
                .innerJoin(Departamento.class)
                .on(Departamento_Table.Depa_Cod.withTable().eq(Provincia_Table.departamento_Depa_Cod))
                .where(Departamento_Table.pais_Pais_Codigo.is(paisCodigo))
                .and(Departamento_Table.Depa_Cod.is(departamentoCodigo))
                .queryList();*/
        List<Provincia> provinciaList = SQLite.select()
                .from(Provincia.class)
                .where(Provincia_Table.departamento_pais_Pais_Codigo.is(paisCodigo))
                .and(Provincia_Table.departamento_Depa_Cod.is(departamentoCodigo))
                .queryList();
        return provinciaList;
    }
}
