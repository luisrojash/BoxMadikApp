package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.adapter.holder.EspecHolder;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.entidad.EspecialidadUi;

import java.util.List;

public class EspecAdapter extends RecyclerView.Adapter<EspecHolder> {
    private List<EspecialidadUi> especialidadUis;

    public EspecAdapter(List<EspecialidadUi> especialidadUis) {
        this.especialidadUis = especialidadUis;
    }

    @NonNull
    @Override
    public EspecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item_especialista, parent, false);
        return new EspecHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecHolder holder, int position) {
        EspecialidadUi especialidadUi = especialidadUis.get(position);
        holder.bind(especialidadUi);
    }

    @Override
    public int getItemCount() {
        if (especialidadUis == null) return 0;
        return especialidadUis.size();
    }
    public void mostrarLista(List<EspecialidadUi> propuestaEspecialidads) {
        this.especialidadUis.clear();
        this.especialidadUis.addAll(propuestaEspecialidads);
        notifyDataSetChanged();
    }
}
