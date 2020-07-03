package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.api.response.especialista.DatosCotizacionResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.local.CotizacionLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.remote.CotizacionRemote;

public class CotizacionRepository implements CotizacionDataSource {

    private static CotizacionRepository mInstance = null;
    private CotizacionLocal cotizacionLocal;
    private CotizacionRemote cotizacionRemote;

    public static final CotizacionRepository getmInstance(CotizacionLocal cotizacionLocal, CotizacionRemote cotizacionRemote) {
        if (mInstance == null) {
            mInstance = new CotizacionRepository(cotizacionLocal, cotizacionRemote);
        }

        return mInstance;
    }

    public CotizacionRepository(CotizacionLocal cotizacionLocal, CotizacionRemote cotizacionRemote) {
        this.cotizacionLocal = cotizacionLocal;
        this.cotizacionRemote = cotizacionRemote;
    }

    @Override
    public void onObtenerComisionPorcentaje(CallBackResultado<String> stringCallBackResultado) {
        cotizacionLocal.onObtenerComisionPorcentaje(stringCallBackResultado);
    }

    @Override
    public void onObtenerTipoCambioDolar(CallBackResultado<String> stringCallBackResultado,String codigoPais) {
        //cotizacionRemote.onObtenerTipoCambioDolar(stringCallBackResultado);
        cotizacionLocal.onObtenerTipoCambioDolar(stringCallBackResultado,codigoPais);
    }

    @Override
    public void onEnviarCotizacion(String descripcion, double coti_montoNeto, double coti_comisionTotal, double coti_sumaTotalSoles, double coti_sumaTotalDolares, double monedaDolar, String codigoUsuario, String codigo_propuesta, String dateInicio, String dateFin, String boxmadik_comision, CallBackResultado<DefaultResponseRegistro> defaultResponseCallBackResultado) {
        cotizacionRemote.onEnviarCotizacion(descripcion, coti_montoNeto, coti_comisionTotal, coti_sumaTotalSoles, coti_sumaTotalDolares, monedaDolar, codigoUsuario, codigo_propuesta, dateInicio, dateFin, boxmadik_comision, defaultResponseCallBackResultado);
    }

    @Override
    public void onObtenerValidacionCotizacion(String usuarioCodigo, String propuestaCodigo, String estadoCotizacion, String paisCodigo, CallBackResultado<DatosCotizacionResponse> datosCotizacionResponseCallBackResultado) {
        cotizacionRemote.onObtenerValidacionCotizacion(usuarioCodigo, propuestaCodigo, estadoCotizacion, paisCodigo, datosCotizacionResponseCallBackResultado);
    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo,String codigoCotizacion) {
        cotizacionRemote.onEnvioNotificacion(grupoNotificacionCodigo, tipoNotificacionCodigo, usuarioCodigo, propuestaCodigo, paisCodigo,codigoCotizacion);
    }
}
