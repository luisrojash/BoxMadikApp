package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosBancaria;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.adapter.SpinnerAdapterTipoBanco;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.adapter.SpinnerAdapterTipoCuenta;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.EspecBancoRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.local.EspecBancoLocal;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.remote.EspecBancoRemote;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.entidad.TipoBancoUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.useCase.MostrarListaBanco;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EspecBancoActivity extends BaseActivity<EspecBancoView, EspecBancoPresenter> implements EspecBancoView,
        AdapterView.OnItemSelectedListener {

    public static final String TAG = EspecBancoActivity.class.getSimpleName();
    public static final String EXTRA_DATA_BANCARAIA_ACTIVITY = "EspecBancoActivity";

    /*@BindView(R.id.autoCompleteTextViewBanco)
     AutoCompleteTextView autoCompleteTextViewBanco;*/
    @BindView(R.id.autoCompleteTextViewBanco)
    Spinner autoCompleteTextViewBanco;
    @BindView(R.id.spinerTipoCuentas)
    Spinner spinnerTipoCuentas;
    @BindView(R.id.editeTeNumeroCuenta)
    EditText editTextNumeroCuenta;//editeTeNumeroCuenta2
    @BindView(R.id.editeTeNumeroCuenta2)
    EditText editeTeNumeroCuenta2;
    @BindView(R.id.btnGuardar)
    Button buttonGuardar;
    private SecurePreferences preferences;
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
    protected EspecBancoPresenter getPresenter() {
        EspecBancoRepository especBancoRepository = EspecBancoRepository.getmInstance(new EspecBancoLocal(
                        InjectorUtils.provideBancoDao()
                ),
                new EspecBancoRemote());
        return new EspecBancoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new MostrarListaBanco(especBancoRepository));
    }

    @Override
    protected EspecBancoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_espec_editar_perfil_cuenta_bancaria);
        ButterKnife.bind(this);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    List<String> spinnerArray;

    private void initView() {
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        progressDialog = new ProgressDialog(this);
        spinnerTipoCuentas.setOnItemSelectedListener(this);
        autoCompleteTextViewBanco.setOnItemSelectedListener(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onKeyUser(keyUser, codigoPais);
    }

    @Override
    public void mostrarListaTipoBancio(List<TipoBancoUi> tipoBancoUiList) {
        SpinnerAdapterTipoBanco adapter = new SpinnerAdapterTipoBanco(this,
                R.layout.custom_spinner_tipo_documento, tipoBancoUiList);
        autoCompleteTextViewBanco.setAdapter(adapter);
       /* BuscarBancoAdapter buscarDepartamentoAdapter = new BuscarBancoAdapter(this, tipoBancoUiList);
        autoCompleteTextViewBanco.setAdapter(buscarDepartamentoAdapter);
        autoCompleteTextViewBanco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoBancoUi tipoBancoUi = (TipoBancoUi) parent.getAdapter().getItem(position);
                presenter.onClickTipoBanco(tipoBancoUi);
            }
        });*/
    }

    @Override
    public void mostrarTextViewBanco(String descripcion) {
        //   autoCompleteTextViewBanco.setText(descripcion);
    }

    @Override
    public void mostrarListaTipoCuenta(List<String> stringListTipoCuenta) {
        SpinnerAdapterTipoCuenta adapter = new SpinnerAdapterTipoCuenta(this,
                R.layout.custom_spinner_tipo_documento, stringListTipoCuenta);
        spinnerTipoCuentas.setAdapter(adapter);
    }

    @Override
    public void mostrarMensajeErrorBancoText(String mensaje) {
        //  autoCompleteTextViewBanco.setError(mensaje);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarMensajeErrorNumeroText(String mensaje) {
        editTextNumeroCuenta.setError(mensaje);
    }

    @Override
    public void mostrarMensajeErrorNumeroInterbakText(String mensaje) {
        editeTeNumeroCuenta2.setError(mensaje);
    }

    @Override
    public void initStartEditActivityPerfil(String extraCorrectamenteEspecBanco) {
        Log.d(TAG, "initStartEditActivityPerfil : ");
        Toast.makeText(this, "Datos Guardados Correctamente ", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void mostrarDataInicial(DatosBancaria datosBancaria) {
        InputFilter[] FilterArrayCuentas = new InputFilter[1];
        FilterArrayCuentas[0] = new InputFilter.LengthFilter(25);
        editeTeNumeroCuenta2.setFilters(FilterArrayCuentas);

        InputFilter[] FilterArrayCuentas2 = new InputFilter[1];
        FilterArrayCuentas2[0] = new InputFilter.LengthFilter(25);
        editTextNumeroCuenta.setFilters(FilterArrayCuentas2);


        TipoBancoUi tipoBancoUi = new TipoBancoUi();
        tipoBancoUi.setId(datosBancaria.getBancoCodigo());
        tipoBancoUi.setDescripcion(datosBancaria.getBancoNombre());
        //autoCompleteTextViewBanco.
        // autoCompleteTextViewBanco.setAutofillHints(datosBancaria.getBancoNombre());
        editTextNumeroCuenta.setText(datosBancaria.getNumerCuenta());
        editeTeNumeroCuenta2.setText(datosBancaria.getNumerCuentaInterbank());
        validarTipoCuenta(datosBancaria.getTipoCuentaCodigo());

    }

    @Override
    public void deshabilitarTextBank() {
        autoCompleteTextViewBanco.setEnabled(false);
    }

    @Override
    public void limpiarTextBank() {
        //  autoCompleteTextViewBanco.setText(null);
        autoCompleteTextViewBanco.setEnabled(true);
    }

    @Override
    public void mostrarDialogProgress() {
        progressDialog.setMessage("Actualizando Datos Bancarios..");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarDialogProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void habilitarButtonGuardar() {
        buttonGuardar.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonGuardar() {
        buttonGuardar.setEnabled(false);
    }

    @Override
    public void mostrarSpinnerLleno(int posicion) {
        autoCompleteTextViewBanco.setSelection(posicion);
    }

    private void validarTipoCuenta(String tipoCuentaCodigo) {
        switch (tipoCuentaCodigo) {
            case "0":
                break;
            case "1":
                spinnerTipoCuentas.setSelection(1);
                break;
            case "2":
                spinnerTipoCuentas.setSelection(2);
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int itemId = parent.getId();
        switch (itemId) {
            case R.id.spinerTipoCuentas:
                try {
                    String tipoRangoPrecioUi = (String) spinnerTipoCuentas.getSelectedItem();
                    presenter.onSpinnerTipoCuenta(tipoRangoPrecioUi);
                    Log.d(TAG, "tipoRangoPrecioUi : " + tipoRangoPrecioUi);
                } catch (Exception e) {
                    Log.d(TAG, "spinnerTipoDoce : " + e.getMessage());
                }

                break;
            case R.id.autoCompleteTextViewBanco:
                try {
                    TipoBancoUi tipoBancoUi = (TipoBancoUi) autoCompleteTextViewBanco.getSelectedItem();
                    presenter.onClickTipoBanco(tipoBancoUi);
                    Log.d(TAG, "tipoRangoPrecioUi : " + tipoBancoUi);
                } catch (Exception e) {
                    Log.d(TAG, "tipoBancoUi : " + e.getMessage());
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick({R.id.btnGuardar, R.id.imageViewCloseBank})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnGuardar:
                String numeroCuenta = editTextNumeroCuenta.getText().toString();
                String numeroCuentainter = editeTeNumeroCuenta2.getText().toString();
                presenter.onClickGuardar(numeroCuenta, numeroCuentainter);
                break;
            case R.id.imageViewCloseBank:
                presenter.onCloseBank();
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
