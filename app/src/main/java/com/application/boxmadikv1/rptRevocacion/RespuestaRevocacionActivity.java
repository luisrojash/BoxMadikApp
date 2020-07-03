package com.application.boxmadikv1.rptRevocacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.rptRevocacion.adapter.PropuestaRevocacionAdapter;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionRepository;
import com.application.boxmadikv1.rptRevocacion.dataSource.local.RespuestaRevocacionLocal;
import com.application.boxmadikv1.rptRevocacion.dataSource.remote.RespuestaRevocacionRemote;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;
import com.application.boxmadikv1.rptRevocacion.useCase.GuardarRespuestRevocacion;
import com.application.boxmadikv1.rptRevocacion.useCase.ListaPropuestaRevocacion;
import com.application.boxmadikv1.rptRevocacion.useCase.ObtenerRespuestaRevoca;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RespuestaRevocacionActivity extends BaseActivity<RespuestaRevocacionView, RespuestaRevocacionPresenter>
        implements RespuestaRevocacionView, AdapterView.OnItemSelectedListener {

    public static final String TAG = RespuestaRevocacionActivity.class.getSimpleName();

    @BindView(R.id.spinnerPropuesta)
    Spinner spinnerPropuesta;
    @BindView(R.id.imaviewSi)
    ImageView imageViewCheckSi;
    @BindView(R.id.imaviewNo)
    ImageView imageViewCheckNo;
    @BindView(R.id.buttonRespRevo)
    Button buttonRespuestaEnviar;
    @BindView(R.id.textInputEditText2)
    TextInputEditText textInputEditTextDetalle;
    //progress dialog
    private ProgressDialog progressDialog;

    @BindView(R.id.constraintLayoutSi)
    ConstraintLayout constraintLayoutSi;
    @BindView(R.id.constraintLayoutNo)
    ConstraintLayout constraintLayoutNo;

    @BindView(R.id.textRespt)
    TextView textViewCombo;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RespuestaRevocacionPresenter getPresenter() {
        RespuestaRevocacionRepository respuestaRevocacionRepository = RespuestaRevocacionRepository.getmInstance(new RespuestaRevocacionLocal(
                InjectorUtils.provideTipoRevocaPropuesta()
        ), new RespuestaRevocacionRemote());
        return new RespuestaRevocacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListaPropuestaRevocacion(respuestaRevocacionRepository),
                new GuardarRespuestRevocacion(respuestaRevocacionRepository),
                new ObtenerRespuestaRevoca(respuestaRevocacionRepository));
    }

    @Override
    protected RespuestaRevocacionView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_resp_revocacion);
        ButterKnife.bind(this);
        initView();
        initToolbar();
    }

    private void initView() {
        spinnerPropuesta.setOnItemSelectedListener(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarListaPropuestaRevocacion(List<PropuestaRevocacionUi> documentoUiList) {
        PropuestaRevocacionAdapter adapter = new PropuestaRevocacionAdapter(this,
                R.layout.custom_spinner_tipo_documento, documentoUiList);
        spinnerPropuesta.setAdapter(adapter);
    }

    @Override
    public void mostrarCheckSi() {
        imageViewCheckSi.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarCheckSi() {
        imageViewCheckSi.setVisibility(View.GONE);
    }

    @Override
    public void mostrarCheckNo() {
        imageViewCheckNo.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarCheckNo() {
        imageViewCheckNo.setVisibility(View.GONE);
    }

    @Override
    public void mostrarMensajeErroDetalles(String mensaje) {
        textInputEditTextDetalle.setError(mensaje);
    }

    @Override
    public void habilitarButtonEnviar() {
        buttonRespuestaEnviar.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonEnviar() {
        buttonRespuestaEnviar.setEnabled(false);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ocultarProgressBarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void starMainActivityEspec() {
        Intent intent = new Intent(this, MenuEspecialistaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void mostrarProgressBarDialog(String mensaje) {
        progressDialog.setMessage(mensaje);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void mostrarDataObtenida(String descripcion_resp, String observa_resp) {
        textInputEditTextDetalle.setText(observa_resp);
        textViewCombo.setVisibility(View.VISIBLE);
        textViewCombo.setText(descripcion_resp);
    }

    @Override
    public void enviarMenuPrinicipal() {

    }

    @Override
    public void deshabilitarWidgetCompleto() {
        constraintLayoutSi.setEnabled(false);
        constraintLayoutNo.setEnabled(false);
        buttonRespuestaEnviar.setEnabled(false);
        buttonRespuestaEnviar.setBackgroundResource(R.drawable.btn_bg_disable);
        textInputEditTextDetalle.setTextColor(getResources().getColor(R.color.md_black_1000));
        textInputEditTextDetalle.setEnabled(false);
    }



    @OnClick({R.id.constraintLayoutSi, R.id.constraintLayoutNo, R.id.buttonRespRevo})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.constraintLayoutSi:
                presenter.onClickButtonSi();
                break;
            case R.id.constraintLayoutNo:
                presenter.onClickButtonNo();
                break;
            case R.id.buttonRespRevo:
                String detalleRespuesta = textInputEditTextDetalle.getText().toString();
                String otros = "otros";
                //presenter.onClickEnviarRespuesta(Constantes.isResultadoCharacterSpecial(Constantes.removeAccents(detalleRespuesta), otros).trim());
                presenter.onClickEnviarRespuesta(detalleRespuesta);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int itemId = parent.getId();
        switch (itemId) {
            case R.id.spinnerPropuesta:
                try {
                    PropuestaRevocacionUi propuestaRevocacionUi = (PropuestaRevocacionUi) spinnerPropuesta.getSelectedItem();
                    if (propuestaRevocacionUi == null) return;
                    presenter.onSpinnerTipoPropuestaRevocacion(propuestaRevocacionUi.getPropuestRevocacionId());
                    Log.d(TAG, "spinnerTipoDoc : " + propuestaRevocacionUi.getPropuestaRevocacionDes());
                } catch (Exception e) {
                    Log.d(TAG, "spinnerTipoDoce : " + e.getMessage());
                }

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
