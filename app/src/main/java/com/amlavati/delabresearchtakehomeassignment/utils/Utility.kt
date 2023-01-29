package com.amlavati.delabresearchtakehomeassignment.utils

object Utility {
    // 60 seconds timer value
    const val TIME_COUNTDOWN: Long = 60000L

    // time format to show seconds
    private const val TIME_FORMAT_SECONDS = "%02d"
    private const val TIME_FORMAT_MILLIES = "%03d"

    // extension function to format time in seconds milli seconds
    fun Long.formatTime(): String {

        val sec = (this / 1000).toInt()
        val milliSeconds = (this % 1000).toInt()

        val secondsText = String.format(TIME_FORMAT_SECONDS, sec)
        val milliSecondsText = String.format(
            TIME_FORMAT_MILLIES,
            milliSeconds
        )

        return "$secondsText : $milliSecondsText"

    }
}