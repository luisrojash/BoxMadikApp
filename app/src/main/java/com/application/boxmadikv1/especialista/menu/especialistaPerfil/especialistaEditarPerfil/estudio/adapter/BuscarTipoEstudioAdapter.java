package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.adapter;

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
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.listener.BuscarListener;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;

import java.util.ArrayList;
import java.util.List;

public class BuscarTipoEstudioAdapter extends ArrayAdapter<TipoEstudiosUi> {

    public static final String TAG = BuscarTipoEstudioAdapter.class.getSimpleName();

    List<TipoEstudiosUi> customers, tempCustomer, suggestions;
    BuscarListener buscarListener;

    public BuscarTipoEstudioAdapter(Context context, List<TipoEstudiosUi> objects, BuscarListener buscarListener) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.customers = objects;
        this.buscarListener = buscarListener;
        this.tempCustomer = new ArrayList<TipoEstudiosUi>(objects);
        this.suggestions = new ArrayList<TipoEstudiosUi>(objects);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipoEstudiosUi customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buscar_item_especialidad_adapter, parent, false);
        }
        TextView txtCustomer = convertView.findViewById(R.id.textViewAutoComplete);

        if (txtCustomer != null)
            txtCustomer.setText(customer.getDescripcion());
       // convertView.setBackgroundColor(getContext().getResources().getColor(R.color.md_red_100));
        //if (position % 2 == 0)

        /*else
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.md_red_100));*/

        return convertView;
    }

    @Nullable
    @Override
    public TipoEstudiosUi getItem(int position) {
        return customers.get(position);
    }

    @Override
    public Filter getFilter() {
        Log.d(TAG, "getFilter : ");
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Log.d(TAG, "convertResultToString : ");
            TipoEstudiosUi customer = (TipoEstudiosUi) resultValue;
            //Log.d(TAG, "convertResultToString : " + customer.getDescripcion());
            return customer.getDescripcion();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String textoChar = constraint.toString();
            String autoAyuda = "*";
            Log.d(TAG, "performFiltering : ");
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                suggestions.clear();
                for (TipoEstudiosUi people : tempCustomer) {
                    if (people.getDescripcion().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    } else if (autoAyuda.equals(textoChar)) {
                        suggestions.add(people);
                        buscarListener.onOcultarTeclado();
                    }
                    //suggestions.add(people);
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
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.d(TAG, "publishResults : ");

            ArrayList<TipoEstudiosUi> c = (ArrayList<TipoEstudiosUi>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (TipoEstudiosUi cust : c) {
                    Log.d(TAG, "cust : " + cust.getDescripcion());
                    add(cust);
                    notifyDataSetChanged();
                }
            }
        }
    };

    public void mostrarLista() {
        notifyDataSetChanged();
    }

}
