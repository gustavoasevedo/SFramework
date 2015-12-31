package com.dss.sframework;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Marcelo on 9/23/15.
 */
public class sFrameworkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "6OgQhKXZ09fnbtXHz3s6MuRaW9LyaX3D1V1PXp6E", "VnA36H2PI0ZXkZoUuNidqQ9SEdkNXTuhiZZvwK3N");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
