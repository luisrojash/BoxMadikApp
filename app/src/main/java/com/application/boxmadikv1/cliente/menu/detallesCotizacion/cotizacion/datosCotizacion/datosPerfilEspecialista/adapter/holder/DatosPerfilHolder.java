package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.adapter.holder;

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
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.listener.DatosPerfilListener;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DatosPerfilHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = DatosPerfilHolder.class.getSimpleName();

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

    private DatosPropuestaUi datosPropuestaUi;
    private DatosPerfilListener datosPerfilListener;

    public DatosPerfilHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        buttonCursos.setOnClickListener(this);
    }

    public void bind(DatosPropuestaUi datosPropuestaUi, DatosPerfilListener datosPerfilListener) {
        LayerDrawable stars = (LayerDrawable) ratingBarCliente.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
        this.datosPropuestaUi = datosPropuestaUi;
        this.datosPerfilListener = datosPerfilListener;
        datosPerfilListener.onClickButton(datosPropuestaUi);
        textViewTituloPropuesta.setText(datosPropuestaUi.getNombrePropuesta().toUpperCase());
        TextViewFecha.setText("Fecha : " + datosPropuestaUi.getFechaPropuesta());
        textInputEditTextDetallePropuesta.setText(datosPropuestaUi.getDetallePropuesta());
        textViewNombreCliente.setText(datosPropuestaUi.getNombreCliente().toUpperCase());
        float porcentaje = Float.parseFloat(datosPropuestaUi.getEstrellasEspec());
        ratingBarCliente.setRating(porcentaje);
        textInputEditTextComentario.setText(datosPropuestaUi.getComentarioEspec());
        Glide.with(itemView).load(datosPropuestaUi.getFotoCliente()).into(circleImageViewPerfilCliente);
        Glide.with(itemView).load(datosPropuestaUi.getImagenRubro()).into(imageViewRubro);


    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.textViewCursos:
                datosPerfilListener.onClickButton(datosPropuestaUi);
                break;
        }
    }
}
