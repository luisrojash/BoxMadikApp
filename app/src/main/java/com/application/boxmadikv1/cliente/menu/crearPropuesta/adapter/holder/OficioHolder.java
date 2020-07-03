package com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.OficiosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.listener.OficioListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OficioHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.textViewConteo)
    TextView textViewConteo;
    @BindView(R.id.textViewOficioDescripcion)
    TextView textViewDescripcion;
    @BindView(R.id.contenidoOficio)
    ConstraintLayout constraintLayoutItem;
    OficiosUi oficiosUi;
    OficioListener oficioListener;

    public OficioHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayoutItem.setOnClickListener(this);
    }

    public void bind(OficiosUi oficiosUi, OficioListener oficioListener) {
        this.oficiosUi = oficiosUi;
        this.oficioListener = oficioListener;
        textViewDescripcion.setText(oficiosUi.getDescripcion());
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.contenidoOficio:
                oficioListener.onClickItemOficio(oficiosUi);
                break;
        }
    }
}
