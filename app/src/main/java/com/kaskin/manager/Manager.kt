package com.kaskin.manager

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Manager : MultiDexApplication() {

    /*override fun onCreate() {
        super.onCreate()
        try {
            ProviderInstaller.installIfNeeded(this)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
            Log.e("Manager", "onCreate: ", e)
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
            Log.e("Manager", "onCreate: ", e)

        }
    }*/
}