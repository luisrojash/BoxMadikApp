package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.adapter.holder.DistritoHolder;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.listener.DistritoListener;

import java.util.List;

public class DistritoAdapter extends RecyclerView.Adapter<DistritoHolder> {

    private List<TipoDistritoUi> distritoUis;
    private DistritoListener distritoListener;

    public DistritoAdapter(List<TipoDistritoUi> distritoUis, DistritoListener distritoListener) {
        this.distritoUis = distritoUis;
        this.distritoListener = distritoListener;
    }

    @NonNull
    @Override
    public DistritoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_distritos_perfil, parent, false);
        return new DistritoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistritoHolder holder, int position) {
        TipoDistritoUi distritoUi = distritoUis.get(position);
        holder.bind(distritoUi,distritoListener);
    }

    @Override
    public int getItemCount() {
        if (distritoUis == null) return 0;
        return distritoUis.size();
    }


    public void eliminarItem(TipoDistritoUi especialidadUi) {
        int position = this.distritoUis.indexOf(especialidadUi);
        if (position != -1) {
            this.distritoUis.remove(especialidadUi);
            notifyDataSetChanged();
        }
    }

    public void agregarItem(TipoDistritoUi especialidadUi) {
        this.distritoUis.add(especialidadUi);
        notifyItemInserted(getItemCount() - 1);
    }
    public void setDistritoUis(List<TipoDistritoUi> distritoUiList){
        this.distritoUis.clear();
        distritoUis.addAll(distritoUiList);
        notifyDataSetChanged();
    }

    public void limpiarVista(List<TipoDistritoUi> listaDistritos) {
        this.distritoUis.clear();
        notifyDataSetChanged();
    }
}
