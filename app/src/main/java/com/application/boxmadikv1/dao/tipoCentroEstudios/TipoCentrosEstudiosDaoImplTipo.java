package com.application.boxmadikv1.dao.tipoCentroEstudios;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.CentroEstudios;
import com.application.boxmadikv1.modelo.CentroEstudios_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class TipoCentrosEstudiosDaoImplTipo extends BaseDaoImpl<CentroEstudios, CentroEstudios_Table> implements TipoCentroEstudiosDao {

    private static TipoCentrosEstudiosDaoImplTipo mInstance = null;

    public static final TipoCentrosEstudiosDaoImplTipo getmInstance() {
        if (mInstance == null) {
            mInstance = new TipoCentrosEstudiosDaoImplTipo();
        }
        return mInstance;
    }


    @Override
    protected Class<CentroEstudios> getEntityClass() {
        return CentroEstudios.class;
    }

    @Override
    protected Class<CentroEstudios_Table> getTableclass() {
        return CentroEstudios_Table.class;
    }

    @Override
    public List<CentroEstudios> obtenerCentroEstudiosActivo(String paisCodigo, String estado) {
        return SQLite.select()
                .from(CentroEstudios.class)
                .where(CentroEstudios_Table.Cest_estado.is(estado))
                .and(CentroEstudios_Table.Pais_Codigo.is(paisCodigo))
                .queryList();
    }
}
