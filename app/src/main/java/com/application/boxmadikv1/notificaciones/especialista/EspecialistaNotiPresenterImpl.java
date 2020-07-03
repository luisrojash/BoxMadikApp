package com.application.boxmadikv1.notificaciones.especialista;


import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.notificaciones.NotificacionesPresenterImpl;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;
import com.application.boxmadikv1.notificaciones.especialista.useCase.ListaNotiEspe;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class EspecialistaNotiPresenterImpl extends NotificacionesPresenterImpl {
    public static final String TAG = EspecialistaNotiPresenterImpl.class.getSimpleName();

    private ListaNotiEspe listaNotiEspe;


    public EspecialistaNotiPresenterImpl(UseCaseHandler handler, ListaNotiEspe listaNotiEspe) {
        super(handler);
        this.listaNotiEspe = listaNotiEspe;
        notificacionesUiLista = new ArrayList<>();
    }

    int pageCount = 1;
    String tipoCarga = "SinLoadMore";

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
        int pageCount = 1;
        //if(notificacionesView!=null)notificacionesView.limpiarVista(notificacionesUiLista);
        initUseCaseListaEspec(codigoUsuario, paisCodigo, Constantes.GRUPO_NOTIFICACION_CLIENTE, pageCount, tipoCarga, tipoNotificacion);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //  initUseCaseListaEspec(codigoUsuario, paisCodigo, Constantes.GRUPO_NOTIFICACION_CLIENTE, pageCount, tipoCarga, tipoNotificacion);
    }

    List<NotificacionesUi> notificacionesUiLista;

    private void initUseCaseListaEspec(String codigoUsuario, String paisCodigo, String grupoNotificacionCliente,
                                       final int countPAge, final String tipoCarga, String tipoNotificacion) {

        handler.execute(listaNotiEspe, new ListaNotiEspe.RequestValues(codigoUsuario,
                        paisCodigo,
                        grupoNotificacionCliente,
                        countPAge,
                        tipoNotificacion),
                new UseCase.UseCaseCallback<ListaNotiEspe.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaNotiEspe.ResponseValue response) {
                        List<NotificacionesUi> notificacionesUiList = response.getNotificacionesUis();
                        if (notificacionesUiList == null || notificacionesUiList.isEmpty()) {
                           /* Log.d(TAG, "No hay items ");
                            if (notificacionesView != null) {
                                notificacionesView.ocultarProgressBar();
                                notificacionesView.mostrarTextViewEmpty();
                            }*/

                            isScrolling = true;
                            switch (tipoCarga) {
                                case "SinLoadMore":
                                    if (notificacionesView != null) {
                                        notificacionesView.ocultarProgressBar();
                                        notificacionesView.mostrarTextViewEmpty();
                                    }
                                    break;
                                case "ConLoadMore":
                                    Log.d(TAG, "ConLoadMore");

                                    break;
                            }

                        } else {
                            if (notificacionesView != null)
                                notificacionesView.ocultarTextViewEmpty();
                            pageCount = countPAge + 1;
                            isScrolling = true;
                            // notificacionesUiLista.addAll(notificacionesUiList);
                            switch (tipoCarga) {
                                case "SinLoadMore":
                                    Log.d(TAG, "SinLoadMore");
                                    if (notificacionesView != null) {
                                        notificacionesView.mostrarLista(notificacionesUiList);
                                        notificacionesView.ocultarProgressBar();
                                        notificacionesView.ocultarTextViewEmpty();
                                    }
                                    break;
                                case "ConLoadMore":
                                    Log.d(TAG, "ConLoadMore");
                                    if (notificacionesView != null) {
                                        notificacionesView.mostrarListaAdd(notificacionesUiList);
                                        //  notificacionesView.mostrarLista(notificacionesUiList);
                                        notificacionesView.ocultarProgressBar();
                                        notificacionesView.ocultarTextViewEmpty();
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
        //Log.d(TAG, "CargarData : " + pageCount);
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

                    initUseCaseListaEspec(codigoUsuario,
                            paisCodigo,
                            Constantes.GRUPO_NOTIFICACION_CLIENTE,
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
