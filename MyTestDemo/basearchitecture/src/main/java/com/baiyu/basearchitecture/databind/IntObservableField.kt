package com.baiyu.basearchitecture.databind

import androidx.databinding.ObservableField

class IntObservableField(value: Int = 0) : ObservableField<Int>(value) {

    override fun get(): Int {
        return super.get()!!
    }

}