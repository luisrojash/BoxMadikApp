package com.application.boxmadikv1.service.remote;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.rptRevocacion.RespuestaRevocacionActivity;
import com.application.boxmadikv1.utils.NotificationUtils;

import org.json.JSONObject;

public class NotificacionCreacionRevocacion {
    public static final String TAG = NotificacionCreacionRevocacion.class.getSimpleName();

    public NotificacionCreacionRevocacion() {

    }

    public void llenarData(Context applicationContext, String title, String message, String timestamp, String imageUrl, JSONObject payload, String tipoNotificacion) {

        Log.d(TAG, "payload :  " + payload.toString());
        try {
            /*Datos Propuesta*/
           /* String codigoPropuesta, nombrePropuesta, descripcionPropuesta, fechaPropuesta,
                    nombreDepartamentoPropuesta, estadoPropuesta, codigoUsuarioPropuesta, rubroImagen;

            codigoPropuesta = payload.getString("codigoPropuesta");
            nombrePropuesta = payload.getString("nombrePropuesta");
            descripcionPropuesta = payload.getString("detallePropuesta");
            fechaPropuesta = payload.getString("fechaPropuesta");
            nombreDepartamentoPropuesta = payload.getString("departamenPropuesta");
            estadoPropuesta = payload.getString("estadoPropuesta");
            codigoUsuarioPropuesta = payload.getString("codigoUsuarioPropuesta");
            rubroImagen = payload.getString("rubroImagen");*/


            Intent resultIntent = new Intent(applicationContext, MenuEspecialistaActivity.class);
            mostrarNotificacion(applicationContext, title, message, timestamp, resultIntent, imageUrl, tipoNotificacion);
        } catch (Throwable tx) {
            Log.e(TAG, "Could not parse malformed JSON: /" + payload + "/");
        }
    }

    private void mostrarNotificacion(Context applicationContext, String title, String message, String timestamp, Intent resultIntent, String imageUrl, String tipoNotificacion) {
        NotificationUtils notificationUtils = new NotificationUtils(applicationContext);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.validarIconoNotificacion(title, message, timestamp, resultIntent, imageUrl, tipoNotificacion);
        // notificationUtils.showNotificationMessage(title, message, timestamp, resultIntent, imageUrl);
    }
}
