package com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.adapter.holder.DescripcionHolder;

import java.util.List;

public class EspecialidadAdapter extends RecyclerView.Adapter<DescripcionHolder> {

    List<EspecialidadesUi> especialidadesUiList;

    public EspecialidadAdapter(List<EspecialidadesUi> especialidadesUiList) {
        this.especialidadesUiList = especialidadesUiList;
    }

    @NonNull
    @Override
    public DescripcionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_especialistas_descripcion, parent, false);
        return new DescripcionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescripcionHolder holder, int position) {
        EspecialidadesUi especialidadesUi = especialidadesUiList.get(position);
        holder.bind(especialidadesUi);
    }

    @Override
    public int getItemCount() {
        if (especialidadesUiList == null) return 0;
        return especialidadesUiList.size();
    }
}
