package com.example.newmanager

import android.app.Application
import com.example.newmanager.Enums.Location

class Manager : Application() {

    private var version:Double = 0.0

    private var ignoreDbVersion: Boolean = false

    private var dbPath:String = ""

    private var dbLocation: Location = Location.Undefined

}