package com.application.boxmadikv1.registraUser.entidad;

public class TipoDocumentoUi {
    String idTipoDcumento;
    String descripcion;

    public String getIdTipoDcumento() {
        return idTipoDcumento;
    }

    public void setIdTipoDcumento(String idTipoDcumento) {
        this.idTipoDcumento = idTipoDcumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
