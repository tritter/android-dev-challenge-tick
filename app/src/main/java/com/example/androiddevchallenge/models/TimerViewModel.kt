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

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Date

class TimerViewModel : ViewModel() {
    val ticks = listOf(TickViewModel(type = TickType.Hour), TickViewModel(type = TickType.Minute), TickViewModel(type = TickType.Second))
    private fun time() = System.currentTimeMillis()
    var timePassed by mutableStateOf(time())
    var isRunning by mutableStateOf(running())
    var isFinished by mutableStateOf(false)
    var countDownTimer: CountDownTimer? = null
    var startDate: Date? = null
    private fun running() = startDate != null

    private fun updateTime() {
        timePassed = time() - (startDate?.time ?: 0)
        isRunning = running()
    }

    private fun milliseconds(): Long {
        return (ticks.sumBy { it.seconds() } * 1000).toLong()
    }

    fun start() {
        isFinished = false
        countDownTimer = object : CountDownTimer(milliseconds(), 100) {
            override fun onTick(millisUntilFinished: Long) { updateTime() }
            override fun onFinish() { finish() }
        }
        startDate = Date()
        countDownTimer?.start()
    }

    fun stop() {
        countDownTimer?.cancel()
        startDate = null
        updateTime()
    }

    fun finish() {
        stop()
        updateTime()
        isFinished = true
    }
}
