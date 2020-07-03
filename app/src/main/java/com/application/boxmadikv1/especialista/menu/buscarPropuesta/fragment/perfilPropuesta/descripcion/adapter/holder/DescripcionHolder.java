package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescripcionHolder extends RecyclerView.ViewHolder {
    public static final String TAG = DescripcionHolder.class.getSimpleName();
    @BindView(R.id.textViewNombrePropuestaEspecialidad)
    TextView textViewTitulo;
    @BindView(R.id.textViewEstado)
    TextView textViewEstado;

    public DescripcionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(EspecialidadesUi especialidadesUi) {
        Log.d(TAG,"especialidadesUi : " +especialidadesUi.getDescripcionEspecialidad()+"");
        textViewTitulo.setText(especialidadesUi.getDescripcionEspecialidad());
        // Log.d(TAG,"text : " +propuestaInicial.getPE_descripcion());
        textViewEstado.setText("ACTIVO");
        textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_light_green_600));
    }

    private void validarTipoEstado(String pe_estado) {
        switch (pe_estado) {
           /* case "0":
                textViewEstado.setText("INACTIVO");
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_red_A400));
                break;*/
            case "1":
                textViewEstado.setText("ACTIVO");
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_light_green_600));
                break;
        }
    }
}
