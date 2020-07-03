package com.application.boxmadikv1.terminosCondiciones;

import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.terminosCondiciones.dataSource.TerminosCondicionesRepository;
import com.application.boxmadikv1.terminosCondiciones.dataSource.local.TerminosCondicionesLocal;
import com.application.boxmadikv1.terminosCondiciones.useCase.ObtenerTerminos;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public abstract class TerminosCondicionesActivity extends BaseActivity<TerminosCondicionesView, TerminosCondicionesPresenter> implements TerminosCondicionesView {

    public static final String TAG = TerminosCondicionesActivity.class.getSimpleName();

    @BindView(R.id.txt_terminos)
    TextView textViewTerminosCondiciones;
    @BindView(R.id.txt_tipo_terminos)
    TextView textViewTipoTituloTerminos;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    /*CheckBox*/
    @BindView(R.id.checkBoxTerminos)
    CheckBox checkBoxTerminos;




    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected TerminosCondicionesPresenter getPresenter() {
        TerminosCondicionesRepository terminosCondicionesRepository = TerminosCondicionesRepository.getmInstance(new TerminosCondicionesLocal());
        return new TerminosCondicionesPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources()
                , new ObtenerTerminos(terminosCondicionesRepository));
    }

    @Override
    protected TerminosCondicionesView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_terminos_condiciones);
        ButterKnife.bind(this);
        initToolbar();

        textViewTerminosCondiciones.setMovementMethod(new ScrollingMovementMethod());
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @OnClick({R.id.btnSiguente, R.id.linerAceptar})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnSiguente:
                presenter.onClickSiguiente();
                break;
            case R.id.linerAceptar:
                presenter.onAceptarTerminosCondiciones();
                break;
        }
    }

    @Override
    public void mostrarCheckTrue() {
        checkBoxTerminos.setChecked(true);
    }

    @Override
    public void mostrarCheckFalse() {
        checkBoxTerminos.setChecked(false);
    }

    @Override
    public void mostrarMensaje(String s) {
        Snackbar.make(progressBar, s, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void mostrarDatosTerminos(String textoTermino, String tipoEstados) {
        textViewTerminosCondiciones.setText(textoTermino);
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
    public void mostrarTituloTerminos(String tituloTerminos) {
        // String mostrarTitulos = getString(R.string.mensaje_confirmacion_terminos_condiciones,tituloTerminos)
        String mostrarTitulos = getResources().getString(R.string.mensaje_confirmacion_terminos_condiciones) + " " + tituloTerminos;
        Resources res = getResources();
        //  String text = res.getString(R.string.mensaje_confirmacion_terminos_condiciones,  tituloTerminos);
        textViewTipoTituloTerminos.setText(mostrarTitulos);
    }
}
