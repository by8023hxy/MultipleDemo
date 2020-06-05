package com.baiyu.mytestdemo

import android.app.Application
import android.content.Context
import com.baiyu.mytestdemo.util.KtxLifeCycleCallBack
import kotlin.properties.Delegates

/**
 * @author Baiyu
 * @date :2020/6/5 8:15 PM June
 * @version: 1.0
 */
class DemoApplication : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        registerActivityLifecycleCallbacks(KtxLifeCycleCallBack())
    }
}