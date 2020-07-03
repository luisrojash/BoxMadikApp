package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.DatosCotizacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.ReportePagoRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.remote.ReportePagoRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.useCase.AceptarCotizacion;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.useCase.NotificacionCotiAceptada;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReportePagoActivity extends BaseActivity<ReportePagoView, ReportePagoPresenter> implements ReportePagoView {

    public static final String TAG = ReportePagoActivity.class.getSimpleName();

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewNombreOficio)
    TextView textViewNombrePropuesta;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextDetallesPropuesta;
    @BindView(R.id.textviewFecha)
    TextView textViewFechaPropuesta;
    @BindView(R.id.textviewNameEspe)
    TextView textViewNameEspe;
    @BindView(R.id.textViewPagar)
    TextView textViewViewPagar;
    @BindView(R.id.imgProfile)
    CircleImageView circleImageViewEspecialista;
    @BindView(R.id.textViewNombreEspecialista)
    TextView textViewNombreEspecialista;
    @BindView(R.id.textViewTelefonoEspecialista)
    TextView textViewTelefonoEspecialista;
    @BindView(R.id.textViewEmailEspecialista)
    TextView textViewEmailEspecialista;
    @BindView(R.id.textViewFechaInicioEspec)
    TextView textViewFechaInicioEspec;
    @BindView(R.id.textViewFechaFinalEspec)
    TextView textViewFechaFinalEspec;


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ReportePagoPresenter getPresenter() {
        ReportePagoRepository reportePagoRepository = ReportePagoRepository.getInstance(new ReportePagoRemote());
        return new ReportePagoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new AceptarCotizacion(reportePagoRepository),
                new NotificacionCotiAceptada(reportePagoRepository));
    }

    @Override
    protected ReportePagoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_cliente_reporte_pago_activity);
        ButterKnife.bind(this);
        initToolbar();
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
            Log.d(TAG,"finish");

            presenter.onBackPresseDatosCotiza();
           // getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        textInputEditTextDetallesPropuesta.setEnabled(false);
        Glide.with(this)
                .load(detallesCotizacionUi.getImageRubro())
                .into(imageViewRubro);
        textViewNombrePropuesta.setText(detallesCotizacionUi.getNombreProyecto().toUpperCase());
        textInputEditTextDetallesPropuesta.setText(detallesCotizacionUi.getDetallesPropuesta());
        textViewFechaPropuesta.setText(Constantes.f_fecha_letras(detallesCotizacionUi.getFechaPropuesta()));
        textViewNameEspe.setText(cotizacionesUi.getNombreEspecialista());
        textViewViewPagar.setText(" S/. "+cotizacionesUi.getMonto());
        Glide.with(this)
                .load(cotizacionesUi.getImagen())
                .into(circleImageViewEspecialista);
        textViewNombreEspecialista.setText(cotizacionesUi.getNombreEspecialista().toUpperCase());
        textViewTelefonoEspecialista.setText(cotizacionesUi.getUsuCelular());
        textViewEmailEspecialista.setText(cotizacionesUi.getUsuEmail());
        textViewFechaInicioEspec.setText(cotizacionesUi.getFecha());
       // textViewFechaFinalEspec.setText(cotizacionesUi.getFecha());
    }

    @Override
    public void ocultarTeclado() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void mostrarDialogoDeConfirmacion(String mensaje) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ReportePagoActivity.this);
        dialog.setTitle("Finalizar Pago")
                //.setIcon(R.drawable.ic_albanieria)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        presenter.onClickAceptoReportePago();
                    }
                }).show();
    }

    @Override
    public void mostrarMensaje(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initStartActivityMenuCliente(String estado) {
        Intent intent = new Intent(this, MenuClienteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("estado", estado);
        startActivity(intent);
        Log.d(TAG, "initStartActivityMenuCliente");
    }

    @Override
    public void initStartActivityDatosCotiza(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent  = new Intent(this,DatosCotizacionActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA,detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI,cotizacionesUi);
        startActivity(intent);
    }


    @OnClick({R.id.btnFinalizar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnFinalizar:
                presenter.onClickBtnFinalizar();
                break;
        }
    }


}
