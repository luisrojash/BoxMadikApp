package com.application.boxmadikv1.especialista.menu.edicionDatos.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class DatosEspecZonaTrabList implements Parcelable {
    private String codigoDistrito;
    private String nombreDistrito;

    public DatosEspecZonaTrabList() {
    }

    private void readFromParcel(Parcel in) {
        codigoDistrito = in.readString();
        nombreDistrito = in.readString();
    }

    protected DatosEspecZonaTrabList(Parcel in) {
        readFromParcel(in);

    }

    public static final Creator<DatosEspecZonaTrabList> CREATOR = new Creator<DatosEspecZonaTrabList>() {
        @Override
        public DatosEspecZonaTrabList createFromParcel(Parcel in) {
            return new DatosEspecZonaTrabList(in);
        }

        @Override
        public DatosEspecZonaTrabList[] newArray(int size) {
            return new DatosEspecZonaTrabList[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigoDistrito);
        dest.writeString(nombreDistrito);
    }
}
