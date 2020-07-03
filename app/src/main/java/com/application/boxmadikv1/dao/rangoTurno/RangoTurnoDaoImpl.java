package com.application.boxmadikv1.dao.rangoTurno;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.RangoPrecio_Table;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.modelo.RangoTurno_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class RangoTurnoDaoImpl extends BaseDaoImpl<RangoTurno, RangoTurno_Table> implements RangoTurnoDao {

    private static RangoTurnoDaoImpl mInstance;

    public static final RangoTurnoDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new RangoTurnoDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<RangoTurno> getEntityClass() {
        return RangoTurno.class;
    }

    @Override
    protected Class<RangoTurno_Table> getTableclass() {
        return RangoTurno_Table.class;
    }

    @Override
    public RangoTurno obtenerMiIdQuerySimple(int rangoTurnoId) {
        return SQLite.select()
                .from(RangoTurno.class)
                .where(RangoTurno_Table.Rat_Item.is(rangoTurnoId))
                .querySingle();
    }

    @Override
    public List<RangoTurno> obtenerListaRangoTurnoPorEsado(String estado) {
        return SQLite.select()
                .from(RangoTurno.class)
                .where(RangoTurno_Table.Rat_Estado.is(estado))
                .queryList();
    }
}
