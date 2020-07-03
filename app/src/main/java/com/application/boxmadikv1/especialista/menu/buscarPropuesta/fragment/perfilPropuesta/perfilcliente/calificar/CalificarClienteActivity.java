package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.calificar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
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
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalificarClienteActivity extends BaseActivity<CalificarClienteView, CalificarClientePresenter> implements CalificarClienteView {

    public static final String TAG = CalificarClienteActivity.class.getSimpleName();

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
    protected CalificarClientePresenter getPresenter() {
        return new CalificarClientePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected CalificarClienteView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.calificar_cliente_activity);
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
    public void mostrarDataInicial(ItemUi itemUi, String nombreCliente, String paisCliente, String imagenCliente) {
        textViewNombre.setText(nombreCliente.toUpperCase());
        textViewFecha.setText(Constantes.f_fecha_letras(itemUi.getFechaPropuesta()));
        Glide.with(getActivity())
                .load(imagenCliente)
                .into(imageViewProfile);
        Glide.with(getActivity())
                .load(paisCliente)
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
        Intent intent = new Intent(this, MenuEspecialistaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick({R.id.btnEnviar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnEnviar:
                float ratingValue = ratingBar.getRating();
                String editTextComentario = textInputEditTextComentario.getText().toString();
              //  presenter.onClickCalificar(ratingValue, Constantes.isResultadoCharacterSpecial(editTextComentario,""));
                presenter.onClickCalificar(ratingValue, editTextComentario);
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ventana_principal) {

            return true;
        } else {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
