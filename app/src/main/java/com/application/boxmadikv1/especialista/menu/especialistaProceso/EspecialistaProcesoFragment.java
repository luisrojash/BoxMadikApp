package com.application.boxmadikv1.especialista.menu.especialistaProceso;

import android.os.Bundle;

import com.application.boxmadikv1.especialista.menu.abstracto.EspecAbstractFragment;

public class EspecialistaProcesoFragment extends EspecAbstractFragment{



    public static EspecialistaProcesoFragment newInstance(String keyUser,String codigoPais, String tipoEstadoEspe) {
        Bundle bundle = new Bundle();
        EspecialistaProcesoFragment fragment = new EspecialistaProcesoFragment();
        bundle.putString("keyUser", keyUser);
        bundle.putString("codigoPais",codigoPais);
        bundle.putString("tipoEstadoEspe", tipoEstadoEspe);
        fragment.setArguments(bundle);
        return fragment;
    }

        /*extends BaseFragment<EspecialistaProcesoView,EspecialistaProcesoPresenter>implements EspecialistaProcesoView {

   public static final String TAG = EspecialistaProcesoFragment.class.getSimpleName();


    @BindView(R.id.recicladorProcesoEspecializados)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    public static EspecialistaProcesoFragment newInstance(Bundle args) {
        EspecialistaProcesoFragment fragment = new EspecialistaProcesoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected EspecialistaProcesoPresenter getPresenter() {
        return new EspecialistaProcesoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources());
    }

    @Override
    protected EspecialistaProcesoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_proceso_fragment,container,false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }*/
}
