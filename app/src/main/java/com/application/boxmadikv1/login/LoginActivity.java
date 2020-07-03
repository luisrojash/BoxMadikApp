package com.application.boxmadikv1.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.helper.InputValidation;
import com.application.boxmadikv1.login.dataSource.LoginRepository;
import com.application.boxmadikv1.login.dataSource.local.LoginLocal;
import com.application.boxmadikv1.login.dataSource.remote.LoginRemote;
import com.application.boxmadikv1.login.useCase.GuardarUsuario;
import com.application.boxmadikv1.login.useCase.Login;
import com.application.boxmadikv1.login.useCase.UsuariosOnline;
import com.application.boxmadikv1.recuperarClave.RecuperarClaveActivity;
import com.application.boxmadikv1.registraUser.RegistrarUserActivity;
import com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity;
import com.application.boxmadikv1.sesion.SessionManager;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    private final AppCompatActivity activity = LoginActivity.this;
    public static final String TAG = LoginActivity.class.getSimpleName();


    /*EditTeext*/
    @BindView(R.id.et_usuario)
    EditText editTextUsuario;
    @BindView(R.id.et_password)
    EditText editTextClave;
    InputValidation inputValidation;
    //progress dialog
    private ProgressDialog progressDialog;
    // Session Manager Class
    SessionManager session;

    private SecurePreferences preferences;


    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected LoginPresenter getPresenter() {
        //LoginRepository loginRepository = new LoginRepository(new LoginRemote());
        LoginRepository loginRepository = LoginRepository.getmInstance(new LoginRemote(), new LoginLocal(
                InjectorUtils.provideUsuarioDao()
        ));
        return new LoginPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new Login(loginRepository),
                new GuardarUsuario(loginRepository),
                new UsuariosOnline(loginRepository)
        );// new GuardarUsuariosOnline(loginRepository)
    }

    @Override
    protected LoginView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        inputValidation = new InputValidation(activity);
        progressDialog = new ProgressDialog(this);
        // Session Manager
        session = new SessionManager(getApplicationContext());
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
    }

    @OnClick({R.id.btnCrearCuenta, R.id.btnLogin, R.id.textViewOlvidarClave})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnCrearCuenta:
                startActivity(new Intent(this, RegistrarUserActivity.class));
                break;
            case R.id.btnLogin:
                String email = editTextUsuario.getText().toString();
                String clave = editTextClave.getText().toString();
                presenter.onClickLogin(email, clave);
                // validarConexion();
                break;
            case R.id.textViewOlvidarClave:
                startActivity(new Intent(this, RecuperarClaveActivity.class));
                break;
            default:

                // finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // validarConexion();
    }

    private void validarConexion() {
        presenter.validarConexion(inputValidation.isNetworkAvailable());
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(editTextClave, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarErrorUsuarioCampoVacio(String mensajeError) {
        editTextUsuario.setError(mensajeError);
    }

    @Override
    public void mostrarErrorClaveCampoVacio(String mensajeError) {
        mostrarMensaje(mensajeError);
        //  editTextClave.setError(mensajeError);
    }

    @Override
    public void startActivitySeleccionUser(String usuarioCodigo, String usuarioDni, String usuNombre,
                                           String usuaApellidos, String usuCelular, String usuEmail,
                                           String usuFoto, String codigoPais, String tipoDocumento) {
        //Log.d(TAG, "startActivitySeleccionUser : " + codeUsuario);
        //session.createLoginSession("", "", "");
        String tipoUsuarioDefecto = "0";
        preferences.createLoginSession();

        Log.d(TAG, "usuarioCodigo" + codigoPais);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CODIGO, usuarioCodigo);
        preferences.put(Constantes.KEY_SECURE_USUARIO_DNI, usuarioDni);
        preferences.put(Constantes.KEY_SECURE_USUARIO_NOMBRE, usuNombre);
        preferences.put(Constantes.KEY_SECURE_USUARIO_APELLIDOS, usuaApellidos);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CELULAR, usuCelular);
        preferences.put(Constantes.KEY_SECURE_USUARIO_EMAIL, usuEmail);
        preferences.put(Constantes.KEY_SECURE_USUARIO_FOTO, usuFoto);
        preferences.put(Constantes.KEY_SECURE_USUARIO_PAIS, codigoPais);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CODIGO_DOC, tipoDocumento);
        preferences.put(Constantes.KEY_SECURE_USUARIO_TIPO, tipoUsuarioDefecto);
        startActivity(new Intent(this, SeleccionUserActivity.class));
    }

    @Override
    public void mostrarDialog() {
        progressDialog.setMessage("Cargando Sesión Espere....");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void mostrarMensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isConectadoInternet() {
        editTextUsuario.setEnabled(true);
        editTextClave.setEnabled(true);
        String email = editTextUsuario.getText().toString();
        String clave = editTextClave.getText().toString();
        presenter.onClickLogin(email, clave);
    }

    @Override
    public void isDesconectadoInternet() {
        editTextUsuario.setEnabled(false);
        editTextClave.setEnabled(false);
        mostrarMensaje("Prenda sus datos y vuelva Intentar");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAffinity(LoginActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        presenter.setExtras(bundle);
    }
}
