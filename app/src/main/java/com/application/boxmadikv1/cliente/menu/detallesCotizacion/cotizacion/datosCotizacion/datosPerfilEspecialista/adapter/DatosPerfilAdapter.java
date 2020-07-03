package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.adapter.holder.DatosPerfilHolder;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.listener.DatosPerfilListener;

import java.util.List;

public class DatosPerfilAdapter extends RecyclerView.Adapter<DatosPerfilHolder> {

    private List<DatosPropuestaUi> datosPropuestaUiList;
    private DatosPerfilListener datosPerfilListener;

    public DatosPerfilAdapter(List<DatosPropuestaUi> datosPropuestaUiList, DatosPerfilListener datosPerfilListener) {
        this.datosPropuestaUiList = datosPropuestaUiList;
        this.datosPerfilListener = datosPerfilListener;
    }

    @NonNull
    @Override
    public DatosPerfilHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datos_perfil_comentario, parent, false);
        return new DatosPerfilHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatosPerfilHolder holder, int position) {
        DatosPropuestaUi datosPropuestaUi = datosPropuestaUiList.get(position);
        holder.bind(datosPropuestaUi, datosPerfilListener);
    }

    @Override
    public int getItemCount() {
        if (datosPropuestaUiList == null) return 0;
        return datosPropuestaUiList.size();
    }

    public void setMostrarLista(List<DatosPropuestaUi> datosPropuestaUiList) {
        this.datosPropuestaUiList.clear();
        this.datosPropuestaUiList.addAll(datosPropuestaUiList);
        //this.datosPropuestaUiList = datosPropuestaUiList;
        notifyDataSetChanged();
    }


}
