package com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class EspecialidadUi implements Parcelable {
    String codigoEspecialidad;
    String descripcion;
    String estado;

    public EspecialidadUi() {
    }

    public EspecialidadUi(Parcel in) {
        readFromParcel(in);

    }

    private void readFromParcel(Parcel in) {
        codigoEspecialidad = in.readString();
        descripcion = in.readString();
        estado = in.readString();
    }

    public static final Creator<EspecialidadUi> CREATOR = new Creator<EspecialidadUi>() {
        @Override
        public EspecialidadUi createFromParcel(Parcel in) {
            return new EspecialidadUi(in);
        }

        @Override
        public EspecialidadUi[] newArray(int size) {
            return new EspecialidadUi[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigoEspecialidad);
        dest.writeString(descripcion);
        dest.writeString(estado);
    }

    public String getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



}
