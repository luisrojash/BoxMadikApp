package com.application.boxmadikv1.especialista.menu.seleccionRubros.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;

import com.application.boxmadikv1.especialista.menu.seleccionRubros.adapter.holder.SeleccionRubrosHolder;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.listener.SeleccionRubrosListener;

import java.util.List;

public class SeleccionRubrosAdapter extends RecyclerView.Adapter<SeleccionRubrosHolder> {

    public static final String TAG = SeleccionRubrosAdapter.class.getSimpleName();

    private List<SeleccionRubrosUi> seleccionRubrosUiList;
    private RecyclerView reciclador;
    private SeleccionRubrosListener listener;

    public SeleccionRubrosAdapter(List<SeleccionRubrosUi> seleccionRubrosUiList, SeleccionRubrosListener listener) {
        this.seleccionRubrosUiList = seleccionRubrosUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SeleccionRubrosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seleccion_rubros_item_especialista, parent, false);
        return new SeleccionRubrosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeleccionRubrosHolder holder, int position) {
        SeleccionRubrosUi seleccionRubrosUi = seleccionRubrosUiList.get(position);
        holder.bind(seleccionRubrosUi,listener);
    }

    @Override
    public int getItemCount() {
        if (seleccionRubrosUiList == null) return 0;
        return seleccionRubrosUiList.size();
    }

    public void mostrarLista(List<SeleccionRubrosUi> seleccionRubrosUiList) {
        this.seleccionRubrosUiList = seleccionRubrosUiList;
        notifyDataSetChanged();
    }


    public void setRecyclerView(RecyclerView reciclador) {
        this.reciclador = reciclador;
    }

    private SeleccionRubrosHolder getRubroHolder(Object o) {
        SeleccionRubrosHolder rubrosHolder = null;
        int posicion = this.seleccionRubrosUiList.indexOf(o);
        Log.d(TAG, "RubrosHolder posicion: " + posicion);
        if (posicion != -1) {
            if (seleccionRubrosUiList.get(posicion) instanceof SeleccionRubrosUi) {
                rubrosHolder = (SeleccionRubrosHolder) reciclador.findViewHolderForLayoutPosition(posicion);
            }
        }
        return rubrosHolder;
    }



    public void mostrarItemCheckTrue(SeleccionRubrosUi rubrosUi) {
        SeleccionRubrosHolder rubrosHolder = getRubroHolder(rubrosUi);
        if (rubrosHolder != null) {
            rubrosHolder.mostrarCheck();
        }
    }

    public void mostrarItemCheckFalse(SeleccionRubrosUi rubrosUi) {
        SeleccionRubrosHolder rubrosHolder = getRubroHolder(rubrosUi);
        if (rubrosHolder != null) {
            rubrosHolder.ocultarCheck();
        }
    }


}
