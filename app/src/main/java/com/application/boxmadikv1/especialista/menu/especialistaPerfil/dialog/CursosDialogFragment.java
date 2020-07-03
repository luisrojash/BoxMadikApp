package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog;

import android.app.Dialog;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.adapter.CursosDialogAdapter;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource.CursosDialogRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource.remote.CursosDialogRemote;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CursosDialogFragment extends DialogFragment implements CursosDialogView {


    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private CursosDialogAdapter cursosDialogAdapter;

    public CursosDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static CursosDialogFragment newInstance(List<CursosUi> cursosUiList) {
        CursosDialogFragment frag = new CursosDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("cursosUiList",Parcels.wrap(cursosUiList));
        frag.setArguments(args);
        return frag;
    }


    CursosDialogPresenter presenter;

    @Override
    public void setPresenter(CursosDialogPresenter presenter) {
        presenter.onCreate();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CursosDialogRepository cursosDialogRepository = CursosDialogRepository.getmInstance(new CursosDialogRemote());
        presenter = new CursosDialogPresenterImpl(
             //   new ListaCursos(cursosDialogRepository),
                new UseCaseHandler(new UseCaseThreadPoolScheduler())
        );
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

        View view = inflater.inflate(R.layout.fragment_cursos_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        initAdapter();
        return view;
        //return inflater.inflate(R.layout.fragment_cursos_dialog, container);
    }

    private void initAdapter() {
        cursosDialogAdapter = new CursosDialogAdapter(new ArrayList<CursosUi>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cursosDialogAdapter);
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

    @OnClick(R.id.imageView6)
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.imageView6:
                ocultarDialog();
                break;
        }
    }


    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarListaCursos(List<CursosUi> cursosUiList) {
        cursosDialogAdapter.mostrarLista(cursosUiList);

    }

    @Override
    public void ocultarDialog() {
        dismiss();
    }
}
