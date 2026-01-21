package com.example.quizapp.db

import androidx.room.TypeConverter
import java.util.Date

class Convertor {
    //insert or save into db
    @TypeConverter
    fun fromDateToLong(value: Date): Long{
    return value.time
    }

    //fetch ot get into db
    @TypeConverter
    fun fromLongToDate(value : Long): Date{
        return  Date(value)
    }
}