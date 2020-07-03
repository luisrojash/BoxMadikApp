package com.application.boxmadikv1.dao.especialidades;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Especialidades;
import com.application.boxmadikv1.modelo.Especialidades_Table;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.Oficio_Table;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.modelo.Rubro_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class EspecialidadesDaoImpl extends BaseDaoImpl<Especialidades, Especialidades_Table> implements EspecialidadesDao {

    private static EspecialidadesDaoImpl mInstance = null;

    public static final EspecialidadesDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new EspecialidadesDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<Especialidades> getEntityClass() {
        return Especialidades.class;
    }

    @Override
    protected Class<Especialidades_Table> getTableclass() {
        return Especialidades_Table.class;
    }

    @Override
    public List<Especialidades> obtenerListaPorRubroIdAndOficioId(String rubroId,String oficioId,String codigoPais,String estado) {
        return SQLite.select()
                .from(Especialidades.class)
                .innerJoin(Oficio.class)
                .on(Especialidades_Table.oficio_Ofi_codigo.withTable().eq(Oficio_Table.Ofi_codigo.withTable()))
                .innerJoin(Rubro.class)
                .on(Especialidades_Table.oficio_rubro_Rub_Codigo.withTable().eq(Rubro_Table.Rub_Codigo.withTable()))
                .where(Especialidades_Table.oficio_rubro_Rub_Codigo.withTable().is(Integer.valueOf(rubroId)))
                .and(Especialidades_Table.oficio_Ofi_codigo.withTable().is(Integer.valueOf(oficioId)))
                .and(Especialidades_Table.pais_Pais_Codigo.withTable().is(Integer.valueOf(codigoPais)))
                .and(Especialidades_Table.Espe_Estado.withTable().is(estado))
                .queryList();
    }
}
