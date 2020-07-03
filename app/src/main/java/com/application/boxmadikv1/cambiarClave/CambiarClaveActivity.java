package com.application.boxmadikv1.cambiarClave;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CambiarClaveActivity extends BaseActivity<CambiarClaveView, CambiarClavePresenter> implements CambiarClaveView {

    public static final String TAG = CambiarClaveActivity.class.getSimpleName();

    @BindView(R.id.editTextClaveActual)
    ShowHidePasswordEditText editTextClaveActual;
    @BindView(R.id.editTextClaveNueva)
    ShowHidePasswordEditText editTextClaveNueva;
    @BindView(R.id.editTextClaveRepetir)
    ShowHidePasswordEditText editTextClaveRepetir;
    @BindView(R.id.btnGuardar)
    Button buttonGuardar;
    private SecurePreferences preferences;
    //progress dialog
    private ProgressDialog progressDialog;
    private TinyDB tinydb;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CambiarClavePresenter getPresenter() {
        return new CambiarClavePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources());
    }

    @Override
    protected CambiarClaveView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.cambiar_clave_activity);
        ButterKnife.bind(this);
        preferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        progressDialog = new ProgressDialog(this);
        tinydb = new TinyDB(getApplicationContext());
        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onDataUser(keyUser, codigoPais);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @OnClick({R.id.btnGuardar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnGuardar:
                String claveActual = editTextClaveActual.getText().toString();
                String claveNueva = editTextClaveNueva.getText().toString();
                String claveRepetir = editTextClaveRepetir.getText().toString();
                presenter.OnClickCambiarClave(claveActual, claveNueva, claveRepetir);
                break;
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarProgressBarDialog() {
        progressDialog.setMessage("Verificando Clave...");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressBarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void finishActivity() {
        finish();
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
