package com.example.quizapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizapp.model.QuestionModel

@Dao
interface QuestionDAO {

     @Insert
    suspend fun insertQuestions(questionModel: QuestionModel)

    @Query("Select * FROM Questions where cid=:categoryID")
    suspend fun getQuestions(categoryID: Int): List<QuestionModel>
}