package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.adapter.holder;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.listener.RubroItemListener;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RubroItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewDescripcion)
    TextView textViewDescripcion;
    @BindView(R.id.cardviewRubro)
    CardView cardView;

    @BindView(R.id.keyBordRotate)
    LottieAnimationView checkLotie;

    RubroItemListener rubroItemListener;
    RubroItemUi rubroItem;

    public RubroItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardView.setOnClickListener(this);

    }

    public void bind(RubroItemUi rubroItem, RubroItemListener rubroItemListener) {
        this.rubroItem = rubroItem;
        this.rubroItemListener = rubroItemListener;
        textViewDescripcion.setText(rubroItem.getDescripcion());
        validacionCheck(rubroItem);
        Glide.with(itemView.getContext()).load(rubroItem.getImagenRubro()).into(imageViewRubro);
    }

    private void validacionCheck(RubroItemUi rubroItem) {
        if (!rubroItem.isEstadoRubro()) {
            mostrarCheck();
        } else {
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
              //  rubroItem.setEstadoRubro(false);
                rubroItemListener.onItemClickRubro(rubroItem);
                break;
        }
    }
}
