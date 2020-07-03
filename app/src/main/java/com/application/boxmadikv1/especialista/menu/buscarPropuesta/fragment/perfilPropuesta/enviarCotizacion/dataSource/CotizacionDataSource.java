package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.api.response.especialista.DatosCotizacionResponse;

public interface CotizacionDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onObtenerComisionPorcentaje(CallBackResultado<String> stringCallBackResultado);

    void onObtenerTipoCambioDolar(CallBackResultado<String> stringCallBackResultado,String codigoPais);

    void onEnviarCotizacion(String descripcion, double coti_montoNeto, double coti_comisionTotal, double coti_sumaTotalSoles, double coti_sumaTotalDolares, double monedaDolar, String codigoUsuario, String codigo_propuesta, String dateInicio, String dateFin, String boxmadik_comision, CallBackResultado<DefaultResponseRegistro> defaultResponseCallBackResultado);

    void onObtenerValidacionCotizacion(String usuarioCodigo, String propuestaCodigo, String estadoCotizacion, String paisCodigo, CallBackResultado<DatosCotizacionResponse> datosCotizacionResponseCallBackResultado);

    void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo,String codigoCotizacion);
}
