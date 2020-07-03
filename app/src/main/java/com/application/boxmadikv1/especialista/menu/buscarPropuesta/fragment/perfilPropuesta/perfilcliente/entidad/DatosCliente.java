package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad;

public class DatosCliente {
    private String idCodigo;
    private String nombre;
    private String apellido;
    private String foto;
    private String estrellas;
    private String comentario;
    private String usuRazonSocial;
    private String propuestaTitulo;
    private String fechaRegistroComentario;
    private String rubroImagen;

    public DatosCliente() {
    }

    public String getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(String idCodigo) {
        this.idCodigo = idCodigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(String estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuRazonSocial() {
        return usuRazonSocial;
    }

    public void setUsuRazonSocial(String usuRazonSocial) {
        this.usuRazonSocial = usuRazonSocial;
    }

    public String getPropuestaTitulo() {
        return propuestaTitulo;
    }

    public void setPropuestaTitulo(String propuestaTitulo) {
        this.propuestaTitulo = propuestaTitulo;
    }

    public String getFechaRegistroComentario() {
        return fechaRegistroComentario;
    }

    public void setFechaRegistroComentario(String fechaRegistroComentario) {
        this.fechaRegistroComentario = fechaRegistroComentario;
    }

    public String getRubroImagen() {
        return rubroImagen;
    }

    public void setRubroImagen(String rubroImagen) {
        this.rubroImagen = rubroImagen;
    }
}
