package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.adapter.holder.PerfilClienteHolder;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad.DatosCliente;

import java.util.List;

public class PerfilClienteAdapter extends RecyclerView.Adapter<PerfilClienteHolder> {

    private List<DatosCliente> datosClienteList;

    public PerfilClienteAdapter(List<DatosCliente> datosClienteList) {
        this.datosClienteList = datosClienteList;
    }

    @NonNull
    @Override
    public PerfilClienteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perfil_cliente_comentarios, parent, false);
        return new PerfilClienteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilClienteHolder holder, int position) {
        DatosCliente datosCliente = datosClienteList.get(position);
        holder.bind(datosCliente);
    }

    @Override
    public int getItemCount() {
        if (datosClienteList == null) return 0;
        return datosClienteList.size();
    }

    public void setDatosClienteList(List<DatosCliente> datosClienteList) {
        this.datosClienteList.clear();
        this.datosClienteList.addAll(datosClienteList);
        notifyDataSetChanged();
    }
}
