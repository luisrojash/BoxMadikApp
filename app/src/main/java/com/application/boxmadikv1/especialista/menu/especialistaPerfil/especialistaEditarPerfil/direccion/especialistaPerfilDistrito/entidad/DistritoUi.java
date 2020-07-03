package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DistritoUi implements Parcelable {
    private String codigoDepartamento;
    private String codigoProvincia;
    private List<String> codigoDistritoList;

    public DistritoUi() {
    }

    protected DistritoUi(Parcel in) {
        readFromParcel(in);

    }
    private void readFromParcel(Parcel in) {
        codigoDepartamento = in.readString();
        codigoProvincia = in.readString();
        codigoDistritoList = in.createStringArrayList();
    }

    public static final Creator<DistritoUi> CREATOR = new Creator<DistritoUi>() {
        @Override
        public DistritoUi createFromParcel(Parcel in) {
            return new DistritoUi(in);
        }

        @Override
        public DistritoUi[] newArray(int size) {
            return new DistritoUi[size];
        }
    };

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

    public List<String> getCodigoDistritoList() {
        return codigoDistritoList;
    }

    public void setCodigoDistritoList(List<String> codigoDistritoList) {
        this.codigoDistritoList = codigoDistritoList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigoDepartamento);
        dest.writeString(codigoProvincia);
        dest.writeStringList(codigoDistritoList);
    }
}
