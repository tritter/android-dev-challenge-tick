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
package com.example.androiddevchallenge.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.components.tick.TickProgress
import com.example.androiddevchallenge.components.tick.TickSlider
import com.example.androiddevchallenge.models.TickViewModel

@Composable
fun TickItem(viewModel: TickViewModel, running: Boolean, passed: Long) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(3f, fill = true).height(50.dp)) {
            if (running) {
                TickProgress(viewModel, passed)
            } else {
                TickSlider(viewModel)
            }
        }
        Box(Modifier.weight(1f).width(50.dp), contentAlignment = Alignment.Center) {
            Spacer(Modifier.size(5.dp))
            val value: Float by viewModel.value.observeAsState(0.0f)
            Text(if (running) viewModel.displayTime(passed) else viewModel.displayValue(value))
        }
    }
}
