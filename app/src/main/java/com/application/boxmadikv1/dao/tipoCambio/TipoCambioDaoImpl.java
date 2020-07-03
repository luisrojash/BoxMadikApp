package com.application.boxmadikv1.dao.tipoCambio;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.TipoCambio;
import com.application.boxmadikv1.modelo.TipoCambio_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class TipoCambioDaoImpl extends BaseDaoImpl<TipoCambio, TipoCambio_Table> implements TipoCambioDao {

    private static TipoCambioDaoImpl mInstance = null;



    public static final TipoCambioDao getmInstance() {
        if (mInstance == null) {
            mInstance = new TipoCambioDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<TipoCambio> getEntityClass() {
        return TipoCambio.class;
    }

    @Override
    protected Class<TipoCambio_Table> getTableclass() {
        return TipoCambio_Table.class;
    }


    @Override
    public TipoCambio obtenerTipoCambioPorPais(String paisCodigo) {
        return SQLite.select()
                .from(TipoCambio.class)
                .where(TipoCambio_Table.pais_Pais_Codigo.is(paisCodigo))
                .querySingle();
    }
}
