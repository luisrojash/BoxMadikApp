package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog;

import android.os.Bundle;

import com.application.boxmadikv1.base.BasePresenter;

public interface CursosDialogPresenter extends BasePresenter<CursosDialogView>{
    void onExtras(Bundle bundle);

    void onActivityCreated(Bundle savedInstanceState);
}
