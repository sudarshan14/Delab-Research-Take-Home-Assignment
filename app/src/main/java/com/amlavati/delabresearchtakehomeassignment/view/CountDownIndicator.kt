package com.amlavati.delabresearchtakehomeassignment.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.amlavati.delabresearchtakehomeassignment.utils.printDebugLog

@Composable
fun CountDownIndicator(
    modifier: Modifier = Modifier, progress: Float, time: String, size: Int, stroke: Int
) {

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )
    printDebugLog("time $time :: progress $progress  ")
    Column(modifier = modifier) {
        Box {

            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .height(size.dp)
                    .width(size.dp),
                strokeWidth = stroke.dp,
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = time,
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

