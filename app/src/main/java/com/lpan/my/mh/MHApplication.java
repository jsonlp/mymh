package com.lpan.my.mh;

import android.app.Application;

/**
 * Created by lpan on 2019/5/14.
 */
public class MHApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.setContext(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppContext.setContext(null);
    }
}
