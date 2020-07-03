package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.presentacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad.DireccionUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.EstudioPerfilActivity;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PresentacionPerfilActivity extends BaseActivity<PresentacionPerfilView, PresentacionPerfilPresenter> implements PresentacionPerfilView {


    public static final String EXTRA_PRESENTACION_PERFIL_PRESENTACION = "presentacion";
    public static final String TAG = PresentacionPerfilActivity.class.getSimpleName();
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextPresentacion;
    private SecurePreferences preferences;
    @BindView(R.id.textViewValidacion)
    TextView textViewValidacion;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewCheck;
    @BindView(R.id.btnSiguiente)
    Button buttonSiguiente;

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
    protected PresentacionPerfilPresenter getPresenter() {
        return new PresentacionPerfilPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources());
    }

    @Override
    protected PresentacionPerfilView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_especialista_perfil_presentacion);
        ButterKnife.bind(this);
        initPreferences();

    }

    private void initPreferences() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textInputEditTextPresentacion.setEnabled(true);
        preferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        progressDialog = new ProgressDialog(this);
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
                String presentacion = textInputEditTextPresentacion.getText().toString();
                String otros = "otros";
                //presenter.onClickSiguiente(Constantes.isResultadoCharacterSpecial(Constantes.removeAccents(presentacion), otros).trim());
                presenter.onClickSiguiente(presentacion);
                break;
        }
    }

    @Override
    public void mostrarMensajeErrorTextPresentacion(String seleccione_una_direccion_correcta) {
        textInputEditTextPresentacion.setError(seleccione_una_direccion_correcta);
    }

    @Override
    public void initStartActivityCursos(DireccionUi direccionUi, String presentacion) {
        Intent intent = new Intent(getApplicationContext(), EstudioPerfilActivity.class);
        intent.putExtra(EspecialistaPerfilDistritoActivity.EXTRA_PERFIL_ACTIVITY_PERFIL_DIRECCION, direccionUi);
        intent.putExtra(EXTRA_PRESENTACION_PERFIL_PRESENTACION, presentacion);
        startActivity(intent);
    }

    @Override
    public void mostrarMensaje(String mensajeError) {
        Toast.makeText(getApplicationContext(), mensajeError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarImagenTextSinValidar() {
        textViewValidacion.setText(getString(R.string.informacion_antecendentes_sin_validar));
        imageViewCheck.setBackgroundResource(R.drawable.check_antecedentes_sin_validar);
    }

    @Override
    public void mostrarImagenTextValidado() {
        textViewValidacion.setText(getString(R.string.informacion_antecendentes_validado));
        imageViewCheck.setBackgroundResource(R.drawable.check_antecedentes_validado);
    }

    @Override
    public void obtenerKeyUser() {
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onKeyUser(keyUser, codigoPais);
    }

    @Override
    public void mostrarPresentacion(String usuPresentacion) {
        textInputEditTextPresentacion.setText(usuPresentacion);
    }

    @Override
    public void deshabilitaTextPresentacion() {
        buttonSiguiente.setEnabled(false);
    }

    @Override
    public void habilitaTextPresentacion() {
        buttonSiguiente.setEnabled(true);
    }

    @Override
    public void initStartActivityEspecEditPerfil(String editPerfilCorrectamentePresentacion) {
        Toast.makeText(getApplicationContext(), "Datos Actualizados Correctamente", Toast.LENGTH_SHORT).show();
        finish();
        Log.d(TAG, "initStartActivityEspecEditPerfil : " + editPerfilCorrectamentePresentacion);
    }

    @Override
    public void mostrarDialogProgressBar() {
        progressDialog.setMessage("Actualizando Presentación....");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarDialogProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void habilitarButtonGuardar() {

    }

    @Override
    public void deshabilitarButtonGuardar() {

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
