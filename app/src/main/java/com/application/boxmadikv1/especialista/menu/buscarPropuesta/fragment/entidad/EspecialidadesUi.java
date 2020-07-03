package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class EspecialidadesUi implements Parcelable{
    String codigoEspecialidad;
    String descripcionEspecialidad;

    public EspecialidadesUi() {
    }


    public static final Creator<EspecialidadesUi> CREATOR = new Creator<EspecialidadesUi>() {
        @Override
        public EspecialidadesUi createFromParcel(Parcel in) {
            return new EspecialidadesUi(in);
        }

        @Override
        public EspecialidadesUi[] newArray(int size) {
            return new EspecialidadesUi[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigoEspecialidad);
        dest.writeString(descripcionEspecialidad);
    }


    public EspecialidadesUi(Parcel in) {
        readFromParcel(in);
    }
    private void readFromParcel(Parcel in) {
        codigoEspecialidad = in.readString();
        descripcionEspecialidad = in.readString();
    }

    public String getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public String getDescripcionEspecialidad() {
        return descripcionEspecialidad;
    }

    public void setDescripcionEspecialidad(String descripcionEspecialidad) {
        this.descripcionEspecialidad = descripcionEspecialidad;
    }
}
