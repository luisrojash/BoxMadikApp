package com.application.boxmadikv1.notificaciones.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.notificaciones.adapter.holder.NotificacionesHolder;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;
import com.application.boxmadikv1.notificaciones.listener.NotificacionListener;

import java.util.List;

public class NotificacionesAdapter extends RecyclerView.Adapter<NotificacionesHolder> {

    private List<NotificacionesUi> notificacionesUiList;
    private NotificacionListener notificacionListener;

    public NotificacionesAdapter(List<NotificacionesUi> notificacionesUiList, NotificacionListener notificacionListener) {
        this.notificacionesUiList = notificacionesUiList;
        this.notificacionListener = notificacionListener;
    }

    @NonNull
    @Override
    public NotificacionesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificaciones, parent, false);
        return new NotificacionesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificacionesHolder holder, int position) {
        NotificacionesUi notificacionesUi = notificacionesUiList.get(position);
        holder.bind(notificacionesUi, notificacionListener);
    }

    @Override
    public int getItemCount() {
        if (notificacionesUiList == null) return 0;
        return notificacionesUiList.size();
    }

    public void setMostrarLista(List<NotificacionesUi> notificacionesUiList) {
        this.notificacionesUiList.clear();
        this.notificacionesUiList.addAll(notificacionesUiList);
        notifyDataSetChanged();
    }

    public void mostrarListaAdd(List<NotificacionesUi> notificacionesUiList) {
        this.notificacionesUiList.addAll(notificacionesUiList);
        notifyDataSetChanged();
    }

    public void limpiarVista(List<NotificacionesUi> notificacionesUiList) {
        this.notificacionesUiList.clear();
        this.notificacionesUiList.addAll(notificacionesUiList);
        notifyDataSetChanged();
    }

    public void actualizarItem(NotificacionesUi notificacionesUi) {
        int position = this.notificacionesUiList.indexOf(notificacionesUi);
        if (position != -1) {
            this.notificacionesUiList.set(position, notificacionesUi);
            notifyItemChanged(position);
        }
    }
}
