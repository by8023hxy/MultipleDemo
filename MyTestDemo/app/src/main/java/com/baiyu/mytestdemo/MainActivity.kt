package com.baiyu.mytestdemo

import android.content.Intent
import com.baiyu.mytestdemo.base.BaseActivity
import com.baiyu.mytestdemo.ext.setOnClickNoDouble
import com.baiyu.mytestdemo.intentflag.TestIntentFlagActivityA
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun setLayoutId(): Int =R.layout.activity_main

    override fun initView() {

    }

    override fun setViewData() {

    }

    override fun onViewClick() {
        setOnClickNoDouble(listOf(mFlagTest)){
            when(it.id){
                R.id.mFlagTest->{
                    startActivity(Intent(this,TestIntentFlagActivityA::class.java))
                }
            }

        }
    }
}
