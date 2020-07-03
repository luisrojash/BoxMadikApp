package com.application.boxmadikv1.dao.rangoDias;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoDias_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class RangoDiasDaoImpl extends BaseDaoImpl<RangoDias, RangoDias_Table> implements RangoDiasDao {

    private static RangoDiasDaoImpl mInstance = null;


    public static final RangoDiasDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new RangoDiasDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<RangoDias> getEntityClass() {
        return RangoDias.class;
    }

    @Override
    protected Class<RangoDias_Table> getTableclass() {
        return RangoDias_Table.class;
    }

    @Override
    public RangoDias obtenerMiIdQuerySimple(int rangoDiasId) {
        return SQLite.select()
                .from(RangoDias.class)
                .where(RangoDias_Table.Rad_Item.is(rangoDiasId))
                .querySingle();
    }

    @Override
    public List<RangoDias> obtenerListaRangoDiasEstado(String estado) {
        return SQLite.select()
                .from(RangoDias.class)
                .where(RangoDias_Table.Rad_Estado.is(estado))
                .queryList();
    }
}
