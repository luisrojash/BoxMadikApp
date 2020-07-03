package com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter.holder.OficioHolder;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter.holder.RubrosHolder;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.listener.OficioListener;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.listener.RubrosListener;

import java.util.List;

public class RubrosAdapter extends RecyclerView.Adapter<RubrosHolder> {

    public static final String TAG = RubrosAdapter.class.getSimpleName();

    private List<RubrosUi> rubrosUiList;
    private RubrosListener rubrosListener;
    private OficioListener oficioListener;
    private RecyclerView reciclador;

    public RubrosAdapter(List<RubrosUi> rubrosUiList, RubrosListener rubrosListener, OficioListener oficioListener) {
        this.rubrosUiList = rubrosUiList;
        this.rubrosListener = rubrosListener;
        this.oficioListener = oficioListener;
    }

    @NonNull
    @Override
    public RubrosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rubros, parent, false);
        return new RubrosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RubrosHolder holder, int position) {
        RubrosUi rubrosUi = rubrosUiList.get(position);
        holder.bind(rubrosUi, rubrosListener,oficioListener);
    }

    @Override
    public int getItemCount() {
        if (rubrosUiList == null) return 0;
        return rubrosUiList.size();
    }

    public void mostrarLista(List<RubrosUi> rubrosUiList) {
        this.rubrosUiList = rubrosUiList;
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView reciclador) {
        this.reciclador = reciclador;
    }

    private RubrosHolder getRubroHolder(Object o) {
        RubrosHolder rubrosHolder = null;
        int posicion = this.rubrosUiList.indexOf(o);
        Log.d(TAG, "RubrosHolder posicion: " + posicion);
        if (posicion != -1) {
            if (rubrosUiList.get(posicion) instanceof RubrosUi) {
                rubrosHolder = (RubrosHolder) reciclador.findViewHolderForLayoutPosition(posicion);
            }
        }
        return rubrosHolder;
    }

    private OficioHolder getOficioHolder(Object o) {
        OficioHolder oficioHolder = null;
        int posicion = this.rubrosUiList.indexOf(o);
        Log.d(TAG, "CapacidadUi posicion: " + posicion);
        if (posicion != -1) {
            if (rubrosUiList.get(posicion) instanceof RubrosUi) {
                oficioHolder = (OficioHolder) reciclador.findViewHolderForLayoutPosition(posicion);
            }
        }
        return oficioHolder;
    }

    public void mostrarListaOficiosTrue(RubrosUi rubrosUi) {
        RubrosHolder rubrosHolder = getRubroHolder(rubrosUi);
        if (rubrosHolder != null) {
            rubrosHolder.rotarImagenTrue();
        }
    }

    public void mostrarListaOficiosFalse(RubrosUi rubrosUi) {
        RubrosHolder rubrosHolder = getRubroHolder(rubrosUi);
        if (rubrosHolder != null) {
            rubrosHolder.rotarImagenFalse();
        }
    }
}
