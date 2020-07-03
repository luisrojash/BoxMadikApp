package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi;

import android.os.Parcel;
import android.os.Parcelable;

public class ComentariosUi implements Parcelable {

    private String nombrePropuesta;
    private String fechaPropuesta;
    private String detallePropuesta;
    private String fotoCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String estrellasEspec;
    private String comentarioEspec;
    private String imagenRubro;
    private String keyUser;

    public ComentariosUi() {
    }

    public ComentariosUi(String nombrePropuesta, String fechaPropuesta, String detallePropuesta, String fotoCliente, String nombreCliente, String apellidoCliente, String estrellasEspec, String comentarioEspec, String imagenRubro, String keyUser) {
        this.nombrePropuesta = nombrePropuesta;
        this.fechaPropuesta = fechaPropuesta;
        this.detallePropuesta = detallePropuesta;
        this.fotoCliente = fotoCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.estrellasEspec = estrellasEspec;
        this.comentarioEspec = comentarioEspec;
        this.imagenRubro = imagenRubro;
        this.keyUser = keyUser;
    }

    protected ComentariosUi(Parcel in) {
        readFromParcel(in);

    }

    private void readFromParcel(Parcel in) {
        nombrePropuesta = in.readString();
        fechaPropuesta = in.readString();
        detallePropuesta = in.readString();
        fotoCliente = in.readString();
        nombreCliente = in.readString();
        apellidoCliente = in.readString();
        estrellasEspec = in.readString();
        comentarioEspec = in.readString();
        imagenRubro = in.readString();
        keyUser = in.readString();
    }

    public static final Creator<ComentariosUi> CREATOR = new Creator<ComentariosUi>() {
        @Override
        public ComentariosUi createFromParcel(Parcel in) {
            return new ComentariosUi(in);
        }

        @Override
        public ComentariosUi[] newArray(int size) {
            return new ComentariosUi[size];
        }
    };

    public String getNombrePropuesta() {
        return nombrePropuesta;
    }

    public void setNombrePropuesta(String nombrePropuesta) {
        this.nombrePropuesta = nombrePropuesta;
    }

    public String getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(String fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public String getFotoCliente() {
        return fotoCliente;
    }

    public void setFotoCliente(String fotoCliente) {
        this.fotoCliente = fotoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getEstrellasEspec() {
        return estrellasEspec;
    }

    public void setEstrellasEspec(String estrellasEspec) {
        this.estrellasEspec = estrellasEspec;
    }

    public String getComentarioEspec() {
        return comentarioEspec;
    }

    public void setComentarioEspec(String comentarioEspec) {
        this.comentarioEspec = comentarioEspec;
    }

    public String getImagenRubro() {
        return imagenRubro;
    }

    public void setImagenRubro(String imagenRubro) {
        this.imagenRubro = imagenRubro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDetallePropuesta() {
        return detallePropuesta;
    }

    public void setDetallePropuesta(String detallePropuesta) {
        this.detallePropuesta = detallePropuesta;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombrePropuesta);
        dest.writeString(fechaPropuesta);
        dest.writeString(detallePropuesta);
        dest.writeString(fotoCliente);
        dest.writeString(nombreCliente);
        dest.writeString(apellidoCliente);
        dest.writeString(estrellasEspec);
        dest.writeString(comentarioEspec);
        dest.writeString(imagenRubro);
        dest.writeString(keyUser);
    }
}
