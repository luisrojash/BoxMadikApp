package com.application.boxmadikv1.service.remote;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.DatosCotizacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.NotificationUtils;

import org.json.JSONObject;

public class NotificacionCreacionCotizacion {

    public static final String TAG = NotificacionCreacionCotizacion.class.getSimpleName();

    public NotificacionCreacionCotizacion() {
    }

    public void llenarDataInitPerfilCotizacion(Context applicationContext, String title, String message, String timestamp, String imageUrl, JSONObject payload, String tipoNotificacion) {

        Log.d(TAG, "payload :  " + payload.toString());
        try {
            /*Datos Propuesta*/
            String codigoPropuesta, nombrePropuesta, descripcionPropuesta, fechaPropuesta,
                    nombreDepartamentoPropuesta, estadoPropuesta, codigoUsuarioPropuesta, rubroImagen;

            codigoPropuesta = payload.getString("codigoPropuesta");
            nombrePropuesta = payload.getString("nombrePropuesta");
            descripcionPropuesta = payload.getString("detallePropuesta");
            fechaPropuesta = payload.getString("fechaPropuesta");
            nombreDepartamentoPropuesta = payload.getString("departamenPropuesta");
            estadoPropuesta = payload.getString("estadoPropuesta");
            codigoUsuarioPropuesta = payload.getString("codigoUsuarioPropuesta");
            rubroImagen = payload.getString("rubroImagen");


            Log.d(TAG,"codigoPropuesta : " + codigoPropuesta+
                    "codigoUsuarioPropuesta : " + codigoUsuarioPropuesta
            );

            DetallesCotizacionUi detallesCotizacionUi = new DetallesCotizacionUi();
            detallesCotizacionUi.setIdPropuesta(codigoPropuesta);
            detallesCotizacionUi.setNombreProyecto(nombrePropuesta);
            detallesCotizacionUi.setDetallesPropuesta(descripcionPropuesta);
            detallesCotizacionUi.setFechaPropuesta(fechaPropuesta);
            detallesCotizacionUi.setNombreDepartamento(nombreDepartamentoPropuesta);
            detallesCotizacionUi.setUsuarioCodigoPropuesta(codigoUsuarioPropuesta);

            int estadoPropuestaIn = Integer.parseInt(estadoPropuesta);
            detallesCotizacionUi.setTipoEstado(estadoPropuestaIn);
            detallesCotizacionUi.setKeyUser(codigoUsuarioPropuesta);
            detallesCotizacionUi.setImageRubro(rubroImagen);






            /*Datos Cotizacion*/
            String codigoCotizacion, codigoUsuarioCotizacion, usufotoCotizado, estadoCotizacion, cotiPendiente, cotiFinalizado,
                    cotiAceptado, coti_monto_inicial, nombresUsuCoti, estrellasUsuario, imagenPaisUsuCotizado, cotifecha;

            codigoCotizacion = payload.getString("codigoCotizacion");
            codigoUsuarioCotizacion = payload.getString("codigoUsuarioCotizacion");
            usufotoCotizado = payload.getString("usufotoCotizado");
            estadoCotizacion = payload.getString("estadoCotizacion");
            cotiPendiente = payload.getString("cotiPendiente");
            cotiFinalizado = payload.getString("cotiFinalizado");
            cotiAceptado = payload.getString("cotiAceptado");
            coti_monto_inicial = payload.getString("coti_monto_inicial");
            nombresUsuCoti = payload.getString("nombresUsuCoti");
            estrellasUsuario = payload.getString("estrellasUsuario");
            cotifecha = payload.getString("cotifecha");
            imagenPaisUsuCotizado = payload.getString("imagenPaisUsuCotizado");

            Log.d(TAG,"codigoCotizacion : " + codigoCotizacion+
                    " / codigoUsuarioCotizacion : " + codigoUsuarioCotizacion+
                    " / estadoCotizacion : " + estadoCotizacion+
                    " / estadoPropuesta : " + estadoPropuesta
            );

            CotizacionesUi cotizacionesUi = new CotizacionesUi();
            cotizacionesUi.setIdPropuesta(codigoPropuesta);
            cotizacionesUi.setImagen(usufotoCotizado);
            cotizacionesUi.setIdCotizacion(codigoCotizacion);
            cotizacionesUi.setIdUsuarioCotizacion(codigoUsuarioCotizacion);
            cotizacionesUi.setEstadoCotizacion(estadoCotizacion);
            cotizacionesUi.setEstadoPropuesta(estadoPropuesta);
            cotizacionesUi.setCotiPendiente(cotiPendiente);
            cotizacionesUi.setCotiFinalizado(cotiFinalizado);
            cotizacionesUi.setCotiAceptado(cotiAceptado);
            cotizacionesUi.setMonto(coti_monto_inicial);
            cotizacionesUi.setNombreEspecialista(nombresUsuCoti);
            cotizacionesUi.setPuntuacion(estrellasUsuario);
            cotizacionesUi.setPaisImagen(imagenPaisUsuCotizado);
            cotizacionesUi.setCotiDescripcion(descripcionPropuesta);
            cotizacionesUi.setFecha(Constantes.f_fecha_letras(cotifecha));


            String tipoLLegadaNotificacion = "notificacionClicked";
            //Intent resultIntent = new Intent(applicationContext, MenuClienteActivity.class);
            Intent resultIntent = new Intent(applicationContext, DatosCotizacionActivity.class);
            resultIntent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
            resultIntent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
            resultIntent.putExtra("notificacion", tipoLLegadaNotificacion);
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
