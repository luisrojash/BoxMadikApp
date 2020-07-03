package com.application.boxmadikv1.cliente.menu.clientePerfil.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.clientePerfil.adapter.holder.ComentarioHolder;
import com.application.boxmadikv1.cliente.menu.clientePerfil.entidad.ComentariosUi;

import java.util.List;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioHolder> {
    public static final String TAG = ComentarioAdapter.class.getSimpleName();

    private List<ComentariosUi> comentariosUiList;

    public ComentarioAdapter(List<ComentariosUi> comentariosUiList) {
        this.comentariosUiList = comentariosUiList;
    }

    @NonNull
    @Override
    public ComentarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentarios_cliente, parent, false);
        return new ComentarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioHolder holder, int position) {
        ComentariosUi comentariosUi = comentariosUiList.get(position);
        holder.bind(comentariosUi);
    }

    @Override
    public int getItemCount() {
        if (comentariosUiList == null) return 0;
        return comentariosUiList.size();
    }

    public void mostrarLista(List<ComentariosUi> datosClienteList) {
        Log.d(TAG, "mostrarLista : " + datosClienteList.size());
        this.comentariosUiList.clear();
        this.comentariosUiList.addAll(datosClienteList);
        notifyDataSetChanged();
    }

}
