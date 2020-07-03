package com.application.boxmadikv1.especialista.menu.seleccionRubros.adapter.holder;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.application.boxmadikv1.R;

import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.listener.SeleccionRubrosListener;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeleccionRubrosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String TAG = SeleccionRubrosHolder.class.getSimpleName();

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewDescripcion)
    TextView textViewDescripcion;
    @BindView(R.id.cardviewRubro)
    CardView cardView;

    @BindView(R.id.keyBordRotate)
    LottieAnimationView checkLotie;

    SeleccionRubrosListener listener;
    SeleccionRubrosUi seleccionRubrosUi;


    public SeleccionRubrosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardView.setOnClickListener(this);
    }

    public void bind(SeleccionRubrosUi seleccionRubrosUi, SeleccionRubrosListener listener) {
        this.listener = listener;
        this.seleccionRubrosUi = seleccionRubrosUi;
        textViewDescripcion.setText(seleccionRubrosUi.getDescripcion());
        validacionCheck(seleccionRubrosUi);
        Glide.with(itemView.getContext()).load(seleccionRubrosUi.getImageRubro()).into(imageViewRubro);
    }

    private void validacionCheck(SeleccionRubrosUi seleccionRubrosUi) {
        if(!seleccionRubrosUi.isEstadoRubro()){
            mostrarCheck();
        }else{
            ocultarCheck();
        }
    }

    public void mostrarCheck() {
        checkLotie.setVisibility(View.VISIBLE);
        checkLotie.playAnimation();
    }

    public void ocultarCheck() {
        checkLotie.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.cardviewRubro:
                Log.d(TAG, "cardView");
                listener.onClickItemRubro(seleccionRubrosUi);
                break;
            default:
               // listener.onClickItemRubro(seleccionRubrosUi);
                Log.d(TAG, "default");
                break;
        }
    }
}
