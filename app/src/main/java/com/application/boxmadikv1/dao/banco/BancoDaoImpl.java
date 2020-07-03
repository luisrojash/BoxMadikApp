package com.application.boxmadikv1.dao.banco;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Banco;
import com.application.boxmadikv1.modelo.Banco_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class BancoDaoImpl extends BaseDaoImpl<Banco, Banco_Table> implements BancoDao {

    private static BancoDaoImpl mInstance = null;

    public static final BancoDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new BancoDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<Banco> getEntityClass() {
        return Banco.class;
    }

    @Override
    protected Class<Banco_Table> getTableclass() {
        return Banco_Table.class;
    }

    @Override
    public List<Banco> obtenerBancoListaActivo(String codigoPais, String tipoEstado) {
        return SQLite.select()
                .from(Banco.class)
                .where(Banco_Table.pais_Pais_Codigo.is(Integer.valueOf(codigoPais)))
                .and(Banco_Table.Ban_Estado.is(tipoEstado))
                .queryList();
    }
}
