package com.example.core

import android.app.Application
import com.example.di.DaggerApplicationComponent
import timber.log.Timber

class App : Application() {
        val component by lazy {
                DaggerApplicationComponent.factory().create(this)
        }

        override fun onCreate() {
                super.onCreate()
                Timber.plant(object : Timber.DebugTree() {
                        override fun createStackElementTag(element: StackTraceElement): String {
                                return super.createStackElementTag(element) + ":" + element.methodName
                        }
                })
        }
}
