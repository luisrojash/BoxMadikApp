package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.cliente.menu.MenuClienteActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.Adapter.SpinnerTipoBancoAdapter;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource.ClientePerfilCuentaBancariaRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource.local.ClientePerfilCuentaBancariaLocal;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.entidadUi.TipoBancoUi;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.useCase.ListaBanco;
import com.application.boxmadikv1.dao.InjectorUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientePerfilCuentaBancariaActivity extends BaseActivity<ClientePerfilCuentaBancariaView,ClientePerfilCuentaBancariaPresenter> implements ClientePerfilCuentaBancariaView {


    public static final String TAG = ClientePerfilCuentaBancariaActivity.class.getSimpleName();

    @BindView(R.id.btnGuardar)
    Button btnGuardar;

    @BindView(R.id.spinerTipoCuentas)
    Spinner spinerTipoBancos;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ClientePerfilCuentaBancariaPresenter getPresenter() {
        ClientePerfilCuentaBancariaRepository clientePerfilCuentaBancariaRepository=
                ClientePerfilCuentaBancariaRepository.getmInstance(
                        new ClientePerfilCuentaBancariaLocal(
                                InjectorUtils.provideBancoDao()));
        return new ClientePerfilCuentaBancariaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources(),
                new ListaBanco(clientePerfilCuentaBancariaRepository));


    }

    @Override
    protected ClientePerfilCuentaBancariaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.menu_espec_editar_perfil_cuenta_bancaria);
        ButterKnife.bind(this);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @OnClick({R.id.btnGuardar})
    public void OnClick(View view){
        int itemId = view.getId();
        switch (itemId){
            case R.id.btnGuardar:
                Snackbar.make(btnGuardar, "Editar Perfil", Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "btnSiguiente");
                presenter.OnClickMenuCliente();
                break;
        }

    }

    @Override
    public void starActivityMenuCliente() {
        Intent intent = new Intent(this, MenuClienteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void mostrarListaTipoBanco(List<TipoBancoUi> tipoBancoUis) {
        SpinnerTipoBancoAdapter adapter = new SpinnerTipoBancoAdapter(this,
        R.layout.custom_spinner_tipo_documento, tipoBancoUis);
        spinerTipoBancos.setAdapter(adapter);
        // if (presenter != null) presenter.validarTipoPrecio();
        //spinnerTipoPrecio.setSelection(2);
    }
}
