package com.application.boxmadikv1.dao.comision;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.BoxMadik_Comision;
import com.application.boxmadikv1.modelo.BoxMadik_Comision_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class ComisionDaoImpl extends BaseDaoImpl<BoxMadik_Comision, BoxMadik_Comision_Table> implements ComisionDao {

    private static ComisionDaoImpl mInstance = null;

    public static final ComisionDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new ComisionDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<BoxMadik_Comision> getEntityClass() {
        return BoxMadik_Comision.class;
    }

    @Override
    protected Class<BoxMadik_Comision_Table> getTableclass() {
        return BoxMadik_Comision_Table.class;
    }

    @Override
    public BoxMadik_Comision obtenerMiIdQuerySimple(int comisionId) {
           return SQLite.select()
                .from(BoxMadik_Comision.class)
                .where(BoxMadik_Comision_Table.Boxc_item.is(comisionId))
                .querySingle();
    }
}
