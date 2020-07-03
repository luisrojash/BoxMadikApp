package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.chat.ChatActivity;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.DescripcionFragment;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.CotizacionFragment;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.PerfilClienteFragment;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.MyFragmentAdapter;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerfilPropuestaActivity extends BaseActivity<PerfilPropuestaView, PerfilPropuestaPresenter>
        implements PerfilPropuestaView {

    public static final String TAG = PerfilPropuestaActivity.class.getSimpleName();

    @BindView(R.id.tabLayout)
    TabLayout tabLayoutEspecialista;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.imageView2)
    ImageView imageViewRubro;
    @BindView(R.id.textView7)
    TextView textViewNombrePropuesta;
    @BindView(R.id.textViewFecha)
    TextView textViewFecha;
    @BindView(R.id.textViewNombreDepartamento)
    TextView textViewDepartamento;
    @BindView(R.id.textViewNombreDistrito)
    TextView textViewNombreDistrito;
    @BindView(R.id.buttonStartChat)
    FloatingActionButton floatingActionButtonChat;

    //private SessionManager sessionManager;
    private SecurePreferences preferences;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected PerfilPropuestaPresenter getPresenter() {
        return new PerfilPropuestaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected PerfilPropuestaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    AlertDialog.Builder dialogCreate;
    AlertDialog optionDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenteUserDatalles();

        dialogCreate = new AlertDialog.Builder(PerfilPropuestaActivity.this);
        optionDialog = dialogCreate.create();
        // OptionDialog.dismiss();
    }


    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /*@Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }*/


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_especialista_perfil_propuesta);
        ButterKnife.bind(this);
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);


    }

    private void initPresenteUserDatalles() {
        /*HashMap<String, String> user = sessionManager.getUserDetails();*/
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onSessionManager(keyUser, codigoPais);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarFragmentos(ItemUi itemUi) {
        initAdapterViewPager(itemUi);
    }

    @Override
    public void mostrarDataInicial(ItemUi itemUi) {
        Glide.with(this).load(itemUi.getImagePropuesta()).into(imageViewRubro);
        Log.d(TAG, "mostrarDataINicial : " + itemUi.getNombrePropuesta());
        textViewNombrePropuesta.setText(itemUi.getNombrePropuesta().toUpperCase());
        textViewFecha.setText(Constantes.f_fecha_letras(itemUi.getFechaPropuesta()));
        textViewDepartamento.setText(itemUi.getNombreDepartamento() + " - " + itemUi.getNombreDistrito());
        //textViewNombreDistrito.setText("Dist : " + itemUi.getNombreDistrito());

    }

    @Override
    public void iniStartActivityMenuEspecialista() {
        finish();
        Intent intent = new Intent(this, MenuEspecialistaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void starActivityChat(String tipoEstadoGrupo, String idUsuarioPropuesta, String keyUser,
                                 String idPropuesta, String idGrupoChat, String tipoUsuario, String imagenRubro, String nombrePropuesta) {

        Log.d(TAG, "tipoEstadoGrupo :: " + tipoEstadoGrupo +
                " /  idUsuarioPropuesta :: " + idUsuarioPropuesta +
                " /  idUsuarioEmisor :: " + keyUser +
                "/  idPropuesta :: " + idPropuesta +
                "/  idGrupoChat :: " + idGrupoChat +
                "/  idGrupoChat :: " + tipoUsuario

        );

        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra("tipoEstadoGrupo", tipoEstadoGrupo);
        intent.putExtra("idUsuarioPropuesta", idUsuarioPropuesta);
        intent.putExtra("keyUser", keyUser);
        intent.putExtra("idPropuesta", idPropuesta);
        intent.putExtra("idGrupoChat", idGrupoChat);
        intent.putExtra("tipoUsuario", tipoUsuario);
        intent.putExtra("imagenRubro", imagenRubro);
        intent.putExtra("nombrePropuesta", nombrePropuesta);
        startActivity(intent);
    }

    @Override
    public void habilitarButtonChat() {
        floatingActionButtonChat.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonChat() {
        floatingActionButtonChat.setEnabled(false);
    }

    @Override
    public void mostrarMensaje(String mensajeButton) {
        Toast.makeText(this, mensajeButton, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void mostrarDialogInternet() {
        try {
            dialogCreate.setTitle("Problemas Conexión")
                    .setIcon(R.mipmap.ic_boxmadick)
                    .setMessage("Verifique su conexión")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            Intent intent = new Intent(PerfilPropuestaActivity.this, MenuEspecialistaActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }
                    }).show();
        } catch (Exception e) {
            Log.d(TAG, "Exception : " + e);
        }

    }

    @Override
    public void ocultarDialogInternet() {
        optionDialog.dismiss();

    }

    @Override
    public void mostrarFabButtonChat() {
        floatingActionButtonChat.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarFabButtonChat() {
        floatingActionButtonChat.setVisibility(View.GONE);
    }

    @Override
    public void mostrarDialogDireccion(String mensaje) {
        ocultarProgressBar();
        AlertDialog.Builder dialog = new AlertDialog.Builder(PerfilPropuestaActivity.this);
        dialog.setTitle("Actualizar Datos de su Perfil")
                .setIcon(R.mipmap.ic_boxmadick)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                       // dialoginterface.cancel();
                        Intent intent = new Intent(getApplicationContext(), MenuEspecialistaActivity.class);
                        startActivity(intent);

                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                       /* String tipoInicio = "validateRubroEspec";
                        Intent intent = new Intent(getApplicationContext(), ClientPerfilDirectionActivity.class);
                        intent.putExtra("validateRubro", tipoInicio);
                        startActivity(intent);*/
                        Intent intent = new Intent(getApplicationContext(), MenuEspecialistaActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }


    private void initAdapterViewPager(ItemUi itemUi) {
        Bundle args = new Bundle();
        args.putAll(getIntent().getExtras());
        args.putParcelable("itemUi", itemUi);
        /*args.putInt("cursoId", idCurso);
        args.putInt("idProgramaEducativo", idProgramaEducativo);*/
        final MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(DescripcionFragment.newInstance(args), "DESCRIPCIÓN");
        fragmentAdapter.addFragment(CotizacionFragment.newInstance(args), "COTIZACIÓN");
        fragmentAdapter.addFragment(PerfilClienteFragment.newInstance(args), "PERFIL");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(fragmentAdapter);
        tabLayoutEspecialista.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*if (presenter != null)
                    presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);
*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.close, R.id.buttonStartChat})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.close:
                //       finish();
                presenter.onClickClose();
                break;
            case R.id.buttonStartChat:
                presenter.onStartChat();
                break;
        }
    }


    /*@Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }*/
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            presenter.onCheckConexion(isConnected, activeNetwork);


            //  presenter.onCheckConexion(isConnected);
            // Log.d(TAG,"Wifi  : "+isConnected );
            /*int wifiEstado  =intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN);
            if(wifiEstado == WifiManager.WIFI_STATE_ENABLED){
                Log.d(TAG,"Wifi ON : " );
            }else if (wifiEstado == WifiManager.WIFI_STATE_DISABLED){
                Log.d(TAG,"Wifi OF : " );
            }*/
        }
    };
}
