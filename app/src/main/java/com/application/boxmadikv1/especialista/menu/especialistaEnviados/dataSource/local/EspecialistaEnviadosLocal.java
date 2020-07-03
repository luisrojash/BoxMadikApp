package com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.local;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.dao.oficio.OficioDao;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDao;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDaoImpl;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDao;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDao;
import com.application.boxmadikv1.dao.rubro.RubroDao;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class EspecialistaEnviadosLocal implements EspecialistaEnviadosDataSource {

    private RubroDao rubroDao;
    private RangoPrecioDao rangoPrecioDao;
    private OficioDao oficioDao;
    private RangoDiasDao rangoDiasDao;
    private RangoTurnoDao rangoTurnoDao;


    public EspecialistaEnviadosLocal(RubroDao rubroDao, RangoPrecioDao rangoPrecioDao, OficioDao oficioDao, RangoDiasDao rangoDiasDao, RangoTurnoDao rangoTurnoDao) {
        this.rubroDao = rubroDao;
        this.rangoPrecioDao = rangoPrecioDao;
        this.oficioDao = oficioDao;
        this.rangoDiasDao = rangoDiasDao;
        this.rangoTurnoDao = rangoTurnoDao;
    }

    @Override
    public void mostrarListaClienteTodos(CallBackResultado<List<EspecialistaEnviadosUi>> listCallBackResultado) {
        // listCallBackResultado.onCallBackResultado(obtenerLista());
    }

    @Override
    public void mostrarListaClienteEnviados(String usuarioCodigo, String paisCodigo, String priEstado, CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado) {

    }

    @Override
    public void eliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi, String keyUser, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

    }

    public void llenarDataFaltante(ListaCotizacionesResponse resultado, CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado) {

        if (resultado == null) {
            listaCotizacionesResponseCallBackResultado.onCallBackResultado(null);
            return;
        } else {
            List<ListaCotizacionesResponse.CotizacionesResponse> listaResponse = resultado.getCotizacionesResponseList();

            if (listaResponse != null) {
                List<EspecialistaEnviadosUi> especialistaEnviadosUis = new ArrayList<>();
                for (ListaCotizacionesResponse.CotizacionesResponse cotizacionesResponse : resultado.getCotizacionesResponseList()) {
/*
                    EspecialistaEnviadosUi especialistaEnviadosUi = new EspecialistaEnviadosUi();

                    Rubro rubro = rubroDao.obtenerMiIdQuerySimple(Integer.parseInt(cotizacionesResponse.getCodigoRubro()));
                    if (rubro == null) return;
                    RangoPrecio rangoPrecio = rangoPrecioDao.obtenerMiIdQuerySimple(Integer.parseInt(cotizacionesResponse.getRangoPrecioCodigo()));
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
                    especialistaEnviadosUi.setNombreProyecto(cotizacionesResponse.getTitulo());
                    especialistaEnviadosUi.setDetallePropuesta(cotizacionesResponse.getDetallePropuesta());
                    especialistaEnviadosUi.setMontoCotizado(cotizacionesResponse.getMontoCotizado());
                    especialistaEnviadosUi.setFechaRealizo(Constantes.recortarFechaRealizo(cotizacionesResponse.getCotiFecha()));
                    especialistaEnviadosUi.setCodigoRangoPrecio(cotizacionesResponse.getCodigoRangoPrecio());
                    especialistaEnviadosUi.setDescripcionRangoPrecio(rangoPrecio.getRan_descripcion());
                    especialistaEnviadosUi.setCostoPromedio(validarPromedioCotizacion(cotizacionesResponse.getPromedioCotizacion()));
                    especialistaEnviadosUi.setNumeroCotizacion(validarNumeroCotizacion(cotizacionesResponse.getNumeroCotizacion()));
                    especialistaEnviadosUi.setCodigoRubro(cotizacionesResponse.getCodigoRubro());
                    especialistaEnviadosUi.setNombreRubro(rubro.getRub_Desc());
                    especialistaEnviadosUi.setCodigoOficio(cotizacionesResponse.getCodigoOficio());
                    especialistaEnviadosUi.setNombreOficio(oficio.getOfi_desc());
                    especialistaEnviadosUi.setCodigoRangoDias(cotizacionesResponse.getCodigoRangoDias());
                    especialistaEnviadosUi.setNombreRangoDias(rangoDias.getRad_descripcion());
                    especialistaEnviadosUi.setCodigoRangoTurno(cotizacionesResponse.getCodigoRangoTurno());
                    especialistaEnviadosUi.setNombreRangoTurno(rangoTurno.getRat_descripcion());
                    especialistaEnviadosUi.setCodigoUsuarioCreaPropuesta(cotizacionesResponse.getCodigoUsuarioCreaPropuesta());
                    especialistaEnviadosUis.add(especialistaEnviadosUi);*/


                }
                resultado.setEspecialistaEnviadosUis(especialistaEnviadosUis);
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
