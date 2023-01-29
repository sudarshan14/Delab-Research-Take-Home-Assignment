package com.amlavati.delabresearchtakehomeassignment

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), DefaultLifecycleObserver {


    /**
     * [isInBackground] will be used to show notification if app is in background
     */
    companion object {
        var isInBackground: Boolean = false
    }

    override fun onCreate() {
        super<Application>.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }


    override fun onStart(owner: LifecycleOwner) {
        isInBackground = false
    }

    override fun onStop(owner: LifecycleOwner) {
        isInBackground = true
    }
}