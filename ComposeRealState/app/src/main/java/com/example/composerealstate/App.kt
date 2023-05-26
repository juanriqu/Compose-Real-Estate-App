package com.example.composerealstate

import android.app.Application
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * The `App` class is the main application class for the Android app. It uses the Hilt dependency injection library
 * to generate the Hilt components required for dependency injection.
 */
@HiltAndroidApp
class App : Application() {
    /**
     * Overrides the `onCreate` method. Here `Timber` library is initialized and a debug tree is planted to enable logging during development.
     */
    override fun onCreate() {
        super.onCreate()
        Coil.setImageLoader(ImageLoader.Builder(this).build())
        Timber.plant(Timber.DebugTree())
    }
}