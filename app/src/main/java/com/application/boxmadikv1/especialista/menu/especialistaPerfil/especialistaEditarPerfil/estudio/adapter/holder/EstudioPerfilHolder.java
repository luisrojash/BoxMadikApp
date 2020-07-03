package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.DatosCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.listener.CursosListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EstudioPerfilHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
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
    @BindView(R.id.itemClicked)
    ConstraintLayout constraintLayoutClicked;
    private CursosListener cursosListener;
    private DatosCursos datosCursos;

    public EstudioPerfilHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayoutClicked.setOnClickListener(null);
        constraintLayoutClicked.setOnLongClickListener(this);
    }

    public void bind(DatosCursos datosCursos, CursosListener cursosListener) {
        String fechaInicio = "<b>" + "F.Inicio:" + "</b> " +  datosCursos.getFechaInicioEstudio();
        String fechaFin = "<b>" + "F.Fin:" + "</b> " + datosCursos.getFechanFinEstudio();

        this.cursosListener = cursosListener;
        this.datosCursos = datosCursos;
        textViewNombreCurso.setText("CURSO : " + datosCursos.getNombreCurso().toUpperCase());
        textViewNombreTipoEspe.setText("TIPO ESTUDIO : " + datosCursos.getTipoEstudio().toUpperCase());
        textViewNombreCentro.setText(datosCursos.getNombreCrentroEstudio());
        textViewFechaInicio.setText(Html.fromHtml(fechaInicio));
        textViewFechaFin.setText(Html.fromHtml(fechaFin));
        validacionEstado(datosCursos.getValidacion());
    }

    private void validacionEstado(String validacion) {
        switch (validacion) {
            case "0":
                imageViewValidacion.setBackgroundResource(R.drawable.check_antecedentes_sin_validar);
                break;
            case "1":
                imageViewValidacion.setBackgroundResource(R.drawable.check_antecedentes_validado);
                break;
        }
    }


    @Override
    public boolean onLongClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.itemClicked:
                cursosListener.onLongClickCursoEliminar(datosCursos);
                break;
        }
        return true;
    }
}
