package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.local.DetallesLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.remote.DetallesRemote;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.UbicacionDialog;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.listener.UbicacionListener;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase.GuardarEspecialidad;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase.GuardarPropuesta;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.useCase.ObtenerDireccion;
import com.application.boxmadikv1.dao.departamento.DepartamentoDaoImpl;
import com.application.boxmadikv1.dao.distrito.DistritoDaoImpl;
import com.application.boxmadikv1.dao.provincia.ProvinciaDaoImpl;
import com.application.boxmadikv1.sesion.TinyDB;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class DetallesActivity extends BaseActivity<DetallesView, DetallesPresenter> implements DetallesView, UbicacionListener {

    public static final String TAG = DetallesActivity.class.getSimpleName();
    public static final int REQUEST_CODE_CHOOSE_IMAGE_PRIMERA = 100;
    public static final int REQUEST_CODE_CHOOSE_IMAGE_SEGUNDA = 200;

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubroPerf;
    @BindView(R.id.textViewNombreOficio)
    TextView textViewNombreOficio;

    @BindView(R.id.imageViewPrimero)
    ImageView imageViewPrimero;
    @BindView(R.id.imageViewSegundo)
    ImageView imageViewSegundo;

    @BindView(R.id.editTextTituloPropuesta)
    EditText editTextTitulo;
    @BindView(R.id.edtContent)
    EditText editTextDetalles;

    @BindView(R.id.textViewUbicacion)
    TextView textViewUbicacion;

    @BindView(R.id.btnPublicar)
    Button buttonPublicar;

    private ProgressDialog progressDialog;
    // Session Manager Class
    // SessionManager session;
    SecurePreferences preferences;
    TinyDB tinyDB;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected DetallesPresenter getPresenter() {
        DetallesRepository detallesRepository = new DetallesRepository(new DetallesRemote(), new DetallesLocal(
                new DepartamentoDaoImpl(),
                new ProvinciaDaoImpl(),
                new DistritoDaoImpl()
        ));
        return new DetallesPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                this,
                new GuardarPropuesta(detallesRepository),
                new GuardarEspecialidad(detallesRepository),
                new ObtenerDireccion(detallesRepository)
        );
    }

    @Override
    protected DetallesView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_cliente_detalles);
        ButterKnife.bind(this);
        initView();
        initSession();
    }

    private void initSession() {
        // session = new SessionManager(getApplicationContext());
        preferences = new SecurePreferences(this, Constantes.KEY_SECURE_PREFERENCE, true);
        tinyDB = new TinyDB(getApplicationContext());
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarCabecera(String imageRubro, String nombreOficio) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true);
        Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageRubro).into(imageViewRubroPerf);
        textViewNombreOficio.setText(nombreOficio.toUpperCase());
    }

    @Override
    public void mostrarImagenSubidaPrimera(Uri uriComprimida) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);
        Glide.with(DetallesActivity.this)
                .applyDefaultRequestOptions(requestOptions)
                .load(uriComprimida).into(imageViewPrimero);
    }

    @Override
    public void mostrarImagenSubidaSegunda(Uri uriComprimida) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);
        Glide.with(DetallesActivity.this)
                .applyDefaultRequestOptions(requestOptions)
                .load(uriComprimida).into(imageViewSegundo);
    }

    @Override
    public void mostrarMensajeErrorTitulo(String mensaje) {
        editTextTitulo.setError(mensaje);
    }

    @Override
    public void mostrarMensajeErrorDetalles(String mensaje) {
        editTextDetalles.setError(mensaje);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(imageViewPrimero, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarDialogProgress(String mensaje) {
        progressDialog.setMessage(mensaje);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarDialogProgress() {
        progressDialog.dismiss();

    }

    @Override
    public void startMenuClienteActivity(String estado) {
        Intent intent = new Intent(this, MenuClienteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("estado", estado);
        startActivity(intent);
    }

    @Override
    public void limpiarSubidaImagenUno() {
        imageViewPrimero.setImageDrawable(null);
//        imageViewPrimero.setVisibility(View.VISIBLE);
//        imageViewPrimero.getBackground();
        imageViewPrimero.setImageResource(R.drawable.agregar_imagen);
    }


    @Override
    public void limpiarSubidaImagenSegundo() {

        imageViewSegundo.setImageDrawable(null);
        imageViewSegundo.setImageResource(R.drawable.agregar_imagen);
    }

    @Override
    public void limpiarCajasTexto() {
        editTextTitulo.setText("");
        editTextDetalles.setText("");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: NUEVO A ATRAS");
    }

    @Override
    public void startActivityBackAtencion(int posicionTipoPrecio, int posicionTipoTurno, int posicionTipoDias) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("posicionTipoPrecio", posicionTipoPrecio);
        returnIntent.putExtra("posicionTipoTurno", posicionTipoTurno);
        returnIntent.putExtra("posicionTipoDias", posicionTipoDias);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void mostrarTextViewUbicacion(String nombreDepartamento, String nombreProvincia, String nombreDistrito) {
        textViewUbicacion.setText(nombreDepartamento + "," + nombreProvincia + "," + nombreDistrito);
    }

    @Override
    public void initDialogUbicacion(String codigoPais) {
        Log.d(TAG, "initDialogUbicacion");
        UbicacionDialog editNameDialogFragment = UbicacionDialog.newInstance(codigoPais);
        editNameDialogFragment.show(getSupportFragmentManager(), "dialogUbicacion");
    }

    @Override
    public void habilitarButtonPublicar() {
        buttonPublicar.setEnabled(true);
    }

    @Override
    public void deshabilitarButtonPublicar() {
        buttonPublicar.setEnabled(false);
    }

    @OnClick({R.id.imageViewPrimero, R.id.imageViewSegundo, R.id.btnPublicar, R.id.fabcloseuno, R.id.fabclosesegundo, R.id.textViewUbicacion})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.imageViewPrimero:
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setActivityTitle("Recorte Imagen")
                        .start(DetallesActivity.this);
                //checkPickImagePermissionsPrimero();
                break;
            case R.id.imageViewSegundo:
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setActivityTitle("Recorte Imagen")
                        .start(DetallesActivity.this);
                //checkPickImagePermissionsSegundo();
                break;
            case R.id.fabcloseuno:
                presenter.eliminarImagenUno();
                break;
            case R.id.fabclosesegundo:
                presenter.eliminarImagenSegundo();
                break;
            case R.id.btnPublicar:
                String titulo = editTextTitulo.getText().toString();
                String detalles = editTextDetalles.getText().toString();

                Log.d(TAG, "titulo : " + Constantes.removeAccents(titulo) +
                        "detalles : " + Constantes.removeAccents(detalles));
                String otros = "otros";
                /*presenter.onClickPublicarPropuesta(Constantes.isResultadoCharacterSpecial(Constantes.removeAccents(titulo), otros).trim(),
                        Constantes.isResultadoCharacterSpecial(Constantes.removeAccents(detalles), otros));*/

                presenter.onClickPublicarPropuesta(titulo,detalles);
                break;
            case R.id.textViewUbicacion:
                presenter.onClickUbicacion();
                break;
        }
    }

    private void checkImagePrimeroCropper(int requestPrimero) {
    }

    private void checkPickImagePermissionsSegundo() {
        DetallesActivityPermissionsDispatcher.initSubirImageGaleriaSegundoWithPermissionCheck(this);
    }

    private void checkPickImagePermissionsPrimero() {
        DetallesActivityPermissionsDispatcher.initSubirImageGaleriaPrimeroWithPermissionCheck(this);
    }


    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void initSubirImageGaleriaPrimero() {
        MultiImageSelector.create()
                .showCamera(true)
                .count(1)
                .start(DetallesActivity.this, REQUEST_CODE_CHOOSE_IMAGE_PRIMERA);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void initSubirImageGaleriaSegundo() {
        MultiImageSelector.create()
                .showCamera(true)
                .count(1)
                .start(DetallesActivity.this, REQUEST_CODE_CHOOSE_IMAGE_SEGUNDA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "ACtivity:onActivityResult");
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccion_cliente_detalle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ventana_principal) {
            Intent intent = new Intent(this, MenuClienteActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        } else {
            presenter.onActivityBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*Bundle bundle = getIntent().getExtras();
        presenter.setExtras(bundle);
        HashMap<String, String> user = session.getUserDetails();
        Log.d(TAG, "onStart :  " + user.get(SessionManager.KEY_USER));
        String keyUser = user.get(SessionManager.KEY_USER);
        if (keyUser == null) return;*/
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String codigoPais = preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        /*String codigoDepartamento =  preferences.getString(Constantes.KEY_SECURE_USUARIO_CLIENTE_CODIGO_DEPARTAMENTO);
        String codigoProvincia =  preferences.getString(Constantes.KEY_SECURE_USUARIO_CLIENTE_CODIGO_PROVINCIA);
        String codigoDistrito =  preferences.getString(Constantes.KEY_SECURE_USUARIO_CLIENTE_CODIGO_DISTRITO);*/
        ArrayList arrayListRubros = tinyDB.getListString("mylist");
        presenter.onInitKeyUser(keyUser, arrayListRubros, codigoPais);
    }

    @Override
    public void onBackPressed() {
        presenter.onActivityBackPressed();
    }


    @Override
    public void onGuardarUbicacion(String codigoDepartamento, String nombreDepartamento, String codigoProvincia, String nombreProvincia, String codigoDistrito, String nombreDistrito) {
        Log.d(TAG, "codigoDepartamento" + codigoDepartamento +
                "codigoProvincia" + codigoProvincia +
                "codigoDistrito" + codigoDistrito);
        presenter.onUbicacionNueva(codigoDepartamento, nombreDepartamento, codigoProvincia, nombreProvincia, codigoDistrito, nombreDistrito);
    }
}
