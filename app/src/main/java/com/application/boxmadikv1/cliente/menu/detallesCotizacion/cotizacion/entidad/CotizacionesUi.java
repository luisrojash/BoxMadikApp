package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad;

import android.os.Parcel;
import android.os.Parcelable;

public class CotizacionesUi implements Parcelable {
    String idPropuesta;
    String idCotizacion;
    String estadoCotizacion;
    String estadoPropuesta;
    String idUsuarioCotizacion;
    String nombreEspecialista;
    String imagen;
    String fecha;
    String monto;
    String puntuacion;
    String cotiDescripcion;
    String cotiPendiente;
    String cotiFinalizado;
    String cotiAceptado;
    String usuDireccion;
    String paisImagen;
    String usuCelular;
    String usuEmail;
    String usuRazonSocial;
    String fotoUsuarioCotizacion;

    public CotizacionesUi() {
    }

    public CotizacionesUi(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        idPropuesta = in.readString();
        idCotizacion = in.readString();
        estadoCotizacion = in.readString();
        estadoPropuesta = in.readString();
        idUsuarioCotizacion = in.readString();
        nombreEspecialista = in.readString();
        imagen = in.readString();
        fecha = in.readString();
        monto = in.readString();
        puntuacion = in.readString();
        cotiDescripcion = in.readString();
        cotiPendiente = in.readString();
        cotiFinalizado = in.readString();
        cotiAceptado = in.readString();
        usuDireccion = in.readString();
        paisImagen = in.readString();
        usuCelular = in.readString();
        usuEmail = in.readString();
        usuRazonSocial = in.readString();
        fotoUsuarioCotizacion = in.readString();
    }

    public static final Creator<CotizacionesUi> CREATOR = new Creator<CotizacionesUi>() {
        @Override
        public CotizacionesUi createFromParcel(Parcel in) {
            return new CotizacionesUi(in);
        }

        @Override
        public CotizacionesUi[] newArray(int size) {
            return new CotizacionesUi[size];
        }
    };

    public String getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(String idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(String estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }

    public String getEstadoPropuesta() {
        return estadoPropuesta;
    }

    public void setEstadoPropuesta(String estadoPropuesta) {
        this.estadoPropuesta = estadoPropuesta;
    }

    public String getIdUsuarioCotizacion() {
        return idUsuarioCotizacion;
    }

    public void setIdUsuarioCotizacion(String idUsuarioCotizacion) {
        this.idUsuarioCotizacion = idUsuarioCotizacion;
    }

    public String getNombreEspecialista() {
        return nombreEspecialista;
    }

    public void setNombreEspecialista(String nombreEspecialista) {
        this.nombreEspecialista = nombreEspecialista;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCotiDescripcion() {
        return cotiDescripcion;
    }

    public void setCotiDescripcion(String cotiDescripcion) {
        this.cotiDescripcion = cotiDescripcion;
    }

    public String getCotiPendiente() {
        return cotiPendiente;
    }

    public void setCotiPendiente(String cotiPendiente) {
        this.cotiPendiente = cotiPendiente;
    }

    public String getCotiFinalizado() {
        return cotiFinalizado;
    }

    public void setCotiFinalizado(String cotiFinalizado) {
        this.cotiFinalizado = cotiFinalizado;
    }

    public String getCotiAceptado() {
        return cotiAceptado;
    }

    public void setCotiAceptado(String cotiAceptado) {
        this.cotiAceptado = cotiAceptado;
    }

    public String getUsuDireccion() {
        return usuDireccion;
    }

    public void setUsuDireccion(String usuDireccion) {
        this.usuDireccion = usuDireccion;
    }

    public String getPaisImagen() {
        return paisImagen;
    }

    public void setPaisImagen(String paisImagen) {
        this.paisImagen = paisImagen;
    }

    public String getUsuCelular() {
        return usuCelular;
    }

    public void setUsuCelular(String usuCelular) {
        this.usuCelular = usuCelular;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getUsuRazonSocial() {
        return usuRazonSocial;
    }

    public void setUsuRazonSocial(String usuRazonSocial) {
        this.usuRazonSocial = usuRazonSocial;
    }

    public String getFotoUsuarioCotizacion() {
        return fotoUsuarioCotizacion;
    }

    public void setFotoUsuarioCotizacion(String fotoUsuarioCotizacion) {
        this.fotoUsuarioCotizacion = fotoUsuarioCotizacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idPropuesta);
        dest.writeString(idCotizacion);
        dest.writeString(estadoCotizacion);
        dest.writeString(estadoPropuesta);
        dest.writeString(idUsuarioCotizacion);
        dest.writeString(nombreEspecialista);
        dest.writeString(imagen);
        dest.writeString(fecha);
        dest.writeString(monto);
        dest.writeString(puntuacion);
        dest.writeString(cotiDescripcion);
        dest.writeString(cotiPendiente);
        dest.writeString(cotiFinalizado);
        dest.writeString(cotiAceptado);
        dest.writeString(usuDireccion);
        dest.writeString(paisImagen);
        dest.writeString(usuCelular);
        dest.writeString(usuEmail);
        dest.writeString(usuRazonSocial);  //fotoUsuarioCotizacion
        dest.writeString(fotoUsuarioCotizacion);
    }
}
