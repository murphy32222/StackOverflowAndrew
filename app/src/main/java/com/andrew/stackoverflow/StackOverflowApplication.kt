package com.andrew.stackoverflow

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class StackOverflowApplication : Application() {

    private var defaultSubscribeScheduler: Scheduler? = null

    companion object {
        var instance: StackOverflowApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun defaultSubscribeScheduler(): Scheduler? {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io()
        }
        return defaultSubscribeScheduler
    }
}