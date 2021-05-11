package com.icanerdogan.instragramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("n98FcGXARUPx6DYSoyXr8JjsykzpHa8Yr2sK5pbk")
                .clientKey("dhW3OhIWii6vfFDuvMQNIezUv4f5gPWvZmewAf2a")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
