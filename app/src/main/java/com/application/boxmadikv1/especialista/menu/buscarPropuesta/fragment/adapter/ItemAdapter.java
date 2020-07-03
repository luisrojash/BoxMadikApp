package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.adapter.holder.ItemHolder;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;


import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    List<PropuestaEspecialidad> propuestaEspecialidads;

    public ItemAdapter(List<PropuestaEspecialidad> propuestaEspecialidads) {
        this.propuestaEspecialidads = propuestaEspecialidads;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item_especialista, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        PropuestaEspecialidad propuestaInicial = propuestaEspecialidads.get(position);
        holder.bind(propuestaInicial);

    }

    @Override
    public int getItemCount() {
        if (propuestaEspecialidads == null) return 0;
        return propuestaEspecialidads.size();
    }

    public void mostarLista(List<PropuestaEspecialidad> propuestaEspecialidads) {
        this.propuestaEspecialidads.clear();
        this.propuestaEspecialidads.addAll(propuestaEspecialidads);
        notifyDataSetChanged();
    }
}
