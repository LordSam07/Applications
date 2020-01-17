package com.lordsam.weekdays

data class WeekDay(var day: String)

object Supplier{
    var days = listOf<WeekDay>(
        WeekDay("Sunday"),
        WeekDay("Monday"),
        WeekDay("Tuesday"),
        WeekDay("Wednesday"),
        WeekDay("Thursday"),
        WeekDay("Friday"),
        WeekDay("Saturday")
    )
}
