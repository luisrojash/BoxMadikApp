package com.application.boxmadikv1.especialista.menu.especialistaPerfil.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.adapter.holder.TrabajosRealizadosHolder;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.entidad.TrabajosRealizadosUi;

import java.util.List;

public class TrabajosRealizadosAdapter extends RecyclerView.Adapter<TrabajosRealizadosHolder> {
    private List<TrabajosRealizadosUi> trabajosRealizadosUis;

    public TrabajosRealizadosAdapter(List<TrabajosRealizadosUi> trabajosRealizadosUis) {
        this.trabajosRealizadosUis = trabajosRealizadosUis;
    }

    @NonNull
    @Override
    public TrabajosRealizadosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trabajos_espec, parent, false);
        return new TrabajosRealizadosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrabajosRealizadosHolder holder, int position) {
        TrabajosRealizadosUi trabajosRealizadosUi = trabajosRealizadosUis.get(position);
        holder.bind(trabajosRealizadosUi);
    }

    @Override
    public int getItemCount() {
        if (trabajosRealizadosUis == null) return 0;
        return trabajosRealizadosUis.size();
    }

    public void mostrarLista(List<TrabajosRealizadosUi> trabajosRealizadosUis) {
        this.trabajosRealizadosUis.clear();
        this.trabajosRealizadosUis.addAll(trabajosRealizadosUis);
        notifyDataSetChanged();
    }
}
