package com.application.boxmadikv1.cliente.menu.crearPropuesta;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter.RubrosAdapter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.AtencionActivity;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource.RubrosRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource.local.RubrosLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.OficiosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.listener.OficioListener;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.listener.RubrosListener;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.useCase.ListarRubros;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RubrosActivity extends BaseActivity<RubrosView, RubrosPresenter> implements RubrosView, RubrosListener, OficioListener {

    public static final String TAG = RubrosActivity.class.getSimpleName();

    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    RubrosAdapter rubrosAdapter;
    SecurePreferences preferences;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RubrosPresenter getPresenter() {
        RubrosRepository rubrosRepository = RubrosRepository.getmInstance(
                new RubrosLocal(InjectorUtils.provideRubroDao(),
                        InjectorUtils.provideOficioDao()));
        return new RubrosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListarRubros(rubrosRepository));
    }

    @Override
    protected RubrosView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_cliente_rubro);
        ButterKnife.bind(this);
        initVistas();
    }

    private void initVistas() {
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        rubrosAdapter = new RubrosAdapter(new ArrayList<RubrosUi>(), this, this);
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(rubrosAdapter);
        rubrosAdapter.setRecyclerView(reciclador);
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onKeyPais(codigoPais);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaRubros(List<RubrosUi> rubrosUiList) {
        rubrosAdapter.mostrarLista(rubrosUiList);
    }

    @Override
    public void mostrarListaOficiosTrue(RubrosUi rubrosUi) {
        rubrosAdapter.mostrarListaOficiosTrue(rubrosUi);
    }

    @Override
    public void mostrarListaOficiosFalse(RubrosUi rubrosUi) {
        rubrosAdapter.mostrarListaOficiosFalse(rubrosUi);
    }

    @Override
    public void startActivityEspecialidadesNecesarias(int rubroId, int oficioId, String imagenRubro, String nombreOficio,String codigoPais) {
        Log.d(TAG, "startActivityEspecialidadesNecesarias : " + imagenRubro + " /  " + nombreOficio);
        /*Intent intent = new Intent(this, EspecialidadActivity.class);
        intent.putExtra("rubroId", rubroId);
        intent.putExtra("oficioId", oficioId);
        intent.putExtra("imagenRubro", imagenRubro);
        intent.putExtra("nombreOficio", nombreOficio);
        intent.putExtra("codigoPais",codigoPais);
        startActivity(intent);*/


        Intent intent = new Intent(RubrosActivity.this, AtencionActivity.class);
        intent.putExtra("rubroId", rubroId);
        intent.putExtra("oficioId", oficioId);
        intent.putExtra("imageRubro", imagenRubro);
        intent.putExtra("nombreOficio", nombreOficio);
      /*  intent.putExtra("posicionTipoPrecio", posicionTipoPrecio);
        intent.putExtra("posicionTipoTurno", posicionTipoTurno);
        intent.putExtra("posicionTipoDias", posicionTipoDias);*/
       // intent.putExtra("mylist", listaIdEspecialidades);
        intent.putExtra("codigoPais",codigoPais);
        startActivity(intent);
    }


    @Override
    public void onClickItemRubro(RubrosUi rubrosUi) {
        if (presenter != null) presenter.onClickItemRubro(rubrosUi);
    }

    @Override
    public void onClickItemOficio(OficiosUi oficiosUi) {
        if (presenter != null) presenter.onClickItemOficio(oficiosUi);
    }
}
