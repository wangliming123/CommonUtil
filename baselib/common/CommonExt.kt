package cn.twtimes.baselib.common

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(format: String = "yyyyMMddHHmmss"): String =
    SimpleDateFormat(format, Locale.CHINA).format(this)


fun Long.format(format: String = "yyyyMMddHHmmss"): String =
    SimpleDateFormat(format, Locale.CHINA).format(Date(this))

val commonDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
