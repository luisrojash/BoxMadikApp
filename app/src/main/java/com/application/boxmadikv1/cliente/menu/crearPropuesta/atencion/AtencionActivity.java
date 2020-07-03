package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.adapter.SpinnerTipoRangoDiasAdapter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.adapter.SpinnerTipoRangoPrecioAdapter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.adapter.SpinnerTipoRangoTurnoAdapter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.AtencionRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.local.AtencionLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoDiasUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoPrecioUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoTurnoUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase.ListarTipoDias;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase.ListarTipoPrecio;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase.ListarTipoTurno;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.DetallesActivity;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AtencionActivity extends BaseActivity<AtencionView, AtencionPresenter> implements AtencionView,
        AdapterView.OnItemSelectedListener {

    public static final String TAG = AtencionActivity.class.getSimpleName();
    public static final int RESULTADO_ACTIVIDAD_ACTIVIDAD_ATENCION = 1;

    @BindView(R.id.spinnerTipoPresupuesto)
    Spinner spinnerTipoPrecio;
    @BindView(R.id.spinerTipoDias)
    Spinner spinnerTipoDias;
    @BindView(R.id.spinerTipoHorario)
    Spinner spinnerTipoTurno;

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubroPerf;
    @BindView(R.id.textViewNombreOficio)
    TextView textViewNombreOficio;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected AtencionPresenter getPresenter() {
        AtencionRepository atencionRepository = new AtencionRepository(
                new AtencionLocal(InjectorUtils.provideRangoTurnoDao(),
                        InjectorUtils.provideRangoDiasDao(),
                        InjectorUtils.provideRangoPrecioDao()));
        return new AtencionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListarTipoDias(atencionRepository),
                new ListarTipoPrecio(atencionRepository),
                new ListarTipoTurno(atencionRepository));
    }

    @Override
    protected AtencionView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_cliente_atencion);
        ButterKnife.bind(this);
        initAdapterSpinner();
        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initAdapterSpinner() {
        spinnerTipoPrecio.setOnItemSelectedListener(this);
        spinnerTipoDias.setOnItemSelectedListener(this);
        spinnerTipoTurno.setOnItemSelectedListener(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarListaTipoPrecio(List<TipoRangoPrecioUi> tipoRangoPrecioUis) {
        SpinnerTipoRangoPrecioAdapter adapter = new SpinnerTipoRangoPrecioAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoRangoPrecioUis);
        spinnerTipoPrecio.setAdapter(adapter);
        if (presenter != null) presenter.validarTipoPrecio();
        //spinnerTipoPrecio.setSelection(2);
    }

    @Override
    public void mostrarListaTipoDias(List<TipoRangoDiasUi> tipoRangoDiasUis) {
        SpinnerTipoRangoDiasAdapter adapter = new SpinnerTipoRangoDiasAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoRangoDiasUis);
        spinnerTipoDias.setAdapter(adapter);
        if (presenter != null) presenter.validarTipoDias();
    }

    @Override
    public void mostrarListaTipoTurno(List<TipoRangoTurnoUi> tipoRangoTurnoUis) {
        Log.d(TAG, "mostrarListaTipoTurno");
        SpinnerTipoRangoTurnoAdapter adapter = new SpinnerTipoRangoTurnoAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoRangoTurnoUis);
        spinnerTipoTurno.setAdapter(adapter);
        if (presenter != null) presenter.validarTipoTurno();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(spinnerTipoDias, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void startActivityDetalles(int rubroId, int oficioId, String tipoTurno, String tipoPrecio, String tipoDias, String imageRubro, String nombreOficio,
                                      int posicionTipoPrecio, int posicionTipoTurno, int posicionTipoDias, ArrayList<String> listaIdEspecialistas) {

        Log.d(TAG, "startActivityDetalles : " + posicionTipoPrecio + posicionTipoTurno + posicionTipoDias);
        Intent intent = new Intent(this, DetallesActivity.class);
        intent.putExtra("rubroId", rubroId);
        intent.putExtra("oficioId", oficioId);
        intent.putExtra("tipoTurno", tipoTurno);
        intent.putExtra("tipoPrecio", tipoPrecio);
        intent.putExtra("tipoDias", tipoDias);
        intent.putExtra("imageRubro", imageRubro);
        intent.putExtra("nombreOficio", nombreOficio);
        intent.putExtra("posicionTipoPrecio", posicionTipoPrecio);
        intent.putExtra("posicionTipoTurno", posicionTipoTurno);
        intent.putExtra("posicionTipoDias", posicionTipoDias);
        intent.putExtra("mylist", listaIdEspecialistas);
        //startActivity(intent);
        startActivityForResult(intent, RESULTADO_ACTIVIDAD_ACTIVIDAD_ATENCION);
    }

    @Override
    public void mostrarCabecera(String imageRubro, String nombreOficio) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true);
        Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageRubro).into(imageViewRubroPerf);
        textViewNombreOficio.setText(nombreOficio);
    }

    @Override
    public void mostrarSpinnerSeleccionTipoPrecio(int onBackposicionTipoPrecio) {
        spinnerTipoPrecio.setSelection(onBackposicionTipoPrecio);
    }

    @Override
    public void mostrarSpinnerSeleccionTipoDias(int onBackposicionTipoDias) {
        spinnerTipoDias.setSelection(onBackposicionTipoDias);
    }

    @Override
    public void mostrarSpinnerSeleccionTipoturno(int onBackposicionTipoTurno) {
        spinnerTipoTurno.setSelection(onBackposicionTipoTurno);
    }

    @Override
    public void onBackActivityEspec(int posicionTipoPrecio, int posicionTipoTurno, int posicionTipoDias) {
        Log.d(TAG, "onBackActivityEspec : "+ posicionTipoPrecio +" /  "+ posicionTipoTurno+" / "+ posicionTipoDias);
        /*Intent intent = new Intent();
        intent.putExtra("posicionTipoPrecio", posicionTipoPrecio);
        intent.putExtra("posicionTipoTurno", posicionTipoTurno);
        intent.putExtra("posicionTipoDias", posicionTipoDias);
        setResult(RESULT_OK, intent);
        finish();*/
        Intent intent = new Intent();
        intent.putExtra("posicionTipoPrecio", posicionTipoPrecio);
        setResult(RESULT_OK, intent);
        finish();

    }

    @OnClick({R.id.btnSiguiente})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnSiguiente:
                presenter.onClickSiguiente();
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int itemId = parent.getId();
        switch (itemId) {
            case R.id.spinnerTipoPresupuesto:
                try {
                    TipoRangoPrecioUi tipoRangoPrecioUi = (TipoRangoPrecioUi) spinnerTipoPrecio.getSelectedItem();
                    presenter.onSpinnerTipoPrecio(tipoRangoPrecioUi.getIdRangoPrecio(), position);
                    Log.d(TAG, "tipoRangoPrecioUi : " + position);
                } catch (Exception e) {
                    Log.d(TAG, "spinnerPaisPaise : " + e.getMessage());
                }
                break;
            case R.id.spinerTipoHorario:
                try {
                    TipoRangoTurnoUi tipoRangoTurnoUi = (TipoRangoTurnoUi) spinnerTipoTurno.getSelectedItem();
                    presenter.onSpinnerTipoTurno(tipoRangoTurnoUi.getIdRangoTurno(), position);
                    Log.d(TAG, "tipoRangoTurnoUi" + position);
                } catch (Exception e) {
                    Log.d(TAG, "spinnerPaisPaise : " + e.getMessage());
                }

                break;
            case R.id.spinerTipoDias:
                try {
                    TipoRangoDiasUi tipoRangoDiasUi = (TipoRangoDiasUi) spinnerTipoDias.getSelectedItem();
                    presenter.onSpinnerTipoDias(tipoRangoDiasUi.getIdRangoDias(), position);
                    Log.d(TAG, "tipoRangoDiasUi" + position);
                } catch (Exception e) {
                    Log.d(TAG, "spinnerPaisPaise : " + e.getMessage());
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Atencion::onActivityResult");
        if (presenter != null) presenter.onActivityResult(requestCode, resultCode, data);
    }

    /*@Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (presenter != null) presenter.onNewIntent(intent);
        //if (presenter != null) presenter.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onNewIntent");
    }
*/

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
            //presenter.onFinishActivity();
             finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
