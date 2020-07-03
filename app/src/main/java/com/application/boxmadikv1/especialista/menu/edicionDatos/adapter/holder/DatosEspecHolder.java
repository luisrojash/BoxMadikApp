package com.application.boxmadikv1.especialista.menu.edicionDatos.adapter.holder;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecUi;
import com.application.boxmadikv1.especialista.menu.edicionDatos.listener.DatosEspecListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatosEspecHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String TAG = DatosEspecHolder.class.getSimpleName();

    @BindView(R.id.textViewDescripcion)
    TextView textViewDescripcion;
    @BindView(R.id.cardviewItem)
    CardView cardViewItem;
    @BindView(R.id.imageCheck)
    ImageView imageViewCheck;
    @BindView(R.id.imageViewTipo)
    ImageView imageView;
    private DatosEspecListener datosEspecListener;
    private DatosEspecUi datosEspecUi;

    public DatosEspecHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(DatosEspecUi datosEspecUi, DatosEspecListener datosEspecListener) {
        this.datosEspecListener = datosEspecListener;
        this.datosEspecUi = datosEspecUi;
        cardViewItem.setOnClickListener(this);
        textViewDescripcion.setText(datosEspecUi.getDescripcion());
        imageEstado(datosEspecUi);
        validarTipoInformacion(datosEspecUi);
    }

    private void imageEstado(DatosEspecUi datosEspecUi) {
        switch (datosEspecUi.getTipoEstado()){
            case "cliente":
                //  if (view != null) view.mostrarTextoToolbar("Edicio");
                validarImagenCliente(datosEspecUi);
                break;
            case "especialista":
                // if (view != null) view.mostrarTextoToolbar("Cliente");
                validarImagenEspecialista(datosEspecUi);
                break;
            default:
                break;
        }


    }

    private void validarImagenEspecialista(DatosEspecUi datosEspecUi) {
        switch (datosEspecUi.getId()) {
            case "1":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_perfil_datos));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "2":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_ubicacion));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "3":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_zona_trabajo));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "4":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_zona_trabajo));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "5":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_presentacion));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "6":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_cursos_estudios));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "7":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_bancario));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "8":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_key));
                imageViewCheck.setVisibility(View.GONE);
                break;
        }
    }

    private void validarImagenCliente(DatosEspecUi datosEspecUi) {
        switch (datosEspecUi.getId()) {
            case "1":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_perfil_datos));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "2":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_ubicacion));
                imageViewCheck.setVisibility(View.VISIBLE);
                break;
            case "3":
                imageView.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_key));
                imageViewCheck.setVisibility(View.GONE);
                break;

        }
    }

    private void validarTipoInformacion(DatosEspecUi datosEspecUi) {
        if (datosEspecUi.isEstado()) {
            Log.d(TAG, "DatosEspecHolder : " + datosEspecUi.getId());
            imageViewCheck.setImageDrawable(itemView.getResources().getDrawable(R.drawable.check_done));
        } else {
            Log.d(TAG, "DatosEspecHolder : " + datosEspecUi.isEstado());
            imageViewCheck.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_warning_info));
        }
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.cardviewItem:
                datosEspecListener.onClickItem(datosEspecUi);
                break;
        }
    }
}
