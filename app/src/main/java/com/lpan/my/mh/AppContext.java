package com.lpan.my.mh;

import android.content.Context;

/**
 * Created by lpan on 2019/5/14.
 */
public class AppContext {

    static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AppContext.context = context;
    }
}
