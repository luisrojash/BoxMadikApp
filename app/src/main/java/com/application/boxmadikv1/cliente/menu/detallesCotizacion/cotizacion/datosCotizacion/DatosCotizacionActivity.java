package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
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
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.DetallesCotizacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.DatosCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.DatosPerfilEspecialistaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.notificaciones.cliente.ClienteNotiActivity;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.MyFragmentAdapter;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity.EXTRA_SELECCION_USER_CODIGO_PAIS;
import static com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity.EXTRA_SELECCION_USER_CODIGO_USU;
import static com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity.EXTRA_SELECCION_USER_TIPO_NOTIFICACION;

public class DatosCotizacionActivity extends BaseActivity<DatosCotizacionView, DatosCotizacionPresenter> implements DatosCotizacionView {

    public static final String TAG = DatosCotizacionActivity.class.getSimpleName();


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
    @BindView(R.id.textViewNombreDepartamento)
    TextView textViewNombreDepartamento;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected DatosCotizacionPresenter getPresenter() {
        return new DatosCotizacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected DatosCotizacionView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_cliente_datos_cotizacion_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarFragmentos(DetallesCotizacionUi detallesCotizacionUi,
                                  CotizacionesUi cotizacionesUi) {
        initAdapterViewPager(detallesCotizacionUi, cotizacionesUi);
    }

    private void initAdapterViewPager(DetallesCotizacionUi detallesCotizacionUi,
                                      CotizacionesUi cotizacionesUi) {

        // Bundle args = new Bundle();
        /*args.putAll(getIntent().getExtras());
        args.putParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);*/

        final MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(DatosCotizaFragment.newInstance(detallesCotizacionUi, cotizacionesUi), "COTIZACIÓN");
        fragmentAdapter.addFragment(DatosPerfilEspecialistaFragment.newInstance(detallesCotizacionUi, cotizacionesUi), "PERFIL-ESPECIALISTA");
        viewPager.setOffscreenPageLimit(5);
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
    public void mostrarMensaje(String revocación_enviada) {
        Toast.makeText(getApplicationContext(), revocación_enviada, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity(DetallesCotizacionUi detallesCotizacionUi,
            CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(this, DetallesCotizacionActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA,detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI,cotizacionesUi);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void startActivityMenuPrincipalCliente() {
        Intent intent = new Intent(getActivity(), MenuClienteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onFinishStartNotificacion(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        String tipoNotificacion = "notiCliente";
        Intent intent = new Intent(this, ClienteNotiActivity.class);
        intent.putExtra(EXTRA_SELECCION_USER_CODIGO_USU, detallesCotizacionUi.getKeyUser());
        intent.putExtra(EXTRA_SELECCION_USER_CODIGO_PAIS, detallesCotizacionUi.getPaisCodigo());
        intent.putExtra(EXTRA_SELECCION_USER_TIPO_NOTIFICACION, tipoNotificacion);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick({R.id.close})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.close:
                //finish();
                presenter.onClickClose();
                break;
        }
    }
}
