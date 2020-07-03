package com.application.boxmadikv1.cliente.menu.abstracto;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.cliente.menu.abstracto.adapter.ClientAbstractAdapter;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.ClientAbstractRepository;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.local.ClientAbstractLocal;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.remote.ClientAbstractRemote;
import com.application.boxmadikv1.cliente.menu.abstracto.entidad.ClienteEstadosUi;
import com.application.boxmadikv1.cliente.menu.abstracto.listener.ClientAbstractListener;
import com.application.boxmadikv1.cliente.menu.abstracto.useCase.ListarClienteEstados;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.DetallesCotizacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.dao.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.application.boxmadikv1.utils.Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA;

public abstract class ClientAbstractFragment extends BaseFragment<ClientAbstractView, ClientAbstractPresenter> implements ClientAbstractView, ClientAbstractListener {

    public static final String TAG = ClientAbstractFragment.class.getSimpleName();

    @BindView(R.id.recicladorTodosCliente)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    ClientAbstractAdapter clientAbstractAdapter;

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ClientAbstractPresenter getPresenter() {
        ClientAbstractRepository clientAbstractRepository = ClientAbstractRepository.getmInstance(new ClientAbstractLocal(
                InjectorUtils.provideRubroDao(),
                InjectorUtils.provideOficioDao(),
                InjectorUtils.provideRangoTurnoDao(),
                InjectorUtils.provideRangoDiasDao(),
                InjectorUtils.provideRangoPrecioDao(),
                InjectorUtils.provideDepartamentoDao(),
                InjectorUtils.provideProvinciaDao(),
                InjectorUtils.provideDistritoDao()
        ), new ClientAbstractRemote());
        return new ClientAbstractPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListarClienteEstados(clientAbstractRepository));
    }

    @Override
    protected ClientAbstractView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_abstract_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        clientAbstractAdapter = new ClientAbstractAdapter(new ArrayList<ClienteEstadosUi>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(getContext()));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(clientAbstractAdapter);
    }


    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }


    @Override
    public void mostraListaClienteEstados(List<ClienteEstadosUi> clienteEstadosUiList) {
        clientAbstractAdapter.mostrarLista(clienteEstadosUiList);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartActivityDetalles(DetallesCotizacionUi detallesCotizacionUi) {
        Intent intent = new Intent(getContext(), DetallesCotizacionActivity.class);
        intent.putExtra(EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        getActivity().startActivity(intent);
    }

    @Override
    public void onClickEstado(ClienteEstadosUi clienteEstadosUi) {
        presenter.onClickEstado(clienteEstadosUi);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter == null) return;
        //if (getArguments() != null) presenter.setExtras(getArguments());
    }

    @Override
    public void mostrarEmpty() {
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarEmpty() {
        textViewEmpty.setVisibility(View.GONE);
    }

    @Override
    public void pintarColorTabs() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void initListener() {

    }
}
