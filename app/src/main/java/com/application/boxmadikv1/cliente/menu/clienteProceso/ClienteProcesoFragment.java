package com.application.boxmadikv1.cliente.menu.clienteProceso;

import android.os.Bundle;

import com.application.boxmadikv1.cliente.menu.abstracto.ClientAbstractFragment;

public class ClienteProcesoFragment extends ClientAbstractFragment {

    public static ClienteProcesoFragment newInstance(String keyUser,String codigoPais, String tipoEstado) {
        Bundle args = new Bundle();
        ClienteProcesoFragment fragment = new ClienteProcesoFragment();
        args.putString("keyUser", keyUser);
        args.putString("codigoPais",codigoPais);
        args.putString("tiposEstadoCliente", tipoEstado);
        fragment.setArguments(args);
        return fragment;
    }

        /*
        extends BaseFragment<ClienteProcesoView, ClienteProcesoPresenter> implements ClienteProcesoView {


    public static final String TAG = ClienteProcesoFragment.class.getSimpleName();


    @BindView(R.id.recicladorProcesoCliente)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static ClienteProcesoFragment newInstance(String keyUser) {
        Bundle args = new Bundle();
        ClienteProcesoFragment fragment = new ClienteProcesoFragment();
        args.putString("keyUser", keyUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ClienteProcesoPresenter getPresenter() {
        return new ClienteProcesoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources());
    }

    @Override
    protected ClienteProcesoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_proceso_fragment,container,false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }*/
}
