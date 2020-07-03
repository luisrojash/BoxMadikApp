package com.application.boxmadikv1.seleccionUsuario;

import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.SeleccionRubrosActivity;
import com.application.boxmadikv1.notificaciones.cliente.ClienteNotiActivity;
import com.application.boxmadikv1.notificaciones.especialista.EspecialistaNotiActivity;
import com.application.boxmadikv1.seleccionUsuario.UseCase.ActualizacionEstadoOnline;
import com.application.boxmadikv1.seleccionUsuario.dataSource.SeleccionUserRepository;
import com.application.boxmadikv1.seleccionUsuario.dataSource.remote.SeleccionUserRemote;
import com.application.boxmadikv1.sesion.SessionManager;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Config;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeleccionUserActivity extends BaseActivity<SeleccionUserView, SeleccionUserPresenter> implements SeleccionUserView {

    public static final String TAG = SeleccionUserActivity.class.getSimpleName();
    public static final String EXTRA_SELECCION_USER_CODIGO_USU = "codigoUsuario";
    public static final String EXTRA_SELECCION_USER_CODIGO_PAIS = "paisCodigo";
    public static final String EXTRA_SELECCION_USER_TIPO_NOTIFICACION = "tipoNotificacion";


    @BindView(R.id.fabPrimero)
    FloatingActionButton fabPrimeroCliente;
    @BindView(R.id.fabSegundo)
    FloatingActionButton fabSegundoEspecialista;
    @BindView(R.id.textviewIngreso)
    TextView textViewIngreso;
    @BindView(R.id.animacionLotieEspecialista)
    LottieAnimationView animationViewEspecialista;
    @BindView(R.id.animacionLotieCliente)
    LottieAnimationView animationViewCliente;
    @BindView(R.id.txt_counterCliente)
    AppCompatTextView appCompatTextViewCountCliente;
    @BindView(R.id.txt_counterEspecialista)
    AppCompatTextView appCompatTextViewCountEspecialista;


    TinyDB tinydb;


    // Session Manager Class
    SessionManager session;
    SecurePreferences preferences;

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected SeleccionUserPresenter getPresenter() {
        SeleccionUserRepository seleccionUserRepository = SeleccionUserRepository.getmInstance(new SeleccionUserRemote());
        return new SeleccionUserPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ActualizacionEstadoOnline(seleccionUserRepository));
    }

    @Override
    protected SeleccionUserView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_seleccion_user);
        ButterKnife.bind(this);
        checkLogin();
        initBroadCastReceiver();
        initStartLottiie();

    }

    private void initStartLottiie() {
        animationViewEspecialista.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd");
                appCompatTextViewCountEspecialista.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "onAnimationRepeat");
            }
        });
        animationViewCliente.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                appCompatTextViewCountCliente.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initBroadCastReceiver() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    // displayFirebaseRegId();
                    Log.d(TAG, "TOPIC_GLOBAL");

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    // new push notification is received

                    String message = intent.getStringExtra("Usted tiene una notificación");

                    //  Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "message");
                    //txtMessage.setText(message);
                }
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tinydb = new TinyDB(getApplicationContext());
    }


    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        // NotificationUtils.clearNotifications(getApplicationContext());


    }


    @Override
    protected void onStart() {
        super.onStart();
        String nombre = preferences.getString(Constantes.KEY_SECURE_USUARIO_NOMBRE);
        String codigoUsuario = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String paisCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        String tokenUsuario = preferences.getString(Constantes.KEY_SECURE_USUARIO_TOKEN);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SeleccionUserActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                presenter.onRegistroToken(newToken);
                Log.e("newToken", newToken);

            }
        });
        //  initBroadCastReceiver();
        presenter.onDatosPreferencias(codigoUsuario, paisCodigo, tokenUsuario);
        if (nombre == null) return;
        textViewIngreso.setText("Bienvenido " + nombre + ", Porfavor elija la opcion que desea continuar ");

    }

    private void checkLogin() {
        preferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        preferences.checkLogin();
    }


    @OnClick({R.id.imgProfile, R.id.imgProfile2,
            R.id.animacionLotieCliente, R.id.animacionLotieEspecialista,
            R.id.imageViewInterrogationCliente, R.id.imageViewInterrogationEspec})
    public void onClick(View view) {
        int itemId = view.getId();
        String tipoUsuario = preferences.getString(Constantes.KEY_SECURE_USUARIO_TIPO);
        String departamentoId = preferences.getString(Constantes.KEY_SECURE_USUARIO_ESPE_CODIGO_DEPARTAMENT);
        Log.d(TAG, "TipoUsuario : " + departamentoId);
        switch (itemId) {
            case R.id.imgProfile:
                mostrarCheckCliente();
                presenter.onClickCliente(tipoUsuario);
                break;
            case R.id.imgProfile2:
                Log.d(TAG, "relativeSegundo");
                presenter.onValidateSeleccionRubros(tinydb, tipoUsuario, departamentoId);
                break;
            case R.id.animacionLotieCliente:
                presenter.onClickNotificacionCliente();
                Log.d(TAG, "animacionLotieCliente");
                break;
            case R.id.animacionLotieEspecialista:
                presenter.onClickNotificacionEspecialista();
                Log.d(TAG, "animacionLotieEspecialista");
                break;
            case R.id.imageViewInterrogationCliente:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/GunNfdyb17w"));
                startActivity(i);
                break;
            case R.id.imageViewInterrogationEspec:
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/U-yJ7FoQOrE"));
                startActivity(it);
                break;
        }
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarCheckCliente() {
        fabPrimeroCliente.setVisibility(View.VISIBLE);
        fabSegundoEspecialista.setVisibility(View.GONE);
    }

    @Override
    public void mostrarCheckEspecialista() {
        fabPrimeroCliente.setVisibility(View.GONE);
        fabSegundoEspecialista.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(fabPrimeroCliente, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void startMenuCliente(String tipoUsuario) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_TIPO, tipoUsuario);
        startActivity(new Intent(this, MenuClienteActivity.class));
    }

    @Override
    public void startMenuEspecialista(ArrayList<String> arrayList) {
        startActivity(new Intent(this, MenuEspecialistaActivity.class));
    }

    @Override
    public void startSeleccionRubros(String tipoUsuario) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_TIPO, tipoUsuario);
        startActivity(new Intent(this, SeleccionRubrosActivity.class));
    }


    @Override
    public void limpiarPreferencias() {
        preferences.logoutUser();
        preferences.clear();
    }

    @Override
    public void startActivityMenuCliente() {
        startActivity(new Intent(this, MenuClienteActivity.class));
    }

    @Override
    public void startActivityDistritoTrabajo(String tipoInicial) {
        Intent intent = new Intent(this, EspecialistaPerfilDistritoActivity.class);
        intent.putExtra("tipoInicial", tipoInicial);
        startActivity(intent);
    }

    @Override
    public void actualizarToken(String newToken) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_TOKEN, newToken);
    }

    @Override
    public void initStartActivityNotificacionCliente(String codigoUsuario, String paisCodigo, String tipoNotificacion) {
        Intent intent = new Intent(this, ClienteNotiActivity.class);
        intent.putExtra(EXTRA_SELECCION_USER_CODIGO_USU, codigoUsuario);
        intent.putExtra(EXTRA_SELECCION_USER_CODIGO_PAIS, paisCodigo);
        intent.putExtra(EXTRA_SELECCION_USER_TIPO_NOTIFICACION, tipoNotificacion);
        startActivity(intent);
    }

    @Override
    public void initStartActivityNotificacionEspecialista(String codigoUsuario, String paisCodigo, String tipoNotificacion) {
        Intent intent = new Intent(this, EspecialistaNotiActivity.class);
        intent.putExtra(EXTRA_SELECCION_USER_CODIGO_USU, codigoUsuario);
        intent.putExtra(EXTRA_SELECCION_USER_CODIGO_PAIS, paisCodigo);
        intent.putExtra(EXTRA_SELECCION_USER_TIPO_NOTIFICACION, tipoNotificacion);
        startActivity(intent);
    }

    @Override
    public void mostrarConteoEspecialista(String conteoEspecialista) {
        appCompatTextViewCountEspecialista.setText(conteoEspecialista);
    }

    @Override
    public void mostrarConteoCliente(String conteoCliente) {
        appCompatTextViewCountCliente.setText(conteoCliente);
    }

    @Override
    public void onGuardarDataEnPreferencesListaRubros(ArrayList<String> stringListIdRubros) {
        tinydb.putListString("mylist", stringListIdRubros);
    }

    @Override
    public void onGuardarDataEnPreferencesDepartamentoId(String codigoDepartamento) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_ESPE_CODIGO_DEPARTAMENT, codigoDepartamento);
    }

    @Override
    public void habilitarButtonEspec() {
        // buttonEspec.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonEspec() {
        //buttonEspec.setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccion_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cerrar_sesion) {
            presenter.onLogoutUser();
            tinydb.clear();
            preferences.logoutUser();
        } else {
            ActivityCompat.finishAffinity(SeleccionUserActivity.this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAffinity(SeleccionUserActivity.this);
    }
}
