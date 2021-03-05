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

interface ITickType {
    fun getMax(): Int
    fun getUnit(): String
    fun getValue(time: Long): Int
}

enum class TickType : ITickType {
    Second {
        override fun getMax() = 60
        override fun getUnit() = "S"
        override fun getValue(time: Long): Int {
            return ((time % 60000) / 1000).toInt()
        }
    },
    Minute {
        override fun getMax() = 60
        override fun getUnit() = "M"
        override fun getValue(time: Long): Int {
            return ((time % 3600000) / 60000).toInt()
        }
    },
    Hour {
        override fun getMax() = 24
        override fun getUnit() = "H"
        override fun getValue(time: Long): Int {
            return (time / 3600000).toInt()
        }
    }
}
