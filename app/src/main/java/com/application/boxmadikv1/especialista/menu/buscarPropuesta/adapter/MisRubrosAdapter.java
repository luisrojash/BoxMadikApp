package com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter.holder.MisRubrosHolder;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.listener.MisRubrosListener;

import java.util.List;

public class MisRubrosAdapter extends RecyclerView.Adapter<MisRubrosHolder> {

    private List<MisRubrosUi> misRubrosUiList;
    private MisRubrosListener misRubrosListener;

    public MisRubrosAdapter(List<MisRubrosUi> misRubrosUiList, MisRubrosListener misRubrosListener) {
        this.misRubrosUiList = misRubrosUiList;
        this.misRubrosListener = misRubrosListener;
    }

    @NonNull
    @Override
    public MisRubrosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_especialista_mis_rubros, parent, false);
        return new MisRubrosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MisRubrosHolder holder, int position) {
        MisRubrosUi misRubrosUi = misRubrosUiList.get(position);
        holder.bind(misRubrosUi, misRubrosListener);
    }

    @Override
    public int getItemCount() {
        if (misRubrosUiList == null) return 0;
        return misRubrosUiList.size();
    }

    public void mostrarListaMisRubros(List<MisRubrosUi> misRubrosUiList) {
        this.misRubrosUiList.clear();
        this.misRubrosUiList.addAll(misRubrosUiList);
        notifyDataSetChanged();
    }

    public void actualizarAdapter() {
        notifyDataSetChanged();
    }
}
