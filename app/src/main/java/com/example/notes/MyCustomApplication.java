package com.example.notes;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyCustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration=new RealmConfiguration.Builder()
                .name("noteDatabase.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
