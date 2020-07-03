package com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter.holder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.boxmadikv1.R;

import com.application.boxmadikv1.especialista.menu.buscarPropuesta.adapter.EspecialidadAdapter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.listener.PropuestaListener;
import com.application.boxmadikv1.utils.Constantes;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropuestaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String TAG = PropuestaHolder.class.getSimpleName();

    @BindView(R.id.textViewTituloPropuesta)
    TextView textViewTitulo;
    @BindView(R.id.textViewFecha)
    TextView textViewFecha;
    @BindView(R.id.textViewProsupuesto)
    TextView textViewPresupuesto;

    @BindView(R.id.textViewDia)
    TextView textViewDia;
    @BindView(R.id.textViewTurno)
    TextView textViewTurno;
    @BindView(R.id.imageViewRubro)
    ImageView imageViewRubro;


    @BindView(R.id.textViewNombreDepartamento)
    TextView textViewNombreDepartamento;
    @BindView(R.id.textViewNombreDistrito)
    TextView textViewNombreDistrito;

    @BindView(R.id.btnPropuesta)
    Button buttonPropuesta;
    @BindView(R.id.btnCount)
    Button buttonCountEspecialidad;

    @BindView(R.id.recicladorEspec)
    RecyclerView reclRecyclerViewEspec;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;

    @BindView(R.id.textviewPromedio)
    TextView textViewNombrePromedio;
    @BindView(R.id.btnCountCotizacion2)
    Button buttonNumeroCotizacion;


    PropuestaListener propuestaListener;
    ItemUi itemUi;

    public PropuestaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        buttonPropuesta.setOnClickListener(this);
    }

    public void bind(ItemUi itemUi, PropuestaListener propuestaListener) {

        Log.d(TAG, "bind : " + itemUi.getEspecialidadesUiList() + "");
        this.propuestaListener = propuestaListener;
        this.itemUi = itemUi;
        textViewTitulo.setText(itemUi.getNombrePropuesta().toUpperCase());
        textViewFecha.setText("Ingresado: " + Constantes.f_fecha_letras(itemUi.getFechaPropuesta()));
        textViewPresupuesto.setText("Presupuesto: " + itemUi.getDescripcionRangoPrecio());
        textViewNombreDepartamento.setText(itemUi.getNombreDepartamento());
        textViewNombreDistrito.setText("Dist: " + itemUi.getNombreDistrito());
        textViewDia.setText("Día: " + itemUi.getDescripcionRangoDias());
        textViewTurno.setText("Turno: " + itemUi.getDescripcionRangoTurno());
        validarTextoNull(itemUi);

        Glide.with(itemView.getContext()).load(itemUi.getImagePropuesta()).into(imageViewRubro);
        reclRecyclerViewEspec.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        reclRecyclerViewEspec.setHasFixedSize(true);
        validacionLista(itemUi.getEspecialidadesUiList());
    }

    private void validarTextoNull(ItemUi itemUi) {
        if(itemUi.getPromedioCotizacion()==null){
            textViewNombrePromedio.setText(" S./ "+ "0");
        }else{
            textViewNombrePromedio.setText("S./ "+ itemUi.getPromedioCotizacion());

        }
        if(itemUi.getNumeroCotizacion()==null){
            buttonNumeroCotizacion.setText("0");
        }else{
            buttonNumeroCotizacion.setText(itemUi.getNumeroCotizacion());
        }

    }

    EspecialidadAdapter especialidadAdapter;

    private void validacionLista(List<EspecialidadesUi> especialidadesUiList) {


        if (especialidadesUiList == null || especialidadesUiList.isEmpty()) {
            especialidadAdapter = new EspecialidadAdapter(new ArrayList<EspecialidadesUi>());
            textViewEmpty.setVisibility(View.VISIBLE);
            reclRecyclerViewEspec.setVisibility(View.GONE);
            buttonCountEspecialidad.setVisibility(View.GONE);
        } else {
            especialidadAdapter = new EspecialidadAdapter(especialidadesUiList);
            textViewEmpty.setVisibility(View.GONE);
            buttonCountEspecialidad.setVisibility(View.VISIBLE);
            reclRecyclerViewEspec.setVisibility(View.VISIBLE);
            //buttonCountEspecialidad.setText("" + especialidadesUiList.size()); AGREGADO PORQUE NO EXISTE especialista
        }
        textViewEmpty.setVisibility(View.GONE);//--AGREGADO PORQUE NO EXISTE especialista
        reclRecyclerViewEspec.setAdapter(especialidadAdapter);
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        switch (itemId) {
            case R.id.btnPropuesta:
                propuestaListener.onClickPropuestaListener(itemUi);
                break;
        }
    }

    public void actualizarListaEspecialidades(ItemUi itemUi) {
        Log.d(TAG, "PropuestaHolder");
        especialidadAdapter.notifyDataSetChanged();
    }
}
