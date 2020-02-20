package me.test.jcdecaux

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JCDecauxTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@JCDecauxTestApplication)
            modules(appModules)
        }
    }
}
