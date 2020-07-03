package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.especialista.ComentarioResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource.DatosPerfilEspecialistaDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosPerfilEspecialistaRemote implements DatosPerfilEspecialistaDataSource {

    public static final String TAG = DatosPerfilEspecialistaRemote.class.getSimpleName();

    @Override
    public void onMostrarListaComentarios(final String keyUser, final String codigoPais, final CallBackResultado<List<DatosPropuestaUi>> listCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ComentarioResponse> call = loginService.obtenerListaComentarioEspecialista(keyUser, codigoPais);
        call.enqueue(new Callback<ComentarioResponse>() {
            @Override
            public void onResponse(Call<ComentarioResponse> call, Response<ComentarioResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ComentarioResponse message = response.body();
                        if (message.getError()) {
                            Log.d(TAG, " if (message.getError() : ");
                            listCallBackResultado.onCallBackResultado(null);
                        } else {
                            List<DatosPropuestaUi> datosPropuestaUis = new ArrayList<>();
                            Log.d(TAG, " elseelseelse : ");
                            for (ComentarioResponse.ComentarioEspecResponse especResponses
                                    : message.getComentarioEspecResponseList()) {
                                DatosPropuestaUi datosPropuestaUi = new DatosPropuestaUi();
                                datosPropuestaUi.setNombrePropuesta(especResponses.getNombrePropuesta());
                                datosPropuestaUi.setFechaPropuesta(Constantes.f_fecha_letras(especResponses.getFechaPropuesta()));
                                datosPropuestaUi.setDetallePropuesta(especResponses.getDetallePropuesta());
                                datosPropuestaUi.setFotoCliente(especResponses.getFotoCliente());
                                //datosPropuestaUi.setNombreCliente(especResponses.getNombreCliente());
                                datosPropuestaUi.setNombreCliente(obtenerNombre(especResponses));
                                datosPropuestaUi.setApellidoCliente(especResponses.getApellidoCliente());
                                datosPropuestaUi.setEstrellasEspec(especResponses.getEstrellasEspec());
                                datosPropuestaUi.setComentarioEspec(especResponses.getComentarioEspec());
                                datosPropuestaUi.setImagenRubro(especResponses.getRubroImagen());
                                datosPropuestaUi.setUsuRazonSocial(especResponses.getUsuRazonSocial());
                                datosPropuestaUi.setKeyUser(keyUser);
                                // obtenerListaCursos(keyUser, datosPropuestaUi);
                                datosPropuestaUis.add(datosPropuestaUi);

                            }
                            listCallBackResultado.onCallBackResultado(datosPropuestaUis);
                            return;

                            //listCallBackResultado.onCallBackResultado(null);
                        }
                    } else {
                        listCallBackResultado.onCallBackResultado(null);
                        Log.d(TAG, " elseelseelse : nullnullnullnull");
                    }
                }
            }

            @Override
            public void onFailure(Call<ComentarioResponse> call, Throwable t) {
                listCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    private String obtenerNombre(ComentarioResponse.ComentarioEspecResponse especResponses) {
        String nombreUsuario;
        if (especResponses.getNombreCliente() == null) {
            nombreUsuario = especResponses.getUsuRazonSocial();
        } else {
            nombreUsuario = especResponses.getNombreCliente();
        }
        return nombreUsuario;

    }

    private void obtenerListaCursos(String keyUser, final DatosPropuestaUi datosPropuestaUi) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        service.obtenerListaCursos(keyUser)
                .enqueue(new Callback<ListaCursosResponse>() {
                    @Override
                    public void onResponse(Call<ListaCursosResponse> call, Response<ListaCursosResponse> response) {
                        ListaCursosResponse body = response.body();
                        //Log.d(TAG, "body : " + body.getMessage());*/
                        List<CursosUi> cursosUiList;
                        if (body.getError()) {
                            Log.d(TAG, "Nulo Cursos");
                        } else {

                            cursosUiList = new ArrayList<>();
                            for (ListaCursosResponse.ListaCurResponse listaCurResponse : body.getListaCurResponses()) {
                                CursosUi cursosUi = new CursosUi();
                                cursosUi.setCodigoEspeEstudios(listaCurResponse.getCodigoEspeEstudios());
                                cursosUi.setEstadoValidacion(listaCurResponse.getEstadoValidacion());
                                cursosUi.setTipoEstudioNombre(listaCurResponse.getTipoEstudioNombre());
                                cursosUi.setNombreEspeEstudios(listaCurResponse.getNombreEspeEstudios());
                                cursosUi.setNombreCentroEstu(listaCurResponse.getNombreCentroEstu());
                                cursosUi.setAnoInicioEspeEstudios(listaCurResponse.getAnoInicioEspeEstudios());
                                cursosUi.setAnoFinEspeEstudios(listaCurResponse.getAnoFinEspeEstudios());
                                cursosUiList.add(cursosUi);
                            }
                            datosPropuestaUi.setCursosUiList(cursosUiList);
                        }
                    }

                    @Override
                    public void onFailure(Call<ListaCursosResponse> call, Throwable t) {
                        //  listaCursosResponseCallBackResultado.onCallBackResultado(null);
                        Log.d(TAG, "onFailure : " + t.getMessage().toString());

                    }
                });
    }
}
