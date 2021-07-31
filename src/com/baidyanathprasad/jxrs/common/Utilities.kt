package com.baidyanathprasad.jxrs.common

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

fun convertDateToLocalDate(date: Date): LocalDateTime {
    return date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}
