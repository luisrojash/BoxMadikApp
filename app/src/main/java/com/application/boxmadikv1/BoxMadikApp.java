package com.application.boxmadikv1;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.google.firebase.FirebaseApp;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class BoxMadikApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        FirebaseApp.initializeApp(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(BoxMadikApp.this);
    }


}
