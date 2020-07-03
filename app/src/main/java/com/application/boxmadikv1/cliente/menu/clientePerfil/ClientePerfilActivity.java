package com.application.boxmadikv1.cliente.menu.clientePerfil;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.adapter.ComentarioAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.edicionDatos.CliEdicionDatosActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.entidad.ComentariosUi;
import com.application.boxmadikv1.utils.Constantes;
import com.application.boxmadikv1.utils.SecurePreferences;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ClientePerfilActivity extends BaseActivity<ClientePerfilView, ClientePerfilPresenter> implements ClientePerfilView {

    public static final String TAG = ClientePerfilActivity.class.getSimpleName();

    @BindView(R.id.fabeditperfil)
    FloatingActionButton fabeditperfil;
    @BindView(R.id.textView18)
    TextView textViewNombres;
    @BindView(R.id.imgProfile)
    CircleImageView circleImageView;
    @BindView(R.id.textView17)
    TextView textViewProcesos;
    @BindView(R.id.textView15)
    TextView textViewPendiente;
    @BindView(R.id.textView16)
    TextView textViewFinalizado;

    @BindView(R.id.myRatingBar)
    RatingBar ratingBar;
    @BindView(R.id.recicladorComentarios)
    RecyclerView reciclador;

    @BindView(R.id.id_banderaRight)
    ImageView imageViewBandera;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    @BindView(R.id.fabDerecha)
    ImageView fabDerecha;
    @BindView(R.id.fabIzquierda)
    ImageView fabIzquierda;

    private SecurePreferences preferences;
    private ComentarioAdapter comentarioAdapter;


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ClientePerfilPresenter getPresenter() {
        return new ClientePerfilPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ClientePerfilView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usuCodigo = preferences.getString(Constantes.KEY_SECURE_USUARIO_CODIGO);
        String usuNombre = preferences.getString(Constantes.KEY_SECURE_USUARIO_NOMBRE);
        String usuApellido = preferences.getString(Constantes.KEY_SECURE_USUARIO_APELLIDOS);
        String usuFoto = preferences.getString(Constantes.KEY_SECURE_USUARIO_FOTO);
        presenter.onDataUser(usuCodigo, usuNombre, usuApellido, usuFoto);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_cliente_perfil);
        ButterKnife.bind(this);
        initPreferences();
        initView();
    }

    private void initView() {
        comentarioAdapter = new ComentarioAdapter(new ArrayList<ComentariosUi>());
        reciclador.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(comentarioAdapter);
    }

    private void initPreferences() {
        preferences = new SecurePreferences(getApplicationContext(), Constantes.KEY_SECURE_PREFERENCE, true);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @OnClick({R.id.fabeditperfil})
    public void onClick(View view) {
        int itemId = view.getId();
        switch (itemId) {
            case R.id.fabeditperfil:
                Snackbar.make(fabeditperfil, "Perfil", Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "fab_item_profile");
                presenter.OnClickEditarPerfil();
                break;
        }

    }

    @Override
    public void starActivityEditarPerfil() {
        String tipoEstado = "cliente";
        Intent intent = new Intent(this, CliEdicionDatosActivity.class);
        intent.putExtra("tipoEstado", tipoEstado);
        startActivity(intent);
    }

    @Override
    public void mostrarDataInicial(String usuCodigo, String usuNombre, String usuApellido, String usuFoto) {
        //  textViewNombres.setText(usuNombre + " " + usuApellido);
        textViewNombres.setText(usuNombre.toUpperCase());
        Glide.with(this).load(usuFoto).into(circleImageView);
        Glide.with(this).load("http://i63.tinypic.com/1z5i3xj.png").into(imageViewBandera);

    }

    @Override
    public void mostrarDatosPropuesta(String propuestaFinalizada, String propuestaPendiente, String propuestaProceso, float estrellasUsu) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.md_amber_400), PorterDuff.Mode.SRC_ATOP);
        textViewProcesos.setText("" + propuestaProceso);
        textViewPendiente.setText("" + propuestaPendiente);
        textViewFinalizado.setText("" + propuestaFinalizada);
        ratingBar.setRating(estrellasUsu);
    }

    @Override
    public void mostrarListaComentarios(List<ComentariosUi> datosClienteList) {
        Log.d(TAG, "mostrarListaComentarios : " + datosClienteList);
        comentarioAdapter.mostrarLista(datosClienteList);
    }

    @Override
    public void mostrarTextViewEmpty() {
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarBottones() {
        fabDerecha.setVisibility(View.VISIBLE);
        fabIzquierda.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
