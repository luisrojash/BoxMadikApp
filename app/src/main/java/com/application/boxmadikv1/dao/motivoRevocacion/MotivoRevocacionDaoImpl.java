package com.application.boxmadikv1.dao.motivoRevocacion;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.MotivoRevocacion;
import com.application.boxmadikv1.modelo.MotivoRevocacion_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class MotivoRevocacionDaoImpl extends BaseDaoImpl<MotivoRevocacion, MotivoRevocacion_Table> implements MotivoRevocacionDao {

    private static MotivoRevocacionDaoImpl mInstance = null;

    public static final MotivoRevocacionDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new MotivoRevocacionDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<MotivoRevocacion> getEntityClass() {
        return MotivoRevocacion.class;
    }

    @Override
    protected Class<MotivoRevocacion_Table> getTableclass() {
        return MotivoRevocacion_Table.class;
    }

    @Override
    public List<MotivoRevocacion> obtenerListaRevocacion(String estado) {
        return SQLite.select()
                .from(MotivoRevocacion.class)
                .where(MotivoRevocacion_Table.Mrev_estado.is(estado))
                .queryList();
    }
}
