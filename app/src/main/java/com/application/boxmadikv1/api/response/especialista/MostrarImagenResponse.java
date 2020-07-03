package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostrarImagenResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("imagenes")
    @Expose
    ImagenesResponse imagenesResponse;

    public MostrarImagenResponse(Boolean error, String mensaje, ImagenesResponse imagenesResponse) {
        this.error = error;
        this.mensaje = mensaje;
        this.imagenesResponse = imagenesResponse;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ImagenesResponse getImagenesResponse() {
        return imagenesResponse;
    }

    public void setImagenesResponse(ImagenesResponse imagenesResponse) {
        this.imagenesResponse = imagenesResponse;
    }

    public class ImagenesResponse {
        private String Pri_foto1;
        private String Pri_foto2;

        public ImagenesResponse(String pri_foto1, String pri_foto2) {
            Pri_foto1 = pri_foto1;
            Pri_foto2 = pri_foto2;
        }

        public String getPri_foto1() {
            return Pri_foto1;
        }

        public void setPri_foto1(String pri_foto1) {
            Pri_foto1 = pri_foto1;
        }

        public String getPri_foto2() {
            return Pri_foto2;
        }

        public void setPri_foto2(String pri_foto2) {
            Pri_foto2 = pri_foto2;
        }
    }
}
