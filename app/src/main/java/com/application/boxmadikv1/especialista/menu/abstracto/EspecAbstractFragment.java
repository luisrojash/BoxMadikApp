package com.application.boxmadikv1.especialista.menu.abstracto;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.especialista.menu.abstracto.adapter.EspecAbstractAdapter;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.EspecAbstractRepository;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.local.EspecAbstractLocal;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.remote.EspecAbstractRemote;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.abstracto.listener.EspecAbstractListener;
import com.application.boxmadikv1.especialista.menu.abstracto.useCase.EliminarCotizacion;
import com.application.boxmadikv1.especialista.menu.abstracto.useCase.ListaCotizaciones;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.PerfilPropuestaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.calificar.CalificarClienteActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.EspecBancoActivity;
import com.application.boxmadikv1.extra.InternetCheck;
import com.application.boxmadikv1.utils.Config;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class EspecAbstractFragment extends BaseFragment<EspecAbstractView, EspecAbstractPresenter> implements EspecAbstractView, EspecAbstractListener,
        InternetCheck.Consumer<EspecialistaEstadosUi> {

    public static final String TAG = EspecAbstractFragment.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    EspecAbstractAdapter especAbstractAdapter;

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected EspecAbstractPresenter getPresenter() {
        EspecAbstractRepository especAbstractRepository = EspecAbstractRepository.getmInstance(new EspecAbstractLocal(
                        InjectorUtils.provideRubroDao(),
                        InjectorUtils.provideRangoPrecioDao(),
                        InjectorUtils.provideOficioDao(),
                        InjectorUtils.provideRangoDiasDao(),
                        InjectorUtils.provideRangoTurnoDao()
                ),
                new EspecAbstractRemote());
        return new EspecAbstractPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListaCotizaciones(especAbstractRepository),
                new EliminarCotizacion(especAbstractRepository));
    }

    @Override
    protected EspecAbstractView getBaseView() {
        return this;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_abstract_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        // initBroadCastReceiver();
    }

    private void initBroadCastReceiver() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    // new push notification is received
                    //keyUser, codigoPais, Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS
                    String usuarioCodigo = intent.getStringExtra("codigoUsuarioCotizado");
                    String codigoPais = intent.getStringExtra("codigoPais");
                    String tipoEstado = intent.getStringExtra("tipoNotiCodigo");
                    presenter.onRealTipoEstado(usuarioCodigo, codigoPais, tipoEstado);
                    //  Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "usuarioCodigo : " + usuarioCodigo +
                            "codigoPais : " + codigoPais +
                            "tipoEstado : " + tipoEstado);
                    //txtMessage.setText(message);
                }
            }
        };
    }


    LinearLayoutManager linearLayout;

    private void initAdapter() {
        especAbstractAdapter = new EspecAbstractAdapter(new ArrayList<EspecialistaEstadosUi>(), this);
        linearLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        reciclador.setLayoutManager(new LinearLayoutManager(getContext()));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(especAbstractAdapter);


        reciclador.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                presenter.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenter.onScrolled(linearLayout, dx, dy);
            }
        });
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarMensaje(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

   /* public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (checkColide(e.getX(), e.getY())) {
                    isFootballTouched = true;
                    downT = c.MILLISECOND;
                    downX = e.getX();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                //moveFootball(e.getX(), e.getY());
                return true;
            case MotionEvent.ACTION_UP:
                upT = c.MILLISECOND;
                upX = e.getX();
                getVelocity();
                return true;
        }
        return false;
    }*/


    @Override
    public void mostraListaEspecialistaEstados(List<EspecialistaEstadosUi> especialistaEstadosUis) {
        especAbstractAdapter.mostrarLista(especialistaEstadosUis);
        //especAbstractAdapter.notifyDataSetChanged();
    }

    @Override
    public void mostrarDialogConfirmacionDelete(final EspecialistaEstadosUi especialistaEstadosUi, String mensaje) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Eliminar Cotización")
                // .setIcon(R.drawable.ic_delete_icon)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        presenter.onAceptarDeleteItem(especialistaEstadosUi);
                    }
                }).show();
    }

    @Override
    public void startActivityPerfilPropuesta(ItemUi itemUi) {
        String tipoActivity = "actividadClicked";
        Intent intent = new Intent(getActivity(), PerfilPropuestaActivity.class);
        intent.putExtra("itemUi", itemUi);
        intent.putExtra("notificacion", tipoActivity);
        getActivity().startActivity(intent);
    }

    @Override
    public void eliminarItem(EspecialistaEstadosUi especialistaEstadosUi) {
        especAbstractAdapter.eliminarItem(especialistaEstadosUi);
    }

    @Override
    public void onClickEliminarItem(EspecialistaEstadosUi especialistaEstadosUi) {
        presenter.onClickEliminarItem(especialistaEstadosUi);
        Log.d(TAG, "onClickEliminarItem");
    }

    @Override
    public void onClickItem(EspecialistaEstadosUi especialistaEstadosUi) {
        Object object = especialistaEstadosUi;
        new InternetCheck(this, object);
        //presenter.onClickItem(especialistaEstadosUi);
        Log.d(TAG, "onClickItem");
    }

    @Override
    public void actualizarListas() {
        especAbstractAdapter.notifyDataSetChanged();
    }

    @Override
    public void accept(Boolean internet, EspecialistaEstadosUi especialistaEstadosUi) {
        presenter.onValidateInternet(internet, especialistaEstadosUi);
       /* EspecialistaEstadosUi especialistaEstadosUi = (EspecialistaEstadosUi) objeto;
        presenter.onClickItem(especialistaEstadosUi);*/
    }

    @Override
    public void mostrarDialogMensaje(String s) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Problemas Conexión")
                .setIcon(R.mipmap.ic_boxmadick)
                .setMessage(s)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                }).show();
    }


    @Override
    public void onPause() {
        //LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
       /* // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        // NotificationUtils.clearNotifications(getApplicationContext());*/


    }

    @Override
    public void initStartActivityCalifica(ItemUi itemUi, String nombreUsu, String usuPaisImagenCliente, String usuFotoCliente) {
        Intent intent = new Intent(getActivity(), CalificarClienteActivity.class);
        intent.putExtra("itemUi", itemUi);
        intent.putExtra("nombreCliente", nombreUsu);
        intent.putExtra("paisCliente", usuPaisImagenCliente);
        intent.putExtra("imagenCliente", usuFotoCliente);
        startActivity(intent);
    }

    @Override
    public void initStartActivityBancoDatos() {
        Intent intent = new Intent(getActivity(), EspecBancoActivity.class);
        //intent.putExtra(EXTRA_DATA_BANCARAIA_ACTIVITY, datosBancaria);
        startActivity(intent);
    }

    @Override
    public void mostrarDialogCentroBancario() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setCancelable(false);
        dialog.setTitle("Se Requiere Información")
                // .setIcon(R.drawable.ic_delete_icon)
                .setMessage("Tienes una propuesta de servicio por calificar y se necesita tu cuenta bancaria para realizar el deposito")
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        initStartActivityBancoDatos();
                    }
                }).show();
    }

    @Override
    public void mostrarTextViewEmpty() {
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarTextViewEmpty() {
        textViewEmpty.setVisibility(View.GONE);
    }
}
