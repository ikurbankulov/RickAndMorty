package com.example.core

import android.app.Application
import com.example.di.DaggerApplicationComponent

class App: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}