package com.application.boxmadikv1.cliente.menu.detallesCotizacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.DetallesCotizacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.remote.DetallesCotizacionRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.DetallesDescripcionFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.listener.DetallesListener;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.useCase.EliminarPropuesta;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.useCase.EliminarPropuestaUnica;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.MyFragmentAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *
 * Esta Actividad se inicia Cuando se Hace Click en Propuestas Creadas!!!!!!
 *
 *
 * */
public class DetallesCotizacionActivity extends BaseActivity<DetallesCotizacionView, DetallesCotizacionPresenter> implements DetallesCotizacionView, DetallesListener {

    public static final String TAG = DetallesCotizacionActivity.class.getSimpleName();


    @BindView(R.id.tabLayout)
    TabLayout tabLayoutEspecialista;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.imageView2)
    ImageView imageViewRubro;
    @BindView(R.id.textView7)
    TextView textViewNombrePropuesta;
    @BindView(R.id.textViewFecha)
    TextView textViewFecha;

    @BindView(R.id.imageViewDelete)
    ImageView imageViewDelete;

    @BindView(R.id.textViewNombreDepartamento)
    TextView textViewNombreDepartamento;

    //private SecurePreferences preferences;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected DetallesCotizacionPresenter getPresenter() {
        DetallesCotizacionRepository detallesCotizacionRepository = DetallesCotizacionRepository.getmInstance(new DetallesCotizacionRemote());
        return new DetallesCotizacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new EliminarPropuesta(detallesCotizacionRepository),
                new EliminarPropuestaUnica(detallesCotizacionRepository));
    }

    @Override
    protected DetallesCotizacionView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_cliente_detalles_cotizacion);
        ButterKnife.bind(this);
      //  preferences = new SecurePreferences(getApplicationContext(),Constantes.KEY_SECURE_PREFERENCE,true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarFragmentos(DetallesCotizacionUi detallesCotizacionUi) {
        initAdapterViewPager(detallesCotizacionUi);
    }

    private void initAdapterViewPager(DetallesCotizacionUi detallesCotizacionUi) {
        Bundle args = new Bundle();
        args.putAll(getIntent().getExtras());
        args.putParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        /*args.putInt("cursoId", idCurso);
        args.putInt("idProgramaEducativo", idProgramaEducativo);*/
        final MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(DetallesCotizaFragment.newInstance(args), "COTIZACIONES");
        fragmentAdapter.addFragment(DetallesDescripcionFragment.newInstance(args), "DESCRIPCIÓN");
        //viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(fragmentAdapter);
        tabLayoutEspecialista.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*if (presenter != null)
                    presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);
*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void mostrarDataInicial(String imagenRubro, String nombrePropuesta, String fechaPropuesta, String nombreDepartamento) {
        Glide.with(this).load(imagenRubro).into(imageViewRubro);
        textViewNombrePropuesta.setText(nombrePropuesta.toUpperCase());
        textViewFecha.setText(Constantes.f_fecha_letras(fechaPropuesta));
        textViewNombreDepartamento.setText(nombreDepartamento);
    }

    @Override
    public void mostrarDialogConfirmacion(final List<CotizacionesUi> cotizacionesUis, String mensaje, final String tipoEstadoEliminar) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DetallesCotizacionActivity.this);
        dialog.setTitle("Eliminar Propuesta")
                //.setIcon(R.drawable.ic_albanieria)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        presenter.onEliminarPropuestaAceptado(cotizacionesUis, tipoEstadoEliminar);
                    }
                }).show();
    }

    @Override
    public void mostrarMensaje(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initStartActivityMenuCliente(String estado) {
        Intent intent = new Intent(this, MenuClienteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("estado", estado);
        startActivity(intent);
        Log.d(TAG, "initStartActivityMenuCliente");
    }

    @Override
    public void mostrarImagenEliminar() {
        imageViewDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarImagenEliminar() {
        imageViewDelete.setVisibility(View.GONE);
    }


    @OnClick({R.id.close, R.id.imageViewDelete})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.close:
                Intent intent = new Intent(this, MenuClienteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // intent.putExtra("estado", estado);
                startActivity(intent);
                break;
            case R.id.imageViewDelete:
                presenter.onEliminarPropuesta();
                break;
        }
    }

    @Override
    public void ObternerListaPorEstado(List<CotizacionesUi> cotizacionesUis) {
        presenter.onObtenerListaPorEstado(cotizacionesUis);
    }
}
