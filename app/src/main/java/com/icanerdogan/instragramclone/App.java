package com.icanerdogan.instragramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("YOUR_ID")
                .clientKey("YOUR_CLIENT_KEY")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
