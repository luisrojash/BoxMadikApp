package com.application.boxmadikv1.dao.calidadTrabajo;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.CalidadTrabajo;
import com.application.boxmadikv1.modelo.CalidadTrabajo_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class CalidadTrabajoDaoImpl extends BaseDaoImpl<CalidadTrabajo, CalidadTrabajo_Table> implements CalidadTrabajoDao {


    private static CalidadTrabajoDaoImpl mInstance = null;

    public static final CalidadTrabajoDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new CalidadTrabajoDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<CalidadTrabajo> getEntityClass() {
        return CalidadTrabajo.class;
    }

    @Override
    protected Class<CalidadTrabajo_Table> getTableclass() {
        return CalidadTrabajo_Table.class;
    }

    @Override
    public List<CalidadTrabajo> obtenerListaCalidadaTrabajoActivo(String activo) {
        return SQLite.select()
                .from(CalidadTrabajo.class)
                .where(CalidadTrabajo_Table.Ctra_estado.is(activo))
                .queryList();
    }
}
