package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.adapter.RubroItemAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource.RubroItemRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource.local.RubroItemLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.listener.RubroItemListener;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.useCase.MostrarListaRubroItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RubroItemActivity extends BaseActivity<RubroItemView, RubroItemPresenter> implements RubroItemView, RubroItemListener {

    public static final String TAG = RubroItemActivity.class.getSimpleName();
    RubroItemAdapter rubroItemAdapter;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected RubroItemPresenter getPresenter() {
        RubroItemRepository rubroItemRepository = new RubroItemRepository(new RubroItemLocal());
        return new RubroItemPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new MostrarListaRubroItem(rubroItemRepository));
    }

    @Override
    protected RubroItemView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_menu_especialista_rubro_item);
        ButterKnife.bind(this);
        initAdapter();
        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initAdapter() {
        rubroItemAdapter = new RubroItemAdapter(new ArrayList<RubroItemUi>(), this);
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(rubroItemAdapter);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarListaRubrosItem(List<RubroItemUi> rubroItemList) {
        rubroItemAdapter.mostrarListaRubroAdapter(rubroItemList);
    }

    @Override
    public void startBuscarPropuesta(String id, String imageRubro, String descripcion) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("id",id);
        returnIntent.putExtra("imageRubro",imageRubro);
        returnIntent.putExtra("descripcion",descripcion);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onItemClickRubro(RubroItemUi rubroItemUi) {
       // rubroItemUi.setEstadoRubro(false);
        presenter.onItemClickRubro(rubroItemUi);
        /*Intent returnIntent = new Intent();
        returnIntent.putExtra("result",result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();*/
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
}
