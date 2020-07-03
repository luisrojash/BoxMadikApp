package com.application.boxmadikv1.especialista.menu.abstracto.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.dao.oficio.OficioDao;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDao;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDao;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDao;
import com.application.boxmadikv1.dao.rubro.RubroDao;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.EspecAbstractDataSource;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosDataSource;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspecAbstractLocal implements EspecAbstractDataSource {

    public static final String TAG = EspecAbstractLocal.class.getSimpleName();

    private RubroDao rubroDao;
    private RangoPrecioDao rangoPrecioDao;
    private OficioDao oficioDao;
    private RangoDiasDao rangoDiasDao;
    private RangoTurnoDao rangoTurnoDao;


    public EspecAbstractLocal(RubroDao rubroDao, RangoPrecioDao rangoPrecioDao, OficioDao oficioDao, RangoDiasDao rangoDiasDao, RangoTurnoDao rangoTurnoDao) {
        this.rubroDao = rubroDao;
        this.rangoPrecioDao = rangoPrecioDao;
        this.oficioDao = oficioDao;
        this.rangoDiasDao = rangoDiasDao;
        this.rangoTurnoDao = rangoTurnoDao;
    }

    @Override
    public void mostrarListaClienteEnviados(String usuarioCodigo, String paisCodigo, String priEstado, CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado) {

    }

    @Override
    public void eliminarItem(EspecialistaEstadosUi especialistaEstadosUi, String keyUser, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

    }

    public void llenarDataFaltante(ListaCotizacionesResponse resultado, EspecialistaEnviadosDataSource.CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado) {

        if (resultado == null) {
            listaCotizacionesResponseCallBackResultado.onCallBackResultado(null);
            return;
        } else {
            List<ListaCotizacionesResponse.CotizacionesResponse> listaResponse = resultado.getCotizacionesResponseList();

            if (listaResponse != null) {
                List<EspecialistaEstadosUi> especialistaEnviadosUis = new ArrayList<>();
                for (ListaCotizacionesResponse.CotizacionesResponse cotizacionesResponse : resultado.getCotizacionesResponseList()) {

                    final EspecialistaEstadosUi especialistaEnviadosUi = new EspecialistaEstadosUi();

                    Rubro rubro = rubroDao.obtenerMiIdQuerySimple(Integer.parseInt(cotizacionesResponse.getCodigoRubro()));
                    if (rubro == null) return;
                    RangoPrecio rangoPrecio = rangoPrecioDao.obtenerMiIdQuerySimple(Integer.parseInt(cotizacionesResponse.getCodigoRangoPrecio()));
                    if (rangoPrecio == null) return;
                    Oficio oficio = oficioDao.obtenerMiIdQuerySimple(Integer.parseInt(cotizacionesResponse.getCodigoOficio()));
                    if (oficio == null) return;
                    RangoDias rangoDias = rangoDiasDao.obtenerMiIdQuerySimple(Integer.parseInt(cotizacionesResponse.getCodigoRangoDias()));
                    if (rangoDias == null) return;
                    RangoTurno rangoTurno = rangoTurnoDao.obtenerMiIdQuerySimple(Integer.parseInt(cotizacionesResponse.getCodigoRangoTurno()));
                    if (rangoTurno == null) return;

                    especialistaEnviadosUi.setIdCodigoCotizacion(cotizacionesResponse.getCodigoCotizacion() + "");
                    especialistaEnviadosUi.setIdCodigoPropuesta(cotizacionesResponse.getCodigoPropuesta() + "");
                    especialistaEnviadosUi.setImagenRubro(rubro.getRub_Imagen());
                    especialistaEnviadosUi.setNombreProyecto(cotizacionesResponse.getTituloPropuesta());
                    especialistaEnviadosUi.setDetallePropuesta(cotizacionesResponse.getDetallePropuesta());
                    especialistaEnviadosUi.setMontoCotizado(cotizacionesResponse.getPromedioCotizacion());
                    especialistaEnviadosUi.setFechaRealizo(cotizacionesResponse.getCotiFecha());
                    especialistaEnviadosUi.setCodigoRangoPrecio(cotizacionesResponse.getCodigoRangoPrecio());
                    especialistaEnviadosUi.setDescripcionRangoPrecio(rangoPrecio.getRan_descripcion());
                    especialistaEnviadosUi.setCostoPromedio(validarPromedioCotizacion(cotizacionesResponse.getPromedioCotizacion()));
                    especialistaEnviadosUi.setNumeroCotizacion(validarNumeroCotizacion(cotizacionesResponse.getNumerCotizacion()));
                    especialistaEnviadosUi.setCodigoRubro(cotizacionesResponse.getCodigoRubro());
                    especialistaEnviadosUi.setNombreRubro(rubro.getRub_Desc());
                    especialistaEnviadosUi.setCodigoOficio(cotizacionesResponse.getCodigoOficio());
                    especialistaEnviadosUi.setNombreOficio(oficio.getOfi_desc());
                    especialistaEnviadosUi.setCodigoRangoDias(cotizacionesResponse.getCodigoRangoDias());
                    especialistaEnviadosUi.setNombreRangoDias(rangoDias.getRad_descripcion());
                    especialistaEnviadosUi.setCodigoRangoTurno(cotizacionesResponse.getCodigoRangoTurno());
                    especialistaEnviadosUi.setNombreRangoTurno(rangoTurno.getRat_descripcion());
                    especialistaEnviadosUi.setCodigoUsuarioCreaPropuesta(cotizacionesResponse.getUsuarioCreaPropuesta());
                    Log.d(TAG, "cotizacionesResponse.getEstadoCotizacion() " + cotizacionesResponse.getEstadoCotizacion());
                    especialistaEnviadosUi.setCotiEstado(cotizacionesResponse.getEstadoCotizacion());
                    especialistaEnviadosUi.setPropuestaEstado(cotizacionesResponse.getEstadoPropuesta());
                    especialistaEnviadosUi.setDepartamentoNombre(cotizacionesResponse.getDepartamentoNombre());
                    especialistaEnviadosUi.setProvinciaNombre(cotizacionesResponse.getProvinciaNombre());
                    especialistaEnviadosUi.setDistritoNombre(cotizacionesResponse.getDistritoNombre());
                    especialistaEnviadosUi.setEstadoPropuestaCalificar(cotizacionesResponse.getCotiEstadoCalifica());
                    especialistaEnviadosUi.setCodigoCotiUsuario(cotizacionesResponse.getCodigoCotiUsuario());
                    especialistaEnviadosUi.setUsuNombreCliente(cotizacionesResponse.getUsuNombre());
                    especialistaEnviadosUi.setUsuAPellidosCliente(cotizacionesResponse.getUsuAPellidos());
                    especialistaEnviadosUi.setUsuRazonSocialCliente(cotizacionesResponse.getUsuRazonSocial());
                    especialistaEnviadosUi.setUsuFotoCliente(cotizacionesResponse.getUsuFoto());
                    especialistaEnviadosUi.setPaisImagenCliente(cotizacionesResponse.getPaisImagen());
                    especialistaEnviadosUi.setEstadoRevocacion(cotizacionesResponse.getEstadoRevocacion());
                    especialistaEnviadosUi.setOfiMonto(cotizacionesResponse.getOfiMonto());
                    especialistaEnviadosUis.add(especialistaEnviadosUi);


                }
                resultado.setEspecialistaEstadosUis(especialistaEnviadosUis);
                listaCotizacionesResponseCallBackResultado.onCallBackResultado(resultado);
                return;
            }
            listaCotizacionesResponseCallBackResultado.onCallBackResultado(resultado);
            return;
        }


    }


    private String validarPromedioCotizacion(String promedioCotizacion) {
        if (promedioCotizacion == null) {
            return "0";
        } else {
            return promedioCotizacion;
        }
    }

    private String validarNumeroCotizacion(String numeroCotizacion) {
        if (numeroCotizacion == null) {
            return "0";
        } else {
            return numeroCotizacion;
        }
    }
}
