package com.example.quizapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Category")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    val cid: Int=0 ,
//    val image: Int,
    val name: String = "",
    val text: String = "",
    val imagePath:String=""
)
