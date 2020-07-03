package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.AtencionActivity;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.adapter.BuscarEspecialidadAdapter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.adapter.EspecialidadItemAdapter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource.EspecialidadRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource.local.EspecialidadLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.listener.BuscarListener;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.listener.EspecialidadListener;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.useCase.ListarAutoComplete;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EspecialidadActivity extends BaseActivity<EspecialidadView, EspecialidadPresenter> implements EspecialidadView, EspecialidadListener, BuscarListener {

    public static final String TAG = EspecialidadActivity.class.getSimpleName();
    public static final int SECOND_ACTIVITY_REQUEST_CODE_ESPECIALIDAD = 0;

    @BindView(R.id.autoCompleteTextView1)
    AutoCompleteTextView autoCompleteTextViewEspecialidad;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubroPerf;
    @BindView(R.id.textViewNombreOficio)
    TextView textViewNombreOficio;

    @BindView(R.id.recicladorItems)
    RecyclerView recyclerView;

    EspecialidadItemAdapter especialidadItemAdapter;
    BuscarEspecialidadAdapter buscarEspecialidadAdapter;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected EspecialidadPresenter getPresenter() {
        EspecialidadRepository especialidadRepository = EspecialidadRepository.getmInstance(
                new EspecialidadLocal(InjectorUtils.provideEspecialidadesDao()));
        return new EspecialidadPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler())
                , getResources()
                , new ListarAutoComplete(especialidadRepository));
    }

    @Override
    protected EspecialidadView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_cliente_especialidad);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        especialidadItemAdapter = new EspecialidadItemAdapter(new ArrayList<EspecialidadUi>(), this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(especialidadItemAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarVistaCabecera(String imagenRubro, String nombreOficio) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true);
        Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(imagenRubro).into(imageViewRubroPerf);
        textViewNombreOficio.setText(nombreOficio);
    }

    @Override
    public void mostrarListaAutoComplete(List<EspecialidadUi> especialidadUiList) {
        buscarEspecialidadAdapter = new BuscarEspecialidadAdapter(this, especialidadUiList, this);
        autoCompleteTextViewEspecialidad.setAdapter(buscarEspecialidadAdapter);
        autoCompleteTextViewEspecialidad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EspecialidadUi especialidadUi = (EspecialidadUi) parent.getAdapter().getItem(position);
                presenter.onClickAutoComplete(especialidadUi);
                Log.d(TAG, "especialidadUi : " + especialidadUi.getDescripcion());
            }
        });
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(recyclerView, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void agregarItemEspecialidad(EspecialidadUi especialidadUi) {
        especialidadItemAdapter.agregarItem(especialidadUi);
    }

    @Override
    public void validarItemsAgregar(EspecialidadUi especialidadUi) {
        int contarItem = especialidadItemAdapter.getItemCount();
        if (especialidadItemAdapter == null) return;
        presenter.onValidarItemsAgregar(contarItem, especialidadUi);
    }

    @Override
    public void startActivityHorarioAtencion(ArrayList<String> listaIdEspecialidades, int idRubro, int idOficio, String imageRubro, String nombreOficio,
                                             int posicionTipoPrecio, int posicionTipoTurno, int posicionTipoDias,String codigoPais) {
        Intent intent = new Intent(EspecialidadActivity.this, AtencionActivity.class);
        intent.putExtra("rubroId", idRubro);
        intent.putExtra("oficioId", idOficio);
        intent.putExtra("imageRubro", imageRubro);
        intent.putExtra("nombreOficio", nombreOficio);
        intent.putExtra("posicionTipoPrecio", posicionTipoPrecio);
        intent.putExtra("posicionTipoTurno", posicionTipoTurno);
        intent.putExtra("posicionTipoDias", posicionTipoDias);
        intent.putExtra("mylist", listaIdEspecialidades);
        intent.putExtra("codigoPais",codigoPais);
        // startActivity(intent);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE_ESPECIALIDAD);
    }

    @Override
    public void mostrarOnclickLista(List<EspecialidadUi> listaEspecialidadCompleta) {
        // buscarEspecialidadAdapter.agregandoListaOnClick(listaEspecialidadCompleta);
    }

    @Override
    public void ClearEditText() {
        autoCompleteTextViewEspecialidad.getText().clear();
    }

    @Override
    public void eliminarEspecialidad(EspecialidadUi especialidadUi) {
        especialidadItemAdapter.eliminarItem(especialidadUi);
    }


    @OnClick({R.id.btnAgregar, R.id.btnSiguiente, R.id.autoCompleteTextView1})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnAgregar:
                String tipoEspec = autoCompleteTextViewEspecialidad.getText().toString();
                presenter.onClickAgregarItem(tipoEspec);
                ClearEditText();
                onOcultarTeclado();

                break;
            case R.id.btnSiguiente:
                presenter.onClickSiguiente();
                break;
            case R.id.autoCompleteTextView1:
                Log.d(TAG, "autoCompleteTextView1");
                presenter.onClickEspecialidad();
                //buscarEspecialidadAdapter.getFilter().convertResultToString()
                break;
        }

    }

    @Override
    public void onItemClickEspecialidadDelete(EspecialidadUi especialidadUi) {
//        especialidadItemAdapter.eliminarItem(especialidadUi);
        presenter.onItemClickEspecialidadDelete(especialidadUi);

    }

    @Override
    public void onOcultarTeclado() {
        Log.d(TAG, "OCULTARTECLADO");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(autoCompleteTextViewEspecialidad.getWindowToken(), 0);

        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d(TAG, "onKeyDown : ");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed : ");
        return;
        //super.onBackPressed();
    }


    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "ACtivity:onActivityResult");
        presenter.onActivityResult(requestCode, resultCode, data);
        //super.onActivityResult(requestCode, resultCode, data);
    }
}
