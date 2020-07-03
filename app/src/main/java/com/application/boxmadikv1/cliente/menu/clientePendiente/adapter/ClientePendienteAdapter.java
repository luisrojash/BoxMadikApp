package com.application.boxmadikv1.cliente.menu.clientePendiente.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.clientePendiente.adapter.holder.ClientePendienteHolder;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.cliente.menu.clientePendiente.listener.ClientePendienteListener;

import java.util.List;

public class ClientePendienteAdapter extends RecyclerView.Adapter<ClientePendienteHolder> {

    private List<ClientePendienteUi> clientePendienteUiList;

    private ClientePendienteListener clientePendienteListener;

    public ClientePendienteAdapter(List<ClientePendienteUi> clientePendienteUiList, ClientePendienteListener clientePendienteListener) {
        this.clientePendienteUiList = clientePendienteUiList;
        this.clientePendienteListener = clientePendienteListener;
    }

    @NonNull
    @Override
    public ClientePendienteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todos_cliente, parent, false);
        return new ClientePendienteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientePendienteHolder holder, int position) {
        ClientePendienteUi clientePendienteUi = clientePendienteUiList.get(position);
        holder.bind(clientePendienteUi,clientePendienteListener);
    }

    @Override
    public int getItemCount() {
        if (clientePendienteUiList == null) return 0;
        return clientePendienteUiList.size();
    }

    public void mostrarLista(List<ClientePendienteUi> clientePendienteUiList) {
        this.clientePendienteUiList = clientePendienteUiList;
        notifyDataSetChanged();
    }


    public void agregarItem(ClientePendienteUi clientePendienteUi) {
        this.clientePendienteUiList.add(clientePendienteUi);
        notifyItemInserted(getItemCount() - 1);
    }

    public void editarItem(ClientePendienteUi clientePendienteUi) {
        int position = this.clientePendienteUiList.indexOf(clientePendienteUi);
        if (position != -1) {
            this.clientePendienteUiList.set(position, clientePendienteUi);
            notifyItemChanged(position);
        }
    }

    public void eliminarItem(ClientePendienteUi clientePendienteUi) {
        int position = this.clientePendienteUiList.indexOf(clientePendienteUi);
        if (position != -1) {
            this.clientePendienteUiList.remove(clientePendienteUi);
            notifyItemRemoved(position);
        }
    }

    public void actualizarItem(ClientePendienteUi clientePendienteUi) {
        int position = this.clientePendienteUiList.indexOf(clientePendienteUi);
        if (position != -1) {
            this.clientePendienteUiList.set(position, clientePendienteUi);
            notifyItemChanged(position);
        }
    }
}
