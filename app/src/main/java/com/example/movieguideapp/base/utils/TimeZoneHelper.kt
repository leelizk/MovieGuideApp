package com.example.movieguideapp.base.utils

import java.text.SimpleDateFormat
import java.util.*

val NEW_YORK_TIME_ZONE: TimeZone = TimeZone.getTimeZone("America/New_York")

val UTC_TIME_ZONE: TimeZone = TimeZone.getTimeZone("UTC")

fun createCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeZone = UTC_TIME_ZONE
    return calendar
}

fun createSimpleDataFormat(format: String, timeZone: TimeZone = UTC_TIME_ZONE): SimpleDateFormat {
    val sdf = SimpleDateFormat(format, Locale.ENGLISH)
    sdf.timeZone = timeZone
    return sdf
}
