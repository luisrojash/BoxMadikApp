package com.application.boxmadikv1.dao.banco;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Banco;

import java.util.List;

public interface BancoDao extends BaseDao<Banco> {
    List<Banco> obtenerBancoListaActivo(String codigoPais, String tipoEstado);
}
