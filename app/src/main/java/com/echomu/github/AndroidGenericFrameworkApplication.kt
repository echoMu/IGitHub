package com.echomu.github

import androidx.multidex.MultiDexApplication
import com.echomu.github.di.applicationModules
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by echoMu.
 */
class AndroidGenericFrameworkApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        MMKV.initialize(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AndroidGenericFrameworkApplication)
            androidFileProperties()
            fragmentFactory()
            modules(applicationModules)
        }
    }

    companion object {
        lateinit var instance: AndroidGenericFrameworkApplication
    }

}