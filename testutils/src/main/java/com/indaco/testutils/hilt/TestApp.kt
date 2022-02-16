package com.indaco.testutils.hilt

import android.app.Application

open class TestApp: Application() {

    companion object {
        lateinit var instance: TestApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}