package com.application.boxmadikv1.base.activity;


import android.os.Bundle;

import com.application.boxmadikv1.base.BasePresenter;


public interface BaseActivityPresenter<T extends BaseActivityView> extends BasePresenter<T> {
    void setExtras(Bundle extras);


}