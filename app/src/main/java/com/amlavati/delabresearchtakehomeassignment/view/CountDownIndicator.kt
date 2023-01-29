package com.amlavati.delabresearchtakehomeassignment.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
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


//            CircularProgressIndicatorBackGround(
//                modifier = Modifier
//                    .height(size.dp)
//                    .width(size.dp),
//                color = Color.Black,
//                stroke
//            )

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

/*
* composable to set background color of the progress bar*/

@Composable
fun CircularProgressIndicatorBackGround(
    modifier: Modifier = Modifier, color: Color, stroke: Int
) {
    val style = with(LocalDensity.current) { Stroke(stroke.dp.toPx()) }

    Canvas(modifier = modifier, onDraw = {

        val innerRadius = (size.minDimension - style.width) / 2

        drawArc(
            color = color, startAngle = 0f, sweepAngle = 360f, topLeft = Offset(
                (size / 2.0f).width - innerRadius, (size / 2.0f).height - innerRadius
            ), size = Size(innerRadius * 2, innerRadius * 2), useCenter = false, style = style
        )
    })
}