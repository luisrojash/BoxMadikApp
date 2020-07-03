package com.application.boxmadikv1.splash.asyntask;

import android.os.AsyncTask;
import android.util.Log;

import com.application.boxmadikv1.BoxDB;
import com.application.boxmadikv1.api.response.DatosInicialResponse;
import com.application.boxmadikv1.modelo.Banco;
import com.application.boxmadikv1.modelo.BoxMadik_Comision;
import com.application.boxmadikv1.modelo.CalidadTrabajo;
import com.application.boxmadikv1.modelo.CentroEstudios;
import com.application.boxmadikv1.modelo.Departamento;
import com.application.boxmadikv1.modelo.Distrito;
import com.application.boxmadikv1.modelo.Especialidades;
import com.application.boxmadikv1.modelo.MotivoRevocacion;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.Pais;
import com.application.boxmadikv1.modelo.PropuestaRevocacion;
import com.application.boxmadikv1.modelo.Provincia;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.modelo.SolucionRevocacion;
import com.application.boxmadikv1.modelo.TipoCambio;
import com.application.boxmadikv1.modelo.TipoDocumento;
import com.application.boxmadikv1.modelo.TipoEstudios;
import com.application.boxmadikv1.modelo.TipoMoneda;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.List;

public class DatosInicialesAsyntask extends AsyncTask<DatosInicialResponse, String, DatosInicialesAsyntask.DatosInicialesObject> {

    public static final int IMPORT_DATA_ERROR_RED = 1;
    public static final int IMPORT_DATA_CORRECTO = 2;
    public static final String TAG = DatosInicialesAsyntask.class.getSimpleName();

    private DatabaseDefinition database;
    private Callback<DatosInicialesObject> callback;

    public DatosInicialesAsyntask(Callback<DatosInicialesObject> callback) {
        Log.d(TAG, "LoginAsyntask");
        database = FlowManager.getDatabase(BoxDB.class);
        this.callback = callback;
    }


    @Override
    protected DatosInicialesObject doInBackground(DatosInicialResponse... params) {
        int estado;
        DatosInicialResponse resultado = params[0];
        List<Banco> bancoList = resultado.getBanco();
        List<Pais> paisList = resultado.getPais();
        List<CalidadTrabajo> calidadTrabajoList = resultado.getCalidad_trabajo();
        List<Departamento> departamentoList = resultado.getDepartamento();
        List<Distrito> distritoList = resultado.getDistrito();
        List<MotivoRevocacion> motivoRevocacionList = resultado.getMotivo_revocacion();
        List<Provincia> provinciaList = resultado.getProvincia();
        List<Oficio> oficioList = resultado.getOficio();
        List<Rubro> rubroList = resultado.getRubro();
        List<SolucionRevocacion> solucionRevocacionList = resultado.getSolucion_revocacion();
        List<TipoDocumento> tipoDocumentoList = resultado.getTipo_documento();
        List<TipoEstudios> tipoEstudiosList = resultado.getTipo_estudios();
        List<TipoMoneda> tipoMonedaList = resultado.getTipo_moneda();
        List<Especialidades> especialidadesList = resultado.getEspecialidades();
        List<RangoPrecio> rangoPrecioList = resultado.getRango_precio();
        List<RangoDias> rangoDiasList = resultado.getRango_dias();
        List<RangoTurno> rangoTurnoList = resultado.getRango_turno();
        List<BoxMadik_Comision> boxMadikComisionList = resultado.getComision();
        List<CentroEstudios> centroEstudiosList = resultado.getCentro_estudios();
        List<TipoCambio> tipoCambioList = resultado.getTipo_cambio();
        List<PropuestaRevocacion> propuestaRevocacionList = resultado.getPropuesta_revocacion();
        try {
            fastStoreList(Banco.class, bancoList);
            fastStoreList(Pais.class, paisList);
            fastStoreList(CalidadTrabajo.class, calidadTrabajoList);
            fastStoreList(Departamento.class, departamentoList);
            fastStoreList(Distrito.class, distritoList);
            fastStoreList(MotivoRevocacion.class, motivoRevocacionList);
            fastStoreList(Provincia.class, provinciaList);
            fastStoreList(Oficio.class, oficioList);
            fastStoreList(Rubro.class, rubroList);
            fastStoreList(SolucionRevocacion.class, solucionRevocacionList);
            fastStoreList(TipoDocumento.class, tipoDocumentoList);
            fastStoreList(TipoEstudios.class, tipoEstudiosList);
            fastStoreList(TipoMoneda.class, tipoMonedaList);
            fastStoreList(Especialidades.class, especialidadesList);
            fastStoreList(RangoPrecio.class, rangoPrecioList);
            fastStoreList(RangoDias.class, rangoDiasList);
            fastStoreList(RangoTurno.class, rangoTurnoList);
            fastStoreList(BoxMadik_Comision.class, boxMadikComisionList);
            fastStoreList(CentroEstudios.class, centroEstudiosList);
            fastStoreList(TipoCambio.class, tipoCambioList);
            fastStoreList(PropuestaRevocacion.class, propuestaRevocacionList);
            estado = IMPORT_DATA_CORRECTO;
        } catch (Exception e) {
            e.printStackTrace();
            estado = IMPORT_DATA_ERROR_RED;
        }
        return new DatosInicialesObject(estado);
    }


    private <T extends BaseModel> void fastStoreList(Class<T> clazz, List<T> list) {
        if (list != null && !list.isEmpty()) {
            FastStoreModelTransaction fastStoreModelTransaction = FastStoreModelTransaction
                    .insertBuilder(FlowManager.getModelAdapter(clazz))
                    .addAll(list)
                    .build();
            database.beginTransactionAsync(fastStoreModelTransaction).build().execute();
        }
    }

    @Override
    protected void onPostExecute(DatosInicialesObject datosInicialesObject) {
        //  super.onPostExecute(datosInicialesObject);
        if (callback != null) {
            callback.onFinish(datosInicialesObject);
        }
    }


    public interface Callback<T> {
        void onFinish(T object);
    }

    public class DatosInicialesObject {
        private int state;

        public DatosInicialesObject(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
