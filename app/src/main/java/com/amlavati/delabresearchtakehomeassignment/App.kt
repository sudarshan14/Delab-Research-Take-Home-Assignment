package com.amlavati.delabresearchtakehomeassignment

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.amlavati.delabresearchtakehomeassignment.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

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

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }


    override fun onStart(owner: LifecycleOwner) {
        isInBackground = false
    }

    override fun onStop(owner: LifecycleOwner) {
        isInBackground = true
    }
}