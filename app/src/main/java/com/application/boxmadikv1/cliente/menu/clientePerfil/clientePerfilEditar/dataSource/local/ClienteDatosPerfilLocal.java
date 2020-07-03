package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.local;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;
import com.application.boxmadikv1.dao.pais.PaisDao;
import com.application.boxmadikv1.dao.tipoDocumento.TipoDocumentoDao;
import com.application.boxmadikv1.modelo.Pais;
import com.application.boxmadikv1.modelo.TipoDocumento;

import okhttp3.RequestBody;

public class ClienteDatosPerfilLocal implements DatosPerfilDataSource {

    private TipoDocumentoDao tipoDocumentoDao;
    private PaisDao paisDao;

    public ClienteDatosPerfilLocal(TipoDocumentoDao tipoDocumentoDao, PaisDao paisDao) {
        this.tipoDocumentoDao = tipoDocumentoDao;
        this.paisDao = paisDao;
    }

    @Override
    public void onObtenerNombrePais(String codigoPais, CallBackResultado<String> stringDatosPerfilDataSource) {
        Pais pais = paisDao.obtenerPais(Integer.parseInt(codigoPais));
        if (pais == null) return;
        stringDatosPerfilDataSource.onResultado(pais.getPais_Nombre());
    }

    @Override
    public void onObtenerNombreTipoDoc(String codigotipoDoc, CallBackResultado<String> stringDatosPerfilDataSource) {
        TipoDocumento tipoDocumento = tipoDocumentoDao.obtenerTipoDocumento(Integer.parseInt(codigotipoDoc));
        if (tipoDocumento == null) return;
        stringDatosPerfilDataSource.onResultado(tipoDocumento.getTDoc_Desc_corta());
    }

}
