package com.application.boxmadikv1.cliente.menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.bandeja.BandejaActivity;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.clienteCancelado.ClienteCanceladoFragment;
import com.application.boxmadikv1.cliente.menu.clienteFinalizado.ClienteFinalizadoFragment;
import com.application.boxmadikv1.cliente.menu.clientePendiente.ClientePendienteFragment;
import com.application.boxmadikv1.cliente.menu.clientePerfil.ClientePerfilActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.ClientPerfilDirectionActivity;
import com.application.boxmadikv1.cliente.menu.clienteProceso.ClienteProcesoFragment;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.RubrosActivity;
import com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity;
import com.application.boxmadikv1.utils.Config;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.MyFragmentAdapter;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuClienteActivity extends BaseActivity<MenuClienteView, MenuClientePresenter> implements MenuClienteView {

    public static final String TAG = MenuClienteActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @BindView(R.id.fab)
    FloatingActionMenu fabMenu;

    @BindView(R.id.fab_item_profile)
    FloatingActionButton fabProfile;
    @BindView(R.id.fab_item_message)
    FloatingActionButton fabMessage;
    @BindView(R.id.fab_item_setting)
    FloatingActionButton fabSetting;
    @BindView(R.id.fab_item_exit)
    FloatingActionButton fabExit;

    @BindView(R.id.contenedorConst)
    ConstraintLayout contenedorBuscar;

    @BindView(R.id.tablMenuCliente)
    TabLayout tabLayoutCliente;
    @BindView(R.id.viewPagerMenuCliente)
    ViewPager viewPager;

    @BindView(R.id.viewView)
    View view;
    @BindView(R.id.imageBuscar)
    ImageView imageViewBuscar;

    @BindView(R.id.buttonCrearPropuesta)
    Button buttonCrearPropuesta;

    @BindView(R.id.editTextBuscar)
    EditText editTextBuscar;

    // Session Manager Class
    // SessionManager session;
    private SecurePreferences preferences;

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
    protected MenuClientePresenter getPresenter() {
        return new MenuClientePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected MenuClienteView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_cliente_principal);
        ButterKnife.bind(this);
        initBottom();
        initMostrarMenuPrincipal();
        initSession();
        initBroadCastReceiver();
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
                    Log.d(TAG, "REGISTRATION_COMPLETE:");

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    // new push notification is received
                    Log.d(TAG, "PUSH_NOTIFICATION:");
                    presenter.onRealTipoEstado(intent.getExtras());
                    //Toast.makeText(getApplicationContext(), "Push notification: " + mensaje, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "message");
                    //txtMessage.setText(message);
                }
            }
        };
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
        //NotificationUtils.clearNotifications(getApplicationContext())

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*HashMap<String, String> user = session.getUserDetails();
        Log.d(TAG, "onStart :  " + user.get(SessionManager.KEY_USER));
        String keyUser = user.get(SessionManager.KEY_USER);
        if (keyUser == null) return;
        presenter.onInitKeyUser(keyUser);*/
    }

    private void initSession() {
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        String tipoUsuario = preferences.getString(Constantes.KEY_SECURE_USUARIO_TIPO);
        Log.d(TAG, "initSession : " + tipoUsuario);
    }

    private void initMostrarMenuPrincipal() {
        fabMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fabMenu.isOpened()) {
                    mostrarContenidoPagerTransparente();
                    Log.d(TAG, "!fabMenu.isOpened()");
                } else {
                    ocultarContenidoPagerTransparente();
                    Log.d(TAG, "else");
                }
                fabMenu.toggle(true);
            }
        });

    }

    private void initBottom() {
        fabMenu.setMenuButtonColorNormalResId(R.color.md_white_1000);
        fabMenu.setMenuButtonColorPressedResId(R.color.md_white_1000);
        /*NO Rotation*/
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(fabMenu.getMenuIconView(), "scaleX", 1.0f, 1.0f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(fabMenu.getMenuIconView(), "scaleY", 1.0f, 1.0f);
        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(fabMenu.getMenuIconView(), "scaleX", 1.0f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(fabMenu.getMenuIconView(), "scaleY", 1.0f, 1.0f);
        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                fabMenu.getMenuIconView().setImageResource(fabMenu.isOpened()
                        ? R.drawable.ic_clien_prueba : R.drawable.ic_clien_prueba); //ic_specialist
            }
        });
        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));
        fabMenu.setIconToggleAnimatorSet(set);
        /*****/
        setFabColorEtiquetas(fabProfile, fabMessage, fabSetting, fabExit);
    }

    private void setFabColorEtiquetas(com.github.clans.fab.FloatingActionButton... fabProfile) {
        for (com.github.clans.fab.FloatingActionButton fab : fabProfile) {
            fab.setColorNormal(ContextCompat.getColor(this, R.color.md_white_1000));
            fab.setLabelColors(ContextCompat.getColor(this, R.color.md_white_1000),
                    ContextCompat.getColor(this, R.color.md_white_1000),
                    ContextCompat.getColor(this, R.color.md_white_1000));
            fab.setLabelTextColor(ContextCompat.getColor(this, R.color.md_grey_800
            ));
        }
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarFragmentos(String keyUser, String codigoPais) {
        initAdapterViewPager(keyUser, codigoPais);
    }

    @Override
    public void mostrarFab() {
        fabMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarFab() {
        fabMenu.setVisibility(View.GONE);
    }

    @Override
    public void mostrarBuscador() {
        contenedorBuscar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarBuscador() {
        contenedorBuscar.setVisibility(View.GONE);
    }

    @Override
    public void mostrarContenidoPagerTransparente() {
        buttonCrearPropuesta.setEnabled(false);
        imageViewBuscar.setEnabled(false);
        editTextBuscar.setEnabled(false);
        tabLayoutCliente.setEnabled(false);
        viewPager.setEnabled(false);
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarContenidoPagerTransparente() {
        buttonCrearPropuesta.setEnabled(true);
        imageViewBuscar.setEnabled(true);
        editTextBuscar.setEnabled(true);
        tabLayoutCliente.setEnabled(true);
        viewPager.setEnabled(true);
        view.setVisibility(View.GONE);
    }

    @Override
    public void starActivityRubros() {
        /*Intent intent = new Intent(this,RubrosActivity.class);
        intent.putExtra("keyUser",keyUser);
        startActivity(intent);*/
        startActivity(new Intent(this, RubrosActivity.class));
    }

    @Override
    public void mostrarMensaje(String propuesta_creada_correctamente) {
        mostrarMensajeSnackBar(propuesta_creada_correctamente);
    }

    @Override
    public void starActivityPerfil() {
        /*String tipoUser = "cliente";
        Intent intent = new Intent(this, MenuClienteActivity.class);
        intent.putExtra("tipoUsuario", tipoUser);
        startActivity(intent);*/
        startActivity(new Intent(this, ClientePerfilActivity.class));
    }

    @Override
    public void obteniendoKeyUser() {
        /*HashMap<String, String> user = session.getUserDetails();
        Log.d(TAG, "onStart :  " + user.get(SessionManager.KEY_USER));
        String keyUser = user.get(SessionManager.KEY_USER);
        if (keyUser == null) return;*/
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String paisCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        Log.d(TAG, "MenuClienteActivity: " + paisCodigo);
        presenter.onInitKeyUser(keyUser, paisCodigo);
    }

    @Override
    public void initStartActivitySeleccionUser() {
        Intent intent = new Intent(this, SeleccionUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void mostrarDialogDireccion(String mensaje) {
        ocultarProgressBar();
        AlertDialog.Builder dialog = new AlertDialog.Builder(MenuClienteActivity.this);
        dialog.setTitle("Actualizar Datos de su Perfil")
                .setIcon(R.mipmap.ic_boxmadick)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();

                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        String tipoInicio = "validateRubroCliente";
                        Intent intent = new Intent(getApplicationContext(), ClientPerfilDirectionActivity.class);
                        intent.putExtra("validateRubro", tipoInicio);
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    public void mostrarNotificacion(String tituloChat, String mensajeChat) {
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.NOTIFICATION)
                .setIcon(R.mipmap.ic_mensaje_boxmadik)
                .setTitle(tituloChat)
                .setDialogBackgroundColor(getResources().getColor(R.color.md_orange_200))
                .setAutoDismissAfter(2000)
                .setMessage(mensajeChat);
        builder.show();
    }

    @Override
    public void actualizarFragmentoCotizacionPendiente() {
        Fragment primerFragmento = getFragment(ClientePendienteFragment.class);
        if (primerFragmento == null) return;
        primerFragmento.onStart();
        try {
            ((ClientePendienteFragment) primerFragmento).ocultarEmpty();
        } catch (Exception e) {
            Log.d(TAG, "Ocurrio algun problema");
        }

    }


    private void mostrarMensajeSnackBar(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void initAdapterViewPager(String keyUser, String codigoPais) {
        Log.d(TAG, "KeyUser : " + keyUser);
        /*Bundle args = getIntent().getExtras();
        if (args == null) return;
        args.putAll(getIntent().getExtras());
        args.putString("keyUser", keyUs)er;*/

        final MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(ClientePendienteFragment.newInstance(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE), "PENDIENTE");//TODOS
        fragmentAdapter.addFragment(ClienteProcesoFragment.newInstance(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO), "PROCESO");
        fragmentAdapter.addFragment(ClienteFinalizadoFragment.newInstance(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO), "FINALIZADO");
        fragmentAdapter.addFragment(ClienteCanceladoFragment.newInstance(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS), "REVOCADOS"); //cancelados
        //fragmentAdapter.addFragment(ClienteTodosFragment.newInstance(keyUser), "TODOS"); //cancelados
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(fragmentAdapter);
        tabLayoutCliente.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (presenter != null)
                    presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    private <T extends Fragment> T getFragment(Class<T> tClass) {
        List<Fragment> fragments = getFragments();
        for (Fragment fragment :
                fragments) {
            if (tClass.isInstance(fragment)) {
                return (T) fragment;
            }
        }
        return null;
    }

    private List<Fragment> getFragments() {
        return getSupportFragmentManager().getFragments();
    }

    @OnClick({R.id.fab_item_profile, R.id.fab_item_message, R.id.fab_item_setting, R.id.fab_item_exit, R.id.buttonCrearPropuesta})
    public void onClick(View view) {
        int itemId = view.getId();
        int pagePosition = viewPager.getCurrentItem();
        switch (itemId) {
            case R.id.fab_item_profile:
                Snackbar.make(fabProfile, "Perfil", Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "fab_item_profile");
                presenter.OnClickVerPerfil();
                break;
            case R.id.fab_item_message:
                Snackbar.make(fabProfile, "Mensajes", Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "fab_item_message");
                String tipoUsuario = "cliente";
                initStartActivityBandeja(tipoUsuario);
                break;
            case R.id.fab_item_setting:
                Snackbar.make(fabProfile, "Ajustes", Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "fab_item_setting");
                break;
            case R.id.fab_item_exit:
                presenter.initStartActivitySeleccionUser();
                Snackbar.make(fabProfile, "Salir", Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "fab_item_exit");
                break;
            case R.id.buttonCrearPropuesta:
                presenter.OnClickCrearPropusta();
                break;
            default:
                break;
        }

    }

    private void initStartActivityBandeja(String tipoUsuario) {
        Intent intent = new Intent(this, BandejaActivity.class);
        intent.putExtra("tipoUsuario", tipoUsuario);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
