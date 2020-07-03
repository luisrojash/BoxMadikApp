package com.application.boxmadikv1.extra;

import android.os.AsyncTask;

import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class InternetCheck extends AsyncTask<Void, Void, Boolean> {

    private Consumer mConsumer;
    private Object object;

    public interface Consumer<T> {
        void accept(Boolean internet, T objeto);
    }

    public InternetCheck(Consumer consumer, Object object) {
        mConsumer = consumer;
        this.object = object;
        execute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Socket sock = new Socket();
            sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
            sock.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean internet) {
        validarInstancia(internet, object);
        // mConsumer.accept(internet);
    }

    private void validarInstancia(Boolean internet, Object object) {
        if (object instanceof ItemUi) {
            ItemUi itemUi = (ItemUi) object;
            mConsumer.accept(internet,itemUi);
        }else if (object instanceof EspecialistaEstadosUi){
            EspecialistaEstadosUi especialistaEstadosUi = (EspecialistaEstadosUi) object;
            mConsumer.accept(internet,especialistaEstadosUi);
        }else if (object instanceof CotizacionesUi){
            CotizacionesUi cotizacionesUi = (CotizacionesUi) object;
            mConsumer.accept(internet,cotizacionesUi);
        }else{
            mConsumer.accept(internet,null);
        }
    }
}
