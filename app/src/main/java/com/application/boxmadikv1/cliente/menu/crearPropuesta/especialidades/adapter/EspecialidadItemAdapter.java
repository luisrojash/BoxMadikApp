package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.adapter.holder.EspecialidadItemHolder;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.listener.EspecialidadListener;

import java.util.List;

public class EspecialidadItemAdapter extends RecyclerView.Adapter<EspecialidadItemHolder> {

    private List<EspecialidadUi> especialidadUiList;
    private EspecialidadListener listener;

    public EspecialidadItemAdapter(List<EspecialidadUi> especialidadUiList, EspecialidadListener listener) {
        this.especialidadUiList = especialidadUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EspecialidadItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_cliente_especialidad, parent, false);
        return new EspecialidadItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecialidadItemHolder holder, int position) {
        EspecialidadUi especialidadUi = especialidadUiList.get(position);
        holder.bind(especialidadUi,listener);
    }

    @Override
    public int getItemCount() {
        if (especialidadUiList == null) return 0;
        return especialidadUiList.size();
    }


    public void eliminarItem(EspecialidadUi especialidadUi) {
        int position = this.especialidadUiList.indexOf(especialidadUi);
        if (position != -1) {
            this.especialidadUiList.remove(especialidadUi);
            notifyDataSetChanged();
        }
    }

    public void agregarItem(EspecialidadUi especialidadUi) {
        this.especialidadUiList.add(especialidadUi);
        notifyItemInserted(getItemCount() - 1);
    }
}
