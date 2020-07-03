package com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.listener.MisRubrosListener;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MisRubrosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String TAG = MisRubrosHolder.class.getSimpleName();

    @BindView(R.id.imageNombreRubro)
    ImageView imageRubro;
    @BindView(R.id.textViewNombreRubro)
    TextView textViewNombreRubro;
    @BindView(R.id.fabPrimero)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.contenidoItem)
    ConstraintLayout constraintLayoutContenidoItem;
    MisRubrosListener misRubrosListener;
    MisRubrosUi misRubrosUi;

    public MisRubrosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayoutContenidoItem.setOnClickListener(this);
    }

    public void bind(MisRubrosUi misRubrosUi, MisRubrosListener misRubrosListener) {
        this.misRubrosListener = misRubrosListener;
        this.misRubrosUi = misRubrosUi;
        validarImageRubro(misRubrosUi);
        validarCheck(misRubrosUi);

    }

    private void validarCheck(MisRubrosUi misRubrosUi) {
        if (misRubrosUi.isEstadoCheck()) {
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton.setVisibility(View.GONE);
        }
    }

    private void validarImageRubro(MisRubrosUi misRubrosUi) {
        textViewNombreRubro.setText(misRubrosUi.getDescripcion());
        if (misRubrosUi.getImagenRubros().isEmpty()) {
            //logo
            imageRubro.setBackgroundResource(R.drawable.especialista);
        } else {
            Glide.with(itemView.getContext())
                    .load(misRubrosUi.getImagenRubros())
                    .into(imageRubro);
        }
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.contenidoItem:
                validarOnclickRubro(misRubrosUi);
                break;
            default:
                break;
        }
    }

    private void validarOnclickRubro(MisRubrosUi misRubrosUi) {
        switch (misRubrosUi.getTipoRubro()) {
            case MisRubrosUi.DEFAULT_MIS_RUBROS:
                Log.d(TAG, "NO HACER NADA");
                break;
            case MisRubrosUi.MIS_RUBROS_NORMAL:
                misRubrosListener.onClickRubros(misRubrosUi);
                break;
        }
    }
}
