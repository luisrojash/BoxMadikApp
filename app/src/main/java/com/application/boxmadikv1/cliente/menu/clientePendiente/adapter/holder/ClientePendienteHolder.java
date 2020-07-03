package com.application.boxmadikv1.cliente.menu.clientePendiente.adapter.holder;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.cliente.menu.clientePendiente.listener.ClientePendienteListener;
import com.bumptech.glide.Glide;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientePendienteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.txtNombreProyecto)
    TextView textViewNombreProyecto;
    @BindView(R.id.btnCountCotizacion)
    Button buttonConteoCotizacion;
    @BindView(R.id.textFechaIniciada)
    TextView textViewFechaIniciada;
    @BindView(R.id.textPropuesto)
    TextView textViewPropuesto;
    @BindView(R.id.textPromedio)
    TextView textViewPromedio;
    @BindView(R.id.textViewEstado)
    TextView textViewEstado;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;

    @BindView(R.id.cardviewCurso)
    CardView cardViewItem;

    ClientePendienteListener clientePendienteListener;
    ClientePendienteUi clientePendienteUi;

    public ClientePendienteHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewItem.setOnClickListener(this);
    }




    public void bind(ClientePendienteUi clientePendienteUi, ClientePendienteListener clientePendienteListener) {
        this.clientePendienteUi = clientePendienteUi;
        if (clientePendienteListener != null)
            this.clientePendienteListener = clientePendienteListener;
        textViewNombreProyecto.setText(clientePendienteUi.getNombreProyecto());
        buttonConteoCotizacion.setText(clientePendienteUi.getNumeroCotizacion() + "");
        textViewFechaIniciada.setText("Ingresado el : " + clientePendienteUi.getFechaRealizo());
        textViewPropuesto.setText("Presupuesto : " +"S/. "+ clientePendienteUi.getPropuesto());
        textViewPromedio.setText("Promedio : " +"S/. "+ clientePendienteUi.getPromedioCotizacion());
        textViewEstado.setText("Pendiente");
        textViewEstado.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
        Glide.with(itemView.getContext()).load(clientePendienteUi.getImage()).into(imageViewRubro);

        // validacionEstado(clientePendienteUi);
       //validacionTipoRubro(clientePendienteUi);

    }

    private void validacionTipoRubro(ClientePendienteUi clientePendienteUi) {
        switch (clientePendienteUi.getTipoRubro()) {
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
    }

    private void validacionEstado(ClientePendienteUi clientePendienteUi) {
        switch (clientePendienteUi.getEstado()) {
            case "EN PROCESO":
                textViewEstado.setText(clientePendienteUi.getEstado());
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
                break;
            case "FINALIZADO":
                textViewEstado.setText(clientePendienteUi.getEstado());
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_light_green_600));
                break;
            case "CANCELADO":
                textViewEstado.setText(clientePendienteUi.getEstado());
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_red_A400));
                break;
        }


    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.cardviewCurso:
                clientePendienteListener.onClickPendiente(clientePendienteUi);
                break;
        }
    }
}
