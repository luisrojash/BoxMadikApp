package com.application.boxmadikv1.service.remote;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.PerfilPropuestaActivity;
import com.application.boxmadikv1.service.MyFirebaseInstanceIDService;
import com.application.boxmadikv1.utils.Config;
import com.application.boxmadikv1.utils.NotificationUtils;

import org.json.JSONObject;

public class NotificacionAceptoCotizacion {

    public static final String TAG = NotificacionAceptoCotizacion.class.getSimpleName();

    public NotificacionAceptoCotizacion() {
    }


    public void llenarData(Context applicationContext, String title, String message, String timestamp, String imageUrl, JSONObject payload, String tipoNotificacion) {

        Log.d(TAG, "payload :  " + payload.toString());
        try {
            /*Datos Propuesta*/
           String codigoPropuesta, nombrePropuesta, descripcionPropuesta, fechaPropuesta,
                    nombreDepartamentoPropuesta, estadoPropuesta, codigoUsuarioPropuesta;

           /* codigoPropuesta = payload.getString("codigoPropuesta");
            nombrePropuesta = payload.getString("nombrePropuesta");
            descripcionPropuesta = payload.getString("detallePropuesta");
            fechaPropuesta = payload.getString("fechaPropuesta");
            nombreDepartamentoPropuesta = payload.getString("departamenPropuesta");
            estadoPropuesta = payload.getString("estadoPropuesta");
            codigoUsuarioPropuesta = payload.getString("codigoUsuarioPropuesta");*/



            String paisImage = payload.getString("paisImage");
            String propuestaCodigo = payload.getString("propuestaCodigo");
            String rubroImagen = payload.getString("rubroImagen");
            String propuestaFecha = payload.getString("rubroImagen");
            String propuestaDetalle = payload.getString("propuestaDetalle");
            String propuestaTitulo = payload.getString("propuestaTitulo");
            String propuestaUsuarioCodigo = payload.getString("propuestaUsuarioCodigo");
            String propuestaNumCotizacion = payload.getString("propuestaNumCotizacion");
            String propuestaPromedioCoti = payload.getString("propuestaPromedioCoti");
            String rubroDescripcion = payload.getString("rubroDescripcion");
            String rubroCodigo = payload.getString("rubroCodigo");
            String oficioDescripcion = payload.getString("oficioDescripcion");
            String ofiMontoOficio = payload.getString("ofiMontoOficio");
            String rangoDiasDescripcion = payload.getString("rangoDiasDescripcion");
            String rangoDiasCodigo = payload.getString("rangoDiasCodigo");
            String rangoTurnoDescripcion = payload.getString("rangoTurnoDescripcion");
            String rangoTurnoCodigo = payload.getString("rangoTurnoCodigo");
            //String usuarioCodigoPropuesta = payload.getString("usuarioCodigoPropuesta");
            //String cotizacionCodigo = payload.getString("cotizacionCodigo");
            //String cotizacionCodigoUsuario = payload.getString("cotizacionCodigoUsuario");
            String cotizacionEstado = payload.getString("cotizacionEstado");
            String departamentoNombre = payload.getString("departamentoNombre");
            String provinciaNombre = payload.getString("provinciaNombre");
            String distritoNombre = payload.getString("distritoNombre");
            String propuestaEstado = payload.getString("propuestaEstado");

            ItemUi itemUi = new ItemUi();
            itemUi.setPaisCodigo("51");
            itemUi.setUsuPaisImagenCliente(paisImage);
            itemUi.setCodigoPropuesta(propuestaCodigo);
            itemUi.setNombrePropuesta(propuestaTitulo);
            itemUi.setImagePropuesta(rubroImagen);
            itemUi.setFechaPropuesta(propuestaFecha);
            itemUi.setDetallesPropuesta(propuestaDetalle);
            itemUi.setCodigoRubro(rubroCodigo);
            itemUi.setDescripcionRubro(rubroDescripcion);
            //itemUi.setCodigoOficio(propuestaInicial.getOficio_Ofi_codigo());
            itemUi.setDescripcionOficio(oficioDescripcion);
            itemUi.setCodigoRangoDias(rangoDiasCodigo);
            itemUi.setDescripcionRangoDias(rangoDiasDescripcion);
            itemUi.setCodigoRangoTurno(rangoTurnoCodigo);
            itemUi.setDescripcionRangoTurno(rangoTurnoDescripcion);
            //itemUi.setCodigoRangoPrecio(ra);
            itemUi.setCodigoUsuarioPropuesta(propuestaUsuarioCodigo);
            itemUi.setCotiEstado(cotizacionEstado);
            itemUi.setPropuestaEstado(propuestaEstado);
            itemUi.setPromedioCotizacion(propuestaPromedioCoti);
            itemUi.setNumeroCotizacion(propuestaNumCotizacion);
            itemUi.setNombreDepartamento(departamentoNombre);
            itemUi.setNombreDistrito(distritoNombre);
           // itemUi.setDescripcionRangoPrecio(propuestaInicial.getRangoPrecionDescripcion());
            itemUi.setMontoOfico(ofiMontoOficio);




            int tipoCreadaCotiza = 10;
            Intent resultIntent = new Intent(applicationContext, PerfilPropuestaActivity.class);
            String tipoActivity = "notificacionAceptoCotizacionEspec";
            resultIntent.putExtra("itemUi", itemUi);
            resultIntent.putExtra("notificacion", tipoActivity);
            resultIntent.putExtra("tipoDataEnviar", tipoCreadaCotiza);
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

    public void sincroData(JSONObject payload, String tipoNotificacion, String titulo, String mensaje, MyFirebaseInstanceIDService myFirebaseInstanceIDService) {
        Log.d(TAG, "payload :  " + payload.toString());
        try {
            String codigoUsuarioCotizado = payload.getString("codigoUsuarioCotizado");
            String codigoPais = payload.getString("codigoPais");
            // String tipoNotiCodigo = payload.getString("tipoNotiCodigo");tipoNotificacion
            String tipoNotiCodigo = payload.getString("tipoNotiCodigo");


            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("codigoUsuarioCotizado", codigoUsuarioCotizado);
            pushNotification.putExtra("codigoPais", codigoPais);
            pushNotification.putExtra("tipoNotiCodigo", tipoNotiCodigo);
            pushNotification.putExtra("tipoNotificacion", tipoNotificacion);
            pushNotification.putExtra("titulo", titulo);
            pushNotification.putExtra("mensaje", mensaje);
            LocalBroadcastManager.getInstance(myFirebaseInstanceIDService).sendBroadcast(pushNotification);
        } catch (Throwable tx) {
            Log.e(TAG, "Could not parse malformed JSON: /" + payload + "/");
        }
    }
}
