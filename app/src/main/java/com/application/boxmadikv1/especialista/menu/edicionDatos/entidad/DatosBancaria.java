package com.application.boxmadikv1.especialista.menu.edicionDatos.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class DatosBancaria implements Parcelable{
    private String bancoCodigo;
    private String bancoNombre;
    private String tipoCuentaCodigo;
    private String numerCuenta;
    private String numerCuentaInterbank;

    public DatosBancaria() {
    }

    public DatosBancaria(String bancoCodigo, String bancoNombre, String tipoCuentaCodigo, String numerCuenta, String numerCuentaInterbank) {
        this.bancoCodigo = bancoCodigo;
        this.bancoNombre = bancoNombre;
        this.tipoCuentaCodigo = tipoCuentaCodigo;
        this.numerCuenta = numerCuenta;
        this.numerCuentaInterbank = numerCuentaInterbank;
    }

    private void readFromParcel(Parcel in) {
        bancoCodigo = in.readString();
        bancoNombre = in.readString();
        tipoCuentaCodigo = in.readString();
        numerCuenta = in.readString();
        numerCuentaInterbank = in.readString();
    }
    protected DatosBancaria(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<DatosBancaria> CREATOR = new Creator<DatosBancaria>() {
        @Override
        public DatosBancaria createFromParcel(Parcel in) {
            return new DatosBancaria(in);
        }

        @Override
        public DatosBancaria[] newArray(int size) {
            return new DatosBancaria[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bancoCodigo);
        dest.writeString(bancoNombre);
        dest.writeString(tipoCuentaCodigo);
        dest.writeString(numerCuenta);
        dest.writeString(numerCuentaInterbank);
    }
}
