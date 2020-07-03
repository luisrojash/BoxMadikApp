package com.application.boxmadikv1.splash;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.extra.InternetCheck;
import com.application.boxmadikv1.helper.InputValidation;
import com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity;
import com.application.boxmadikv1.splash.dataSource.SplashRepository;
import com.application.boxmadikv1.splash.dataSource.local.SplashLocal;
import com.application.boxmadikv1.splash.dataSource.remote.SplashRemote;
import com.application.boxmadikv1.splash.useCase.ImportarDatosIniciales;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> implements SplashView, InternetCheck.Consumer {

    public static final String TAG = SplashActivity.class.getSimpleName();

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    InputValidation inputValidation;

    SplashActivity splashActivity;


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected SplashPresenter getPresenter() {
        // SplashRepository splashRepository = new SplashRepository(new SplashRemote(), new SplashLocal());

        SplashRepository splashRepository = SplashRepository.getmInstance(new SplashRemote(), new SplashLocal());
        return new SplashPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ImportarDatosIniciales(splashRepository));
    }

    @Override
    protected SplashView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //  SharedPreferences.Editor.clear();
        initVistas();
        splashActivity = this;
        // boolean status = Constantes.isOnlineNet();


       /* new Thread(new Runnable() {
            @Override
            public void run() {
                initProgressbar();
                Intent i = new Intent(SplashActivity.this, SeleccionUserActivity.class);
                startActivity(i);
                finish();
            }
        }).start();*/
    }

    private void initVistas() {
        inputValidation = new InputValidation(getActivity());

    }

    @Override
    protected void onStart() {
        super.onStart();
        new InternetCheck(splashActivity, null);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }


    @Override
    public void startSeleccionarUserActivity() {
        Intent intent = new Intent(SplashActivity.this, SeleccionUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void mostrarDialogMensaje(String mensaje) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SplashActivity.this);
        dialog.setTitle("Problemas Conexión")
                .setIcon(R.mipmap.ic_boxmadick)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        finish();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        //presenter.onClickRenuevaDatosGenerales(inputValidation.isNetworkAvailable());
                        new InternetCheck(splashActivity,null);
                    }
                }).show();
    }

    @Override
    public void accept(Boolean internet,Object o) {
        if (presenter != null) presenter.onCheckConexion(internet);
        Log.d(TAG, "CONEXION : " + internet + "");
    }
}
