package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.adapter.EspecAdapter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource.DetallesDescripcionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource.remote.DetallesDescripcionRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.entidad.EspecialidadUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.useCase.ObtenerImagen;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetallesDescripcionFragment extends BaseFragment<DetallesDescripcionView, DetallesDescripcionPresenter> implements DetallesDescripcionView {

    public static final String TAG = DetallesDescripcionFragment.class.getSimpleName();


    @BindView(R.id.textviewPromedio)
    TextView textViewPromedio;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditText;

    @BindView(R.id.textViewRangoDias)
    TextView textViewRangoDias;
    @BindView(R.id.textviewRangoTurno)
    TextView textViewRangoTurno;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewDescripcion)
    TextView textViewNombeRubro;
    @BindView(R.id.textViewDescripcionOficio)
    TextView textViewNombreOficio;

    @BindView(R.id.textViewEmptyImagenes)
    TextView textViewImagenVacia;
    @BindView(R.id.imageViewPrimero)
    ImageView imageViewPrimero;
    @BindView(R.id.imageViewSegundo)
    ImageView imageViewSegundo;

    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    @BindView(R.id.textViewEmpty)
    TextView textViewRecicladorVacia;

    @BindView(R.id.btnCountCotizacion2)
    Button btnCountCotizacion2;
    private EspecAdapter especAdapter;

    public static DetallesDescripcionFragment newInstance(Bundle args) {
        DetallesDescripcionFragment fragment = new DetallesDescripcionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected DetallesDescripcionPresenter getPresenter() {
        DetallesDescripcionRepository detallesDescripcionRepository = DetallesDescripcionRepository.getmInstance(new DetallesDescripcionRemote());
        return new DetallesDescripcionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerImagen(detallesDescripcionRepository));
    }

    @Override
    protected DetallesDescripcionView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_pendiente_detalles_descripcion_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        especAdapter = new EspecAdapter(new ArrayList<EspecialidadUi>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(especAdapter);
    }

    @Override
    public void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi) {
        //ocultahint();
        //textInputEditText.setHint("detalles del servicio");
        textInputEditText.setEnabled(false);
        textViewPromedio.setText("S/. " + detallesCotizacionUi.getCostoPromedio());
        btnCountCotizacion2.setText(detallesCotizacionUi.getNumeroCotizacion() + "");
        textInputEditText.setText(detallesCotizacionUi.getDetallesPropuesta());
        textViewRangoDias.setText(detallesCotizacionUi.getDescripcionRangoDias());
        textViewRangoTurno.setText(detallesCotizacionUi.getDescripcionRangoTurno());
        //.diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(getActivity())
                .load(detallesCotizacionUi.getImageRubro())
                .into(imageViewRubro);
        textViewNombeRubro.setText(detallesCotizacionUi.getNombreRubro());
        textViewNombreOficio.setText(detallesCotizacionUi.getNombreOficio());
    }

    private void ocultahint() {
        textInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    textInputEditText.setHint("");
                else
                    textInputEditText.setHint("");
            }
        });
    }

    @Override
    public void ocultarTeclado() {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void mostrarTextoImagenVacio(String s) {
        textViewImagenVacia.setVisibility(View.VISIBLE);
        textViewImagenVacia.setText(s);
        imageViewPrimero.setVisibility(View.GONE);
        imageViewSegundo.setVisibility(View.GONE);
    }

    @Override
    public void mostrarImagenSegunda(String segundaImageUri) {
        textViewImagenVacia.setVisibility(View.GONE);
        imageViewPrimero.setVisibility(View.VISIBLE);
        imageViewSegundo.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).load(segundaImageUri).into(imageViewSegundo);
    }

    @Override
    public void mostrarImagenPrimera(String primeraImageUri) {
        textViewImagenVacia.setVisibility(View.GONE);
        imageViewPrimero.setVisibility(View.VISIBLE);
        imageViewSegundo.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).load(primeraImageUri).into(imageViewPrimero);
    }

    @Override
    public void mostrarTodasImagenes(String primeraImageUri, String segundaImageUri) {
        textViewImagenVacia.setVisibility(View.GONE);
        imageViewPrimero.setVisibility(View.VISIBLE);
        imageViewSegundo.setVisibility(View.VISIBLE);
        Glide.with(getActivity()).load(segundaImageUri).into(imageViewSegundo);
        Glide.with(getActivity()).load(primeraImageUri).into(imageViewPrimero);
    }

    @Override
    public void mostrarTextoVacio(String no_tiene_especialidades) {
        textViewRecicladorVacia.setVisibility(View.VISIBLE);
        textViewRecicladorVacia.setText(no_tiene_especialidades);
    }

    @Override
    public void mostrarListaEspec(List<EspecialidadUi> especialidadUis) {
        especAdapter.mostrarLista(especialidadUis);
    }

    @Override
    public void ocultarTexto() {
        textViewRecicladorVacia.setVisibility(View.GONE);
    }
}
