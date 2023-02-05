package com.app.fintechlab

import android.app.Application
import com.app.fintechlab.di.AppComponent
import com.app.fintechlab.di.DaggerAppComponent

open class FilmsApp : Application()  {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}