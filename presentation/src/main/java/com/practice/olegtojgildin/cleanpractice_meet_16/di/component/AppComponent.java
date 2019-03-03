package com.practice.olegtojgildin.cleanpractice_meet_16.di.component;

import com.practice.olegtojgildin.cleanpractice_meet_16.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by olegtojgildin on 03/03/2019.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    MainComponent mainSubComponent();
}