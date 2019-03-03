package com.practice.olegtojgildin.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Created by olegtojgildin on 17/02/2019.
 */

public final class NetworkManager {
    private final ConnectivityManager mConnectivityManager;

    @Inject
    public NetworkManager(final ConnectivityManager connectivityManager) {
        this.mConnectivityManager = connectivityManager;
    }

    public  boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = mConnectivityManager != null ? mConnectivityManager.getActiveNetworkInfo() : null;
        return (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting());
    }

}
