package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.listener.BuscarListener;
import com.application.boxmadikv1.dao.InjectorUtils;

import com.application.boxmadikv1.dialogDatePicker.DatePickerFragment;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.EspecialistaPerfilActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.adapter.BuscarCentroEstudiosAdapter;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.adapter.BuscarTipoEstudioAdapter;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.adapter.EstudioPerfilAdapter;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.EstudioPerfilRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.local.EstudiosPerfilLocal;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.remote.EstudioPerfilRemote;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.DatosCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.listener.CursosListener;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.useCase.ListarTipoCentroEstudios;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.useCase.ListarTipoEstudios;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EstudioPerfilActivity extends BaseActivity<EstudioPerfilView, EstudioPerfilPresenter> implements
        EstudioPerfilView,
        BuscarListener, CursosListener {

    public static final String TAG = EstudioPerfilActivity.class.getSimpleName();
    public static final String EXTRA_ESPE_CENTRO_ESTUDIOS = "EstudioPerfilActivity";

    private BuscarTipoEstudioAdapter buscarTipoEstudioAdapter;
    private BuscarCentroEstudiosAdapter buscarCentroEstudiosAdapter;
    private EstudioPerfilAdapter estudioPerfilAdapter;
    @BindView(R.id.autoCompleteTextViewCurso)
    AutoCompleteTextView autoCompleteTextViewCurso;
    @BindView(R.id.autoCompleteTextCentroEstudios)
    AutoCompleteTextView autoCompleteTextCentroEstudios;
    @BindView(R.id.reciclador)
    RecyclerView recyclerView;
    @BindView(R.id.editTextNombreCurso)
    EditText editTextNombreCurso;
    @BindView(R.id.editFechaInicio)
    Button buttonFechaInicio;
    @BindView(R.id.editFechaFin)
    Button buttonFechaFin;
    @BindView(R.id.btnGuardar)
    Button buttonGuardar;
    //progress dialog
    private ProgressDialog progressDialog;

    private SecurePreferences preferences;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected EstudioPerfilPresenter getPresenter() {
        EstudioPerfilRepository estudioPerfilRepository = EstudioPerfilRepository.getmInstance(new EstudiosPerfilLocal(
                        InjectorUtils.provideTipoEstudiosDao(),
                        InjectorUtils.provideCentroEstudiosDao()
                ),
                new EstudioPerfilRemote());
        return new EstudioPerfilPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new ListarTipoCentroEstudios(estudioPerfilRepository),
                new ListarTipoEstudios(estudioPerfilRepository));
    }

    @Override
    protected EstudioPerfilView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_especialista_editar_perfil_estudio);
        ButterKnife.bind(this);
        initAdapter();

    }

    private void initAdapter() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        progressDialog = new ProgressDialog(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        estudioPerfilAdapter = new EstudioPerfilAdapter(new ArrayList<DatosCursos>(), this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(estudioPerfilAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onOcultarTeclado() {
        Log.d(TAG, "OCULTARTECLADO");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(autoCompleteTextCentroEstudios.getWindowToken(), 0);
    }

    @Override
    public void mostrarListaAutoCompleteTipoEstudios(List<TipoEstudiosUi> tipoCentroEstudiosUiList) {
        buscarTipoEstudioAdapter = new BuscarTipoEstudioAdapter(this, tipoCentroEstudiosUiList, this);
        autoCompleteTextViewCurso.setAdapter(buscarTipoEstudioAdapter);
        autoCompleteTextViewCurso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoEstudiosUi tipoEstudiosUi = (TipoEstudiosUi) parent.getAdapter().getItem(position);
                presenter.onClickAutoCompleteTipoEstudio(tipoEstudiosUi);
                Log.d(TAG, "especialidadUi : " + tipoEstudiosUi.getDescripcion());

            }
        });

    }

    @Override
    public void mostrarListaAutoCompleteTipoCentroEstudios(List<TipoCentroEstudiosUi> tipoCentroEstudiosUiList) {
        // autoCompleteTextViewCurso.setEnabled(false);
        // autoCompleteTextCentroEstudios.setEnabled(false);
        buscarCentroEstudiosAdapter = new BuscarCentroEstudiosAdapter(this, tipoCentroEstudiosUiList, this);
        autoCompleteTextCentroEstudios.setAdapter(buscarCentroEstudiosAdapter);
        autoCompleteTextCentroEstudios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoCentroEstudiosUi tipoCentroEstudiosUi = (TipoCentroEstudiosUi) parent.getAdapter().getItem(position);
                presenter.onClickAutoCompleteTipoCentro(tipoCentroEstudiosUi);
                //autoCompleteTextCentroEstudios.setEnabled(false);
                Log.d(TAG, "especialidadUi : " + tipoCentroEstudiosUi.getDescripcion());
            }
        });

    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(editTextNombreCurso, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void limpiarEditTextTipoEstudio() {
        autoCompleteTextViewCurso.setText(null);
        autoCompleteTextViewCurso.setEnabled(true);
    }

    @Override
    public void limpiarEditTextTipoCentroEstudio() {
        autoCompleteTextCentroEstudios.setText(null);
        autoCompleteTextViewCurso.setEnabled(true);
    }

    @Override
    public void mostrarDialogFechaInicio() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondateDateInicio);
        date.show(getSupportFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondateDateInicio = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            /*String dateTime = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year);*/
            String dateTime = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(dayOfMonth);
            buttonFechaInicio.setText(dateTime);

            String mesNombre = new DateFormatSymbols().getMonths()[monthOfYear];
            String anio = String.valueOf(year);
            presenter.onAceptarDateInicio(dateTime, mesNombre, anio);
            // Log.d(TAG,"ondateDateInicio : " + view.getd)
        }
    };

    @Override
    public void mostrarDialogFechaFin() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondateDateFin);
        date.show(getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void agregarItemDataCurso(DatosCursos datosCursos) {
        estudioPerfilAdapter.agregarItem(datosCursos);
    }

    @Override
    public void mostrarTextTipoEstudio(String s) {
        autoCompleteTextViewCurso.setText(s);
    }

    @Override
    public void mostrarTextTipoCentro(String s) {
        autoCompleteTextCentroEstudios.setText(s);
    }

    @Override
    public void elimnarItemCurso(DatosCursos datosCursos) {
        estudioPerfilAdapter.eliminarItem(datosCursos);
    }

    @Override
    public void obtenerKeyUser() {
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String fotoUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_FOTO);
        String paisCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        Log.d(TAG, "obtenerKeyUser : " + paisCodigo);
        presenter.onKeyUser(keyUser, fotoUser, paisCodigo);
    }

    @Override
    public void mostrarProgressBarDialog() {
        progressDialog.setMessage("Sincronizando Datos......");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressBarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void actualizarDataPreferencesConFoto(String nombreEdit, String apellidosEdit, String celularEdit, String usuFoto) {
        preferences.put(Constantes.KEY_SECURE_USUARIO_NOMBRE, nombreEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_APELLIDOS, apellidosEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CELULAR, celularEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_FOTO, usuFoto);
        String usuFotox = preferences.getString(Constantes.KEY_SECURE_USUARIO_FOTO);
        Log.d(TAG, "usuFotox : " + usuFotox);
    }

    @Override
    public void iniStartActivityMenuEspecialista(String estado) {
        /*Intent intent = new Intent(this, MenuEspecialistaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("estado", estado);
        startActivity(intent);*/
        Toast.makeText(getApplicationContext(), "Datos Guardados Correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void mostrarLista(List<DatosCursos> datosCursosList) {
        estudioPerfilAdapter.mostrarLista(datosCursosList);
    }

    @Override
    public void limpiarTextPuesto() {
        autoCompleteTextViewCurso.setText(null);
    }

    @Override
    public void limpiarTextCentro() {
        autoCompleteTextCentroEstudios.setText(null);
    }

    @Override
    public void limpiartextNombreCurso() {
        editTextNombreCurso.setText(null);
    }


    @Override
    public void limpiarFechaInicio() {
        // buttonFechaInicio.setText(null);
        buttonFechaInicio.setText("Fecha Inicio");
    }

    @Override
    public void limpiarFechaFin() {
        // buttonFechaFin.setText(null);
        buttonFechaFin.setText("Fecha Fin");
    }

    @Override
    public void deshabilitarText() {
        autoCompleteTextViewCurso.setEnabled(false);
        autoCompleteTextCentroEstudios.setEnabled(false);
    }

    @Override
    public void habilitarButtonGuardar() {
        buttonGuardar.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonGuardar() {
        buttonGuardar.setEnabled(false);
    }


    DatePickerDialog.OnDateSetListener ondateDateFin = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            /*String dateTime = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year);*/
            String dateTime = String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(dayOfMonth);
            String mesNombre = new DateFormatSymbols().getMonths()[monthOfYear];
            String anio = String.valueOf(year);
            buttonFechaFin.setText(dateTime);
            presenter.onAceptarDateFin(dateTime, mesNombre, anio);
        }
    };


    @OnClick({R.id.btnAgregarItem, R.id.editFechaInicio, R.id.editFechaFin, R.id.btnGuardar, R.id.imageViewClosePuesto, R.id.imageViewCloseCentro})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.btnAgregarItem:
                String nombreCurso = editTextNombreCurso.getText().toString();
                presenter.onClickAgregarItem(nombreCurso);
                break;
            case R.id.editFechaInicio:
                presenter.onClickFechaInicio();
                break;
            case R.id.editFechaFin:
                presenter.onClickFechaFin();
                break;
            case R.id.btnGuardar:
                presenter.onClickGuardar();
                break;
            case R.id.imageViewClosePuesto:
                presenter.onClickDeletePuesto();
                break;
            case R.id.imageViewCloseCentro:
                presenter.onClickDeleteCentro();
                break;
        }

    }

    @Override
    public void onLongClickCursoEliminar(final DatosCursos datosCursos) {
        String mensaje = "Esta Seguro que desea eliminar Curso Agregado";
        AlertDialog.Builder dialog = new AlertDialog.Builder(EstudioPerfilActivity.this);
        dialog.setTitle("Eliminar")
                .setIcon(R.drawable.ic_delete_icon)
                .setMessage(mensaje)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        presenter.onLongClickCursoEliminar(datosCursos);
                    }
                }).show();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccion_cliente_detalle, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ventana_principal) {
            Intent intent = new Intent(this, EspecialistaPerfilActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        } else {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
