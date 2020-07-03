package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemHolder extends RecyclerView.ViewHolder {

    public static final String TAG = ItemHolder.class.getSimpleName();

    @BindView(R.id.textViewNombrePropuestaEspecialidad)
    TextView textViewTitulo;
    @BindView(R.id.textViewEstado)
    TextView textViewEstado;

    public ItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(PropuestaEspecialidad propuestaInicial) {
        textViewTitulo.setText(propuestaInicial.getPE_descripcion());
        Log.d(TAG,"text : " +propuestaInicial.getPE_descripcion());
        validarTipoEstado(propuestaInicial.getPE_estado());
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
