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
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDireccionUi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BuscarDireccionAdapter extends ArrayAdapter<TipoDireccionUi> {

    public static final String TAG = BuscarDireccionAdapter.class.getSimpleName();

    List<TipoDireccionUi> customers;

    private GoogleApiClient mGoogleApiClient;
    private LatLngBounds mBounds;



    public BuscarDireccionAdapter(Context context, GoogleApiClient googleApiClient, LatLngBounds mBounds) {
        super(context, android.R.layout.simple_list_item_1);
        this.mGoogleApiClient = googleApiClient;
        this.mBounds = mBounds;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TipoDireccionUi customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buscar_item_especialidad_adapter, parent, false);
        }
        TextView txtCustomer = convertView.findViewById(R.id.textViewAutoComplete);

        if (txtCustomer != null)
            try {
                txtCustomer.setText(customers.get(position).getDescripcion());
            } catch (Exception e) {
                Log.d(TAG, "Error");
            }

        //  convertView.setBackgroundColor(getContext().getResources().getColor(R.color.md_red_100));
        return convertView;
    }

    @Nullable
    @Override
    public TipoDireccionUi getItem(int position) {
        //return customers.get(position);
      /*  if (customers.size() - 1 == 0) {
            return null;
        } else if (customers.size() == 0) {
            return null;
        }*/
        if (customers.size() == 0) return null;
        return customers.get(position);


    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            TipoDireccionUi customer = (TipoDireccionUi) resultValue;
            // Log.d(TAG, "convertResultToString : " + customer.getDescripcion());
            if (customer == null) return null;
            return customer.getDescripcion() + dataDireccion;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            // Skip the autocomplete query if no constraints are given.
            if (constraint != null) {
                // customers.clear();
                // Query the autocomplete API for the (constraint) search string.
                try {
                    customers = getAutocomplete(constraint);
                    if (customers != null) {
                        // The API successfully returned results.
                        results.values = customers;
                        results.count = customers.size();
                    } else {

                    }
                } catch (Exception e) {
                    Log.d(TAG, "Exeption ");
                }

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Log.d(TAG, "publishResults : ");

            ArrayList<TipoDireccionUi> c = (ArrayList<TipoDireccionUi>) results.values;

            try {
                if (results != null && results.count > 0) {
                    clear();
                    for (TipoDireccionUi cust : c) {
                        Log.d(TAG, "publishResults : " + cust.getDescripcion());

                        add(cust);
                        notifyDataSetChanged();
                    }
                }

            } catch (Exception e) {
                Log.d(TAG, "Invalid index 2, size is 0");
            }

        }

    };

    private ArrayList<TipoDireccionUi> getAutocomplete(CharSequence constraint) {
        try {
            if (mGoogleApiClient.isConnected()) {
                Log.i(TAG, "Starting autocomplete query for: " + constraint);
                // Submit the query to the autocomplete API and retrieve a PendingResult that will
                // contain the results when the query completes.
                String mensaje = constraint.toString() + " " + dataDireccion;
                PendingResult<AutocompletePredictionBuffer> results =
                        Places.GeoDataApi
                                .getAutocompletePredictions(mGoogleApiClient, mensaje,
                                        mBounds, null);
                // This method should have been called off the main UI thread. Block and wait for at most 60s
                // for a result from the API.
                AutocompletePredictionBuffer autocompletePredictions = results
                        .await(120, TimeUnit.SECONDS);
                // Confirm that the query completed successfully, otherwise return null
                final Status status = autocompletePredictions.getStatus();
                if (!status.isSuccess()) {
//                Toast.makeText(mContext, "Error contacting API: " + status.toString(),
//                        Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error getting autocomplete prediction API call: " + status.toString());
                    autocompletePredictions.release();
                    return null;
                }
                Log.d(TAG, "Query completed. Received " + autocompletePredictions.getCount()
                        + " predictions.");
                // Copy the results into our own data structure, because we can't hold onto the buffer.
                // AutocompletePrediction objects encapsulate the API response (place ID and description).
                Iterator<AutocompletePrediction> iterator = autocompletePredictions.iterator();
                ArrayList resultList = new ArrayList<>(autocompletePredictions.getCount());
                while (iterator.hasNext()) {
                    AutocompletePrediction prediction = iterator.next();
                    // Get the details of this prediction and copy it into a new PlaceAutocomplete object.
                    resultList.add(new TipoDireccionUi(prediction.getPlaceId(),
                            prediction.getFullText(null).toString()));


                    Log.d(TAG, "prediction : " + prediction.getPrimaryText(null) +
                            "prediction 2 : " + prediction.getPlaceId());
                }
                // Release the buffer now that all data has been copied.
                autocompletePredictions.release();

                return resultList;
            }
            Log.e(TAG, "Google API client is not connected for autocomplete query.");
            return null;

        }catch (Exception e){
            Log.e(TAG, "NULL");
        }

        return null;
    }

    public void limpiarLista() {
        if (customers == null) return;
        customers.clear();
        notifyDataSetChanged();
    }

    String dataDireccion = "";

    public void tipoDepartamento(String dataDireccion) {
        this.dataDireccion = dataDireccion;
    }
}