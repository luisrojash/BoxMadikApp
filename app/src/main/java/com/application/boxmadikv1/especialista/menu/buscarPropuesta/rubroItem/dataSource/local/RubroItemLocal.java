package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource.local;

import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource.RubroItemDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;
import com.application.boxmadikv1.modelo.Rubro;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class RubroItemLocal implements RubroItemDataSource {
    @Override
    public void onMostrarListaRubroItem(CallBackResultado<List<RubroItemUi>> listCallBackResultado) {
        List<Rubro> rubroList = SQLite.select()
                .from(Rubro.class)
                .queryList();

        List<RubroItemUi> seleccionRubrosUiList = new ArrayList<>();
        for(Rubro rubro : rubroList){
            RubroItemUi seleccionRubrosUi = new RubroItemUi();
            seleccionRubrosUi.setRubroItemId(rubro.getRub_Codigo()+"");
            seleccionRubrosUi.setDescripcion(rubro.getRub_Desc());
            seleccionRubrosUi.setImagenRubro(rubro.getRub_Imagen());
            seleccionRubrosUi.setEstadoRubro(true);
            seleccionRubrosUiList.add(seleccionRubrosUi);
        }
        listCallBackResultado.onCallBackResultado(seleccionRubrosUiList);
    }
}
