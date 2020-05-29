package com.example.quiz6room

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        var context: Context? = null
        val instance by lazy {
            this
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}