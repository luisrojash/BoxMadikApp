package com.application.boxmadikv1.especialista.menu.edicionDatos.entidad;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DatosEspecZonaTrabajo implements Parcelable {

    private String codigoDepartamento;
    private String nombreDepartamento;
    private String codigoProvincia;
    private String nombreProvincia;
    private List<DatosEspecZonaTrabList> zonaTrabLists;

    public DatosEspecZonaTrabajo() {
        zonaTrabLists = new ArrayList<DatosEspecZonaTrabList>();
    }


    private void readFromParcel(Parcel in) {
        zonaTrabLists = new ArrayList<DatosEspecZonaTrabList>();
        codigoDepartamento = in.readString();
        nombreDepartamento = in.readString();
        codigoProvincia = in.readString();
        nombreProvincia = in.readString();
        in.readTypedList(zonaTrabLists, DatosEspecZonaTrabList.CREATOR);
    }


    protected DatosEspecZonaTrabajo(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<DatosEspecZonaTrabajo> CREATOR = new Creator<DatosEspecZonaTrabajo>() {
        @Override
        public DatosEspecZonaTrabajo createFromParcel(Parcel in) {
            return new DatosEspecZonaTrabajo(in);
        }

        @Override
        public DatosEspecZonaTrabajo[] newArray(int size) {
            return new DatosEspecZonaTrabajo[size];
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

    public List<DatosEspecZonaTrabList> getZonaTrabLists() {
        return zonaTrabLists;
    }

    public void setZonaTrabLists(List<DatosEspecZonaTrabList> zonaTrabLists) {
        this.zonaTrabLists = zonaTrabLists;
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
        dest.writeTypedList(zonaTrabLists);
    }


}
