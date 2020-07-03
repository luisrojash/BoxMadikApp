package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
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
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.DesembolsarRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.remote.DesembolsarRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.useCase.CambiarEstadoFinalizado;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.useCase.RegistrarDesembolso;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DesembolsarActivity extends BaseActivity<DesembolsarView, DesembolsarPresenter> implements DesembolsarView {

    public static final String TAG = DesembolsarActivity.class.getSimpleName();

    @BindView(R.id.imgProfile)
    ImageView imageViewProfile;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.textViewDireccion)
    TextView textViewDireccion;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextComentario;
    @BindView(R.id.textViewFecha)
    TextView textViewFecha;
    //progress dialog
    private ProgressDialog progressDialog;
    @BindView(R.id.id_banderaRight)
    ImageView imageViewBandera;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected DesembolsarPresenter getPresenter() {
        DesembolsarRepository desembolsarRepository = DesembolsarRepository.getmInstance(new DesembolsarRemote());
        return new DesembolsarPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new RegistrarDesembolso(desembolsarRepository),
                new CambiarEstadoFinalizado(desembolsarRepository));
    }

    @Override
    protected DesembolsarView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_cliente_desembolsar);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initView() {
        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarDatainicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        textViewNombre.setText(cotizacionesUi.getNombreEspecialista().toUpperCase());
        textViewDireccion.setText("-Aun No hay-");
        Glide.with(getActivity())
                .load(cotizacionesUi.getImagen())
                .into(imageViewProfile);
        Glide.with(getActivity())
                .load(cotizacionesUi.getPaisImagen())
                .into(imageViewBandera);
        textViewFecha.setText(cotizacionesUi.getFecha());
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ocultarDialogProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void mostrarDialogProgressBar() {
        progressDialog.setMessage("Enviado Desembolso..");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void initStartActivityMenu(String estado) {
        Intent intent = new Intent(this, MenuClienteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("estado", estado);
        startActivity(intent);
        Log.d(TAG, "initStartActivityMenuCliente");
    }

    @OnClick({R.id.btnEnviar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnEnviar:
                float ratingValue = ratingBar.getRating();
                String editTextComentario = textInputEditTextComentario.getText().toString();
                //presenter.onClickFinalizarDesemBolso(ratingValue, Constantes.isResultadoCharacterSpecial(editTextComentario,""));
                presenter.onClickFinalizarDesemBolso(ratingValue,editTextComentario);
                break;
        }
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
