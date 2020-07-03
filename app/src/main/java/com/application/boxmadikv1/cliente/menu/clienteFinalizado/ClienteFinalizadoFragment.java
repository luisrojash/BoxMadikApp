package com.application.boxmadikv1.cliente.menu.clienteFinalizado;

import android.os.Bundle;

import com.application.boxmadikv1.cliente.menu.abstracto.ClientAbstractFragment;

public class ClienteFinalizadoFragment  extends ClientAbstractFragment{




    public static ClienteFinalizadoFragment newInstance(String keyUser,String codigoPais, String tipoEstado) {
        Bundle args = new Bundle();
        ClienteFinalizadoFragment fragment = new ClienteFinalizadoFragment();
        args.putString("keyUser", keyUser);
        args.putString("codigoPais",codigoPais);
        args.putString("tiposEstadoCliente", tipoEstado);
        fragment.setArguments(args);
        return fragment;
    }

        /*extends BaseFragment<ClienteFinalizadoView, ClienteFinalizadoPresenter> implements ClienteFinalizadoView {

    public static final String TAG = ClienteFinalizadoFragment.class.getSimpleName();
    @BindView(R.id.recicladorFinalizadoCliente)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static ClienteFinalizadoFragment newInstance(String keyUser) {
        Bundle args = new Bundle();
        ClienteFinalizadoFragment fragment = new ClienteFinalizadoFragment();
        args.putString("keyUser", keyUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ClienteFinalizadoPresenter getPresenter() {
        return null;
    }

    @Override
    protected ClienteFinalizadoView getBaseView() {
        return null;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_finalizado_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }*/
}
