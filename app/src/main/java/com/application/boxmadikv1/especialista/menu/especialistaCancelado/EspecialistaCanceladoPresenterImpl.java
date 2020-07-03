package com.application.boxmadikv1.especialista.menu.especialistaCancelado;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;

public class EspecialistaCanceladoPresenterImpl extends BaseFragmentPresenterImpl<EspecialistaCanceladoView> implements EspecialistaCanceladoPresenter {

    public static final String TAG = EspecialistaCanceladoPresenterImpl.class.getSimpleName();


    public EspecialistaCanceladoPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onActivityCreated() {

    }
}