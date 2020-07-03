package com.application.boxmadikv1.dao.tipoCambio;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.TipoCambio;

public interface TipoCambioDao extends BaseDao<TipoCambio> {

    TipoCambio obtenerTipoCambioPorPais(String paisCodigo);
}
