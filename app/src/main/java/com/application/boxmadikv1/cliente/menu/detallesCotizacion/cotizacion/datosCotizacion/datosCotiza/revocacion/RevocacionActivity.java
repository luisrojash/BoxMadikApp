package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.DatosCotizacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.adapter.SpinnerTipoCalidadTrabajoAdapter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.adapter.SpinnerTipoMotivoRevocacionAdapter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.adapter.SpinnerTipoSolicitaRevocanteAdapter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.local.RevocacionLocal;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.remote.RevocacionRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.EnvioNotificacionRevocacion;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.MostrarListaTipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.MostrarListaTipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.MostrarListaTipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase.RegistrarRevocacion;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.useCase.CambiarEstadoRevocados;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.bumptech.glide.Glide;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RevocacionActivity extends BaseActivity<RevocacionView, RevocacionPresenter> implements RevocacionView, AdapterView.OnItemSelectedListener, DiscreteSeekBar.OnProgressChangeListener {

    public static final String TAG = RevocacionActivity.class.getSimpleName();

    @BindView(R.id.spinnerMotivoRevocacion)
    Spinner spinnerMotivoRevocacion;
    @BindView(R.id.spinnerCalidadtrabajo)
    Spinner spinnerCalidadtrabajo;
    @BindView(R.id.spinnerSolucionRevocante)
    Spinner spinnerSolucionRevocante;

    @BindView(R.id.seekBarPorcentaje)
    DiscreteSeekBar discreteSeekBarPorcentaje;
    @BindView(R.id.textView19)
    TextView textViewNumeroPorcentaje;

    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextObsevacion;

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;

    @BindView(R.id.textViewNombreOficio)
    TextView textViewNombreRubro;

    @BindView(R.id.btnEnviar)
    Button buttonEnviar;

    //progress dialog
    private ProgressDialog progressDialog;
    private SecurePreferences preferences;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RevocacionPresenter getPresenter() {
        RevocacionRepository revocacionRepository = RevocacionRepository.getmInstance(new RevocacionRemote(),
                new RevocacionLocal(
                        InjectorUtils.provideRevocacionDao(),
                        InjectorUtils.provideSolucionRevocacionDao(),
                        InjectorUtils.provideCalidadTrabajoDao()
                ));
        return new RevocacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new MostrarListaTipoCalidadTrabajoUi(revocacionRepository),
                new MostrarListaTipoMotivoRevocacionUi(revocacionRepository),
                new MostrarListaTipoSolicitaRevocanteUi(revocacionRepository),
                new RegistrarRevocacion(revocacionRepository),
                new CambiarEstadoRevocados(revocacionRepository),
                new EnvioNotificacionRevocacion(revocacionRepository));
    }

    @Override
    protected RevocacionView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_cliente_revocacion);
        ButterKnife.bind(this);
        initAdapterSpinner();
        initView();
    }

    private void initView() {
        discreteSeekBarPorcentaje.setOnProgressChangeListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(getActivity());
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
    }

    private void initAdapterSpinner() {
        spinnerMotivoRevocacion.setOnItemSelectedListener(this);
        spinnerCalidadtrabajo.setOnItemSelectedListener(this);
        spinnerSolucionRevocante.setOnItemSelectedListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String paisCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onKeyPais(paisCodigo);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int itemId = parent.getId();
        switch (itemId) {
            case R.id.spinnerMotivoRevocacion:
                TipoMotivoRevocacionUi tipoMotivoRevocacionUi = (TipoMotivoRevocacionUi) spinnerMotivoRevocacion.getSelectedItem();
                presenter.onSpinnerTipoMotivoRevocacion(tipoMotivoRevocacionUi);
                Log.d(TAG, "spinnerMotivoRevocacion : " + position);
                break;
            case R.id.spinnerCalidadtrabajo:
                TipoCalidadTrabajoUi tipoCalidadTrabajoUi = (TipoCalidadTrabajoUi) spinnerCalidadtrabajo.getSelectedItem();
                presenter.onSpinnerTipoCalidadTrabajo(tipoCalidadTrabajoUi);
                Log.d(TAG, "spinnerCalidadtrabajo" + position);
                break;
            case R.id.spinnerSolucionRevocante:
                TipoSolicitaRevocanteUi tipoSolucionRevocacionUi = (TipoSolicitaRevocanteUi) spinnerSolucionRevocante.getSelectedItem();
                presenter.onSpinnerSolucitaRevocante(tipoSolucionRevocacionUi);
                Log.d(TAG, "spinnerSolucionRevocante" + position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void mostrarListaTipoSolicitaRevocante(List<TipoSolicitaRevocanteUi> solicitaRevocanteUiList) {
        SpinnerTipoSolicitaRevocanteAdapter adapter = new SpinnerTipoSolicitaRevocanteAdapter(this,
                R.layout.custom_spinner_tipo_documento, solicitaRevocanteUiList);
        spinnerSolucionRevocante.setAdapter(adapter);
    }

    @Override
    public void mostrarListaTipoMotivoRevocacion(List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList) {
        SpinnerTipoMotivoRevocacionAdapter adapter = new SpinnerTipoMotivoRevocacionAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoMotivoRevocacionUiList);
        spinnerMotivoRevocacion.setAdapter(adapter);
    }

    @Override
    public void mostrarListaTipoCalidadTrabajo(List<TipoCalidadTrabajoUi> tipoCalidadTrabajoUiList) {
        SpinnerTipoCalidadTrabajoAdapter adapter = new SpinnerTipoCalidadTrabajoAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoCalidadTrabajoUiList);
        spinnerCalidadtrabajo.setAdapter(adapter);
    }

    @Override
    public void mostrarProcentajeTrabajo(int value) {
        textViewNumeroPorcentaje.setText(value + "%");
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(spinnerCalidadtrabajo, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarDialogProgressBar() {
        progressDialog.setMessage("Enviando Revocación..");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarDialogProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void initStarActivityDatosCotiza(String estado, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(this, DatosCotizacionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("estado", estado);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void mostrarData(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        textViewNombreRubro.setText(detallesCotizacionUi.getNombreProyecto().toUpperCase());
        Glide.with(this)
                .load(detallesCotizacionUi.getImageRubro())
                .into(imageViewRubro);
    }

    @Override
    public void habilitarButtonRevocacion() {
        buttonEnviar.setEnabled(true);
    }

    @Override
    public void deshhabilitarButtonRevocacion() {
        buttonEnviar.setEnabled(false);
    }

    @Override
    public void initStartActivityMainPrincipal() {
        Intent intent = new Intent(getActivity(), MenuClienteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /*@Override
    public void initStarActivityDatosCotiza(String estado) {
        Intent intent = new Intent(this, DatosCotizacionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("estado", estado);
        startActivity(intent);
    }*/


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

    @Override
    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
        if (presenter != null) presenter.onProgressChanged(value);
        Log.d(TAG, "onProgressChanged : " + value);
    }

    @Override
    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
        Log.d(TAG, "onStartTrackingTouch : " + seekBar.getProgress());
    }

    @Override
    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
        Log.d(TAG, "onStartTrackingTouch : " + seekBar.getNumericTransformer());
    }


    @OnClick({R.id.btnEnviar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnEnviar:
                String observacion = textInputEditTextObsevacion.getText().toString();
                String otros= "otros";
               // presenter.onClickEnviar(Constantes.isResultadoCharacterSpecial(Constantes.removeAccents(observacion), otros).trim());
                presenter.onClickEnviar(observacion);
                break;
        }
    }
}
