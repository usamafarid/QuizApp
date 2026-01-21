package com.example.quizapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "LeaderBoard")
data class LeaderBoardModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String,
    val score: Int,
    val date: Date
//    val totalQuestion: Int,
//    val time: Int
)