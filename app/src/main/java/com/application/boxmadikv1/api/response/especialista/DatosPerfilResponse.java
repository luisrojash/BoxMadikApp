package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosPerfilResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("datosPerfilCliente")
    @Expose
    private DatosPerfilClienteResponse datosPerfilClienteResponse;

    public DatosPerfilResponse(Boolean error, String mensaje, DatosPerfilClienteResponse datosPerfilClienteResponse) {
        this.error = error;
        this.mensaje = mensaje;
        this.datosPerfilClienteResponse = datosPerfilClienteResponse;
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

    public DatosPerfilClienteResponse getDatosPerfilClienteResponse() {
        return datosPerfilClienteResponse;
    }

    public void setDatosPerfilClienteResponse(DatosPerfilClienteResponse datosPerfilClienteResponse) {
        this.datosPerfilClienteResponse = datosPerfilClienteResponse;
    }

    public class DatosPerfilClienteResponse {
        String total_propuestas_creada;
        String total_propuestas_proceso;
        String total_propuestas_finalizada;
        String nombre;
        String apellidos;
        String foto;
        String usuDireccion;
        String totalPendientes;
        String totalPagados;
        String usuCalificacion;
        String paisImagen;
        String usuRazonSocial;

        public DatosPerfilClienteResponse(String total_propuestas_creada,
                                          String total_propuestas_proceso, String total_propuestas_finalizada,
                                          String nombre, String apellidos, String foto, String usuDireccion,
                                          String totalPendientes, String totalPagados, String usuCalificacion,
                                          String paisImagen, String usuRazonSocial) {
            this.total_propuestas_creada = total_propuestas_creada;
            this.total_propuestas_proceso = total_propuestas_proceso;
            this.total_propuestas_finalizada = total_propuestas_finalizada;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.foto = foto;
            this.usuDireccion = usuDireccion;
            this.totalPendientes = totalPendientes;
            this.totalPagados = totalPagados;
            this.usuCalificacion = usuCalificacion;
            this.paisImagen = paisImagen;
            this.usuRazonSocial = usuRazonSocial;
        }

        public String getTotal_propuestas_creada() {
            return total_propuestas_creada;
        }

        public void setTotal_propuestas_creada(String total_propuestas_creada) {
            this.total_propuestas_creada = total_propuestas_creada;
        }

        public String getTotal_propuestas_proceso() {
            return total_propuestas_proceso;
        }

        public void setTotal_propuestas_proceso(String total_propuestas_proceso) {
            this.total_propuestas_proceso = total_propuestas_proceso;
        }

        public String getTotal_propuestas_finalizada() {
            return total_propuestas_finalizada;
        }

        public void setTotal_propuestas_finalizada(String total_propuestas_finalizada) {
            this.total_propuestas_finalizada = total_propuestas_finalizada;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public String getUsuDireccion() {
            return usuDireccion;
        }

        public void setUsuDireccion(String usuDireccion) {
            this.usuDireccion = usuDireccion;
        }

        public String getTotalPendientes() {
            return totalPendientes;
        }

        public void setTotalPendientes(String totalPendientes) {
            this.totalPendientes = totalPendientes;
        }

        public String getTotalPagados() {
            return totalPagados;
        }

        public void setTotalPagados(String totalPagados) {
            this.totalPagados = totalPagados;
        }

        public String getUsuCalificacion() {
            return usuCalificacion;
        }

        public void setUsuCalificacion(String usuCalificacion) {
            this.usuCalificacion = usuCalificacion;
        }

        public String getPaisImagen() {
            return paisImagen;
        }

        public void setPaisImagen(String paisImagen) {
            this.paisImagen = paisImagen;
        }

        public String getUsuRazonSocial() {
            return usuRazonSocial;
        }

        public void setUsuRazonSocial(String usuRazonSocial) {
            this.usuRazonSocial = usuRazonSocial;
        }
    }
}
