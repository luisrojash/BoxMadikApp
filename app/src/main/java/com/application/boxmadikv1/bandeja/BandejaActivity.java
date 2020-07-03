package com.application.boxmadikv1.bandeja;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.bandeja.adapter.BandejaAdapter;
import com.application.boxmadikv1.bandeja.dataSource.BandejaRepository;
import com.application.boxmadikv1.bandeja.dataSource.remote.BandejaRemote;
import com.application.boxmadikv1.bandeja.entidad.BandejaUi;
import com.application.boxmadikv1.bandeja.listener.BandejaListener;
import com.application.boxmadikv1.bandeja.useCase.MostrarListaGrupo;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.chat.ChatActivity;
import com.application.boxmadikv1.utils.Config;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BandejaActivity extends BaseActivity<BandejaView, BandejaPresenter> implements BandejaView, BandejaListener {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    private BandejaAdapter bandejaAdapter;

    public static final String TAG = BandejaActivity.class.getSimpleName();

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
    protected BandejaPresenter getPresenter() {
        BandejaRepository bandejaRepository = BandejaRepository.getmInstance(new BandejaRemote());
        return new BandejaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new MostrarListaGrupo(bandejaRepository));
    }

    @Override
    protected BandejaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.bandeja_activity);
        ButterKnife.bind(this);
        initBroadCastReceiver();
        preferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        initView();
        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initBroadCastReceiver() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    // new push notification is received
                    String mensaje = intent.getStringExtra("mensaje");
                    String codigoGrupoChat = intent.getStringExtra("codigoGrupoChat");
                    String timeStamp = intent.getStringExtra("timeStamp");

                    presenter.onDataRealTime(mensaje, codigoGrupoChat, timeStamp);
                    //Toast.makeText(getApplicationContext(), "Push notification: " + mensaje, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "message");
                    //txtMessage.setText(message);
                }
            }
        };
    }

    private void initView() {
        bandejaAdapter = new BandejaAdapter(new ArrayList<BandejaUi>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(bandejaAdapter);
        bandejaAdapter.setRecyclerView(reciclador);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userKey = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String paisCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onPreferencesData(userKey, paisCodigo);
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
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaGrupo(List<BandejaUi> bandejaUiList) {
        bandejaAdapter.mostrarLista(bandejaUiList);
    }

    @Override
    public void initStartActivityBandejaCliente(BandejaUi bandejaUi) {
        Log.d(TAG, "initStartActivityBandejaCliente" +
                "bandejaUi.getIdUsuarioCotiza() : " + bandejaUi.getIdUsuarioCotiza() +
                "bandejaUi.getIdUsuarioPropuesta(): " + bandejaUi.getIdUsuarioPropuesta());
        Intent intent = new Intent(this, ChatActivity.class);

        intent.putExtra("idUsuarioPropuesta", bandejaUi.getIdUsuarioCotiza());
        intent.putExtra("keyUser", bandejaUi.getIdUsuarioPropuesta());

        intent.putExtra("idGrupoChat", bandejaUi.getIdGrupoChat());
        intent.putExtra("tipoUsuario", bandejaUi.getTipoUsuario());
        intent.putExtra("imagenRubro", bandejaUi.getImagenRubro());
        intent.putExtra("nombrePropuesta", bandejaUi.getNombrePropuesta());
        startActivity(intent);
    }

    @Override
    public void initStartActivityBandejaEspecialista(BandejaUi bandejaUi) {
        Log.d(TAG, "initStartActivityBandejaEspecialista" +
                "bandejaUi.getIdUsuarioCotiza() : " + bandejaUi.getIdUsuarioCotiza() +
                "bandejaUi.getIdUsuarioPropuesta(): " + bandejaUi.getIdUsuarioPropuesta());
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("idUsuarioPropuesta", bandejaUi.getIdUsuarioPropuesta());
        intent.putExtra("keyUser", bandejaUi.getIdUsuarioCotiza());
        intent.putExtra("idGrupoChat", bandejaUi.getIdGrupoChat());
        intent.putExtra("tipoUsuario", bandejaUi.getTipoUsuario());
        intent.putExtra("imagenRubro", bandejaUi.getImagenRubro());
        intent.putExtra("nombrePropuesta", bandejaUi.getNombrePropuesta());
        startActivity(intent);
    }

    @Override
    public void actualizarVistasBandeja(BandejaUi bandejaUi) {
        bandejaAdapter.actualizarItemBandeja(bandejaUi);
        //  bandejaAdapter.mostrarConteo(bandejaUi);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarTextViewEmpty() {
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultartTextViewEmpty() {
        textViewEmpty.setVisibility(View.GONE);
    }


    @Override
    public void onClickBandeja(BandejaUi bandejaUi) {
        presenter.onClickBandeja(bandejaUi);
       /* Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("idUsuarioPropuesta",  bandejaUi.getIdUsuarioCotiza());
        intent.putExtra("keyUser", bandejaUi.getIdUsuarioPropuesta());
        intent.putExtra("idGrupoChat", bandejaUi.getIdGrupoChat());
        intent.putExtra("tipoUsuario", bandejaUi.getTipoUsuario());
        intent.putExtra("imagenRubro",bandejaUi.getImagenRubro());
        intent.putExtra("nombrePropuesta",bandejaUi.getNombrePropuesta());
        startActivity(intent);*/
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
