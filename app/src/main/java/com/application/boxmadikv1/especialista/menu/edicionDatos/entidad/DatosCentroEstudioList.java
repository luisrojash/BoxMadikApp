package com.application.boxmadikv1.especialista.menu.edicionDatos.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class DatosCentroEstudioList implements Parcelable {
    private String codigoEspeEstudios;
    private String nombreCurso;
    private String codigoTipoEstudios;
    private String nombreTipoEstudiosM;
    private String codigoCentroEstudios;
    private String nombreCentroEstudios;
    private String mesInicio;
    private String anioInicio;
    private String mesFin;
    private String anioFin;
    private String fechaInicio;
    private String fechaFin;

    public DatosCentroEstudioList() {
    }

    public DatosCentroEstudioList(String codigoEspeEstudios, String nombreCurso, String codigoTipoEstudios,
                                  String nombreTipoEstudiosM, String codigoCentroEstudios, String nombreCentroEstudios,
                                  String mesInicio, String anioInicio, String mesFin, String anioFin,
                                  String fechaInicio,String fechaFin) {
        this.codigoEspeEstudios = codigoEspeEstudios;
        this.nombreCurso = nombreCurso;
        this.codigoTipoEstudios = codigoTipoEstudios;
        this.nombreTipoEstudiosM = nombreTipoEstudiosM;
        this.codigoCentroEstudios = codigoCentroEstudios;
        this.nombreCentroEstudios = nombreCentroEstudios;
        this.mesInicio = mesInicio;
        this.anioInicio = anioInicio;
        this.mesFin = mesFin;
        this.anioFin = anioFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    private void readFromParcel(Parcel in) {
        codigoEspeEstudios=in.readString();
        nombreCurso = in.readString();
        codigoTipoEstudios = in.readString();
        nombreTipoEstudiosM = in.readString();
        codigoCentroEstudios = in.readString();
        nombreCentroEstudios = in.readString();
        mesInicio = in.readString();
        anioInicio = in.readString();
        mesFin = in.readString();
        anioFin = in.readString();
        fechaInicio = in.readString();
        fechaFin = in.readString();
    }

    protected DatosCentroEstudioList(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<DatosCentroEstudioList> CREATOR = new Creator<DatosCentroEstudioList>() {
        @Override
        public DatosCentroEstudioList createFromParcel(Parcel in) {
            return new DatosCentroEstudioList(in);
        }

        @Override
        public DatosCentroEstudioList[] newArray(int size) {
            return new DatosCentroEstudioList[size];
        }
    };

    public String getCodigoEspeEstudios() {
        return codigoEspeEstudios;
    }

    public void setCodigoEspeEstudios(String codigoEspeEstudios) {
        this.codigoEspeEstudios = codigoEspeEstudios;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getCodigoTipoEstudios() {
        return codigoTipoEstudios;
    }

    public void setCodigoTipoEstudios(String codigoTipoEstudios) {
        this.codigoTipoEstudios = codigoTipoEstudios;
    }

    public String getNombreTipoEstudiosM() {
        return nombreTipoEstudiosM;
    }

    public void setNombreTipoEstudiosM(String nombreTipoEstudiosM) {
        this.nombreTipoEstudiosM = nombreTipoEstudiosM;
    }

    public String getCodigoCentroEstudios() {
        return codigoCentroEstudios;
    }

    public void setCodigoCentroEstudios(String codigoCentroEstudios) {
        this.codigoCentroEstudios = codigoCentroEstudios;
    }

    public String getNombreCentroEstudios() {
        return nombreCentroEstudios;
    }

    public void setNombreCentroEstudios(String nombreCentroEstudios) {
        this.nombreCentroEstudios = nombreCentroEstudios;
    }

    public String getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(String mesInicio) {
        this.mesInicio = mesInicio;
    }

    public String getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(String anioInicio) {
        this.anioInicio = anioInicio;
    }

    public String getMesFin() {
        return mesFin;
    }

    public void setMesFin(String mesFin) {
        this.mesFin = mesFin;
    }

    public String getAnioFin() {
        return anioFin;
    }

    public void setAnioFin(String anioFin) {
        this.anioFin = anioFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigoEspeEstudios);
        dest.writeString(nombreCurso);
        dest.writeString(codigoTipoEstudios);
        dest.writeString(nombreTipoEstudiosM);
        dest.writeString(codigoCentroEstudios);
        dest.writeString(nombreCentroEstudios);
        dest.writeString(mesInicio);
        dest.writeString(anioInicio);
        dest.writeString(mesFin);
        dest.writeString(anioFin);
        dest.writeString(fechaInicio);
        dest.writeString(fechaFin);
    }
}
