package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.Adapter;

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
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.entidadUi.TipoBancoUi;

import java.util.List;

public class SpinnerTipoBancoAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<TipoBancoUi> items;
    private final int mResource;

    public SpinnerTipoBancoAdapter(@NonNull Context context, @LayoutRes int resource,
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
        TipoBancoUi tipoBancoUi = items.get(position);
        textViewTipoDocumento.setText(tipoBancoUi.getDescripcion());
        return view;
    }



}
