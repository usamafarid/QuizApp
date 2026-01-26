package com.example.quizapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.LeaderBoardModel
import com.example.quizapp.repository.QuizRepository
import com.google.android.material.color.utilities.Score.score
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date


class ResultViewModel(val repository: QuizRepository): ViewModel() {
    fun insertResult(result: LeaderBoardModel){
        viewModelScope.launch(Dispatchers.IO) {
            val result= LeaderBoardModel(0, name = "",0,date= Date())
            repository.insertResult(result)
        }

    }
    fun getResult(){
      viewModelScope.launch(Dispatchers.IO){
          repository.getTopScore()
      }
    }
}