package com.application.boxmadikv1.api.response;

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

import java.util.List;

public class DatosInicialResponse {

    private boolean error;
    private List<Pais> pais;
    private List<Banco> banco;
    private List<CalidadTrabajo> calidad_trabajo;
    private List<Departamento> departamento;
    private List<Distrito> distrito;
    private List<MotivoRevocacion> motivo_revocacion;
    private List<Provincia> provincia;
    private List<Oficio> oficio;
    private List<Rubro> rubro;
    private List<SolucionRevocacion> solucion_revocacion;
    private List<TipoDocumento> tipo_documento;
    private List<TipoEstudios> tipo_estudios;
    private List<TipoMoneda> tipo_moneda;
    private List<Especialidades> especialidades;
    private List<RangoDias> rango_dias;
    private List<RangoPrecio> rango_precio;
    private List<RangoTurno> rango_turno;
    private List<BoxMadik_Comision> boxmadik_comision;
    private List<CentroEstudios> centro_estudios;
    private List<TipoCambio> tipo_cambio;
    private List<PropuestaRevocacion> propuesta_revocacion;

    public DatosInicialResponse(boolean error, List<Pais> pais, List<Banco> banco, List<CalidadTrabajo> calidad_trabajo, List<Departamento> departamento, List<Distrito> distrito,
                                List<MotivoRevocacion> motivo_revocacion, List<Provincia> provincia,
                                List<Oficio> oficio, List<Rubro> rubro, List<SolucionRevocacion> solucion_revocacion,
                                List<TipoDocumento> tipo_documento, List<TipoEstudios> tipo_estudios,
                                List<TipoMoneda> tipo_moneda, List<Especialidades> especialidades,
                                List<RangoDias> rango_dias, List<RangoPrecio> rango_precio, List<RangoTurno> rango_turno,
                                List<BoxMadik_Comision> boxMadikComision, List<CentroEstudios> centro_estudios,
                                List<TipoCambio> tipo_cambio,List<PropuestaRevocacion> propuesta_revocacion) {
        this.error = error;
        this.pais = pais;
        this.banco = banco;
        this.calidad_trabajo = calidad_trabajo;
        this.departamento = departamento;
        this.distrito = distrito;
        this.motivo_revocacion = motivo_revocacion;
        this.provincia = provincia;
        this.oficio = oficio;
        this.rubro = rubro;
        this.solucion_revocacion = solucion_revocacion;
        this.tipo_documento = tipo_documento;
        this.tipo_estudios = tipo_estudios;
        this.tipo_moneda = tipo_moneda;
        this.especialidades = especialidades;
        this.rango_dias = rango_dias;
        this.rango_precio = rango_precio;
        this.rango_turno = rango_turno;
        this.boxmadik_comision = boxMadikComision;
        this.centro_estudios = centro_estudios;
        this.tipo_cambio = tipo_cambio;
        this.propuesta_revocacion = propuesta_revocacion;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Pais> getPais() {
        return pais;
    }

    public void setPais(List<Pais> pais) {
        this.pais = pais;
    }

    public List<Banco> getBanco() {
        return banco;
    }

    public void setBanco(List<Banco> banco) {
        this.banco = banco;
    }

    public List<CalidadTrabajo> getCalidad_trabajo() {
        return calidad_trabajo;
    }

    public void setCalidad_trabajo(List<CalidadTrabajo> calidad_trabajo) {
        this.calidad_trabajo = calidad_trabajo;
    }

    public List<Departamento> getDepartamento() {
        return departamento;
    }

    public void setDepartamento(List<Departamento> departamento) {
        this.departamento = departamento;
    }

    public List<Distrito> getDistrito() {
        return distrito;
    }

    public void setDistrito(List<Distrito> distrito) {
        this.distrito = distrito;
    }

    public List<MotivoRevocacion> getMotivo_revocacion() {
        return motivo_revocacion;
    }

    public void setMotivo_revocacion(List<MotivoRevocacion> motivo_revocacion) {
        this.motivo_revocacion = motivo_revocacion;
    }

    public List<Provincia> getProvincia() {
        return provincia;
    }

    public void setProvincia(List<Provincia> provincia) {
        this.provincia = provincia;
    }

    public List<Oficio> getOficio() {
        return oficio;
    }

    public void setOficio(List<Oficio> oficio) {
        this.oficio = oficio;
    }

    public List<Rubro> getRubro() {
        return rubro;
    }

    public void setRubro(List<Rubro> rubro) {
        this.rubro = rubro;
    }

    public List<SolucionRevocacion> getSolucion_revocacion() {
        return solucion_revocacion;
    }

    public void setSolucion_revocacion(List<SolucionRevocacion> solucion_revocacion) {
        this.solucion_revocacion = solucion_revocacion;
    }

    public List<TipoDocumento> getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(List<TipoDocumento> tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public List<TipoEstudios> getTipo_estudios() {
        return tipo_estudios;
    }

    public void setTipo_estudios(List<TipoEstudios> tipo_estudios) {
        this.tipo_estudios = tipo_estudios;
    }

    public List<TipoMoneda> getTipo_moneda() {
        return tipo_moneda;
    }

    public void setTipo_moneda(List<TipoMoneda> tipo_moneda) {
        this.tipo_moneda = tipo_moneda;
    }

    public List<Especialidades> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }

    public List<RangoDias> getRango_dias() {
        return rango_dias;
    }

    public void setRango_dias(List<RangoDias> rango_dias) {
        this.rango_dias = rango_dias;
    }

    public List<RangoPrecio> getRango_precio() {
        return rango_precio;
    }

    public void setRango_precio(List<RangoPrecio> rango_precio) {
        this.rango_precio = rango_precio;
    }

    public List<RangoTurno> getRango_turno() {
        return rango_turno;
    }

    public void setRango_turno(List<RangoTurno> rango_turno) {
        this.rango_turno = rango_turno;
    }

    public List<BoxMadik_Comision> getComision() {
        return boxmadik_comision;
    }

    public void setComision(List<BoxMadik_Comision> boxMadikComision) {
        this.boxmadik_comision = boxMadikComision;
    }

    public List<BoxMadik_Comision> getBoxmadik_comision() {
        return boxmadik_comision;
    }

    public void setBoxmadik_comision(List<BoxMadik_Comision> boxmadik_comision) {
        this.boxmadik_comision = boxmadik_comision;
    }

    public List<CentroEstudios> getCentro_estudios() {
        return centro_estudios;
    }

    public void setCentro_estudios(List<CentroEstudios> centro_estudios) {
        this.centro_estudios = centro_estudios;
    }

    public List<TipoCambio> getTipo_cambio() {
        return tipo_cambio;
    }

    public void setTipo_cambio(List<TipoCambio> tipo_cambio) {
        this.tipo_cambio = tipo_cambio;
    }

    public List<PropuestaRevocacion> getPropuesta_revocacion() {
        return propuesta_revocacion;
    }

    public void setPropuesta_revocacion(List<PropuestaRevocacion> propuesta_revocacion) {
        this.propuesta_revocacion = propuesta_revocacion;
    }
}
