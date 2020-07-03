package com.application.boxmadikv1.dao.pais;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Pais;
import com.application.boxmadikv1.modelo.Pais_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class PaisDaoImpl extends BaseDaoImpl<Pais, Pais_Table> implements PaisDao {

    private static PaisDaoImpl mInstance = null;

    public static final PaisDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new PaisDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<Pais> getEntityClass() {
        return Pais.class;
    }

    @Override
    protected Class<Pais_Table> getTableclass() {
        return Pais_Table.class;
    }

    @Override
    public Pais obtenerPais(int paisCodigo) {
        Pais pais = SQLite.select()
                .from(Pais.class)
                .where(Pais_Table.Pais_Codigo.is(paisCodigo))
                .querySingle();
        return pais;
    }

    @Override
    public List<Pais> obtenerListaEstado(String estado) {
        List<Pais> paisList = SQLite.select()
                .from(Pais.class)
                .where(Pais_Table.Pais_estado.is(estado))
                .queryList();
        return paisList;
    }
}
