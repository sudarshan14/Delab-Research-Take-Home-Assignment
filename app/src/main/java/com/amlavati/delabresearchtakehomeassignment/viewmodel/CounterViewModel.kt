package com.amlavati.delabresearchtakehomeassignment.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.amlavati.delabresearchtakehomeassignment.App
import com.amlavati.delabresearchtakehomeassignment.App.Companion.isInBackground
import com.amlavati.delabresearchtakehomeassignment.utils.Utility
import com.amlavati.delabresearchtakehomeassignment.utils.createNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.roundToInt


class CounterViewModel(private val context: App) :
    ViewModel() {

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private var countDownTimer: CountDownTimer? = null

    private val _time = MutableStateFlow(Utility.TIME_COUNTDOWN)
    val time: StateFlow<Long> = _time

    private val _progress = MutableStateFlow(1.00F)
    val progress: StateFlow<Float> = _progress

    fun handleCountDownTimer(stopAndReset: Boolean) {
        // stopAndReset is true when Stop button is clicked
        if (stopAndReset) {
            resetTimer()
            return
        }

        if (isPlaying.value) {
            pauseTimer()
        } else {
            startTimer()
        }

    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        handleTimerValues(false, Utility.TIME_COUNTDOWN, 1.0F)
    }

    private fun pauseTimer() {
        // pauses the timer and saves the values to restart
        countDownTimer?.cancel()
        handleTimerValues(false, _time.value, _progress.value)

    }

    private fun startTimer() {

        //countDownInterval is set to 1 to show milli seconds counter
        // if only seconds counter is required then update this value with 1000

        countDownTimer = object : CountDownTimer(_time.value, 1) {
            override fun onTick(millisUntilFinished: Long) {
                val progressValue = millisUntilFinished.toFloat() / Utility.TIME_COUNTDOWN
                val progressValueRoundOff = (progressValue * 100.0).roundToInt() / 100.0
                handleTimerValues(true, millisUntilFinished, progressValueRoundOff.toFloat())
            }

            override fun onFinish() {
                resetTimer()
                if (isInBackground)
                    createNotification(context = context.applicationContext, "Timer", "Timer Done")
            }

        }.start()
    }

    private fun handleTimerValues(
        isPlaying: Boolean,
        text: Long,
        progress: Float,
    ) {
        _isPlaying.value = isPlaying
        _time.value = text
        _progress.value = progress
    }


}