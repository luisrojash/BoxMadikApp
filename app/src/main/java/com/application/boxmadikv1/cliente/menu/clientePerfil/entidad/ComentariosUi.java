package com.application.boxmadikv1.cliente.menu.clientePerfil.entidad;

public class ComentariosUi {

    private String idComentario;
    private String fechaComentario;
    private String textoComentario;
    private String estrellas;
    private String imagenRubro;
    private String nombrePropuesta;

    public ComentariosUi() {
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public String getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(String estrellas) {
        this.estrellas = estrellas;
    }

    public String getImagenRubro() {
        return imagenRubro;
    }

    public void setImagenRubro(String imagenRubro) {
        this.imagenRubro = imagenRubro;
    }

    public String getNombrePropuesta() {
        return nombrePropuesta;
    }

    public void setNombrePropuesta(String nombrePropuesta) {
        this.nombrePropuesta = nombrePropuesta;
    }
}
