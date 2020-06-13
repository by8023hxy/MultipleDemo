package com.baiyu.basearchitecture.databind

import androidx.databinding.ObservableField

class DoubleObservableField(value: Double = 0.0) : ObservableField<Double>(value) {

    override fun get(): Double {
        return super.get()!!
    }

}