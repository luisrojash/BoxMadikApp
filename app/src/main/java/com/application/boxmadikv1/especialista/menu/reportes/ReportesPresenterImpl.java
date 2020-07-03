package com.application.boxmadikv1.especialista.menu.reportes;

import android.content.res.Resources;

import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;

public class ReportesPresenterImpl extends BaseActivityPresenterImpl<ReportesView> implements ReportesPresenter {

    public ReportesPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }
}
