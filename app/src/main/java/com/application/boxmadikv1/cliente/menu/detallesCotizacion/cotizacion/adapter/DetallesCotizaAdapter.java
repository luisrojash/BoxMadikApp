package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.adapter.holder.DetallesCotizaHolder;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.listener.DetallesCotizaListener;

import java.util.List;

public class DetallesCotizaAdapter extends RecyclerView.Adapter<DetallesCotizaHolder> {

    private List<CotizacionesUi> cotizacionesUiList;
    private DetallesCotizaListener detallesCotizaListener;

    public DetallesCotizaAdapter(List<CotizacionesUi> cotizacionesUiList, DetallesCotizaListener detallesCotizaListener) {
        this.cotizacionesUiList = cotizacionesUiList;
        this.detallesCotizaListener = detallesCotizaListener;
    }

    @NonNull
    @Override
    public DetallesCotizaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalles_cotizaciones, parent, false);
        return new DetallesCotizaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetallesCotizaHolder holder, int position) {
        CotizacionesUi cotizacionesUi = cotizacionesUiList.get(position);
        holder.bind(cotizacionesUi,detallesCotizaListener);
    }

    @Override
    public int getItemCount() {
        if (cotizacionesUiList == null) return 0;
        return cotizacionesUiList.size();
    }

    public void mostrarLista(List<CotizacionesUi> clientePendienteUiList) {
        this.cotizacionesUiList = clientePendienteUiList;
        notifyDataSetChanged();
    }
}
