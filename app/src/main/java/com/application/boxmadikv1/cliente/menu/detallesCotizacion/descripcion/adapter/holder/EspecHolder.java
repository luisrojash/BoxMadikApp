package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.entidad.EspecialidadUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EspecHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textViewNombrePropuestaEspecialidad)
    TextView textViewTitulo;
    @BindView(R.id.textViewEstado)
    TextView textViewEstado;

    public EspecHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(EspecialidadUi especialidadUi) {
        textViewTitulo.setText(especialidadUi.getDescripcion());
        validarTipoEstado(especialidadUi.getPE_estado());
    }

    private void validarTipoEstado(String pe_estado) {
        switch (pe_estado) {
            case "0":
                textViewEstado.setText("INACTIVO");
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_red_A400));
                break;
            case "1":
                textViewEstado.setText("ACTIVO");
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_light_green_600));
                break;
        }
    }
}
