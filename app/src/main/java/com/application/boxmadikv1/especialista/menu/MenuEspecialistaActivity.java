package com.application.boxmadikv1.especialista.menu;

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
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.ClientPerfilDirectionActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity;
import com.application.boxmadikv1.especialista.menu.especialistaCancelado.EspecialistaCanceladoFragment;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.EspecialistaEnviadosFragment;
import com.application.boxmadikv1.especialista.menu.especialistaFinalizado.EspecialistaFinalizadoFragment;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.EspecialistaPerfilActivity;
import com.application.boxmadikv1.especialista.menu.especialistaProceso.EspecialistaProcesoFragment;
import com.application.boxmadikv1.especialista.menu.reportes.ReportesActivity;
import com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity;
import com.application.boxmadikv1.utils.Config;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.MyFragmentAdapter;
import com.application.boxmadikv1.utils.SecurePreferences;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuEspecialistaActivity extends BaseActivity<MenuEspecialistaView, MenuEspecialistaPresenter> implements MenuEspecialistaView {

    public static final String TAG = MenuEspecialistaActivity.class.getSimpleName();

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @BindView(R.id.fab)
    FloatingActionMenu fabMenu;

    @BindView(R.id.fab_item_profile)
    FloatingActionButton fabProfile;
    @BindView(R.id.fab_item_message)
    FloatingActionButton fabMessage;
    @BindView(R.id.fab_item_card)
    FloatingActionButton fabCard;
    @BindView(R.id.fab_item_exit)
    FloatingActionButton fabExit;
    @BindView(R.id.fab_item_setting)
    FloatingActionButton fabSettings;

    @BindView(R.id.contenedorConst)
    ConstraintLayout contenedorBuscar;

    @BindView(R.id.tablMenuEspecialista)
    TabLayout tabLayoutEspecialista;
    @BindView(R.id.viewPagerMenuEspecialista)
    ViewPager viewPager;

    @BindView(R.id.viewView)
    View view;

    @BindView(R.id.imageBuscar)
    ImageView imageViewBuscar;
    @BindView(R.id.buttonBuscarPropuesta)
    Button buttonBuscarPropuesta;
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
    protected MenuEspecialistaPresenter getPresenter() {
        return new MenuEspecialistaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources());
    }

    @Override
    protected MenuEspecialistaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_especialista_principal);
        ButterKnife.bind(this);
        initAnimation();
        initBottom();
        initMostrarMenuPrincipal();
        initSession();
        initBroadCastReceiver();

    }
    public void pintarColorTabRevocados(){
        //tabLayoutEspecialista.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayoutEspecialista.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayoutEspecialista.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
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
                   /* String mensaje = intent.getStringExtra("mensaje");
                    String timeStamp = intent.getStringExtra("timeStamp");
                    String codigoUsuario = intent.getStringExtra("codigoUsuario");
                    String codigoMensaje = intent.getStringExtra("codigoMensaje");*/
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


    private void initSession() {
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        String tipoUsuario = preferences.getString(Constantes.KEY_SECURE_USUARIO_TIPO);
        Log.d(TAG, "initSession : " + tipoUsuario);
    }

    Animation animTranslate;

    private void initAnimation() {
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
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

    private void ocultarContenidoPagerTransparente() {
        buttonBuscarPropuesta.setEnabled(true);
        imageViewBuscar.setEnabled(true);
        editTextBuscar.setEnabled(true);
        tabLayoutEspecialista.setEnabled(true);
        viewPager.setEnabled(true);
        view.setVisibility(View.GONE);


    }

    //  boolean isRelease = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN");
                // isRelease = false;
                //code move pointer position
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "ACTION_MOVE");
               /* if (isRelease) {
                    return true;
                }*/
                // code to draw line from x to y
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "ACTION_POINTER_UP");
                //isRelease = true;
                //Release pointer
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP");
                //isRelease = true;
                //Release pointer
                break;
        }
        return true;
    }

    private void mostrarContenidoPagerTransparente() {
        buttonBuscarPropuesta.setEnabled(false);
        imageViewBuscar.setEnabled(false);
        editTextBuscar.setEnabled(false);
        tabLayoutEspecialista.setEnabled(false);
        viewPager.setEnabled(false);
        view.setVisibility(View.VISIBLE);
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
                        ? R.drawable.ic_espec_prueba : R.drawable.ic_espec_prueba); //ic_specialist
            }
        });
        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));
        fabMenu.setIconToggleAnimatorSet(set);
        /*****/
        setFabColorEtiquetas(fabProfile, fabMessage, fabSettings, fabCard, fabExit);
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
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onInitKeyUser(keyUser, codigoPais);
    }

    @Override
    public void mostrarFragmentos(String keyUser, String codigoPais) {
        initAdapterViewPager(keyUser, codigoPais);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        presenter.setExtras(bundle);
    }

    private void initAdapterViewPager(String keyUser, String codigoPais) {
        Log.d(TAG, "KeyUser : " + keyUser);
        Bundle args = new Bundle();
        if (args == null) return;/*
        args.putAll(getIntent().getExtras());
        args.putString("keyUser", keyUser);*/
        final MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(EspecialistaEnviadosFragment.newInstance(keyUser, codigoPais, Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS), "ENVIADOS");
        fragmentAdapter.addFragment(EspecialistaProcesoFragment.newInstance(keyUser, codigoPais, Constantes.ESTADO_ESPECIALISTA_ACEPTADO), "EN PROCESO");
        fragmentAdapter.addFragment(EspecialistaFinalizadoFragment.newInstance(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO), "FINALIZADO");
        fragmentAdapter.addFragment(EspecialistaCanceladoFragment.newInstance(keyUser, codigoPais, Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS), "REVOCADOS");//CANCELADO

        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(fragmentAdapter);
        tabLayoutEspecialista.setupWithViewPager(viewPager);
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
                //  Log.d(TAG, "state " + state);
                //  viewPager.
            }
        });
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
    public void startBuscarPropuestaActivity(ArrayList<String> listIdRubros) {
        Intent intent = new Intent(this, BuscarPropuestaActivity.class);
        //intent.putExtra("mylist", listIdRubros);
        startActivity(intent);
    }

    @Override
    public void initStartActivitySeleccionUser() {
        Intent intent = new Intent(this, SeleccionUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarDialogDireccion(String mensaje) {
        ocultarProgressBar();
        AlertDialog.Builder dialog = new AlertDialog.Builder(MenuEspecialistaActivity.this);
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
                        String tipoInicio = "validateRubroEspec";
                        Intent intent = new Intent(getApplicationContext(), ClientPerfilDirectionActivity.class);
                        intent.putExtra("validateRubro", tipoInicio);
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    public void actualizarListasAceptoCotiza() {
        Fragment primerFragmento = getFragment(EspecialistaEnviadosFragment.class);
        Fragment segundoFragmento = getFragment(EspecialistaProcesoFragment.class);
        if (primerFragmento == null) return;
        if (segundoFragmento == null) return;
        primerFragmento.onStart();
        segundoFragmento.onStart();
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


    @OnClick({R.id.buttonBuscarPropuesta, R.id.fab_item_exit, R.id.fab_item_card, R.id.fab_item_profile, R.id.fab_item_message})
    public void onClick(View view) {
        int itemId = view.getId();
        int pagePosition = viewPager.getCurrentItem();
        switch (itemId) {
            case R.id.fab_item_profile:
                Log.d(TAG, "fab_item_profile");
                initEditarPerfilEspecialista();
                break;
            case R.id.fab_item_message:
                String tipoUsuario = "especialista";
                initStartActivityBandeja(tipoUsuario);
                Log.d(TAG, "fab_item_message");
                break;
            case R.id.fab_item_setting:
                Log.d(TAG, "fab_item_setting");
                break;
            case R.id.fab_item_card:
                Log.d(TAG, "fab_item_card");
                initStartActivityReportes();
                break;
            case R.id.fab_item_exit:
                Log.d(TAG, "fab_item_exit");
                presenter.initStartActivitySeleccionUser();
                break;
            case R.id.buttonBuscarPropuesta:
                //SeleccionRubrosActivity.startSeleccionRubros(this);
                view.startAnimation(animTranslate);
                presenter.onClickBuscarPropuesta();
                //startActivity(new Intent(this, BuscarPropuestaActivity.class));
                break;

        }

    }

    private void initStartActivityReportes() {
        Intent intent = new Intent(this, ReportesActivity.class);
        startActivity(intent);
    }

    private void initStartActivityBandeja(String tipoUsuario) {
        Intent intent = new Intent(this, BandejaActivity.class);
        intent.putExtra("tipoUsuario", tipoUsuario);
        startActivity(intent);
    }

    private void initEditarPerfilEspecialista() {
        startActivity(new Intent(this, EspecialistaPerfilActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
}
