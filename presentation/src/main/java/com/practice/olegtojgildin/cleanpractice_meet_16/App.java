package com.practice.olegtojgildin.cleanpractice_meet_16;

import android.app.Application;

import com.practice.olegtojgildin.cleanpractice_meet_16.di.component.AppComponent;
import com.practice.olegtojgildin.cleanpractice_meet_16.di.component.DaggerAppComponent;
import com.practice.olegtojgildin.cleanpractice_meet_16.di.module.AppModule;

/**
 * Created by olegtojgildin on 03/03/2019.
 */


public final class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        prepareDaggerComponents();
    }

    private void prepareDaggerComponents() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
