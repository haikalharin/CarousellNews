package com.haikal.carousellTest

import android.app.Application
import com.haikal.carousellTest.di.appModule
import com.haikal.carousellTest.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CarouselTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CarouselTestApp)
            modules(
                listOf(
                    vmModule,
                    appModule,
                )
            )
        }
    }
}