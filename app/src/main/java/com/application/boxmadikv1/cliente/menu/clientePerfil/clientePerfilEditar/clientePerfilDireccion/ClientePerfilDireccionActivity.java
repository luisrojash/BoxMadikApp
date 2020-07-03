package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarDepartamentoAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarDireccionAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarDistritoAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter.BuscarProvinciaAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.local.ClientePerfilDireccionLocal;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.remote.ClientePerfilDireccionRemote;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDireccionUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.GuardarDatosEditados;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.ListaDepartamento;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.ListaDistrito;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase.ListaProvincia;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.RubrosActivity;
import com.application.boxmadikv1.dao.InjectorUtils;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class ClientePerfilDireccionActivity extends BaseActivity<ClientePerfilDireccionView, ClientePerfilDireccionPresenter> implements ClientePerfilDireccionView,
        OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    public static final String TAG = ClientePerfilDireccionActivity.class.getSimpleName();


    public static final String EXTRA_CLIENTE_DIRECCION_EDITAR_CODIGO_DEPARTAMENTO = "codigoDepartamento";
    public static final String EXTRA_CLIENTE_DIRECCION_EDITAR_CODIGO_PROVINCIA = "codigoProvincia";
    public static final String EXTRA_CLIENTE_DIRECCION_EDITAR_CODIGO_DISTRITO = "codigoDistrito";
    public static final String EXTRA_CLIENTE_DIRECCION_EDITAR_LATITUD = "latitud";
    public static final String EXTRA_CLIENTE_DIRECCION_EDITAR_LONGITUD = "longitud";
    public static final String EXTRA_CLIENTE_DIRECCION_EDITAR_DESCRIPCION = "descripcion";


    @BindView(R.id.autoCompleteTextViewDireccion)
    AutoCompleteTextView autoCompleteTextViewDireccion;

    @BindView(R.id.autoCompleteTextViewDepartamento)
    AutoCompleteTextView autoCompleteTextViewDepartamento;
    @BindView(R.id.autoCompleteTextViewProvincia)
    AutoCompleteTextView autoCompleteTextViewProvincia;
    @BindView(R.id.autoCompleteTextViewDistrito)
    AutoCompleteTextView autoCompleteTextViewDistrito;

    @BindView(R.id.btnSiguiente)
    Button button;


    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private SecurePreferences preferences;


    //progress dialog
    private ProgressDialog progressDialog;

   /* BuscarDireccionAdapter mAdapter;
    @BindView(R.id.list_search)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_et)
    EditText mSearchEdittext;*/


    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ClientePerfilDireccionPresenter getPresenter() {
        ClientePerfilDireccionRepository clientePerfilDireccionRepository =
                ClientePerfilDireccionRepository.getmInstance(new ClientePerfilDireccionLocal(
                                InjectorUtils.provideDepartamentoDao(),
                                InjectorUtils.provideProvinciaDao(),
                                InjectorUtils.provideDistritoDao()),
                        new ClientePerfilDireccionRemote());
        return new ClientePerfilDireccionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new ListaDepartamento(clientePerfilDireccionRepository),
                new ListaProvincia(clientePerfilDireccionRepository),
                new ListaDistrito(clientePerfilDireccionRepository),
                new GuardarDatosEditados(clientePerfilDireccionRepository));

    }

    @Override
    protected ClientePerfilDireccionView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_cliente_editar_perfil_direccion);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        initGoogleView();
        initMapsView();
    }

    private void initPreferences() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String userFoto = preferences.getString(Constantes.KEY_SECURE_USUARIO_FOTO);
        String codigoPais=preferences.getString(Constantes.KEY_SECURE_USUARIO_PAIS);
        presenter.onKeyUser(keyUser, userFoto,codigoPais);
    }

    BuscarDireccionAdapter setAdapter;

    private void initGoogleView() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();

        setAdapter = new BuscarDireccionAdapter(this, mGoogleApiClient, BOUNDS_INDIA);
        autoCompleteTextViewDireccion.setAdapter(setAdapter);
        autoCompleteTextViewDireccion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoDireccionUi tipoDireccionUi = (TipoDireccionUi) parent.getAdapter().getItem(position);
                presenter.onAutCompleteTipoDireccion(tipoDireccionUi);
            }
        });
//        String dataDireccion = "La Molina";
//        setAdapter.tipoDepartamento(dataDireccion);

    }

    @OnClick({R.id.floatingActionButton, R.id.imageViewClose, R.id.btnSiguiente, R.id.imageViewCloseDepart, R.id.imageViewCloseProvincia, R.id.imageViewCloseDistrito})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.floatingActionButton:
                presenter.onClickBuscarMapaDireccion(autoCompleteTextViewDireccion.getText().toString());
                break;
            case R.id.imageViewClose:
                presenter.onClickCloseDireccion();
                limpiarVista();
                break;
            case R.id.btnSiguiente:
                presenter.onClickSiguiente(autoCompleteTextViewDireccion.getText().toString());
                break;
            case R.id.imageViewCloseDepart:
                presenter.onClickCloseDepartamento();
                break;
            case R.id.imageViewCloseProvincia:
                presenter.onClickCloseProvincia();
                break;
            case R.id.imageViewCloseDistrito:
                presenter.onClickCloseDistrito();
                break;

        }
    }

    private void limpiarVista() {
        setAdapter.limpiarLista();
        autoCompleteTextViewDireccion.setEnabled(true);
        autoCompleteTextViewDireccion.setText("");
    }

    private void initMapsView() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void mostrarListaTipoDepartamento(List<TipoDepartamentoUi> tipoDepartamentoUis) {
        //autoCompleteTextViewDepartamento.setOnItemClickListener(this);
        BuscarDepartamentoAdapter buscarDepartamentoAdapter = new BuscarDepartamentoAdapter(this, tipoDepartamentoUis);
        autoCompleteTextViewDepartamento.setAdapter(buscarDepartamentoAdapter);

        autoCompleteTextViewDepartamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick");
                TipoDepartamentoUi tipoDepartamentoUi = (TipoDepartamentoUi) parent.getAdapter().getItem(position);
                presenter.onSpinnerTipoDepartamento(tipoDepartamentoUi);
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
                autoCompleteTextViewDistrito.setEnabled(true);
            }
        });


    }

    @Override

    public void mostrarListaTipoDistrito(List<TipoDistritoUi> tipoDistritoUis) {
        BuscarDistritoAdapter buscarDistritoAdapter = new BuscarDistritoAdapter(this, tipoDistritoUis);
        autoCompleteTextViewDistrito.setAdapter(buscarDistritoAdapter);
        autoCompleteTextViewDistrito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TipoDistritoUi tipoDistritoUi = (TipoDistritoUi) parent.getAdapter().getItem(position);
                presenter.onSpinnerTipoDistrito(tipoDistritoUi);
                autoCompleteTextViewDireccion.setEnabled(true);
                Log.d(TAG, "tipoDistritoUi : " + position);
            }
        });

    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void desactivarAutoCompleDireccion(TipoDireccionUi tipoDireccionUi) {
        autoCompleteTextViewDireccion.setEnabled(false);
        obtenerCoordenadas(tipoDireccionUi);
    }

    private void obtenerCoordenadas(final TipoDireccionUi tipoDireccionUi) {
        Places.GeoDataApi.getPlaceById(mGoogleApiClient, tipoDireccionUi.getId())
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess()) {
                            final Place myPlace = places.get(0);
                            LatLng queriedLocation = myPlace.getLatLng();
                            Log.v("Latitude is", "" + queriedLocation.latitude);
                            Log.v("Longitude is", "" + queriedLocation.longitude);
                            tipoDireccionUi.setLatitud(String.valueOf(queriedLocation.latitude));
                            tipoDireccionUi.setLongitud(String.valueOf(queriedLocation.longitude));
                            presenter.onObtenerCoordenadasDireccion(tipoDireccionUi);
                        }
                        places.release();
                    }
                });
    }

    @Override
    public void activarAutoCompleDireccion() {
        autoCompleteTextViewDireccion.setEnabled(true);
    }

    Marker currentMarkerPrimer;

    @Override
    public void buscarMapaDireccion(TipoDireccionUi tipoDireccionUi, final GoogleMap googleMap) {
        if (tipoDireccionUi== null || tipoDireccionUi.getLatitud() == null) return;
        double dobleLatitud= Double.parseDouble(tipoDireccionUi.getLatitud());
        double dobleLongitud= Double.parseDouble(tipoDireccionUi.getLongitud());
        LatLng latLng = new LatLng(dobleLatitud, dobleLongitud);
        if (currentMarkerPrimer != null) currentMarkerPrimer.remove();
        Marker currentMarker = googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(tipoDireccionUi.getDescripcion()));
        currentMarkerPrimer = currentMarker;
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(TAG, "onMapClick : " + latLng.latitude + "");
                if (currentMarkerPrimer != null) {
                    currentMarkerPrimer.remove();
                    currentMarkerPrimer = googleMap.addMarker(new MarkerOptions()
                            .position(latLng).title("Nueva Ubicacion"));
                    Log.d(TAG, "onCameraIdle : " + latLng.latitude + "");
                }
            }
        });

    }

    @Override
    public void mostrarMensajeErrorAutoCompleteDepartamentoError(String mensaje) {
        autoCompleteTextViewDepartamento.setError(mensaje);
    }

    @Override
    public void mostrarTextViewDepartamento(String s) {
        autoCompleteTextViewDepartamento.setText(s);
    }

    @Override
    public void mostrarMensajeErrorAutoCompleteProvinciaError(String mensaje) {
        autoCompleteTextViewProvincia.setError(mensaje);
    }

    @Override
    public void mostrarTextViewProvincia(String s) {
        autoCompleteTextViewProvincia.setText(s);
    }

    @Override
    public void mostrarMensajeErrorAutoCompleteDistritoError(String mensaje) {
        autoCompleteTextViewDistrito.setError(mensaje);
    }

    @Override
    public void mostrarTextViewDistrito(String s) {
        autoCompleteTextViewDistrito.setText(s);
    }

    @Override
    public void mostrarEditextDireccion(String direccion) {
        autoCompleteTextViewDireccion.setText(direccion);
    }

    @Override
    public void initStartActivityPresentacion(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto,
                                              String codigoDepartamento, String codigoProvincia, String codigoDistrito,
                                              double latitud, double longitud, String descripcionDireccion) {
       /* Intent intent = new Intent(this, ClientePerfilPresentacionActivity.class);
        intent.putExtra(ClientePerfilEditarActivity.EXTRA_CLIENTE_PERFIL_EDITAR_NOMBRE, nombreEdit);
        intent.putExtra(ClientePerfilEditarActivity.EXTRA_CLIENTE_PERFIL_EDITAR_APELLIDOS, apellidosEdit);
        intent.putExtra(ClientePerfilEditarActivity.EXTRA_CLIENTE_PERFIL_EDITAR_CELULAR, celularEdit);
        intent.putExtra(ClientePerfilEditarActivity.EXTRA_CLIENTE_PERFIL_EDITAR_FOTO, usuarioFoto);
        intent.putExtra(EXTRA_CLIENTE_DIRECCION_EDITAR_CODIGO_DEPARTAMENTO, codigoDepartamento);
        intent.putExtra(EXTRA_CLIENTE_DIRECCION_EDITAR_CODIGO_PROVINCIA, codigoProvincia);
        intent.putExtra(EXTRA_CLIENTE_DIRECCION_EDITAR_CODIGO_DISTRITO, codigoDistrito);
        intent.putExtra(EXTRA_CLIENTE_DIRECCION_EDITAR_LATITUD, latitud);
        intent.putExtra(EXTRA_CLIENTE_DIRECCION_EDITAR_LONGITUD, longitud);
        intent.putExtra(EXTRA_CLIENTE_DIRECCION_EDITAR_DESCRIPCION,descripcionDireccion);
        startActivity(intent);*/
    }

    @Override
    public void actualizarDataPreferencesSinFoto(String nombreEdit, String apellidosEdit, String celularEdit) {

        preferences.put(Constantes.KEY_SECURE_USUARIO_NOMBRE, nombreEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_APELLIDOS, apellidosEdit);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CELULAR, celularEdit);
        String usuNombre = preferences.getString(Constantes.KEY_SECURE_USUARIO_NOMBRE);
        Log.d(TAG, "usuNombre : " + usuNombre);
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
    public void mostrarProgressBarDialog() {
        progressDialog.setMessage("Actualizando Dirección....");
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void ocultarProgressBarDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void iniStartActivityMenuCliente(String ediPerfilCorrectamenteUsuario) {
        Intent intent = new Intent(this, MenuClienteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("estado", ediPerfilCorrectamenteUsuario);
        startActivity(intent);
    }

    @Override
    public void mostrarButtonTextGuardar() {
        button.setText("Guardar");
    }

    @Override
    public void mostrarButtonTextSiguiente() {
        button.setText("Siguiente");
    }

    @Override
    public void initStartActivityDistritos(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto, String id, String id1, String id2, double latitud, double longitud, String descripcion) {

    }

    @Override
    public void initStartActivityEspecEditPerfil() {
       /* preferences.put(Constantes.KEY_SECURE_USUARIO_CLIENTE_CODIGO_DEPARTAMENTO, codigoDepartamento);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CLIENTE_CODIGO_PROVINCIA, codigoProvincia);
        preferences.put(Constantes.KEY_SECURE_USUARIO_CLIENTE_CODIGO_DISTRITO, codigoDistrito);*/
        Toast.makeText(getApplicationContext(), "Datos Guardados Correctamente", Toast.LENGTH_SHORT).show();
        finish();
        Log.d(TAG, "initStartActivityEspecEditPerfil");
    }

    @Override
    public void borrarTextDepartamento() {
        autoCompleteTextViewDepartamento.setText(null);
        autoCompleteTextViewDepartamento.setEnabled(true);


        autoCompleteTextViewProvincia.setText(null);
        autoCompleteTextViewProvincia.setEnabled(false);

        autoCompleteTextViewDistrito.setText(null);
        autoCompleteTextViewDistrito.setEnabled(false);

        autoCompleteTextViewDireccion.setText(null);
        autoCompleteTextViewDireccion.setEnabled(false);
    }

    @Override
    public void borrarTextProvincia() {
        autoCompleteTextViewProvincia.setText(null);
        autoCompleteTextViewProvincia.setEnabled(true);

        autoCompleteTextViewDistrito.setEnabled(false);
        autoCompleteTextViewDistrito.setText(null);

       autoCompleteTextViewDireccion.setText(null);
       autoCompleteTextViewDireccion.setEnabled(false);
    }

    @Override
    public void borrarTextDistrito() {
        autoCompleteTextViewDistrito.setText(null);
        autoCompleteTextViewDistrito.setEnabled(true);

        autoCompleteTextViewDireccion.setText(null);
        autoCompleteTextViewDireccion.setEnabled(false);

    }

    @Override
    public void deshabilitarText() {
        autoCompleteTextViewDepartamento.setEnabled(false);
        autoCompleteTextViewProvincia.setEnabled(false);
        autoCompleteTextViewDistrito.setEnabled(false);
        autoCompleteTextViewDireccion.setEnabled(false);
    }

    @Override
    public void habilitartText() {
        autoCompleteTextViewDepartamento.setEnabled(true);
        autoCompleteTextViewProvincia.setEnabled(true);
        autoCompleteTextViewDistrito.setEnabled(true);
        autoCompleteTextViewDireccion.setEnabled(true);
    }

    @Override
    public void filtrarAdapterGoogle(String descripcion) {
        setAdapter.tipoDepartamento(descripcion);
    }

    @Override
    public void initStartActivityRubros() {
        startActivity(new Intent(this, RubrosActivity.class));
        //startActivity(new Intent(this, MenuClienteActivity.class));
        finish();
    }

    @Override
    public void initStartMenuCliente() {
        startActivity(new Intent(this, MenuClienteActivity.class));
    }

    @Override
    public void initStartMenuEspecialista() {
        startActivity(new Intent(this, MenuEspecialistaActivity.class));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        presenter.onGooglenMap(googleMap);
    }


    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
        initPreferences();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

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
    public void deshabilitarProvDist() {
        autoCompleteTextViewProvincia.setEnabled(false);
        autoCompleteTextViewDistrito.setEnabled(false);
    }

    @Override
    public void deshabilitarButtonGuardar() {
        button.setEnabled(false);
    }

    @Override
    public void habilidarButtonGuardar() {
        button.setEnabled(true);
    }
}
