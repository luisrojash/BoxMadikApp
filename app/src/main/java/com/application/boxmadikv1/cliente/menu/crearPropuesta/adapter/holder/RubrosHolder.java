package com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter.holder;

import androidx.core.view.ViewCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.adapter.OficioAdapter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.OficiosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.listener.OficioListener;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.listener.RubrosListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RubrosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String TAG = RubrosHolder.class.getSimpleName();

    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;
    @BindView(R.id.textViewDescripcion)
    TextView textViewDescripcion;
    @BindView(R.id.cardviewRubro)
    CardView cardView;
    @BindView(R.id.recicladorOficio)
    RecyclerView recyclerView;
    @BindView(R.id.keyBordRotate)
    ImageView imageViewRotate;
    OficioAdapter oficioAdapter;
    RubrosListener rubrosListener;
    RubrosUi rubrosUi;
    OficioListener oficioListener;

    public RubrosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardView.setOnClickListener(this);
    }

    public void bind(RubrosUi rubrosUi, RubrosListener rubrosListener, OficioListener oficioListener) {
        this.rubrosListener = rubrosListener;
        this.rubrosUi = rubrosUi;
        this.oficioListener = oficioListener;
        initAdapter();
        initMostrarListaOficio(rubrosUi);
        textViewDescripcion.setText(rubrosUi.getDescripcionRub());
        Glide.with(itemView.getContext()).load(rubrosUi.getImagenRub()).into(imageViewRubro);
    }

    private void initMostrarListaOficio(RubrosUi rubrosUi) {
        Log.d(TAG, "RubrosHolder posicion: " + rubrosUi.getOficiosUiList().size());
        if (rubrosUi.getOficiosUiList() == null) Log.d(TAG, "Lista Nula");
        else oficioAdapter.mostrarLista(rubrosUi.getOficiosUiList());
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        oficioAdapter = new OficioAdapter(new ArrayList<OficiosUi>(),oficioListener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(oficioAdapter);
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.cardviewRubro:
                rubrosListener.onClickItemRubro(rubrosUi);
                break;
            default:
                break;
        }
    }

    public OficioAdapter getOficioAdapter() {
        return oficioAdapter;
    }

    public void rotarImagenTrue() {
        recyclerView.setVisibility(View.VISIBLE);
        imageViewRotate.clearAnimation();
        ViewCompat.animate(imageViewRotate).rotation(180).start();
    }

    public void rotarImagenFalse() {
        recyclerView.setVisibility(View.GONE);
        imageViewRotate.clearAnimation();
        ViewCompat.animate(imageViewRotate).rotation(0).start();
    }
}
