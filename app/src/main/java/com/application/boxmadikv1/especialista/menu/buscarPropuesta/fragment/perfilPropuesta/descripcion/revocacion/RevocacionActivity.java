package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.dao.motivoRevocacion.MotivoRevocacionDaoImpl;
import com.application.boxmadikv1.dao.solucionRevocacion.SolucionRevocacionDaoImpl;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.adapter.SpinnerTipoMotivoRevocacionAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.adapter.SpinnerTipoSolucionRevocacionAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.local.RevocacionLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.remote.RevocacionRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoSolucionRevocacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase.ObtenerListaMotivoRevocacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase.ObtenerListaSolucionRevocacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase.RegistrarRevocacionEspecialista;
import com.bumptech.glide.Glide;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RevocacionActivity extends BaseActivity<RevocacionView, RevocacionPresenter> implements RevocacionView, DiscreteSeekBar.OnProgressChangeListener, AdapterView.OnItemSelectedListener {


    public static final String TAG = RevocacionActivity.class.getSimpleName();

    @BindView(R.id.seekBarPorcentaje)
    DiscreteSeekBar discreteSeekBarPorcentaje;
    @BindView(R.id.textView19)
    TextView textViewNumeroPorcentaje;

    @BindView(R.id.spinnerMotivoRevocacion)
    Spinner spinnerMotivoRevocacion;
    @BindView(R.id.spinnerSolucionRevocacion)
    Spinner spinnerSolucionRevocacion;

    @BindView(R.id.edtContent)
    TextInputEditText textInputObservacion;

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewNombreOficio)
    TextView textViewNombrePropuesta;

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
    protected RevocacionPresenter getPresenter() {
        RevocacionRepository revocacionRepository = new RevocacionRepository(new RevocacionLocal(
                new MotivoRevocacionDaoImpl(),
                new SolucionRevocacionDaoImpl()
        ),
                new RevocacionRemote());
        return new RevocacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerListaMotivoRevocacion(revocacionRepository),
                new ObtenerListaSolucionRevocacion(revocacionRepository),
                new RegistrarRevocacionEspecialista(revocacionRepository));
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
        setContentView(R.layout.activity_menu_especialista_revocacion);
        ButterKnife.bind(this);
        initView();
        initSpinnerAdapter();
        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void initSpinnerAdapter() {
        spinnerMotivoRevocacion.setOnItemSelectedListener(this);
        spinnerSolucionRevocacion.setOnItemSelectedListener(this);
        progressDialog = new ProgressDialog(getActivity());
        String porcentaje = "0%";
        textViewNumeroPorcentaje.setText(porcentaje);
    }

    private void initView() {
        discreteSeekBarPorcentaje.setOnProgressChangeListener(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    /*SeekBar*/
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

    @Override
    public void mostrarProcentajeTrabajo(int value) {
        textViewNumeroPorcentaje.setText(value + "%");
    }

    @Override
    public void mostrarListaMotivoRevocacion(List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList) {
        Log.d(TAG, "mostrarListaMotivoRevocacion : " + tipoMotivoRevocacionUiList.size());
        SpinnerTipoMotivoRevocacionAdapter adapter = new SpinnerTipoMotivoRevocacionAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoMotivoRevocacionUiList);
        spinnerMotivoRevocacion.setAdapter(adapter);
    }

    @Override
    public void mostrarListaSolucionRevocacion(List<TipoSolucionRevocacionUi> tipoSolucionRevocacionUiList) {
        Log.d(TAG, "mostrarListaSolucionRevocacion : " + tipoSolucionRevocacionUiList.size());
        SpinnerTipoSolucionRevocacionAdapter adapter = new SpinnerTipoSolucionRevocacionAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoSolucionRevocacionUiList);
        spinnerSolucionRevocacion.setAdapter(adapter);
    }

    @Override
    public void mostrarMensaje(String ingrese_motivo_revocacion) {
        Snackbar.make(textViewNumeroPorcentaje, ingrese_motivo_revocacion, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarMensajeErrorEditTextObservacion(String ingrese_observación) {
        textInputObservacion.setError(ingrese_observación);
    }

    @Override
    public void mostrarDataInicial(ItemUi itemUi) {
        textViewNombrePropuesta.setText(itemUi.getNombrePropuesta());
        Glide.with(getActivity())
                .load(itemUi.getImagePropuesta())
                .into(imageViewRubro);
    }

    @Override
    public void ocultarDialogProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void mostrarDialogProgressBar() {
        progressDialog.setMessage("Enviando Revocación..");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void initStartActivityMenuPrincial() {
        Intent intent = new Intent(this, MenuEspecialistaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int itemId = parent.getId();
        switch (itemId) {
            case R.id.spinnerSolucionRevocacion:
                try {
                    TipoSolucionRevocacionUi tipoSolucionRevocacionUi = (TipoSolucionRevocacionUi) spinnerSolucionRevocacion.getSelectedItem();
                    if (tipoSolucionRevocacionUi == null) return;
                    String tipoRevocacionId = tipoSolucionRevocacionUi.getIdTipSolucionRevocacion().toString();
                    presenter.onSpinnerTipoSolucionRevocacion(tipoRevocacionId);
                } catch (Exception e) {
                    Log.d(TAG, "spinnerPaisPaise : " + e.getMessage());
                }
                break;
            case R.id.spinnerMotivoRevocacion:
                try {
                    TipoMotivoRevocacionUi tipoMotivoRevocacionUi = (TipoMotivoRevocacionUi) spinnerMotivoRevocacion.getSelectedItem();
                    if (tipoMotivoRevocacionUi == null) return;
                    presenter.onSpinnerTipoMotivoRevocacion(tipoMotivoRevocacionUi.getIdTipoMotivoRevocacion());
                } catch (Exception e) {
                    Log.d(TAG, "spinnerPaisPaise : " + e.getMessage());
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick({R.id.btnEnviar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnEnviar:
                String observacion = textInputObservacion.getText().toString();
                presenter.onClickEnviarRevocacion(observacion);
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
