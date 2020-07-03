package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarDepartamentoAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarDistritoAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarProvinciaAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.adapter.DistritoAdapter;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.EspecialistaPerfilDistritoRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.local.EspecialistaPerfilDistritoLocal;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad.DireccionUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.listener.DistritoListener;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.useCase.ListaDepartamento;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.useCase.ListaDistrito;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.useCase.ListaProvincia;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.presentacion.PresentacionPerfilActivity;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EspecialistaPerfilDistritoActivity extends BaseActivity<EspecialistaPerfilDistritoView, EspecialistaPerfilDistritoPresenter> implements EspecialistaPerfilDistritoView, DistritoListener {

    public static final String TAG = EspecialistaPerfilDistritoActivity.class.getSimpleName();

    public static final String EXTRA_PERFIL_ACTIVITY_PERFIL_DIRECCION = "direccionUi";

    public static final String EXTRA_PERFIL_ZONA_TRABAJO = "EspecialistaPerfilDistritoActivity";

    @BindView(R.id.autoCompleteTextViewDepartamento)
    AutoCompleteTextView autoCompleteTextViewDepartamento;
    @BindView(R.id.autoCompleteTextViewProvincia)
    AutoCompleteTextView autoCompleteTextViewProvincia;
    @BindView(R.id.autoCompleteTextViewDistrito)
    AutoCompleteTextView autoCompleteTextViewDistrito;
    @BindView(R.id.recicladorItems)
    RecyclerView recyclerView;
    @BindView(R.id.btnRegistrar)
    Button buttonRegistrar;
    @BindView(R.id.btnSiguiente)
    Button buttonGrabar;


    private DistritoAdapter distritoAdapter;
    private SecurePreferences preferences;
    private ProgressDialog progressDialog;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected EspecialistaPerfilDistritoPresenter getPresenter() {
        EspecialistaPerfilDistritoRepository especialistaPerfilDistritoRepository = EspecialistaPerfilDistritoRepository.getmInstance(new EspecialistaPerfilDistritoLocal(
                InjectorUtils.provideDepartamentoDao(),
                InjectorUtils.provideProvinciaDao(),
                InjectorUtils.provideDistritoDao()
        ));
        return new EspecialistaPerfilDistritoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListaDepartamento(especialistaPerfilDistritoRepository),
                new ListaProvincia(especialistaPerfilDistritoRepository),
                new ListaDistrito(especialistaPerfilDistritoRepository));
    }

    @Override
    protected EspecialistaPerfilDistritoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_especialista_editar_perfil_distrito);
        ButterKnife.bind(this);
        initAdapter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onKeyUser(keyUser, codigoPais);
    }

    private void initAdapter() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        progressDialog = new ProgressDialog(this);
        distritoAdapter = new DistritoAdapter(new ArrayList<TipoDistritoUi>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(distritoAdapter);

    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarListaTipoDepartamento(List<TipoDepartamentoUi> tipoDepartamentoUis) {
        BuscarDepartamentoAdapter buscarDepartamentoAdapter = new BuscarDepartamentoAdapter(this, tipoDepartamentoUis);
        autoCompleteTextViewDepartamento.setAdapter(buscarDepartamentoAdapter);

        autoCompleteTextViewDepartamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick");
                TipoDepartamentoUi tipoDepartamentoUi = (TipoDepartamentoUi) parent.getAdapter().getItem(position);
                presenter.onSpinnerTipoDepartamento(tipoDepartamentoUi);
                autoCompleteTextViewDepartamento.setEnabled(false);
                autoCompleteTextViewProvincia.setEnabled(true);
                Log.d(TAG, "especialidadUi : " + tipoDepartamentoUi.getDescripcion());
            }
        });
    }

    @Override
    public void mostrarListaTipoProvincia(List<TipoProvinciaUi> tipoProvinciaUis) {
        BuscarProvinciaAdapter buscarProvinciaAdapter = new BuscarProvinciaAdapter(this, tipoProvinciaUis);
        autoCompleteTextViewProvincia.setAdapter(buscarProvinciaAdapter);
        autoCompleteTextViewProvincia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoProvinciaUi tipoProvinciaUi = (TipoProvinciaUi) parent.getAdapter().getItem(position);
                presenter.onSpinnerTipoProvincia(tipoProvinciaUi);
                autoCompleteTextViewDepartamento.setEnabled(false);
                autoCompleteTextViewProvincia.setEnabled(false);
                autoCompleteTextViewDistrito.setEnabled(true);
            }
        });
    }

    @Override
    public void mostrarListaTipoDistrito(List<TipoDistritoUi> tipoDistritoUis) {
        Log.d(TAG, "mostrarListaTipoDistrito : " + tipoDistritoUis.size());
        BuscarDistritoAdapter buscarDistritoAdapter = new BuscarDistritoAdapter(this, tipoDistritoUis);
        autoCompleteTextViewDistrito.setAdapter(buscarDistritoAdapter);
        autoCompleteTextViewDistrito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoDistritoUi tipoDistritoUi = (TipoDistritoUi) parent.getAdapter().getItem(position);
                presenter.onSpinnerTipoDistrito(tipoDistritoUi);
                Log.d(TAG, "tipoDistritoUi : " + position);
            }
        });
    }

    @Override
    public void mostrarTextViewProvincia(String s) {
        autoCompleteTextViewProvincia.setText("Prov: " + s);
    }

    @Override
    public void mostrarTextViewDepartamento(String s) {
        autoCompleteTextViewDepartamento.setText("Dpto: " + s);
    }

    @Override
    public void mostrarTextViewDistrito(String s) {
        autoCompleteTextViewDistrito.setText("Dtto: " + s);
    }

    @Override
    public void mostrarMensaje(String escriba_o_seleccione_una_especialidad) {
        Toast.makeText(getApplicationContext(), escriba_o_seleccione_una_especialidad, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validarItemsAgregar(TipoDistritoUi tipoDistritoUi) {
        int contarItem = distritoAdapter.getItemCount();
        if (distritoAdapter == null) return;
        presenter.onValidarItemsAgregar(contarItem, tipoDistritoUi);
    }

    @Override
    public void agregarItemDistrito(TipoDistritoUi tipoDistritoUi) {
        distritoAdapter.agregarItem(tipoDistritoUi);
        autoCompleteTextViewDistrito.setText(null);
    }

    @Override
    public void eliminarEspecialidad(TipoDistritoUi tipoDistritoUi) {
        distritoAdapter.eliminarItem(tipoDistritoUi);
    }

    @Override
    public void mostrarMensajeErrorAutoCompleteProvinciaError(String mensaje) {
        autoCompleteTextViewProvincia.setError(mensaje);
    }

    @Override
    public void mostrarMensajeErrorAutoCompleteDepartamentoError(String mensaje) {
        autoCompleteTextViewDepartamento.setError(mensaje);
    }

    @Override
    public void initStartActivityPresentacionActivity(DireccionUi direccionUi) {
        Intent intent = new Intent(this, PresentacionPerfilActivity.class);
        intent.putExtra(EXTRA_PERFIL_ACTIVITY_PERFIL_DIRECCION, direccionUi);
        startActivity(intent);
    }

    @Override
    public void mostrarProgressBarDialog() {
        progressDialog.setMessage("Verificando el Registro Espere..");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressBarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void initStartActivityMenuEspecialista(String departamentoCodigo, String tipoUsuarioEspec) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_ESPE_CODIGO_DEPARTAMENT, departamentoCodigo);
        preferences.put(Constantes.KEY_SECURE_USUARIO_TIPO, tipoUsuarioEspec);
        Intent intent = new Intent(this, MenuEspecialistaActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setMostrarLista(List<TipoDistritoUi> distritoUiList) {
        distritoAdapter.setDistritoUis(distritoUiList);
    }

    @Override
    public void habilitasFunciones() {
        autoCompleteTextViewDepartamento.setEnabled(true);
        autoCompleteTextViewProvincia.setEnabled(true);
        autoCompleteTextViewDistrito.setEnabled(true);
        recyclerView.setEnabled(true);
        buttonRegistrar.setEnabled(true);
        buttonGrabar.setEnabled(true);
    }


    @OnClick({R.id.btnRegistrar, R.id.btnSiguiente, R.id.imageViewCloseDepart, R.id.imageViewCloseProvincia, R.id.imageViewCloseDistrito})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnRegistrar:
                String distritoNombre = autoCompleteTextViewDistrito.getText().toString();
                presenter.onClickAgregarDistritos(distritoNombre);
                break;
            case R.id.btnSiguiente:
                presenter.onClickSiguiente();
                break;
            case R.id.imageViewCloseDepart:
                presenter.onClickCloseDepart();
                break;
            case R.id.imageViewCloseProvincia:
                presenter.onClickCloseProv();
                break;
            case R.id.imageViewCloseDistrito:
                presenter.onClickCloseDistrit();
                break;
        }
    }

    @Override
    public void onClickEliminar(TipoDistritoUi tipoDistritoUi) {
        Log.d(TAG, "onClickEliminar +" + tipoDistritoUi.getDescripcion());
        presenter.onClickEliminar(tipoDistritoUi);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void deshabilitaFunciones() {
        autoCompleteTextViewDepartamento.setEnabled(false);
        autoCompleteTextViewProvincia.setEnabled(false);
        autoCompleteTextViewDistrito.setEnabled(false);
        recyclerView.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonGrabar.setEnabled(false);

    }


    @Override
    public void finishActivity(String codigoDepartamento) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_ESPE_CODIGO_DEPARTAMENT, codigoDepartamento);
        String mensaje = "Datos ingresados Correctamente ";
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void limpiarDepartNull() {
        autoCompleteTextViewDepartamento.setText(null);
        autoCompleteTextViewDepartamento.setEnabled(true);

        autoCompleteTextViewProvincia.setText(null);
        autoCompleteTextViewProvincia.setEnabled(false);

        autoCompleteTextViewDistrito.setText(null);
        autoCompleteTextViewDistrito.setEnabled(false);


    }

    @Override
    public void limpiarProvNull() {
        autoCompleteTextViewProvincia.setText(null);
        autoCompleteTextViewProvincia.setEnabled(true);

        autoCompleteTextViewDistrito.setEnabled(false);
        autoCompleteTextViewDistrito.setText(null);
    }

    @Override
    public void limpiarDistriNull() {
        autoCompleteTextViewDistrito.setText(null);
    }

    @Override
    public void deshabilitaProvDist() {
        autoCompleteTextViewProvincia.setEnabled(false);
        autoCompleteTextViewDistrito.setEnabled(false);
    }

    @Override
    public void limpiarListaDistritos(List<TipoDistritoUi> listaDistritos) {
        Log.d(TAG, "limpiarListaDistritos ");

        distritoAdapter.limpiarVista(listaDistritos);
    }

    @Override
    public void deshabilitarButtonGuardar() {
        buttonGrabar.setEnabled(false);
    }

    @Override
    public void habilitarButtonGuardar() {
        buttonGrabar.setEnabled(true);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

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
}
