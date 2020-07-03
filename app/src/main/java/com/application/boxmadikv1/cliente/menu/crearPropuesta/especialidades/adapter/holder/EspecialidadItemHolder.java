package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.listener.EspecialidadListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EspecialidadItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.textViewDescripcion)
    TextView textViewDescripcionEspecialidad;
    @BindView(R.id.imageViewDelete)
    ImageView imageViewDelete;
    EspecialidadListener listener;
    EspecialidadUi especialidadUi;

    public EspecialidadItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        imageViewDelete.setOnClickListener(this);

    }

    public void bind(EspecialidadUi especialidadUi, EspecialidadListener listener) {
        this.especialidadUi = especialidadUi;
        this.listener = listener;
        textViewDescripcionEspecialidad.setText(especialidadUi.getDescripcion());
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.textViewDescripcion:
               // listener.onItemClickEspecialidadDelete(especialidadUi);
                break;
            case R.id.imageViewDelete:
                listener.onItemClickEspecialidadDelete(especialidadUi);
                break;
        }
    }
}
