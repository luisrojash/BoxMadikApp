package com.application.boxmadikv1.notificaciones.adapter.holder;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;
import com.application.boxmadikv1.notificaciones.listener.NotificacionListener;
import com.application.boxmadikv1.utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificacionesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = NotificacionesHolder.class.getSimpleName();
    @BindView(R.id.txtTitulo)
    TextView textViewTitulo;
    @BindView(R.id.txtNombreProyecto)
    TextView textViewNombreProyecto;
    @BindView(R.id.textViewFechaHora)
    TextView textViewFechaHora;
    @BindView(R.id.cardViewNoti)
    CardView cardViewNoti;
    @BindView(R.id.imageViewNoti)
    ImageView imageViewNoti;
    NotificacionesUi notificacionesUi;
    @BindView(R.id.imageViewOptions)
    ImageView imageViewOptions;

    NotificacionListener notificacionListener;

    public NotificacionesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewNoti.setOnClickListener(this);
        imageViewOptions.setOnClickListener(this);
    }

    public void bind(NotificacionesUi notificacionesUi, NotificacionListener notificacionListener) {
        this.notificacionListener = notificacionListener;
        this.notificacionesUi = notificacionesUi;
        textViewTitulo.setText(notificacionesUi.getTitulo());
        textViewNombreProyecto.setText(notificacionesUi.getSubTitulo());
        validarLeido(notificacionesUi);
        validarTipoNotificacion(notificacionesUi);
        if (notificacionesUi.getFecha() == null) return;
        textViewFechaHora.setText(Constantes.f_fecha_letras(notificacionesUi.getFecha()));
    }

    private void validarTipoNotificacion(NotificacionesUi notificacionesUi) {

        switch (notificacionesUi.getTipoNotificacion()) {
            case Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK:
                imageViewNoti.setImageDrawable(itemView.getResources().getDrawable(R.drawable.mensaje_boxmadik));
                Log.d(TAG, "TIPO_NOTIFICACION_MENSAJE_BOXMADIK : ");
                imageViewOptions.setVisibility(View.GONE);
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA:
                Log.d(TAG, "TIPO_NOTIFICACION_CREACION_PROPUESTA : ");
                 imageViewNoti.setImageDrawable(itemView.getResources().getDrawable(R.drawable.mensaje_boxmadik));
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION:
                Log.d(TAG, "TIPO_NOTIFICACION_CREACION_REVOCACION : ");
                imageViewNoti.setImageDrawable(itemView.getResources().getDrawable(R.drawable.revocacion_cliente));
                break;
            case Constantes.TIPO_NOTIFICACION_AUTORIZACION_PAGO:
                imageViewNoti.setImageDrawable(itemView.getResources().getDrawable(R.drawable.cotizacion_aceptada));
                break;
            case Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION:
                Log.d(TAG, "TIPO_NOTIFICACION_ACEPTO_COTIZACION : ");
                imageViewNoti.setImageDrawable(itemView.getResources().getDrawable(R.drawable.cotizacion_aceptada));
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION:
                Log.d(TAG, "TIPO_NOTIFICACION_CREACION_COTIZACION : ");
                imageViewNoti.setImageDrawable(itemView.getResources().getDrawable(R.drawable.crea_cotizacion));
                break;
            case Constantes.TIPO_NOTIFICACION_RESPUESTA_NOTIFICACION:
                Log.d(TAG, "TIPO_NOTIFICACION_CREACION_COTIZACION : ");
                imageViewNoti.setImageDrawable(itemView.getResources().getDrawable(R.drawable.revocacion_cliente));
                break;

        }
    }

    private void validarLeido(NotificacionesUi notificacionesUi) {

        if (notificacionesUi.isEstadoLeido()) {
            cardViewNoti.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
        } else {
            cardViewNoti.setBackgroundColor(itemView.getResources().getColor(R.color.md_blue_50));
        }
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.cardViewNoti:
                if (notificacionListener != null)
                    notificacionListener.onClickNoti(notificacionesUi);
                break;
            case R.id.imageViewOptions:
                if (notificacionListener != null)
                    notificacionListener.onClickOptions(notificacionesUi);
                break;
            default:
                break;
        }

    }
}
