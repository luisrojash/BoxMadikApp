package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revoca;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.api.response.cliente.ObtenerRespuestaRevocaEspResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RevocaActivity extends BaseActivity<RevocaView, RevocaPresenter> implements RevocaView {

    public static final String TAG = RevocaActivity.class.getSimpleName();
    @BindView(R.id.textViewMotivoRevocacion)
    TextView textViewMotivoRevocacion;
    @BindView(R.id.textViewCalidadTrabajo)
    TextView textViewCalidadTrabajo;
    @BindView(R.id.textView19)
    TextView textViewPorcentaje2;
    @BindView(R.id.textViewSolucionRevocante)
    TextView textViewSolucionRevocante;
    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextDetalles;

    @BindView(R.id.textViewNombreOficio)
    TextView textViewNombrePro;
    @BindView(R.id.btnEnviar)
    Button buttonEnviar;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RevocaPresenter getPresenter() {
        return new RevocaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected RevocaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_revoca_espec);
        ButterKnife.bind(this);
        initToolbar();
    }
    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected ProgressBar getProgressBar() {
        return null;
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
    public void mostrarProgressBarDialog(String mensaje) {

    }

    @Override
    public void mostrarDataInicial(String nombrePropuesta, String imagenRubro) {
        textViewNombrePro.setText(nombrePropuesta.toUpperCase());
        Glide.with(getActivity())
                .load(imagenRubro)
                .into(imageViewRubro);
    }

    @Override
    public void mostrarDataCargada(ObtenerRespuestaRevocaEspResponse.ObtenerRevocaEspResponse obtenerRevocaEspResponse) {
        Log.d(TAG, "mostrarDataCargada : "+ obtenerRevocaEspResponse.getNombreCalidad() );
        textViewMotivoRevocacion.setText(obtenerRevocaEspResponse.getNombreMotivo());
        textViewCalidadTrabajo.setText(obtenerRevocaEspResponse.getNombreCalidad());
        textViewSolucionRevocante.setText(obtenerRevocaEspResponse.getNombreSolucion());
        textViewPorcentaje2.setText(obtenerRevocaEspResponse.getPorcentajeTrabajo());
        textInputEditTextDetalles.setText(obtenerRevocaEspResponse.getDetallesRevocacion());

    }

    @Override
    public void deshabilitarWidget() {
        textInputEditTextDetalles.setEnabled(false);
        textInputEditTextDetalles.setTextColor(getResources().getColor(R.color.md_black_1000));
        buttonEnviar.setEnabled(false);
        buttonEnviar.setBackgroundResource(R.drawable.btn_bg_disable);
    }
}
