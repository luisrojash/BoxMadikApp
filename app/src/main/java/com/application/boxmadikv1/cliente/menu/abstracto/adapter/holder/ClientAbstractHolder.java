package com.application.boxmadikv1.cliente.menu.abstracto.adapter.holder;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.abstracto.entidad.ClienteEstadosUi;
import com.application.boxmadikv1.cliente.menu.abstracto.listener.ClientAbstractListener;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientAbstractHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.txtNombreProyecto)
    TextView textViewNombreProyecto;
    @BindView(R.id.btnCountCotizacion)
    Button buttonConteoCotizacion;
    @BindView(R.id.textFechaIniciadaRespt)
    TextView textViewFechaIniciada;
    @BindView(R.id.textPropuestoRespt)
    TextView textViewPropuesto;
    @BindView(R.id.textPromedioRespt)
    TextView textViewPromedio;
    @BindView(R.id.textViewEstado)
    TextView textViewEstado;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;

    @BindView(R.id.cardviewCurso)
    CardView cardViewItem;

    ClientAbstractListener abstractListener;
    ClienteEstadosUi clienteEstadosUi;

    public ClientAbstractHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewItem.setOnClickListener(this);
    }

    public void bind(ClienteEstadosUi clienteEstadosUi, ClientAbstractListener abstractListener) {
        this.clienteEstadosUi = clienteEstadosUi;
        this.abstractListener = abstractListener;
        validarTipoEstado(clienteEstadosUi.getTiposEstado());
        textViewNombreProyecto.setText(clienteEstadosUi.getNombreProyecto().toUpperCase());
        buttonConteoCotizacion.setText(clienteEstadosUi.getNumeroCotizacion() + "");
        textViewFechaIniciada.setText( Constantes.f_fecha_letras(clienteEstadosUi.getFechaRealizo()));
        textViewPropuesto.setText("S/. " + clienteEstadosUi.getPropuesto());
        textViewPromedio.setText( "S/. " + clienteEstadosUi.getPromedioCotizacion());

        textViewEstado.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
        Glide.with(itemView.getContext()).load(clienteEstadosUi.getImage()).into(imageViewRubro);
    }

    private void validarTipoEstado(String tiposEstado) {
        switch (tiposEstado) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_CANCELADOS:
                textViewEstado.setText("Cancelado");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                textViewEstado.setText("Pendiente");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:
                textViewEstado.setText("Proceso");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO:
                textViewEstado.setText("Finalizado");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.cardviewCurso:
                if (abstractListener != null) abstractListener.onClickEstado(clienteEstadosUi);
                break;
        }
    }
}
