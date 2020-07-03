package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.CulquiRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.remote.CulquiRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.useCase.CrearPago;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.useCase.RegistroToken;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.ReportePagoActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.validation.Validation;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CulquiActivity extends BaseActivity<CulquiView, CulquiPresenter> implements CulquiView {

    public static final String TAG = CulquiActivity.class.getSimpleName();

    @BindView(R.id.txt_cardnumber)
    EditText textViewCardNumber;

    @BindView(R.id.txt_cvv)
    EditText textViewCvv;

    @BindView(R.id.txt_email)
    EditText editTextEmail;

    @BindView(R.id.txt_year)
    EditText editTextAnio;
    @BindView(R.id.txt_month)
    EditText editTextMes;

    @BindView(R.id.kind_card)
    TextView kind_card;

    /*@BindView(R.id.carViewPago)
    CardView cardViewPagoAceptado;

    @BindView(R.id.textViewPago)
    TextView textViewMontoPago;*/

    @BindView(R.id.btnSiguiente)
    Button buttonSiguiente;

    @BindView(R.id.textViewPagoCotizacion)
    TextView textViewMontoPagar;


    Validation validation;

    SecurePreferences securePreferences;
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
    protected CulquiPresenter getPresenter() {
        CulquiRepository culquiRepository = CulquiRepository.getmInstance(new CulquiRemote());
        return new CulquiPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new RegistroToken(culquiRepository),
                new CrearPago(culquiRepository));
    }

    @Override
    protected CulquiView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_culqui);
        ButterKnife.bind(this);
        initToolbar();
        validation = new Validation();
        securePreferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        progressDialog = new ProgressDialog(this);
        initView();
    }

    private void initView() {
        textViewCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    textViewCvv.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = textViewCardNumber.getText().toString();
                if (s.length() == 0) {
                    textViewCardNumber.setBackgroundResource(R.drawable.border_error);
                }

                if (validation.luhn(text)) {
                    textViewCardNumber.setBackgroundResource(R.drawable.border_sucess);
                } else {
                    textViewCardNumber.setBackgroundResource(R.drawable.border_error);
                }

                int cvv = validation.bin(text, kind_card);
                if (cvv > 0) {
                    textViewCvv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(cvv)});
                    textViewCvv.setEnabled(true);
                } else {
                    //  textViewCvv.setEnabled(false);
                    textViewCvv.setText("");
                }
            }
        });
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @OnClick({R.id.btnSiguiente})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnSiguiente:
                String numberCard = textViewCardNumber.getText().toString();
                String textoCvv = textViewCvv.getText().toString();
                String textAnio = editTextAnio.getText().toString();
                String textMes = editTextMes.getText().toString();
                String email = editTextEmail.getText().toString();
                presenter.onClickSiguiente(numberCard, textoCvv, email, textAnio, textMes);
                break;
           /* case R.id.close:
                ocultarCardViewPagoAceptado();
                break;*/
            /*case R.id.depositar:
                presenter.onClickDepositar();
                break;*/
        }
    }

    @Override
    public void initStartActivityReportePago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(CulquiActivity.this, ReportePagoActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void mostrarMensaje(String message) {
        Toast.makeText(CulquiActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String idToke = securePreferences.getString(Constantes.KEY_SECURE_CULQUI_TOKEN);
        presenter.onPreferencesToken(idToke);
    }

    @Override
    public void onPutSecurePreferences(String idToken) {
        securePreferences.put(Constantes.KEY_SECURE_CULQUI_TOKEN, idToken);
    }

    @Override
    public void mostrarCardViewPagoAceptado() {
        /// cardViewPagoAceptado.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarCardViewPagoAceptado() {
        //  cardViewPagoAceptado.setVisibility(View.GONE);
    }

    @Override
    public void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {

      /*  textViewCardNumber.setText("4111111111111111");
        textViewCvv.setText("123");
        textViewCvv.setEnabled(false);
        editTextAnio.setText("2020");
        editTextMes.setText("09");
        editTextEmail.setText("boxmadik@gmail.com");*/
        Log.d(TAG, "cotizacionesUi.getMonto() " + cotizacionesUi.getMonto());
        textViewMontoPagar.setText("PEN: S./ " + cotizacionesUi.getMonto() + " \n Monto a Pagar ");
    }

    @Override
    public void mostrarDialogProgress() {
        progressDialog.setMessage("Verificando el Registro Espere..");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarDialogProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void deshabilitarCulquiPagoBasico() {
        textViewCardNumber.setEnabled(false);
        textViewCvv.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextAnio.setEnabled(false);
        editTextMes.setEnabled(false);
        buttonSiguiente.setEnabled(false);
    }

    @Override
    public void habilitarCulquiPagoBasico() {
        textViewCardNumber.setEnabled(true);
        textViewCvv.setEnabled(true);
        editTextEmail.setEnabled(true);
        editTextAnio.setEnabled(true);
        editTextMes.setEnabled(true);
        buttonSiguiente.setEnabled(true);
    }

    @Override
    public void mostraEditTextTarjetaError(String mensaje) {
        textViewCardNumber.setError(mensaje);
    }

    @Override
    public void mostraEditTextAnioError(String mensaje) {
        editTextAnio.setError(mensaje);
    }

    @Override
    public void mostraEditTextMesError(String mensaje) {
        editTextMes.setError(mensaje);
    }

    @Override
    public void mostraEditTextCvvError(String mensaje) {
        textViewCvv.setError(mensaje);
    }

    @Override
    public void mostraEditTextEmailError(String mensaje) {
        editTextEmail.setError(mensaje);
    }

    @Override
    public void deshabilitartBotonPago() {
        buttonSiguiente.setEnabled(false);
    }

    @Override
    public void habilitarBotonPago() {
        buttonSiguiente.setEnabled(true);
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed");
        finish();
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
            Log.d(TAG, "onBackPressed");
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
