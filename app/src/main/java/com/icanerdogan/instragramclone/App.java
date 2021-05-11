package com.icanerdogan.instragramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("n98FcGXARUPx6DYSoyXr8Jjsyk")
                .clientKey("dhW3OhIWii6vfFDuvMQNIezUv4f")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
