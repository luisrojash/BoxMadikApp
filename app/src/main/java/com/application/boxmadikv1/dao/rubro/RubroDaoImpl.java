package com.application.boxmadikv1.dao.rubro;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.modelo.Rubro_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class RubroDaoImpl extends BaseDaoImpl<Rubro, Rubro_Table> implements RubroDao {


    private static RubroDaoImpl mInstance = null;

    public RubroDaoImpl() {
    }

    public static final RubroDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new RubroDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<Rubro> getEntityClass() {
        return Rubro.class;
    }

    @Override
    protected Class<Rubro_Table> getTableclass() {
        return Rubro_Table.class;
    }

    @Override
    public Rubro obtenerMiIdQuerySimple(int rubroId) {
        return SQLite.select().
                from(Rubro.class)
                .where(Rubro_Table.Rub_Codigo.is(rubroId))
                .querySingle();
    }

    @Override
    public List<Rubro> obtenerListaRubroPorPais(String paisCodigo, String rubroEstado) {
        return SQLite.select()
                .from(Rubro.class)
                .where(Rubro_Table.pais_Pais_Codigo.is(paisCodigo))
                .and(Rubro_Table.Rub_Estado.is(rubroEstado))
                .queryList();
    }
}
