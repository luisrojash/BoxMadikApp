package com.application.boxmadikv1.registraUser;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.registraUser.adapter.SpinnerTipoDocumentoAdapter;
import com.application.boxmadikv1.registraUser.adapter.SpinnerTipoPaisAdapter;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioRepository;
import com.application.boxmadikv1.registraUser.dataSource.local.RegistrarUsuarioLocal;
import com.application.boxmadikv1.registraUser.dataSource.remote.RegistrarUsuarioRemote;
import com.application.boxmadikv1.registraUser.entidad.TipoDocumentoUi;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;
import com.application.boxmadikv1.registraUser.entidad.UserUi;

import com.application.boxmadikv1.registraUser.terminos.RegistroTerminosActivity;
import com.application.boxmadikv1.registraUser.useCase.ListarTipoDocumento;
import com.application.boxmadikv1.registraUser.useCase.ListarTipoPais;
import com.application.boxmadikv1.registraUser.useCase.RegistrarUsuario;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.nereo.multi_image_selector.MultiImageSelector;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public class RegistrarUserActivity extends BaseActivity<RegistrarUsuarioView, RegistrarUsuarioPresenter>
        implements RegistrarUsuarioView, AdapterView.OnItemSelectedListener {

    public static final int REQUEST_CODE_CHOOSE_IMAGE = 100;
    public static final String TAG = RegistrarUserActivity.class.getSimpleName();
    public static final int SECOND_ACTIVITY_REQUEST_CODE = 0;


    @BindView(R.id.imgProfile)
    CircleImageView circleImageViewFotos;

    /*EditText*/
    @BindView(R.id.editTextNumeroDocumento)
    EditText editTextNumeroDocumento;
    @BindView(R.id.editTextNombres)
    EditText editTextNombres;
    @BindView(R.id.editTextApellidos)
    EditText editTextApellidos;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextClave)
    EditText editTextClave;
    /*@BindView(R.id.editTextRepetirClave)
    EditText editTextRepetirClave;*/
    @BindView(R.id.editTextCelular)
    EditText editTextCelular;
    @BindView(R.id.editTextRazonSocial)
    EditText editTextRazonSocial;

    /*---EditText----*/
    /*Spinner*/
    @BindView(R.id.spinnerTipoDoc)
    Spinner spinnerTipoDocumento;
    @BindView(R.id.spinnerPaisPais)
    Spinner spinnerTipoPais;

    /*CheckBox*/
    @BindView(R.id.checkBoxTerminos)
    CheckBox checkBoxTerminos;


    @BindView(R.id.editTextDia)
    EditText editTextDia;
    @BindView(R.id.editTextMes)
    EditText editTextMes;
    @BindView(R.id.editTextAnio)
    EditText editTextAnio;
    @BindView(R.id.constraintLayoutFechaNacimiento)
    ConstraintLayout constraintLayoutFechaNacimiento;

    @BindView(R.id.btnRegistrar)
    Button buttonRegistrar;

    @BindView(R.id.editFechaNac)
    EditText editTextFechaNac;

    //progress dialog
    private ProgressDialog progressDialog;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RegistrarUsuarioPresenter getPresenter() {
        // RegistrarUsuarioRepository registrarUsuarioRepository = new RegistrarUsuarioRepository(new RegistrarUsuarioRemote(), new RegistrarUsuarioLocal());
        RegistrarUsuarioRepository registrarUsuarioRepository = RegistrarUsuarioRepository.getmInstance(new RegistrarUsuarioRemote(),
                new RegistrarUsuarioLocal(
                        InjectorUtils.provideTipoDocumentoDao(),
                        InjectorUtils.providePaisDao()
                ));
        return new RegistrarUsuarioPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                this,
                new RegistrarUsuario(registrarUsuarioRepository),
                new ListarTipoDocumento(registrarUsuarioRepository),
                new ListarTipoPais(registrarUsuarioRepository));
    }

    @Override
    protected RegistrarUsuarioView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_registrar_user);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {

       /* try {
            //onDateSetListener = new DatePickerDialogTheme();
            DialogFragment dialogfragment = new DatePickerDialogTheme();

            dialogfragment.show(getSupportFragmentManager(), "Theme 3");
        } catch (Exception e) {
            Log.d(TAG, "errorInflate");
        }*/


         onDateSetListener = new DatePickerDialog.OnDateSetListener() {
           @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = month + "/" + dayOfMonth + "/" + year;

                presenter.onCumple( year,  month,  dayOfMonth);
                editTextFechaNac.setText(date);
            }
        };
    }


//    final Calendar calendar = Calendar.getInstance();
//    int year = calendar.get(Calendar.YEAR);
//    int month = calendar.get(Calendar.MONTH);
//    int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//    DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
//            AlertDialog.THEME_HOLO_DARK, this, year, month, day);


    @OnClick({R.id.fab, R.id.relative, R.id.btnRegistrar, R.id.btn_terminos, R.id.checkBoxTerminos, R.id.editFechaNac})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                // checkPickImagePermissions();
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
                break;
            case R.id.relative:
                // checkPickImagePermissions();
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
                break;
            case R.id.btnRegistrar:
                String tipoDocumento = editTextNumeroDocumento.getText().toString();
                String nombre = editTextNombres.getText().toString();
                String apellido = editTextApellidos.getText().toString();
                String email = editTextEmail.getText().toString();
                String clave = editTextClave.getText().toString();
                //  String repetirClave = editTextRepetirClave.getText().toString();
                String celular = editTextCelular.getText().toString();
                String razonSocial = editTextRazonSocial.getText().toString();
                String dia = editTextDia.getText().toString();
                String mes = editTextMes.getText().toString();
                String anio = editTextAnio.getText().toString();
                UserUi userUi = new UserUi();
                userUi.setTipoDoc(tipoDocumento);
                userUi.setNombre(nombre);
                userUi.setApellidoPaterno(apellido);
                userUi.setEmail(email);
                userUi.setClave(clave);
                userUi.setRepetirClave(clave);
                userUi.setCelular(celular);
                userUi.setRazonSocial(razonSocial);
                userUi.setDia(dia);
                userUi.setMes(mes);
                userUi.setAnio(anio);
                Log.d(TAG,"tipoDocumento : " + tipoDocumento.length());
                if (presenter != null) presenter.onRegistrarUsuario(userUi);
                break;
            case R.id.btn_terminos:
                String android_os = getAndroidVersion(android.os.Build.VERSION.SDK_INT);
                Log.d(TAG, "btn_terminos::android_os : " + android_os);
                //presenter.onAceptarTerminosCondiciones();
                break;
            case R.id.checkBoxTerminos:
                presenter.onCheckBoxTerminos();
                break;
            case R.id.editFechaNac:
                initStartDialogCumple();
                break;
            default:
                break;
        }
    }

    private String getAndroidVersion(int sdk) {
        switch (sdk) {
            case 1:
                presenter.onAceptarTerminosCondiciones();
                return "(Android 1.0)";
            case 2:
                presenter.onAceptarTerminosCondiciones();
                return "Petit Four" + "(Android 1.1)";
            case 3:
                presenter.onAceptarTerminosCondiciones();
                return "Cupcake" + "(Android 1.5)";
            case 4:
                presenter.onAceptarTerminosCondiciones();
                return "Donut" + "(Android 1.6)";
            case 5:
                presenter.onAceptarTerminosCondiciones();
                return "Eclair" + "(Android 2.0)";
            case 6:
                presenter.onAceptarTerminosCondiciones();
                return "Eclair" + "(Android 2.0.1)";
            case 7:
                presenter.onAceptarTerminosCondiciones();
                return "Eclair" + "(Android 2.1)";
            case 8:
                presenter.onAceptarTerminosCondiciones();
                return "Froyo" + "(Android 2.2)";
            case 9:
                presenter.onAceptarTerminosCondiciones();
                return "Gingerbread" + "(Android 2.3)";
            case 10:
                presenter.onAceptarTerminosCondiciones();
                return "Gingerbread" + "(Android 2.3.3)";
            case 11:
                presenter.onAceptarTerminosCondiciones();
                return "Honeycomb" + "(Android 3.0)";
            case 12:
                presenter.onAceptarTerminosCondiciones();
                return "Honeycomb" + "(Android 3.1)";
            case 13:
                presenter.onAceptarTerminosCondiciones();
                return "Honeycomb" + "(Android 3.2)";
            case 14:
                presenter.onAceptarTerminosCondiciones();
                return "Ice Cream Sandwich" + "(Android 4.0)";
            case 15:
                presenter.onAceptarTerminosCondiciones();
                return "Ice Cream Sandwich" + "(Android 4.0.3)";
            case 16:
                presenter.onAceptarTerminosCondiciones();
                return "Jelly Bean" + "(Android 4.1)";
            case 17:
                presenter.onAceptarTerminosCondiciones();
                return "Jelly Bean" + "(Android 4.2)";
            case 18:
                presenter.onAceptarTerminosCondiciones();
                return "Jelly Bean" + "(Android 4.3)";
            case 19:
                presenter.onAceptarTerminosCondiciones();
                return "KitKat" + "(Android 4.4)";
            case 20:
                presenter.onAceptarTerminosCondiciones();
                return "KitKat Watch" + "(Android 4.4)";
            case 21:
                presenter.onAceptarTerminosCondiciones();
                return "Lollipop" + "(Android 5.0)";
            case 22:
                presenter.onAceptarTerminosCondiciones();
                return "Lollipop" + "(Android 5.1)";
            case 23:
                presenter.onAceptarTerminosCondiciones();
                return "Marshmallow" + "(Android 6.0)";
            case 24:
                mostrarMensaje("Android no compatible, Revizar los ter. y cond. en la pagina web");
                return "Nougat" + "(Android 7.0)";
            case 25:
                mostrarMensaje("Android no compatible, Revizar los ter. y cond. en la pagina web");
                return "Nougat" + "(Android 7.1.1)";
            case 26:
                mostrarMensaje("Android no compatible, Revizar los ter. y cond. en la pagina web");
                return "Oreo" + "(Android 8.0)";
            case 27:
                mostrarMensaje("Android no compatible, Revizar los ter. y cond. en la pagina web");
                return "Oreo" + "(Android 8.1)";
            case 28:
                mostrarMensaje("Android no compatible, Revizar los ter. y cond. en la pagina web");
                return "Pie" + "(Android 9.0)";
            default:
                return "";
        }
    }
//    final Calendar calendar = Calendar.getInstance();
//    int year = calendar.get(Calendar.YEAR);
//    int month = calendar.get(Calendar.MONTH);
//    int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//    DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
//            AlertDialog.THEME_HOLO_DARK, this, year, month, day);


    private void initStartDialogCumple() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        /*DatePickerDialog dialog = new DatePickerDialog(RegistrarUserActivity.this,
                android.R.style.Theme_Holo_Dialog_MinWidth,
                onDateSetListener,
                year, month, day);*/
        /*DatePickerDialog dialog = new DatePickerDialog(getActivity(),
            AlertDialog.THEME_HOLO_DARK, onDateSetListener, year, month, day);*/
        DatePickerDialog dialog = new DatePickerDialog(RegistrarUserActivity.this,
                AlertDialog.THEME_HOLO_LIGHT,onDateSetListener,year,month,day);
       // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }

    private void checkPickImagePermissions() {
        RegistrarUserActivityPermissionsDispatcher.initSubirImageGaleriaWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void initSubirImageGaleria() {
        MultiImageSelector.create()
                .showCamera(true)
                .count(1)
                .start(RegistrarUserActivity.this, REQUEST_CODE_CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "ACtivity:onActivityResult");
        presenter.onActivityResult(requestCode, resultCode, data);
    }


    private void initSpinerAdapter() {
        //  editTextEmail.setText("@gmail.com");
        spinnerTipoDocumento.setOnItemSelectedListener(this);
        spinnerTipoPais.setOnItemSelectedListener(this);
        progressDialog = new ProgressDialog(this);
        // initGlideGif();
    }

    private void initGlideGif() {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);
        Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load("https://i.imgur.com/99yekNI.gif").into(circleImageViewFotos);


    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarImagenSubida(Uri imagen) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);
        Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(imagen).into(circleImageViewFotos);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(spinnerTipoDocumento, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarListaSpinnerTipoDocumento(List<TipoDocumentoUi> tipoDocumentoUiList) {
        SpinnerTipoDocumentoAdapter adapter = new SpinnerTipoDocumentoAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoDocumentoUiList);
        spinnerTipoDocumento.setAdapter(adapter);
    }

    @Override
    public void mostrarListaSpinnerTipoPais(List<TipoPaisUi> tipoPaisUiList) {
        SpinnerTipoPaisAdapter adapter = new SpinnerTipoPaisAdapter(this,
                R.layout.custom_spinner_tipo_documento, tipoPaisUiList);
        spinnerTipoPais.setAdapter(adapter);
        presenter.onFilterPeru(tipoPaisUiList);
    }

    @Override
    public void mostrarErrorEditTextTipoDocumento(String mensaje) {
        editTextNumeroDocumento.requestFocus();
        editTextNumeroDocumento.setError(mensaje);

    }

    @Override
    public void mostrarErrorEditTextNombre(String mensaje) {
        editTextNombres.setError(mensaje);
        editTextNombres.requestFocus();
    }

    @Override
    public void mostrarErrorEditTextApellidos(String mensaje) {
        editTextApellidos.setError(mensaje);
        editTextApellidos.requestFocus();
    }

    @Override
    public void mostrarErrorEditTextEmail(String mensaje) {
        editTextEmail.setError(mensaje);
        editTextEmail.requestFocus();
    }

    @Override
    public void mostrarErrorEditTextCelular(String mensaje) {
        editTextCelular.setError(mensaje);
        editTextCelular.requestFocus();
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
    public void startLoginActivity(String estado) {
        /*Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
       /* Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("estado", estado);
        startActivity(intent);*/
        Toast.makeText(getApplicationContext(), "Usuario Creado Correctamente", Toast.LENGTH_SHORT).show();
        finish();
        //  LoginActivity.startLoginActivity(this);
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
    public void editTextTipoDocumentoValidar(String tipoDocumento) {
        editTextNumeroDocumento.setText(null);
        switch (tipoDocumento) {
            case "1":
                mostrarEditTextNombre();
                mostrarEditTextApellido();
                mostrarEditTextFechaNacimiento();
                ocultarEdiTextRazonSocial();
                InputFilter[] FilterArrayDni = new InputFilter[1];
                FilterArrayDni[0] = new InputFilter.LengthFilter(8);
                editTextNumeroDocumento.setFilters(FilterArrayDni);
                editTextNumeroDocumento.setHint("Número de Documento");
                break;
            case "4":
                mostrarEditTextNombre();
                mostrarEditTextApellido();
                mostrarEditTextFechaNacimiento();
                ocultarEdiTextRazonSocial();
                /*InputFilter[] FilterArrayCarnetExtranjero = new InputFilter[1];
                FilterArrayCarnetExtranjero[0] = new InputFilter.LengthFilter(12);
                editTextNumeroDocumento.setFilters(FilterArrayCarnetExtranjero);*/
                /*
                InputFilter[] FilterArrayCarnetExtranjero = new InputFilter[1];
                FilterArrayCarnetExtranjero[0] = new InputFilter.LengthFilter(20);
                editTextNumeroDocumento.setFilters(FilterArrayCarnetExtranjero);
                */
                InputFilter[] FilterArrayCarnetExtranjero = new InputFilter[1];
                FilterArrayCarnetExtranjero[0] = new InputFilter.LengthFilter(22);
                editTextNumeroDocumento.setFilters(FilterArrayCarnetExtranjero);
                editTextNumeroDocumento.setHint("Número de Documento");
                break;
            case "6":
                ocultarEditTextNombre();
                ocultarEditTextApellido();
                ocultarEditTextFechaNacimiento();
                mostrarEditTextRazonSocial();
                InputFilter[] FilterArrayRegionUnico = new InputFilter[1];
                FilterArrayRegionUnico[0] = new InputFilter.LengthFilter(11);
                editTextNumeroDocumento.setFilters(FilterArrayRegionUnico);
                editTextNumeroDocumento.setHint("Número de Ruc");
                break;
            case "7":
                mostrarEditTextNombre();
                mostrarEditTextApellido();
                mostrarEditTextFechaNacimiento();
                mostrarEditTextFechaNacimiento();
                ocultarEdiTextRazonSocial();
                InputFilter[] FilterArrayPasaporte = new InputFilter[1];
                FilterArrayPasaporte[0] = new InputFilter.LengthFilter(12);
                editTextNumeroDocumento.setFilters(FilterArrayPasaporte);
                editTextNumeroDocumento.setHint("Número de Documento");
                break;
            case "11":
                mostrarEditTextNombre();
                mostrarEditTextApellido();
                mostrarEditTextFechaNacimiento();
                ocultarEdiTextRazonSocial();
                InputFilter[] FilterArrayPartidaNacimiento = new InputFilter[1];
                FilterArrayPartidaNacimiento[0] = new InputFilter.LengthFilter(15);
                editTextNumeroDocumento.setFilters(FilterArrayPartidaNacimiento);
                editTextNumeroDocumento.setHint("Número de Documento");
                break;
            case "0":
                mostrarEditTextNombre();
                mostrarEditTextApellido();
                mostrarEditTextFechaNacimiento();
                InputFilter[] FilterArrayOtros = new InputFilter[1];
                FilterArrayOtros[0] = new InputFilter.LengthFilter(15);
                //editTextNumeroDocumento.setFilters(FilterArrayOtros);
                editTextNumeroDocumento.setFilters(FilterArrayOtros);
                editTextNumeroDocumento.setHint("Número de Documento");
                break;
            default:
                mostrarEditTextNombre();
                mostrarEditTextApellido();
                mostrarEditTextFechaNacimiento();
                editTextNumeroDocumento.setHint("Número de Documento");
                break;

        }
    }

    @Override
    public void initSpinnerAdapter() {
        initSpinerAdapter();
    }

    @Override
    public void initStartActivitytTerminosRegistrosUser(int terminosCondicionesRegistrarUser) {
        /*Intent intent = new Intent(this, RegistroTerminosActivity.class);
        intent.putExtra(Constantes.EXTRAS_TERMINOS_CONDICIONES, Constantes.TERMINOS_CONDICIONES_REGISTRAR_USER);
        startActivity(intent);*/

        Intent intent = new Intent(this, RegistroTerminosActivity.class);
        intent.putExtra(Constantes.EXTRAS_TERMINOS_CONDICIONES, Constantes.TERMINOS_CONDICIONES_REGISTRAR_USER);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void mostrarPositionPeru(int position) {
        spinnerTipoPais.setSelection(position);
    }

    @Override
    public void mostrarEditTextNombre() {
        editTextNombres.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarEditTextNombre() {
        editTextNombres.setVisibility(View.GONE);
    }

    @Override
    public void mostrarEditTextApellido() {
        editTextApellidos.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarEditTextApellido() {
        editTextApellidos.setVisibility(View.GONE);
    }

    @Override
    public void mostrarEditTextRazonSocial() {
        editTextRazonSocial.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarEdiTextRazonSocial() {
        editTextRazonSocial.setVisibility(View.GONE);
    }

    @Override
    public void mostrarEditTextFechaNacimiento() {
        //editTextCumple.setVisibility(View.VISIBLE);
        constraintLayoutFechaNacimiento.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarEditTextFechaNacimiento() {
        //editTextCumple.setVisibility(View.GONE);
        constraintLayoutFechaNacimiento.setVisibility(View.GONE);
    }

    @Override
    public void mostrarErrorEditTextDia(String mensaje) {
        editTextDia.setError(mensaje);
    }

    @Override
    public void mostrarErrorEditTextMes(String mensaje) {
        editTextMes.setError(mensaje);
    }

    @Override
    public void mostrarErrorEditTextAnio(String mensaje) {
        editTextAnio.setError(mensaje);
    }

    @Override
    public void mostrarErrorEditTextRazonSocial(String ingrese_razon_social) {
        editTextRazonSocial.setError(ingrese_razon_social);
    }

    @Override
    public void habilitarButtonRegistrar() {
        buttonRegistrar.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonRegistrar() {
        buttonRegistrar.setEnabled(false);
    }

    @Override
    public void obtenerAnioActual() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String obtenerAnioActual = String.valueOf(year) + "-" + String.valueOf(month + 1)
                + "-" + String.valueOf(day);
        presenter.onObtenerAnioActual(obtenerAnioActual);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int itemId = parent.getId();
        switch (itemId) {
            case R.id.spinnerTipoDoc:
                try {
                    TipoDocumentoUi tipoDocumentoUi = (TipoDocumentoUi) spinnerTipoDocumento.getSelectedItem();
                    if (tipoDocumentoUi == null) return;
                    presenter.onSpinnerTipoDocumento(tipoDocumentoUi.getIdTipoDcumento());
                    Log.d(TAG, "spinnerTipoDoc : " + tipoDocumentoUi.getIdTipoDcumento());
                } catch (Exception e) {
                    Log.d(TAG, "spinnerTipoDoce : " + e.getMessage());
                }


                break;
            case R.id.spinnerPaisPais:
                try {
                    TipoPaisUi tipoPaisUi = (TipoPaisUi) spinnerTipoPais.getSelectedItem();
                    if (tipoPaisUi == null) return;
                    presenter.onSpinnerTipoPais(tipoPaisUi.getIdTipoPais());
                    Log.d(TAG, "spinnerPais" + tipoPaisUi.getIdTipoPais());
                } catch (Exception e) {
                    Log.d(TAG, "spinnerPaisPaise : " + e.getMessage());
                }
                /*TipoPaisUi tipoPaisUi =  spinnerTipoPais.getSelectedItem();
                if (tipoPaisUi == null) return;
                presenter.onSpinnerTipoPais(tipoPaisUi.getIdTipoPais());
                Log.d(TAG, "spinnerPais" + tipoPaisUi.getIdTipoPais());*/


                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
