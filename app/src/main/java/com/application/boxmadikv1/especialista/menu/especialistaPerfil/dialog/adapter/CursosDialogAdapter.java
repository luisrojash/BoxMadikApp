package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.adapter.holder.CursosDialogHolder;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;

import java.util.List;

public class CursosDialogAdapter extends RecyclerView.Adapter<CursosDialogHolder> {

    private List<CursosUi> cursosUiList;

    public CursosDialogAdapter(List<CursosUi> cursosUiList) {
        this.cursosUiList = cursosUiList;
    }

    @NonNull
    @Override
    public CursosDialogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cursos_dialog, parent, false);
        return new CursosDialogHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursosDialogHolder holder, int position) {
        CursosUi cursosUi = cursosUiList.get(position);
        holder.bind(cursosUi);
    }

    @Override
    public int getItemCount() {
        if (cursosUiList == null) return 0;
        return cursosUiList.size();
    }

    public void mostrarLista(List<CursosUi> cursosUiList) {
        this.cursosUiList=cursosUiList;
        notifyDataSetChanged();
    }
}
