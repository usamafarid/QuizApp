package com.example.quizapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Questions",
    foreignKeys = [
        ForeignKey(
            entity = CategoryModel::class,
            parentColumns = ["cid"],
            childColumns = ["cid"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["cid"])]
)
data class QuestionModel(
    @PrimaryKey(autoGenerate = true)
    val qid: Int=0,
    var questionText: String = "",
    var optionA: String = "",
    var optionB: String = "",
    var optionC: String = "",
    var optionD: String = "",
    val correct: String = "",
    val userSelected: String?,
  //  val category: String = "",
    val cid: Int=0
)
