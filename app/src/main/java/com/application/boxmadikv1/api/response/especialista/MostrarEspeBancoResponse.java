package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostrarEspeBancoResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;

    @SerializedName("dataBank")
    @Expose
    private MostrarEspeBancoResp mostrarEspeBancoResp;

    public MostrarEspeBancoResponse(Boolean error, String mensaje, MostrarEspeBancoResp mostrarEspeBancoResp) {
        this.error = error;
        this.mensaje = mensaje;
        this.mostrarEspeBancoResp = mostrarEspeBancoResp;
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

    public MostrarEspeBancoResp getMostrarEspeBancoResp() {
        return mostrarEspeBancoResp;
    }

    public void setMostrarEspeBancoResp(MostrarEspeBancoResp mostrarEspeBancoResp) {
        this.mostrarEspeBancoResp = mostrarEspeBancoResp;
    }

    public class MostrarEspeBancoResp {
        private String bancoCodigo;
        private String bancoNombre;
        private String tipoCuentaCodigo;
        private String numerCuenta;
        private String numerCuentaInterbank;

        public MostrarEspeBancoResp(String bancoCodigo, String bancoNombre, String tipoCuentaCodigo, String numerCuenta, String numerCuentaInterbank) {
            this.bancoCodigo = bancoCodigo;
            this.bancoNombre = bancoNombre;
            this.tipoCuentaCodigo = tipoCuentaCodigo;
            this.numerCuenta = numerCuenta;
            this.numerCuentaInterbank = numerCuentaInterbank;
        }

        public String getBancoCodigo() {
            return bancoCodigo;
        }

        public void setBancoCodigo(String bancoCodigo) {
            this.bancoCodigo = bancoCodigo;
        }

        public String getBancoNombre() {
            return bancoNombre;
        }

        public void setBancoNombre(String bancoNombre) {
            this.bancoNombre = bancoNombre;
        }

        public String getTipoCuentaCodigo() {
            return tipoCuentaCodigo;
        }

        public void setTipoCuentaCodigo(String tipoCuentaCodigo) {
            this.tipoCuentaCodigo = tipoCuentaCodigo;
        }

        public String getNumerCuenta() {
            return numerCuenta;
        }

        public void setNumerCuenta(String numerCuenta) {
            this.numerCuenta = numerCuenta;
        }

        public String getNumerCuentaInterbank() {
            return numerCuentaInterbank;
        }

        public void setNumerCuentaInterbank(String numerCuentaInterbank) {
            this.numerCuentaInterbank = numerCuentaInterbank;
        }
    }
}
