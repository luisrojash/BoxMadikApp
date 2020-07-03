package com.application.boxmadikv1.especialista.menu.buscarPropuesta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter.MisRubrosAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter.PropuestaAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.local.BuscarPropuestaLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.BuscarPropuestaRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.remote.BuscarPropuestaRemote;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.ItemFragment;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.PerfilPropuestaActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.listener.MisRubrosListener;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.listener.PropuestaListener;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.RubroItemActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.useCase.MostrarListaRubros;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.useCase.MostrarListaTotalRubros;
import com.application.boxmadikv1.extra.InternetCheck;
import com.application.boxmadikv1.extra.InternetCheck.Consumer;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.MyFragmentAdapter;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuscarPropuestaActivity extends BaseActivity<BuscarPropuestaView, BuscarPropuestaPresenter> implements
        BuscarPropuestaView, MisRubrosListener, PropuestaListener, Consumer {

    public static final String TAG = BuscarPropuestaActivity.class.getSimpleName();

    public static final int RESULTADO_ACTIVIDAD_ACTIVIDAD_PROPUESTA = 1;
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_PROPUESTA = "codigoPropuesta";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_TITULO_PROPUESTA = "titulo";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_DETALLE_PROPUESTA = "detallePropuesta";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_FECHA_PROPUESTA = "fecha";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RUBRO = "codigoRubro";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_OFICIO = "codigoOficio";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_DIAS = "codigoRangoDiasId";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_TURNO = "codigoRangoTurnoId";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_RANGO_PRECIO = "codigoRangoPrecioId";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_CODIGO_USUARIO_PROPUESTA = "codigoUsuarioPropuesta";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NUMERO_COTIZACION = "numeroCotizacion";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_PROMEDIO_COTIZACION = "promedioCotizacion";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_TIPO_COTIZACION = "tipoEstadoPropuesta";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DEPARTAMENTO = "nombreDepartamento";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_PROVINCIA = "nombreProvincia";
    public static final String EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_NOMBRE_DISTRITO = "nombreDistrito";
    @BindView(R.id.imageView)
    LottieAnimationView lottieAnimationView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imageViewEspecialidades)
    ImageView imageViewRotation;
    @BindView(R.id.imageNombreFiltro)
    ImageView imageViewRubroItem;
    @BindView(R.id.textViewDescripcion)
    TextView textViewNombrRubroItem;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    @BindView(R.id.fabDerecha)
    ImageView fabDerecha;
    @BindView(R.id.fabIzquierda)
    ImageView fabIzquierda;

    @BindView(R.id.recyclerView)
    RecyclerView reciclador;

    @BindView(R.id.contentFitlro)
    ConstraintLayout contentFilto;

    @BindView(R.id.viewPagerBuscarPropuesta)
    ViewPager viewPager;

    MisRubrosAdapter misRubrosAdapter;
    //DynamicPagerAdapter fragmentAdapter;
    MyFragmentAdapter fragmentAdapter;
    TinyDB tinyDB;
    SecurePreferences preferences;

    @BindView(R.id.reciclador)
    RecyclerView recicladorPropuesta;

    /*Adapter*/

    private PropuestaAdapter propuestaAdapter;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected BuscarPropuestaPresenter getPresenter() {
        BuscarPropuestaRepository buscarPropuestaRepository = BuscarPropuestaRepository.getmInstance(
                new BuscarPropuestaLocal(
                        InjectorUtils.provideRubroDao()),
                new BuscarPropuestaRemote());
        return new BuscarPropuestaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new MostrarListaRubros(buscarPropuestaRepository),
                new MostrarListaTotalRubros(buscarPropuestaRepository));
    }

    @Override
    protected BuscarPropuestaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_especialista_buscar_propuesta);
        ButterKnife.bind(this);
        initAdapter();
        initFragmentAdapter();
        initToolbar();

    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initPreferences() {
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        String userKey = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String departamentoId = preferences.getString(Constantes.KEY_SECURE_USUARIO_ESPE_CODIGO_DEPARTAMENT);
        String codigPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        Log.d(TAG, "departamentoId : " + departamentoId);
        if (userKey == null) return;
        presenter.onKeyUser(userKey, departamentoId, codigPais);
        initTinyDb();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPreferences();

    }

    private void initTinyDb() {
        tinyDB = new TinyDB(getApplicationContext());
        presenter.setTinyDB(tinyDB);
    }

    private void initFragmentAdapter() {
        fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
    }

    LinearLayoutManager linearLayout;

    private void initAdapter() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        misRubrosAdapter = new MisRubrosAdapter(new ArrayList<MisRubrosUi>(), this);
        reciclador.setHasFixedSize(true);
        reciclador.setLayoutManager(gridLayoutManager);
        reciclador.setAdapter(misRubrosAdapter);
        mostrarContenidoEspecialidades();
        linearLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        propuestaAdapter = new PropuestaAdapter(new ArrayList<ItemUi>(), this);
        recicladorPropuesta.setHasFixedSize(true);
        recicladorPropuesta.setLayoutManager(linearLayout);
        recicladorPropuesta.setAdapter(propuestaAdapter);

        recicladorPropuesta.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                presenter.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenter.onScrolled(linearLayout, dx, dy);
            }
        });
        propuestaAdapter.setRecyclerView(recicladorPropuesta);

    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaMisRubros(List<MisRubrosUi> misRubrosUiList) {
        misRubrosAdapter.mostrarListaMisRubros(misRubrosUiList);
    }

    @Override
    public void mostrarContenidoFiltro(String descripcion, String image) {
        textViewNombrRubroItem.setText(descripcion);
        imageViewRotation.clearAnimation();
        lottieAnimationView.playAnimation();
        contentFilto.setVisibility(View.VISIBLE);
        reciclador.setVisibility(View.GONE);
        //contenTotal.setVisibility(View.VISIBLE);
        Glide.with(this).load(image).into(imageViewRubroItem);
    }


    @Override
    public void mostrarContenidoEspecialidades() {
        reciclador.setVisibility(View.VISIBLE);
        contentFilto.setVisibility(View.GONE);
        // contenTotal.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarOnclickEspecialidadRotationTrue() {
        imageViewRotation.clearAnimation();
        ViewCompat.animate(imageViewRotation).rotation(360).start();
    }

    @Override
    public void mostrarOnclickEspecialidadRotationFalse() {
        imageViewRotation.clearAnimation();
        ViewCompat.animate(imageViewRotation).rotation(0).start();
    }

    @Override
    public void startSeleccionRubroItem() {
        Intent intent = new Intent(this, RubroItemActivity.class);
        // performAuthorizationRequest
        startActivityForResult(intent, RESULTADO_ACTIVIDAD_ACTIVIDAD_PROPUESTA);
    }


    @Override
    public void initViewPagerAdapter() {
        if (fragmentAdapter == null) return;
        // viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(fragmentAdapter);
    }

    @Override
    public void initClearFragments() {
        if (fragmentAdapter == null) return;
        fragmentAdapter.clear();
    }

    @Override
    public void mostrarMensajeSnackBar(String s) {
        Snackbar.make(progressBar, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarMensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void mostrarListaBuscadorPropuestaFragment(int codigoPropuesta, String titulo, String detallePropuesta,
                                                      String fecha, String codigoRubro, String codigoOficio,
                                                      String codigoRangoDiasId, String codigoRangoTurnoId,
                                                      String codigoRangoPrecioId, String codigoUsuarioPropuesta,
                                                      String numeroCotizacion, String promedioCotizacion,
                                                      String tipoEstadoPropuesta, String nombreDepartamento,
                                                      String nombreProvincia, String nombreDistrito) {
        if (fragmentAdapter == null) return;
        Log.d(TAG, "mostrarListaBuscadorPropuestaFragment : " + codigoPropuesta);
       /* Bundle bundle = new Bundle();
        bundle.putAll(getIntent().getExtras());
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
        bundle.putString(BuscarPropuestaActivity.EXTRA_BUSCAR_PROPUESTA_ACTIVIDAD_TIPO_COTIZACION, tipoEstadoPropuesta);*/
        fragmentAdapter.addFragment(ItemFragment.newInstance(codigoPropuesta, titulo, detallePropuesta,
                fecha, codigoRubro, codigoOficio, codigoRangoDiasId,
                codigoRangoTurnoId, codigoRangoPrecioId, codigoUsuarioPropuesta,
                numeroCotizacion, promedioCotizacion, tipoEstadoPropuesta, nombreDepartamento,
                nombreProvincia, nombreDistrito), titulo);
    }

    @Override
    public void actualizarMisRubrosAdapter() {
        misRubrosAdapter.actualizarAdapter();
    }

    @Override
    public void actualizarAdapterFragmentos() {
        fragmentAdapter.actualizarFragmentos();
        textViewEmpty.setVisibility(View.GONE);
    }

    @Override
    public void mostrarMensajeEmptyTexto(String s) {
        textViewEmpty.setVisibility(View.VISIBLE);
        textViewEmpty.setText(s);
        recicladorPropuesta.setVisibility(View.GONE);
    }

    @Override
    public void ocultarBotones() {
        fabDerecha.setVisibility(View.GONE);
        fabIzquierda.setVisibility(View.GONE);
    }

    @Override
    public void mostrarBotones() {
        fabDerecha.setVisibility(View.VISIBLE);
        fabIzquierda.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarListaPropuesta(List<ItemUi> itemUiList) {
        propuestaAdapter.setMostraPropuestaLista(itemUiList);
        recicladorPropuesta.setVisibility(View.VISIBLE);
        textViewEmpty.setVisibility(View.GONE);

    }

    @Override
    public void agregarLoadMoreLista(List<ItemUi> itemUiList) {
        propuestaAdapter.agregarLoadMoreLista(itemUiList);
    }

    @Override
    public void initStartActivityDetallesPropuesta(ItemUi itemUi) {
        Log.d(TAG, "initStartActivityDetallesPropuesta " + itemUi.getNombrePropuesta());
        String tipoActivity = "actividadClicked";
        Intent intent = new Intent(this, PerfilPropuestaActivity.class);
        intent.putExtra("itemUi", itemUi);
        intent.putExtra("notificacion", tipoActivity);
        startActivity(intent);
    }

    @Override
    public void actualizarEspecialistaAdapter(ItemUi itemUi) {
        Log.d(TAG, "actualizarEspecialistaAdapter " + itemUi.getEspecialidadesUiList().size() + "");
        propuestaAdapter.actualizarDataEspecialista(itemUi);
    }

    @Override
    public void mostrarDialogMensaje(String s) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(BuscarPropuestaActivity.this);
        dialog.setTitle("Problemas Conexión")
                .setIcon(R.mipmap.ic_boxmadick)
                .setMessage(s)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        finish();
                    }
                })
               /* .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        //presenter.onClickRenuevaDatosGenerales(inputValidation.isNetworkAvailable());
                        new InternetCheck(BuscarPropuestaActivity.this,null);
                    }
                })*/
                .show();
    }


    @OnClick({R.id.constFiltro, R.id.constMisEspecialidades})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.constFiltro:
                presenter.onClickFiltro();
                break;
            case R.id.constMisEspecialidades:
                presenter.onClickEspecialidades();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        if (presenter != null) presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        presenter.onNewIntent(intent);
    }


    @Override
    public void onClickRubros(MisRubrosUi misRubrosUi) {
        presenter.onClickMisRubros(misRubrosUi);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ventana_principal) {

            return true;
        } else {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickPropuestaListener(ItemUi itemUi) {
        Object object = itemUi;
        new InternetCheck(this,object);

       /* if (object instanceof ItemUi) {
            ItemUi itemUi1 = (ItemUi) object;
            new InternetCheck(this,object);
            Log.d(TAG, "onClickPropuestaListener : " +itemUi1.getNombrePropuesta());
        }*/

        // presenter.onClickPropuestaListener(itemUi);
    }

    @Override
    public void accept(Boolean internet,Object objeto) {
        presenter.onClickEstadoInternet(internet,objeto);
    }
}
