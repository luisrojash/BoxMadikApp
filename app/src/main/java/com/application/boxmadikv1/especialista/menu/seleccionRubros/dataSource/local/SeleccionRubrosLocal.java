package com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource.local;

import com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource.SeleccionRubrosDataSource;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;
import com.application.boxmadikv1.modelo.Rubro;
import com.application.boxmadikv1.modelo.Rubro_Table;
import com.application.boxmadikv1.utils.Constantes;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class SeleccionRubrosLocal implements SeleccionRubrosDataSource {

    @Override
    public void onMostrarListaSeleccionRubros(String codigoPais,CallbackResultado<List<SeleccionRubrosUi>> listCallbackResultado) {
        List<Rubro> rubroList = SQLite.select()
                .from(Rubro.class)
                .where(Rubro_Table.pais_Pais_Codigo.is(codigoPais))
                .and(Rubro_Table.Rub_Estado.is(Constantes.ESTADO_ACTIVO))
                .queryList();

        List<SeleccionRubrosUi> seleccionRubrosUiList = new ArrayList<>();
        for(Rubro rubro : rubroList){
            SeleccionRubrosUi seleccionRubrosUi = new SeleccionRubrosUi();
            seleccionRubrosUi.setIdSeleccionRubroId(rubro.getRub_Codigo()+"");
            seleccionRubrosUi.setDescripcion(rubro.getRub_Desc());
            seleccionRubrosUi.setImageRubro(rubro.getRub_Imagen());
            seleccionRubrosUi.setEstadoRubro(true);
            seleccionRubrosUiList.add(seleccionRubrosUi);
        }
        listCallbackResultado.onCallBackResultado(seleccionRubrosUiList);
    }
}
