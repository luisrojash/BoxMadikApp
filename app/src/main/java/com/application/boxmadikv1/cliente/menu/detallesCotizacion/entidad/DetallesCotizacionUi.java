package com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DetallesCotizacionUi implements Parcelable {

    /*Guarda los siguientes estados:\\\\n
    (0= Cancelado;
     1=Pendiente;
      2= En Proceso;
      3= Finalizado;
       4= Pagado;
        5= Revocado)*/
    public static final int ESTADO_CANCELADO = 0;
    public static final int ESTADO_PENDIENTE = 1;
    public static final int ESTADO_PROCESO = 2;
    public static final int ESTADO_FINALIZADO = 3;
    public static final int ESTADO_PAGADOS = 4;
    public static final int ESTADO_REVOCADOS = 5;


    String idPropuesta;
    String nombreProyecto;
    int numeroCotizacion;
    String fechaPropuesta;
    String detallesPropuesta;
    String codigoRangoDias;
    String descripcionRangoDias;
    String codigoRangoTurno;
    String descripcionRangoTurno;
    String codigoRangoPrecio;
    String descripcionRangoPrecio;
    String codigoRubro;
    String nombreRubro;
    String codigoOficio;
    String nombreOficio;
    String imageRubro;
    String costoPromedio;
    String imagenPropuesta1;
    String imagenPropuesta2;
    int tipoEstado;
    String keyUser;
    String usuarioCodigoPropuesta;
    String nombreDepartamento;
    String nombreDistrito;
    String paisCodigo;
    List<EspecialidadUi> especialidadUiList;

    public DetallesCotizacionUi() {
        especialidadUiList = new ArrayList<>();
    }

    public DetallesCotizacionUi(Parcel in) {
        especialidadUiList = new ArrayList<>();
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        idPropuesta = in.readString();
        nombreProyecto = in.readString();
        numeroCotizacion = in.readInt();
        fechaPropuesta = in.readString();
        detallesPropuesta = in.readString();
        codigoRangoDias = in.readString();
        descripcionRangoDias = in.readString();
        codigoRangoTurno = in.readString();
        descripcionRangoTurno = in.readString();
        codigoRangoPrecio = in.readString();
        descripcionRangoPrecio = in.readString();
        codigoRubro = in.readString();
        nombreRubro = in.readString();
        codigoOficio = in.readString();
        nombreOficio = in.readString();
        imageRubro = in.readString();
        costoPromedio = in.readString();
        imagenPropuesta1 = in.readString();
        imagenPropuesta2 = in.readString();
        tipoEstado = in.readInt();
        keyUser = in.readString();
        usuarioCodigoPropuesta = in.readString();
        nombreDepartamento = in.readString();
        nombreDistrito = in.readString();
        paisCodigo = in.readString();
        in.readTypedList(especialidadUiList, EspecialidadUi.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idPropuesta);
        dest.writeString(nombreProyecto);
        dest.writeInt(numeroCotizacion);
        dest.writeString(fechaPropuesta);
        dest.writeString(detallesPropuesta);
        dest.writeString(codigoRangoDias);
        dest.writeString(descripcionRangoDias);
        dest.writeString(codigoRangoTurno);
        dest.writeString(descripcionRangoTurno);
        dest.writeString(codigoRangoPrecio);
        dest.writeString(descripcionRangoPrecio);
        dest.writeString(codigoRubro);
        dest.writeString(nombreRubro);
        dest.writeString(codigoOficio);
        dest.writeString(nombreOficio);
        dest.writeString(imageRubro);
        dest.writeString(costoPromedio);
        dest.writeString(imagenPropuesta1);
        dest.writeString(imagenPropuesta2);
        dest.writeInt(tipoEstado);
        dest.writeString(keyUser);
        dest.writeString(usuarioCodigoPropuesta);
        dest.writeString(nombreDepartamento);
        dest.writeString(nombreDistrito);
        dest.writeString(paisCodigo);
        dest.writeTypedList(especialidadUiList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetallesCotizacionUi> CREATOR = new Creator<DetallesCotizacionUi>() {
        @Override
        public DetallesCotizacionUi createFromParcel(Parcel in) {
            return new DetallesCotizacionUi(in);
        }

        @Override
        public DetallesCotizacionUi[] newArray(int size) {
            return new DetallesCotizacionUi[size];
        }
    };

    public String getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public int getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(int numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public String getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(String fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public String getDetallesPropuesta() {
        return detallesPropuesta;
    }

    public void setDetallesPropuesta(String detallesPropuesta) {
        this.detallesPropuesta = detallesPropuesta;
    }

    public String getCodigoRangoDias() {
        return codigoRangoDias;
    }

    public void setCodigoRangoDias(String codigoRangoDias) {
        this.codigoRangoDias = codigoRangoDias;
    }

    public String getDescripcionRangoDias() {
        return descripcionRangoDias;
    }

    public void setDescripcionRangoDias(String descripcionRangoDias) {
        this.descripcionRangoDias = descripcionRangoDias;
    }

    public String getCodigoRangoTurno() {
        return codigoRangoTurno;
    }

    public void setCodigoRangoTurno(String codigoRangoTurno) {
        this.codigoRangoTurno = codigoRangoTurno;
    }

    public String getDescripcionRangoTurno() {
        return descripcionRangoTurno;
    }

    public void setDescripcionRangoTurno(String descripcionRangoTurno) {
        this.descripcionRangoTurno = descripcionRangoTurno;
    }

    public String getCodigoRangoPrecio() {
        return codigoRangoPrecio;
    }

    public void setCodigoRangoPrecio(String codigoRangoPrecio) {
        this.codigoRangoPrecio = codigoRangoPrecio;
    }

    public String getDescripcionRangoPrecio() {
        return descripcionRangoPrecio;
    }

    public void setDescripcionRangoPrecio(String descripcionRangoPrecio) {
        this.descripcionRangoPrecio = descripcionRangoPrecio;
    }

    public String getCodigoRubro() {
        return codigoRubro;
    }

    public void setCodigoRubro(String codigoRubro) {
        this.codigoRubro = codigoRubro;
    }

    public String getCodigoOficio() {
        return codigoOficio;
    }

    public void setCodigoOficio(String codigoOficio) {
        this.codigoOficio = codigoOficio;
    }

    public String getImageRubro() {
        return imageRubro;
    }

    public void setImageRubro(String imageRubro) {
        this.imageRubro = imageRubro;
    }

    public String getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(String costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public String getImagenPropuesta1() {
        return imagenPropuesta1;
    }

    public void setImagenPropuesta1(String imagenPropuesta1) {
        this.imagenPropuesta1 = imagenPropuesta1;
    }

    public String getImagenPropuesta2() {
        return imagenPropuesta2;
    }

    public void setImagenPropuesta2(String imagenPropuesta2) {
        this.imagenPropuesta2 = imagenPropuesta2;
    }

    public int getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(int tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public List<EspecialidadUi> getEspecialidadUiList() {
        return especialidadUiList;
    }

    public void setEspecialidadUiList(List<EspecialidadUi> especialidadUiList) {
        this.especialidadUiList = especialidadUiList;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    public String getNombreOficio() {
        return nombreOficio;
    }

    public void setNombreOficio(String nombreOficio) {
        this.nombreOficio = nombreOficio;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    public String getUsuarioCodigoPropuesta() {
        return usuarioCodigoPropuesta;
    }

    public void setUsuarioCodigoPropuesta(String usuarioCodigoPropuesta) {
        this.usuarioCodigoPropuesta = usuarioCodigoPropuesta;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getPaisCodigo() {
        return paisCodigo;
    }

    public void setPaisCodigo(String paisCodigo) {
        this.paisCodigo = paisCodigo;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
}
