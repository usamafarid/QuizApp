package com.example.quizapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizapp.model.LeaderBoardModel


@Dao
interface LeaderBoardDAO {

   @Insert
   suspend fun insertResult(result: LeaderBoardModel)
@Query("SELECT * FROM leaderboard ORDER BY score > 0 DESC LIMIT 5")
    fun  getTopScores(): LiveData<List<LeaderBoardModel>>
}