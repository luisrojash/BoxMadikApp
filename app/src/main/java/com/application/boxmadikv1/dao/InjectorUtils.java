package com.application.boxmadikv1.dao;

import com.application.boxmadikv1.dao.banco.BancoDao;
import com.application.boxmadikv1.dao.banco.BancoDaoImpl;
import com.application.boxmadikv1.dao.calidadTrabajo.CalidadTrabajoDao;
import com.application.boxmadikv1.dao.calidadTrabajo.CalidadTrabajoDaoImpl;
import com.application.boxmadikv1.dao.comision.ComisionDao;
import com.application.boxmadikv1.dao.comision.ComisionDaoImpl;
import com.application.boxmadikv1.dao.departamento.DepartamentoDao;
import com.application.boxmadikv1.dao.departamento.DepartamentoDaoImpl;
import com.application.boxmadikv1.dao.distrito.DistritoDao;
import com.application.boxmadikv1.dao.distrito.DistritoDaoImpl;
import com.application.boxmadikv1.dao.especialidades.EspecialidadesDao;
import com.application.boxmadikv1.dao.especialidades.EspecialidadesDaoImpl;
import com.application.boxmadikv1.dao.motivoRevocacion.MotivoRevocacionDao;
import com.application.boxmadikv1.dao.motivoRevocacion.MotivoRevocacionDaoImpl;
import com.application.boxmadikv1.dao.oficio.OficioDao;
import com.application.boxmadikv1.dao.oficio.OficioDaoImpl;
import com.application.boxmadikv1.dao.pais.PaisDao;
import com.application.boxmadikv1.dao.pais.PaisDaoImpl;
import com.application.boxmadikv1.dao.provincia.ProvinciaDao;
import com.application.boxmadikv1.dao.provincia.ProvinciaDaoImpl;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDao;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDaoImpl;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDao;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDaoImpl;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDao;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDaoImpl;
import com.application.boxmadikv1.dao.rubro.RubroDao;
import com.application.boxmadikv1.dao.rubro.RubroDaoImpl;
import com.application.boxmadikv1.dao.solucionRevocacion.SolucionRevocacionDao;
import com.application.boxmadikv1.dao.solucionRevocacion.SolucionRevocacionDaoImpl;
import com.application.boxmadikv1.dao.tipoCambio.TipoCambioDao;
import com.application.boxmadikv1.dao.tipoCambio.TipoCambioDaoImpl;
import com.application.boxmadikv1.dao.tipoCentroEstudios.TipoCentroEstudiosDao;
import com.application.boxmadikv1.dao.tipoCentroEstudios.TipoCentrosEstudiosDaoImplTipo;
import com.application.boxmadikv1.dao.tipoDocumento.TipoDocumentoDao;
import com.application.boxmadikv1.dao.tipoDocumento.TipoDocumentoDaoImpl;
import com.application.boxmadikv1.dao.tipoEstudio.TipoEstudiosDao;
import com.application.boxmadikv1.dao.tipoEstudio.TipoEstudiosDaoImpl;
import com.application.boxmadikv1.dao.tipoRevocacionPropuesta.TipoRevocaPropuestaDao;
import com.application.boxmadikv1.dao.tipoRevocacionPropuesta.TipoRevocaPropuestaDaoImpl;
import com.application.boxmadikv1.dao.usuario.UsuarioDao;
import com.application.boxmadikv1.dao.usuario.UsuarioDaoImpl;

public class InjectorUtils {

    public static RubroDao provideRubroDao() {
        return RubroDaoImpl.getmInstance();
    }

    public static OficioDao provideOficioDao() {
        return OficioDaoImpl.getmInstance();
    }

    public static RangoTurnoDao provideRangoTurnoDao() {
        return RangoTurnoDaoImpl.getmInstance();
    }

    public static RangoPrecioDao provideRangoPrecioDao() {
        return RangoPrecioDaoImpl.getmIntance();
    }

    public static RangoDiasDao provideRangoDiasDao() {
        return RangoDiasDaoImpl.getmInstance();
    }

    public static EspecialidadesDao provideEspecialidadesDao() {
        return EspecialidadesDaoImpl.getmInstance();
    }

    public static final ComisionDao provideComisionDao() {
        return ComisionDaoImpl.getmInstance();
    }

    public static final MotivoRevocacionDao provideRevocacionDao() {
        return MotivoRevocacionDaoImpl.getmInstance();
    }

    public static final DepartamentoDao provideDepartamentoDao() {
        return DepartamentoDaoImpl.getmInstance();
    }

    public static final ProvinciaDao provideProvinciaDao() {
        return ProvinciaDaoImpl.getmInstance();
    }

    public static final DistritoDao provideDistritoDao() {
        return DistritoDaoImpl.getmInstance();

    }

    public static final BancoDao provideBancoDao() {
        return BancoDaoImpl.getmInstance();
    }

    public static final CalidadTrabajoDao provideCalidadTrabajoDao() {
        return CalidadTrabajoDaoImpl.getmInstance();
    }

    public static final SolucionRevocacionDao provideSolucionRevocacionDao() {
        return SolucionRevocacionDaoImpl.getmInstance();
    }

    public static final UsuarioDao provideUsuarioDao() {
        return UsuarioDaoImpl.getmInstance();
    }

    public static final TipoDocumentoDao provideTipoDocumentoDao() {
        return TipoDocumentoDaoImpl.getmInstance();
    }

    public static final PaisDao providePaisDao() {
        return PaisDaoImpl.getmInstance();
    }

    public static final TipoEstudiosDao provideTipoEstudiosDao() {
        return TipoEstudiosDaoImpl.getmInstance();
    }

    public static final TipoCentroEstudiosDao provideCentroEstudiosDao() {
        return TipoCentrosEstudiosDaoImplTipo.getmInstance();
    }

    public static final TipoCambioDao provideTipoCambioDao() {
        return TipoCambioDaoImpl.getmInstance();
    }

    public static final TipoRevocaPropuestaDao provideTipoRevocaPropuesta() {
        return TipoRevocaPropuestaDaoImpl.getmInstance();
    }

}
