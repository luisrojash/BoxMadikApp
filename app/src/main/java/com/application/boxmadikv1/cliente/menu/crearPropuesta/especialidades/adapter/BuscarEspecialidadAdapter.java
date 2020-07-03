package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.application.boxmadikv1.R;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.listener.BuscarListener;

import java.util.ArrayList;
import java.util.List;

public class BuscarEspecialidadAdapter extends ArrayAdapter<EspecialidadUi> {


    public static final String TAG = BuscarEspecialidadAdapter.class.getSimpleName();

    List<EspecialidadUi> customers, tempCustomer, suggestions;
    BuscarListener buscarListener;

    public BuscarEspecialidadAdapter(Context context, List<EspecialidadUi> objects, BuscarListener buscarListener) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.customers = objects;
        this.buscarListener = buscarListener;
        this.tempCustomer = new ArrayList<EspecialidadUi>(objects);
        this.suggestions = new ArrayList<EspecialidadUi>(objects);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EspecialidadUi customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buscar_item_especialidad_adapter, parent, false);
        }
        TextView txtCustomer = convertView.findViewById(R.id.textViewAutoComplete);

        if (txtCustomer != null)
            txtCustomer.setText(customer.getDescripcion());
        //convertView.setBackgroundColor(getContext().getResources().getColor(R.color.md_red_100));
        //if (position % 2 == 0)

        /*else
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.md_red_100));*/

        return convertView;
    }

    @Nullable
    @Override
    public EspecialidadUi getItem(int position) {
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
            EspecialidadUi customer = (EspecialidadUi) resultValue;
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
                for (EspecialidadUi especialidadUi : tempCustomer) {
                    if (especialidadUi.getDescripcion().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(especialidadUi);
                    } else if (autoAyuda.equals(textoChar)) {
                        suggestions.add(especialidadUi);
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

            ArrayList<EspecialidadUi> c = (ArrayList<EspecialidadUi>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (EspecialidadUi cust : c) {
                    Log.d(TAG, "cust : " + cust.getDescripcion());
                    add(cust);
                    notifyDataSetChanged();
                }
            }

        }
    };

}
