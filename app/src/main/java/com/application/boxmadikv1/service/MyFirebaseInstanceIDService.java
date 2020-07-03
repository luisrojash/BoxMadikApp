package com.application.boxmadikv1.service;


import android.util.Log;

import com.application.boxmadikv1.service.remote.NotificacionAceptoCotizacion;
import com.application.boxmadikv1.service.remote.NotificacionCreacionCotizacion;
import com.application.boxmadikv1.service.remote.NotificacionCreacionPropuesta;
import com.application.boxmadikv1.service.remote.NotificacionCreacionRevocacion;
import com.application.boxmadikv1.service.remote.NotificacionMensajeChat;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    public static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    //  private NotificationUtils notificationUtils;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "NEW_TOKENSS" + s);
        String tokenCelular = s;
        //guardarPreferenciasEncrypt(tokenCelular);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage == null) {
            Log.d(TAG, "NOTIFICACION RECIBIDA");
            return;
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            //handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            //Log.d(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                // handleDataMessage(json);
                Log.d(TAG, "try: " + json.toString());
                handleDataMessage(json);
            } catch (Exception e) {
                Log.d(TAG, "Exception: " + e.getMessage());
            }
        }

    }

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");
            //boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            String tipoNotificacion = data.getString("tipoNotificacion");
            JSONObject payload = data.getJSONObject("payload");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            //Log.e(TAG, "isBackground: " + isBackground);
            //Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            Log.e(TAG, "timestamp: " + timestamp);
            Log.e(TAG, "tipoNotificacion: " + tipoNotificacion);


            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {

                Log.e(TAG, "!NotificationUtils.isAppIsInBackground(getApplicationContext()");
                // app is in foreground, broadcast the push message
               /* Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("mensaje", message);
                pushNotification.putExtra("timeStamp",timestamp);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);*/
                initiValidaRealdata(tipoNotificacion, title, message, timestamp, imageUrl, payload);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                //notificationUtils.playNotificationSound();
            } else {
                Log.e(TAG, " else !NotificationUtils.isAppIsInBackground(getApplicationContext()");
                // app is in background, show the notification in notification tray
                initStartActivityValidar(tipoNotificacion, title, message, timestamp, imageUrl, payload);

            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    private void initiValidaRealdata(String tipoNotificacion, String title, String message, String timestamp, String imageUrl, JSONObject payload) {
        switch (tipoNotificacion) {
            case Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK:
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA:
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION:
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION");
                break;
            case Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION:
                NotificacionAceptoCotizacion notificacionAceptoCotizacion = new NotificacionAceptoCotizacion();
                notificacionAceptoCotizacion.sincroData(payload,tipoNotificacion,title,message,this);
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION:
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION");
                NotificacionCreacionPropuesta notificacionCreacionPropuesta = new NotificacionCreacionPropuesta();
                notificacionCreacionPropuesta.sincroData(payload, tipoNotificacion, title, message, this);

                break;
            case Constantes.TIPO_NOTIFICACION_CHAT_MENSAJE:
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_CHAT_MENSAJE");
                NotificacionMensajeChat notificacionMensajeChat = new NotificacionMensajeChat();
                notificacionMensajeChat.sincroData(payload,tipoNotificacion,title,message,this);
                break;
            default:
                break;
        }

    }

    private void initStartActivityValidar(String tipoNotificacion, String title, String message, String timestamp, String imageUrl, JSONObject payload) {
        switch (tipoNotificacion) {
            case Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK:
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA:
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA");
                NotificacionCreacionPropuesta notificacionCreacionPropuesta = new NotificacionCreacionPropuesta();
                notificacionCreacionPropuesta.llenarDataInitPerfilPropuesta(getApplicationContext(), title, message, timestamp, imageUrl, payload, tipoNotificacion);
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION:
                NotificacionCreacionRevocacion notificacionCreacionRevocacion = new NotificacionCreacionRevocacion();
                notificacionCreacionRevocacion.llenarData(getApplicationContext(), title, message, timestamp, imageUrl, payload, tipoNotificacion);

                break;
            case Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION:
                NotificacionAceptoCotizacion notificacionAceptoCotizacion = new NotificacionAceptoCotizacion();
                notificacionAceptoCotizacion.llenarData(getApplicationContext(), title, message, timestamp, imageUrl, payload, tipoNotificacion);
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION:
                NotificacionCreacionCotizacion notificacionCreacionCotizacion = new NotificacionCreacionCotizacion();
                notificacionCreacionCotizacion.llenarDataInitPerfilCotizacion(getApplicationContext(), title, message, timestamp, imageUrl, payload, tipoNotificacion);
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION");
                break;
            case Constantes.TIPO_NOTIFICACION_CHAT_MENSAJE:
                Log.e(TAG, " Constantes.TIPO_NOTIFICACION_CHAT_MENSAJE");
                NotificacionMensajeChat notificacionMensajeChat = new NotificacionMensajeChat();
                notificacionMensajeChat.llenarDataMensajeChat(getApplicationContext(), title, message, timestamp, imageUrl, payload, tipoNotificacion);

                break;
            default:
                break;
        }

    }


}
