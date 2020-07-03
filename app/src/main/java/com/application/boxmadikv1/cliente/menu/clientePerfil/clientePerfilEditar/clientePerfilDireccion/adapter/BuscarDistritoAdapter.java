package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;

import java.util.ArrayList;
import java.util.List;

public class BuscarDistritoAdapter extends ArrayAdapter<TipoDistritoUi> {

    public static final String TAG = BuscarDistritoAdapter.class.getSimpleName();


    List<TipoDistritoUi> customers, tempCustomer, suggestions;
    //   BuscarListener buscarListener;

    public BuscarDistritoAdapter(Context context, List<TipoDistritoUi> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        Log.d(TAG, "BuscarDistritoAdapter : " + objects.size());
        this.customers = objects;
        // this.buscarListener = buscarListener;
        this.tempCustomer = new ArrayList<TipoDistritoUi>(objects);
        this.suggestions = new ArrayList<TipoDistritoUi>(objects);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipoDistritoUi customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buscar_item_especialidad_adapter, parent, false);
        }
        TextView txtCustomer = convertView.findViewById(R.id.textViewAutoComplete);

        if (txtCustomer != null)
            txtCustomer.setText(customer.getDescripcion());

        return convertView;
    }

    @Nullable
    @Override
    public TipoDistritoUi getItem(int position) {
        return customers.get(position);
    }

    @Override
    public Filter getFilter() {
        //Log.d(TAG, "getFilter : ");
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            TipoDistritoUi customer = (TipoDistritoUi) resultValue;
            return customer.getDescripcion();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            try {
                String textoChar = constraint.toString();
                String autoAyuda = "*";
                //Log.d(TAG, "performFiltering : ");
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    suggestions.clear();
                    for (TipoDistritoUi people : tempCustomer) {
                        if (people.getDescripcion().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            suggestions.add(people);
                        } else if (autoAyuda.equals(textoChar)) {
                            suggestions.add(people);
                        }
                    }


                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();

                    return filterResults;
                } else {
                    Log.d(TAG, "else : ");

                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                    return filterResults;
                }
            } catch (Exception e) {
                Log.d(TAG, "Evitando Bug : ");
            }
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.d(TAG, "publishResults : ");
            //    try {
            try {
                ArrayList<TipoDistritoUi> c = (ArrayList<TipoDistritoUi>) results.values;
                if (results != null && results.count > 0) {

                    try {
                        clear();
                        for (TipoDistritoUi cust : c) {
                            Log.d(TAG, "cust : " + cust.getDescripcion());
                            add(cust);
                            notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        Log.d(TAG, "Evitando Bug : ");
                    }

                }
            }catch (Exception e){
                Log.d(TAG, "Evitando Bug : ");
            }


        }
    };
}
