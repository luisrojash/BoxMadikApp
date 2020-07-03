package com.application.boxmadikv1.notificaciones;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
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
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.DatosCotizacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.PerfilPropuestaActivity;
import com.application.boxmadikv1.notificaciones.adapter.NotificacionesAdapter;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;
import com.application.boxmadikv1.notificaciones.listener.NotificacionListener;
import com.application.boxmadikv1.rptRevocacion.RespuestaRevocacionActivity;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//public class NotificacionesActivity extends BaseActivity<NotificacionesView, NotificacionesPresenter> implements NotificacionesView {

public abstract class NotificacionesActivity<T extends AppCompatActivity, P extends NotificacionesPresenterImpl>
        extends AppCompatActivity implements NotificacionesView, NotificacionListener {

    public static final String TAG = NotificacionesActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    private NotificacionesAdapter notificacionesAdapter;
    private ProgressDialog progressDialog;

    protected abstract P obtenerPresenter();

    // protected abstract Bundle getExtrasInf();

    protected P presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        ButterKnife.bind(this);
        setupPresenter();
        progressDialog = new ProgressDialog(this);
        if (presenter != null) presenter.onCreate();
        initAdapter();
        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    LinearLayoutManager linearLayout;

    private void initAdapter() {
        linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        notificacionesAdapter = new NotificacionesAdapter(new ArrayList<NotificacionesUi>(), this);
        reciclador.setHasFixedSize(true);
        reciclador.setLayoutManager(linearLayout);
        reciclador.setAdapter(notificacionesAdapter);
        // mostrarContenidoEspecialidades();


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
        //notificacionesAdapter.setRecyclerView(reciclador);
    }


    private void setupPresenter() {
        presenter = (P) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = obtenerPresenter();
            Log.d(TAG, "setupPresenter");
            Bundle extras = getIntent().getExtras();
            presenter.setExtras(extras);


        }
        setPresenter(presenter);
    }

    @Override
    public P onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void setPresenter(NotificacionesPresenter presenter) {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) presenter.onStart();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        //if (presenter != null) presenter.onRe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }

    @Override
    public void mostrarProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (presenter != null) presenter.onBackPressed();
    }

    @Override
    public void onClickNoti(NotificacionesUi notificacionesUi) {
        presenter.onClickNoti(notificacionesUi);
        Log.d(TAG, "onClickNoti : " + notificacionesUi.getTipoNotificacion());
    }

    @Override
    public void mostrarLista(List<NotificacionesUi> notificacionesUiList) {
        notificacionesAdapter.setMostrarLista(notificacionesUiList);
    }

    @Override
    public void mostrarListaAdd(List<NotificacionesUi> notificacionesUiList) {
        notificacionesAdapter.mostrarListaAdd(notificacionesUiList);
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

    @Override
    public void initStartActivityDetallesPropuesta(ItemUi itemUi) {
        Log.d(TAG, "initStartActivityDetallesPropuesta : " + itemUi.getEspecialidadesUiList().size());

        //String tipoActivity = "actividadClicked";
        String tipoActivity = "actividadNotiNoti";
        Intent intent = new Intent(this, PerfilPropuestaActivity.class);
        intent.putExtra("itemUi", itemUi);
        intent.putExtra("notificacion", tipoActivity);
        startActivity(intent);
    }

    @Override
    public void initStartActivityDetallesCotizacionUser(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        String tipoLLegadaNotificacion = "actividadNotiNoti";
        Intent resultIntent = new Intent(this, DatosCotizacionActivity.class);
        resultIntent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        resultIntent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        resultIntent.putExtra("notificacion", tipoLLegadaNotificacion);
        startActivity(resultIntent);
        /*AQUI FALTA PAPu*/
    }

    @Override
    public void limpiarVista(List<NotificacionesUi> notificacionesUiList) {
        notificacionesAdapter.limpiarVista(notificacionesUiList);
    }

    @Override
    public void initStartActivityRespuestaRevocacion(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, String tipousuario) {
        String tipoLLegada = "notificacionRespuestaRevocaCliente";
        Intent intent = new Intent(this, RespuestaRevocacionActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        intent.putExtra("tipousuario", tipousuario);
        intent.putExtra("tipoLlegada", tipoLLegada);
        startActivity(intent);
    }

    @Override
    public void onClickOptions(final NotificacionesUi notificacionesUi) {
        String mensaje = "¿ Estas seguro que desea marcar como Visto ?";
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Marcar como Leido")
                .setIcon(R.mipmap.ic_boxmadick)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        presenter.onClickOptionsLeido(notificacionesUi);
                    }
                }).show();
    }

    @Override
    public void actualizarVistaNotificacion(NotificacionesUi notificacionesUi) {
        notificacionesAdapter.actualizarItem(notificacionesUi);
    }

    @Override
    public void mostrarTextViewEmpty() {
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarTextViewEmpty() {
        textViewEmpty.setVisibility(View.GONE);
    }


    @Override
    public void mostrarProgressDialog() {
        progressDialog.setMessage("Obteniendo Datos..");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initStartActivityPerfilPropuesta(ItemUi itemUi, int tipoDataEnviar) {
        String tipoActivity = "notificacionAceptoCotizacionEspec";
        Intent intent = new Intent(this, PerfilPropuestaActivity.class);
        intent.putExtra("itemUi", itemUi);
        intent.putExtra("notificacion", tipoActivity);
        intent.putExtra("tipoDataEnviar", tipoDataEnviar);
        startActivity(intent);
    }
}
