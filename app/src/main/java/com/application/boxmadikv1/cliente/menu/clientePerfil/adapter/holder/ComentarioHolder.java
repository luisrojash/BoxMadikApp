package com.application.boxmadikv1.cliente.menu.clientePerfil.adapter.holder;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.clientePerfil.entidad.ComentariosUi;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComentarioHolder extends RecyclerView.ViewHolder {

    public static final String TAG = ComentarioHolder.class.getSimpleName();

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


    public ComentarioHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ComentariosUi comentariosUi) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
        textViewTituloPropuesta.setText(comentariosUi.getNombrePropuesta().toUpperCase());
        textViewFecha.setText(Constantes.f_fecha_letras(comentariosUi.getFechaComentario()));
        textInputEditTextComentarios.setText(comentariosUi.getTextoComentario());
        Glide.with(itemView).load(comentariosUi.getImagenRubro()).into(imageViewRubro);
        Log.d(TAG, "comentariosUi.getTextoComentario() " + comentariosUi.getTextoComentario());
        if (comentariosUi.getEstrellas() == null) return;
        float estrellas = Float.parseFloat(comentariosUi.getEstrellas());
        ratingBar.setRating(estrellas);

    }


}
