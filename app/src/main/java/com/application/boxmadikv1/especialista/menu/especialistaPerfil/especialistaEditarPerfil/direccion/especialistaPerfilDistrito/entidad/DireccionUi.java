package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class DireccionUi implements Parcelable {
    private String nombreEdit;
    private String apellidosEdit;
    private String celularEdit;
    private String usuarioFoto;
    private String codigoDepartamento;
    private String codigoProvincia;
    private String codigoDistrito;
    private String descripcionDireccion;
    private String latitud;
    private String longitud;
    private DistritoUi distritoUi;

    public DireccionUi() {
    }

    protected DireccionUi(Parcel in) {
        readFromParcel(in);

    }

    private void readFromParcel(Parcel in) {
        nombreEdit = in.readString();
        apellidosEdit = in.readString();
        celularEdit = in.readString();
        usuarioFoto = in.readString();
        codigoDepartamento = in.readString();
        codigoProvincia = in.readString();
        codigoDistrito = in.readString();
        descripcionDireccion = in.readString();
        latitud = in.readString();
        longitud = in.readString();
        distritoUi = in.readParcelable(DistritoUi.class.getClassLoader());
    }

    public static final Creator<DireccionUi> CREATOR = new Creator<DireccionUi>() {
        @Override
        public DireccionUi createFromParcel(Parcel in) {
            return new DireccionUi(in);
        }

        @Override
        public DireccionUi[] newArray(int size) {
            return new DireccionUi[size];
        }
    };

    public String getNombreEdit() {
        return nombreEdit;
    }

    public void setNombreEdit(String nombreEdit) {
        this.nombreEdit = nombreEdit;
    }

    public String getApellidosEdit() {
        return apellidosEdit;
    }

    public void setApellidosEdit(String apellidosEdit) {
        this.apellidosEdit = apellidosEdit;
    }

    public String getCelularEdit() {
        return celularEdit;
    }

    public void setCelularEdit(String celularEdit) {
        this.celularEdit = celularEdit;
    }

    public String getUsuarioFoto() {
        return usuarioFoto;
    }

    public void setUsuarioFoto(String usuarioFoto) {
        this.usuarioFoto = usuarioFoto;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public String getDescripcionDireccion() {
        return descripcionDireccion;
    }

    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public DistritoUi getDistritoUi() {
        return distritoUi;
    }

    public void setDistritoUi(DistritoUi distritoUi) {
        this.distritoUi = distritoUi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreEdit);
        dest.writeString(apellidosEdit);
        dest.writeString(celularEdit);
        dest.writeString(usuarioFoto);
        dest.writeString(codigoDepartamento);
        dest.writeString(codigoProvincia);
        dest.writeString(codigoDistrito);
        dest.writeString(descripcionDireccion);
        dest.writeString(latitud);
        dest.writeString(longitud);
        dest.writeParcelable(distritoUi, flags);
    }
}
