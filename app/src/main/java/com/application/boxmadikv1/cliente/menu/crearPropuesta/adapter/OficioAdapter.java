package com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter.holder.OficioHolder;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.OficiosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.listener.OficioListener;

import java.util.List;

public class OficioAdapter extends RecyclerView.Adapter<OficioHolder> {

    public static final String TAG = OficioAdapter.class.getSimpleName();

    private List<OficiosUi> oficiosUiList;
    private OficioListener oficioListener;

    public OficioAdapter(List<OficiosUi> oficiosUiList, OficioListener oficioListener) {
        this.oficiosUiList = oficiosUiList;
        this.oficioListener = oficioListener;
    }

    @NonNull
    @Override
    public OficioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_cliente_oficio, parent, false);
        return new OficioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OficioHolder holder, int position) {
        OficiosUi oficiosUi = oficiosUiList.get(position);
        holder.bind(oficiosUi, oficioListener);
    }

    @Override
    public int getItemCount() {
        if (oficiosUiList == null) return 0;
        return oficiosUiList.size();
    }

    public void mostrarLista(List<OficiosUi> oficiosUiList) {
        this.oficiosUiList = oficiosUiList;
        notifyDataSetChanged();
    }
}
