package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.chat.ChatActivity;
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.CulquiActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource.DatosCotizaRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource.remote.DatosCotizaRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.DesembolsarActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.ReportePagoActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.RevocacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.terminos.TerminosCotizaActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.useCase.ValidarTipoRevocacion;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.cliente.menu.tipopago.TipoPagoActivity;
import com.application.boxmadikv1.rptRevocacion.RespuestaRevocacionActivity;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

public class DatosCotizaFragment extends BaseFragment<DatosCotizaView, DatosCotizaPresenter> implements DatosCotizaView {

    public static final String TAG = DatosCotizaFragment.class.getSimpleName();

    @BindView(R.id.imgProfile)
    ImageView imageViewProfile;
    @BindView(R.id.textView11)
    TextView textViewNombre;

    @BindView(R.id.textView15)
    TextView textViewPendiente;
    @BindView(R.id.textView17)
    TextView textViewFinalizadas;
    @BindView(R.id.textView16)
    TextView textViewAceptadas;

    @BindView(R.id.btnAceptar)
    Button buttonAceptar;
    @BindView(R.id.btnDesembolsar)
    Button buttonDesembolsar;
    @BindView(R.id.btnEnviarMensaje)
    Button buttonEnviarMensaje;

    @BindView(R.id.btnEnviarRevocacion)
    Button buttonRevocacion;
    @BindView(R.id.btnLevantarRevocacion)
    Button buttonLevantarRevocacion;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextDetallesCotiza;
    @BindView(R.id.textView10)
    TextView textViewMontoCotizado;

    @BindView(R.id.id_banderaRight)
    ImageView imageViewBandera;

    @BindView(R.id.textViewFechaCotiza)
    TextView textViewFechaCotiza;

    @BindView(R.id.btnReportePago)
    Button btnReportePago;

    @BindView(R.id.btnVerRespuesta)
    Button buttonVerRepuestaRevocada;

    private ProgressDialog progressDialog;


    public static DatosCotizaFragment newInstance(DetallesCotizacionUi detallesCotizacionUi,
                                                  CotizacionesUi cotizacionesUi) {
        Bundle args = new Bundle();
        DatosCotizaFragment fragment = new DatosCotizaFragment();
        args.putParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        args.putParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);

    }

    private void initView() {
        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }


    @Override
    protected DatosCotizaPresenter getPresenter() {
        DatosCotizaRepository datosCotizaRepository = DatosCotizaRepository.getmInstance(new DatosCotizaRemote());
        return new DatosCotizaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ValidarTipoRevocacion(datosCotizaRepository));
    }

    @Override
    protected DatosCotizaView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_datos_cotiza_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void mostrarDataInicial(String imagen, String nombreEspecialista, float puntuacion, String detallesCotiza,
                                   String cotiPendiente, String cotiFinalizados, String cotiAceptados, String monto,
                                   String paisImagen, String fechaCotiza) {
        Log.d(TAG, "monto" + monto);
        textViewMontoCotizado.setText("Cotizado:   S./ " + monto.toUpperCase());
        textViewNombre.setText(nombreEspecialista.toUpperCase());
        ratingBar.setRating(puntuacion);
        textInputEditTextDetallesCotiza.setHint("  ");
        textInputEditTextDetallesCotiza.setText(detallesCotiza);
        textViewPendiente.setText(cotiPendiente);
        textViewFinalizadas.setText(cotiFinalizados);
        textViewAceptadas.setText(cotiAceptados);
        Glide.with(getActivity())
                .load(imagen)
                .into(imageViewProfile);
        textInputEditTextDetallesCotiza.setEnabled(false);
        Glide.with(getActivity())
                .load(imagen)
                .into(imageViewProfile);
        Glide.with(getActivity())
                .load(paisImagen)
                .into(imageViewBandera);
        // textViewFechaCotiza.setText("Fecha: \n " + fechaCotiza);
        textViewFechaCotiza.setText("Fecha: " + fechaCotiza);

    }

    @Override
    public void initStartActivityTerminoCondicionesPagar(DetallesCotizacionUi detallesCotizacionUi,
                                                         CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(getActivity(), TerminosCotizaActivity.class);
        intent.putExtra(Constantes.EXTRAS_TERMINOS_CONDICIONES, Constantes.TERMINOS_CONDICIONES_PAGAR);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);
    }


    @Override
    public void mostrarButtonAceptar() {
        buttonAceptar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarButtonDesembolsar() {
        buttonDesembolsar.setVisibility(View.GONE);
    }

    @Override
    public void ocultarButtonAceptar() {
        buttonAceptar.setVisibility(View.GONE);
    }

    @Override
    public void mostrarButtonDesembolsar() {
        buttonDesembolsar.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initStartActivityTerminoCondicionesRevocacion(DetallesCotizacionUi detallesCotizacionUi,
                                                              CotizacionesUi cotizacionesUi) {

       /* Intent intent = new Intent(getActivity(), TerminosRevocacionActivity.class);
        intent.putExtra(Constantes.EXTRAS_TERMINOS_CONDICIONES, Constantes.TERMINOS_CONDICIONES_REVOCACION);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);*/

        Intent intent = new Intent(getActivity(), RevocacionActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void initStartActivityDesembolsar(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(getActivity(), DesembolsarActivity.class);
        // intent.putExtra(Constantes.EXTRAS_TERMINOS_CONDICIONES, Constantes.TERMINOS_CONDICIONES_REVOCACION);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void mostrarButtonLevantarRevocacion() {
        buttonRevocacion.setVisibility(View.GONE);
        buttonLevantarRevocacion.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarButtonRevocacion() {
        buttonRevocacion.setVisibility(View.VISIBLE);
        buttonLevantarRevocacion.setVisibility(View.GONE);
    }

    @Override
    public void ocultarTeclado() {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void mostrarButtonRevocacionVisible() {
        buttonRevocacion.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarButtonRevocacionGone() {
        buttonRevocacion.setVisibility(View.GONE);
    }

    @Override
    public void starActivityChat(String tipoEstadoGrupo, String idUsuarioCotizacions, String keyUser, String idPropuestas, String idGrupoChat, String tipoUsuario, String imagenRubro, String nombrePropuesta) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("tipoEstadoGrupo", tipoEstadoGrupo);
        intent.putExtra("idUsuarioPropuesta", idUsuarioCotizacions);
        intent.putExtra("keyUser", keyUser);
        intent.putExtra("idPropuesta", idPropuestas);
        intent.putExtra("idGrupoChat", idGrupoChat);
        intent.putExtra("tipoUsuario", tipoUsuario);
        intent.putExtra("imagenRubro", imagenRubro);
        intent.putExtra("nombrePropuesta", nombrePropuesta);
        startActivity(intent);
    }

    @Override
    public void deshabilitarButtonAceptarCotiza() {
        Log.d(TAG, "deshabilitarButtonAceptarCotiza");
        /*final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            buttonAceptar.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.btn_bg_disable));
          //  buttonAceptar.set ContextCompat.getDrawable(context, R.drawable.ready)
        } else {
            buttonAceptar.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.btn_bg_disable));
        }*/

        /*Drawable background = getResources().getDrawable(R.drawable.btn_bg_disable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            buttonAceptar.setBackground(background);
        }else{
            Log.d(TAG,"asdasddad");
            buttonAceptar.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.btn_bg_disable));
        }*/
        buttonAceptar.setBackgroundResource(R.drawable.btn_bg_disable);
        buttonAceptar.setEnabled(false);
    }

    @Override
    public void habilitarButtonAceptarCotiza() {
        Log.d(TAG, "habilitarButtonAceptarCotiza");
        buttonAceptar.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonEnviarMensaje() {
        buttonEnviarMensaje.setBackgroundResource(R.drawable.btn_bg_disable);
        buttonEnviarMensaje.setEnabled(false);
    }

    @Override
    public void habilitarButtonEnviarMensaje() {
        buttonEnviarMensaje.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonRevocacion() {
        buttonRevocacion.setBackgroundResource(R.drawable.btn_bg_disable);
        buttonRevocacion.setEnabled(false);
    }

    @Override
    public void habilitarButtonRevocacion() {
        buttonRevocacion.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonDesembolsar() {
        buttonDesembolsar.setBackgroundResource(R.drawable.btn_bg_disable);
        buttonDesembolsar.setEnabled(false);
    }

    @Override
    public void habilitarButtonDesembolsar() {
        buttonDesembolsar.setEnabled(true);
    }

    @Override
    public void mostrarBotonReportePago() {
        btnReportePago.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarBotonReportePago() {
        btnReportePago.setVisibility(View.GONE);
    }

    @Override
    public void deshabilitarButtonReportePago() {
        btnReportePago.setEnabled(false);
    }

    @Override
    public void habilitarButtonReportePago() {
        btnReportePago.setEnabled(true);
    }

    @Override
    public void habilidatButtonLevantarRevocacion() {
        buttonLevantarRevocacion.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonLevantarRevocacion() {
        buttonLevantarRevocacion.setEnabled(false);
    }

    @Override
    public void mostrarButtonVerRepuestaRevocada() {
        buttonVerRepuestaRevocada.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarButtonVerRepuestaRevocada() {
        buttonVerRepuestaRevocada.setVisibility(View.GONE);
    }

    @Override
    public void habilitarButtonVerRepuestaRevocada() {
        buttonVerRepuestaRevocada.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonVerRepuestaRevocada() {
        buttonVerRepuestaRevocada.setBackgroundResource(R.drawable.btn_bg_disable);
        buttonVerRepuestaRevocada.setEnabled(false);
    }

    @Override
    public void initStartActivityReportePago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(getActivity(), CulquiActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void initStartActivityReportePagoPagado(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(getActivity(), ReportePagoActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void initStartActivityRespuestaRevocada(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        String tipousuario = "cliente";
        Intent intent = new Intent(getActivity(), RespuestaRevocacionActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        intent.putExtra("tipousuario", tipousuario);
        startActivity(intent);
    }

    @Override
    public void mostrarDialogConfirmacion(String mensaje, final DetallesCotizacionUi detallesCotizacionUi, final CotizacionesUi cotizacionesUi) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Confirmacion")
                .setIcon(R.mipmap.ic_revocacion_cliente)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();

                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        presenter.onClickCoonfirmarLevantarRevoca(detallesCotizacionUi, cotizacionesUi);
                        //presenter.onClickRenuevaDatosGenerales(inputValidation.isNetworkAvailable());
                        // new InternetCheck(splashActivity,null);
                    }
                }).show();
    }

    @Override
    public void mostrarProgressDialog(String mensaje) {
        progressDialog.setMessage(mensaje);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void initStartActivityMenuCliente() {
        Intent intent = new Intent(getActivity(), MenuClienteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void mostraButtonLevantarRevocacion() {
        buttonLevantarRevocacion.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarButtoLevantarRevocacion() {
        buttonLevantarRevocacion.setVisibility(View.GONE);
    }

    @Override
    public void initStartActivityTipoPago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(getActivity(), TipoPagoActivity.class);
        intent.putExtra("montoPagar",cotizacionesUi.getMonto());
        /*intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);*/
        startActivity(intent);
    }

    @OnClick({R.id.btnAceptar, R.id.btnDesembolsar, R.id.btnEnviarRevocacion,
            R.id.btnLevantarRevocacion, R.id.btnEnviarMensaje, R.id.btnReportePago,
            R.id.btnVerRespuesta})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnAceptar:
                presenter.onClickAceptart();
                break;
            case R.id.btnDesembolsar:
                presenter.onClickDesembolsar();
                break;
            case R.id.btnEnviarRevocacion:
                presenter.onClickEnviarRevocacion();
                break;
            case R.id.btnEnviarMensaje:
                presenter.onClickMensaje();
                break;
            case R.id.btnReportePago:
                presenter.onClickReportePago();
                break;
            case R.id.btnLevantarRevocacion:
                presenter.onClickLevantarRevocacion();
                break;
            case R.id.btnVerRespuesta:
                presenter.onClickBtnVerRespuesta();
                break;
        }
    }
}
