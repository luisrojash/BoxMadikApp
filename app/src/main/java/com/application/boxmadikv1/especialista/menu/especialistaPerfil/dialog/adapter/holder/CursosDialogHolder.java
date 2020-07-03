package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.adapter.holder;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursosDialogHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.check_validado)
    ImageView imageViewValidacion;
    @BindView(R.id.textNombreCurso)
    TextView textViewNombreCurso;
    @BindView(R.id.textTipoEstudioNombre)
    TextView textViewNombreTipoEspe;
    @BindView(R.id.textNombreCentro)
    TextView textViewNombreCentro;
    @BindView(R.id.textFechaInicio)
    TextView textViewFechaInicio;
    @BindView(R.id.textFechaFin)
    TextView textViewFechaFin;
    @BindView(R.id.textViewValidacionCursos)
    TextView textViewEstadoCalifcacion;

    public CursosDialogHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(CursosUi cursosUi) {
        String fechaInicio = "<b>" + "F.Inicio:" + "</b> " + cursosUi.getMesInicioEspeEstudios() + "/" + cursosUi.getAnoInicioEspeEstudios();
        String fechaFin = "<b>" + "F.Fin:" + "</b> " + cursosUi.getMesFinEspeEstudios() + "/" + cursosUi.getAnoFinEspeEstudios();
        //  mytextview.setText(Html.fromHtml(sourceString));
        textViewNombreCurso.setText(cursosUi.getNombreEspeEstudios());
        textViewNombreTipoEspe.setText(cursosUi.getTipoEstudioNombre());
        textViewNombreCentro.setText(cursosUi.getNombreCentroEstu());
        textViewFechaInicio.setText(Html.fromHtml(fechaInicio));
        textViewFechaFin.setText(Html.fromHtml(fechaFin));
        validacionEstado(cursosUi.getEstadoValidacion());
    }

    private void validacionEstado(String estadoValidacion) {
        switch (estadoValidacion) {
            case "0":
                imageViewValidacion.setBackgroundResource(R.drawable.check_antecedentes_sin_validar);
                textViewEstadoCalifcacion.setText("Sin Validar");
                break;
            case "1":
                imageViewValidacion.setBackgroundResource(R.drawable.check_antecedentes_validado);
                textViewEstadoCalifcacion.setText("Validado");
                break;
        }
    }

}
