package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.listener.DistritoListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DistritoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.textViewDescripcion)
    TextView textViewDesscripcion;
    @BindView(R.id.imageViewDelete)
    ImageView imageViewDelete;
    private DistritoListener distritoListener;
    private TipoDistritoUi tipoDistritoUi;

    public DistritoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        imageViewDelete.setOnClickListener(this);
    }

    public void bind(TipoDistritoUi distritoUi, DistritoListener distritoListener) {
        this.tipoDistritoUi = distritoUi;
        this.distritoListener = distritoListener;
        textViewDesscripcion.setText(distritoUi.getDescripcion());
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.imageViewDelete:
                if (distritoListener != null) distritoListener.onClickEliminar(tipoDistritoUi);
                break;
        }
    }
}
