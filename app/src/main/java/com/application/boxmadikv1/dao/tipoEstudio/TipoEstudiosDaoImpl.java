package com.application.boxmadikv1.dao.tipoEstudio;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.TipoEstudios;
import com.application.boxmadikv1.modelo.TipoEstudios_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class TipoEstudiosDaoImpl extends BaseDaoImpl<TipoEstudios, TipoEstudios_Table> implements TipoEstudiosDao {

    private static TipoEstudiosDaoImpl mInstance = null;

    public static final TipoEstudiosDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new TipoEstudiosDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<TipoEstudios> getEntityClass() {
        return TipoEstudios.class;
    }

    @Override
    protected Class<TipoEstudios_Table> getTableclass() {
        return TipoEstudios_Table.class;
    }

    @Override
    public List<TipoEstudios> obtenerTipoEstudiosActivo(String estado) {
        return SQLite.select()
                .from(TipoEstudios.class)
                .where(TipoEstudios_Table.Test_estado.is(estado))
                .queryList();
    }
}
