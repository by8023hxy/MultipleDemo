package com.baiyu.mytestdemo.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.baiyu.mytestdemo.ext.loge
import com.baiyu.mytestdemo.util.KtxLifeCycleCallBack
import kotlinx.android.synthetic.main.intent_flag_base_layout.*
import kotlinx.android.synthetic.main.normal_task_layout.*
import kotlinx.android.synthetic.main.normal_title_layout.*

/**
 * @author Baiyu
 * @date :2020/6/4 7:59 PM June
 * @version: 1.0
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        createView()
        initView()
        setViewData()
        onViewClick()
    }

    abstract fun setLayoutId(): Int
    abstract fun initView()
    abstract fun setViewData()
    open fun onViewClick() {}

    @SuppressLint("SetTextI18n")
    private fun createView() {
        mTitle.title = javaClass.simpleName
        mTaskId.text = "TaskId=$taskId"
        if ("TestIntentFlagActivityF" == javaClass.simpleName) {
            setViewStatus(View.VISIBLE)
        } else {
            setViewStatus(View.GONE)
        }

    }

    private fun setViewStatus(viewStatus: Int) {
        btn_new_task?.visibility = viewStatus
        btn_clear_top?.visibility = viewStatus
        btn_clear_single_top?.visibility = viewStatus
        btn_clear_newtask_top?.visibility = viewStatus
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        "onNewIntent : ${javaClass.simpleName}".loge(KtxLifeCycleCallBack.LOG_TAG)
    }

}
