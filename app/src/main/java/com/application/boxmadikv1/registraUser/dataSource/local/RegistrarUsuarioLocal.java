package com.application.boxmadikv1.registraUser.dataSource.local;

import android.util.Log;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.dao.pais.PaisDao;
import com.application.boxmadikv1.dao.tipoDocumento.TipoDocumentoDao;
import com.application.boxmadikv1.modelo.Pais;
import com.application.boxmadikv1.modelo.TipoDocumento;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioDataSource;
import com.application.boxmadikv1.registraUser.entidad.TipoDocumentoUi;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;
import com.application.boxmadikv1.utils.Constantes;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

public class RegistrarUsuarioLocal implements RegistrarUsuarioDataSource {
    public static final String TAG = RegistrarUsuarioLocal.class.getSimpleName();

    private TipoDocumentoDao tipoDocumentoDao;
    private PaisDao paisDao;

    public RegistrarUsuarioLocal(TipoDocumentoDao tipoDocumentoDao, PaisDao paisDao) {
        this.tipoDocumentoDao = tipoDocumentoDao;
        this.paisDao = paisDao;
    }

    @Override
    public void onRegistrarUsuario(RequestBody requestFile, RequestBody requestBodyTipoDoc, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyEmail, RequestBody requestBodyClave, RequestBody requestBodyCelular, RequestBody requestBodyTipoDocumentoId, RequestBody requestBodyTipoPaisId, RequestBody requestBodyDateTimeCumple,RequestBody requestBodyRazonSocial, CallbackResultado<DefaultResponse> defaultResponseCallbackResultado) {

    }


    @Override
    public void onListartTipoDocumento(String tipoPaisId, CallbackResultado<List<TipoDocumentoUi>> listCallbackResultado) {
      //  List<TipoDocumento> tipoDocumentoList = tipoDocumentoDao.getAll();
        List<TipoDocumento> tipoDocumentoList = tipoDocumentoDao.obtenerListaTipoDocumentoPorPais(tipoPaisId,Constantes.ESTADO_ACTIVO);
        if (tipoDocumentoList == null) return;
        List<TipoDocumentoUi> tipoDocumentoUiList = new ArrayList<>();
        TipoDocumentoUi tipoDocumentoUiVacio = new TipoDocumentoUi();
        tipoDocumentoUiVacio.setIdTipoDcumento("30");
        tipoDocumentoUiVacio.setDescripcion("Seleccione Tipo Documento");
        tipoDocumentoUiList.add(tipoDocumentoUiVacio);
        for (TipoDocumento tipoDocumento : tipoDocumentoList) {
            TipoDocumentoUi tipoDocumentoUi = new TipoDocumentoUi();
            tipoDocumentoUi.setIdTipoDcumento(tipoDocumento.getTDoc_Codigo() + "");
            tipoDocumentoUi.setDescripcion(tipoDocumento.getTDoc_Desc_corta());
            tipoDocumentoUiList.add(tipoDocumentoUi);
        }
        listCallbackResultado.onCallBackResultado(tipoDocumentoUiList);
    }

    @Override
    public void onListartTipoPais(CallbackResultado<List<TipoPaisUi>> listCallbackResultado) {
        // List<Pais> tipoPaisList = paisDao.getAll();
        List<Pais> tipoPaisList = paisDao.obtenerListaEstado(Constantes.ESTADO_ACTIVO);
        Log.d(TAG, "onListartTipoPais : " + tipoPaisList.size());
        if (tipoPaisList == null) return;
        List<TipoPaisUi> tipoPaisUiList = new ArrayList<>();
        TipoPaisUi tipoDocumentoUiVacio = new TipoPaisUi();
        tipoDocumentoUiVacio.setIdTipoPais("0");
        tipoDocumentoUiVacio.setDescripcion("Seleccione Pais");
        tipoPaisUiList.add(tipoDocumentoUiVacio);
        for (Pais pais : tipoPaisList) {
            TipoPaisUi tipoPaisUi = new TipoPaisUi();
            tipoPaisUi.setIdTipoPais(pais.getPais_Codigo() + "");
            tipoPaisUi.setDescripcion(pais.getPais_Nombre());
            tipoPaisUiList.add(tipoPaisUi);
        }
        listCallbackResultado.onCallBackResultado(tipoPaisUiList);
    }
}
