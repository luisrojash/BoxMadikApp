package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.adapter.holder;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad.DatosCliente;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilClienteHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.circleImageView2)
    CircleImageView circleImageViewFotoPerfil;
    @BindView(R.id.textViewNombre2)
    TextView textViewDatosCompletos;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextComentarios;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;
    @BindView(R.id.constraintLayoutContentContentComentarios)
    ConstraintLayout constraintLayoutContentContentComentarios;
    @BindView(R.id.textViewTituloPropuesta)
    TextView textViewTituloPropuesta;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewFecha)
    TextView textViewFecha;

    public PerfilClienteHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(DatosCliente datosCliente) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
        textViewTituloPropuesta.setText(datosCliente.getPropuestaTitulo().toUpperCase());
        textViewFecha.setText(Constantes.f_fecha_letras(datosCliente.getFechaRegistroComentario()));
        Glide.with(itemView).load(datosCliente.getRubroImagen()).into(imageViewRubro);
        if (datosCliente.getComentario() == null) {
            constraintLayoutContentContentComentarios.setVisibility(View.GONE);
        } else {
            textViewDatosCompletos.setText(validarNombreNull(datosCliente));
            constraintLayoutContentContentComentarios.setVisibility(View.VISIBLE);
            textInputEditTextComentarios.setText(datosCliente.getComentario());
            Glide.with(itemView)
                    .load(datosCliente.getFoto())
                    .into(circleImageViewFotoPerfil);
            if (datosCliente.getEstrellas() == null) return;
            float estrellas = Float.parseFloat(datosCliente.getEstrellas());
            ratingBar.setRating(estrellas);
        }


    }

    private String validarNombreNull(DatosCliente datosCliente) {
        String nombreUsuario = "";
        if (datosCliente.getNombre() == null) {
            nombreUsuario = datosCliente.getUsuRazonSocial();
        } else {
            nombreUsuario = datosCliente.getNombre() + " " + datosCliente.getApellido();
        }
        return nombreUsuario;
    }
}
