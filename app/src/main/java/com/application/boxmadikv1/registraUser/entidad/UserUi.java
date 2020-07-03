package com.application.boxmadikv1.registraUser.entidad;

public class UserUi {
    String keyUser;
    String tipoDoc;
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String email;
    String clave;
    String repetirClave;
    String celular;
    String foto;
    String imageStringBase64;
    String razonSocial;
    String dia;
    String mes;
    String anio;

    String tipoIdDocumento;
    String tipoIDPais;

    public UserUi() {
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getImageStringBase64() {
        return imageStringBase64;
    }

    public void setImageStringBase64(String imageStringBase64) {
        this.imageStringBase64 = imageStringBase64;
    }

    public String getRepetirClave() {
        return repetirClave;
    }

    public void setRepetirClave(String repetirClave) {
        this.repetirClave = repetirClave;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipoIdDocumento() {
        return tipoIdDocumento;
    }

    public void setTipoIdDocumento(String tipoIdDocumento) {
        this.tipoIdDocumento = tipoIdDocumento;
    }

    public String getTipoIDPais() {
        return tipoIDPais;
    }

    public void setTipoIDPais(String tipoIDPais) {
        this.tipoIDPais = tipoIDPais;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
