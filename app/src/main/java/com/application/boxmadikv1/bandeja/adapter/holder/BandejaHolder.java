package com.application.boxmadikv1.bandeja.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.bandeja.entidad.BandejaUi;
import com.application.boxmadikv1.bandeja.listener.BandejaListener;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BandejaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String TAG = BandejaHolder.class.getSimpleName();
    @BindView(R.id.img_profile)
    CircleImageView circleImageViewUserFoto;
    @BindView(R.id.img_rubro)
    CircleImageView circleImageViewRubro;
    @BindView(R.id.txt_name)
    TextView textViewNombrePropuesta;
    @BindView(R.id.txt_NombrePersona)
    TextView textViewNombrePersona;
    @BindView(R.id.txt_message)
    TextView textViewMensaje;
    @BindView(R.id.cardViewItem)
    ConstraintLayout constraintLayoutItem;
    @BindView(R.id.txt_counter)
    public AppCompatTextView compatTextViewCount;
    @BindView(R.id.img_status_message)
    AppCompatImageView appCompatImageViewCheck;

    BandejaListener listener;
    BandejaUi bandejaUi;

    public BandejaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayoutItem.setOnClickListener(this);
    }

    public void bind(BandejaUi bandejaUi, BandejaListener listener) {
        this.listener = listener;
        this.bandejaUi = bandejaUi;
        textViewMensaje.setVisibility(View.VISIBLE);
        Log.d(TAG, "bandejaUi.getNombreReceptor() : " + bandejaUi.getNombreReceptor());
        textViewNombrePersona.setText(bandejaUi.getNombreReceptor().toUpperCase());
        textViewNombrePropuesta.setText(bandejaUi.getNombrePropuesta().toUpperCase());
        textViewMensaje.setText(bandejaUi.getMensaje());
        validarEstadoGrupoLeido(bandejaUi);
        //compatTextViewCount.setVisibility(View.VISIBLE);

        Glide.with(itemView.getContext())
                .load(bandejaUi.getFotoReceptor())
                .into(circleImageViewUserFoto);
        Glide.with(itemView.getContext())
                .load(bandejaUi.getImagenRubro())
                .into(circleImageViewRubro);
        if (bandejaUi.getConteoMensaje() > 0) {
            compatTextViewCount.setVisibility(View.VISIBLE);
            compatTextViewCount.setText(bandejaUi.getConteoMensaje() + "");
        } else {
            compatTextViewCount.setVisibility(View.GONE);
        }


    }

    private void validarEstadoGrupoLeido(BandejaUi bandejaUi) {
        if (bandejaUi.getEstadoGrupoLeido() == null) {

            appCompatImageViewCheck.setVisibility(View.VISIBLE);
            appCompatImageViewCheck.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_double_check_colored));
            return;


        }
        switch (bandejaUi.getEstadoGrupoLeido()) {
            case "1"://Entregado
                appCompatImageViewCheck.setVisibility(View.VISIBLE);
                appCompatImageViewCheck.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_double_check));
                break;
            case "2"://Leido
                appCompatImageViewCheck.setVisibility(View.VISIBLE);
                appCompatImageViewCheck.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_double_check_colored));
                break;
            default:
                appCompatImageViewCheck.setVisibility(View.VISIBLE);
                appCompatImageViewCheck.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_double_check));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.cardViewItem:
                listener.onClickBandeja(bandejaUi);
                break;
        }
    }

    public void mostrarConteo() {
        compatTextViewCount.setVisibility(View.VISIBLE);

    }

    public void ocultarConteo() {
        compatTextViewCount.setVisibility(View.GONE);
    }
}
