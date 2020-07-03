package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.local;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.api.response.especialista.DatosCotizacionResponse;
import com.application.boxmadikv1.dao.comision.ComisionDao;
import com.application.boxmadikv1.dao.tipoCambio.TipoCambioDao;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionDataSource;
import com.application.boxmadikv1.modelo.BoxMadik_Comision;
import com.application.boxmadikv1.modelo.TipoCambio;

public class CotizacionLocal implements CotizacionDataSource {
    public static final String TAG = CotizacionLocal.class.getSimpleName();

    private ComisionDao comisionDao;
    private TipoCambioDao tipoCambioDao;

    public CotizacionLocal(ComisionDao comisionDao, TipoCambioDao tipoCambioDao) {
        this.comisionDao = comisionDao;
        this.tipoCambioDao = tipoCambioDao;
    }

    @Override
    public void onObtenerComisionPorcentaje(CallBackResultado<String> stringCallBackResultado) {
        //List<BoxMadik_Comision> comisionList = comisionDao.getAll();
        BoxMadik_Comision boxMadikComision = comisionDao.obtenerMiIdQuerySimple(1);
        //Log.d(TAG, "boxMadikComision : " + boxMadikComision.ge)
        if (boxMadikComision == null) return;
        stringCallBackResultado.onCallBackResultado(boxMadikComision.getBoxc_comision());
    }

    @Override
    public void onObtenerTipoCambioDolar(CallBackResultado<String> stringCallBackResultado,String codigoPais) {
        TipoCambio tipoCambio = tipoCambioDao.obtenerTipoCambioPorPais(codigoPais);
        if(tipoCambio==null)return;
        stringCallBackResultado.onCallBackResultado(String.valueOf(tipoCambio.getTc_Valor()));
    }

    @Override
    public void onEnviarCotizacion(String descripcion, double coti_montoNeto, double coti_comisionTotal, double coti_sumaTotalSoles, double coti_sumaTotalDolares, double monedaDolar, String codigoUsuario, String codigo_propuesta, String dateInicio, String dateFin, String boxmadik_comision, CallBackResultado<DefaultResponseRegistro> defaultResponseCallBackResultado) {

    }

    @Override
    public void onObtenerValidacionCotizacion(String usuarioCodigo, String propuestaCodigo, String estadoCotizacion, String paisCodigo, CallBackResultado<DatosCotizacionResponse> datosCotizacionResponseCallBackResultado) {

    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo, String codigoCotizacion) {

    }
}
