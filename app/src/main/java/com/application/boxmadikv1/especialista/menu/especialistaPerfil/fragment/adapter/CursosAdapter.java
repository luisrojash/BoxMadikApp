package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter;


public class CursosAdapter {
        /*extends AbstractTableAdapter<ColumnaCabeceraCursos, FilaCabeceraCursos, CeldasCursos> {


    public static final String TAG = CursosAdapter.class.getSimpleName();


    public final int COLUMNA_CURSOS_CABECERA = 1;
    public final int FILA_TIPO_VALIDACION = 2;
    public final int CELDAS_CURSOS_RESULTADO = 3;

    private final LayoutInflater mInflater;

    public CursosAdapter(Context context) {
        super(context);
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        ColumnaCabeceraCursos columnaCabecera = mColumnHeaderItems.get(position);
        // Log.d(TAG, "mColumnHeaderItems : " + mColumnHeaderItems.size());
        if (columnaCabecera instanceof TipoCabeceraCursosUi) {
            TipoCabeceraCursosUi tipoCabeceraCursosUi = (TipoCabeceraCursosUi) columnaCabecera;
            Log.d(TAG, "tipoCabeceraCursosUi : " + tipoCabeceraCursosUi.getDescripcion());
            if (tipoCabeceraCursosUi == null) return 0;
            return COLUMNA_CURSOS_CABECERA;
        }
        return 0;

    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        FilaCabeceraCursos filaCabecera = mRowHeaderItems.get(position);
        if (filaCabecera == null) return 0;
        if (filaCabecera instanceof TipoFilaValidacionCursosUi) {
            return FILA_TIPO_VALIDACION;
        }
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        int cantidad = mCellItems.size();
        if (cantidad != 0) {
            CeldasCursos cell = mCellItems.get(0).get(position);
            if (cell instanceof CeldasResutadoUi) {
                Log.d(TAG, "getCellItemViewType : CELDAS_CURSOS_RESULTADO " );
                return CELDAS_CURSOS_RESULTADO;
            }
        }
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case CELDAS_CURSOS_RESULTADO:
                layout = mInflater.inflate(R.layout.tabla_celdas_cursos_resultado, parent, false);
                Log.d(TAG, "onCreateCellViewHolder : CELDAS_CURSOS_RESULTADO " );
                return new CeldasCursosResultadoHolder(layout);
        }
        return null;
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel,
                                     int columnPosition, int rowPosition) {
        if (holder instanceof CeldasCursosResultadoHolder && cellItemModel instanceof CeldasResutadoUi) {
            CeldasResutadoUi celdasResutadoUi = (CeldasResutadoUi) cellItemModel;
            CeldasCursosResultadoHolder puntualHolder = (CeldasCursosResultadoHolder) holder;
            puntualHolder.bind(celdasResutadoUi);
        }

    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case COLUMNA_CURSOS_CABECERA:
                layout = mInflater.inflate(R.layout.tabla_columnas_cursos_cabecera, parent, false);
                return new ColumnaTipoCabeceraHolder(layout);
        }
        return null;
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object
            columnHeaderItemModel, int columnPosition) {
        if (holder instanceof ColumnaTipoCabeceraHolder && columnHeaderItemModel instanceof TipoCabeceraCursosUi) {
            TipoCabeceraCursosUi tipoCabeceraCursosUi = (TipoCabeceraCursosUi) columnHeaderItemModel;
            ColumnaTipoCabeceraHolder tipoCabeceraHolder = (ColumnaTipoCabeceraHolder) holder;
            tipoCabeceraHolder.bind(tipoCabeceraCursosUi);
        }
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch (viewType) {
            case FILA_TIPO_VALIDACION:
                layout = mInflater.inflate(R.layout.tabla_filas_resultado, parent, false);
                return new FilasTipoValidacionHolder(layout);
        }
        return null;
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel,
                                          int rowPosition) {

        if (holder instanceof FilasTipoValidacionHolder && rowHeaderItemModel instanceof TipoFilaValidacionCursosUi) {
            TipoFilaValidacionCursosUi filaValidacionCursosUi = (TipoFilaValidacionCursosUi) rowHeaderItemModel;
            FilasTipoValidacionHolder filasTipoValidacionHolder = (FilasTipoValidacionHolder) holder;
            filasTipoValidacionHolder.bind(filaValidacionCursosUi);
        }

    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.tabla_vista_disenio_esquina_tipo, null, false);
    }*/
}
