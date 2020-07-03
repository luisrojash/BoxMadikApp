package com.application.boxmadikv1.especialista.menu.especialistaEnviados.adapter.holder;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.listener.EspecialistaEnviadoListener;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EspecialistaEnviadosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.txtNombreProyecto)
    TextView textViewNombreProyecto;
    @BindView(R.id.btnCountCotizacion)
    Button buttonConteoCotizacion;
    @BindView(R.id.textCotizado)
    TextView textViewCotizado;
    @BindView(R.id.textMontoCoti)
    TextView textViewMontoCoti;
    @BindView(R.id.textPromedio)
    TextView textViewPromedio;
    @BindView(R.id.textViewEstado)
    TextView textViewEstado;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.btnEliminar)
    Button buttonEliminar;
    @BindView(R.id.cardViewItemView)
    CardView cardViewItem;
    private EspecialistaEnviadoListener especialistaEnviadoListener;
    private EspecialistaEnviadosUi especialistaEnviadosUi;

    public EspecialistaEnviadosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        buttonEliminar.setOnClickListener(this);
        cardViewItem.setOnClickListener(this);
    }

    public void bind(EspecialistaEnviadosUi especialistaEnviadosUi, EspecialistaEnviadoListener especialistaEnviadoListener) {
        this.especialistaEnviadosUi = especialistaEnviadosUi;
        this.especialistaEnviadoListener = especialistaEnviadoListener;
        textViewNombreProyecto.setText(especialistaEnviadosUi.getNombreProyecto());
        buttonConteoCotizacion.setText(especialistaEnviadosUi.getNumeroCotizacion() + "");
        textViewCotizado.setText("Cotizado el : " + especialistaEnviadosUi.getFechaRealizo());
        textViewMontoCoti.setText("Monto Coti : PEN " + especialistaEnviadosUi.getMontoCotizado());

        textViewPromedio.setText("Promedio : S./ " + especialistaEnviadosUi.getCostoPromedio());

        textViewEstado.setText("Enviado");
        textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_red_A400));

        Glide.with(itemView.getContext()).load(especialistaEnviadosUi.getImagenRubro()).into(imageViewRubro);
        //validacionEstado(especialistaEnviadosUi);
        //validacionTipoRubro(especialistaEnviadosUi);
    }

   /* private void validacionTipoRubro(EspecialistaEnviadosUi especialistaEnviadosUi) {
        switch (especialistaEnviadosUi.getTipoRubro()) {
            case 1:
                imageViewRubro.setImageResource(R.drawable.ic_electricista);
                break;
            case 2:
                imageViewRubro.setImageResource(R.drawable.ic_albanieria);
                break;
            case 3:
                imageViewRubro.setImageResource(R.drawable.ic_informatica);
                break;
        }
    }*/

    private void validacionEstado(EspecialistaEnviadosUi especialistaEnviadosUi) {
        switch (especialistaEnviadosUi.getEstado()) {
            case "EN PROCESO":
                textViewEstado.setText(especialistaEnviadosUi.getEstado());
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
                break;
            case "FINALIZADO":
                textViewEstado.setText(especialistaEnviadosUi.getEstado());
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_light_green_600));
                break;
            case "CANCELADO":
                textViewEstado.setText(especialistaEnviadosUi.getEstado());
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_red_A400));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.btnEliminar:
                if (especialistaEnviadoListener != null)
                    especialistaEnviadoListener.onClickEliminarItem(especialistaEnviadosUi);
                break;
            case R.id.cardViewItemView:
                if (especialistaEnviadoListener != null)
                    especialistaEnviadoListener.onClickItem(especialistaEnviadosUi);
                break;
        }
    }
}
