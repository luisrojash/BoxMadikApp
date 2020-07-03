package com.application.boxmadikv1.dao.tipoRevocacionPropuesta;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.PropuestaRevocacion;
import com.application.boxmadikv1.modelo.PropuestaRevocacion_Table;

public class TipoRevocaPropuestaDaoImpl extends BaseDaoImpl<PropuestaRevocacion, PropuestaRevocacion_Table> implements TipoRevocaPropuestaDao {


    private static TipoRevocaPropuestaDaoImpl mInstance = null;

    public static final TipoRevocaPropuestaDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new TipoRevocaPropuestaDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<PropuestaRevocacion> getEntityClass() {
        return PropuestaRevocacion.class;
    }

    @Override
    protected Class<PropuestaRevocacion_Table> getTableclass() {
        return PropuestaRevocacion_Table.class;
    }
}
