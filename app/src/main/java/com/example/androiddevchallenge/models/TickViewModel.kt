/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TickViewModel(type: TickType) : ViewModel() {
    val type: TickType = type
    private val _value = MutableLiveData(0.0f)
    val value: LiveData<Float> = _value

    fun onValueChanged(newValue: Float) {
        _value.value = newValue
    }

    fun seconds(): Int {
        return (type.getMax().toFloat() * (_value.value ?: 0.0f)).toInt()
    }

    fun displayValue(value: Float?): String {
        val max = type.getMax()
        return "${(max.toFloat() * (value ?: 0.0f)).toInt()}${type.getUnit()}"
    }

    fun progress(time: Long): Float {
        val max = type.getMax()
        val value = seconds() - type.getValue(time)
        return value.toFloat() / max.toFloat()
    }

    fun displayTime(time: Long): String {
        return "${seconds() - type.getValue(time)}${type.getUnit()}"
    }
}
