package com.application.boxmadikv1.chat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface ChatPresenter extends BaseActivityPresenter<ChatView> {
    void onClickBackPressed();

    void onObtenerDatoPreferencia(String usuarioCodigo, String paisCodigo);

    void onEnviarMensaje(String mensaje);

    void onDataRealTime(String mensaje, String timeStamp, String codigoUsuario,String codigoMensaje);

    void onScrollStateChanged(RecyclerView recyclerView, int newState);

    void onScrolled(LinearLayoutManager layoutManager, int dx, int dy);

}
