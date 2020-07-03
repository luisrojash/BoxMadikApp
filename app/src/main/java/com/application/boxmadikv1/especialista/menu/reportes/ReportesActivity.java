package com.application.boxmadikv1.especialista.menu.reportes;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.especialista.menu.reportes.deposito.DepositoFragment;
import com.application.boxmadikv1.especialista.menu.reportes.movimiento.MovimientoFragment;
import com.application.boxmadikv1.utils.MyFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportesActivity extends BaseActivity<ReportesView, ReportesPresenter> implements ReportesView {

    public static final String TAG = ReportesActivity.class.getSimpleName();

    @BindView(R.id.tablMenuEspecialista)
    TabLayout tabLayoutEspecialista;
    @BindView(R.id.viewPagerMenuEspecialista)
    ViewPager viewPager;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ReportesPresenter getPresenter() {
        return new ReportesPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources());
    }

    @Override
    protected ReportesView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.reportes_especialista_activity);
        ButterKnife.bind(this);
        initView();
        initToolbar();
    }
    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(MovimientoFragment.newInstance(), "MOVIMIENTO");
        fragmentAdapter.addFragment(DepositoFragment.newInstance(), "DEPOSITO");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(fragmentAdapter);
        tabLayoutEspecialista.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //   if (presenter != null)
                // presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
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
