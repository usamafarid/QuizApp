package com.example.quizapp.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.LeaderBoardModel
import com.example.quizapp.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeaderBoardVM(private  val repository: QuizRepository): ViewModel() {
    fun insertResult(result: LeaderBoardModel){
        viewModelScope.launch(Dispatchers.IO){

            repository.insertResult(result)
        }

    }

    val topScore: LiveData<List<LeaderBoardModel>> =repository.getTopScore()

//    private val _score= MutableLiveData<List<LeaderBoardModel>>()
//
//    val score: LiveData<List<LeaderBoardModel>>
//        get() = _score



//    fun getTopScore(){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.getTopScore()
//        }
//
//    }
    //only use this
    fun saveScore(leaderBoardModel: LeaderBoardModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertResult(leaderBoardModel)
        }

    }
}