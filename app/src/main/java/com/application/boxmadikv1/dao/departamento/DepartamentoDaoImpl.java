package com.application.boxmadikv1.dao.departamento;

import android.util.Log;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Departamento;
import com.application.boxmadikv1.modelo.Departamento_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class DepartamentoDaoImpl extends BaseDaoImpl<Departamento, Departamento_Table> implements DepartamentoDao {


    private static DepartamentoDaoImpl mInstance = null;

    public static final DepartamentoDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new DepartamentoDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<Departamento> getEntityClass() {
        return Departamento.class;
    }

    @Override
    protected Class<Departamento_Table> getTableclass() {
        return Departamento_Table.class;
    }

    @Override
    public Departamento obtenerMiIdQuerySimple(int departamentoId,int paisId) {
        Log.d(TAG, "obtenerMiIdQuerySimple " + departamentoId);
        Departamento departamento = SQLite.select()
                .from(Departamento.class)
                .where(Departamento_Table.pais_Pais_Codigo.is(paisId))
                .and(Departamento_Table.Depa_Cod.is(departamentoId))
                .querySingle();
        return departamento;
    }

    @Override
    public List<Departamento> departamentoPorPais(int paisCodigo) {
        List<Departamento> departamentoList = SQLite.select()
                .from(Departamento.class)
                .where(Departamento_Table.pais_Pais_Codigo.is(paisCodigo))
                .queryList();
        return departamentoList;
    }
}
