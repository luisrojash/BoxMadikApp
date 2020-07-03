package com.application.boxmadikv1.especialista.menu.especialistaCancelado;

import android.os.Bundle;

import com.application.boxmadikv1.especialista.menu.abstracto.EspecAbstractFragment;

public class EspecialistaCanceladoFragment extends EspecAbstractFragment {

    public static EspecialistaCanceladoFragment newInstance(String keyUser,String codigoPais, String tipoEstadoEspe) {
        Bundle bundle = new Bundle();
        EspecialistaCanceladoFragment fragment = new EspecialistaCanceladoFragment();
        bundle.putString("keyUser", keyUser);
        bundle.putString("codigoPais",codigoPais);
        bundle.putString("tipoEstadoEspe", tipoEstadoEspe);
        fragment.setArguments(bundle);
        return fragment;
    }

        /*extends BaseFragment<EspecialistaCanceladoView, EspecialistaCanceladoPresenter> implements EspecialistaCanceladoView {

    public static final String TAG = EspecialistaCanceladoFragment.class.getSimpleName();


    @BindView(R.id.recicladorCanceladoEspecialista)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    public static EspecialistaCanceladoFragment newInstance(Bundle args) {
        EspecialistaCanceladoFragment fragment = new EspecialistaCanceladoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected EspecialistaCanceladoPresenter getPresenter() {
        return new EspecialistaCanceladoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected EspecialistaCanceladoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_cancelado_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }*/
}
