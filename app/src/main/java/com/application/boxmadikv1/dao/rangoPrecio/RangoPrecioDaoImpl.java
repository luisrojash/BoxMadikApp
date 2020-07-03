package com.application.boxmadikv1.dao.rangoPrecio;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.RangoDias_Table;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoPrecio_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class RangoPrecioDaoImpl extends BaseDaoImpl<RangoPrecio, RangoPrecio_Table> implements RangoPrecioDao {

    private static RangoPrecioDaoImpl mIntance = null;

    public static final RangoPrecioDaoImpl getmIntance() {
        if (mIntance == null) {
            mIntance = new RangoPrecioDaoImpl();
        }
        return mIntance;
    }

    @Override
    protected Class<RangoPrecio> getEntityClass() {
        return RangoPrecio.class;
    }

    @Override
    protected Class<RangoPrecio_Table> getTableclass() {
        return RangoPrecio_Table.class;
    }

    @Override
    public RangoPrecio obtenerMiIdQuerySimple(int rangoPrecioId) {
        return SQLite.select()
                .from(RangoPrecio.class)
                .where(RangoPrecio_Table.Ran_Item.is(rangoPrecioId))
                .querySingle();
    }

    @Override
    public List<RangoPrecio> obtenerListaRangoPrecioPorPais(String codigoPais, String estado) {
        return SQLite.select()
                .from(RangoPrecio.class)
                .where(RangoPrecio_Table.pais_Pais_Codigo.is(Integer.valueOf(codigoPais)))
                .and(RangoPrecio_Table.Ran_Estado.is(estado))
                .queryList();
    }
}

