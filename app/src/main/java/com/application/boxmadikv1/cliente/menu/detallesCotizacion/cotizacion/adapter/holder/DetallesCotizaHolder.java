package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.adapter.holder;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.listener.DetallesCotizaListener;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetallesCotizaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = DetallesCotizaHolder.class.getSimpleName();
    @BindView(R.id.imgProfile)
    ImageView imageViewProfile;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.textViewFecha)
    TextView textViewFecha;
    @BindView(R.id.textViewMonto)
    TextView textViewMonto;
    @BindView(R.id.cardViewItemView)
    CardView cardViewItemView;
    @BindView(R.id.tagEstado)
    TextView textViewEstadoTag;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;
    @BindView(R.id.textViewDireccion)
    TextView textViewDireccion;
    @BindView(R.id.textViewPCalifficar)
    TextView textViewPendienteCalificar;

    DetallesCotizaListener detallesCotizaListener;
    CotizacionesUi cotizacionesUi;


    public DetallesCotizaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewItemView.setOnClickListener(this);
    }

    public void bind(CotizacionesUi cotizacionesUi, DetallesCotizaListener detallesCotizaListener) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
        this.detallesCotizaListener = detallesCotizaListener;
        this.cotizacionesUi = cotizacionesUi;
        Log.d(TAG, "bind : " + cotizacionesUi.getNombreEspecialista());
        textViewNombre.setText(cotizacionesUi.getNombreEspecialista().toUpperCase());
        textViewFecha.setText("Fecha Cot : " + cotizacionesUi.getFecha());
        textViewMonto.setText("S./ " + cotizacionesUi.getMonto());
        textViewDireccion.setText(cotizacionesUi.getUsuDireccion());
        validarTipoEstrellas();
        Glide.with(itemView.getContext())
                .load(cotizacionesUi.getImagen())
                .into(imageViewProfile);
        validarPorPropuesta(cotizacionesUi);
        //validarTiposCotizacion(cotizacionesUi);
        Log.d(TAG, " PROPUESTA : " + cotizacionesUi.getEstadoPropuesta());
    }

    private void validarPorPropuesta(CotizacionesUi cotizacionesUi) {
        switch (cotizacionesUi.getEstadoPropuesta()) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_CANCELADOS:
                textViewPendienteCalificar.setVisibility(View.GONE);
                validarTiposCotizacion(cotizacionesUi,"");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                textViewPendienteCalificar.setVisibility(View.GONE);
                textViewEstadoTag.setText("Pendiente");
                //validarTiposCotizacion(cotizacionesUi);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:
                /*Aqui para calificar */
                validarTiposCotizacion(cotizacionesUi,"");
                // textViewPendienteCalificar.setVisibility(View.VISIBLE);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO:
                textViewPendienteCalificar.setVisibility(View.GONE);
                textViewPendienteCalificar.setVisibility(View.VISIBLE);
                validarTiposCotizacion(cotizacionesUi,"");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO:
                validarTiposCotizacion(cotizacionesUi,"");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
               /* textViewPendienteCalificar.setText("Cotización Revocada");
                textViewPendienteCalificar.setVisibility(View.VISIBLE);
                textViewEstadoTag.setVisibility(View.GONE);*/
                validarTiposCotizacion(cotizacionesUi,Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS);
                break;

        }
    }

    private void validarTipoEstrellas() {
        //
        if (cotizacionesUi.getPuntuacion() == null) {
            float estrellas = 0;
            ratingBar.setRating(estrellas);
        } else {

            float estrellas = Float.parseFloat(cotizacionesUi.getPuntuacion());
            Log.d(TAG, "estrellas : " + estrellas);
            ratingBar.setRating(estrellas);
        }
    }

    private void validarTiposCotizacion(CotizacionesUi cotizacionesUi,String estadoPropuesta) {
        //0=Cancelado; 1= Pendiente; 2=Aceptado; 3=No Aceptado
        switch (cotizacionesUi.getEstadoCotizacion()) {
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                textViewPendienteCalificar.setVisibility(View.GONE);
                textViewEstadoTag.setVisibility(View.VISIBLE);
                textViewEstadoTag.setText("Cancelado");
                Log.d(TAG, " ESTADO_ESPECIALISTA_REVOCADOS");
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                textViewEstadoTag.setVisibility(View.VISIBLE);
                textViewEstadoTag.setText("No Aceptado");
                textViewPendienteCalificar.setVisibility(View.GONE);
                Log.d(TAG, " ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS");
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
              /*  textViewEstadoTag.setVisibility(View.VISIBLE);
                textViewEstadoTag.setText("Aceptado");*/
                /*if (cotizacionesUi.getEstadoPropuesta().equals("2"))
                    textViewPendienteCalificar.setVisibility(View.VISIBLE);*/

                textViewEstadoTag.setVisibility(View.VISIBLE);
                textViewEstadoTag.setText("Aceptado");
                validaNombreEstadoPropuesta(estadoPropuesta);
                Log.d(TAG, " ESTADO_ESPECIALISTA_ACEPTADO");
                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                textViewEstadoTag.setVisibility(View.VISIBLE);
                textViewPendienteCalificar.setVisibility(View.GONE);
                textViewEstadoTag.setText("No Aceptado");
                Log.d(TAG, " ESTADO_ESPECIALISTA_NO_ACEPTADO");
                break;
            case Constantes.ESTADO_ESPECIALISTA_PAGADO:
                textViewEstadoTag.setVisibility(View.VISIBLE);
                textViewPendienteCalificar.setVisibility(View.GONE);
                textViewEstadoTag.setText("Por Pagar");
                Log.d(TAG, " ESTADO_ESPECIALISTA_PAGADO");
                break;
            case Constantes.ESTADO_ESPECIALISTA_FINALIZADO:
                textViewEstadoTag.setVisibility(View.VISIBLE);
                textViewEstadoTag.setText("Finalizado");
                textViewPendienteCalificar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void validaNombreEstadoPropuesta(String estadoPropuesta) {
        switch (estadoPropuesta){
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
                textViewPendienteCalificar.setText("Cotización Revocada");
                textViewPendienteCalificar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.cardViewItemView:
                onClickItemView(detallesCotizaListener, cotizacionesUi);
                break;
            default:
                break;
        }
    }

    private void onClickItemView(DetallesCotizaListener detallesCotizaListener, CotizacionesUi cotizacionesUi) {
        if (detallesCotizaListener != null) {
            detallesCotizaListener.onClickDetallesCotiza(cotizacionesUi);
        }
    }


}
