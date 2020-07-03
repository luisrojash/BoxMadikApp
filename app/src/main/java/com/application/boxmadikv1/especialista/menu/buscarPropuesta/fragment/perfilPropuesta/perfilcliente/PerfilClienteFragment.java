package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
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
import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.adapter.PerfilClienteAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.calificar.CalificarClienteActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.PerfilClienteRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.remote.PerfilClienteRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad.DatosCliente;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.useCase.ObtenerListaComentarios;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.useCase.ObtenerPerfil;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class PerfilClienteFragment extends BaseFragment<PerfilClienteView, PerfilClientePresenter> implements PerfilClienteView {

    public static final String TAG = PerfilClienteFragment.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.imgProfile)
    ImageView imageViewProfile;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.textViewDireccion)
    TextView textViewDireccion;
    @BindView(R.id.textView15)
    TextView textViewPropuestasPendiente;
    @BindView(R.id.textView17)
    TextView textViewPropuestasFinalizadas;
    @BindView(R.id.textView16)
    TextView textViewPropuestaPagada;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    PerfilClienteAdapter perfilClienteAdapter;
    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;

    @BindView(R.id.buttonCalificar)
    Button buttonCalificar;

    @BindView(R.id.id_banderaRight)
    ImageView imageViewPrimero;
    @BindView(R.id.id_bandera)
    ImageView imageViewSegundo;

    @BindView(R.id.fabDerecha)
    ImageView imageViewFabDerecha;
    @BindView(R.id.fabIzquierda)
    ImageView imageViewFabIzquierda;

    public static PerfilClienteFragment newInstance(Bundle args) {
        PerfilClienteFragment fragment = new PerfilClienteFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected PerfilClientePresenter getPresenter() {
        PerfilClienteRepository perfilClienteRepository = PerfilClienteRepository.getmInstance(new PerfilClienteRemote());
        return new PerfilClientePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerPerfil(perfilClienteRepository),
                new ObtenerListaComentarios(perfilClienteRepository));
    }

    @Override
    protected PerfilClienteView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_perfilcliente_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getActivity(), R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
        initAdapter();
    }

    private void initAdapter() {
        reciclador.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        perfilClienteAdapter = new PerfilClienteAdapter(new ArrayList<DatosCliente>());
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(perfilClienteAdapter);

        // perfilClienteAdapter.setDatosClienteList(datosClienteList);

    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarDatosPerfil(DatosPerfilResponse.DatosPerfilClienteResponse datosPerfilClienteResponse, int resultado) {
        if (datosPerfilClienteResponse.getUsuCalificacion() == null) {
            Log.d(TAG, "atosPerfilClienteResponse.getUsuCalificacion() == null");
            ratingBar.setRating(0);
        } else {
            ratingBar.setRating(Float.parseFloat(Constantes.validateUsuCalificacion(datosPerfilClienteResponse.getUsuCalificacion())));
            Log.d(TAG, "atosPerfilClienteResponse.getUsuCalificacion() elseelse");
        }
        String nombre = datosPerfilClienteResponse.getNombre() + " " + datosPerfilClienteResponse.getApellidos();
        textViewNombre.setText(nombre.toUpperCase());
        textViewPropuestasPendiente.setText(datosPerfilClienteResponse.getTotalPendientes());
        textViewPropuestasFinalizadas.setText(datosPerfilClienteResponse.getTotal_propuestas_proceso());

        //textViewPropuestaPagada.setText(datosPerfilClienteResponse.getTotal_propuestas_finalizada());resultado+""
        textViewPropuestaPagada.setText(resultado + "");
        if (datosPerfilClienteResponse.getUsuDireccion() == null) {
            textViewDireccion.setText("-Dirección no Registrada-");
        } else {
            textViewDireccion.setText(datosPerfilClienteResponse.getUsuDireccion());
        }

        Glide.with(getActivity())
                .load(datosPerfilClienteResponse.getFoto())
                .into(imageViewProfile);
        Glide.with(getActivity())
                .load(datosPerfilClienteResponse.getPaisImagen())
                .into(imageViewPrimero);
        Glide.with(getActivity())
                .load(datosPerfilClienteResponse.getPaisImagen())
                .into(imageViewSegundo);
    }

    @Override
    public void mostrarListaComentarios(List<DatosCliente> clienteList) {
        perfilClienteAdapter.setDatosClienteList(clienteList);
    }

    @Override
    public void ocultarButtonCalificar() {
        buttonCalificar.setVisibility(View.GONE);
    }

    @Override
    public void mostrarButtonCalificar() {

        buttonCalificar.setVisibility(View.VISIBLE);
    }

    @Override
    public void initStartCalificarClienteActivity(ItemUi itemUi, String nombreCliente, String paisCliente, String imagenCliente) {
        Intent intent = new Intent(getActivity(), CalificarClienteActivity.class);
        intent.putExtra("itemUi", itemUi);
        intent.putExtra("nombreCliente", nombreCliente);
        intent.putExtra("paisCliente", paisCliente);
        intent.putExtra("imagenCliente", imagenCliente);
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
    public void mostrarDatosPerfilRazonSocial(DatosPerfilResponse.DatosPerfilClienteResponse datosPerfilClienteResponse) {
        if (datosPerfilClienteResponse.getUsuCalificacion() == null) {
            Log.d(TAG, "atosPerfilClienteResponse.getUsuCalificacion() == null");
            ratingBar.setRating(0);
        } else {
            ratingBar.setRating(Float.parseFloat(Constantes.validateUsuCalificacion(datosPerfilClienteResponse.getUsuCalificacion())));
            Log.d(TAG, "atosPerfilClienteResponse.getUsuCalificacion() elseelse");
        }
        textViewNombre.setText(datosPerfilClienteResponse.getUsuRazonSocial().toUpperCase());
        textViewPropuestasPendiente.setText(datosPerfilClienteResponse.getTotalPendientes());
        textViewPropuestasFinalizadas.setText(datosPerfilClienteResponse.getTotal_propuestas_finalizada());
        textViewPropuestaPagada.setText(datosPerfilClienteResponse.getTotalPagados());
        if (datosPerfilClienteResponse.getUsuDireccion() == null) {
            textViewDireccion.setText("-Dirección no Registrada-");
        } else {
            textViewDireccion.setText(datosPerfilClienteResponse.getUsuDireccion());
        }

        Glide.with(getActivity())
                .load(datosPerfilClienteResponse.getFoto())
                .into(imageViewProfile);
        Glide.with(getActivity())
                .load(datosPerfilClienteResponse.getPaisImagen())
                .into(imageViewPrimero);
        Glide.with(getActivity())
                .load(datosPerfilClienteResponse.getPaisImagen())
                .into(imageViewSegundo);
    }

    @OnClick({R.id.buttonCalificar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.buttonCalificar:
                presenter.onClickCalificar();
                break;

        }
    }
}
