package com.baiyu.mytestdemo.util

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.baiyu.mytestdemo.ext.loge
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils

/**
 * @author Baiyu
 * @date :2020/6/5 8:11 PM June
 * @version: 1.0
 */
class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {

    companion object{
        const val LOG_TAG="IntentFlag_Demo"
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        "onActivityCreated : ${activity.javaClass.simpleName}".loge(LOG_TAG)
        printTask()
    }

    override fun onActivityStarted(activity: Activity) {
        "onActivityStarted : ${activity.javaClass.simpleName}".loge(LOG_TAG)
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {
    }

    private fun printTask(){
        val listName=ArrayList<String>()
        for (activity in ActivityUtils.getActivityList()){
            listName.add(activity.javaClass.simpleName)
        }
        LogUtils.json(LOG_TAG,listName)
    }


    override fun onActivityDestroyed(activity: Activity) {
        "onActivityDestroyed : ${activity.javaClass.simpleName}".loge(LOG_TAG)
        printTask()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity) {

    }


}