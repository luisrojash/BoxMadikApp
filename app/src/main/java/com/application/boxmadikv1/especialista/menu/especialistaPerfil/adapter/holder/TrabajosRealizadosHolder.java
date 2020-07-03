package com.application.boxmadikv1.especialista.menu.especialistaPerfil.adapter.holder;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.entidad.TrabajosRealizadosUi;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class TrabajosRealizadosHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textViewTituloPropuesta)
    TextView textViewTituloPropuesta;
    @BindView(R.id.textViewFecha)
    TextView TextViewFecha;
    @BindView(R.id.edtContentDetalle)
    TextInputEditText textInputEditTextDetallePropuesta;
    @BindView(R.id.textViewNombre2)
    TextView textViewNombreCliente;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBarCliente;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextComentario;
    @BindView(R.id.circleImage)
    CircleImageView circleImageViewPerfilCliente;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewCursos)
    Button buttonCursos;

    public TrabajosRealizadosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(TrabajosRealizadosUi trabajosRealizadosUi) {
        LayerDrawable stars = (LayerDrawable) ratingBarCliente.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
        textViewTituloPropuesta.setText(trabajosRealizadosUi.getNombrePropuesta().toUpperCase());
        TextViewFecha.setText("Fecha : " + trabajosRealizadosUi.getFechaPropuesta());
        textInputEditTextDetallePropuesta.setText(trabajosRealizadosUi.getDetallePropuesta());
        textViewNombreCliente.setText(nombreValidarNombreNull(trabajosRealizadosUi));
        float porcentaje = Float.parseFloat(trabajosRealizadosUi.getEstrellasEspec());
        ratingBarCliente.setRating(porcentaje);
        textInputEditTextComentario.setText(trabajosRealizadosUi.getComentarioEspec());
        Glide.with(itemView).load(trabajosRealizadosUi.getFotoCliente()).into(circleImageViewPerfilCliente);
        Glide.with(itemView).load(trabajosRealizadosUi.getImagenRubro()).into(imageViewRubro);
    }

    private String nombreValidarNombreNull(TrabajosRealizadosUi trabajosRealizadosUi) {
        String nombre = "";
        if (trabajosRealizadosUi.getNombreCliente() == null) {
            nombre = trabajosRealizadosUi.getUsuNombreRazonSocial().toLowerCase();
        } else {
            nombre = trabajosRealizadosUi.getNombreCliente().toUpperCase();
        }
        return nombre;
    }
}
