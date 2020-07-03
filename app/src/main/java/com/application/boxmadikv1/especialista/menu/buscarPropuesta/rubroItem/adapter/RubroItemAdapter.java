package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.adapter.holder.RubroItemHolder;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.listener.RubroItemListener;

import java.util.List;

public class RubroItemAdapter extends RecyclerView.Adapter<RubroItemHolder> {

    private List<RubroItemUi> rubroItemList;
    private RubroItemListener rubroItemListener;

    public RubroItemAdapter(List<RubroItemUi> rubroItemList, RubroItemListener rubroItemListener) {
        this.rubroItemList = rubroItemList;
        this.rubroItemListener = rubroItemListener;
    }

    @NonNull
    @Override
    public RubroItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_especilista_rubro_item, parent, false);
        return new RubroItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RubroItemHolder holder, int position) {
        RubroItemUi rubroItem = rubroItemList.get(position);
        holder.bind(rubroItem,rubroItemListener);
    }

    @Override
    public int getItemCount() {
        if (rubroItemList == null) return 0;
        return rubroItemList.size();
    }

    public void mostrarListaRubroAdapter(List<RubroItemUi> rubroItemList) {
        this.rubroItemList = rubroItemList;
        notifyDataSetChanged();
    }
}
