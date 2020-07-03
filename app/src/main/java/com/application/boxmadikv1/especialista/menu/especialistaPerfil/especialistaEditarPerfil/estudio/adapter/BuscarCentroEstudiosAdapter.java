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
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import java.util.ArrayList;
import java.util.List;

public class BuscarCentroEstudiosAdapter  extends ArrayAdapter<TipoCentroEstudiosUi> {

    public static final String TAG = BuscarTipoEstudioAdapter.class.getSimpleName();

    List<TipoCentroEstudiosUi> customers, tempCustomer, suggestions;
    BuscarListener buscarListener;

    public BuscarCentroEstudiosAdapter(Context context, List<TipoCentroEstudiosUi> objects, BuscarListener buscarListener) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.customers = objects;
        this.buscarListener = buscarListener;
        this.tempCustomer = new ArrayList<TipoCentroEstudiosUi>(objects);
        this.suggestions = new ArrayList<TipoCentroEstudiosUi>(objects);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipoCentroEstudiosUi customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buscar_item_especialidad_adapter, parent, false);
        }
        TextView txtCustomer = convertView.findViewById(R.id.textViewAutoComplete);

        if (txtCustomer != null)
            txtCustomer.setText(customer.getDescripcion());
        //convertView.setBackgroundColor(getContext().getResources().getColor(R.color.md_red_100));

        return convertView;
    }

    @Nullable
    @Override
    public TipoCentroEstudiosUi getItem(int position) {
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
            TipoCentroEstudiosUi customer = (TipoCentroEstudiosUi) resultValue;
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
                for (TipoCentroEstudiosUi people : tempCustomer) {
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

            ArrayList<TipoCentroEstudiosUi> c = (ArrayList<TipoCentroEstudiosUi>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (TipoCentroEstudiosUi cust : c) {
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
