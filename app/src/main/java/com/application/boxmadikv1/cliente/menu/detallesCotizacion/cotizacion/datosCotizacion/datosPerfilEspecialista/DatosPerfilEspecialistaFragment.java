package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.adapter.DatosPerfilAdapter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.calificacion.CalificacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource.DatosPerfilEspecialistaRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.dataSource.remote.DatosPerfilEspecialistaRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad.DatosPropuestaUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.listener.DatosPerfilListener;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.useCase.ListaComentarios;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;


import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.CursosDialogFragment;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource.CursosDialogRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource.remote.CursosDialogRemote;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.useCase.ListaCursos;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DatosPerfilEspecialistaFragment extends BaseFragment<DatosPerfilEspecialistaView, DatosPerfilEspecialistaPresenter> implements DatosPerfilEspecialistaView, DatosPerfilListener {

    public static final String TAG = DatosPerfilEspecialistaFragment.class.getSimpleName();

    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.imgProfile)
    CircleImageView circleImageViewPerfil;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;

    @BindView(R.id.textView15)
    TextView textViewPendiente;
    @BindView(R.id.textView17)
    TextView textViewFinalizadas;
    @BindView(R.id.textView16)
    TextView textViewAceptadas;

    @BindView(R.id.id_banderaRight)
    ImageView imageViewPrimero;
    @BindView(R.id.id_bandera)
    ImageView imageViewSegundo;

    @BindView(R.id.buttonCalificar)
    Button buttonCalificar;

    @BindView(R.id.fabDerecha)
    ImageView imageViewFabDerecha;
    @BindView(R.id.fabIzquierda)
    ImageView imageViewFabIzquierda;




    private DatosPerfilAdapter datosPerfilAdapter;

    public static DatosPerfilEspecialistaFragment newInstance(DetallesCotizacionUi detallesCotizacionUi,
                                                              CotizacionesUi cotizacionesUi) {
        Bundle args = new Bundle();
        DatosPerfilEspecialistaFragment fragment = new DatosPerfilEspecialistaFragment();
        args.putParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        args.putParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected DatosPerfilEspecialistaPresenter getPresenter() {
        DatosPerfilEspecialistaRepository datosPerfilEspecialistaRepository = new DatosPerfilEspecialistaRepository(new DatosPerfilEspecialistaRemote());
        CursosDialogRepository cursosDialogRepository = new CursosDialogRepository(new CursosDialogRemote());
        return new DatosPerfilEspecialistaPresenterImpl(new UseCaseHandler(
                new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListaComentarios(datosPerfilEspecialistaRepository),
                new ListaCursos(cursosDialogRepository)
                );
    }

    @Override
    protected DatosPerfilEspecialistaView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_datos_perfil_especialista_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);

    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        datosPerfilAdapter = new DatosPerfilAdapter(new ArrayList<DatosPropuestaUi>(), this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(datosPerfilAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarListaComentarios(List<DatosPropuestaUi> resultado) {
        datosPerfilAdapter.setMostrarLista(resultado);
    }

    @Override
    public void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        textViewNombre.setText(cotizacionesUi.getNombreEspecialista().toUpperCase());
        textViewPendiente.setText(cotizacionesUi.getCotiPendiente());
        textViewFinalizadas.setText(cotizacionesUi.getCotiFinalizado());
        textViewAceptadas.setText(cotizacionesUi.getCotiAceptado());
        Glide.with(getActivity())
                .load(cotizacionesUi.getImagen())
                .into(circleImageViewPerfil);
        Glide.with(getActivity())
                .load(cotizacionesUi.getPaisImagen())
                .into(imageViewPrimero);
        Glide.with(getActivity())
                .load(cotizacionesUi.getPaisImagen())
                .into(imageViewSegundo);
        if (cotizacionesUi.getPuntuacion() == null || cotizacionesUi.getPuntuacion().equals("null") || cotizacionesUi.getPuntuacion().isEmpty()) {
            ratingBar.setRating(0);
        } else {
            float puntuacion = Float.parseFloat(cotizacionesUi.getPuntuacion());
            ratingBar.setRating(puntuacion);
        }

    }


    @Override
    public void mostrarDialogCursos(String idUsuarioCotizacion) {
       /* CursosDialogFragment editNameDialogFragment = CursosDialogFragment.newInstance(idUsuarioCotizacion);
        editNameDialogFragment.show(getFragmentManager(), "fragment_edit_name");*/
       presenter.onMostrarListaCursos(idUsuarioCotizacion);
    }

    @Override
    public void mostrarButtonCalificar() {
        buttonCalificar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarButtonCalificar() {
        buttonCalificar.setVisibility(View.GONE);
    }

    @Override
    public void initStartActivityCalificacion(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(getActivity(), CalificacionActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA,detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI,cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void mostrarBotonDerecho() {
        imageViewFabDerecha.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarBotonDerecho() {
        imageViewFabDerecha.setVisibility(View.GONE);
    }

    @Override
    public void mostrarBotonIzquierdo() {
        imageViewFabIzquierda.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarBotonIzquierdo() {
        imageViewFabIzquierda.setVisibility(View.GONE);
    }

    @Override
    public void mostrarListaCursosDialog(List<CursosUi> cursosUiList) {
        CursosDialogFragment editNameDialogFragment = CursosDialogFragment.newInstance(cursosUiList);
        editNameDialogFragment.show(getFragmentManager(), "fragment_edit_name");
    }

//    @Override
//    public void mostrarListaCursos(List<CursosUi> cursosUiList) {
//        datosPerfilAdapter.MostrarListaCursos(cursosUiList);
//    }


    @Override
    public void onClickButton(DatosPropuestaUi datosPropuestaUi) {
        presenter.onDatosCursos(datosPropuestaUi);

        /*CursosDialogFragment editNameDialogFragment = CursosDialogFragment.newInstance(datosPropuestaUi.getKeyUser());
        editNameDialogFragment.show(getFragmentManager(), "fragment_edit_name");*/

    }

    @OnClick({R.id.textViewCursos,R.id.buttonCalificar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.textViewCursos:
                presenter.onClickCursos();
                break;
            case R.id.buttonCalificar:
                presenter.onClickCalificar();
                break;
        }
    }
}
