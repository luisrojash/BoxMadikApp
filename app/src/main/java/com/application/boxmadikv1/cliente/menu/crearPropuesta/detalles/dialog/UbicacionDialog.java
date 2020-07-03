package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarDepartamentoAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarDistritoAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarProvinciaAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.UbicacionRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.local.UbicacionLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.listener.UbicacionListener;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.useCase.ListaDepartamento;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.useCase.ListaDistrito;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.useCase.ListaProvincia;
import com.application.boxmadikv1.dao.InjectorUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UbicacionDialog extends DialogFragment implements UbicacionView {

    @BindView(R.id.autoCompleteTextViewDepartamento)
    AutoCompleteTextView autoCompleteTextViewDepartamento;
    @BindView(R.id.autoCompleteTextViewProvincia)
    AutoCompleteTextView autoCompleteTextViewProvincia;
    @BindView(R.id.autoCompleteTextViewDistrito)
    AutoCompleteTextView autoCompleteTextViewDistrito;
    private UbicacionListener mListener;

    public static UbicacionDialog newInstance(String codigoPais) {
        UbicacionDialog frag = new UbicacionDialog();
        Bundle args = new Bundle();
        args.putString("codigoPais", codigoPais);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UbicacionListener) {
            //init the listener
            mListener = (UbicacionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }

    UbicacionPresenter presenter;
    Unbinder unbinder;

    @Override
    public void setPresenter(UbicacionPresenter presenter) {
        presenter.onCreate();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UbicacionRepository ubicacionRepository = UbicacionRepository.getInstance(new UbicacionLocal(
                InjectorUtils.provideDepartamentoDao(),
                InjectorUtils.provideProvinciaDao(),
                InjectorUtils.provideDistritoDao()
        ));
        presenter = new UbicacionPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new ListaDepartamento(ubicacionRepository),
                new ListaProvincia(ubicacionRepository),
                new ListaDistrito(ubicacionRepository));
        if (presenter != null) {
            setPresenter(presenter);
            presenter.attachView(this);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // String title = "Lista Cursos Estudiados";
        // getDialog().setTitle(title);
       /* getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);*/
        Bundle bundle = getArguments();
        if (presenter != null) presenter.onExtras(bundle);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) presenter.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        this.getDialog().getWindow();
        if (presenter != null) presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) presenter.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_ubicacion, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        initAdapter();
        return view;
        //return inflater.inflate(R.layout.fragment_cursos_dialog, container);
    }

    private void initAdapter() {
        /*cursosDialogAdapter = new CursosDialogAdapter(new ArrayList<CursosUi>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cursosDialogAdapter);*/
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) presenter.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (presenter != null) presenter.onDestroy();
    }

    @Override
    public void mostrarListaTipoDepartamento(List<TipoDepartamentoUi> tipoDepartamentoUis) {
        BuscarDepartamentoAdapter buscarDepartamentoAdapter = new BuscarDepartamentoAdapter(getActivity(), tipoDepartamentoUis);
        autoCompleteTextViewDepartamento.setAdapter(buscarDepartamentoAdapter);

        autoCompleteTextViewDepartamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TipoDepartamentoUi tipoDepartamentoUi = (TipoDepartamentoUi) parent.getAdapter().getItem(position);
                presenter.onSpinnerTipoDepartamento(tipoDepartamentoUi);
            }
        });
    }

    @Override
    public void mostrarTextViewDepartamento(String s) {
        autoCompleteTextViewDepartamento.setText(s);
    }

    @Override
    public void mostrarListaTipoProvincia(List<TipoProvinciaUi> tipoProvinciaUis) {
        BuscarProvinciaAdapter buscarProvinciaAdapter = new BuscarProvinciaAdapter(getActivity(), tipoProvinciaUis);
        autoCompleteTextViewProvincia.setAdapter(buscarProvinciaAdapter);
        autoCompleteTextViewProvincia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoProvinciaUi tipoProvinciaUi = (TipoProvinciaUi) parent.getAdapter().getItem(position);
                presenter.onSpinnerTipoProvincia(tipoProvinciaUi);
            }
        });
    }

    @Override
    public void mostrarTextViewProvincia(String s) {
        autoCompleteTextViewProvincia.setText(s);
    }

    @Override
    public void mostrarListaTipoDistrito(List<TipoDistritoUi> tipoDistritoUis) {
        BuscarDistritoAdapter buscarDistritoAdapter = new BuscarDistritoAdapter(getActivity(), tipoDistritoUis);
        autoCompleteTextViewDistrito.setAdapter(buscarDistritoAdapter);
        autoCompleteTextViewDistrito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoDistritoUi tipoDistritoUi = (TipoDistritoUi) parent.getAdapter().getItem(position);
                presenter.onSpinnerTipoDistrito(tipoDistritoUi);
                //Log.d(TAG, "tipoDistritoUi : " + position);
            }
        });
    }

    @Override
    public void mostrarTextViewDistrito(String s) {
        autoCompleteTextViewDistrito.setText(s);
    }

    @Override
    public void borrarTextDepartamento() {
        autoCompleteTextViewDepartamento.setText(null);
        autoCompleteTextViewDepartamento.setEnabled(true);
    }

    @Override
    public void borrarTextProvincia() {
        autoCompleteTextViewProvincia.setText(null);
        autoCompleteTextViewProvincia.setEnabled(true);
    }

    @Override
    public void borrarTextDistrito() {
        autoCompleteTextViewDistrito.setText(null);
        autoCompleteTextViewDistrito.setEnabled(true);
    }

    @Override
    public void mostrarMensajeErrorAutoCompleteDepartamentoError(String mensaje) {
        autoCompleteTextViewDepartamento.setError(mensaje);
    }

    @Override
    public void mostrarMensajeErrorAutoCompleteProvinciaError(String mensaje) {
        autoCompleteTextViewProvincia.setError(mensaje);
    }

    @Override
    public void mostrarMensajeErrorAutoCompleteDistritoError(String mensaje) {
        autoCompleteTextViewDistrito.setError(mensaje);
    }

    @Override
    public void deshabilitarAutoCompleteTextViewProvincia() {
        autoCompleteTextViewProvincia.setEnabled(false);
    }

    @Override
    public void habilitarAutoCompleteTextViewProvincia() {
        autoCompleteTextViewProvincia.setEnabled(true);
    }

    @Override
    public void deshabilitarAutoCompleteTextViewDistrito() {
        autoCompleteTextViewDistrito.setEnabled(false);
    }

    @Override
    public void habilitarAutoCompleteTextViewDistrito() {
        autoCompleteTextViewDistrito.setEnabled(true);
    }

    @Override
    public void enviarDataPadreActivity(String codigoDepartamento, String nombreDepartamento, String codigoProvincia, String nombreProvincia, String codigoDistrito, String nombreDistrito) {
        dismiss();
        mListener.onGuardarUbicacion(codigoDepartamento, nombreDepartamento, codigoProvincia, nombreProvincia, codigoDistrito, nombreDistrito);
    }



    @OnClick({R.id.imageView6, R.id.btnRegistrar,R.id.imageViewCloseDepart,R.id.imageViewCloseProvincia,R.id.imageViewCloseDistrito})
    public void Onclick(View view) {
        int itemid = view.getId();
        switch (itemid) {
            case R.id.imageView6:
                dismiss();
                break;
            case R.id.btnRegistrar:
                presenter.onGuardarUbicacion();
                break;
            case R.id.imageViewCloseDepart:
                presenter.onClickCloseDepartamento();
                break;
            case R.id.imageViewCloseProvincia:
                presenter.onClickCloseProvincia();
                break;
            case R.id.imageViewCloseDistrito:
                presenter.onClickCloseDistrito();
                break;
        }
    }
}
