package com.application.boxmadikv1.cliente.menu.clienteCancelado;

import android.os.Bundle;

import com.application.boxmadikv1.cliente.menu.abstracto.ClientAbstractFragment;

public class ClienteCanceladoFragment extends ClientAbstractFragment{


    public static ClienteCanceladoFragment newInstance(String keyUser,String codigoPais, String tipoEstado) {
        Bundle args = new Bundle();
        ClienteCanceladoFragment fragment = new ClienteCanceladoFragment();
        args.putString("keyUser", keyUser);
        args.putString("codigoPais",codigoPais);
        args.putString("tiposEstadoCliente", tipoEstado);
        fragment.setArguments(args);
        return fragment;
    }


        /*extends BaseFragment<ClienteCanceladoView, ClienteCanceladoPresenter> implements ClienteCanceladoView {

    public static final String TAG = ClienteCanceladoFragment.class.getSimpleName();

    @BindView(R.id.recicladorCanceladoCliente)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;




    public ClienteCanceladoFragment() {
    }

    public static ClienteCanceladoFragment newInstance(String keyUser) {
        Bundle args = new Bundle();
        ClienteCanceladoFragment fragment = new ClienteCanceladoFragment();
        args.putString("keyUser", keyUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ClienteCanceladoPresenter getPresenter() {
        return new ClienteCanceladoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ClienteCanceladoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_cancelado_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }*/
}
