package com.application.boxmadikv1.bandeja.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.bandeja.adapter.holder.BandejaHolder;
import com.application.boxmadikv1.bandeja.entidad.BandejaUi;
import com.application.boxmadikv1.bandeja.listener.BandejaListener;

import java.util.List;

public class BandejaAdapter extends RecyclerView.Adapter<BandejaHolder> {
    public static final String TAG = BandejaAdapter.class.getSimpleName();

    private List<BandejaUi> bandejaUiList;
    private BandejaListener listener;
    private RecyclerView reciclador;

    public BandejaAdapter(List<BandejaUi> bandejaUiList, BandejaListener listener) {
        this.bandejaUiList = bandejaUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BandejaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bandeja_mensaje, parent, false);
        return new BandejaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BandejaHolder holder, int position) {
        BandejaUi bandejaUi = bandejaUiList.get(position);
        holder.bind(bandejaUi, listener);
    }

    @Override
    public int getItemCount() {
        if (bandejaUiList == null) return 0;
        return bandejaUiList.size();
    }

    public void mostrarLista(List<BandejaUi> bandejaUiList) {
        this.bandejaUiList.clear();
        this.bandejaUiList.addAll(bandejaUiList);
        notifyDataSetChanged();
    }

    public void actualizarItemBandeja(BandejaUi bandejaUi) {
        Log.d(TAG, "actualizarItemBandeja : ");
        int position = this.bandejaUiList.indexOf(bandejaUi);

        if (position != -1) {
             this.bandejaUiList.set(position, bandejaUi);
             notifyItemChanged(position);
        }
       // BandejaHolder bandejaHolder = (BandejaHolder) reciclador.findViewHolderForLayoutPosition(position);
       // if (bandejaHolder != null) bandejaHolder.mostrarConteo();
    }

    public void setRecyclerView(RecyclerView reciclador) {
        this.reciclador = reciclador;
    }


}
