package com.application.boxmadikv1.especialista.menu.especialistaFinalizado;

import android.os.Bundle;

import com.application.boxmadikv1.especialista.menu.abstracto.EspecAbstractFragment;

public class EspecialistaFinalizadoFragment extends EspecAbstractFragment{



    public static EspecialistaFinalizadoFragment newInstance(String keyUser,String codigoPais, String tipoEstadoEspe) {
        Bundle bundle = new Bundle();
        EspecialistaFinalizadoFragment fragment = new EspecialistaFinalizadoFragment();
        bundle.putString("keyUser", keyUser);
        bundle.putString("codigoPais",codigoPais);
        bundle.putString("tipoEstadoEspe", tipoEstadoEspe);
        fragment.setArguments(bundle);
        return fragment;
    }

        /*extends BaseFragment<EspecialistaFinalizadoView, EspecialistaFinalizadoPresenter> implements EspecialistaFinalizadoView {

    public static final String TAG = EspecialistaFinalizadoFragment.class.getSimpleName();

    @BindView(R.id.recicladorFinalizadoEspecializados)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    public static EspecialistaFinalizadoFragment newInstance(Bundle args) {
        EspecialistaFinalizadoFragment fragment = new EspecialistaFinalizadoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected EspecialistaFinalizadoPresenter getPresenter() {
        return new EspecialistaFinalizadoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected EspecialistaFinalizadoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_finalizado_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }*/
}
