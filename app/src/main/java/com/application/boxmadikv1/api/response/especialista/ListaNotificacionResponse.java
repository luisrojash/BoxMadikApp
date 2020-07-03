package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaNotificacionResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("listaNotiCliente")
    @Expose
    private List<ListaNotificacionResponse.NotificacioneResponse> responseList;

    public ListaNotificacionResponse(Boolean error, String mensaje, List<ListaNotificacionResponse.NotificacioneResponse> listaCurResponses) {
        this.error = error;
        this.mensaje = mensaje;
        this.responseList = listaCurResponses;
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

    public List<NotificacioneResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<NotificacioneResponse> responseList) {
        this.responseList = responseList;
    }

    public class NotificacioneResponse {
        private String Noti_codigo;
        private String Noti_descripcion;
        private String Pri_Titulo;
        private String Noti_fecha;
        private String Tnot_icono;
        private String Noti_estado;
        private String Noti_leido;
        private String Tnot_codigo;
        private String Gn_codigo;
        private String Pri_Codigo;
        private String usuario_Usu_Codigo;
        private String Usu_Codigo_Docu;
        private String Pri_Estado;
        private String Coti_Estado;

        /*Datos Propuesta Inicial*/
        /*private String Pri_Codigo;
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
        private String Pri_Estado;
        private String Promedio_coti;
        private String Num_cotizacion;
        private String Depa_Desc;
        private String Dist_Desc;
*/


        public NotificacioneResponse() {
        }

        public String getNoti_codigo() {
            return Noti_codigo;
        }

        public void setNoti_codigo(String noti_codigo) {
            Noti_codigo = noti_codigo;
        }

        public String getNoti_descripcion() {
            return Noti_descripcion;
        }

        public void setNoti_descripcion(String noti_descripcion) {
            Noti_descripcion = noti_descripcion;
        }

        public String getPri_Titulo() {
            return Pri_Titulo;
        }

        public void setPri_Titulo(String pri_Titulo) {
            Pri_Titulo = pri_Titulo;
        }

        public String getNoti_fecha() {
            return Noti_fecha;
        }

        public void setNoti_fecha(String noti_fecha) {
            Noti_fecha = noti_fecha;
        }

        public String getTnot_icono() {
            return Tnot_icono;
        }

        public void setTnot_icono(String tnot_icono) {
            Tnot_icono = tnot_icono;
        }

        public String getNoti_estado() {
            return Noti_estado;
        }

        public void setNoti_estado(String noti_estado) {
            Noti_estado = noti_estado;
        }

        public String getNoti_leido() {
            return Noti_leido;
        }

        public void setNoti_leido(String noti_leido) {
            Noti_leido = noti_leido;
        }

        public String getTnot_codigo() {
            return Tnot_codigo;
        }

        public void setTnot_codigo(String tnot_codigo) {
            Tnot_codigo = tnot_codigo;
        }

        public String getGn_codigo() {
            return Gn_codigo;
        }

        public void setGn_codigo(String gn_codigo) {
            Gn_codigo = gn_codigo;
        }

        public String getPri_Codigo() {
            return Pri_Codigo;
        }

        public void setPri_Codigo(String pri_Codigo) {
            Pri_Codigo = pri_Codigo;
        }

        public String getUsuario_Usu_Codigo() {
            return usuario_Usu_Codigo;
        }

        public void setUsuario_Usu_Codigo(String usuario_Usu_Codigo) {
            this.usuario_Usu_Codigo = usuario_Usu_Codigo;
        }

        public String getUsu_Codigo_Docu() {
            return Usu_Codigo_Docu;
        }

        public void setUsu_Codigo_Docu(String usu_Codigo_Docu) {
            Usu_Codigo_Docu = usu_Codigo_Docu;
        }

        public String getPri_Estado() {
            return Pri_Estado;
        }

        public void setPri_Estado(String pri_Estado) {
            Pri_Estado = pri_Estado;
        }

        public String getCoti_Estado() {
            return Coti_Estado;
        }

        public void setCoti_Estado(String coti_Estado) {
            Coti_Estado = coti_Estado;
        }
    }
}
