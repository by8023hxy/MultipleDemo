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
class TestIntentFlagActivityC : BaseActivity() {
    override fun setLayoutId(): Int = R.layout.intent_flag_base_layout

    override fun initView() {

    }

    override fun setViewData() {

    }

    override fun onViewClick() {
        setOnClickNoDouble(listOf(btn_normal)){
            intent = Intent(this, TestIntentFlagActivityD::class.java)
            startActivity(intent)
        }
    }
}