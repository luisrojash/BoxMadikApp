package com.application.boxmadikv1.dao.tipoDocumento;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.TipoDocumento;

import java.util.List;

public interface TipoDocumentoDao extends BaseDao<TipoDocumento> {

    TipoDocumento obtenerTipoDocumento(int codigoTipoDocumento);

    /*Obtenemos Filtro de Tipo Documento por Pais y Estado Activo = 1*/
    List<TipoDocumento> obtenerListaTipoDocumentoPorPais(String paisCodigo, String documentoEstado);
}
