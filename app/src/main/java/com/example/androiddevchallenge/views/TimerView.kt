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
package com.example.androiddevchallenge.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.components.ActionButtons
import com.example.androiddevchallenge.components.TickItem
import com.example.androiddevchallenge.models.TimerViewModel

@ExperimentalAnimationApi
@Composable
fun TimerView(viewModel: TimerViewModel = TimerViewModel()) {
    Box {
        Column(
            Modifier
                .padding(15.dp, 15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            viewModel.ticks.forEach { tick ->
                TickItem(tick, viewModel.isRunning, viewModel.timePassed)
            }
            ActionButtons(
                isRunning = viewModel.isRunning,
                onStart = { viewModel.start() },
                onStop = { viewModel.stop() }
            )
        }
    }
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
        if (viewModel.isRunning || viewModel.isFinished) {
            BoxWithConstraints {
                val offsetY: Float by animateFloatAsState(
                    if (viewModel.isFinished) -constraints.maxHeight.toFloat() else 0.0f,
                    animationSpec = tween(durationMillis = 3000, easing = FastOutSlowInEasing)
                )
                Image(
                    painter = painterResource(R.drawable.ic_rocket),
                    contentDescription = "Android rocket launch!",
                    Modifier.offset(y = Dp(offsetY))
                )
            }
        }
    }
}
