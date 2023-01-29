package com.amlavati.delabresearchtakehomeassignment.utils

import android.util.Log
import com.amlavati.delabresearchtakehomeassignment.BuildConfig


const val TAG = "DelabCounter"

fun printDebugLog(message: String) {

    if (BuildConfig.DEBUG) {
        Log.d(
            TAG,
            "$message   name: ${Thread.currentThread().name} , id: ${Thread.currentThread().id}"
        )
    }
}

fun printDebugLog(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}

fun printErrorLog(message: String) {

    if (BuildConfig.DEBUG) {
        Log.e(TAG, message)
    }
}
