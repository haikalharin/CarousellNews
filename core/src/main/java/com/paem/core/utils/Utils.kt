package com.paem.core.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Double.round(): Double {
    return (this * 10).toInt() / 10.0
}

fun getRelativeTimeSpan(millis: Long): String {
    val now = System.currentTimeMillis()
    val diff = (now/1000L)  - millis

    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val months = weeks / 4
    val years = months / 12

    return when {
        years >= 1 -> "$years year${if (years > 1) "s" else ""} ago"
        months >= 1 -> "$months month${if (months > 1) "s" else ""} ago"
        weeks >= 1 -> "$weeks week${if (weeks > 1) "s" else ""} ago"
        days >= 1 -> "$days day${if (days > 1) "s" else ""} ago"
        hours >= 1 -> "$hours hour${if (hours > 1) "s" else ""} ago"
        minutes >= 1 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
        else -> "$seconds second${if (seconds > 1) "s" else ""} ago"
    }
}
