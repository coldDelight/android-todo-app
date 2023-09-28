package com.chan.todolist.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateUtil {
    fun getDate(): String {
        val format = SimpleDateFormat("yyyy.MM.dd", Locale("ko-KR"))
        val curTime = System.currentTimeMillis()
        return format.format(Date(curTime))
    }

    fun getDayWeek(): String {
        return when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> "일"
            2 -> "월"
            3 -> "화"
            4 -> "수"
            5 -> "목"
            6 -> "금"
            else -> "토"
        }
    }
}