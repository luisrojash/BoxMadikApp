package com.application.boxmadikv1.especialista.menu.edicionDatos.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.edicionDatos.adapter.holder.DatosEspecHolder;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecUi;
import com.application.boxmadikv1.especialista.menu.edicionDatos.listener.DatosEspecListener;

import java.util.List;

public class DatosEspecAdapter extends RecyclerView.Adapter<DatosEspecHolder> {
    public static final String TAG = DatosEspecAdapter.class.getSimpleName();

    private List<DatosEspecUi> datosEspecUiList;
    private DatosEspecListener datosEspecListener;

    public DatosEspecAdapter(List<DatosEspecUi> datosEspecUiList, DatosEspecListener datosEspecListener) {
        this.datosEspecUiList = datosEspecUiList;
        this.datosEspecListener = datosEspecListener;
    }

    @NonNull
    @Override
    public DatosEspecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datos_espect, parent, false);
        return new DatosEspecHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatosEspecHolder holder, int position) {
        DatosEspecUi datosEspecUi = datosEspecUiList.get(position);
        holder.bind(datosEspecUi, datosEspecListener);
    }

    @Override
    public int getItemCount() {
        if (datosEspecUiList == null) return 0;
        return datosEspecUiList.size();
    }

    public void setMostraLista(List<DatosEspecUi> datosEspecUis) {
        this.datosEspecUiList.clear();
        //this.datosEspecUiList = datosEspecUis;
        this.datosEspecUiList.addAll(datosEspecUis);
        notifyDataSetChanged();
    }

    public void actualizacionEstadoDireccion(boolean b, String s) {
       // Log.d(TAG,"estadoDireccionAdpter: " + b + "/ tipoEstadoIdAdapter : "+s);
        for (DatosEspecUi datosEspecUi : datosEspecUiList) {
         //   Log.d(TAG, "actualizacionEstadoDireccion : " + datosEspecUi.getId() + " Second Id : " + s);
            if (datosEspecUi.getId().equals(s)) {
                Log.d(TAG, "actualizacionEstadoDireccionTRUE : " + datosEspecUi.getId() + " Second Id : " + s +
                                    " / DESCRIPCION : "+datosEspecUi.getDescripcion());
                datosEspecUi.setEstado(b);
                continue;
            }
            continue;
        }
        notifyDataSetChanged();
    }
}
