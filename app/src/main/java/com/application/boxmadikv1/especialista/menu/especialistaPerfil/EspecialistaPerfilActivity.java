package com.application.boxmadikv1.especialista.menu.especialistaPerfil;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.especialista.menu.MenuEspecialistaActivity;
import com.application.boxmadikv1.especialista.menu.edicionDatos.DatosEspecActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.adapter.TrabajosRealizadosAdapter;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource.EspecialistaPerfilRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource.remote.EspecialistaPerfilRemote;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.entidad.TrabajosRealizadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.listener.ComentarioListener;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.useCase.MostrarComentarios;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EspecialistaPerfilActivity extends BaseActivity<EspecialistaPerfilView, EspecialistaPerfilPresenter>
        implements EspecialistaPerfilView, ComentarioListener {

    public static final String TAG = EspecialistaPerfilActivity.class.getSimpleName();
    public static final String EXTRA_EDITAR_PERFIL_NOMBRE_PROPUESTA = "nombrePropuesta";
    public static final String EXTRA_EDITAR_PERFIL_APELLIDO_CLIENTE = "apellidoCliente";
    public static final String EXTRA_EDITAR_PERFIL_FECHA_PROPUESTA = "fechaPropuesta";
    public static final String EXTRA_EDITAR_PERFIL_FOTO_CLIENTE = "fotoCliente";
    public static final String EXTRA_EDITAR_PERFIL_NOMBRE_CLIENTE = "nombreCliente";
    public static final String EXTRA_EDITAR_PERFIL_ESTRELLAS_CLIENTE = "estrellasEspec";
    public static final String EXTRA_EDITAR_PERFIL_COMENTARIO_CLIENTE = "comentarioEspec";
    private static final int FEATURED_IMAGE_RATIO = 24;


    @BindView(R.id.textViewNombreCompleto)
    TextView textViewNombreCompleto;
    @BindView(R.id.imgProfile)
    CircleImageView circleImageView;

   /* @BindView(R.id.viewPagerBuscarPropuesta)
    ViewPager viewPager;*/

    @BindView(R.id.textView15)
    TextView textViewPendiente;
    @BindView(R.id.textView17)
    TextView textViewFinalizada;
    @BindView(R.id.textView16)
    TextView textViewAceptada;

    @BindView(R.id.reciclador)
    RecyclerView recicladorTrabajos;

    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;

    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;

    @BindView(R.id.fabDerecha)
    ImageView fabDerecha;

    @BindView(R.id.fabIzquierda)
    ImageView fabIzquierda;

    @BindView(R.id.edtContent)
    TextInputEditText textInputEditTextResenia;

    // private MyFragmentAdapter fragmentAdapter;
    private SecurePreferences preferences;
    private TrabajosRealizadosAdapter trabajosRealizadosAdapter;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected EspecialistaPerfilPresenter getPresenter() {
        EspecialistaPerfilRepository especialistaPerfilRepository = EspecialistaPerfilRepository.getmInstance(new EspecialistaPerfilRemote());
        return new EspecialistaPerfilPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler())
                , getResources(),
                new MostrarComentarios(especialistaPerfilRepository));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String keyUser = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String nombreUsu = preferences.getString(Constantes.KEY_SECURE_USUARIO_NOMBRE);
        String apellidoUsu = preferences.getString(Constantes.KEY_SECURE_USUARIO_APELLIDOS);
        String fotoUsu = preferences.getString(Constantes.KEY_SECURE_USUARIO_FOTO);
        presenter.onObtenerDataPref(keyUser, nombreUsu, apellidoUsu, fotoUsu);
    }

    @Override
    protected EspecialistaPerfilView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_especialista_perfil_activity);
        ButterKnife.bind(this);
        initPreferences();
        //initFragmentAdapter();
        initToolbar();
        initView();
    }

    private void initView() {
        trabajosRealizadosAdapter = new TrabajosRealizadosAdapter(new ArrayList<TrabajosRealizadosUi>());
        recicladorTrabajos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recicladorTrabajos.setHasFixedSize(true);
        recicladorTrabajos.setAdapter(trabajosRealizadosAdapter);
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

   /* private void initFragmentAdapter() {
        fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
    }*/

    private void initPreferences() {
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void mostrarDataInicial(String nombreUsu, String apellidoUsu, String fotoUsu) {
        textViewNombreCompleto.setText(nombreUsu + " " + apellidoUsu);
        Glide.with(this).load(fotoUsu).into(circleImageView);
    }

    @Override
    public void initViewPagerAdapter() {
        //  if (fragmentAdapter == null) return;
        // viewPager.setAdapter(fragmentAdapter);
    }

    @Override
    public void initClearFragments() {
        // if (fragmentAdapter == null) return;
        // fragmentAdapter.clear();
    }

    @Override
    public void actualizarAdapterFragmentos() {
        //  fragmentAdapter.actualizarFragmentos();
        // textViewEmpty.setVisibility(View.GONE);
    }

    @Override
    public void mostrarMensajeToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarListaComentarios(List<TrabajosRealizadosUi> trabajosRealizadosUis) {
        trabajosRealizadosAdapter.mostrarLista(trabajosRealizadosUis);
    }


    @Override
    public void mostrarDatosPropuesta(String cotiFinalizada, String cotiPendiente, String cotiAceptado, float estrellasUsu, String datosResenia) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
        textViewPendiente.setText("" + cotiPendiente);

        textViewFinalizada.setText("" + cotiFinalizada);

        textViewAceptada.setText("" + cotiAceptado);

        ratingBar.setRating(estrellasUsu);

        textInputEditTextResenia.setText(datosResenia);

    }

    @Override
    public void mostrarTextoEmpty(String s) {
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarTextoEmpty() {
        textViewEmpty.setVisibility(View.GONE);
    }

    @Override
    public void mostrarBotones() {
        fabDerecha.setVisibility(View.VISIBLE);
        fabIzquierda.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarMensajeResenia(String datosResenia) {
        textInputEditTextResenia.setHint(datosResenia);
    }

    @OnClick({R.id.fabeditperfil})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.fabeditperfil:
                initStartActivityEdit();
                break;
        }
    }

    private void initStartActivityEdit() {//DatosEspecActivity
        // startActivity(new Intent(this, EspecEditarPerfilActivity.class));
        //startActivity(new Intent(this, DatosEspecActivity.class));

        String tipoEstado = "especialista";
        Intent intent = new Intent(this, DatosEspecActivity.class);
        intent.putExtra("tipoEstado", tipoEstado);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClickCursosListener(String keyUser) {
       /* FragmentManager fm = getSupportFragmentManager();
        CursosDialogFragment editNameDialogFragment = CursosDialogFragment.newInstance(keyUser);
        editNameDialogFragment.show(fm, "fragment_edit_name");
        Log.d(TAG, "onClickCursosListener : " + keyUser);*/
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
          /*  ActivityCompat.finishAffinity(EspecialistaPerfilActivity.this);
            startActivity(new Intent(getApplicationContext(), MenuEspecialistaActivity.class));*/
            Intent intent = new Intent(this, MenuEspecialistaActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
