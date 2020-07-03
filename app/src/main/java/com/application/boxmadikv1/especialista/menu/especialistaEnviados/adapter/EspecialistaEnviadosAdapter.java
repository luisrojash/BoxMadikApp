package com.application.boxmadikv1.especialista.menu.especialistaEnviados.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.adapter.holder.EspecialistaEnviadosHolder;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.listener.EspecialistaEnviadoListener;

import java.util.List;

public class EspecialistaEnviadosAdapter extends RecyclerView.Adapter<EspecialistaEnviadosHolder> {

    private List<EspecialistaEnviadosUi> especialistaEnviadosUiList;
    private EspecialistaEnviadoListener especialistaEnviadoListener;

    public EspecialistaEnviadosAdapter(List<EspecialistaEnviadosUi> especialistaEnviadosUiList, EspecialistaEnviadoListener especialistaEnviadoListener) {
        this.especialistaEnviadosUiList = especialistaEnviadosUiList;
        this.especialistaEnviadoListener = especialistaEnviadoListener;
    }

    @NonNull
    @Override
    public EspecialistaEnviadosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enviados_especialista, parent, false);
        return new EspecialistaEnviadosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecialistaEnviadosHolder holder, int position) {
        EspecialistaEnviadosUi especialistaEnviadosUi = especialistaEnviadosUiList.get(position);
        holder.bind(especialistaEnviadosUi,especialistaEnviadoListener);
    }

    @Override
    public int getItemCount() {
        if (especialistaEnviadosUiList == null) return 0;
        return especialistaEnviadosUiList.size();
    }

    public void mostrarLista(List<EspecialistaEnviadosUi> especialistaEnviadosUiList) {
        this.especialistaEnviadosUiList = especialistaEnviadosUiList;
        notifyDataSetChanged();
    }

    public void eliminarItem(EspecialistaEnviadosUi especialistaEnviadosUiList) {
        int position = this.especialistaEnviadosUiList.indexOf(especialistaEnviadosUiList);
        if (position != -1) {
            this.especialistaEnviadosUiList.remove(especialistaEnviadosUiList);
            notifyItemRemoved(position);
        }
    }
}
