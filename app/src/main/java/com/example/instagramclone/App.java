package com.example.instagramclone;

import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("dp9BFM39OSaGv7ZAWwFwfP5uIiApyYuXYPSuYvLG")
                // if defined
                .clientKey("A97V1R6B1aKwIXVrAU2MwVTdxAzFNAaeEerJ0A9E")
                .server("https://parseapi.back4app.com/")
                .build()
        );



    }
}
