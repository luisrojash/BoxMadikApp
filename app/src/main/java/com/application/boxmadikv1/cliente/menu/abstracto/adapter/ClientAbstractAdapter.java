package com.application.boxmadikv1.cliente.menu.abstracto.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.abstracto.adapter.holder.ClientAbstractHolder;
import com.application.boxmadikv1.cliente.menu.abstracto.entidad.ClienteEstadosUi;
import com.application.boxmadikv1.cliente.menu.abstracto.listener.ClientAbstractListener;

import java.util.List;

public class ClientAbstractAdapter extends RecyclerView.Adapter<ClientAbstractHolder> {

    private List<ClienteEstadosUi> clienteEstadosUis;

    private ClientAbstractListener abstractListener;

    public ClientAbstractAdapter(List<ClienteEstadosUi> clienteEstadosUis, ClientAbstractListener abstractListener) {
        this.clienteEstadosUis = clienteEstadosUis;
        this.abstractListener = abstractListener;
    }

    @NonNull
    @Override
    public ClientAbstractHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todos_cliente, parent, false);
        return new ClientAbstractHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientAbstractHolder holder, int position) {
        ClienteEstadosUi clienteEstadosUi = clienteEstadosUis.get(position);
        holder.bind(clienteEstadosUi,abstractListener);
    }

    @Override
    public int getItemCount() {
        if (clienteEstadosUis == null) return 0;
        return clienteEstadosUis.size();
    }

    public void mostrarLista(List<ClienteEstadosUi> clienteEstadosUis) {
        this.clienteEstadosUis.clear();
        this.clienteEstadosUis.addAll(clienteEstadosUis);
        //this.clienteEstadosUis = clienteEstadosUis;
        notifyDataSetChanged();
    }


    public void agregarItem(ClienteEstadosUi clienteEstadosUi) {
        this.clienteEstadosUis.add(clienteEstadosUi);
        notifyItemInserted(getItemCount() - 1);
    }

    public void editarItem(ClienteEstadosUi clienteEstadosUi) {
        int position = this.clienteEstadosUis.indexOf(clienteEstadosUi);
        if (position != -1) {
            this.clienteEstadosUis.set(position, clienteEstadosUi);
            notifyItemChanged(position);
        }
    }

    public void eliminarItem(ClienteEstadosUi clienteEstadosUi) {
        int position = this.clienteEstadosUis.indexOf(clienteEstadosUi);
        if (position != -1) {
            this.clienteEstadosUis.remove(clienteEstadosUi);
            notifyItemRemoved(position);
        }
    }

    public void actualizarItem(ClienteEstadosUi clienteEstadosUi) {
        int position = this.clienteEstadosUis.indexOf(clienteEstadosUi);
        if (position != -1) {
            this.clienteEstadosUis.set(position, clienteEstadosUi);
            notifyItemChanged(position);
        }
    }
}
