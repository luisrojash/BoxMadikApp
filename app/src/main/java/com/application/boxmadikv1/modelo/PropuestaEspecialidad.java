package com.application.boxmadikv1.modelo;

public class PropuestaEspecialidad {
    private int PE_Codigo;
    private String PE_descripcion;
    private String PE_estado;
    private String especialidades_Espe_codigo;
    private String propuesta_inicial_Pri_Codigo;
    private String rubro_rub_codigo;
    private String oficio_ofi_codigo;

    public PropuestaEspecialidad() {
    }

    public int getPE_Codigo() {
        return PE_Codigo;
    }

    public void setPE_Codigo(int PE_Codigo) {
        this.PE_Codigo = PE_Codigo;
    }

    public String getPE_descripcion() {
        return PE_descripcion;
    }

    public void setPE_descripcion(String PE_descripcion) {
        this.PE_descripcion = PE_descripcion;
    }

    public String getPE_estado() {
        return PE_estado;
    }

    public void setPE_estado(String PE_estado) {
        this.PE_estado = PE_estado;
    }

    public String getEspecialidades_Espe_codigo() {
        return especialidades_Espe_codigo;
    }

    public void setEspecialidades_Espe_codigo(String especialidades_Espe_codigo) {
        this.especialidades_Espe_codigo = especialidades_Espe_codigo;
    }

    public String getPropuesta_inicial_Pri_Codigo() {
        return propuesta_inicial_Pri_Codigo;
    }

    public void setPropuesta_inicial_Pri_Codigo(String propuesta_inicial_Pri_Codigo) {
        this.propuesta_inicial_Pri_Codigo = propuesta_inicial_Pri_Codigo;
    }

    public String getRubro_rub_codigo() {
        return rubro_rub_codigo;
    }

    public void setRubro_rub_codigo(String rubro_rub_codigo) {
        this.rubro_rub_codigo = rubro_rub_codigo;
    }

    public String getOficio_ofi_codigo() {
        return oficio_ofi_codigo;
    }

    public void setOficio_ofi_codigo(String oficio_ofi_codigo) {
        this.oficio_ofi_codigo = oficio_ofi_codigo;
    }
}
