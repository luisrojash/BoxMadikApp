package com.application.boxmadikv1.cliente.menu.clienteTodos;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.UseCaseThreadPoolScheduler;
import com.application.boxmadikv1.base.fragment.BaseFragment;

import butterknife.BindView;

public class ClienteTodosFragment extends BaseFragment<ClienteTodosView, ClienteTodosPresenter> implements ClienteTodosView {

    public static final String TAG = ClienteTodosFragment.class.getSimpleName();
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    public static ClienteTodosFragment newInstance(String keyUser) {
        Bundle args = new Bundle();
        ClienteTodosFragment fragment = new ClienteTodosFragment();
        args.putString("keyUser", keyUser);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ClienteTodosPresenter getPresenter() {
        return new ClienteTodosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected ClienteTodosView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.menu_cliente_todos_fragment, container, false);
        return view;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }
}
