package com.example.to_doapp.database

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {

    @TypeConverter
    fun convertFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertToDate(time: Long): Date {
        return Date(time)
    }

}