package com.application.boxmadikv1.especialista.menu.abstracto.adapter.holder;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.abstracto.listener.EspecAbstractListener;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EspecAbstractHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.txtNombreProyecto)
    TextView textViewNombreProyecto;
    @BindView(R.id.btnCountCotizacion)
    Button buttonConteoCotizacion;
    @BindView(R.id.textCotizadoRespt)
    TextView textViewCotizado;
    @BindView(R.id.textMontoCotiRespt)
    TextView textViewMontoCoti;
    @BindView(R.id.textPromedioRespt)
    TextView textViewPromedio;
    @BindView(R.id.textViewEstado)
    TextView textViewEstado;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.btnEliminar)
    Button buttonEliminar;
    @BindView(R.id.cardViewItemView)
    CardView cardViewItem;

    @BindView(R.id.textViewPCalifficar)
    TextView textViewCalificarPendiente;
    @BindView(R.id.tagEstado)
    TextView textViewEstadoTag;
    private EspecAbstractListener especAbstractListener;
    private EspecialistaEstadosUi especialistaEstadosUi;

    public EspecAbstractHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        buttonEliminar.setOnClickListener(this);
        cardViewItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.btnEliminar:
                if (especAbstractListener != null)
                    especAbstractListener.onClickEliminarItem(especialistaEstadosUi);
                break;
            case R.id.cardViewItemView:
                if (especAbstractListener != null)
                    especAbstractListener.onClickItem(especialistaEstadosUi);
                break;
        }
    }


    public void bind(EspecialistaEstadosUi especialistaEstadosUi, EspecAbstractListener especAbstractListener) {
        this.especialistaEstadosUi = especialistaEstadosUi;
        this.especAbstractListener = especAbstractListener;
        //validarTipoEstados(especialistaEstadosUi);
        validaTipoEstado(especialistaEstadosUi);
        // validaTipoEstadoCalifica(especialistaEstadosUi);
        validarEstadoEliminar(especialistaEstadosUi);
        textViewNombreProyecto.setText(especialistaEstadosUi.getNombreProyecto().toUpperCase());
        buttonConteoCotizacion.setText(especialistaEstadosUi.getNumeroCotizacion() + "");
        textViewCotizado.setText(Constantes.f_fecha_letras(especialistaEstadosUi.getFechaRealizo()));
        textViewMontoCoti.setText(" PEN " + especialistaEstadosUi.getMontoCotizado());
        textViewPromedio.setText(" S./ " + especialistaEstadosUi.getCostoPromedio());
        Glide.with(itemView.getContext()).load(especialistaEstadosUi.getImagenRubro()).into(imageViewRubro);
        //validarMostrarItemEnviados(especialistaEstadosUi);
        validaTipoRevocacion(especialistaEstadosUi);

    }

    private void validaTipoRevocacion(EspecialistaEstadosUi especialistaEstadosUi) {
        if (especialistaEstadosUi.getEstadoRevocacion() == null) return;
        switch (especialistaEstadosUi.getEstadoRevocacion()) {
            case "1":
                textViewCalificarPendiente.setVisibility(View.VISIBLE);
                textViewCalificarPendiente.setText("Pendiente de Respuesta");
                break;
            case "2":
                textViewCalificarPendiente.setVisibility(View.GONE);
                break;
            default:
                textViewCalificarPendiente.setVisibility(View.GONE);
                break;
        }

    }

    private void validarMostrarItemEnviados(EspecialistaEstadosUi especialistaEstadosUi) {
        /*if (especialistaEstadosUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE)){

            if(especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO)){

            }else if (especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO)){

            }
        }*/

        if (especialistaEstadosUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE) &&
                especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO) ||
                especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_ACEPTADO)) {

            cardViewItem.setVisibility(View.GONE);
        } else if (especialistaEstadosUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE) &&
                especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_ACEPTADO)) {
            cardViewItem.setVisibility(View.VISIBLE);

        } else {

        }
    }

    private void validaTipoEstadoCalifica(EspecialistaEstadosUi especialistaEstadosUi) {
        if (especialistaEstadosUi.getEstadoPropuestaCalificar() == null) {
            textViewCalificarPendiente.setVisibility(View.GONE);
            return;
        }
        if (especialistaEstadosUi.getEstadoPropuestaCalificar().equals("0")) {
            textViewCalificarPendiente.setVisibility(View.GONE);
        } else if (especialistaEstadosUi.getEstadoPropuestaCalificar().equals("1")) {
            textViewCalificarPendiente.setVisibility(View.VISIBLE);
        } else if (especialistaEstadosUi.getEstadoPropuestaCalificar().equals("2")) {
            textViewCalificarPendiente.setVisibility(View.GONE);
        }
    }

    private void validaTipoEstado(EspecialistaEstadosUi especialistaEstadosUi) {
        if (especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_PAGADO) &&
                especialistaEstadosUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO)) {
        /*if (especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_PAGADO) &&
                especialistaEstadosUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO)) {*/
            //textViewCalificarPendiente.setVisibility(View.GONE);
            textViewCalificarPendiente.setVisibility(View.VISIBLE);
            textViewCalificarPendiente.setText("Pendiente Calificar");
            /*textViewEstadoTag.setVisibility(View.VISIBLE);
            textViewEstadoTag.setText("Pagado");*/
        } else if (especialistaEstadosUi.getCotiEstado().equals(Constantes.ESTADO_ESPECIALISTA_FINALIZADO) &&
                especialistaEstadosUi.getPropuestaEstado().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO)) {
            textViewCalificarPendiente.setVisibility(View.GONE);
            /*if(especialistaEstadosUi.getEstadoPropuestaCalificar().equals("0")){

            }else if (especialistaEstadosUi.getEstadoPropuestaCalificar().equals("1")){
                textViewCalificarPendiente.setVisibility(View.GONE);
                textViewEstadoTag.setText("Finalizado");
                textViewEstadoTag.setVisibility(View.GONE);
            }else if (especialistaEstadosUi.getEstadoPropuestaCalificar().equals("2")){
                textViewCalificarPendiente.setVisibility(View.VISIBLE);
                textViewEstadoTag.setText("Finalizado");
                textViewEstadoTag.setVisibility(View.VISIBLE);
            }else {
                textViewCalificarPendiente.setVisibility(View.GONE);
                textViewEstadoTag.setText("Finalizado");
                textViewEstadoTag.setVisibility(View.GONE);
            }*/
            // textViewCalificarPendiente.setVisibility(View.VISIBLE);
            /*textViewEstadoTag.setText("Finalizado");
            textViewEstadoTag.setVisibility(View.VISIBLE);*/
        } else {
            textViewCalificarPendiente.setVisibility(View.GONE);
        }

    }

    private void validarEstadoEliminar(EspecialistaEstadosUi especialistaEstadosUi) {
        switch (especialistaEstadosUi.getPropuestaEstado()) {
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                buttonEliminar.setVisibility(View.VISIBLE);
                break;
            default:
                buttonEliminar.setVisibility(View.GONE);
                break;
        }
    }

    private void validarTipoEstados(EspecialistaEstadosUi especialistaEstadosUi) {
        switch (especialistaEstadosUi.getCotiEstado()) {
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                buttonEliminar.setVisibility(View.GONE);
                textViewEstado.setText("Revocados");
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_red_A400));
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                textViewEstado.setText("Enviado");
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_indigo_700));
                buttonEliminar.setVisibility(View.VISIBLE);
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                textViewEstado.setText("Proceso");
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_orange_400));
                buttonEliminar.setVisibility(View.GONE);
                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                buttonEliminar.setVisibility(View.GONE);
                textViewEstado.setText("Finalizado");
                textViewEstado.setTextColor(itemView.getResources().getColor(R.color.md_light_green_A400));
                break;
        }
    }
}
