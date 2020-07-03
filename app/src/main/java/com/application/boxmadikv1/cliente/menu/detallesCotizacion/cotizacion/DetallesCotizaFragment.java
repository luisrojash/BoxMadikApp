package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.adapter.DetallesCotizaAdapter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource.DetallesCotizaRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource.remote.DetallesCotizaRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.DatosCotizacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.DesembolsarActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.listener.DetallesCotizaListener;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.useCase.ObtenerCotizacionCliente;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.listener.DetallesListener;
import com.application.boxmadikv1.extra.InternetCheck;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetallesCotizaFragment extends BaseFragment<DetallesCotizaView, DetallesCotizaPresenter> implements DetallesCotizaView, DetallesCotizaListener, InternetCheck.Consumer<CotizacionesUi> {

    public static String EXTRA_DETALLES_COTIZA_COTIZACIONESUI = "cotizacionesUi";

    public static final String TAG = DetallesCotizaFragment.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;

    private DetallesCotizaAdapter detallesCotizaAdapter;
    private DetallesListener detallesListener;

    public static DetallesCotizaFragment newInstance(Bundle args) {
        DetallesCotizaFragment fragment = new DetallesCotizaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected DetallesCotizaPresenter getPresenter() {
        DetallesCotizaRepository detallesCotizaRepository = DetallesCotizaRepository.getmInstance(new DetallesCotizaRemote());
        return new DetallesCotizaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerCotizacionCliente(detallesCotizaRepository));
    }

    @Override
    protected DetallesCotizaView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_pendiente_detalles_cotiza_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarMensaje(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarListaCotiza(List<CotizacionesUi> cotizacionesUis) {
        detallesCotizaAdapter.mostrarLista(cotizacionesUis);
    }

    @Override
    public void enviarListaActividadPrincipal(List<CotizacionesUi> cotizacionesUis) {
        detallesListener.ObternerListaPorEstado(cotizacionesUis);
    }

    @Override
    public void startDatosCotizacionActivity(CotizacionesUi cotizacionesUi, DetallesCotizacionUi detallesCotizacionUi) {
        Intent intent = new Intent(getActivity(), DatosCotizacionActivity.class);
        intent.putExtra(EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        startActivity(intent);
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
    public void initStartActivityCalifica(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(getActivity(), DesembolsarActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA,detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI,cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        detallesCotizaAdapter = new DetallesCotizaAdapter(new ArrayList<CotizacionesUi>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(getContext()));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(detallesCotizaAdapter);
    }

    @Override
    public void onClickDetallesCotiza(CotizacionesUi cotizacionesUi) {
        Object objeto = cotizacionesUi;
        new InternetCheck(this, objeto);
        //presenter.onClickDetalleCotizaciones(cotizacionesUi);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetallesListener) {
            detallesListener = (DetallesListener) context;
        } else {
            throw new ClassCastException("Implementar la Actividad principal");
        }
    }

    @Override
    public void onDetach() {
        detallesListener = null;
        super.onDetach();
    }

    @Override
    public void accept(Boolean internet, CotizacionesUi cotizacionesUi) {
        presenter.onValidateInternet(internet, cotizacionesUi);
    }
}
