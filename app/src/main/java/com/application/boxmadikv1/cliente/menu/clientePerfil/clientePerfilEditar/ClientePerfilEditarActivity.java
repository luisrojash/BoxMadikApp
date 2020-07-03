package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.local.ClienteDatosPerfilLocal;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.remote.ClienteDatosPerfilRemote;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.useCase.ObtenerPais;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.useCase.ObtenerTipoDoc;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public abstract class ClientePerfilEditarActivity extends BaseActivity<ClientePerfilEditarView, ClientePerfilEditarPresenter> implements ClientePerfilEditarView {
    public static final String TAG = ClientePerfilEditarActivity.class.getSimpleName();
    public static final int RESULTADO_ACTIVIDAD_ACTIVIDAD_ATENCION = 1;

    public static final String EXTRA_CLIENTE_PERFIL_EDITAR_NOMBRE = "nombreEdit";
    public static final String EXTRA_CLIENTE_PERFIL_EDITAR_APELLIDOS = "apellidosEdit";
    public static final String EXTRA_CLIENTE_PERFIL_EDITAR_CELULAR = "celularEdit";
    public static final String EXTRA_CLIENTE_PERFIL_EDITAR_FOTO = "usuarioFoto";

    @BindView(R.id.btnSiguiente)
    Button btnSiguiente;

    @BindView(R.id.txtTipoDocumento)
    EditText txtTipoDocumento;
    @BindView(R.id.editTextNumeroDocumento)
    EditText editTextNumeroDocumento;
    @BindView(R.id.editTextNombres)
    EditText editTextNombres;
    @BindView(R.id.editTextApellidos)
    EditText editTextApellidos;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextCelular)
    EditText editTextCelular;
    @BindView(R.id.textPais)
    EditText txtPais;
    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }


    // Session Manager Class
    //SessionManager session;
    SecurePreferences preferences;
    ProgressDialog progressDialog;

    private void initSession() {
        //session = new SessionManager(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void obteniendoKeyUser() {
        String usuarioCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String usuarioDni = preferences.getString(Constantes.KEY_SECURE_USUARIO_DNI);
        String usuarioNombre = preferences.getString(Constantes.KEY_SECURE_USUARIO_NOMBRE);
        String usuarioApellidos = preferences.getString(Constantes.KEY_SECURE_USUARIO_APELLIDOS);
        String usuarioCelular = preferences.getString(Constantes.KEY_SECURE_USUARIO_CELULAR);
        String usuarioEmail = preferences.getString(Constantes.KEY_SECURE_USUARIO_EMAIL);
        String usuarioFoto = preferences.getString(Constantes.KEY_SECURE_USUARIO_FOTO);
        String usuarioPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        String usuarioTipoDoc = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO_DOC);
        Log.d(TAG, "actualizarDataPreferencesConFoto : " + usuarioFoto);
        presenter.onInitKeyUser(usuarioCodigo, usuarioDni, usuarioNombre, usuarioApellidos,
                usuarioCelular, usuarioEmail, usuarioFoto, usuarioPais, usuarioTipoDoc);
    }

    @Override
    public void mostrarDatosInit(String usuarioDni, String usuarioNombre, String usuarioApellidos, String usuarioCelular,
                                 String usuarioEmail, String usuarioFoto) {
        //txtTipoDocumento.setText(tipodoc);
        editTextNumeroDocumento.setText(usuarioDni);
        editTextNombres.setText(usuarioNombre);
        editTextApellidos.setText(usuarioApellidos);
        editTextEmail.setText(usuarioEmail);
        editTextCelular.setText(usuarioCelular);
        //txtPais.setText(pais);
        Glide.with(this).load(usuarioFoto).into(imgProfile);
    }

    @Override
    public void mostrarPaisNombre(String nombrePais) {
        txtPais.setText(nombrePais);

    }

    @Override
    public void mostrarTipoDocNombre(String tipoDocNombre) {
        txtTipoDocumento.setText(tipoDocNombre);
    }

    @Override
    public void mostrarImageUsuario(Uri uriComprimida) {
        Glide.with(this).load(uriComprimida).into(imgProfile);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(imgProfile, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarProgressBarDialog() {
        progressDialog.setMessage("Actualizando Perfil....");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressBarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void actualizarDataPreferencesConFoto(String nombreEdit, String apellidoEdit, String celularEdit, String usuFoto) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_NOMBRE, nombreEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_APELLIDOS, apellidoEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CELULAR, celularEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_FOTO, usuFoto);
        Log.d(TAG, "actualizarDataPreferencesConFoto : " + usuFoto);
    }
    @Override
    public void actualizarDataPreferencesSinFoto(String nombreEdit, String apellidoEdit, String celularEdit) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_NOMBRE, nombreEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_APELLIDOS, apellidoEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CELULAR, celularEdit);
    }

    @Override
    public void startActivityPerfil() {
        Toast.makeText(getApplicationContext(), "Datos Guardados Correctamente", Toast.LENGTH_SHORT);
        finish();
    }


    @Override
    protected ClientePerfilEditarPresenter getPresenter() {

        DatosPerfilRepository datosPerfilRepository = DatosPerfilRepository.getmInstance(new ClienteDatosPerfilLocal(
                InjectorUtils.provideTipoDocumentoDao(),
                InjectorUtils.providePaisDao()
        ), new ClienteDatosPerfilRemote());
        return new ClientePerfilEditarPresenterImpl(
                new UseCaseHandler(
                        new UseCaseThreadPoolScheduler()),
                getResources(),
                this,
                new ObtenerPais(datosPerfilRepository),
                new ObtenerTipoDoc(datosPerfilRepository));
    }

    @Override
    protected ClientePerfilEditarView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_cliente_editar_perfil);
        ButterKnife.bind(this);
        initSession();

    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @OnClick({R.id.btnSiguiente, R.id.fab, R.id.relative})
    public void OnClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnSiguiente:
                // Snackbar.make(btnSiguiente, "Editar Perfil", Snackbar.LENGTH_SHORT).show();
                //Log.d(TAG, "btnSiguiente");
                String nombreEdit = editTextNombres.getText().toString();
                String apellidosEdit = editTextApellidos.getText().toString();
                String celularEdit = editTextCelular.getText().toString();
                presenter.OnClickPerfilDireccion(nombreEdit, apellidosEdit, celularEdit);
                break;
            case R.id.fab:
                // checkPickImagePermissions();
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
                break;
            case R.id.relative:
                // checkPickImagePermissions();
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "Activity:onActivityResult");
        presenter.onActivityResult(requestCode, resultCode, data);
    }


   /* @Override
    public void starActivityPerfilDireccion(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto) {
        Intent intent = new Intent(this, ClientePerfilDireccionActivity.class);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_NOMBRE, nombreEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_APELLIDOS, apellidosEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_CELULAR, celularEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_FOTO, usuarioFoto);
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
    public void habilitarButtonGuardar() {
        btnSiguiente.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonGuardar() {
        btnSiguiente.setEnabled(false);
    }
}
