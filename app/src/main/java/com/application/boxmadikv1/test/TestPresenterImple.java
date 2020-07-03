package com.application.boxmadikv1.test;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;

public class TestPresenterImple extends BaseActivityPresenterImpl<TestView> implements TestPresenter {

    public TestPresenterImple(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void OnClickButton() {
        String texto="Hola Ya esta";
        if (view!=null){
            view.cambiartexto(texto);
        }
    }
}
