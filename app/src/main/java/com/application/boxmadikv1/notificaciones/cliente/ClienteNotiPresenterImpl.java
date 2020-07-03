package com.application.boxmadikv1.notificaciones.cliente;

import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.notificaciones.NotificacionesPresenterImpl;
import com.application.boxmadikv1.notificaciones.cliente.useCase.ListaNotiCliente;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class ClienteNotiPresenterImpl extends NotificacionesPresenterImpl {
    public static final String TAG = ClienteNotiPresenterImpl.class.getSimpleName();
    private ListaNotiCliente listaNotiCliente;


    public ClienteNotiPresenterImpl(UseCaseHandler handler, ListaNotiCliente listaNotiCliente) {
        super(handler);
        this.listaNotiCliente = listaNotiCliente;
        notificacionesUiLista = new ArrayList<>();
        // Log.d(TAG, "Codigo " + codigoUsuario);

    }

    int pageCount = 1;
    String tipoCarga = "SinLoadMore";

    @Override
    public void onStart() {
        // super.onStart();
        Log.d(TAG, "ClienteNotiPresenterImpl" +
                " " + codigoUsuario);
        // actualizarVista();
        int pageCount = 1;

        initUseCaseListaCliente(codigoUsuario, paisCodigo, Constantes.GRUPO_NOTIFICACION_ESPECIALISTA, pageCount, tipoCarga, tipoNotificacion);

        //actualizarVista();
    }

    private void actualizarVista() {
        // if (notificacionesView != null) notificacionesView.limpiarVista(no);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    List<NotificacionesUi> notificacionesUiLista;

    private void initUseCaseListaCliente(String codigoUsuario, String paisCodigo, String grupoNotificacionCodigo,
                                         final int countPAge, final String tipoCarga, String tipoNotificacion) {

        Log.d(TAG, "codigoUsuario : " + codigoUsuario + " / " +
                "paisCodigo : " + paisCodigo + " / " +
                "grupoNotificacionCodigo : " + grupoNotificacionCodigo + " / " +
                "countPAge : " + countPAge + " / " +
                "tipoCarga : " + tipoCarga + " / " +
                "tipoNotificacion : " + tipoNotificacion);
        handler.execute(listaNotiCliente,
                new ListaNotiCliente.RequestValues(codigoUsuario,
                        paisCodigo,
                        grupoNotificacionCodigo,
                        countPAge,
                        tipoNotificacion),
                new UseCase.UseCaseCallback<ListaNotiCliente.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaNotiCliente.ResponseValue response) {
                        List<NotificacionesUi> notificacionesUiList = response.getNotificacionesUis();
                        //  Log.d(TAG, "notificacionesUiLista : " + notificacionesUiList.size());
                        isScrolling = true;
                        if (notificacionesUiList == null || notificacionesUiList.isEmpty()) {
                            Log.d(TAG, "No hay items ");
                            switch (tipoCarga) {
                                case "SinLoadMore":
                                    if (notificacionesView != null) {
                                        notificacionesView.ocultarProgressBar();
                                        notificacionesView.mostrarTextViewEmpty();
                                    }
                                    break;
                                case "ConLoadMore":
                                    break;
                            }
                        } else {
                            if (notificacionesView != null)
                                notificacionesView.ocultarTextViewEmpty();
                            pageCount = countPAge + 1;
                            isScrolling = true;
                            switch (tipoCarga) {
                                case "SinLoadMore":
                                    //notificacionesUiLista.addAll(notificacionesUiList);
                                    if (notificacionesView != null) {
                                        notificacionesView.mostrarLista(notificacionesUiList);
                                        notificacionesView.ocultarProgressBar();
                                        //   notificacionesView.ocultarTextViewEmpty();
                                    }

                                    Log.d(TAG, "SinLoadMore : " + notificacionesUiLista.size());
                                    break;
                                case "ConLoadMore":
                                    notificacionesUiLista.addAll(notificacionesUiList);
                                    Log.d(TAG, "ConLoadMore +" + notificacionesUiLista.size());
                                    if (notificacionesView != null) {
                                        notificacionesView.mostrarListaAdd(notificacionesUiLista);
                                        //notificacionesView.mostrarLista(notificacionesUiList);
                                        notificacionesView.ocultarProgressBar();
                                        //  notificacionesView.ocultarTextViewEmpty();
                                    }

                                    break;
                            }

                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        Log.d(TAG, "onScrollStateChanged");
    }

    int currentItems, totalItems, scrollOutItems;
    boolean isScrolling = true;
    String tipoCargaConLoadMore = "ConLoadMore";

    @Override
    public void onScrolled(LinearLayoutManager linearLayout, int dx, int dy) {
        currentItems = linearLayout.getChildCount();
        totalItems = linearLayout.getItemCount();
        scrollOutItems = linearLayout.findFirstVisibleItemPosition();
        Log.d(TAG, "totalItems : " + totalItems +
                "currentItems : " + totalItems +
                "scrollOutItems : " + totalItems);
        if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
            isScrolling = false;
            if (notificacionesView != null) notificacionesView.mostrarProgressBar();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    initUseCaseListaCliente(codigoUsuario,
                            paisCodigo,
                            Constantes.GRUPO_NOTIFICACION_ESPECIALISTA,
                            pageCount,
                            tipoCargaConLoadMore,
                            tipoNotificacion);


                    Log.d(TAG, "CargarData : " + pageCount);

                    return;
                }
            }, 2000);
        }
    }
}
