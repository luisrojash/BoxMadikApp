package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesDataSource;
import com.application.boxmadikv1.dao.departamento.DepartamentoDao;
import com.application.boxmadikv1.dao.distrito.DistritoDao;
import com.application.boxmadikv1.dao.provincia.ProvinciaDao;
import com.application.boxmadikv1.modelo.Departamento;
import com.application.boxmadikv1.modelo.Departamento_Table;
import com.application.boxmadikv1.modelo.Distrito;
import com.application.boxmadikv1.modelo.Distrito_Table;
import com.application.boxmadikv1.modelo.Especialidades;
import com.application.boxmadikv1.modelo.Especialidades_Table;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.Oficio_Table;
import com.application.boxmadikv1.modelo.Provincia;
import com.application.boxmadikv1.modelo.Provincia_Table;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.modelo.Rubro_Table;
import com.application.boxmadikv1.utils.Constantes;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesLocal implements DetallesDataSource {

    public static final String TAG = DetallesLocal.class.getSimpleName();
    private DepartamentoDao departamentoDao;
    private ProvinciaDao provinciaDao;
    private DistritoDao distritoDao;

    public DetallesLocal(DepartamentoDao departamentoDao, ProvinciaDao provinciaDao, DistritoDao distritoDao) {
        this.departamentoDao = departamentoDao;
        this.provinciaDao = provinciaDao;
        this.distritoDao = distritoDao;
    }

    @Override
    public void onRegistrarPropuesta(RequestBody bodyTitulo, RequestBody bodyDetalles, RequestBody bodyPaisCodigo, RequestBody bodyRangPrecioId, RequestBody bodyRangDiasId, RequestBody bodyRangTurnoId, RequestBody bodyRubroId, RequestBody bodyOficioId, ArrayList<String> listaIdEspecialistas, RequestBody requestFile1, RequestBody requestFile2, RequestBody requesKeyUser, RequestBody bodyCodigoDepartamento, RequestBody bodyCodigoProvincia, RequestBody bodyCodigoDistrito, CallbackResultado<DefaultResponseRegistro> defaultResponseCallbackResultado) {

    }

    @Override
    public void onRegistrarEspecialidad(String userLastId, int rubroId, int oficioId, String codigoPais, ArrayList<String> listaIdEspecialistas, final CallbackResultado<DefaultResponseRegistro> defaultResponseCallbackResultado) {
        if (listaIdEspecialistas == null || listaIdEspecialistas.isEmpty()) {
            Log.d(TAG, "listaIdEspecialistas == null");
            defaultResponseCallbackResultado.onCallbackResultado(null);
        } else {
            Log.d(TAG, "else");
            for (String itemId : listaIdEspecialistas) {


                Especialidades especialidades = SQLite.select()
                        .from(Especialidades.class)
                        .innerJoin(Oficio.class)
                        .on(Especialidades_Table.oficio_Ofi_codigo.withTable().eq(Oficio_Table.Ofi_codigo.withTable()))
                        .innerJoin(Rubro.class)
                        .on(Especialidades_Table.oficio_rubro_Rub_Codigo.withTable().eq(Rubro_Table.Rub_Codigo.withTable()))
                        .where(Especialidades_Table.oficio_rubro_Rub_Codigo.withTable().is(Integer.valueOf(rubroId)))
                        .and(Especialidades_Table.oficio_Ofi_codigo.withTable().is(Integer.valueOf(oficioId)))
                        .and(Especialidades_Table.Espe_codigo.withTable().is(Integer.valueOf(itemId)))
                        .querySingle();


                Log.d(TAG, "especialidades : " + especialidades.getEspe_desc() +
                        "/itemId  : " + itemId +
                        "/propuestaId  : " + userLastId +
                        "/rubroId  : " + rubroId +
                        "/oficioId  : " + oficioId);
                String baseUrl = "http://192.168.1.14/Archivosphp/api/";
                String baseRemote = "http://diazosorio.com/BoxMyApi/api/";
                String rubCodigo = String.valueOf(rubroId);
                String oficioCodigo = String.valueOf(oficioId);
                Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
                Call<DefaultResponseRegistro> call = loginService.crearPropuestaEspecialidad(especialidades.getEspe_desc(),
                        itemId,
                        userLastId,
                        rubCodigo,
                        oficioCodigo,
                        codigoPais);
                call.enqueue(new Callback<DefaultResponseRegistro>() {
                    @Override
                    public void onResponse(Call<DefaultResponseRegistro> call, Response<DefaultResponseRegistro> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                DefaultResponseRegistro message = response.body();
                                Log.d(TAG, "onRegistrarPropuesta : " + message.getMessage());
                                defaultResponseCallbackResultado.onCallbackResultado(message);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponseRegistro> call, Throwable t) {
                        Log.d(TAG, "onFailure : " + t.getMessage().toString());
                    }
                });

            }
        }
    }

    @Override
    public void onObtenerDireccion(String codigoPais, String codigoDepartamento, String codigoProvincia, String codigoDistrito, CallbackResultadoDireccion callbackResultadoDireccion) {
        Departamento departamento = departamentoDao.obtenerMiIdQuerySimple(Integer.valueOf(codigoDepartamento), Integer.parseInt(codigoPais));

        Provincia provincia = provinciaDao.obtenerMiIdQuerySimple(Integer.valueOf(codigoProvincia), Integer.valueOf(codigoPais), departamento.getDepa_Cod());

        Distrito distrito = distritoDao.obtenerMiIdQuerySimple(Integer.valueOf(codigoDistrito),
                provincia.getProv_cod(),
                departamento.getDepa_Cod(),
                Integer.valueOf(codigoPais));

        String codeDepart = String.valueOf(departamento.getDepa_Cod());
        String codeProv = String.valueOf(provincia.getProv_cod());
        String codeDist = String.valueOf(distrito.getDist_cod());

        callbackResultadoDireccion.onCallbackDireccion(codeDepart, codeProv, codeDist,
                departamento.getDepa_Desc(),
                provincia.getProv_Desc(),
                distrito.getDist_Desc());



    }

}
