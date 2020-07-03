package com.application.boxmadikv1.recuperarClave;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.recuperarClave.dataSource.RecuperarClaveRepository;
import com.application.boxmadikv1.recuperarClave.dataSource.remote.RecuperarClaveRemote;
import com.application.boxmadikv1.recuperarClave.useCase.RecuperarClave;
import com.application.boxmadikv1.sesion.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecuperarClaveActivity extends BaseActivity<RecuperarClaveView, RecuperarClavePresenter> implements RecuperarClaveView {
    public static final String TAG = RecuperarClaveActivity.class.getSimpleName();


    //progress dialog
    private ProgressDialog progressDialog;
    // Session Manager Class
    SessionManager session;

    @BindView(R.id.et_correo)
    EditText et_correo;
    //private ProgressBar progressDialog_;


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RecuperarClavePresenter getPresenter() {
        RecuperarClaveRepository recuperarClaveRepository = RecuperarClaveRepository.getmInstance(new RecuperarClaveRemote());
        return new RecuperarClavePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new RecuperarClave(recuperarClaveRepository));
    }

    @Override
    protected RecuperarClaveView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_recuperar_clave);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarMensaje(String mensaje, String estado) {
        switch (estado) {
            case "true":
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                break;
            case "false":
               // finish();
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void mostrarErrorCorreoCampoVacio(String mensajeError) {
        //Toast.makeText(this, mensajeError, Toast.LENGTH_SHORT).show();
        et_correo.setError(mensajeError);
    }

    @Override
    public void mostrarProgressDialog() {
        progressDialog.setMessage("Cargando....");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressDialog() {
       progressDialog.dismiss();
    }


    @OnClick({R.id.btn_enviar})
    public void onClick(View view) {
        int itemid = view.getId();

        switch (itemid) {
            case R.id.btn_enviar:
                presenter.onEnviarCorreo(et_correo.getText().toString());
                break;
        }
    }
}
