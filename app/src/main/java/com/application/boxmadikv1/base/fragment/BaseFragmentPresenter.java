package com.application.boxmadikv1.base.fragment;

import android.os.Bundle;

import com.application.boxmadikv1.base.BasePresenter;
import com.application.boxmadikv1.base.BaseView;

public interface BaseFragmentPresenter <T extends BaseView> extends BasePresenter<T> {

    void onCreateView();

    void onViewCreated();

    void onActivityCreated();

    void onDestroyView();

    void onDetach();

    void setExtras(Bundle extras);
}