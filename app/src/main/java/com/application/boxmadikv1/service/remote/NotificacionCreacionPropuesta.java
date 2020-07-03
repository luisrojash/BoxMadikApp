package com.application.boxmadikv1.service.remote;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.PerfilPropuestaActivity;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;
import com.application.boxmadikv1.service.MyFirebaseInstanceIDService;
import com.application.boxmadikv1.utils.Config;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.NotificationUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificacionCreacionPropuesta {
    public static final String TAG = NotificacionCreacionPropuesta.class.getSimpleName();

    public NotificacionCreacionPropuesta() {
    }

    public void llenarDataInitPerfilPropuesta(Context applicationContext, String title, String message,
                                              String timestamp, String imageUrl, JSONObject payload, String tipoNotificacion) {
        Log.d(TAG, "payload :  " + payload.toString());
        try {
            String codigoPropuesta, titulo, imageRubro, fecha, detallePropuesta, codigoRubro,
                    nombreRubro, codigoOficio, nombreOficio, codigoRangoDiasId, nombreRangoDias,
                    codigoRangoTurnoId, nombreRangoTurno, codigoRangoPrecioId, codigoUsuarioPropuesta,
                    tipoEstadoPropuesta, promedioCotizacion, numeroCotizacion, nombreDepartamento, nombreDistrito,
                    paisCodigo,montoOficio;

            codigoPropuesta = payload.getString("Pri_Codigo");
            titulo = payload.getString("Pri_Titulo");
            imageRubro = payload.getString("rubroImagen");
            fecha = payload.getString("Pri_Fecha");
            detallePropuesta = payload.getString("Pri_Detalle");
            codigoRubro = payload.getString("rubro_Rub_Codigo");
            nombreRubro = payload.getString("nombreRubro");
            codigoOficio = payload.getString("oficio_Ofi_codigo");
            nombreOficio = payload.getString("nombreOficio");
            codigoRangoDiasId = payload.getString("rango_dias_Rad_Item");
            nombreRangoDias = payload.getString("nombreRangoDias");
            codigoRangoTurnoId = payload.getString("rango_turno_Rat_Item");
            nombreRangoTurno = payload.getString("nombreRangoTurno");
            codigoRangoPrecioId = payload.getString("rango_precio_Ran_Item");
            codigoUsuarioPropuesta = payload.getString("usuario_Usu_Codigo");
            tipoEstadoPropuesta = payload.getString("Pri_Estado");
            promedioCotizacion = payload.getString("Promedio_coti");
            numeroCotizacion = payload.getString("Num_cotizacion");
            nombreDepartamento = payload.getString("DepaDesc");
            nombreDistrito = payload.getString("distritoDescripcion");
            paisCodigo = payload.getString("pais_Pais_Codigo");
            montoOficio = payload.getString("montoOficio");
            Log.d(TAG, "nombreDepartamento ");

            ItemUi itemUi = new ItemUi();
            itemUi.setCodigoPropuesta(codigoPropuesta);
            itemUi.setNombrePropuesta(titulo);
            itemUi.setImagePropuesta(imageRubro);
            itemUi.setFechaPropuesta(fecha);
            itemUi.setDetallesPropuesta(detallePropuesta);
            itemUi.setCodigoRubro(codigoRubro);
            itemUi.setDescripcionRubro(nombreRubro);
            itemUi.setCodigoOficio(codigoOficio);
            itemUi.setDescripcionOficio(nombreOficio);
            itemUi.setCodigoRangoDias(codigoRangoDiasId);
            itemUi.setDescripcionRangoDias(nombreRangoDias);
            itemUi.setCodigoRangoTurno(codigoRangoTurnoId);
            itemUi.setDescripcionRangoTurno(nombreRangoTurno);
            itemUi.setCodigoRangoPrecio(codigoRangoPrecioId);
            itemUi.setCodigoUsuarioPropuesta(codigoUsuarioPropuesta);
            itemUi.setPropuestaEstado(tipoEstadoPropuesta);
            //itemUi.setCotiEstado(tipoEstadoPropuesta);
            //itemUi.setDescripcionRangoPrecio("0");
            itemUi.setPromedioCotizacion(promedioCotizacion);
            itemUi.setNumeroCotizacion(numeroCotizacion);
            itemUi.setNombreDepartamento(nombreDepartamento);
            itemUi.setNombreDistrito(nombreDistrito);
            itemUi.setPaisCodigo(paisCodigo);
            itemUi.setMontoOfico(montoOficio);


            String tipoLLegadaNotificacion = "notificacionClicked";
            Intent resultIntent = new Intent(applicationContext, PerfilPropuestaActivity.class);
            resultIntent.putExtra("itemUi", itemUi);
            resultIntent.putExtra("notificacion", tipoLLegadaNotificacion);

            //itemUi.setEspecialidadesUiList(llenarEspecialidad(codigoPropuesta, codigoRubro, codigoOficio,
             //       applicationContext, title, message, timestamp, resultIntent, imageUrl, tipoNotificacion,paisCodigo));
            //showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
            mostrarNotificacion(applicationContext, title, message, timestamp, resultIntent, imageUrl, tipoNotificacion);

        } catch (Throwable tx) {
            Log.e(TAG, "Could not parse malformed JSON: /" + payload + "/");
        }
    }

    private List<EspecialidadesUi> llenarEspecialidad(String codigoPropuesta, String codigoRubro, String codigoOficio, final Context applicationContext,
                                                      final String title, final String message, final String timestamp, final Intent resultIntent, final String imageUrl,
                                                      final String tipoNotificacion, String paisCodigo) {
        final List<EspecialidadesUi> especialidadesUiList = new ArrayList<>();

        int propuestaCodigo = Integer.parseInt(codigoPropuesta);
        int rubroCodigo = Integer.parseInt(codigoRubro);
        int oficioCodigo = Integer.parseInt(codigoOficio);

        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaPropuestaEspecialidadResponse> call = loginService.obtenerListaPropuestaEspecialidad(propuestaCodigo,
                rubroCodigo,
                oficioCodigo,
                Integer.parseInt(paisCodigo));
        call.enqueue(new Callback<ListaPropuestaEspecialidadResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaEspecialidadResponse> call, Response<ListaPropuestaEspecialidadResponse> response) {
                ListaPropuestaEspecialidadResponse body = response.body();

                if (body == null) return;
                Log.d(TAG, "resultado == NULL");
                List<PropuestaEspecialidad> especialidadList = body.getPropuestaEspecialidad();
                if (especialidadList == null) {
                    mostrarNotificacion(applicationContext, title, message, timestamp, resultIntent, imageUrl, tipoNotificacion);
                    Log.d(TAG, "WSP if No tiene Especialidades");
                    // if (view != null) view.mostrarTextoVacio("No tiene Especialidades");
                } else {
                    Log.d(TAG, "WSP ELSE");

                    for (PropuestaEspecialidad propuestaEspecialidad
                            : especialidadList) {
                        EspecialidadesUi especialidadesUi = new EspecialidadesUi();
                        especialidadesUi.setCodigoEspecialidad(String.valueOf(propuestaEspecialidad.getPE_Codigo()));
                        especialidadesUi.setDescripcionEspecialidad(propuestaEspecialidad.getPE_descripcion());
                        especialidadesUiList.add(especialidadesUi);
                    }

                    mostrarNotificacion(applicationContext, title, message, timestamp, resultIntent, imageUrl, tipoNotificacion);

                }
            }

            @Override
            public void onFailure(Call<ListaPropuestaEspecialidadResponse> call, Throwable t) {
                Log.d(TAG, "WSP if No tiene Especialidades");
            }
        });

        return especialidadesUiList;
    }

    private void mostrarNotificacion(Context applicationContext, String title, String message, String timestamp, Intent resultIntent, String imageUrl, String tipoNotificacion) {
        NotificationUtils notificationUtils = new NotificationUtils(applicationContext);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.validarIconoNotificacion(title, message, timestamp, resultIntent, imageUrl, tipoNotificacion);
        // notificationUtils.showNotificationMessage(title, message, timestamp, resultIntent, imageUrl);
    }


    public void sincroData(JSONObject payload, String tipoNotificacion, String title, String message, MyFirebaseInstanceIDService myFirebaseInstanceIDService) {
        Log.d(TAG, "payload :  " + payload.toString());
        try {
           /* String codigoUsuarioCotizado = payload.getString("codigoUsuarioCotizado");
            String codigoPais = payload.getString("codigoPais");
            // String tipoNotiCodigo = payload.getString("tipoNotiCodigo");tipoNotificacion
            String tipoNotiCodigo = payload.getString("tipoNotiCodigo");*/


            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("tipoNotificacion", tipoNotificacion);
            /*pushNotification.putExtra("codigoPais", codigoPais);
            pushNotification.putExtra("tipoNotiCodigo", tipoNotiCodigo);
            pushNotification.putExtra("tipoNotificacion", tipoNotificacion);
            pushNotification.putExtra("titulo", title);
            pushNotification.putExtra("mensaje", message);*/
            LocalBroadcastManager.getInstance(myFirebaseInstanceIDService).sendBroadcast(pushNotification);
        } catch (Throwable tx) {
            Log.e(TAG, "Could not parse malformed JSON: /" + payload + "/");
        }


    }
}
