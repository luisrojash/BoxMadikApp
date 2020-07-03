package com.application.boxmadikv1.especialista.menu.abstracto.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.abstracto.adapter.holder.EspecAbstractHolder;
import com.application.boxmadikv1.especialista.menu.abstracto.listener.EspecAbstractListener;

import java.util.List;

public class EspecAbstractAdapter extends RecyclerView.Adapter<EspecAbstractHolder> {

    private List<EspecialistaEstadosUi> especialistaEstadosUiList;
    private EspecAbstractListener especAbstractListener;

    public EspecAbstractAdapter(List<EspecialistaEstadosUi> especialistaEstadosUiList, EspecAbstractListener especAbstractListener) {
        this.especialistaEstadosUiList = especialistaEstadosUiList;
        this.especAbstractListener = especAbstractListener;
    }

    @NonNull
    @Override
    public EspecAbstractHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_abstract_especialista, parent, false);
        return new EspecAbstractHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecAbstractHolder holder, int position) {
        EspecialistaEstadosUi especialistaEstadosUi = especialistaEstadosUiList.get(position);
        holder.bind(especialistaEstadosUi,especAbstractListener);
    }

    @Override
    public int getItemCount() {
        if (especialistaEstadosUiList == null) return 0;
        return especialistaEstadosUiList.size();
    }

    public void mostrarLista(List<EspecialistaEstadosUi> especialistaEstadosUis) {
        //this.especialistaEstadosUiList = especialistaEstadosUis;
        if(especialistaEstadosUis == null)return;
        this.especialistaEstadosUiList.clear();
        this.especialistaEstadosUiList.addAll(especialistaEstadosUis);
        notifyDataSetChanged();
    }

    public void eliminarItem(EspecialistaEstadosUi especialistaEstadosUis) {
        int position = this.especialistaEstadosUiList.indexOf(especialistaEstadosUis);
        if (position != -1) {
            this.especialistaEstadosUiList.remove(especialistaEstadosUis);
            notifyItemRemoved(position);
        }
    }
}
