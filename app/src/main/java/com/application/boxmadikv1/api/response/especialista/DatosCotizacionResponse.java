package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosCotizacionResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("datosCotizacion")
    @Expose
    private DatosCotizacionEspecialistaResponse datosCotizacionEspecialistaResponse;

    public DatosCotizacionResponse(Boolean error, String mensaje, DatosCotizacionEspecialistaResponse datosCotizacionEspecialistaResponse) {
        this.error = error;
        this.mensaje = mensaje;
        this.datosCotizacionEspecialistaResponse = datosCotizacionEspecialistaResponse;
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

    public DatosCotizacionEspecialistaResponse getDatosCotizacionEspecialistaResponse() {
        return datosCotizacionEspecialistaResponse;
    }

    public void setDatosCotizacionEspecialistaResponse(DatosCotizacionEspecialistaResponse datosCotizacionEspecialistaResponse) {
        this.datosCotizacionEspecialistaResponse = datosCotizacionEspecialistaResponse;
    }

    public class DatosCotizacionEspecialistaResponse {
        String Coti_Monto_Ini;
        String Coti_Monto_Comi;
        String Coti_Monto_Total;
        String Coti_Monto_Dolar;
        String Coti_FecIni;
        String Coti_FecFin;
        String Coti_Descripcion;

        public DatosCotizacionEspecialistaResponse(String coti_Monto_Ini, String coti_Monto_Comi, String coti_Monto_Total, String coti_Monto_Dolar, String coti_FecIni, String coti_FecFin, String coti_Descripcion) {
            Coti_Monto_Ini = coti_Monto_Ini;
            Coti_Monto_Comi = coti_Monto_Comi;
            Coti_Monto_Total = coti_Monto_Total;
            Coti_Monto_Dolar = coti_Monto_Dolar;
            Coti_FecIni = coti_FecIni;
            Coti_FecFin = coti_FecFin;
            Coti_Descripcion = coti_Descripcion;
        }

        public String getCoti_Monto_Ini() {
            return Coti_Monto_Ini;
        }

        public void setCoti_Monto_Ini(String coti_Monto_Ini) {
            Coti_Monto_Ini = coti_Monto_Ini;
        }

        public String getCoti_Monto_Comi() {
            return Coti_Monto_Comi;
        }

        public void setCoti_Monto_Comi(String coti_Monto_Comi) {
            Coti_Monto_Comi = coti_Monto_Comi;
        }

        public String getCoti_Monto_Total() {
            return Coti_Monto_Total;
        }

        public void setCoti_Monto_Total(String coti_Monto_Total) {
            Coti_Monto_Total = coti_Monto_Total;
        }

        public String getCoti_Monto_Dolar() {
            return Coti_Monto_Dolar;
        }

        public void setCoti_Monto_Dolar(String coti_Monto_Dolar) {
            Coti_Monto_Dolar = coti_Monto_Dolar;
        }

        public String getCoti_FecIni() {
            return Coti_FecIni;
        }

        public void setCoti_FecIni(String coti_FecIni) {
            Coti_FecIni = coti_FecIni;
        }

        public String getCoti_FecFin() {
            return Coti_FecFin;
        }

        public void setCoti_FecFin(String coti_FecFin) {
            Coti_FecFin = coti_FecFin;
        }

        public String getCoti_Descripcion() {
            return Coti_Descripcion;
        }

        public void setCoti_Descripcion(String coti_Descripcion) {
            Coti_Descripcion = coti_Descripcion;
        }
    }
}
