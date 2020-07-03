package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ItemUi implements Parcelable {
    String codigoPropuesta;
    String nombrePropuesta;
    String imagePropuesta;
    String fechaPropuesta;
    String detallesPropuesta;
    String codigoRubro;
    String descripcionRubro;
    String codigoOficio;
    String descripcionOficio;
    String codigoRangoDias;
    String descripcionRangoDias;
    String codigoRangoTurno;
    String descripcionRangoTurno;
    String codigoRangoPrecio;
    String descripcionRangoPrecio;
    String codigoUsuarioPropuesta;
    String numeroCotizacion;
    String keyUser;
    String promedioCotizacion;
    String cotiEstado;
    String nombreDepartamento;
    String nombreDistrito;
    String paisCodigo;
    String propuestaEstado;
    String idCotizacion;
    String idUsuarioCotizacion;
    String usuNombreCliente;
    String usuAPellidosCliente;
    String usuRazonSocialCliente;
    String usuFotoCliente;
    String usuPaisImagenCliente;
    String montoOfico;


    List<EspecialidadesUi> especialidadesUiList;

    public ItemUi() {
        especialidadesUiList = new ArrayList<>();
    }

    public ItemUi(Parcel in) {
        especialidadesUiList = new ArrayList<>();
        readFromParcel(in);
    }

    public static final Creator<ItemUi> CREATOR = new Creator<ItemUi>() {
        @Override
        public ItemUi createFromParcel(Parcel in) {
            return new ItemUi(in);
        }

        @Override
        public ItemUi[] newArray(int size) {
            return new ItemUi[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigoPropuesta);
        dest.writeString(nombrePropuesta);
        dest.writeString(imagePropuesta);
        dest.writeString(fechaPropuesta);
        dest.writeString(detallesPropuesta);
        dest.writeString(codigoRubro);
        dest.writeString(descripcionRubro);
        dest.writeString(codigoOficio);
        dest.writeString(descripcionOficio);
        dest.writeString(codigoRangoDias);
        dest.writeString(descripcionRangoDias);
        dest.writeString(codigoRangoTurno);
        dest.writeString(descripcionRangoTurno);
        dest.writeString(codigoRangoPrecio);
        dest.writeString(descripcionRangoPrecio);
        dest.writeString(codigoUsuarioPropuesta);
        dest.writeString(numeroCotizacion);
        dest.writeString(keyUser);
        dest.writeString(promedioCotizacion);
        dest.writeString(cotiEstado);
        dest.writeString(nombreDepartamento);
        dest.writeString(nombreDistrito);
        dest.writeString(paisCodigo);
        dest.writeString(propuestaEstado);
        dest.writeString(idCotizacion);
        dest.writeString(idUsuarioCotizacion);

        dest.writeString(usuNombreCliente);
        dest.writeString(usuAPellidosCliente);
        dest.writeString(usuRazonSocialCliente);
        dest.writeString(usuFotoCliente);//usuPaisImagenCliente
        dest.writeString(usuPaisImagenCliente);
        dest.writeString(montoOfico);
        dest.writeTypedList(especialidadesUiList);
    }

    private void readFromParcel(Parcel in) {
        codigoPropuesta = in.readString();
        nombrePropuesta = in.readString();
        imagePropuesta = in.readString();
        fechaPropuesta = in.readString();
        detallesPropuesta = in.readString();
        codigoRubro = in.readString();
        descripcionRubro = in.readString();
        codigoOficio = in.readString();
        descripcionOficio = in.readString();
        codigoRangoDias = in.readString();
        descripcionRangoDias = in.readString();
        codigoRangoTurno = in.readString();
        descripcionRangoTurno = in.readString();
        codigoRangoPrecio = in.readString();
        descripcionRangoPrecio = in.readString();
        codigoUsuarioPropuesta = in.readString();
        numeroCotizacion = in.readString();
        keyUser = in.readString();
        promedioCotizacion = in.readString();
        cotiEstado = in.readString();
        nombreDepartamento = in.readString();
        nombreDistrito = in.readString();
        paisCodigo = in.readString();
        propuestaEstado = in.readString();
        idCotizacion = in.readString();
        idUsuarioCotizacion = in.readString();

        usuNombreCliente = in.readString();
        usuAPellidosCliente = in.readString();
        usuRazonSocialCliente = in.readString();
        usuFotoCliente = in.readString();
        usuPaisImagenCliente = in.readString();

        montoOfico = in.readString();
        in.readTypedList(especialidadesUiList, EspecialidadesUi.CREATOR);
    }

    public String getCodigoPropuesta() {
        return codigoPropuesta;
    }

    public void setCodigoPropuesta(String codigoPropuesta) {
        this.codigoPropuesta = codigoPropuesta;
    }

    public String getNombrePropuesta() {
        return nombrePropuesta;
    }

    public void setNombrePropuesta(String nombrePropuesta) {
        this.nombrePropuesta = nombrePropuesta;
    }

    public String getImagePropuesta() {
        return imagePropuesta;
    }

    public void setImagePropuesta(String imagePropuesta) {
        this.imagePropuesta = imagePropuesta;
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

    public String getCodigoRubro() {
        return codigoRubro;
    }

    public void setCodigoRubro(String codigoRubro) {
        this.codigoRubro = codigoRubro;
    }

    public String getDescripcionRubro() {
        return descripcionRubro;
    }

    public void setDescripcionRubro(String descripcionRubro) {
        this.descripcionRubro = descripcionRubro;
    }

    public String getCodigoOficio() {
        return codigoOficio;
    }

    public void setCodigoOficio(String codigoOficio) {
        this.codigoOficio = codigoOficio;
    }

    public String getDescripcionOficio() {
        return descripcionOficio;
    }

    public void setDescripcionOficio(String descripcionOficio) {
        this.descripcionOficio = descripcionOficio;
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

    public String getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(String numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public List<EspecialidadesUi> getEspecialidadesUiList() {
        return especialidadesUiList;
    }

    public void setEspecialidadesUiList(List<EspecialidadesUi> especialidadesUiList) {
        this.especialidadesUiList = especialidadesUiList;
    }

    public String getCodigoUsuarioPropuesta() {
        return codigoUsuarioPropuesta;
    }

    public void setCodigoUsuarioPropuesta(String codigoUsuarioPropuesta) {
        this.codigoUsuarioPropuesta = codigoUsuarioPropuesta;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    public String getPromedioCotizacion() {
        return promedioCotizacion;
    }

    public void setPromedioCotizacion(String promedioCotizacion) {
        this.promedioCotizacion = promedioCotizacion;
    }

    public String getCotiEstado() {
        return cotiEstado;
    }

    public void setCotiEstado(String cotiEstado) {
        this.cotiEstado = cotiEstado;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getPaisCodigo() {
        return paisCodigo;
    }

    public void setPaisCodigo(String paisCodigo) {
        this.paisCodigo = paisCodigo;
    }

    public String getPropuestaEstado() {
        return propuestaEstado;
    }

    public void setPropuestaEstado(String propuestaEstado) {
        this.propuestaEstado = propuestaEstado;
    }

    public String getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(String idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getIdUsuarioCotizacion() {
        return idUsuarioCotizacion;
    }

    public void setIdUsuarioCotizacion(String idUsuarioCotizacion) {
        this.idUsuarioCotizacion = idUsuarioCotizacion;
    }

    public String getUsuNombreCliente() {
        return usuNombreCliente;
    }

    public void setUsuNombreCliente(String usuNombreCliente) {
        this.usuNombreCliente = usuNombreCliente;
    }

    public String getUsuAPellidosCliente() {
        return usuAPellidosCliente;
    }

    public void setUsuAPellidosCliente(String usuAPellidosCliente) {
        this.usuAPellidosCliente = usuAPellidosCliente;
    }

    public String getUsuRazonSocialCliente() {
        return usuRazonSocialCliente;
    }

    public void setUsuRazonSocialCliente(String usuRazonSocialCliente) {
        this.usuRazonSocialCliente = usuRazonSocialCliente;
    }

    public String getUsuFotoCliente() {
        return usuFotoCliente;
    }

    public void setUsuFotoCliente(String usuFotoCliente) {
        this.usuFotoCliente = usuFotoCliente;
    }

    public String getUsuPaisImagenCliente() {
        return usuPaisImagenCliente;
    }

    public void setUsuPaisImagenCliente(String usuPaisImagenCliente) {
        this.usuPaisImagenCliente = usuPaisImagenCliente;
    }

    public String getMontoOfico() {
        return montoOfico;
    }

    public void setMontoOfico(String montoOfico) {
        this.montoOfico = montoOfico;
    }
    /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemUi that = (ItemUi) o;

        return codigoPropuesta.equals(that.codigoPropuesta) ;
    }

    @Override
    public int hashCode() {
        int result = 17;
        //result=31*result+age;
        result = 31 * result + (codigoPropuesta != null ? codigoPropuesta.hashCode() : 0);
        return result;
    }*/


}
