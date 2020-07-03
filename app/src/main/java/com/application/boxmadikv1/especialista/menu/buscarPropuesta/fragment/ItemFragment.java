package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.adapter.ItemAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.local.ItemLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.remote.ItemRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.PerfilPropuestaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.MostrarListaPropuestaEspecialidad;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerOficio;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerRangoDias;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerRangoPrecio;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerRangoTurno;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.useCase.ObtenerRubro;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_OFICIO;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_PROPUESTA;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_DIAS;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_PRECIO;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_TURNO;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RUBRO;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_USUARIO_PROPUESTA;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_DETALLE_PROPUESTA;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_FECHA_PROPUESTA;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DEPARTAMENTO;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DISTRITO;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_PROVINCIA;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NUMERO_COTIZACION;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_PROMEDIO_COTIZACION;
import static com.application.boxmadikv1.especialista.menu.buscarPropuesta.BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_TITULO_PROPUESTA;

public class ItemFragment extends BaseFragment<ItemView, ItemPresenter> implements ItemView {


    public static final String TAG = ItemFragment.class.getSimpleName();

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewTituloPropuesta)
    TextView textViewTitulo;
    @BindView(R.id.textViewFecha)
    TextView textViewFecha;
    @BindView(R.id.textViewProsupuesto)
    TextView textViewPresupuesto;
    @BindView(R.id.textViewDia)
    TextView textViewDia;
    @BindView(R.id.textViewTurno)
    TextView textViewTurno;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;

    @BindView(R.id.reciclador)
    RecyclerView reciclador;

    @BindView(R.id.textViewNombreDepartamento)
    TextView textViewNombreDepartamento;
    @BindView(R.id.textViewNombreDistrito)
    TextView textViewNombreDistrito;


    public static ItemFragment newInstance(int codigoPropuesta, String titulo,
                                           String detallePropuesta, String fecha,
                                           String codigoRubro, String codigoOficio,
                                           String codigoRangoDiasId, String codigoRangoTurnoId,
                                           String codigoRangoPrecioId, String codigoUsuarioPropuesta,
                                           String numeroCotizacion, String promedioCotizacion,
                                           String tipoEstadoPropuesta, String nombreDepartamento,
                                           String nombreProvincia, String nombreDistrito) {
        Bundle bundle = new Bundle();
        ItemFragment fragment = new ItemFragment();
        bundle.putInt(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_PROPUESTA, codigoPropuesta);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_TITULO_PROPUESTA, titulo);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_DETALLE_PROPUESTA, detallePropuesta);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_FECHA_PROPUESTA, fecha);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RUBRO, codigoRubro);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_OFICIO, codigoOficio);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_DIAS, codigoRangoDiasId);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_TURNO, codigoRangoTurnoId);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_PRECIO, codigoRangoPrecioId);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_USUARIO_PROPUESTA, codigoUsuarioPropuesta);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NUMERO_COTIZACION, numeroCotizacion);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_PROMEDIO_COTIZACION, promedioCotizacion);
        bundle.putString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_TIPO_COTIZACION, tipoEstadoPropuesta);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DEPARTAMENTO, nombreDepartamento);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_PROVINCIA, nombreProvincia);
        bundle.putString(EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DISTRITO, nombreDistrito);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ItemPresenter getPresenter() {
        ItemRepository itemRepository = ItemRepository.getmInstance(new ItemLocal(
                InjectorUtils.provideRubroDao(),
                InjectorUtils.provideOficioDao(),
                InjectorUtils.provideRangoDiasDao(),
                InjectorUtils.provideRangoTurnoDao(),
                InjectorUtils.provideRangoPrecioDao()
        ), new ItemRemote());
        return new ItemPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new ObtenerRubro(itemRepository),
                new ObtenerOficio(itemRepository),
                new ObtenerRangoDias(itemRepository),
                new ObtenerRangoPrecio(itemRepository),
                new ObtenerRangoTurno(itemRepository),
                new MostrarListaPropuestaEspecialidad(itemRepository));
    }

    @Override
    protected ItemView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_especialista_item_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void mostrarListaPropuestaEspecialidad(List<PropuestaEspecialidad> especialidadList) {
        ItemAdapter itemAdapter = new ItemAdapter(especialidadList);
        reciclador.setVisibility(View.VISIBLE);
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(itemAdapter);
        textViewEmpty.setVisibility(View.GONE);
    }

    @Override
    public void mostrarDataBasica(String titulo, String fecha, String nombreDepartamento, String nombreDistrito) {
        textViewTitulo.setText(titulo);
        textViewFecha.setText("Ingresado: " + Constantes.f_fecha_letras(fecha));
        textViewNombreDepartamento.setText(nombreDepartamento);
        textViewNombreDistrito.setText("Dist: " + nombreDistrito);
    }

    @Override
    public void mostrarRangoDiasTexto(String nombreRangoDias) {
        textViewDia.setText("Día: " + nombreRangoDias);
    }

    @Override
    public void mostrarRangoPrecioTexto(String nombreRangoPrecio) {
        textViewPresupuesto.setText("Presupuesto: " + nombreRangoPrecio);
    }

    @Override
    public void mostrarRangoturnoTexto(String nombreRangoTurno) {
        textViewTurno.setText("Turno: " + nombreRangoTurno);
    }

    @Override
    public void mostrarImagenRubro(String descripcion) {
        Glide.with(getActivity()).load(descripcion).into(imageViewRubro);
    }

    @Override
    public void mostrarTextoVacio(String no_tiene_especialidades) {
        textViewEmpty.setVisibility(View.VISIBLE);
        textViewEmpty.setText(no_tiene_especialidades);
        reciclador.setVisibility(View.GONE);
    }

    @Override//ItemUi
    // public void startActivityPerfilPropuesta(String imageRubro, String titulo, String fecha, int codigoPropuesta, String codigoRubro, String codigoOficio, String codigoRangoDiasId, String codigoRangoTurnoId, String codigoRangoPrecioId) {
    public void startActivityPerfilPropuesta(ItemUi itemUi) {
        String tipoActivity = "actividadClicked";
        Intent intent = new Intent(getActivity(), PerfilPropuestaActivity.class);
        intent.putExtra("itemUi", itemUi);
        intent.putExtra("notificacion", tipoActivity);
        getActivity().startActivity(intent);

    }

    @OnClick({R.id.btnPropuesta})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnPropuesta:
                presenter.onClickPropuesta();
                break;
            default:
                break;
        }
    }


}
