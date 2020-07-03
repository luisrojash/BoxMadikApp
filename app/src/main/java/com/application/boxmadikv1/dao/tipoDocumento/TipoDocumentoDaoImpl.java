package com.application.boxmadikv1.dao.tipoDocumento;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.TipoDocumento;
import com.application.boxmadikv1.modelo.TipoDocumento_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class TipoDocumentoDaoImpl extends BaseDaoImpl<TipoDocumento, TipoDocumento_Table> implements TipoDocumentoDao {

    private static TipoDocumentoDaoImpl mInstance = null;

    public static final TipoDocumentoDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new TipoDocumentoDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<TipoDocumento> getEntityClass() {
        return TipoDocumento.class;
    }

    @Override
    protected Class<TipoDocumento_Table> getTableclass() {
        return TipoDocumento_Table.class;
    }

    @Override
    public TipoDocumento obtenerTipoDocumento(int codigoTipoDocumento) {
        TipoDocumento tipoDocumento = SQLite.select()
                .from(TipoDocumento.class)
                .where(TipoDocumento_Table.TDoc_Codigo.is(codigoTipoDocumento))
                .querySingle();
        return tipoDocumento;
    }

    @Override
    public List<TipoDocumento> obtenerListaTipoDocumentoPorPais(String paisCodigo, String documentoEstado) {
        return SQLite.select()
                .from(TipoDocumento.class)
                .where(TipoDocumento_Table.Pais_Codigo.is(paisCodigo))
                .and(TipoDocumento_Table.TDoc_Estado.is(documentoEstado))
                .queryList();
    }
}