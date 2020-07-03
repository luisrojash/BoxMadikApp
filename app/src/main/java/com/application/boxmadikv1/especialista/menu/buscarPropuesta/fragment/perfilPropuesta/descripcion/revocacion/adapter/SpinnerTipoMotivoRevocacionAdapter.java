package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.application.boxmadikv1.R;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoMotivoRevocacionUi;
import java.util.List;

public class SpinnerTipoMotivoRevocacionAdapter extends ArrayAdapter<TipoMotivoRevocacionUi>{

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<TipoMotivoRevocacionUi> items;
    private final int mResource;

    public SpinnerTipoMotivoRevocacionAdapter(@NonNull Context context, @LayoutRes int resource,
                                                @NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);
        TextView textViewTipoDocumento = view.findViewById(R.id.textViewSpinnerTipoDocumento);
        TipoMotivoRevocacionUi tipoDocumentoUi = items.get(position);
        textViewTipoDocumento.setText(tipoDocumentoUi.getDecripcion());
        return view;
    }
}
