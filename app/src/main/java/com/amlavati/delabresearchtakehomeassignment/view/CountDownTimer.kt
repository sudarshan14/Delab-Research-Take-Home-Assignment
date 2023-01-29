package com.amlavati.delabresearchtakehomeassignment.view

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amlavati.delabresearchtakehomeassignment.utils.Utility
import com.amlavati.delabresearchtakehomeassignment.utils.Utility.formatTime
import com.amlavati.delabresearchtakehomeassignment.viewmodel.CounterViewModel
import com.google.accompanist.permissions.*

@Composable
fun CountDownTimer(
    viewModel: CounterViewModel = viewModel()
) {


    val time by viewModel.time.collectAsState(Utility.TIME_COUNTDOWN)
    val progress by viewModel.progress.collectAsState(1.00F)
    val isPlaying by viewModel.isPlaying.collectAsState(false)


    CountDownView(time = time.formatTime(), progress = progress, isPlaying = isPlaying) {
        viewModel.handleCountDownTimer(it)
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CountDownView(
    time: String,
    progress: Float,
    isPlaying: Boolean,
    optionSelected: (stopTime: Boolean) -> Unit
) {


//    checking notification permission on onStart event

    val permission: PermissionState =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    if (!permission.status.isGranted)
                        permission.launchPermissionRequest()
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CountDownIndicator(
            progress = progress,
            time = time,
            size = 150,
            stroke = 5
        )

        Row(
            Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = {
                    optionSelected(false)
                },
                modifier =
                Modifier
                    .height(70.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(25.dp),
            ) {
                val pair = if (!isPlaying) {
                    "Start"
                } else {
                    "Pause"
                }
                Text(text = pair)
            }

            Button(
                onClick = {
                    optionSelected(true)
                },

                modifier =
                Modifier
                    .height(70.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(25.dp),
                enabled = isPlaying
            ) {
                Text(text = "Stop")
            }

        }

    }
}
