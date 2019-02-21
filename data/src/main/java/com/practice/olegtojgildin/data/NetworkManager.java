package com.practice.olegtojgildin.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by olegtojgildin on 17/02/2019.
 */

public final class NetworkManager {

    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting());
    }

}
