package com.application.boxmadikv1.bandeja;

import com.application.boxmadikv1.bandeja.entidad.BandejaUi;
import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface BandejaPresenter extends BaseActivityPresenter<BandejaView>{
    void onPreferencesData(String userKey, String paisCodigo);

    void onClickBandeja(BandejaUi bandejaUi);

    void onDataRealTime(String mensaje, String codigoGrupoChat, String timeStamp);
}
