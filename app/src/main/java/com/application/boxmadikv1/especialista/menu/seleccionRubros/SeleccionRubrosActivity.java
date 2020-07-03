package com.application.boxmadikv1.especialista.menu.seleccionRubros;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.adapter.SeleccionRubrosAdapter;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource.SeleccionRubrosRepository;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource.local.SeleccionRubrosLocal;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.listener.SeleccionRubrosListener;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.useCase.ListarSeleccionRubros;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeleccionRubrosActivity extends BaseActivity<SeleccionRubrosView, SeleccionRubrosPresenter> implements SeleccionRubrosView, SeleccionRubrosListener {

    public static final String TAG = SeleccionRubrosActivity.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    @BindView(R.id.btnSiguiente)
    Button buttonSiguiente;
    SeleccionRubrosAdapter seleccionRubrosAdapter;
    private TinyDB tinydb;
    private SecurePreferences preferences;


    /*public static void startSeleccionRubros(Context context) {
        Intent intent = new Intent(context, SeleccionRubrosActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }*/

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected SeleccionRubrosPresenter getPresenter() {
        SeleccionRubrosRepository seleccionRubrosRepository = new SeleccionRubrosRepository(new SeleccionRubrosLocal());
        return new SeleccionRubrosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListarSeleccionRubros(seleccionRubrosRepository));
    }

    @Override
    protected SeleccionRubrosView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onKeyUser(keyUser, codigoPais);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_especialista_sele_rubros);
        ButterKnife.bind(this);
        initAdapter();
        initValidarSeleccionRubros();
    }

    private void initValidarSeleccionRubros() {
        tinydb = new TinyDB(getApplicationContext());
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        //presenter.onValidateSeleccionRubros(tinydb);
        // tinydb.putListString("mylist", stringListIdRubros);
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        seleccionRubrosAdapter = new SeleccionRubrosAdapter(new ArrayList<SeleccionRubrosUi>(), this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(seleccionRubrosAdapter);
        seleccionRubrosAdapter.setRecyclerView(recyclerView);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaRubrosSel(List<SeleccionRubrosUi> seleccionRubrosUiList) {
        seleccionRubrosAdapter.mostrarLista(seleccionRubrosUiList);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(progressBar, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarCheckTrue(SeleccionRubrosUi seleccionRubrosUi) {
        seleccionRubrosAdapter.mostrarItemCheckTrue(seleccionRubrosUi);
    }

    @Override
    public void mostrarOcultarFalse(SeleccionRubrosUi seleccionRubrosUi) {
        seleccionRubrosAdapter.mostrarItemCheckFalse(seleccionRubrosUi);
    }

    @Override
    public void startMenuEspecialista(ArrayList<String> stringListIdRubros) {
        tinydb.putListString("mylist", stringListIdRubros);
        finish();
        String tipoInicial = "0";
        Intent intent = new Intent(this, EspecialistaPerfilDistritoActivity.class);
        intent.putExtra("tipoInicial", tipoInicial);
        startActivity(intent);
        //  presenter.onValidarDepartmentZonaTrabajo();
    }

    @Override
    public void startMenuEspecialistaLlena(ArrayList<String> arrayList) {
        tinydb.putListString("mylist", arrayList);
        Intent intent = new Intent(this, MenuEspecialistaActivity.class);
        intent.putExtra("mylist", arrayList);
        startActivity(intent);
    }

    @Override
    public void finishActivityRubros(ArrayList<String> arrayList) {
        tinydb.putListString("mylist", arrayList);
        finish();
        Toast.makeText(getApplicationContext(), "Datos Actualizados Correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void habilitarButtonSiguiente() {
        buttonSiguiente.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonSiguiente() {
        buttonSiguiente.setEnabled(false);
    }

   /* @Override
    public void actualizarLista() {
        seleccionRubrosAdapter
    }*/


    @Override
    public void onClickItemRubro(SeleccionRubrosUi seleccionRubrosUi) {
        Log.d(TAG, "onClickItemRubro : ");
        presenter.onClickItemRubroSeleccion(seleccionRubrosUi);
    }

    @OnClick({R.id.btnSiguiente})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnSiguiente:
                String departamentoId = preferences.getString(Constantes.KEY_SECURE_USUARIO_ESPE_CODIGO_DEPARTAMENT);
                presenter.onClickSiguiente(departamentoId);
                // startActivity(new Intent(this, MenuEspecialistaActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed : ");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
