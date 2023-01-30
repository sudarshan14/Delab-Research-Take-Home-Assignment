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


    /*  private fun usingHandler(){

        private var countDownTimerHandler = Handler(Looper.getMainLooper())
      private var runnable: Runnable? = null
          var millisUntilFinished = Utility.TIME_COUNTDOWN
          val countDownInterval = 1000L
          var timeInMilliSeconds = 0L
          var startTime = SystemClock.uptimeMillis() + millisUntilFinished
          printDebugLog("startTime $startTime")
          var updateTime = 0L
          runnable = object : Runnable {
              override fun run() {
                  //    startTime = SystemClock.uptimeMillis() - 3234566565L

  //                timeInMilliSeconds = SystemClock.uptimeMillis() -startTime
  //                printDebugLog("timeInMilliSeconds $timeInMilliSeconds")
  //                updateTime = 0L + timeInMilliSeconds

                  millisUntilFinished -= countDownInterval
                  //   millisUntilFinished -= d
                  val progressValue = millisUntilFinished.toFloat() / Utility.TIME_COUNTDOWN
                  val progressValueRoundOff = (progressValue * 100.0).roundToInt() / 100.0
                  handleTimerValues(true, millisUntilFinished, progressValueRoundOff.toFloat())

                  countDownTimerHandler.postDelayed(this, 1000L)
              }
          }
      }*/
}