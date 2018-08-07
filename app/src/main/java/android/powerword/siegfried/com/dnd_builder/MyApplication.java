package android.powerword.siegfried.com.dnd_builder;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    public static MyApplication context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    public void onTerminate() {
        context = null;
        super.onTerminate();
    }
}
