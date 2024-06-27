package com.example.app

import android.app.Application
import org.example.umbrella.application.startApplication

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startApplication(this)
    }
}
