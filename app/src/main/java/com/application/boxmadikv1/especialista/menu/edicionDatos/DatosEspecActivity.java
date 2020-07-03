package com.application.boxmadikv1.especialista.menu.edicionDatos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cambiarClave.CambiarClaveActivity;
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.dao.departamento.DepartamentoDaoImpl;
import com.application.boxmadikv1.dao.distrito.DistritoDaoImpl;
import com.application.boxmadikv1.dao.provincia.ProvinciaDaoImpl;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.edicionDatos.adapter.DatosEspecAdapter;
import com.application.boxmadikv1.especialista.menu.edicionDatos.dataSource.DatosEspecRepository;
import com.application.boxmadikv1.especialista.menu.edicionDatos.dataSource.local.DatosEspecLocal;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosBancaria;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosCentroEstudioList;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecEditDireccion;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecUi;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecZonaTrabajo;
import com.application.boxmadikv1.especialista.menu.edicionDatos.listener.DatosEspecListener;
import com.application.boxmadikv1.especialista.menu.edicionDatos.useCase.ObtenerDireccion;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.EspecEditarPerfilActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.EspecBancoActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.EspecPerfilDireccionActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.EstudioPerfilActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.presentacion.PresentacionPerfilActivity;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.SeleccionRubrosActivity;
import com.application.boxmadikv1.extra.InternetCheck;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.EspecBancoActivity.EXTRA_DATA_BANCARAIA_ACTIVITY;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.EspecPerfilDireccionActivity.EXTRA_DIRECCION_EDIT_ESPEC;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity.EXTRA_PERFIL_ZONA_TRABAJO;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.EstudioPerfilActivity.EXTRA_ESPE_CENTRO_ESTUDIOS;
import static com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.presentacion.PresentacionPerfilActivity.EXTRA_PRESENTACION_PERFIL_PRESENTACION;

public class DatosEspecActivity extends BaseActivity<DatosEspecView, DatosEspecPresenter> implements DatosEspecView,
        DatosEspecListener,InternetCheck.Consumer {

    public static final String TAG = DatosEspecActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView recyclerView;

    private SecurePreferences preferences;
    private DatosEspecAdapter datosEspecAdapter;
    private TinyDB tinyDB;

    DatosEspecActivity datosEspecActivity;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected DatosEspecPresenter getPresenter() {
        DatosEspecRepository detallesRepository = DatosEspecRepository.getInstance(
                new DatosEspecLocal(
                        new DepartamentoDaoImpl(),
                        new ProvinciaDaoImpl(),
                        new DistritoDaoImpl()
                )
        );
        return new DatosEspecPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ObtenerDireccion(detallesRepository));
    }

    @Override
    protected DatosEspecView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_especialista_datos_espec);
        ButterKnife.bind(this);
        datosEspecActivity = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        new InternetCheck(datosEspecActivity, null);
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        tinyDB = new TinyDB(getApplicationContext());
        initOnKeyUser();
        datosEspecAdapter = new DatosEspecAdapter(new ArrayList<DatosEspecUi>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(datosEspecAdapter);
    }

    private void initOnKeyUser() {
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String paisCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        Log.d(TAG, "keyUser: " + keyUser);
        presenter.onKeyUser(keyUser, paisCodigo, tinyDB);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarLista(List<DatosEspecUi> datosEspecUiList) {
        datosEspecAdapter.setMostraLista(datosEspecUiList);
    }

    @Override
    public void initStartActivityEditarPerfil() {
        startActivity(new Intent(getApplicationContext(), EspecEditarPerfilActivity.class));
    }

    @Override
    public void initStartActivityEditarDireccion(DatosEspecEditDireccion datosEspecEditDireccion) {
        Intent intent = new Intent(this, EspecPerfilDireccionActivity.class);
        intent.putExtra(EXTRA_DIRECCION_EDIT_ESPEC, datosEspecEditDireccion);
        startActivity(intent);
    }

    @Override
    public void initStartActivityEditarZonaTrabajo(DatosEspecZonaTrabajo datosEspecZonaTrabajo) {
        String tipoInicial = "1";
        Intent intent = new Intent(this, EspecialistaPerfilDistritoActivity.class);
        intent.putExtra("tipoInicial", tipoInicial);
        intent.putExtra(EXTRA_PERFIL_ZONA_TRABAJO, datosEspecZonaTrabajo);
        startActivity(intent);
    }

    @Override
    public void initStartActivityEditarPresentacion(String usuarioPresentacion) {
        Intent intent = new Intent(this, PresentacionPerfilActivity.class);
        intent.putExtra(EXTRA_PRESENTACION_PERFIL_PRESENTACION, usuarioPresentacion);
        startActivity(intent);
    }

    @Override
    public void initStartActivityEditarCursos(List<DatosCentroEstudioList> centroEstudioLists) {
        Intent intent = new Intent(this, EstudioPerfilActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_ESPE_CENTRO_ESTUDIOS, (ArrayList<? extends Parcelable>) centroEstudioLists);
        // intent.putExtra(EXTRA_ESPE_CENTRO_ESTUDIOS, centroEstudioLists);
        startActivity(intent);
    }

    @Override
    public void initStartActivityEditarCuentaBancaria(DatosBancaria datosBancaria) {
        Intent intent = new Intent(this, EspecBancoActivity.class);
        intent.putExtra(EXTRA_DATA_BANCARAIA_ACTIVITY, datosBancaria);
        startActivity(intent);
    }

    @Override
    public void estadoDireccion(boolean b, String tipoEstadoId) {
        Log.d(TAG, "estadoDireccion" + b + "/ tipoEstadoId : " + tipoEstadoId);
        datosEspecAdapter.actualizacionEstadoDireccion(b, tipoEstadoId);
    }

    @Override
    public void mostrarMensaje(String mensajeError) {
        Toast.makeText(getApplicationContext(), mensajeError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishCliente() {
        Intent intent = new Intent(this, MenuClienteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onFinishEspecialista() {
        Intent intent = new Intent(this, MenuEspecialistaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void initStartActivityEditarRubroTrabajo() {
        //startActivity(new Intent(this, SeleccionRubrosActivity.class));
        String tipoInicial = "tipoInicioEditar";
        Intent intent = new Intent(this, SeleccionRubrosActivity.class);
        intent.putExtra("tipoInicial", tipoInicial);
        startActivity(intent);
    }

    @Override
    public void initStartActivityCambiarClave() {
        Intent intent = new Intent(this, CambiarClaveActivity.class);
        startActivity(intent);
    }

    @Override
    public void mostrarDialogMensaje(String s) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(datosEspecActivity);
        dialog.setTitle("Problemas Conexión")
                .setIcon(R.mipmap.ic_boxmadick)
                .setMessage(s)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                        finish();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        //presenter.onClickRenuevaDatosGenerales(inputValidation.isNetworkAvailable());
                        new InternetCheck(datosEspecActivity,null);
                    }
                }).show();
    }

    @Override
    public void mostrarTitleToolbar(String titulo) {
        getSupportActionBar().setTitle(titulo);
    }

    @Override
    public void onClickItem(DatosEspecUi datosEspecUi) {
        presenter.onClickItem(datosEspecUi);
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
            presenter.onClickFinish();
           /* Intent intent = new Intent(this,MenuEspecialistaActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*/
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void accept(Boolean internet, Object objeto) {
        presenter.onStatusConexion(internet,objeto);
    }
}
