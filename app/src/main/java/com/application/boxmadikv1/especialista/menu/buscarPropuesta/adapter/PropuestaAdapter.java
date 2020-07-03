package com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter.holder.PropuestaHolder;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.listener.PropuestaListener;

import java.util.List;

public class PropuestaAdapter extends RecyclerView.Adapter<PropuestaHolder> {

    private List<ItemUi> itemUiList;
    private PropuestaListener propuestaListener;
    private RecyclerView recyclerView;

    public PropuestaAdapter(List<ItemUi> itemUiList, PropuestaListener propuestaListener) {
        this.itemUiList = itemUiList;
        this.propuestaListener = propuestaListener;
    }

    @NonNull
    @Override
    public PropuestaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_propuesta_especialista, parent, false);
        return new PropuestaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropuestaHolder holder, int position) {
        ItemUi itemUi = itemUiList.get(position);
        holder.bind(itemUi,propuestaListener);
    }

    @Override
    public int getItemCount() {
        if (itemUiList == null) return 0;
        return itemUiList.size();
    }

    public void setMostraPropuestaLista(List<ItemUi> itemUiList) {
        this.itemUiList.clear();
        this.itemUiList.addAll(itemUiList);
        notifyDataSetChanged();
    }

    public void agregarLoadMoreLista(List<ItemUi> itemUiList) {
        this.itemUiList.addAll(itemUiList);
        notifyDataSetChanged();
    }

    private PropuestaHolder getHolder(Object o) {
        PropuestaHolder propuestaHolder = null;
        int posicion = this.itemUiList.indexOf(o);
      //  Log.d(TAG, "RubrosHolder posicion: " + posicion);
        if (posicion != -1) {
            if (itemUiList.get(posicion) instanceof ItemUi) {
                propuestaHolder = (PropuestaHolder) recyclerView.findViewHolderForLayoutPosition(posicion);
            }
        }
        return propuestaHolder;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void actualizarDataEspecialista(ItemUi itemUi) {
        PropuestaHolder propuestaHolder = getHolder(itemUi);
        if (propuestaHolder != null) {
         //   propuestaHolder.actualizarListaEspecialidades(itemUi);
            notifyDataSetChanged();
        }
    }
}
