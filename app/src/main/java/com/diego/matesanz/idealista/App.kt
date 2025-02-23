package com.diego.matesanz.idealista

import android.app.Application
import com.diego.matesanz.idealista.data.DataKoinModule
import com.diego.matesanz.idealista.framework.FrameworkKoinModule
import com.diego.matesanz.idealista.framework.frameworkKoinModule
import com.diego.matesanz.idealista.usecases.UseCasesKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.ksp.generated.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                AppKoinModule().module,
                DataKoinModule().module,
                UseCasesKoinModule().module,
                FrameworkKoinModule().module,
                frameworkKoinModule,
            )
        }
    }
}
