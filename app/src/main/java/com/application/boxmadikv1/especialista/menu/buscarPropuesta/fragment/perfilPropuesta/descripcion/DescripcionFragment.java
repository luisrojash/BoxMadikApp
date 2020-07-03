package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.adapter.DescripcionAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource.DescripcionRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource.remote.DescripcionRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revoca.RevocaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.useCase.MostrarImagenPropuesta;
import com.application.boxmadikv1.rptRevocacion.RespuestaRevocacionActivity;
import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class DescripcionFragment extends BaseFragment<DescripcionView, DescripcionPresenter> implements DescripcionView {

    public static final String TAG = DescripcionFragment.class.getSimpleName();

    /*@BindView(R.id.textviewPromedio)
    TextView textViewPromedio;*/
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
    Button bbtnCountCotizacion;
    @BindView(R.id.textviewPromedio)
    TextView textViewPromedioCotizacion;

    @BindView(R.id.btnRevocacion)
    Button btnRevocacion;

    @BindView(R.id.buttonVerRevocacion)
    Button btnVerRevocacion;

    public static DescripcionFragment newInstance(Bundle args) {
        DescripcionFragment fragment = new DescripcionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected DescripcionPresenter getPresenter() {
        DescripcionRepository descripcionRepository = DescripcionRepository.getmInstance(new DescripcionRemote());
        return new DescripcionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(), new MostrarImagenPropuesta(descripcionRepository)
        );
    }

    @Override
    protected DescripcionView getBaseView() {
        return this;
    }


    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_descripcion_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarDataInicial(ItemUi itemUi) {
        textInputEditText.setEnabled(false);
        //textViewPromedio.setText("S/. " + itemUi.getDescripcionRangoPrecio());
        textInputEditText.setText(itemUi.getDetallesPropuesta());
        textViewRangoDias.setText(itemUi.getDescripcionRangoDias());
        textViewRangoTurno.setText(itemUi.getDescripcionRangoTurno());
        //.diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(getActivity())
                .load(itemUi.getImagePropuesta())
                .into(imageViewRubro);
        textViewNombeRubro.setText(itemUi.getDescripcionRubro());
        textViewNombreOficio.setText(itemUi.getDescripcionOficio());
        textViewPromedioCotizacion.setText("S/. " + itemUi.getPromedioCotizacion());
        Log.d(TAG, "numeroCotizacion : " + itemUi.getNumeroCotizacion());
        bbtnCountCotizacion.setText(itemUi.getNumeroCotizacion());

    }

    @Override
    public void ocultarTeclado() {
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void mostrarTeclado() {

    }

    @Override
    public void mostrarTextoImagenVacio(String no_hay_imagenes) {
        textViewImagenVacia.setVisibility(View.VISIBLE);
        textViewImagenVacia.setText(no_hay_imagenes);
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
    public void mostrarImagenDetallePrimero(String uriImagenPrimero) {
        /*CropImage.activity(Uri.parse(uriImagenPrimero))
                .start(getActivity());*/
        Log.d(TAG, "mostrarImagenDetallePrimero : " + uriImagenPrimero);
        // for fragment (DO NOT use `getActivity()`)
        /*CropImage.activity(Uri.parse(uriImagenPrimero))
                .start(getContext(), this);
        CropImage.activity()
                .start(getContext(), this);*/

        Intent intent = CropImage.activity(Uri.parse(uriImagenPrimero))
                .getIntent(getContext());
        startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);

       /* Uri uriParse = Uri.parse(uriImagenPrimero);
        CropImage.activity(uriParse)
                .start(getContext(), this); // (DO NOT use `getActivity()`)*/
    }


    @Override
    public void mostrarImagenDetalleSegundo(String uriImagenSegundo) {
       /* CropImage.activity(Uri.parse(uriImagenSegundo))
                .start(getActivity());*/

        /*Intent intent = CropImage.activity(Uri.parse(uriImagenSegundo))
                .getIntent(getContext());
        startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);*/

        Uri uriParse = Uri.parse(uriImagenSegundo);
        CropImage.activity(uriParse)
                .start(getContext(), this); // (DO NOT use `getActivity()`)
    }

    @Override
    public void mostrarTextoListaVacia(String s) {
        recyclerView.setVisibility(View.GONE);
//        textViewRecicladorVacia.setVisibility(View.VISIBLE);
//        textViewRecicladorVacia.setText(s);
    }

    @Override
    public void mostrarListaEspecialidades(List<EspecialidadesUi> especialidadesUiList) {
        Log.d(TAG, "mostrarListaEspecialidades : " + especialidadesUiList);
        recyclerView.setVisibility(View.VISIBLE);
//        textViewRecicladorVacia.setVisibility(View.GONE);
        DescripcionAdapter descripcionAdapter = new DescripcionAdapter(especialidadesUiList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(descripcionAdapter);

    }

    @Override
    public void startActivityRevocacion(ItemUi itemUi) {
        Log.d(TAG, "startActivityRevocacion : ");
        Intent intent = new Intent(getActivity(), RevocaActivity.class);
        intent.putExtra("itemUi", itemUi);
        startActivity(intent);
    }

    @Override
    public void mostrarMensaje(String mensajePendiente) {
        Toast.makeText(getActivity(), mensajePendiente, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarButtonRevocacion() {
        btnRevocacion.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarButtonRevocacion() {
        btnRevocacion.setVisibility(View.GONE);
    }

    @Override
    public void mostrarButtonRevocacionTextoEnProceso(String texto) {
        btnRevocacion.setText(texto);
    }

    @Override
    public void habilitarButtonRevocacion() {
        btnRevocacion.setEnabled(true);

    }

    @Override
    public void deshabilitarButtonRevocacion() {
        btnRevocacion.setEnabled(false);
        btnRevocacion.setBackgroundResource(R.drawable.btn_bg_disable);
    }

    @Override
    public void mostrarButtonVerRevocacion() {
        btnVerRevocacion.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarButtonVerRevocacion() {
        btnVerRevocacion.setVisibility(View.GONE);
    }

    @Override
    public void habilitarButtonVerRevocacion() {
        btnVerRevocacion.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonVerRevocacion() {
        btnVerRevocacion.setEnabled(false);
        btnVerRevocacion.setBackgroundResource(R.drawable.btn_bg_disable);
    }

    @Override
    public void initStartActivityRespuestaRevocacion(ItemUi itemUi) {
        String tipousuario = "especialista";
        Intent intent = new Intent(getActivity(), RespuestaRevocacionActivity.class);
        intent.putExtra("itemUi",itemUi);
        intent.putExtra("tipousuario",tipousuario);
        startActivity(intent);
    }


    @OnClick({R.id.imageViewPrimero, R.id.imageViewSegundo, R.id.btnRevocacion,R.id.buttonVerRevocacion})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.imageViewPrimero:
                presenter.onClickImagePrimero();
                break;
            case R.id.imageViewSegundo:
                presenter.onClickImageSegundo();
                break;
            case R.id.btnRevocacion:
                presenter.onClickRevocacion();
                break;
            case R.id.buttonVerRevocacion:
                presenter.onClickButtonVerRevocacion();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
