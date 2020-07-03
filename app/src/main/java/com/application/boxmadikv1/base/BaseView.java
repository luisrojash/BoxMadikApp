package com.application.boxmadikv1.base;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
