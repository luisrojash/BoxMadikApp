package com.application.boxmadikv1.especialista.menu.edicionDatos.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class DatosEspecEditDireccion implements Parcelable {
    private String codigoDepartamento;
    private String nombreDepartamento;
    private String codigoProvincia;
    private String nombreProvincia;
    private String codigoDistrito;
    private String nombreDistrito;
    private String nombreDireccion;
    private String usuLatitud;
    private String usuLongitud;

    public DatosEspecEditDireccion() {
    }

    protected DatosEspecEditDireccion(Parcel in) {
        readFromParcel(in);

    }

    private void readFromParcel(Parcel in) {
        codigoDepartamento = in.readString();
        nombreDepartamento = in.readString();
        codigoProvincia = in.readString();
        nombreProvincia = in.readString();
        codigoDistrito = in.readString();
        nombreDistrito = in.readString();
        nombreDireccion = in.readString();
        usuLatitud = in.readString();
        usuLongitud = in.readString();
    }

    public static final Creator<DatosEspecEditDireccion> CREATOR = new Creator<DatosEspecEditDireccion>() {
        @Override
        public DatosEspecEditDireccion createFromParcel(Parcel in) {
            return new DatosEspecEditDireccion(in);
        }

        @Override
        public DatosEspecEditDireccion[] newArray(int size) {
            return new DatosEspecEditDireccion[size];
        }
    };

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getNombreDireccion() {
        return nombreDireccion;
    }

    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }

    public String getUsuLatitud() {
        return usuLatitud;
    }

    public void setUsuLatitud(String usuLatitud) {
        this.usuLatitud = usuLatitud;
    }

    public String getUsuLongitud() {
        return usuLongitud;
    }

    public void setUsuLongitud(String usuLongitud) {
        this.usuLongitud = usuLongitud;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigoDepartamento);
        dest.writeString(nombreDepartamento);
        dest.writeString(codigoProvincia);
        dest.writeString(nombreProvincia);
        dest.writeString(codigoDistrito);
        dest.writeString(nombreDistrito);
        dest.writeString(nombreDireccion);
        dest.writeString(usuLatitud);
        dest.writeString(usuLongitud);
    }
}
