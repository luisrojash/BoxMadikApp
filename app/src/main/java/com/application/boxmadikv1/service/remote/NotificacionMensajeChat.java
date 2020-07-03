package com.application.boxmadikv1.service.remote;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity;
import com.application.boxmadikv1.service.MyFirebaseInstanceIDService;
import com.application.boxmadikv1.utils.Config;
import com.application.boxmadikv1.utils.NotificationUtils;

import org.json.JSONObject;

public class NotificacionMensajeChat {

    public static final String TAG = NotificacionMensajeChat.class.getSimpleName();

    public NotificacionMensajeChat() {
    }


    public void sincroData(JSONObject payload,String tipoNotificacion,String titulo,String mensaje2, MyFirebaseInstanceIDService myFirebaseInstanceIDService) {

        Log.d(TAG, "payload :  " + payload.toString());
        try {
            String codigoUsuario = payload.getString("usu_codigo");
            String mensaje = payload.getString("mensaje");
            Log.d(TAG,"mensaje : " + mensaje);
            String timestamp = payload.getString("datetime");
            String codigoMensaje = payload.getString("codigoMensaje");
            String codigoGrupoChat = payload.getString("grupo_chat_codigo");
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


            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("titulo", titulo);
            pushNotification.putExtra("mensaje", mensaje);
            pushNotification.putExtra("timeStamp", timestamp);
            pushNotification.putExtra("codigoUsuario", codigoUsuario);
            pushNotification.putExtra("codigoMensaje", codigoMensaje);
            pushNotification.putExtra("codigoGrupoChat", codigoGrupoChat);//tipoNotificacion
            pushNotification.putExtra("tipoNotificacion", tipoNotificacion);
            LocalBroadcastManager.getInstance(myFirebaseInstanceIDService).sendBroadcast(pushNotification);
        } catch (Throwable tx) {
            Log.e(TAG, "Could not parse malformed JSON: /" + payload + "/");
        }
    }

    public void llenarDataMensajeChat(Context applicationContext, String title, String message, String timestamp, String imageUrl, JSONObject payload, String tipoNotificacion) {

        try {
            /*Datos Propuesta*/
            String codigoPropuesta, nombrePropuesta, descripcionPropuesta, fechaPropuesta,
                    nombreDepartamentoPropuesta, estadoPropuesta, codigoUsuarioPropuesta, rubroImagen;

            String codigoUsuario = payload.getString("usu_codigo");
            String mensaje = payload.getString("mensaje");
            String timestamp2 = payload.getString("datetime");
            String codigoMensaje = payload.getString("codigoMensaje");


            Intent resultIntent = new Intent(applicationContext, SeleccionUserActivity.class);
            mostrarNotificacion(applicationContext, title, message, timestamp2, resultIntent, imageUrl, tipoNotificacion);
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
