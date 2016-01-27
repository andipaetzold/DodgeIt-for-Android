package de.andipaetzold.dodgeit;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static final String LOG_TAG = "DODGE_IT";

    private static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getContext() {
        return App.context;
    }
}
