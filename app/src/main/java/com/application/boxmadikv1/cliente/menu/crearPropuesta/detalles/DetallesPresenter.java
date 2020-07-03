package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles;

import android.content.Intent;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

import java.util.ArrayList;

public interface DetallesPresenter extends BaseActivityPresenter<DetallesView> {

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onClickPublicarPropuesta(String titulo, String detalles);

    void onInitKeyUser(String keyUser, ArrayList<String> stringListIdRubros,String codigoPais);

    void eliminarImagenSegundo();

    void eliminarImagenUno();

    void onActivityBackPressed();

    void onClickUbicacion();


    void onUbicacionNueva(String codigoDepartamento, String nombreDepartamento, String codigoProvincia, String nombreProvincia, String codigoDistrito, String nombreDistrito);
}
