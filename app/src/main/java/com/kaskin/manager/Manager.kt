package com.kaskin.manager

import androidx.multidex.MultiDexApplication
import com.kaskin.manager.Enums.Location
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Manager : MultiDexApplication() {

    private var version: Double = 0.0

    private var ignoreDbVersion: Boolean = false

    private var dbPath: String = ""

    private var dbLocation: Location = Location.Undefined
}