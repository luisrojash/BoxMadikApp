package com.application.boxmadikv1.dao.solucionRevocacion;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.SolucionRevocacion;
import com.application.boxmadikv1.modelo.SolucionRevocacion_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class SolucionRevocacionDaoImpl extends BaseDaoImpl<SolucionRevocacion,SolucionRevocacion_Table> implements SolucionRevocacionDao {


    private static SolucionRevocacionDaoImpl mInstance = null;

    public static final SolucionRevocacionDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new SolucionRevocacionDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<SolucionRevocacion> getEntityClass() {
        return SolucionRevocacion.class;
    }

    @Override
    protected Class<SolucionRevocacion_Table> getTableclass() {
        return SolucionRevocacion_Table.class;
    }

    @Override
    public List<SolucionRevocacion> obtenerListaRevocacionActiva(String estado) {
        return SQLite.select()
                .from(SolucionRevocacion.class)
                .where(SolucionRevocacion_Table.Srev_estado.is(estado))
                .queryList();
    }
}
