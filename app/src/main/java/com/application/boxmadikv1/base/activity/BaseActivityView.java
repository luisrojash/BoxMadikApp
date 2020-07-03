package com.application.boxmadikv1.base.activity;

import com.application.boxmadikv1.base.BasePresenter;
import com.application.boxmadikv1.base.BaseView;

public interface BaseActivityView <T extends BasePresenter> extends BaseView<T> {

    void mostrarProgressBar();

    void ocultarProgressBar();



}
