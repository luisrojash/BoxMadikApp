package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.calificacion;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalificacionActivity extends BaseActivity<CalificacionView, CalificacionPresenter> implements CalificacionView {

    public static final String TAG = CalificacionActivity.class.getSimpleName();

    @BindView(R.id.imgProfile)
    ImageView imageViewProfile;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextComentario;
    @BindView(R.id.textViewFecha)
    TextView textViewFecha;
    @BindView(R.id.id_banderaRight)
    ImageView imageViewBandera;

    //progress dialog
    private ProgressDialog progressDialog;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CalificacionPresenter getPresenter() {
        return new CalificacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected CalificacionView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_calificacion_cli);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        progressDialog = new ProgressDialog(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
    }


    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        textViewNombre.setText(cotizacionesUi.getNombreEspecialista().toUpperCase());
        textViewFecha.setText(Constantes.f_fecha_letras(cotizacionesUi.getFecha()));
        Glide.with(getActivity())
                .load(cotizacionesUi.getImagen())
                .into(imageViewProfile);
        Glide.with(getActivity())
                .load(cotizacionesUi.getPaisImagen())
                .into(imageViewBandera);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarProgressBarDialog() {
        progressDialog.setMessage("Calificando ...");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressBarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void finishActivity(String mensaje) {
        finish();
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btnEnviar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnEnviar:
                float ratingValue = ratingBar.getRating();
                String editTextComentario = textInputEditTextComentario.getText().toString();
                presenter.onClickCalificar(ratingValue, editTextComentario);
                break;
        }
    }
}
