package com.application.boxmadikv1.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.chat.adapter.ChatAdapter;
import com.application.boxmadikv1.chat.entidad.MensajesUi;
import com.application.boxmadikv1.utils.Config;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends BaseActivity<ChatView, ChatPresenter> implements ChatView {

    public static final String TAG = ChatActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txt_name)
    TextView textViewNombre;
    @BindView(R.id.txt_rol)
    TextView textViewRol;
    @BindView(R.id.img_profile)
    CircleImageView circleImageView;
    @BindView(R.id.imageView7)
    ImageView imageViewRubro;
    @BindView(R.id.txt_nombrePropuesta)
    TextView textViewNombrePropuesta;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.edt_message)
    TextInputEditText textInputEditTextMensaje;
    @BindView(R.id.btn_send)
    FloatingActionButton floatingActionButtonEnviarMensaje;

    private SecurePreferences preferences;
    private ChatAdapter chatAdapter;
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
    protected ChatPresenter getPresenter() {
        return new ChatPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources());
    }

    @Override
    protected ChatView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        initBroadCastReceiver();
        preferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        initAdapter();
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
                    Log.d(TAG, "TOPIC_GLOBAL Aqui actualizamos la data:");

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    // new push notification is received

                    String mensaje = intent.getStringExtra("mensaje");
                    String timeStamp = intent.getStringExtra("timeStamp");
                    String codigoUsuario = intent.getStringExtra("codigoUsuario");
                    String codigoMensaje = intent.getStringExtra("codigoMensaje");
                    presenter.onDataRealTime(mensaje, timeStamp, codigoUsuario, codigoMensaje);
                    //Toast.makeText(getApplicationContext(), "Push notification: " + mensaje, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "message");
                    //txtMessage.setText(message);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();


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

    LinearLayoutManager layoutManager;

    private void initAdapter() {
        layoutManager = new LinearLayoutManager(getActivity());
        chatAdapter = new ChatAdapter(new ArrayList<MensajesUi>());
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(chatAdapter);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                presenter.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                presenter.onScrolled(layoutManager, dx, dy);
            }
        });


        // recycler.addOnScrollListener(new CustomScrollListener());

        //chatAdapter.setmLinearLayoutManager(layoutManager);
        //chatAdapter.setRecyclerView(recycler);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usuarioCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String paisCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        chatAdapter.setUserId(usuarioCodigo);
        chatAdapter.setRecyclerView(recycler);
        presenter.onObtenerDatoPreferencia(usuarioCodigo, paisCodigo);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarDatos(String nombre, String foto, String tipoUsuario, String imagenRubro, String nombrePropuesta) {
        textViewNombre.setText(nombre);
        textViewRol.setText(tipoUsuario);
        textViewNombrePropuesta.setText(nombrePropuesta);
        Glide.with(getActivity())
                .load(foto)
                .into(circleImageView);
        Glide.with(getActivity())
                .load(imagenRubro)
                .into(imageViewRubro);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void agregarMensaje(MensajesUi mensajesUi) {
        chatAdapter.agregarItem(mensajesUi);
        textInputEditTextMensaje.setText("");
       /* chatAdapter.notifyDataSetChanged();
        if (chatAdapter.getItemCount() > 1) {
            // scrolling to bottom of the recycler view
            recycler.getLayoutManager().smoothScrollToPosition(recycler, null, chatAdapter.getItemCount() - 1);
        }*/
    }

    @Override
    public void mostrarLista(List<MensajesUi> mensajesUiList) {
       /* chatAdapter .notifyDataSetChanged();
        if (chatAdapter.getItemCount() > 1) {
            recycler.getLayoutManager().smoothScrollToPosition(recycler, null, chatAdapter.getItemCount() - 1);
        }*/
        chatAdapter.mostrarLista(mensajesUiList);
    }

    @Override
    public void mostrarListaAdd(List<MensajesUi> mensajesUiList) {
        chatAdapter.mostrarListaAdd(mensajesUiList);
    }

    @Override
    public void habilitarButtonEnviar() {
        floatingActionButtonEnviarMensaje.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonEnviar() {
        floatingActionButtonEnviarMensaje.setEnabled(false);
    }

    @OnClick({R.id.layout_profile, R.id.btn_send})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.layout_profile:
                presenter.onClickBackPressed();
                break;
            case R.id.btn_send:
                String mensaje = textInputEditTextMensaje.getText().toString();
                String chat = "chat";
                Log.d(TAG, "Chat Envio Mensaje : " + Constantes.isResultadoCharacterChat(mensaje));
                presenter.onEnviarMensaje(Constantes.isResultadoCharacterChat(mensaje));
                break;
        }
    }
}
