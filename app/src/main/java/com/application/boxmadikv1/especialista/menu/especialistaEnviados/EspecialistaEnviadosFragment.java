package com.application.boxmadikv1.especialista.menu.especialistaEnviados;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.boxmadikv1.especialista.menu.abstracto.EspecAbstractFragment;

public class EspecialistaEnviadosFragment extends EspecAbstractFragment {


    public static EspecialistaEnviadosFragment newInstance(String keyUser,String codigoPais, String tipoEstadoEspe) {
        Bundle bundle = new Bundle();
        EspecialistaEnviadosFragment fragment = new EspecialistaEnviadosFragment();
        bundle.putString("keyUser", keyUser);
        bundle.putString("codigoPais",codigoPais);
        bundle.putString("tipoEstadoEspe", tipoEstadoEspe);
        fragment.setArguments(bundle);
        return fragment;
    }

       /*
        extends BaseFragment<EspecialistaEnviadosView, EspecialistaEnviadosPresenter> implements EspecialistaEnviadosView, EspecialistaEnviadoListener {

    public static final String TAG = EspecialistaEnviadosFragment.class.getSimpleName();

    @BindView(R.id.recicladorEnviadosEspecialista)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    EspecialistaEnviadosAdapter clienteTodosAdapter;


    public static EspecialistaEnviadosFragment newInstance(String keyUser) {
        Bundle bundle = new Bundle();
        EspecialistaEnviadosFragment fragment = new EspecialistaEnviadosFragment();
        bundle.putString("keyUser", keyUser);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected EspecialistaEnviadosPresenter getPresenter() {
        EspecialistaEnviadosRepository especialistaEnviadosRepository = EspecialistaEnviadosRepository.getmInstance(new EspecialistaEnviadosLocal(
                new RubroDaoImpl(),
                new RangoPrecioDaoImpl(),
                new OficioDaoImpl(),
                new RangoDiasDaoImpl(),
                new RangoTurnoDaoImpl()
        ), new EspecialistaEnviadosRemote());
        return new EspecialistaEnviadosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListarEspecialistaEnviados(especialistaEnviadosRepository),
                new ListaCotizaciones(especialistaEnviadosRepository),
                new EliminarCotizacion(especialistaEnviadosRepository));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected EspecialistaEnviadosView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_enviados_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        clienteTodosAdapter = new EspecialistaEnviadosAdapter(new ArrayList<EspecialistaEnviadosUi>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(getContext()));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(clienteTodosAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostraListaEspecialistaEnviados(List<EspecialistaEnviadosUi> especialistaEnviadosUiList) {
        clienteTodosAdapter.mostrarLista(especialistaEnviadosUiList);
    }

    @Override
    public void mostrarMensaje(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarDialogConfirmacionDelete(final EspecialistaEnviadosUi especialistaEnviadosUi,String mensaje) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Eliminar Cotización")
               // .setIcon(R.drawable.ic_delete_icon)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        presenter.onAceptarDeleteItem(especialistaEnviadosUi);
                    }
                }).show();
    }

    @Override
    public void eliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi) {
        clienteTodosAdapter.eliminarItem(especialistaEnviadosUi);
    }

    @Override
    public void startActivityPerfilPropuesta(ItemUi itemUi) {
        Intent intent = new Intent(getActivity(), PerfilPropuestaActivity.class);
        intent.putExtra("itemUi", itemUi);
        getActivity().startActivity(intent);
    }

    @Override
    public void onClickEliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi) {
        presenter.onClickEliminarItem(especialistaEnviadosUi);
    }

    @Override
    public void onClickItem(EspecialistaEnviadosUi especialistaEnviadosUi) {
        presenter.onClickItem(especialistaEnviadosUi);
    }*/
}
