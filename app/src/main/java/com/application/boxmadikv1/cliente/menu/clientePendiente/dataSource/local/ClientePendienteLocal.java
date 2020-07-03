package com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.ClientePendienteDataSource;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.dao.oficio.OficioDao;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDao;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDao;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDao;
import com.application.boxmadikv1.dao.rubro.RubroDao;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.PropuestaInicial;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.modelo.Rubro;

import java.util.ArrayList;
import java.util.List;

public class ClientePendienteLocal implements ClientePendienteDataSource {

    public static final String TAG = ClientePendienteLocal.class.getSimpleName();

    private RubroDao rubroDao;
    private OficioDao oficioDao;
    private RangoTurnoDao rangoTurnoDao;
    private RangoDiasDao rangoDiasDao;
    private RangoPrecioDao rangoPrecioDao;

    public ClientePendienteLocal(RubroDao rubroDao, OficioDao oficioDao, RangoTurnoDao rangoTurnoDao, RangoDiasDao rangoDiasDao, RangoPrecioDao rangoPrecioDao) {
        this.rubroDao = rubroDao;
        this.oficioDao = oficioDao;
        this.rangoTurnoDao = rangoTurnoDao;
        this.rangoDiasDao = rangoDiasDao;
        this.rangoPrecioDao = rangoPrecioDao;
    }

    @Override
    public void mostrarListaClienteTodos(String keyUser, CallBackResultado<ListaPropuestaPendienteResponse> listCallBackResultado) {
    }

    public void mostrarListaFiltradaLocal(ListaPropuestaPendienteResponse resultado, CallBackResultado<ListaPropuestaPendienteResponse> callBackResultado) {
        List<PropuestaInicial> propuestaInicialList = resultado.getPropuestasClientePendientes();

        List<ClientePendienteUi> pendienteUiListNueva = new ArrayList<>();

        for (PropuestaInicial propuestaInicial : propuestaInicialList) {
            ClientePendienteUi clientePendienteEntidad = new ClientePendienteUi();

            String rubroId = propuestaInicial.getRubro_Rub_Codigo();
            String oficioId = propuestaInicial.getOficio_Ofi_codigo();
            String ranTurnoId = propuestaInicial.getRango_turno_Rat_Item();
            String ranDiasId = propuestaInicial.getRango_dias_Rad_Item();
            String ranPrecioId = propuestaInicial.getRango_precio_Ran_Item();

            Rubro rubro = rubroDao.obtenerMiIdQuerySimple(Integer.parseInt(rubroId));
            Oficio oficio = oficioDao.obtenerMiIdQuerySimple(Integer.parseInt(oficioId));
            RangoTurno rangoTurno = rangoTurnoDao.obtenerMiIdQuerySimple(Integer.parseInt(ranTurnoId));
            RangoDias rangoDias = rangoDiasDao.obtenerMiIdQuerySimple(Integer.parseInt(ranDiasId));
            RangoPrecio rangoPrecio = rangoPrecioDao.obtenerMiIdQuerySimple(Integer.parseInt(ranPrecioId));

            Log.d(TAG, "rubroId : " + rubroId + " / " + rubro.getRub_Desc()
                    + " / oficio" + oficio.getOfi_desc()
                    + " / rangoTurno" + rangoTurno.getRat_descripcion()
                    + " / rangoDias" + rangoDias.getRad_descripcion()
                    + " / rangoPrecio" + rangoPrecio.getRan_descripcion());

            clientePendienteEntidad.setId(propuestaInicial.getPri_Codigo() + "");
            clientePendienteEntidad.setNombreProyecto(propuestaInicial.getPri_Titulo());
            clientePendienteEntidad.setDetallesProyecto(propuestaInicial.getPri_Detalle());
            clientePendienteEntidad.setImage(rubro.getRub_Imagen());
            clientePendienteEntidad.setFechaRealizo(recortarFechaRealizo(propuestaInicial.getPri_Fecha()));
            clientePendienteEntidad.setPropuesto(rangoPrecio.getRan_descripcion());
            clientePendienteEntidad.setPromedio(rangoPrecio.getRan_Monto2() + "");

            clientePendienteEntidad.setCodigoRangoDias(String.valueOf(rangoDias.getRad_Item()));
            clientePendienteEntidad.setDescripcionRangoDias(rangoDias.getRad_descripcion());

            clientePendienteEntidad.setCodigoRangoPresupuesto(String.valueOf(rangoPrecio.getRan_Item()));
            clientePendienteEntidad.setDescripcionRangoPresupuesto(rangoPrecio.getRan_descripcion());

            clientePendienteEntidad.setCodigoRangoTurno(String.valueOf(rangoTurno.getRat_Item()));
            clientePendienteEntidad.setDescripcionRangoTurno(rangoTurno.getRat_descripcion());

            clientePendienteEntidad.setUsuarioCodigoPropuestaInicial(propuestaInicial.getUsuario_Usu_Codigo());

            clientePendienteEntidad.setNombreRubro(rubro.getRub_Desc());
            clientePendienteEntidad.setNombreOficio(oficio.getOfi_desc());
            if (propuestaInicial.getNum_cotizacion() == null) {
                clientePendienteEntidad.setNumeroCotizacion(0);
            } else {
                clientePendienteEntidad.setNumeroCotizacion(Integer.parseInt(propuestaInicial.getNum_cotizacion()));
            }
            if (propuestaInicial.getPromedio_coti() == null) {
                clientePendienteEntidad.setPromedioCotizacion("0");
            } else {
                clientePendienteEntidad.setPromedioCotizacion(propuestaInicial.getPromedio_coti());
            }


            pendienteUiListNueva.add(clientePendienteEntidad);

        }
       // resultado.setClientePendienteUiList(pendienteUiListNueva);
        callBackResultado.onCallBackResultado(resultado);
    }

    private String recortarFechaRealizo(String FechaRealizo) {
        String[] parts = FechaRealizo.split(":");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556

        String s = part1;
        FechaRealizo = s.substring(0, 10);//he
        return FechaRealizo;
    }

}
