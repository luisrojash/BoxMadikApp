package com.application.boxmadikv1.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DefaultNotiTipoPropuesta {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("propuestaInicial")
    @Expose
    private NotiPropuestaResponse notiPropuestaResponse;

    public DefaultNotiTipoPropuesta() {
    }

    public DefaultNotiTipoPropuesta(Boolean error, String mensaje, NotiPropuestaResponse notiPropuestaResponse) {
        this.error = error;
        this.mensaje = mensaje;
        this.notiPropuestaResponse = notiPropuestaResponse;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public NotiPropuestaResponse getNotiPropuestaResponse() {
        return notiPropuestaResponse;
    }

    public void setNotiPropuestaResponse(NotiPropuestaResponse notiPropuestaResponse) {
        this.notiPropuestaResponse = notiPropuestaResponse;
    }

    public class NotiPropuestaResponse {
        private String Pri_Codigo;
        private String Pri_Titulo;
        private String Pri_Fecha;
        private String Pri_Detalle;
        private String rubro_Rub_Codigo;
        private String nombreRubro;
        private String rubroImagen;
        private String oficio_Ofi_codigo;
        private String nombreOficio;
        private String rango_dias_Rad_Item;
        private String nombreRangoDias;
        private String rango_turno_Rat_Item;
        private String nombreRangoTurno;
        private String Pri_Estado;
        private String Promedio_coti;
        private String Num_cotizacion;
        private String Depa_Desc;
        private String Dist_Desc;
        private String rango_precio_Ran_Item;
        private String usuarioPropuesta;

        public String getPri_Codigo() {
            return Pri_Codigo;
        }

        public void setPri_Codigo(String pri_Codigo) {
            Pri_Codigo = pri_Codigo;
        }

        public String getPri_Titulo() {
            return Pri_Titulo;
        }

        public void setPri_Titulo(String pri_Titulo) {
            Pri_Titulo = pri_Titulo;
        }

        public String getPri_Fecha() {
            return Pri_Fecha;
        }

        public void setPri_Fecha(String pri_Fecha) {
            Pri_Fecha = pri_Fecha;
        }

        public String getPri_Detalle() {
            return Pri_Detalle;
        }

        public void setPri_Detalle(String pri_Detalle) {
            Pri_Detalle = pri_Detalle;
        }

        public String getRubro_Rub_Codigo() {
            return rubro_Rub_Codigo;
        }

        public void setRubro_Rub_Codigo(String rubro_Rub_Codigo) {
            this.rubro_Rub_Codigo = rubro_Rub_Codigo;
        }

        public String getNombreRubro() {
            return nombreRubro;
        }

        public void setNombreRubro(String nombreRubro) {
            this.nombreRubro = nombreRubro;
        }

        public String getRubroImagen() {
            return rubroImagen;
        }

        public void setRubroImagen(String rubroImagen) {
            this.rubroImagen = rubroImagen;
        }

        public String getOficio_Ofi_codigo() {
            return oficio_Ofi_codigo;
        }

        public void setOficio_Ofi_codigo(String oficio_Ofi_codigo) {
            this.oficio_Ofi_codigo = oficio_Ofi_codigo;
        }

        public String getNombreOficio() {
            return nombreOficio;
        }

        public void setNombreOficio(String nombreOficio) {
            this.nombreOficio = nombreOficio;
        }

        public String getRango_dias_Rad_Item() {
            return rango_dias_Rad_Item;
        }

        public void setRango_dias_Rad_Item(String rango_dias_Rad_Item) {
            this.rango_dias_Rad_Item = rango_dias_Rad_Item;
        }

        public String getNombreRangoDias() {
            return nombreRangoDias;
        }

        public void setNombreRangoDias(String nombreRangoDias) {
            this.nombreRangoDias = nombreRangoDias;
        }

        public String getRango_turno_Rat_Item() {
            return rango_turno_Rat_Item;
        }

        public void setRango_turno_Rat_Item(String rango_turno_Rat_Item) {
            this.rango_turno_Rat_Item = rango_turno_Rat_Item;
        }

        public String getPri_Estado() {
            return Pri_Estado;
        }

        public void setPri_Estado(String pri_Estado) {
            Pri_Estado = pri_Estado;
        }

        public String getPromedio_coti() {
            return Promedio_coti;
        }

        public void setPromedio_coti(String promedio_coti) {
            Promedio_coti = promedio_coti;
        }

        public String getNum_cotizacion() {
            return Num_cotizacion;
        }

        public void setNum_cotizacion(String num_cotizacion) {
            Num_cotizacion = num_cotizacion;
        }

        public String getDepa_Desc() {
            return Depa_Desc;
        }

        public void setDepa_Desc(String depa_Desc) {
            Depa_Desc = depa_Desc;
        }

        public String getDist_Desc() {
            return Dist_Desc;
        }

        public void setDist_Desc(String dist_Desc) {
            Dist_Desc = dist_Desc;
        }

        public String getNombreRangoTurno() {
            return nombreRangoTurno;
        }

        public void setNombreRangoTurno(String nombreRangoTurno) {
            this.nombreRangoTurno = nombreRangoTurno;
        }

        public String getRango_precio_Ran_Item() {
            return rango_precio_Ran_Item;
        }

        public void setRango_precio_Ran_Item(String rango_precio_Ran_Item) {
            this.rango_precio_Ran_Item = rango_precio_Ran_Item;
        }

        public String getUsuarioPropuesta() {
            return usuarioPropuesta;
        }

        public void setUsuarioPropuesta(String usuarioPropuesta) {
            this.usuarioPropuesta = usuarioPropuesta;
        }
    }




}
