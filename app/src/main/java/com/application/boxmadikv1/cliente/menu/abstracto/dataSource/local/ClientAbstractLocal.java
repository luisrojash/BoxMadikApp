package com.application.boxmadikv1.cliente.menu.abstracto.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.ClientAbstractDataSource;

import com.application.boxmadikv1.cliente.menu.abstracto.entidad.ClienteEstadosUi;
import com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.local.ClientePendienteLocal;
import com.application.boxmadikv1.dao.departamento.DepartamentoDao;
import com.application.boxmadikv1.dao.distrito.DistritoDao;
import com.application.boxmadikv1.dao.oficio.OficioDao;
import com.application.boxmadikv1.dao.provincia.ProvinciaDao;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDao;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDao;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDao;
import com.application.boxmadikv1.dao.rubro.RubroDao;
import com.application.boxmadikv1.modelo.Departamento;
import com.application.boxmadikv1.modelo.Distrito;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.PropuestaInicial;
import com.application.boxmadikv1.modelo.Provincia;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.modelo.Rubro;

import java.util.ArrayList;
import java.util.List;

import static com.application.boxmadikv1.utils.Constantes.recortarFechaRealizo;

public class ClientAbstractLocal implements ClientAbstractDataSource {

    public static final String TAG = ClientAbstractLocal.class.getSimpleName();

    private RubroDao rubroDao;
    private OficioDao oficioDao;
    private RangoTurnoDao rangoTurnoDao;
    private RangoDiasDao rangoDiasDao;
    private RangoPrecioDao rangoPrecioDao;
    private DepartamentoDao departamentoDao;
    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;

    public ClientAbstractLocal(RubroDao rubroDao, OficioDao oficioDao, RangoTurnoDao rangoTurnoDao, RangoDiasDao rangoDiasDao, RangoPrecioDao rangoPrecioDao, DepartamentoDao departamentoDao, ProvinciaDao provinciaDao, DistritoDao distritoDao) {
        this.rubroDao = rubroDao;
        this.oficioDao = oficioDao;
        this.rangoTurnoDao = rangoTurnoDao;
        this.rangoDiasDao = rangoDiasDao;
        this.rangoPrecioDao = rangoPrecioDao;
        this.departamentoDao = departamentoDao;
        this.provinciaDao = provinciaDao;
        this.distritoDao = distritoDao;
    }

    @Override
    public void mostrarListaClienteEstados(String keyUser, String codigoPais, String tipoEstado, CallBackResultado<ListaPropuestaPendienteResponse> listCallBackResultado) {

    }

    public void mostrarListaFiltradaLocal(ListaPropuestaPendienteResponse resultado, CallBackResultado<ListaPropuestaPendienteResponse> callBackResultado) {

        List<PropuestaInicial> propuestaInicialList = resultado.getPropuestasClientePendientes();

        List<ClienteEstadosUi> clienteEstadosUis = new ArrayList<>();

        for (PropuestaInicial propuestaInicial : propuestaInicialList) {
            ClienteEstadosUi clienteEstadosUi = new ClienteEstadosUi();

            String rubroId = propuestaInicial.getRubro_Rub_Codigo();
            String oficioId = propuestaInicial.getOficio_Ofi_codigo();
            String ranTurnoId = propuestaInicial.getRango_turno_Rat_Item();
            String ranDiasId = propuestaInicial.getRango_dias_Rad_Item();
            String ranPrecioId = propuestaInicial.getRango_precio_Ran_Item();
            String departamentoId = propuestaInicial.getPri_Departamento();
            String provinciaId = propuestaInicial.getPri_Provincia();
            String distritoId = propuestaInicial.getPri_Distrito();
            Log.d(TAG, "propuestaInicial : " + propuestaInicial.getPri_Departamento());

            Rubro rubro = rubroDao.obtenerMiIdQuerySimple(Integer.parseInt(rubroId));
            Oficio oficio = oficioDao.obtenerMiIdQuerySimple(Integer.parseInt(oficioId));
            RangoTurno rangoTurno = rangoTurnoDao.obtenerMiIdQuerySimple(Integer.parseInt(ranTurnoId));
            RangoDias rangoDias = rangoDiasDao.obtenerMiIdQuerySimple(Integer.parseInt(ranDiasId));
            RangoPrecio rangoPrecio = rangoPrecioDao.obtenerMiIdQuerySimple(Integer.parseInt(ranPrecioId));
            int paisCodigo = 51;
            Departamento departamento = departamentoDao.obtenerMiIdQuerySimple(Integer.parseInt(departamentoId), paisCodigo);
            Provincia provincia = provinciaDao.obtenerMiIdQuerySimple(Integer.parseInt(provinciaId),paisCodigo,departamento.getDepa_Cod());
            Distrito distrito = distritoDao.obtenerMiIdQuerySimple(Integer.parseInt(distritoId),provincia.getProv_cod(), departamento.getDepa_Cod(),paisCodigo);
            Log.d(TAG, "rubroId : " + rubroId + " / " + rubro.getRub_Desc()
                    + " / oficio" + oficio.getOfi_desc()
                    + " / rangoTurno" + rangoTurno.getRat_descripcion()
                    + " / rangoDias" + rangoDias.getRad_descripcion()
                    + " / rangoPrecio" + departamento.getDepa_Desc()
                    + " / provincia" + provincia.getProv_Desc()
                    + " / distrito" + distrito.getDist_cod());

            clienteEstadosUi.setId(propuestaInicial.getPri_Codigo() + "");
            clienteEstadosUi.setNombreProyecto(propuestaInicial.getPri_Titulo());
            clienteEstadosUi.setDetallesProyecto(propuestaInicial.getPri_Detalle());
            clienteEstadosUi.setImage(rubro.getRub_Imagen());
            clienteEstadosUi.setFechaRealizo(propuestaInicial.getPri_Fecha());
            clienteEstadosUi.setPropuesto(rangoPrecio.getRan_descripcion());
            clienteEstadosUi.setPromedio(rangoPrecio.getRan_Monto2() + "");

            clienteEstadosUi.setCodigoRangoDias(String.valueOf(rangoDias.getRad_Item()));
            clienteEstadosUi.setDescripcionRangoDias(rangoDias.getRad_descripcion());

            clienteEstadosUi.setCodigoRangoPresupuesto(String.valueOf(rangoPrecio.getRan_Item()));
            clienteEstadosUi.setDescripcionRangoPresupuesto(rangoPrecio.getRan_descripcion());

            clienteEstadosUi.setCodigoRangoTurno(String.valueOf(rangoTurno.getRat_Item()));
            clienteEstadosUi.setDescripcionRangoTurno(rangoTurno.getRat_descripcion());

            clienteEstadosUi.setUsuarioCodigoPropuestaInicial(propuestaInicial.getUsuario_Usu_Codigo());
            clienteEstadosUi.setTiposEstado(propuestaInicial.getPri_Estado());
            clienteEstadosUi.setNombreDepartamento(departamento.getDepa_Desc());
            clienteEstadosUi.setNombreDistrito(distrito.getDist_Desc());

            clienteEstadosUi.setNombreRubro(rubro.getRub_Desc());
            clienteEstadosUi.setNombreOficio(oficio.getOfi_desc());
            if (propuestaInicial.getNum_cotizacion() == null) {
                clienteEstadosUi.setNumeroCotizacion(0);
            } else {
                clienteEstadosUi.setNumeroCotizacion(Integer.parseInt(propuestaInicial.getNum_cotizacion()));
            }
            if (propuestaInicial.getPromedio_coti() == null) {
                clienteEstadosUi.setPromedioCotizacion("0");
            } else {
                clienteEstadosUi.setPromedioCotizacion(propuestaInicial.getPromedio_coti());
            }


            clienteEstadosUis.add(clienteEstadosUi);

        }
        resultado.setClienteEstadosUis(clienteEstadosUis);
        callBackResultado.onCallBackResultado(resultado);
    }
}
