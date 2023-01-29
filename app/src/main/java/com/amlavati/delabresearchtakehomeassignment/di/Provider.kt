package com.amlavati.delabresearchtakehomeassignment.di

import android.content.Context
import com.amlavati.delabresearchtakehomeassignment.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Provider class for hilt dependencies
 */

@Module
@InstallIn(SingletonComponent::class)
object Provider {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext appContext: Context): App {
        return appContext as App
    }
}