package com.baiyu.mytestdemo.intentflag

import android.content.Intent
import com.baiyu.mytestdemo.R
import com.baiyu.mytestdemo.base.BaseActivity
import com.baiyu.mytestdemo.ext.setOnClickNoDouble
import kotlinx.android.synthetic.main.intent_flag_base_layout.*

/**
 * @author Baiyu
 * @date :2020/6/4 8:18 PM June
 * @version: 1.0
 */
class TestIntentFlagActivityF : BaseActivity() {
    override fun setLayoutId(): Int = R.layout.intent_flag_base_layout

    override fun initView() {

    }

    override fun setViewData() {

    }

    override fun onViewClick() {
        intent = Intent(this, TestIntentFlagActivityA::class.java)
        setOnClickNoDouble(
            listOf(
                btn_normal,
                btn_clear_top,
                btn_clear_single_top,
                btn_clear_newtask_top
            )
        ) {
            when (it.id) {
                R.id.btn_normal -> {
                    //正常跳转 不加flag
                }
                R.id.btn_clear_top -> {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                R.id.btn_clear_single_top -> {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
                R.id.btn_clear_newtask_top -> {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                }

            }
            startActivity(intent)
        }
    }
}