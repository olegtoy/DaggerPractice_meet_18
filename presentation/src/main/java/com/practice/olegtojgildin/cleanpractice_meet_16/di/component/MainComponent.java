package com.practice.olegtojgildin.cleanpractice_meet_16.di.component;

import com.practice.olegtojgildin.cleanpractice_meet_16.di.module.AppModule;
import com.practice.olegtojgildin.cleanpractice_meet_16.ui.MainActivity;

import dagger.Subcomponent;

/**
 * Created by olegtojgildin on 03/03/2019.
 */

@Subcomponent()
public interface MainComponent {

    void inject(final MainActivity mainActivity);

}
