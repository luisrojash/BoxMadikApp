package com.application.boxmadikv1.dao.oficio;

import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.Oficio_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class OficioDaoImpl extends BaseDaoImpl<Oficio, Oficio_Table> implements OficioDao {

    private static OficioDaoImpl mInstance = null;

    public OficioDaoImpl() {
    }

    public static final OficioDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new OficioDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<Oficio> getEntityClass() {
        return Oficio.class;
    }

    @Override
    protected Class<Oficio_Table> getTableclass() {
        return Oficio_Table.class;
    }

    @Override
    public Oficio obtenerMiIdQuerySimple(int oficioId) {
        return SQLite.select()
                .from(Oficio.class)
                .where(Oficio_Table.Ofi_codigo.is(oficioId))
                .querySingle();
    }

    @Override
    public List<Oficio> obtenerListaOficioPorRubroCodigo(String rubro_Rub_Codigo,String codigoPais,String oficioEstado) {
        return SQLite.select().
                from(Oficio.class)
                .where(Oficio_Table.rubro_Rub_Codigo.withTable().is(Integer.valueOf(rubro_Rub_Codigo)))
                .and(Oficio_Table.pais_Pais_Codigo.is(Integer.valueOf(codigoPais)))
                .and(Oficio_Table.Ofi_Estado.is(oficioEstado))
                .queryList();
    }
}
