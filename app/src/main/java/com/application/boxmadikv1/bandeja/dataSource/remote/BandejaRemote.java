package com.application.boxmadikv1.bandeja.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.ListaChatGrupoResponse;
import com.application.boxmadikv1.bandeja.dataSource.BandejaDataSource;
import com.application.boxmadikv1.bandeja.entidad.BandejaUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BandejaRemote implements BandejaDataSource {

    public static final String TAG = BandejaRemote.class.getSimpleName();

    @Override

    public void onMostrarListaGrupoChat(String usuarioCodigo, String paisCodigo, final String tipoUsuario, final CallBackResultado<List<BandejaUi>> listCallBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaChatGrupoResponse> call = apiService.obtenerListaGrupoChat(usuarioCodigo,
                paisCodigo,
                tipoUsuario);
        call.enqueue(new Callback<ListaChatGrupoResponse>() {
            @Override
            public void onResponse(Call<ListaChatGrupoResponse> call, Response<ListaChatGrupoResponse> response) {
                ListaChatGrupoResponse cambioResponse = response.body();
                if (cambioResponse != null) {
                    if (cambioResponse.getError()) {
                        Log.d(TAG, "cambioResponse.getError() : " + cambioResponse.getMessage());
                        listCallBackResultado.onCallBackResultado(null);
                    } else {

                        List<BandejaUi> bandejaUiList = new ArrayList<>();

                        for (ListaChatGrupoResponse.ListaChatGrupoResp listaChatGrupoResp
                                : cambioResponse.getListaChatGrupoResps()) {
                            BandejaUi bandejaUi = new BandejaUi();
                            bandejaUi.setIdGrupoChat(listaChatGrupoResp.getCodigoGrupoChat());
                            bandejaUi.setIdUsuarioPropuesta(listaChatGrupoResp.getCodigoUsuarioPropuesta());
                            bandejaUi.setIdUsuarioCotiza(listaChatGrupoResp.getCodigoUsuCotiza());
                            bandejaUi.setTipoUsuario(tipoUsuario);
                            bandejaUi.setImagenRubro(listaChatGrupoResp.getImagenRubro());
                            bandejaUi.setNombrePropuesta(listaChatGrupoResp.getTituloPropuesta());
                            bandejaUi.setMensaje(listaChatGrupoResp.getMensajeChat());
                            //bandejaUi.setNombreReceptor(listaChatGrupoResp.getNombreUsuario() + " " + listaChatGrupoResp.getApellidoUsuario());
                            bandejaUi.setNombreReceptor(validarNombreNull(listaChatGrupoResp));
                            bandejaUi.setFotoReceptor(listaChatGrupoResp.getUsuFoto());
                            bandejaUi.setEstadoGrupoLeido(listaChatGrupoResp.getEstadoChatGrupo());//----
                            bandejaUi.setEstadoPropuesta(listaChatGrupoResp.getPropuestaEstado());
                            if (listaChatGrupoResp.getCotiEstado() != null) {
                                bandejaUi.setCotiEstado(listaChatGrupoResp.getCotiEstado());
                            }
                            if (listaChatGrupoResp.getMensajesNoLeidos() == null) {
                                bandejaUi.setConteoMensaje(0);//--
                            } else {
                                bandejaUi.setConteoMensaje(Integer.parseInt(listaChatGrupoResp.getMensajesNoLeidos()));//--
                            }

                            bandejaUiList.add(bandejaUi);
                        }
                        listCallBackResultado.onCallBackResultado(bandejaUiList);
                    }

                }
            }

            @Override
            public void onFailure(Call<ListaChatGrupoResponse> call, Throwable t) {
                listCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });

    }

    private String validarNombreNull(ListaChatGrupoResponse.ListaChatGrupoResp listaChatGrupoResp) {
        String nombreUser = "";
        if (listaChatGrupoResp.getNombreUsuario() == null) {
            nombreUser = listaChatGrupoResp.getUsuNombreRazonSocial();
            Log.d(TAG, "validarNombreNull if : " + nombreUser);
        } else {
            Log.d(TAG, "validarNombreNull else: " + nombreUser);
            nombreUser = listaChatGrupoResp.getNombreUsuario() + " " + listaChatGrupoResp.getApellidoUsuario();
        }
        return nombreUser;
    }
}
