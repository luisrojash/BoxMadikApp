package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.adapter.holder.EstudioPerfilHolder;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.DatosCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.listener.CursosListener;

import java.util.List;

public class EstudioPerfilAdapter extends RecyclerView.Adapter<EstudioPerfilHolder> {

    private List<DatosCursos> datosCursosList;
    private CursosListener cursosListener;

    public EstudioPerfilAdapter(List<DatosCursos> datosCursosList, CursosListener cursosListener) {
        this.datosCursosList = datosCursosList;
        this.cursosListener = cursosListener;
    }

    @NonNull
    @Override
    public EstudioPerfilHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudio_perfil_spec, parent, false);
        return new EstudioPerfilHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudioPerfilHolder holder, int position) {
        DatosCursos datosCursos = datosCursosList.get(position);
        holder.bind(datosCursos,cursosListener);
    }

    @Override
    public int getItemCount() {
        if (datosCursosList == null) return 0;
        return datosCursosList.size();
    }

    public void eliminarItem(DatosCursos datosCursos) {
        int position = this.datosCursosList.indexOf(datosCursos);
        if (position != -1) {
            this.datosCursosList.remove(datosCursos);
            notifyDataSetChanged();
        }
    }

    public void agregarItem(DatosCursos datosCursos) {
        this.datosCursosList.add(datosCursos);
        notifyItemInserted(getItemCount() - 1);
    }
    public void mostrarLista(List<DatosCursos> datosCursosList){
        this.datosCursosList.clear();
        this.datosCursosList.addAll(datosCursosList);
        notifyDataSetChanged();
    }
}
